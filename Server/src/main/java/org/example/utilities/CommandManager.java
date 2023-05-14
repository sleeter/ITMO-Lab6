package org.example.utilities;

import org.example.commands.ICommand;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager {
    private final ArrayList<ICommand> commands;
    private final ICommand addCommand;
    private final ICommand addIfMaxCommand;
    private final ICommand clearCommand;
    private final ICommand executeScriptCommand;
    private final ICommand exitCommand;
    private final ICommand filterStartsWithNameCommand;
    private final ICommand helpCommand;
    private final ICommand infoCommand;
    private final ICommand printFieldAscendingPostalAddressCommand;
    private final ICommand removeByIdCommand;
    private final ICommand removeGreaterCommand;
    private final ICommand removeLowerCommand;
    private final ICommand saveCommand;
    private final ICommand showCommand;
    private final ICommand sumOfEmployeesCountCommand;
    private final ICommand updateCommand;
    private final ICommand serverExitCommand;
    public CommandManager(ICommand addCommand, ICommand addIfMaxCommand, ICommand clearCommand, ICommand executeScriptCommand, ICommand exitCommand, ICommand filterStartsWithNameCommand, ICommand helpCommand, ICommand infoCommand, ICommand printFieldAscendingPostalAddressCommand, ICommand removeByIdCommand, ICommand removeGreaterCommand, ICommand removeLowerCommand, ICommand saveCommand, ICommand showCommand, ICommand sumOfEmployeesCountCommand, ICommand updateCommand, ICommand serverExitCommand){
        this.addCommand = addCommand;
        this.addIfMaxCommand = addIfMaxCommand;
        this.clearCommand = clearCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.exitCommand = exitCommand;
        this.filterStartsWithNameCommand = filterStartsWithNameCommand;
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.printFieldAscendingPostalAddressCommand = printFieldAscendingPostalAddressCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.removeLowerCommand = removeLowerCommand;
        this.saveCommand = saveCommand;
        this.showCommand = showCommand;
        this.sumOfEmployeesCountCommand = sumOfEmployeesCountCommand;
        this.updateCommand = updateCommand;
        this.serverExitCommand = serverExitCommand;

        commands = new ArrayList<>(Arrays.asList(addCommand, addIfMaxCommand, clearCommand, executeScriptCommand, exitCommand,
                filterStartsWithNameCommand, helpCommand, infoCommand, printFieldAscendingPostalAddressCommand,
                removeByIdCommand, removeGreaterCommand, removeLowerCommand, saveCommand, showCommand,
                sumOfEmployeesCountCommand, updateCommand, serverExitCommand));
    }

    /**
     * If the command is not found, print a message to the user
     *
     * @param command The command that was not found.
     * @return Nothing.
     */
    public boolean noSuchCommand(String command) {
        ConsoleWriter.printLn("Command '" + command + "' was not found. Try to write 'help' for more info.");
        return false;
    }
    /**
     * Prints a list of all commands and their descriptions
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean help(String stringArgument, Object objectArgument) {
        if (helpCommand.execute(stringArgument, objectArgument)) {
            for (ICommand command : commands) {
                ResponseOutputer.appendTable(command.getName() + " " + command.getUsage(), command.getDescription());
            }
            return true;
        } else return false;
    }
    /**
     * @return List of manager's commands.
     */
    public ArrayList<ICommand> getCommands() {
        return commands;
    }
    /**
     * Executes needed command.
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean info(String stringArgument, Object objectArgument) {
        return infoCommand.execute(stringArgument, objectArgument);
    }
    /**
     * Executes needed command.
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean show(String stringArgument, Object objectArgument) {
        return showCommand.execute(stringArgument, objectArgument);
    }
    /**
     * Executes needed command.
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean add(String stringArgument, Object objectArgument) {
        return addCommand.execute(stringArgument, objectArgument);
    }
    /**
     * Executes needed command.
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean update(String stringArgument, Object objectArgument) {
        return updateCommand.execute(stringArgument, objectArgument);
    }
    /**
     * Executes needed command.
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean removeById(String stringArgument, Object objectArgument) {
        return removeByIdCommand.execute(stringArgument, objectArgument);
    }
    /**
     * Executes needed command.
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean clear(String stringArgument, Object objectArgument) {
        return clearCommand.execute(stringArgument, objectArgument);
    }
    /**
     * Executes needed command.
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean save(String stringArgument, Object objectArgument) {
        return saveCommand.execute(stringArgument, objectArgument);
    }
    /**
     * Executes needed command.
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean executeScript(String stringArgument, Object objectArgument) {
        return executeScriptCommand.execute(stringArgument, objectArgument);
    }
    /**
     * Executes needed command.
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean exit(String stringArgument, Object objectArgument) {
        save(stringArgument, objectArgument);
        return exitCommand.execute(stringArgument, objectArgument);
    }
    /**
     * Executes needed command.
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean addIfMax(String stringArgument, Object objectArgument) {
        return addIfMaxCommand.execute(stringArgument, objectArgument);
    }
    /**
     * Executes needed command.
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean removeGreater(String stringArgument, Object objectArgument) {
        return removeGreaterCommand.execute(stringArgument, objectArgument);
    }
    /**
     * Executes needed command.
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean removeLower(String stringArgument, Object objectArgument) {
        return removeLowerCommand.execute(stringArgument, objectArgument);
    }
    /**
     * Executes needed command.
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean sumOfEmployeesCount(String stringArgument, Object objectArgument) {
        return sumOfEmployeesCountCommand.execute(stringArgument, objectArgument);
    }
    /**
     * Executes needed command.
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean filterStartsWithName(String stringArgument, Object objectArgument) {
        return filterStartsWithNameCommand.execute(stringArgument, objectArgument);
    }
    /**
     * Executes needed command.
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean printFieldAscendingPostalAddress(String stringArgument, Object objectArgument) {
        return printFieldAscendingPostalAddressCommand.execute(stringArgument, objectArgument);
    }
    /**
     * Executes needed command.
     *
     * @param stringArgument Its string argument.
     * @param objectArgument Its object argument.
     * @return Command exit status.
     */
    public boolean serverExit(String stringArgument, Object objectArgument) {
        save(stringArgument, objectArgument);
        return serverExitCommand.execute(stringArgument, objectArgument);
    }
    /**
     * This function is used to print out the string representation of the command manager
     *
     * @return The string "CommandManager (helper class for working with commands)"
     */
    @Override
    public String toString() {
        return "CommandManager (helper class for working with commands)";
    }
}
