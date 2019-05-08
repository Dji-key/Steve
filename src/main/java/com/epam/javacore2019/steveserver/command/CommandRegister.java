package com.epam.javacore2019.steveserver.command;

import com.epam.javacore2019.steveclient.util.Trigger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Storage of all existing commands {@link ACommand} and their keys for invocation.
 */
public enum  CommandRegister {
    INSTANCE;
    public final Map<String, ACommand> REGISTER = new HashMap<String, ACommand>(){
        {
            CommandAbout about = new CommandAbout();
            put(about.getTrigger().getKey(), about);

            CommandTime time = new CommandTime();
            put(time.getTrigger().getKey(), time);

            CommandWeather weather = new CommandWeather();
            put(weather.getTrigger().getKey(), weather);
        }
    };

    public List<Trigger> getTriggers() {
        List<Trigger> result = new ArrayList<>();
        for (ACommand command : REGISTER.values()) {
            result.add(command.getTrigger());
        }
        return result;
    }
    /**
     * invokes overridden method of {@link ACommand} if any, otherwise prints "No such command"
     * @param commandName get it from {@link com.epam.javacore2019.Application}
     */
    public void execute(String commandName, String param) {
        if (commandName == null) {
            System.out.println("No such command");
            return;
        }

        if (REGISTER.containsKey(commandName)) {
            REGISTER.get(commandName).execute(param);
        } else {
            System.out.println("No such command");
        }
    }
}