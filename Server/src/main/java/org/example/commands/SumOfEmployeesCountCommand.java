package org.example.commands;

import org.example.data.Organization;
import org.example.exceptions.CollectionIsEmptyException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utilities.CollectionManager;
import org.example.utilities.ResponseOutputer;

import java.util.LinkedHashSet;

public class SumOfEmployeesCountCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public SumOfEmployeesCountCommand(CollectionManager collectionManager) {
        super("sum_of_employees_count", "", "display the count of employees for all elements of the collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (!stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            LinkedHashSet<Organization> organizations = collectionManager.getCollection();
            Integer sumOfEmployeesCount = 0;
            for(Organization organization : organizations){
                sumOfEmployeesCount += organization.getEmployeesCount();
            }
            ResponseOutputer.appendLn(sumOfEmployeesCount);
            return true;
        } catch (WrongAmountOfElementsException e) {
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (CollectionIsEmptyException e) {
            ResponseOutputer.appendError("Collection is empty!");
        }
        return false;
    }
}
