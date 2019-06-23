package com.billy.magictower.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.billy.magictower.GamePlayConstants;
import com.billy.magictower.R;
import com.billy.magictower.activity.MTBaseActivity;
import com.billy.magictower.controller.FloorController;
import com.billy.magictower.entity.FloorMap;
import com.billy.magictower.util.ApplicationUtil;
import com.billy.magictower.util.JsonUtil;

import org.jetbrains.annotations.NotNull;


public class FloorView implements IGameView {


    private Bitmap[] sprite;
    private Matrix matrix;

    private FloorController controller;

    public FloorView(MTBaseActivity context,FloorController controller,int width)
    {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inScaled = false;
        Bitmap map = BitmapFactory.decodeResource(context.getResources(),R.drawable.map16,opts);
        ApplicationUtil.log("width",map.getWidth());
        ApplicationUtil.log("height",map.getHeight());


        sprite = new Bitmap[GamePlayConstants.GameResConstants.MAP_SPRITE_HEIGHT_COUNT *
                                GamePlayConstants.GameResConstants.MAP_META_WIDTH];

        matrix = new Matrix();
        matrix.setScale(
                ((float)width / GamePlayConstants.MAP_WIDTH) / GamePlayConstants.GameResConstants.MAP_META_WIDTH,
                ((float)width / GamePlayConstants.MAP_WIDTH) / GamePlayConstants.GameResConstants.MAP_META_HEIGHT);
        ApplicationUtil.log("__MATRIX__",matrix.toString());
        for(int i = 0; i < GamePlayConstants.GameResConstants.MAP_SPRITE_HEIGHT_COUNT; i++)
        {
            for(int j = 0; j < GamePlayConstants.GameResConstants.MAP_SPRITE_WIDTH_COUNT; j++)
            {
                sprite[i * GamePlayConstants.GameResConstants.MAP_SPRITE_WIDTH_COUNT + j] =
                        Bitmap.createBitmap(map,
                                j * GamePlayConstants.GameResConstants.MAP_META_WIDTH,
                                i* GamePlayConstants.GameResConstants.MAP_META_HEIGHT,
                                GamePlayConstants.GameResConstants.MAP_META_WIDTH,
                                GamePlayConstants.GameResConstants.MAP_META_HEIGHT,
                                matrix,true);
            }
        }

        map.recycle();

        this.controller = controller;

    }




    @Override
    public void onDraw(Canvas lockCanvas, Paint paint) {
        FloorMap map = controller.getMap();

        for(int i = 0;i < map.getMap().length / GamePlayConstants.MAP_WIDTH;i++)
        {
            for(int j = 0;j < GamePlayConstants.MAP_WIDTH;j++)
            {
                int width = lockCanvas.getWidth() / GamePlayConstants.MAP_WIDTH;
                matrix.setTranslate(
                        ((float)lockCanvas.getWidth() / GamePlayConstants.MAP_WIDTH) / GamePlayConstants.GameResConstants.MAP_META_WIDTH,
                        ((float)lockCanvas.getWidth() / GamePlayConstants.MAP_WIDTH) / GamePlayConstants.GameResConstants.MAP_META_HEIGHT);
                matrix.postTranslate(j * width,i * width);
                drawElement(lockCanvas,
                        GamePlayConstants.GameValueConstants.valueMap.get(map.getMap()[i * GamePlayConstants.MAP_WIDTH + j]),
                        paint);
            }
        }
    }


    private void drawElement(Canvas canvas,int id,Paint paint)
    {
        canvas.drawBitmap(sprite[id],matrix,paint);
    }

    @Override
    public void onExit() {
        for(Bitmap bitmap : sprite)
        {
            recycle(bitmap);
        }
    }

    private void recycle(@NotNull Bitmap bitmap)
    {
        if(!bitmap.isRecycled())
            bitmap.recycle();
    }

}
