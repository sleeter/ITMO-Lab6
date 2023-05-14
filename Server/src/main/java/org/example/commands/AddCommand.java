package org.example.commands;

import org.example.data.Organization;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.interactions.OrganizationRaw;
import org.example.utilities.CollectionManager;
import org.example.utilities.ResponseOutputer;

import java.time.LocalDate;

/**
 * The class is responsible for adding an organization to the collection
 */
public class AddCommand extends AbstractCommand{

    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        super("add" ,"{element}", "add a new element to the collection");
        this.collectionManager = collectionManager;
    }



    /**
     * The function adds an organization to the collection
     *
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try{
            if (!stringArgument.isEmpty() || objectArgument == null) throw new WrongAmountOfElementsException();
            OrganizationRaw organizationRaw = (OrganizationRaw) objectArgument;
            collectionManager.addToCollection(new Organization(
                    collectionManager.generateNewIdForOrganization(),
                    organizationRaw.getName(),
                    organizationRaw.getCoordinates(),
                    LocalDate.now(),
                    organizationRaw.getAnnualTurnover(),
                    organizationRaw.getName() + "_Unique",
                    organizationRaw.getEmployeesCount(),
                    organizationRaw.getType(),
                    organizationRaw.getPostalAddress()
            ));
            ResponseOutputer.appendLn("Organization was added successfully!");
            return true;
        } catch (WrongAmountOfElementsException e){
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (ClassCastException exception) {
            ResponseOutputer.appendError("The object passed by the client is incorrect!");
        }
        return false;
    }
}
