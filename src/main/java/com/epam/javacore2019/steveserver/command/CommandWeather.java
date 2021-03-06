package com.epam.javacore2019.steveserver.command;

import com.epam.javacore2019.steveserver.util.Utils;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

public class CommandWeather extends ACommand {

    private final Trigger TRIGGER = new Trigger("weather", new String[]{"yesterday", "today", "tomorrow"}, null, "temp", "temperature");

    CommandWeather() {
        description = "Show temp of some day and my opinion";
    }

    @Override
    public Trigger getTrigger() {
        return TRIGGER;
    }

    @Override
    public void execute(String params, HttpExchange httpExchange) {

        Properties properties = new Properties();
        String fileName = "application.properties";

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            properties.load(input);

            long time = new Date().getTime() / 1000;
            int secIn24Hours = 24 * 60 * 60;

            if (params != null) {
                switch (params) {
                    case "yesterday":
                        time -= secIn24Hours;
                        break;
                    case "tomorrow":
                        time += secIn24Hours;
                        break;
                    case "today":
                    default:
                        break;
                }
            }

            String url = properties.getProperty("weatherSPbUrl").replace("{{param}}", String.valueOf(time));
            JSONObject jsonObject = Utils.readJsonFromUrl(url);

            double temperature = jsonObject.getJSONObject("currently").getDouble("temperature");
            StringBuilder opinion = new StringBuilder("Temperature is ").append(temperature).append(" degrees");

            if (temperature < -30) {
                opinion.append(" and I'll die if I go out");
            } else if (temperature >= -30 && temperature < -10) {
                opinion.append(" too cold for a walk");
            } else if (temperature >= -10 && temperature <= 0) {
                opinion.append(" nothing special. Water freeze sort of");
            } else if (temperature > 0 && temperature < 10) {
                opinion.append(" and now U may go out and don't die for sure");
            } else if (temperature >= 10 && temperature <= 25) {
                opinion.append(" it's looking good outside. U will be able to go for a walk if U don't have a computer.. or the internet.. or both.. So bad, bro I feel sorry for you");
            } else if (temperature > 25) {
                opinion.append(" and U should stay at home if U don't want melting");
            }

//            JSONObject outputJSONObject = new  JSONObject();
//            outputJSONObject.put("text", opinion);
//            httpExchange.getResponseBody().write(outputJSONObject.toString().getBytes());

            httpExchange.getResponseHeaders().set("Content", "text");
            httpExchange.sendResponseHeaders(200, 0);
            httpExchange.getResponseBody().write(opinion.toString().getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
