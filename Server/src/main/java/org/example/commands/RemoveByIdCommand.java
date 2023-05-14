package org.example.commands;

import org.example.exceptions.CollectionIsEmptyException;
import org.example.exceptions.MustBeNotEmptyException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utilities.CollectionManager;
import org.example.utilities.ResponseOutputer;

/**
 * Remove an organization from the collection
 */
public class RemoveByIdCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "<id>", "remove element from collection by its id");
        this.collectionManager = collectionManager;
    }

    /**
     * Remove an organization from the collection
     * @return the response of right execution
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try{
            if (stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            int id = Integer.parseInt(stringArgument);
            if(collectionManager.getById(id) == null) throw new MustBeNotEmptyException();
            collectionManager.removeByIdFromCollection(id);
            ResponseOutputer.appendLn("Organization was removed successfully");
            return true;

        } catch (WrongAmountOfElementsException e){
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (NumberFormatException e) {
            ResponseOutputer.appendError("The id have to be an Integer value");
        } catch (MustBeNotEmptyException e) {
            ResponseOutputer.appendError("There is no organization with this id");
        } catch (CollectionIsEmptyException e) {
            ResponseOutputer.appendError("Collection is empty!");
        }
        return false;
    }
}
