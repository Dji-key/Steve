package com.epam.javacore2019.steveserver.state;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class StateExecuting implements AppState {
    @Override
    public void execute(String commandRequest, HttpExchange httpExchange, Context context) {
        try {
            httpExchange.sendResponseHeaders(404, 0);
            httpExchange.getResponseHeaders().set("Content-Type", "html/text");
            httpExchange.getResponseBody().write("Server is busy".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        httpExchange.close();
    }
}
