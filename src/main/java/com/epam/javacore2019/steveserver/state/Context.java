package com.epam.javacore2019.steveserver.state;

public enum  Context {
    INSTANCE;

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
