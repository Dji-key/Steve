package com.epam.javacore2019.state;

public class Context {
     private AppState state;

    {
        state = new StateIdle();
    }

    void setState(AppState state) {
        this.state = state;
    }

    public void execute(String commandRequest) {
        state.execute(commandRequest, this);
    }
}
