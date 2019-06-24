package com.billy.magictower.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;

import com.billy.magictower.GamePlayConstants;
import com.billy.magictower.R;
import com.billy.magictower.activity.MTBaseActivity;
import com.billy.magictower.controller.HeroController;
import com.billy.magictower.util.BitmapUtil;

public class HeroStatusView implements IGameView {

    private HeroController heroController;

    private Bitmap blueKey,redKey,yellowKey,sword,hp,shield,coin,book;

    private RectF bookRect;

    public HeroStatusView(MTBaseActivity context,HeroController controller)
    {
        heroController = controller;
        blueKey = loadBitmap(context, R.drawable.bluekey,2.5f);
        redKey = loadBitmap(context,R.drawable.redkey,2.5f);
        yellowKey = loadBitmap(context,R.drawable.yellowkey,2.5f);
        sword = loadBitmap(context,R.drawable.sword,2.5f);
        hp = loadBitmap(context,R.drawable.hp,2.5f);
        shield = loadBitmap(context,R.drawable.shield,2.5f);
        coin = loadBitmap(context,R.drawable.coin,2.5f);
        book = loadBitmap(context,R.drawable.book,1.5f);

        bookRect = new RectF();
    }

    private Bitmap loadBitmap(MTBaseActivity context,int id,float scale)
    {
        Bitmap bitmap = BitmapUtil.decode(context,id);
        Matrix matrix = new Matrix();
        matrix.setScale(GamePlayConstants.GameResConstants.MAP_META_WIDTH  / (float)bitmap.getWidth() * scale,
                 GamePlayConstants.GameResConstants.MAP_META_HEIGHT / (float)bitmap.getHeight() * scale);

        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }


    @Override
    public void onDraw(Canvas lockCanvas, Paint paint) {
        drawEquipment(lockCanvas, paint);
        drawStatus(lockCanvas, paint);
    }

    private void drawStatus(Canvas lockCanvas, Paint paint) {
        float left = GamePlayConstants.GameResConstants.MAP_META_WIDTH;
        float top = GamePlayConstants.GameResConstants.MAP_SPRITE_FINAL_WIDTH * GamePlayConstants.MAP_WIDTH +
                GamePlayConstants.GameResConstants.MAP_META_HEIGHT;
        lockCanvas.drawBitmap(
                hp,
                left,
                top,
                paint);

        lockCanvas.drawText(String.valueOf(heroController.getHeroAttribute().getHp())
                ,left + hp.getWidth() * 2,top + (float)hp.getWidth()/2,paint);

        top += GamePlayConstants.GameResConstants.MAP_META_HEIGHT + hp.getHeight();
        lockCanvas.drawBitmap(
                sword,
                GamePlayConstants.GameResConstants.MAP_META_WIDTH,
                top,
                paint);
        lockCanvas.drawText(String.valueOf(heroController.getHeroAttribute().getAtk())
                ,left + sword.getWidth() * 2,top + (float)sword.getWidth()/2,paint);

        top += GamePlayConstants.GameResConstants.MAP_META_HEIGHT + sword.getHeight();
        lockCanvas.drawBitmap(
                shield,
                GamePlayConstants.GameResConstants.MAP_META_WIDTH,
                top,
                paint);
        lockCanvas.drawText(String.valueOf(heroController.getHeroAttribute().getDef())
                ,left + shield.getWidth() * 2,top + (float)shield.getWidth()/2,paint);

        top += GamePlayConstants.GameResConstants.MAP_META_HEIGHT + shield.getHeight();
        lockCanvas.drawBitmap(
                coin,
                GamePlayConstants.GameResConstants.MAP_META_WIDTH,
                top,
                paint);
        lockCanvas.drawText(String.valueOf(heroController.getHeroAttribute().getCoin())
                ,left + coin.getWidth() * 2,top + (float)coin.getWidth()/2,paint);
    }

    private void drawEquipment(Canvas lockCanvas, Paint paint) {

        float left = (float) lockCanvas.getWidth() / 2 + GamePlayConstants.GameResConstants.MAP_META_WIDTH;
        float top = GamePlayConstants.GameResConstants.MAP_SPRITE_FINAL_WIDTH * GamePlayConstants.MAP_WIDTH +
                GamePlayConstants.GameResConstants.MAP_META_HEIGHT * 2;

        paint.setTextSize(30);
        lockCanvas.drawBitmap(
                yellowKey,
                left,
                top,
                paint);

        lockCanvas.drawText(String.valueOf(heroController.getHeroAttribute().getYellowKey())
                , left + yellowKey.getWidth() * 2, top + (float) yellowKey.getWidth() / 2, paint);

        top += GamePlayConstants.GameResConstants.MAP_META_HEIGHT + yellowKey.getHeight();
        lockCanvas.drawBitmap(
                blueKey,
                left,
                top,
                paint);

        lockCanvas.drawText(String.valueOf(heroController.getHeroAttribute().getBlueKey())
                , left + blueKey.getWidth() * 2, top + (float) blueKey.getWidth() / 2, paint);


        top += GamePlayConstants.GameResConstants.MAP_META_HEIGHT + blueKey.getHeight();
        lockCanvas.drawBitmap(
                redKey,
                left,
                top,
                paint);

        lockCanvas.drawText(String.valueOf(heroController.getHeroAttribute().getRedKey())
                , left + redKey.getWidth() * 2, top + (float) redKey.getWidth() / 2, paint);


        if (heroController.getEquipmentList().size() > 0) {
            top += GamePlayConstants.GameResConstants.MAP_META_HEIGHT + redKey.getHeight();
            lockCanvas.drawBitmap(book, left, top, paint);
            bookRect.set(left,top,left + book.getWidth(),top + book.getHeight());
        }

    }

    public int onClick(float x,float y)
    {
        if(heroController.getEquipmentList().size() > 0)
        {
            if(bookRect.contains(x,y))
            {
                return GamePlayConstants.EquipmentCode.BOOK_CLICK;
            }
        }

        return 0;
    }



    @Override
    public void onExit() {
        BitmapUtil.recycle(yellowKey);
        BitmapUtil.recycle(redKey);
        BitmapUtil.recycle(blueKey);
        BitmapUtil.recycle(sword);
        BitmapUtil.recycle(shield);
        BitmapUtil.recycle(hp);
        BitmapUtil.recycle(book);
    }
}
