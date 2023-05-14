package org.example.commands;

import org.example.data.Organization;
import org.example.exceptions.CollectionIsEmptyException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.interactions.OrganizationRaw;
import org.example.utilities.CollectionManager;
import org.example.utilities.ResponseOutputer;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public class RemoveLowerCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    public RemoveLowerCommand(CollectionManager collectionManager) {
        super("remove_lower", "{element}", "remove from the collection all elements lower than the given one");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (!stringArgument.isEmpty() || objectArgument == null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            OrganizationRaw organizationRaw = (OrganizationRaw) objectArgument;
            Organization organizationToCompare = new Organization(
                    collectionManager.generateNewIdForOrganization(),
                    organizationRaw.getName(),
                    organizationRaw.getCoordinates(),
                    LocalDate.now(),
                    organizationRaw.getAnnualTurnover(),
                    organizationRaw.getName() + "_Unique",
                    organizationRaw.getEmployeesCount(),
                    organizationRaw.getType(),
                    organizationRaw.getPostalAddress()
            );
            LinkedHashSet<Organization> organizations = collectionManager.getCollection();
            organizations.removeIf(organization -> organizationToCompare.compareTo(organization) >= 0);
            return true;
        } catch (WrongAmountOfElementsException e) {
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (CollectionIsEmptyException e) {
            ResponseOutputer.appendError("Collection is empty!");
        }
        return false;
    }
}
