package com.hk4browser.app.hk4browser.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by anton on 14.11.2017.
 */

public class JsonUtils {
//TODO do it in another thread
    public HashMap<String, List<String>> getExpandableListDetail(String jsonString){

        HashMap<String, List<String>> list = new HashMap<String, List<String>>();
        JSONObject jsonResponse = new JSONObject();
        try {
            jsonResponse = new JSONObject(jsonString);
            Log.i("menu: "," create new Json Object for getExpandableListDetail in JsonUtils");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("EXCEPTION!!!: "," getExpandableListDetail coming string is: "+jsonString);
        }
        Iterator iterator = jsonResponse.keys();
        List<String> childs;
        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            childs = new ArrayList<String>();
            try {
                JSONArray childsArray = jsonResponse.getJSONArray(s);
                for (int i = 0; i < childsArray.length(); i++) {
                    JSONObject o = childsArray.getJSONObject(i);
                    String childName = o.getString("name");
                    childs.add(childName);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            list.put(s, childs);
        }
        Log.i("resp", "returned hashmap from JsonUtil keyset size "+list.keySet().size());
        return list;
    }
}
