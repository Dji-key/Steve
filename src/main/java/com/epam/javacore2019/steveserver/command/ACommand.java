package com.epam.javacore2019.steveserver.command;

import com.sun.net.httpserver.HttpExchange;

/**
 * Implementation of pattern "Command"
 */
public abstract class ACommand {

    String description;

    public abstract Trigger getTrigger();

    public void execute(String param, HttpExchange httpExchange) {
        System.out.println("Command " + getClass() + " not implemented");
    }
}
