package com.billy.magictower.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.billy.magictower.activity.MTBaseActivity;

public class BitmapUtil {
    public static Bitmap decode(MTBaseActivity context,int id)
    {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inScaled = false;
        return BitmapFactory.decodeResource(context.getResources(), id,opts);
    }

    public static void recycle(Bitmap bitmap)
    {
        if(!bitmap.isRecycled())
            bitmap.recycle();
    }
}
