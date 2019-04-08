package com.epam.javacore2019.util;

import com.epam.javacore2019.command.CommandAbout;
import com.epam.javacore2019.command.CommandTime;
import com.epam.javacore2019.command.CommandWeather;

import java.util.HashMap;
import java.util.Map;

/**
 *Contains {@link Trigger} and array of parameters for all commands
 */
public enum  NameRegister {
    INSTANCE;
    final Map<Trigger, String[]> REGISTER = new HashMap<Trigger, String[]>() {
        {
            put(CommandAbout.trigger,  CommandAbout.params);
            put(CommandTime.trigger, CommandTime.params);
            put(CommandWeather.trigger, CommandWeather.params);
        }
    };
}
