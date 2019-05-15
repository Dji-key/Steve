package com.epam.javacore2019.steveclient.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    static List<Trigger> getTriggersFromServer(String url) {

        List<Trigger> triggers = new ArrayList<>();

        StringBuilder responseString = new StringBuilder();
        try (InputStream inputStream = new URL(url).openStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String input;
            while ((input = reader.readLine()) != null) {
                responseString.append(input);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObjectRaw = new JSONObject(responseString.toString());
            JSONArray jsonArrayOfTriggers = jsonObjectRaw.getJSONArray("triggers");

            if (jsonArrayOfTriggers != null) {
                int length = jsonArrayOfTriggers.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonOneTrigger = jsonArrayOfTriggers.getJSONObject(i);

                    String key = jsonOneTrigger.getString("key");

                    String[] params = null;
                    if (!jsonOneTrigger.isNull("params")) {
                        JSONArray jsonParams = jsonOneTrigger.getJSONArray("params");
                        params = new String[jsonParams.length()];
                        for (int j = 0; j < params.length; j++) {
                            params[j] = jsonParams.getString(j);
                        }
                    }

                    String[] strictParams = null;
                    if (!jsonOneTrigger.isNull("strictParams")) {
                        JSONArray jsonStrictParams = jsonOneTrigger.getJSONArray("strictParams");
                        strictParams = new String[jsonStrictParams.length()];
                        for (int j = 0; j < strictParams.length; j++) {
                            strictParams[j] = jsonStrictParams.getString(j);
                        }
                    }

                    String[] words = null;
                    if (!jsonOneTrigger.isNull("words")) {
                        JSONArray jsonWords = jsonOneTrigger.getJSONArray("words");
                        words = new String[jsonWords.length()];
                        for (int j = 0; j < words.length; j++) {
                            words[j] = jsonWords.getString(j);
                        }
                    }

                    triggers.add(new Trigger(key, params, strictParams, words));
                }
            }
        } catch (JSONException e) {
            e.getCause();
        }

        return triggers;
    }

    public static JSONObject readJsonFromStream(InputStream inputStream) throws IOException, JSONException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();

        while (reader.ready()) {
            builder.append(reader.readLine());
        }
        String jsonText = builder.toString();

        return new JSONObject(jsonText);
    }

//    public static String readAllFromStream(InputStream inputStream) throws IOException {
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
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
