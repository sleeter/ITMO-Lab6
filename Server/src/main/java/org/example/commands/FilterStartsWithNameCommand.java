package org.example.commands;

import org.example.data.Organization;
import org.example.exceptions.CollectionIsEmptyException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utilities.CollectionManager;
import org.example.utilities.ResponseOutputer;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;

public class FilterStartsWithNameCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public FilterStartsWithNameCommand(CollectionManager collectionManager) {
        super("filter_starts_with_name", "name", "display elements whose name field value starts with the given substring");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            LinkedHashSet<Organization> organizations = collectionManager.getCollection();
            for (Organization organization : organizations) {
                if (organization.getName().regionMatches(0, stringArgument, 0, stringArgument.length()))
                    ResponseOutputer.appendLn(organization.toString());
            }
            return true;
        } catch (WrongAmountOfElementsException e) {
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (CollectionIsEmptyException e) {
            ResponseOutputer.appendError("Collection is empty!");
        }
        return false;
    }
}
