package com.epam.javacore2019.steveserver.state;

import com.epam.javacore2019.steveserver.command.CommandRegister;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class StateIdle implements AppState {

    @Override
    public void execute(String commandRequest, HttpExchange httpExchange, Context context) {
        context.setState(new StateExecuting());

        String[] command = commandRequest.split(" ", 2);

        try {
            httpExchange.sendResponseHeaders(200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String commandName = command[0];
        String commandParams = command.length == 2 ? command[1] : null;

        CommandRegister.INSTANCE.execute(commandName, commandParams, httpExchange);
        httpExchange.close();

        context.setState(this);
    }
}
