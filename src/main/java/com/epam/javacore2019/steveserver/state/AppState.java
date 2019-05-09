package com.epam.javacore2019.steveserver.state;

import com.sun.net.httpserver.HttpExchange;

public interface AppState {
    void execute(String commandRequest, HttpExchange httpExchange, Context context);
}
