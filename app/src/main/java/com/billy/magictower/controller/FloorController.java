package com.billy.magictower.controller;

import android.os.Environment;

import com.billy.magictower.activity.MTBaseActivity;
import com.billy.magictower.entity.FloorMap;
import com.billy.magictower.util.ApplicationUtil;
import com.billy.magictower.util.JsonUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FloorController {

    private static File dir = new File(Environment.getExternalStorageDirectory() + "/.magicTower/");

    private int level = 0;
    private FloorMap[] localMap;

    public FloorController(MTBaseActivity context)
    {
        InputStream is = null;
        try {
            is = context.getAssets().open("floor.json");
            StringBuilder stringBuffer = new StringBuilder();
            byte[] buf = new byte[1024];
            int byteCount;
            while ( (byteCount = is.read(buf)) != -1)
            {
                stringBuffer.append(new String(buf,0,byteCount));
            }

            localMap = JsonUtil.getMap(stringBuffer.toString());
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
    }

    public void saveGame()
    {
        String path = Environment.getExternalStorageState();
        if(path.equals(Environment.MEDIA_MOUNTED))
        {
            OutputStream outputStream = null;
            dir.mkdirs();
            File file = new File(dir,"user_floor.json");
            try {
                outputStream = new FileOutputStream(file);
                outputStream.write(JsonUtil.toJson(localMap).getBytes());
                ApplicationUtil.log("IO","save success");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                ApplicationUtil.log("IO","save failed");
            }finally {
                if(outputStream != null)
                {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void loadGame()
    {
        InputStream is = null;
        try {
            File file = new File(dir,"user_floor.json");
            is = new FileInputStream(file);
            StringBuilder stringBuffer = new StringBuilder();
            byte[] buf = new byte[1024];
            int byteCount;
            while ( (byteCount = is.read(buf)) != -1)
            {
                stringBuffer.append(new String(buf,0,byteCount));
            }

            localMap = JsonUtil.getMap(stringBuffer.toString());
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
    }

    public void setLevel(int level)
    {
        this.level = level;
    }
    public FloorMap getMap()
    {
        return localMap[level];
    }


}
