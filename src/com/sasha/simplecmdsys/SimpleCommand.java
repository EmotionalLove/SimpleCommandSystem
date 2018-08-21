package com.sasha.simplecmdsys;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SimpleCommand {
    private String commandName;
    private String[] commandArgs;

    public SimpleCommand(String commandName){
        this.commandName = commandName;
    }

    /* package-private */ void setArguments(String theMessage, SimpleCommandProcessor commandProcessor){
        if (!theMessage.contains(" ")){
            this.commandArgs = null;
            return;
        }
        String updatedMessage = theMessage.replace(commandProcessor.getCommandPrefix() + this.commandName + " ", ""); // i dont want the actual '-command' to be in the array
        List<String> list = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(updatedMessage);
        while (m.find()) {
            list.add(m.group(1).replace("\"", ""));
        }
        this.commandArgs = list.toArray(new String[0]);
    }

    /**
     * Retrieve a command's arguments as an array.
     * Arguments surrounded by quotation marks are treated as one argument.
     */
    public String[] getArguments() {
        return commandArgs;
    }

    public String getCommandName() {
        return commandName;
    }

    public abstract void onCommand();
}
