package org.example.commands;

import org.example.data.Organization;
import org.example.exceptions.CollectionIsEmptyException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utilities.CollectionManager;
import org.example.utilities.ResponseOutputer;

import java.util.LinkedHashSet;

public class PrintFieldAscendingPostalAddressCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public PrintFieldAscendingPostalAddressCommand(CollectionManager collectionManager) {
        super("print_field_ascending_postal_address", "", "display the values of the postalAddress field of all elements in ascending order");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (!stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            LinkedHashSet<Organization> organizations = collectionManager.getCollection();
            organizations.stream().sorted().map(Organization::getPostalAddress).forEach(ResponseOutputer::appendLn);
            return true;
        } catch (WrongAmountOfElementsException e) {
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (CollectionIsEmptyException e) {
            ResponseOutputer.appendError("Collection is empty!");
        }
        return false;
    }
}
