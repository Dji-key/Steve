package com.epam.javacore2019.state;

public interface AppState {
    void execute(String commandName, Context context);
}
