package org.example.commands;

import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utilities.CollectionManager;
import org.example.utilities.ResponseOutputer;

/**
 * The `ShowCommand` class is a command that prints all the elements of the collection in string
 * representation
 */
public class ShowCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    public ShowCommand(CollectionManager collectionManager) {
        super("show","", "print to standard output all elements of the collection in string representation");
        this.collectionManager = collectionManager;
    }

    /**
     * Prints all the organizations in the collection
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (!stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            ResponseOutputer.appendLn(collectionManager.showCollection());
            return true;
        } catch (WrongAmountOfElementsException e){
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        }
        return false;
    }
}