package com.epam.javacore2019.steveserver.state;

import com.epam.javacore2019.steveserver.command.CommandRegister;
import com.epam.javacore2019.steveclient.util.Analyzer;

public class StateIdle implements AppState {

    @Override
    public void execute(String commandRequest, Context context) {
        context.setState(new StateExecuting());

        //Executing will be here

        context.setState(this);
    }
}
