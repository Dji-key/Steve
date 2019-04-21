package com.epam.javacore2019.command;

import com.epam.javacore2019.util.Trigger;

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
