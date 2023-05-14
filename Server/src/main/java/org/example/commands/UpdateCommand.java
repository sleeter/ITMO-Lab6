package org.example.commands;

import org.example.data.*;
import org.example.exceptions.*;
import org.example.interactions.OrganizationRaw;
import org.example.utilities.*;

import java.time.LocalDate;

/**
 * The class is used to update the value of the collection element whose id is equal to the given one
 */
public class UpdateCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        super("update" ,"<id> {element}", "update the value of the collection element whose id is equal to the given one");
        this.collectionManager = collectionManager;
    }

    /**
     * The function takes an argument, which is the id of the organization that will be updated.
     * If the argument is empty, the function throws an exception.
     * If the argument is not empty, the function checks if the organization with the given id exists.
     * If it doesn't exist, the function throws an exception.
     * If the organization exists, the function asks the user to enter the new values for the
     * organization.
     * The function then replaces the old organization with the new one.
     * The function returns true if the organization was updated successfully
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try{
            if (stringArgument.isEmpty() || objectArgument == null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            int id = Integer.parseInt(stringArgument);
            if (id <= 0) throw new NumberFormatException();
            Organization oldOrganization = collectionManager.getById(id);
            if (oldOrganization == null) throw new OrganizationNotFoundException();

            OrganizationRaw organizationRaw = (OrganizationRaw) objectArgument;

            String name = organizationRaw.getName() == null ? oldOrganization.getName() : organizationRaw.getName();
            String fullname = name + "_Unique";
            Coordinates coordinates = (organizationRaw.getCoordinates() == null || organizationRaw.getCoordinates().getY() == null) ? oldOrganization.getCoordinates() : organizationRaw.getCoordinates();
            LocalDate creationDate = oldOrganization.getCreationDate();
            float annualTurnover = organizationRaw.getAnnualTurnover() <= 0 ? oldOrganization.getAnnualTurnover() : organizationRaw.getAnnualTurnover();
            int employeesCount = organizationRaw.getEmployeesCount() <= 0 ? oldOrganization.getEmployeesCount() : organizationRaw.getEmployeesCount();
            OrganizationType organizationType = organizationRaw.getType() == null ? oldOrganization.getType() : organizationRaw.getType();
            Address address = (organizationRaw.getPostalAddress() != null) ? oldOrganization.getPostalAddress() : organizationRaw.getPostalAddress();

            collectionManager.removeFromCollection(oldOrganization);
            collectionManager.addToCollection(new Organization(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    annualTurnover,
                    fullname,
                    employeesCount,
                    organizationType,
                    address
            ));
            ResponseOutputer.appendLn("Organization was updated successfully");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.appendError("Collection is empty!");
        } catch (NumberFormatException exception) {
            ResponseOutputer.appendError("ID have to be a positive value!");
        } catch (OrganizationNotFoundException exception) {
            ResponseOutputer.appendError("There is no organization with this ID in the collection!");
        } catch (ClassCastException exception) {
            ResponseOutputer.appendError("The object passed by the client is incorrect!");
        }
        return false;
    }
}
