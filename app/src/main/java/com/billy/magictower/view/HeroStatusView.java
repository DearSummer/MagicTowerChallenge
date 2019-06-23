package com.billy.magictower.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.billy.magictower.GamePlayConstants;
import com.billy.magictower.R;
import com.billy.magictower.activity.MTBaseActivity;
import com.billy.magictower.controller.HeroController;
import com.billy.magictower.util.BitmapUtil;

public class HeroStatusView implements IGameView {

    private HeroController heroController;

    private Bitmap blueKey,redKey,yellowKey,sword,hp,shield,coin;

    public HeroStatusView(MTBaseActivity context,HeroController controller)
    {
        heroController = controller;
        blueKey = loadBitmap(context, R.drawable.bluekey);
        redKey = loadBitmap(context,R.drawable.redkey);
        yellowKey = loadBitmap(context,R.drawable.yellowkey);
        sword = loadBitmap(context,R.drawable.sword);
        hp = loadBitmap(context,R.drawable.hp);
        shield = loadBitmap(context,R.drawable.shield);
        coin = loadBitmap(context,R.drawable.coin);
    }

    private Bitmap loadBitmap(MTBaseActivity context,int id)
    {
        Bitmap bitmap = BitmapUtil.decode(context,id);
        Matrix matrix = new Matrix();
        matrix.setScale(GamePlayConstants.GameResConstants.MAP_META_WIDTH  / (float)bitmap.getWidth() * 2.5f,
                 GamePlayConstants.GameResConstants.MAP_META_HEIGHT / (float)bitmap.getHeight() * 2.5f);

        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }


    @Override
    public void onDraw(Canvas lockCanvas, Paint paint) {
        drawEquipment(lockCanvas, paint);
        drawStatus(lockCanvas, paint);
    }

    private void drawStatus(Canvas lockCanvas, Paint paint) {
        float left = GamePlayConstants.GameResConstants.MAP_META_WIDTH;
        float right = GamePlayConstants.GameResConstants.MAP_SPRITE_FINAL_WIDTH * GamePlayConstants.MAP_WIDTH +
                GamePlayConstants.GameResConstants.MAP_META_HEIGHT;
        lockCanvas.drawBitmap(
                hp,
                left,
                right,
                paint);

        lockCanvas.drawText(String.valueOf(heroController.getHeroAttribute().getHp())
                ,left + hp.getWidth() * 2,right + (float)hp.getWidth()/2,paint);

        right += GamePlayConstants.GameResConstants.MAP_META_HEIGHT + hp.getHeight();
        lockCanvas.drawBitmap(
                sword,
                GamePlayConstants.GameResConstants.MAP_META_WIDTH,
                right,
                paint);
        lockCanvas.drawText(String.valueOf(heroController.getHeroAttribute().getAtk())
                ,left + sword.getWidth() * 2,right + (float)sword.getWidth()/2,paint);

        right += GamePlayConstants.GameResConstants.MAP_META_HEIGHT + sword.getHeight();
        lockCanvas.drawBitmap(
                shield,
                GamePlayConstants.GameResConstants.MAP_META_WIDTH,
                right,
                paint);
        lockCanvas.drawText(String.valueOf(heroController.getHeroAttribute().getDef())
                ,left + shield.getWidth() * 2,right + (float)shield.getWidth()/2,paint);

        right += GamePlayConstants.GameResConstants.MAP_META_HEIGHT + shield.getHeight();
        lockCanvas.drawBitmap(
                coin,
                GamePlayConstants.GameResConstants.MAP_META_WIDTH,
                right,
                paint);
        lockCanvas.drawText(String.valueOf(heroController.getHeroAttribute().getCoin())
                ,left + coin.getWidth() * 2,right + (float)coin.getWidth()/2,paint);
    }

    private void drawEquipment(Canvas lockCanvas, Paint paint) {

        float left = (float)lockCanvas.getWidth() / 2 + GamePlayConstants.GameResConstants.MAP_META_WIDTH;
        float right = GamePlayConstants.GameResConstants.MAP_SPRITE_FINAL_WIDTH * GamePlayConstants.MAP_WIDTH +
                GamePlayConstants.GameResConstants.MAP_META_HEIGHT * 2;

        paint.setTextSize(30);
        lockCanvas.drawBitmap(
                yellowKey,
                left,
                right,
                paint);

        lockCanvas.drawText(String.valueOf(heroController.getHeroAttribute().getYellowKey())
                ,left + yellowKey.getWidth() * 2,right + (float)yellowKey.getWidth()/2,paint);

        right += GamePlayConstants.GameResConstants.MAP_META_HEIGHT + yellowKey.getHeight();
        lockCanvas.drawBitmap(
                blueKey,
                left,
                right,
                paint);

        lockCanvas.drawText(String.valueOf(heroController.getHeroAttribute().getBlueKey())
                ,left + blueKey.getWidth() * 2,right + (float)blueKey.getWidth()/2,paint);


        right += GamePlayConstants.GameResConstants.MAP_META_HEIGHT + blueKey.getHeight();
        lockCanvas.drawBitmap(
                redKey,
                left,
                right,
                paint);

        lockCanvas.drawText(String.valueOf(heroController.getHeroAttribute().getRedKey())
                ,left + redKey.getWidth() * 2,right + (float)redKey.getWidth()/2,paint);
    }

    @Override
    public void onExit() {
        BitmapUtil.recycle(yellowKey);
        BitmapUtil.recycle(redKey);
        BitmapUtil.recycle(blueKey);
        BitmapUtil.recycle(sword);
        BitmapUtil.recycle(shield);
        BitmapUtil.recycle(hp);
    }
}
