package com.epam.javacore2019.steveserver.handler;

import com.epam.javacore2019.steveserver.command.CommandRegister;
import com.epam.javacore2019.steveserver.command.Trigger;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class HandlerGetTriggers implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        JSONObject jsonObject = new JSONObject();
        try
        {
            JSONArray jsonArray = new JSONArray();
            for (Trigger trigger : CommandRegister.INSTANCE.getTriggers())
            {
                JSONObject triggerJSON = new JSONObject();
                triggerJSON.put("key", trigger.getKey());
                triggerJSON.put("words", trigger.getWords());
                triggerJSON.put("params", trigger.getParams());
                triggerJSON.put("strictParams", trigger.getStrictParams());
                jsonArray.put(triggerJSON);
            }
            jsonObject.put("triggers", jsonArray);
        } catch (JSONException jse) {
            System.out.println("Something went wrong");
        }

        httpExchange.sendResponseHeaders(200, 0);
        httpExchange.getResponseHeaders().set("Context-Type", "application/json");
        httpExchange.getResponseBody().write(jsonObject.toString().getBytes());
        httpExchange.close();
    }
}
