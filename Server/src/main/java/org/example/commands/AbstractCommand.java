package org.example.commands;


/**
 * The AbstractCommand class is an abstract class that implements the ICommand interface.
 * It has three fields: name, usage and description.
 * It has three methods: getName, getUsage and getDescription.
 * It has one constructor: AbstractCommand(String name, String usage, String description).
 * It has one toString method: toString().
 * It has one hashCode method: hashCode().
 * It has one equals method: equals(Object obj).
 */

public abstract class AbstractCommand implements ICommand {
    private final String name;
    private final String usage;
    private final String description;

    public AbstractCommand(String name, String usage, String description) {
        this.name = name;
        this.usage = usage;
        this.description = description;
    }

    /**
     * @return Name of the command.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return Usage of the command.
     */
    @Override
    public String getUsage() {
        return usage;
    }

    /**
     * @return Description of the command.
     */
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + " " + usage + " (" + description + ")";
    }

    @Override
    public int hashCode() {
        return name.hashCode() + usage.hashCode() + description.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AbstractCommand other = (AbstractCommand) obj;
        return name.equals(other.getName()) && usage.equals(other.getUsage()) &&
                description.equals(other.getDescription());
    }
}
