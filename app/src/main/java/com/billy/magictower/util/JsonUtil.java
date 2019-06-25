package com.billy.magictower.util;


import com.billy.magictower.activity.MTBaseActivity;
import com.billy.magictower.model.FloorMap;
import com.billy.magictower.model.HeroAttribute;
import com.billy.magictower.model.Monster;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtil {
    private static Gson gson = new Gson();

    public static FloorMap[] getMap(String value)
    {
        return parseJson(value,FloorMap[].class);
    }
    public static HeroAttribute getHeroAttribute(String value)
    {
        return parseJson(value,HeroAttribute.class);
    }
    public static Monster[] getMonster(String value)
    {
        return parseJson(value,Monster[].class);
    }
    
    public static <T> T parseJson(String value,Class<T> cls)
    {
        return gson.fromJson(value,cls);
    }


    public static String loadJsonFromAsset(MTBaseActivity context, String name)
    {
        InputStream is = null;
        StringBuilder stringBuffer = null;
        try {
            is = context.getAssets().open(name);
            stringBuffer = new StringBuilder();
            byte[] buf = new byte[1024];
            int byteCount;
            while ( (byteCount = is.read(buf)) != -1)
            {
                stringBuffer.append(new String(buf,0,byteCount));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(stringBuffer != null)
            return stringBuffer.toString();

        return null;
    }



    public static <T> String toJson(T map)
    {
        return gson.toJson(map);
    }
}
