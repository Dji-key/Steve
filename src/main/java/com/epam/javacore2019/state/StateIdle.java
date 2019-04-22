package com.epam.javacore2019.state;

import com.epam.javacore2019.command.CommandRegister;
import com.epam.javacore2019.util.Analyzer;

public class StateIdle implements AppState {

    private Analyzer analyzer = new Analyzer();

    @Override
    public void execute(String commandRequest, Context context) {
        context.setState(new StateExecuting());

        String[] command = analyzer.analyze(commandRequest);

        synchronized (context) {
            context.notify();
        }

        if (command != null) {
            CommandRegister.INSTANCE.execute(command[0], command[1]);
        }

        context.setState(this);
    }
}
