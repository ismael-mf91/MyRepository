package com.example.utils;

import com.example.myrestapplication.data.model.Identity;
import com.example.myrestapplication.data.model.WorkItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.Map;

public class Utils {

    final int SUCCESS = 200;

    public String convertObjectToJSON(Object object){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(object);
        return json;

    }

    public Collection<WorkItem> convertStringToObject(String string){
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<WorkItem>>(){}.getType();
        Collection<WorkItem> enums = gson.fromJson(string, collectionType);
        return enums;

    }

    public Identity convertStringToIdentity(String string){
        Gson gson = new Gson();
        Identity identity = gson.fromJson(string, Identity.class);

        return identity;
    }

    public Map convertStringToMap(String string){
        Gson gson = new Gson();
        Map map = gson.fromJson(string, Map.class);

        return map;
    }

    public JSONObject convertStringToJson(String string){
        JSONObject json = null;
        try {
             json =  new JSONObject(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getOutputJson(HttpURLConnection conn) throws IOException {
        String output = "";
        String total = "";
        if (conn.getResponseCode() != SUCCESS) {
            throw new RuntimeException("Failed : HTTP error code : "   + conn.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        while ((output = br.readLine()) != null) {
            total += output;
        }
        return total;
    }


}
