package com.epam.javacore2019.command;

/**
 * Implementation of pattern "Command"
 */
public abstract class ACommand {

    private String name;
    String description;

    ACommand(String name) {
        this.name = name;
    }

    public void execute() {
        System.out.println("Command " + name + " not implemented");
    }
}
