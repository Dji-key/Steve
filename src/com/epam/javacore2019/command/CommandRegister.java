package com.epam.javacore2019.command;

import java.util.HashMap;
import java.util.Map;

public class CommandRegister {
    private final Map<String, ACommand> REGISTER = new HashMap<String, ACommand>(){
        {
            put("about", new CommandAbout("about"));
        }
    };

    public void execute(String commandName) {
        if (REGISTER.containsKey(commandName)) {
            REGISTER.get(commandName).execute();
        } else {
            System.out.println("No such command");
        }
    }
}
