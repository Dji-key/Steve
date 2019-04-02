package com.epam.javacore2019.command;

import com.epam.javacore2019.Info;

public class CommandAbout extends ACommand {

    CommandAbout(String name) {
        super(name);
        description = "Information about program";
    }

    @Override
    public void execute() {
        System.out.println("Version: " + Info.VERSION + "\nAuthor: " + Info.AUTHOR);
    }
}
