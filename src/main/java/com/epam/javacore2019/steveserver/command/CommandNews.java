package com.epam.javacore2019.steveserver.command;

import com.epam.javacore2019.steveserver.util.Utils;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CommandNews extends ACommand{

    private final Trigger TRIGGER = new Trigger("news", null, new String[] {"-t"}, "new", "tidings");

    CommandNews() {
        description = "Shows article for given topic or random.\nParameters: -t (topic) defines topic for article";
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

            String param = params.split(" ")[1];

            String url = properties.getProperty("newsUrl").replace("{{param}}", param);

            JSONObject jsonObject = Utils.readJsonFromUrl(url);
            JSONArray jsonArrayOfArticles = jsonObject.getJSONArray("articles");
            int numberOfArticles = jsonArrayOfArticles.length();
            int articlePointer = ThreadLocalRandom.current().nextInt(numberOfArticles + 1);
            JSONObject jsonArticle = jsonArrayOfArticles.getJSONObject(articlePointer);

            String title = jsonArticle.getString("title") + ".\n";
            String source = "Источник: " + jsonArticle.getJSONObject("source").getString("name") + "\n";
            String urlOfNews = "Ссылка: " + jsonArticle.getString("url");

            JSONObject outputJSONObject = new  JSONObject();
            outputJSONObject.put("text", title + source + urlOfNews);
            httpExchange.getResponseBody().write(outputJSONObject.toString().getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
