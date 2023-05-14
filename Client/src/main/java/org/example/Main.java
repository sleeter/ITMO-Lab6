package org.example;

import org.example.exceptions.NotInDeclaredLimitsException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utilities.ConsoleWriter;
import org.example.utilities.UserHandler;

import java.util.Scanner;

public class Main {
    private static final int RECONNECTION_TIMEOUT = 5 * 1000;
    private static final int MAX_RECONNECTION_ATTEMPTS = 5;

    private static String host;
    private static int port;
    private static String script = null;

    /**
     * It takes a String array of two elements,
     * and if the array has exactly two elements, it parses the second element as an integer,
     * and if the integer is in the range [0, 65535], it sets the host and port variables to the
     * corresponding values
     *
     * @param hostAndPortArgs the array of arguments passed to the main method.
     * @return Nothing.
     */
    private static boolean initializeConnectionAddress(String[] hostAndPortArgs) {
        try {
            if (hostAndPortArgs.length != 2 && hostAndPortArgs.length != 3) throw new WrongAmountOfElementsException();
            host = hostAndPortArgs[0];
            port = Integer.parseInt(hostAndPortArgs[1]);
            if(hostAndPortArgs.length == 3) script = hostAndPortArgs[2];
            if (port < 0) throw new NotInDeclaredLimitsException();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            String jarName = new java.io.File(Main.class.getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath())
                    .getName();
            ConsoleWriter.printLn("Usage: 'java -jar " + jarName + " <host> <port>'");
        } catch (NumberFormatException exception) {
            ConsoleWriter.printError("The port must be represented by a number!");
        } catch (NotInDeclaredLimitsException exception) {
            ConsoleWriter.printError("The port cannot be negative!");
        }
        return false;
    }

    public static void main(String[] args) {
        if (!initializeConnectionAddress(args)) return;
        Scanner userScanner = new Scanner(System.in);
        UserHandler userHandler = new UserHandler(userScanner, script);
        Client client = new Client(host, port, RECONNECTION_TIMEOUT, MAX_RECONNECTION_ATTEMPTS, userHandler);
        client.run();
        userScanner.close();
    }
}
