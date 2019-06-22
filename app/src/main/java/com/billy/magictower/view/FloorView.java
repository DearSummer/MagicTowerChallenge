package com.billy.magictower.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.billy.magictower.GamePlayConstants;
import com.billy.magictower.R;
import com.billy.magictower.activity.MTBaseActivity;

public class FloorView implements IGameView {

    private int level;

    private Bitmap ground,wall;

    public FloorView(MTBaseActivity context)
    {
        Bitmap map = BitmapFactory.decodeResource(context.getResources(), R.drawable.map16);
        ground = Bitmap.createBitmap(map,0,0,
                GamePlayConstants.GameResContants.MAP_META_WIDTH, GamePlayConstants.GameResContants.MAP_META_HEIGHT);
        wall = Bitmap.createBitmap(map, GamePlayConstants.GameResContants.MAP_META_WIDTH,0 ,
                GamePlayConstants.GameResContants.MAP_META_WIDTH, GamePlayConstants.GameResContants.MAP_META_HEIGHT);

        map.recycle();
    }

    public void setLevel(int level)
    {
        this.level = level;
    }


    @Override
    public void onDraw(Canvas lockCanvas, Paint paint) {
        lockCanvas.drawBitmap(ground,0,0,paint);
        lockCanvas.drawBitmap(wall,100,100,paint);
    }

    @Override
    public void onExit() {
        if(!wall.isRecycled())
            wall.recycle();

        if(!ground.isRecycled())
            ground.recycle();
    }
}
