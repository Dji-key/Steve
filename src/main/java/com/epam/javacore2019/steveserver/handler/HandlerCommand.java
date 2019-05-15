package com.epam.javacore2019.steveserver.handler;

import com.epam.javacore2019.steveserver.command.CommandExecutor;
import com.epam.javacore2019.steveserver.util.Utils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HandlerCommand implements HttpHandler {

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

        new CommandExecutor(commandRequest, httpExchange).start();
    }
}
