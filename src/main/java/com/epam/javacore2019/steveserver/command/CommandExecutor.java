package com.epam.javacore2019.steveserver.command;

import com.epam.javacore2019.steveserver.state.Context;
import com.sun.net.httpserver.HttpExchange;

public class CommandExecutor extends Thread {

    private Context context = Context.INSTANCE;

    private  String command;
    private HttpExchange httpExchange;

    public CommandExecutor(String command, HttpExchange httpExchange) {
        this.command = command;
        this.httpExchange = httpExchange;
    }

    @Override
    public void run() {
        context.execute(command, httpExchange);
    }
}
