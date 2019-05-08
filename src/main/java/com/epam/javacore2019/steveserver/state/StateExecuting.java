package com.epam.javacore2019.steveserver.state;

public class StateExecuting implements AppState {
    @Override
    public void execute(String commandName, Context context) {
        System.out.println("I'm busy");//Must be changed
    }
}
