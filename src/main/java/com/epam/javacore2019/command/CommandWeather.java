package com.epam.javacore2019.command;

import com.epam.javacore2019.util.Trigger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Properties;

public class CommandWeather extends ACommand {

    public static final Trigger trigger = new Trigger("weather", "temp", "temperature");
    public static final String[] params = new String[] {"yesterday", "today", "tomorrow"};

    CommandWeather() {
        description = "Show temp of some day and my opinion";
    }

    @Override
    public void execute(String... params) {

        Properties properties = new Properties();
        String fileName = "application.properties";

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)){

            properties.load(input);
            //----------------------------------------
            long time = new Date().getTime()/1000; //ADD DATE HERE <<<-----------------------------------
            //-----------------------------------------
            String url = String.format(properties.getProperty("weatherSPb"), time);

            JSONObject json = readJsonFromUrl(url);
            System.out.println(json.getJSONObject("currently").get("temperature"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject readJsonFromUrl(String url) throws IOException, JSONException {

        try (InputStream input = new URL(url).openStream()) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
            String jsonText = readAll(reader);

            return new JSONObject(jsonText);

        }
    }

    private String readAll(BufferedReader reader) throws IOException {

        StringBuilder builder = new StringBuilder();

        while (reader.ready()) {
            builder.append(reader.readLine());
        }

        return builder.toString();
    }
}
