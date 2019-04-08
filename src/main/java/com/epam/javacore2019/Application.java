package com.epam.javacore2019;

import com.epam.javacore2019.command.CommandRegister;
import com.epam.javacore2019.util.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    public static void main(String[] args) {

        CommandRegister register = CommandRegister.INSTANCE;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            String input = reader.readLine();
            String[] command = Parser.parseString(input);
            if (command[0] != null) {
                if (command[1] != null) {
                    register.execute(command[0], command[1]);
                } else {
                    register.execute(command[0]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}