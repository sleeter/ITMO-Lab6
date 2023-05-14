package org.example.commands;

import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utilities.ResponseOutputer;

/**
 * The `HelpCommand` class is a command that displays the help of the available commands
 */
public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
        super("help","", "display help on available commands");
    }

    /**
     * Prints the usage of the command
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (!stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        }
        return true;
    }
}
