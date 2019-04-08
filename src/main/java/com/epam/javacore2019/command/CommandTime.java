package com.epam.javacore2019.command;

import com.epam.javacore2019.util.Trigger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Shows current time in form of 24 hour or AM/PM
 */
public class CommandTime extends ACommand {

    public static final Trigger trigger = new Trigger("time");
    public static final String[] params = null;

    CommandTime() {
        description = "Shows current time";
    }

    @Override
    public void execute(String... params) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormatAmPm = new SimpleDateFormat("h:mm:ss a");
        Calendar calendar = Calendar.getInstance();
        System.out.println(dateFormat.format(calendar.getTime()));
        System.out.println(dateFormatAmPm.format(calendar.getTime()));
    }
}
