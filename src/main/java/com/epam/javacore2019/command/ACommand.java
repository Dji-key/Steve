package com.epam.javacore2019.command;

/**
 * Implementation of pattern "Command"
 */
public abstract class ACommand {

    String description;

    public void execute(String... params) {
        System.out.println("Command " + getClass() + " not implemented");
    }
}
