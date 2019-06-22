package com.billy.magictower.util;


import com.billy.magictower.entity.FloorMap;
import com.google.gson.Gson;

public class JsonUtil {
    private static Gson gson = new Gson();

    public static FloorMap[] getMap(String value)
    {
        return gson.fromJson(value,FloorMap[].class);
    }

}
