package com.epam.javacore2019.steveserver.command;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    public void execute(String params, HttpExchange httpExchange) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

//        JSONObject outputJSONObject = new JSONObject();
        String time = "Current time: " + dateFormat.format(calendar.getTime());
//        try {
//            outputJSONObject.put("text", time);
//            httpExchange.getResponseBody().write(outputJSONObject.toString().getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            httpExchange.getResponseHeaders().set("Content", "text");
            httpExchange.sendResponseHeaders(200, 0);
            httpExchange.getResponseBody().write(time.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
