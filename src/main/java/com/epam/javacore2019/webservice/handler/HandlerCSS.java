package com.epam.javacore2019.webservice.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HandlerCSS implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String path = "webclient/static" + httpExchange.getRequestURI().getPath();

        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder builder = new StringBuilder();
        while (reader.ready()) {
            builder.append(reader.readLine());
        }
        httpExchange.sendResponseHeaders(200, 0);
        httpExchange.getResponseHeaders().set("Content-Type", "text/css");
        httpExchange.getResponseBody().write(builder.toString().getBytes());
        httpExchange.close();
    }
}
