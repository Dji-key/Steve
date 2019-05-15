package com.epam.javacore2019.steveserver.command;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * Implementation of {@link ACommand}
 */
public class CommandAbout extends ACommand {

    private final Trigger TRIGGER = new Trigger("about", null, null, "father", "author");

    CommandAbout() {
        description = "Shows information about program";
    }

    @Override
    public Trigger getTrigger() {
        return TRIGGER;
    }
    /**
     * Loads information from properties and prints out
     */
    @Override
    public void execute(String params, HttpExchange httpExchange) {

        Properties properties = new Properties();
        String fileName = "application.properties";

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)){

            properties.load(input);
            String infoString = "Version: " + properties.getProperty("version") + "\nAuthor: " + properties.getProperty("author");

//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("text", infoString);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

//            httpExchange.getResponseHeaders().put("Date", Arrays.asList(new String[] {"text/plain"}));
            httpExchange.getResponseHeaders().set("Content", "text");
            httpExchange.sendResponseHeaders(200, 0);

//            httpExchange.getResponseBody().write(jsonObject.toString().getBytes());
            httpExchange.getResponseBody().write(infoString.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
