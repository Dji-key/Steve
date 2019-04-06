package com.epam.javacore2019.command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommandAbout extends ACommand {

    CommandAbout(String name) {
        super(name);
        description = "Information about program";
    }

    @Override
    public void execute() {

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
