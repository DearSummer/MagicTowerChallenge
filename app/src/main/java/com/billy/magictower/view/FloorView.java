package com.billy.magictower.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.billy.magictower.GamePlayConstants;
import com.billy.magictower.R;
import com.billy.magictower.activity.MTBaseActivity;
import com.billy.magictower.entity.FloorMap;
import com.billy.magictower.util.JsonUtil;

import java.io.IOException;
import java.io.InputStream;

public class FloorView implements IGameView {

    private int level;

    private Bitmap ground,wall,yellowDoor;
    private FloorMap[] localMap;

    public FloorView(MTBaseActivity context)
    {
        Bitmap map = BitmapFactory.decodeResource(context.getResources(), R.drawable.map16);
        ground = Bitmap.createBitmap(map,0,0,
                GamePlayConstants.GameResContants.MAP_META_WIDTH, GamePlayConstants.GameResContants.MAP_META_HEIGHT);
        wall = Bitmap.createBitmap(map, GamePlayConstants.GameResContants.MAP_META_WIDTH,0 ,
                GamePlayConstants.GameResContants.MAP_META_WIDTH, GamePlayConstants.GameResContants.MAP_META_HEIGHT);
        yellowDoor = Bitmap.createBitmap(map,
                GamePlayConstants.GameResContants.MAP_META_WIDTH, GamePlayConstants.GameResContants.MAP_META_HEIGHT,
                GamePlayConstants.GameResContants.MAP_META_WIDTH, GamePlayConstants.GameResContants.MAP_META_HEIGHT);

        map.recycle();
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

    public void setLevel(int level)
    {
        this.level = level;
    }


    @Override
    public void onDraw(Canvas lockCanvas, Paint paint) {
        lockCanvas.drawBitmap(ground,0,0,paint);
        lockCanvas.drawBitmap(wall,100,100,paint);
        lockCanvas.drawBitmap(yellowDoor,200,200,paint);
    }

    @Override
    public void onExit() {
        if(!wall.isRecycled())
            wall.recycle();

        if(!ground.isRecycled())
            ground.recycle();

        if(!yellowDoor.isRecycled())
            yellowDoor.recycle();
    }
}
