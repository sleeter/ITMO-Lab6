package org.example;

import org.example.commands.*;
import org.example.utilities.*;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static final int PORT = 1402;
    public static final int CONNECTION_TIMEOUT = 60 * 1000;


    public static void main(String[] args) {
        String filename = "LAB5678FILE.csv";
        if(args.length == 1) filename = args[0];
        FileManager fileManager = new FileManager(filename);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        CommandManager commandManager = new CommandManager(
                new AddCommand(collectionManager),
                new AddIfMaxCommand(collectionManager),
                new ClearCommand(collectionManager),
                new ExecuteScriptCommand(),
                new ExitCommand(),
                new FilterStartsWithNameCommand(collectionManager),
                new HelpCommand(),
                new InfoCommand(collectionManager),
                new PrintFieldAscendingPostalAddressCommand(collectionManager),
                new RemoveByIdCommand(collectionManager),
                new RemoveGreaterCommand(collectionManager),
                new RemoveLowerCommand(collectionManager),
                new SaveCommand(collectionManager),
                new ShowCommand(collectionManager),
                new SumOfEmployeesCountCommand(collectionManager),
                new UpdateCommand(collectionManager),
                new ServerExitCommand()
        );
        RequestHandler requestHandler = new RequestHandler(commandManager);
        Server server = new Server(PORT, CONNECTION_TIMEOUT, requestHandler);
        server.run();
    }
}