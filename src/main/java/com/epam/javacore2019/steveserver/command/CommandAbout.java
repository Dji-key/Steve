package com.epam.javacore2019.steveserver.command;

import com.epam.javacore2019.steveclient.util.Trigger;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Implementation of {@link ACommand}
 */
public class CommandAbout extends ACommand {

    private final Trigger TRIGGER = new Trigger("about", null, "father", "author");

    CommandAbout() {
        description = "Information about program";
    }

    @Override
    public Trigger getTrigger() {
        return TRIGGER;
    }
    /**
     * Loads information from properties and prints out
     */
    @Override
    public void execute(String param, HttpExchange httpExchange) {

        Properties properties = new Properties();
        String fileName = "application.properties";

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)){

            properties.load(input);
            System.out.println("Version: " + properties.getProperty("version") + "\nAuthor: " + properties.getProperty("author"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
