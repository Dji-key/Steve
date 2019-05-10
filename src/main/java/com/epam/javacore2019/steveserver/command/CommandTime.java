package com.epam.javacore2019.steveserver.command;

import com.sun.net.httpserver.HttpExchange;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Shows current time in form of 24 hour or AM/PM
 */
public class CommandTime extends ACommand {

    private final Trigger TRIGGER = new Trigger("time", null, null);

    CommandTime() {
        description = "Shows current time";
    }

    @Override
    public Trigger getTrigger() {
        return TRIGGER;
    }

    @Override
    public void execute(String param, HttpExchange httpExchange) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormatAmPm = new SimpleDateFormat("h:mm:ss a");
        Calendar calendar = Calendar.getInstance();
        System.out.println(dateFormat.format(calendar.getTime()));
        System.out.println(dateFormatAmPm.format(calendar.getTime()));
    }
}
