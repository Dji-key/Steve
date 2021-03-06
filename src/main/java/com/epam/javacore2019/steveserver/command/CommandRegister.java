package com.epam.javacore2019.steveserver.command;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Storage of all existing commands {@link ACommand} and their keys for invocation.
 */
public enum  CommandRegister {
    INSTANCE;
    public final Map<String, ACommand> REGISTER = new HashMap<String, ACommand>(){
        {
            CommandAbout about = new CommandAbout();
            put(about.getTrigger().getKey(), about);

            CommandTime time = new CommandTime();
            put(time.getTrigger().getKey(), time);

            CommandWeather weather = new CommandWeather();
            put(weather.getTrigger().getKey(), weather);

            CommandNews news = new CommandNews();
            put(news.getTrigger().getKey(), news);
        }
    };

    public List<Trigger> getTriggers() {
        List<Trigger> result = new ArrayList<>();
        for (ACommand command : REGISTER.values()) {
            result.add(command.getTrigger());
        }
        return result;
    }
    /**
     * invokes overridden method of {@link ACommand} if any, otherwise prints "No such command"
     * @param commandName get it from {@link com.epam.javacore2019.Application}
     */
    public void execute(String commandName, String params, HttpExchange httpExchange) {
        if (commandName == null) {
            try {
                httpExchange.sendResponseHeaders(501, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        if (REGISTER.containsKey(commandName)) {

            REGISTER.get(commandName).execute(params, httpExchange);

        } else {
            try {
                httpExchange.sendResponseHeaders(501, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
