package com.epam.javacore2019.steveserver.command;

import com.epam.javacore2019.steveclient.util.Trigger;

/**
 * Implementation of pattern "Command"
 */
public abstract class ACommand {

    String description;

    public abstract Trigger getTrigger();

    public void execute(String param) {
        System.out.println("Command " + getClass() + " not implemented");
    }
}
