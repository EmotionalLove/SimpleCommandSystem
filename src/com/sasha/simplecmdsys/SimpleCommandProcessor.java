package com.sasha.simplecmdsys;

import com.sasha.simplecmdsys.exception.InvalidInputException;
import com.sasha.simplecmdsys.exception.SimpleCommandException;

import java.util.ArrayList;

public class SimpleCommandProcessor {

    private String commandPrefix;
    private ArrayList<SimpleCommand> commandRegistry;

    public SimpleCommandProcessor(String commandPrefix) {
        this.commandPrefix = commandPrefix;
        this.commandRegistry = new ArrayList<>();
    }

    public String getCommandPrefix() {
        return commandPrefix;
    }

    public void register(SimpleCommand command) {
        this.commandRegistry.add(command);
    }

    public void deregister(SimpleCommand command) {
        for (int i = 0; i < this.commandRegistry.size(); i++) {
            if (this.commandRegistry.get(i).getCommandName().equals(command.getCommandName())) {
                this.commandRegistry.remove(i);
                break;
            }
        }
    }

    public ArrayList<SimpleCommand> getCommandRegistry() {
        return commandRegistry;
    }

    /**
     * Before feeding input into this method make sure it begins with the command prefix
     */
    public void processCommand(String input) {
        if (!input.startsWith(commandPrefix)) throw new InvalidInputException("The input doesn't begin with the command prefix '" + commandPrefix + "'");
        commandRegistry.forEach(command -> {
            if (input.split(" ")[0].equalsIgnoreCase(this.commandPrefix + command.getCommandName())) {
                command.setArguments(input, this);
                try {
                    command.onCommand();
                }catch(Exception ee) {
                    throw new SimpleCommandException("A severe error occurred whilst executing \"" + commandPrefix + command.getCommandName() + "\"");
                }
            }
        });
    }
    /**
     * Returns the command's syntax as provided by the @SimpleCommandInfo annotation
     */
    public String[] getSyntax(Class<?> clazz){
        SimpleCommandInfo d = clazz.getAnnotation(SimpleCommandInfo.class);
        if (d == null){
            return new String[]{"No syntax info provided..."};
        }
        return d.syntax();
    }

    /**
     * Returns the description of the command provided by the @SimpleCommandInfo annotation
     */
    public String getDescription(Class<?> clazz){
        SimpleCommandInfo d = clazz.getAnnotation(SimpleCommandInfo.class);
        if (d == null){
            return "No description provided...";
        }
        return d.description();
    }
}
