package com.epam.javacore2019.steveclient.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class Utils {

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

                    String[] words = null;
                    if (!jsonOneTrigger.isNull("words")) {
                        JSONArray jsonWords = jsonOneTrigger.getJSONArray("words");
                        words = new String[jsonWords.length()];
                        for (int j = 0; j < words.length; j++) {
                            words[j] = jsonWords.getString(j);
                        }
                    }

                    triggers.add(new Trigger(key, params, words));
                }
            }
        } catch (JSONException e) {
            e.getCause();
        }

        return triggers;
    }

}
