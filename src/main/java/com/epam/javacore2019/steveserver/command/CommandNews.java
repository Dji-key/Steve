package com.epam.javacore2019.steveserver.command;

public class CommandNews extends ACommand{

    private final Trigger TRIGGER = new Trigger("news", null, new String[] {"-t"}, "new", "tidings");

    CommandNews() {
        description = "Shows article for given topic or random.\nParameters: -t (topic) defines topic for article";
    }

    @Override
    public Trigger getTrigger() {
        return TRIGGER;
    }


}
