package com.epam.javacore2019.steveserver.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Utils {

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {

        try (InputStream input = new URL(url).openStream()) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder builder = new StringBuilder();

            while (reader.ready()) {
                builder.append(reader.readLine());
            }

            String jsonText = builder.toString();

            return new JSONObject(jsonText);
        }
    }

//    private static String readAll(BufferedReader reader) throws IOException {
//
//        StringBuilder builder = new StringBuilder();
//
//        while (reader.ready()) {
//            builder.append(reader.readLine());
//        }
//
//        return builder.toString();
//    }

    public static String readStringFromStream(InputStream inputStream) throws IOException {

        byte[] byteArray = new byte[inputStream.available()];
        inputStream.read(byteArray);

        return new String(byteArray);
    }

}
