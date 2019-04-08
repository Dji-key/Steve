package com.epam.javacore2019.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Storage of all existing commands {@link ACommand} and their keys for invocation.
 */
public enum  CommandRegister {
    INSTANCE;
    private final Map<String, ACommand> REGISTER = new HashMap<String, ACommand>(){
        {
            put(CommandAbout.trigger.getKey(), new CommandAbout());
            put(CommandTime.trigger.getKey(), new CommandTime());
            put(CommandWeather.trigger.getKey(), new CommandWeather());
        }
    };

    /**
     * invokes overridden method of {@link ACommand} if any, otherwise prints "No such command"
     * @param commandName get it from {@link com.epam.javacore2019.Application}
     */
    public void execute(String commandName, String... params) {
        if (commandName == null) {
            System.out.println("No such command");
            return;
        }

        if (REGISTER.containsKey(commandName)) {
            REGISTER.get(commandName).execute(params);
        } else {
            System.out.println("No such command");
        }
    }
}
