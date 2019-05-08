package com.epam.javacore2019.steveserver.state;

public interface AppState {
    void execute(String commandName, Context context);
}
