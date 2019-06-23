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
import com.billy.magictower.controller.HeroController;
import com.billy.magictower.entity.FloorMap;
import com.billy.magictower.util.ApplicationUtil;

import org.jetbrains.annotations.NotNull;


public class FloorView implements IGameView {


    private Bitmap[] sprite;
    private Bitmap[] hero;
    private Matrix matrix;

    private FloorController floorController;
    private HeroController heroController;

    public FloorView(MTBaseActivity context,FloorController floorController,HeroController heroController,int width)
    {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inScaled = false;
        Bitmap map = BitmapFactory.decodeResource(context.getResources(),R.drawable.map16,opts);
        Bitmap player = BitmapFactory.decodeResource(context.getResources(),R.drawable.hero16,opts);
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

        hero = new Bitmap[GamePlayConstants.GameResConstants.HERO_SPRITE_WIDTH_COUNT];
        for(int i = 0;i < GamePlayConstants.GameResConstants.HERO_SPRITE_WIDTH_COUNT;i++)
        {
            hero[i] = Bitmap.createBitmap(player,
                    i * GamePlayConstants.GameResConstants.MAP_META_WIDTH,0,
                    GamePlayConstants.GameResConstants.MAP_META_WIDTH,
                    GamePlayConstants.GameResConstants.MAP_META_HEIGHT,
                    matrix,true);
        }

        map.recycle();
        player.recycle();

        this.floorController = floorController;
        this.heroController = heroController;

    }


    @Override
    public void onDraw(Canvas lockCanvas, Paint paint) {
        FloorMap map = floorController.getMap();

        for(int i = 0;i < map.getMap().length / GamePlayConstants.MAP_WIDTH;i++)
        {
            for(int j = 0;j < GamePlayConstants.MAP_WIDTH;j++) {
                int width = lockCanvas.getWidth() / GamePlayConstants.MAP_WIDTH;
                matrix.setTranslate(
                        ((float) lockCanvas.getWidth() / GamePlayConstants.MAP_WIDTH) / GamePlayConstants.GameResConstants.MAP_META_WIDTH,
                        ((float) lockCanvas.getWidth() / GamePlayConstants.MAP_WIDTH) / GamePlayConstants.GameResConstants.MAP_META_HEIGHT);
                matrix.postTranslate(j * width, i * width);
                if (map.getMap()[i * GamePlayConstants.MAP_WIDTH + j] != GamePlayConstants.GameValueConstants.HERO)
                    drawElement(lockCanvas,
                            GamePlayConstants.GameValueConstants.valueMap.get(map.getMap()[i * GamePlayConstants.MAP_WIDTH + j]),
                            paint);
                else {
                    drawElement(lockCanvas,
                            GamePlayConstants.GameValueConstants.valueMap.get(GamePlayConstants.GameValueConstants.GROUND),
                            paint);
                    drawHero(lockCanvas, heroController.getSpriteId(), paint);
                }
            }
        }
    }

    public int getSpriteWidth()
    {
        return sprite[0].getWidth();
    }


    private void drawHero(Canvas canvas,int id,Paint paint)
    {
        canvas.drawBitmap(hero[id],matrix,paint);
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
