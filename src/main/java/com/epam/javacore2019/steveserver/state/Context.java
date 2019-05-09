package com.epam.javacore2019.steveserver.state;

import com.sun.net.httpserver.HttpExchange;

public enum  Context {
    INSTANCE;

     private AppState state;

    {
        state = new StateIdle();
    }

    void setState(AppState state) {
        this.state = state;
    }

    public void execute(String commandRequest, HttpExchange httpExchange) {
        state.execute(commandRequest, httpExchange, this);
    }
}
