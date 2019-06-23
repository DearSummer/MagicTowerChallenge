package com.billy.magictower.util;


import com.billy.magictower.model.FloorMap;
import com.billy.magictower.model.HeroAttribute;
import com.billy.magictower.model.Monster;
import com.google.gson.Gson;

public class JsonUtil {
    private static Gson gson = new Gson();

    public static FloorMap[] getMap(String value)
    {
        return gson.fromJson(value,FloorMap[].class);
    }
    public static HeroAttribute getHeroAttribute(String value)
    {
        return gson.fromJson(value,HeroAttribute.class);
    }
    public static Monster[] getMonster(String value)
    {
        return gson.fromJson(value,Monster[].class);
    }


    public static String toJson(FloorMap[] map)
    {
        return gson.toJson(map);
    }
}
