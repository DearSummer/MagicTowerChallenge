package com.billy.magictower.util;

import android.os.Environment;

import com.billy.magictower.activity.MTBaseActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtil {

    private static File dir = new File(Environment.getExternalStorageDirectory() + "/magicTower/");


    public static <T> T load(String filName,Class<? extends T> cls)
    {
        InputStream is = null;
        T msg = null;
        try {
            File file = new File(dir,filName);
            is = new FileInputStream(file);
            StringBuilder stringBuffer = new StringBuilder();
            byte[] buf = new byte[1024];
            int byteCount;
            while ( (byteCount = is.read(buf)) != -1)
            {
                stringBuffer.append(new String(buf,0,byteCount));
            }


            msg = JsonUtil.parseJson(stringBuffer.toString(),cls);
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

        return msg;
    }


    public static <T> void save(T entity, String fileName) {
        String path = Environment.getExternalStorageState();
        if (path.equals(Environment.MEDIA_MOUNTED)) {
            OutputStream outputStream = null;
            if (!dir.exists())
                dir.mkdirs();
            File file = new File(dir, fileName);
            try {
                outputStream = new FileOutputStream(file);
                outputStream.write(JsonUtil.toJson(entity).getBytes());
                ApplicationUtil.log("IO", "save " + fileName + " success");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                ApplicationUtil.log("IO", "save " + fileName + " failed");
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
