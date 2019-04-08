package com.epam.javacore2019.command;

import com.epam.javacore2019.util.Trigger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Implementation of {@link ACommand}
 */
public class CommandAbout extends ACommand {

    public static final Trigger trigger = new Trigger("about", "father", "author");
    public static final String[] params = null;

    CommandAbout() {
        description = "Information about program";
    }

    /**
     * Loads information from properties and prints out
     */
    @Override
    public void execute(String... params) {

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
