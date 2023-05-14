package org.example.commands;
/**
 * Intereface for all commands.
 */
public interface ICommand {
    String getDescription();
    String getName();
    String getUsage();
    boolean execute(String stringArgument, Object objectArgument);
}
