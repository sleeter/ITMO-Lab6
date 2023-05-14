package org.example.interactions;
import java.io.Serializable;

public class RequestTemplate implements Serializable {
    private final String commandName;
    private final String commandStringArgument;
    private final Serializable commandObjectArgument;

    public RequestTemplate(String commandName, String commandStringArgument, Serializable commandObjectArgument) {
        this.commandName = commandName;
        this.commandStringArgument = commandStringArgument;
        this.commandObjectArgument = commandObjectArgument;
    }

    public RequestTemplate(String commandName, String commandStringArgument) {
        this(commandName, commandStringArgument, null);
    }

    public RequestTemplate() {
        this("","");
    }
    /**
     * @return Command name.
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * @return Command string argument.
     */
    public String getCommandStringArgument() {
        return commandStringArgument;
    }

    /**
     * @return Command object argument.
     */
    public Object getCommandObjectArgument() {
        return commandObjectArgument;
    }

    /**
     * @return Is this request empty.
     */
    public boolean isEmpty() {
        return commandName.isEmpty() && commandStringArgument.isEmpty() && commandObjectArgument == null;
    }

    @Override
    public String toString() {
        return "Request[" + commandName + ", " + commandStringArgument + ", " + commandObjectArgument + "]";
    }
}
