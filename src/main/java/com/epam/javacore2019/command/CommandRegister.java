package com.epam.javacore2019.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Storage of all existing commands {@link ACommand}
 */
public class CommandRegister {
    private final Map<String, ACommand> REGISTER = new HashMap<String, ACommand>(){
        {
            put("about", new CommandAbout("about"));
            put("weather", new CommandWeather("weather"));
        }
    };

    /**
     * invokes overridden method of {@link ACommand} if any, otherwise prints "No such command"
     * @param commandName get it from {@link com.epam.javacore2019.Application}
     */
    public void execute(String commandName) {
        if (REGISTER.containsKey(commandName)) {
            REGISTER.get(commandName).execute();
        } else {
            System.out.println("No such command");
        }
    }
}
