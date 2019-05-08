package com.epam.javacore2019.steveserver.handler;

import com.epam.javacore2019.steveserver.state.Context;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HandlerCommand implements HttpHandler {

    private Context context = Context.INSTANCE;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String commandRequest;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))) {
            String input;
            StringBuilder builder = new StringBuilder();
            while ((input = reader.readLine()) != null) {
                builder.append(input);
            }
            commandRequest = builder.toString();
        }
        httpExchange.sendResponseHeaders(200, 0);
        httpExchange.close();

        context.execute(commandRequest);
    }
}
