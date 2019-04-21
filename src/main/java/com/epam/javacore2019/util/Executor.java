package com.epam.javacore2019.util;

import com.epam.javacore2019.state.Context;

public class Executor implements Runnable {

    private String commandRequest;
    private Context context;

    Executor(String input, Context context) {
        this.commandRequest = input;
        this.context = context;
    }

    @Override
    public void run() {
        context.execute(commandRequest);
    }
}
