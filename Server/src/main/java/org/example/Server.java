package org.example;

import org.example.exceptions.*;
import org.example.interactions.RequestTemplate;
import org.example.interactions.ResponseResult;
import org.example.interactions.ResponseTemplate;
import org.example.utilities.ConsoleWriter;
import org.example.utilities.RequestHandler;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.*;

/**
 * Runs the server.
 */
public class Server {
    private final int port;
    private final int soTimeout;
    private ServerSocket serverSocket;
    private final RequestHandler requestHandler;

    public Server(int port, int soTimeout, RequestHandler requestHandler) {
        this.port = port;
        this.soTimeout = soTimeout;
        this.requestHandler = requestHandler;
    }

    /**
     * Begins server operation.
     */
    public void run() {
        try{
            openServerSocket();
            boolean processingStatus = true;
            while (processingStatus){
                try (Socket clientSocket = connectToClient()){
                    processingStatus = processClientRequest(clientSocket);
                } catch (ConnectionErrorException | SocketTimeoutException exception){
                    break;
                } catch (IOException exception){
                    ConsoleWriter.printError("An error occurred while trying to terminate the connection with the client!");
                }
            }
            stop();
        } catch (OpeningServerSocketException exception){
            ConsoleWriter.printError("The server cannot be started!");
        }
    }

    /**
     * Finishes server operation.
     */
    private void stop() {
        try{
            if(serverSocket == null) throw new ClosingSocketException();
            serverSocket.close();
            ConsoleWriter.printLn("The server operation has been successfully completed.");
        } catch (ClosingSocketException exception) {
            ConsoleWriter.printError("It is impossible to shut down a server that has not yet started!");
        } catch (IOException exception) {
            ConsoleWriter.printError("An error occurred when shutting down the server!");
        }
    }

    /**
     * Open server socket.
     */
    private void openServerSocket() throws OpeningServerSocketException {
        try{
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(soTimeout);
        } catch (IllegalArgumentException exception) {
            ConsoleWriter.printError("Port '" + port + "' is beyond the limits of possible values!");
            throw new OpeningServerSocketException();
        } catch (IOException exception) {
            ConsoleWriter.printError("An error occurred while trying to use the port '" + port + "'!");
            throw new OpeningServerSocketException();
        }
    }

    /**
     * Connecting to client.
     */
    private Socket connectToClient() throws ConnectionErrorException, SocketTimeoutException {
        try{
            ConsoleWriter.printLn("Listening port '" + port + "'...");
            Socket clientSocket = serverSocket.accept();
            ConsoleWriter.printLn("The connection with the client has been successfully established.");
            return clientSocket;
        } catch (SocketTimeoutException exception) {
            ConsoleWriter.printError("Connection timeout exceeded!");
            throw new SocketTimeoutException();
        } catch (IOException exception) {
            ConsoleWriter.printError("An error occurred while connecting to the client!");
            throw new ConnectionErrorException();
        }
    }

    private boolean processClientRequest(Socket clientSocket) {
        RequestTemplate userRequest = null;
        ResponseTemplate responseToUser;
        try (ObjectInputStream clientReader = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream clientWriter = new ObjectOutputStream(clientSocket.getOutputStream())) {
            do {
                userRequest = (RequestTemplate) clientReader.readObject();
                responseToUser = requestHandler.handle(userRequest);
                clientWriter.writeObject(responseToUser);
                clientWriter.flush();
            } while(responseToUser.getResponseResult() != ResponseResult.SERVER_EXIT);
            return false;
        } catch (ClassNotFoundException exception){
            ConsoleWriter.printError("An error occurred while reading the received data!");
        }catch (InvalidClassException | NotSerializableException exception) {
            ConsoleWriter.printError("An error occurred when sending data to the client!");
        } catch (IOException exception) {
            if (userRequest == null) {
                ConsoleWriter.printError("Unexpected disconnection from the client!");
            } else {
                ConsoleWriter.printLn("The client has been successfully disconnected from the server!");
            }
        }
        return true;
    }

}
