package com.automationTest.service;

import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class JSONService {
    private static volatile JSONService instance = null;
    private JSONService() {

    }

    public static JSONService getInit() {
        if (instance == null) {
            synchronized (JSONService.class) {
                if (instance == null) {
                    instance = new JSONService();
                }
            }
        }
        return instance;
    }
    public  String prettyJSON(JSONObject json){
        return new GsonBuilder().setPrettyPrinting().create().toJson(json.toMap());
    }
}
