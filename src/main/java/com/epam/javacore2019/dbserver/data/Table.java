package com.epam.javacore2019.dbserver.data;

import static com.epam.javacore2019.dbserver.Database.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;

public class Table {

    private JSONObject jsonCriminalsStruct;
    private JSONObject jsonCrimeFamStruct;

    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(STRUCTURE_DIR + "criminals" + STRUCTURE_EXT));
            StringBuilder builder = new StringBuilder();
            while (reader.ready()) {
                builder.append(reader.readLine());
            }
            reader.close();
            jsonCriminalsStruct = new JSONObject(builder.toString());

            reader = new BufferedReader(new BufferedReader(new FileReader(STRUCTURE_DIR + "crimeFamilies" + STRUCTURE_EXT)));
            builder = new StringBuilder();
            while (reader.ready()) {
                builder.append(reader.readLine());
            }
            reader.close();
            jsonCrimeFamStruct = new JSONObject(builder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
