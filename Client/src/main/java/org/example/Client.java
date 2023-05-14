package org.example;

import com.sun.net.httpserver.Request;
import org.example.exceptions.ClosingSocketException;
import org.example.exceptions.ConnectionErrorException;
import org.example.exceptions.NotInDeclaredLimitsException;
import org.example.interactions.RequestTemplate;
import org.example.interactions.ResponseTemplate;
import org.example.utilities.ConsoleWriter;
import org.example.utilities.UserHandler;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Runs the client.
 */
public class Client {
    private final String host;
    private final int port;
    private final int reconnectionTimeout;
    private int reconnectionAttempts;
    private final int maxReconnectionAttempts;
    private final UserHandler userHandler;
    private SocketChannel socketChannel;
    private ObjectOutputStream serverWriter;
    private ObjectInputStream serverReader;

    public Client(String host, int port, int reconnectionTimeout, int maxReconnectionAttempts, UserHandler userHandler) {
        this.host = host;
        this.port = port;
        this.reconnectionTimeout = reconnectionTimeout;
        this.maxReconnectionAttempts = maxReconnectionAttempts;
        this.userHandler = userHandler;
    }

    /**
     * Begins client operation.
     */
    public void run() {
        try {
            boolean processingStatus = true;
            while (processingStatus) {
                try {
                   // connectToServer();
                    processingStatus = processRequestToServer();
                } catch (ConnectionErrorException exception) {
                    if (reconnectionAttempts >= maxReconnectionAttempts) {
                        ConsoleWriter.printError("Exceeded the number of connection attempts!");
                        break;
                    }
                    try {
                        Thread.sleep(reconnectionTimeout);
                    } catch (IllegalArgumentException timeoutException) {
                        ConsoleWriter.printError("Connection waiting time'" + reconnectionTimeout +
                                "' is beyond the limits of possible values!");
                        ConsoleWriter.printLn("Reconnection will be performed immediately.");
                    } catch (Exception timeoutException) {
                        ConsoleWriter.printError("An error occurred while trying to wait for connection!");
                        ConsoleWriter.printLn("Reconnection will be performed immediately.");
                    }
                }
                reconnectionAttempts++;
            }
            if (socketChannel != null) socketChannel.close();
            ConsoleWriter.printLn("The client's work has been successfully completed.");
        } catch (NotInDeclaredLimitsException exception) {
            ConsoleWriter.printError("The client cannot be started!");
        } catch (IOException exception) {
            ConsoleWriter.printError("An error occurred while trying to terminate the connection with the server!");
        }
    }

    /**
     * Connecting to server.
     */
    private void connectToServer() throws ConnectionErrorException, NotInDeclaredLimitsException {
        try {
            if (reconnectionAttempts >= 1) ConsoleWriter.printLn("Reconnecting to the server...");
            socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
            ConsoleWriter.printLn("The connection to the server has been successfully established.");
            ConsoleWriter.printLn("Waiting for permission to exchange data...");
            serverWriter = new ObjectOutputStream(socketChannel.socket().getOutputStream());
            serverReader = new ObjectInputStream(socketChannel.socket().getInputStream());
            ConsoleWriter.printLn("Permission to exchange data has been received.");
        } catch (IllegalArgumentException exception) {
            ConsoleWriter.printError("The server address is entered incorrectly!");
            throw new NotInDeclaredLimitsException();
        } catch (IOException exception) {
            ConsoleWriter.printError("An error occurred while connecting to the server!");
            throw new ConnectionErrorException();
        }
    }
    private void disconnectFromServer(){
        try{
            if(socketChannel == null) throw new ClosingSocketException();
            socketChannel.close();
            ConsoleWriter.printLn("The client operation has been successfully completed.");
        } catch (ClosingSocketException exception) {
            ConsoleWriter.printError("It is impossible to shut down a client that has not yet started!");
        } catch (IOException exception) {
            ConsoleWriter.printError("An error occurred when shutting down the client!");
        }
    }

    /**
     * Server request process.
     */
    private boolean processRequestToServer() throws  ConnectionErrorException, NotInDeclaredLimitsException {
        RequestTemplate requestToServer = null;
        ResponseTemplate serverResponse = null;
        do {
            try {
                requestToServer = serverResponse != null ? userHandler.handle(serverResponse.getResponseResult()) :
                        userHandler.handle(null);
                if (requestToServer.isEmpty()) continue;

                connectToServer();
                serverWriter.writeObject(requestToServer);
                serverResponse = (ResponseTemplate) serverReader.readObject();
                ConsoleWriter.print(serverResponse.getResponseBody());
                disconnectFromServer();

            } catch (InvalidClassException | NotSerializableException exception) {
                ConsoleWriter.printError("An error occurred while sending data to the server!");
                ConsoleWriter.printError(exception);
                ConsoleWriter.printError(serverReader);
            } catch (ClassNotFoundException exception) {
                ConsoleWriter.printError("An error occurred while reading the received data!");
            } catch (IOException exception) {
                ConsoleWriter.printError("The connection to the server is broken!");
                try {
                    reconnectionAttempts++;
                    connectToServer();
                } catch (ConnectionErrorException | NotInDeclaredLimitsException reconnectionException) {
                    if (requestToServer.getCommandName().equals("exit"))
                        ConsoleWriter.printLn("The command will not be registered on the server.");
                    else ConsoleWriter.printLn("Try to repeat the command later.");
                }
            }
        } while (!requestToServer.getCommandName().equals("exit"));
        return false;
    }
}
