package com.epam.javacore2019;

import com.epam.javacore2019.command.CommandRegister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    public static void main(String[] args) {
        CommandRegister register = new CommandRegister();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            String commandName = reader.readLine();
            register.execute(commandName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}