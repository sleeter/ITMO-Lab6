package org.example.commands;

import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utilities.CollectionManager;
import org.example.utilities.ResponseOutputer;

import java.time.LocalDate;

/**
 * Prints out the information about the collection
 */
public class InfoCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info","", "print information about the collection to the standard output stream (type, initialization date, number of elements, etc.)");
        this.collectionManager = collectionManager;
    }

    /**
     * Prints out the information about the collection
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try{
            if (!stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            LocalDate lastInitDate = collectionManager.getLastInitDate();
            String lastInitDateString = (lastInitDate == null) ? "initialization has not yet occurred in this session" :
                    lastInitDate.toString() + " " + lastInitDate.toString();

            LocalDate creationDate = collectionManager.getCreationDate();
            String  creationDateString = creationDate.toString();

            LocalDate lastSaveDate = collectionManager.getLastSaveDate();
            String lastSaveDateString = (lastSaveDate == null) ? "there has not been a save in this session yet" :
                    lastSaveDate.toString() + " " + lastSaveDate.toString();

            ResponseOutputer.appendLn("Information about the collection:");
            ResponseOutputer.appendLn(" Type: " + collectionManager.collectionType());
            ResponseOutputer.appendLn(" Number of elements: " + collectionManager.collectionSize());
            ResponseOutputer.appendLn(" Creation time: " + creationDateString);
            ResponseOutputer.appendLn(" Last save time: " + lastSaveDateString);
            ResponseOutputer.appendLn(" Last init time: " + lastInitDateString);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        }
        return false;
    }
}