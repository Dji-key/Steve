package com.epam.javacore2019.state;

public class StateExecuting implements AppState {
    @Override
    public void execute(String commandName, Context context) {
        System.out.println("I'm busy");
        synchronized (context) {
            context.notify();
        }
    }
}
