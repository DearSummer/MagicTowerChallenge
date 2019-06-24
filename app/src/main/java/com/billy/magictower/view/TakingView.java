package com.billy.magictower.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.util.Log;

import com.billy.magictower.GamePlayConstants;
import com.billy.magictower.R;
import com.billy.magictower.activity.MTBaseActivity;
import com.billy.magictower.controller.ShoppingController;
import com.billy.magictower.controller.StoryController;
import com.billy.magictower.model.Goods;
import com.billy.magictower.util.BitmapUtil;

public class TakingView implements IGameView {

    private ShoppingController shoppingController;
    private StoryController storyController;

    private Bitmap hp,atk,def;
    private RectF hpRect,atkRect,defRect;

    public TakingView(MTBaseActivity context,StoryController storyController, ShoppingController shoppingController) {
        this.shoppingController = shoppingController;
        this.storyController = storyController;

        hp = loadBitmap(context, R.drawable.hp);
        atk = loadBitmap(context,R.drawable.sword);
        def = loadBitmap(context,R.drawable.shield);

        hpRect = new RectF();
        atkRect = new RectF();
        defRect = new RectF();
    }

    @Override
    public void onDraw(Canvas lockCanvas, Paint paint) {
        if (shoppingController.isShopping()) {
            Goods goods = shoppingController.getGoods();
            clear(lockCanvas);
            drawStatus(lockCanvas,goods,paint);
        }
    }

    @Override
    public void onExit() {
        BitmapUtil.recycle(hp);
        BitmapUtil.recycle(atk);
        BitmapUtil.recycle(def);
    }

    private Bitmap loadBitmap(MTBaseActivity context, int id)
    {
        Bitmap bitmap = BitmapUtil.decode(context,id);
        Matrix matrix = new Matrix();
        matrix.setScale(GamePlayConstants.GameResConstants.MAP_META_WIDTH  / (float)bitmap.getWidth() * 2.5f,
                GamePlayConstants.GameResConstants.MAP_META_HEIGHT / (float)bitmap.getHeight() * 2.5f);

        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }

    public int onClick(float x,float y)
    {
        int resultCode = 0;
        if(shoppingController.isShopping())
        {
            if(hpRect.contains(x,y)){
                resultCode = shoppingController.buy(GamePlayConstants.ShoppingStatusCode.HP);
                Log.d("shop", "onClick: hp");
            }
            else if(atkRect.contains(x,y)){
                resultCode = shoppingController.buy(GamePlayConstants.ShoppingStatusCode.ATK);
                Log.d("shop", "onClick: atk");
            }
            else if(defRect.contains(x,y)){
                resultCode = shoppingController.buy(GamePlayConstants.ShoppingStatusCode.DEF);
                Log.d("shop", "onClick: def");
            }
            else{
                resultCode = shoppingController.buy(GamePlayConstants.ShoppingStatusCode.NOT_NEED);
                Log.d("shop", "onClick: not_need");
            }
        }

        return resultCode;
    }


    private void drawStatus(Canvas lockCanvas,Goods goods, Paint paint) {
        float left = (float)lockCanvas.getWidth() / 2 - (hp.getWidth() * 2);
        float top = (float)lockCanvas.getHeight() / 3 -
                GamePlayConstants.GameResConstants.MAP_META_HEIGHT;

        paint.setTextSize(30);
        lockCanvas.drawText(
                "只需要" + goods.getMoney() + "块就可以获得力量哦",
                left - GamePlayConstants.GameResConstants.MAP_META_WIDTH,
                top - GamePlayConstants.GameResConstants.MAP_META_HEIGHT,
                paint
        );

        lockCanvas.drawBitmap(
                hp,
                left,
                top,
                paint);
        lockCanvas.drawText(String.valueOf(goods.getHp())
                ,left + hp.getWidth() * 2,top + (float)hp.getWidth()/2,paint);
        hpRect.set(left,top,left + hp.getWidth() * 4 ,top + hp.getHeight());

        top += GamePlayConstants.GameResConstants.MAP_META_HEIGHT + hp.getHeight();
        lockCanvas.drawBitmap(
                atk,
                left,
                top,
                paint);
        lockCanvas.drawText(String.valueOf(goods.getAtk())
                ,left + atk.getWidth() * 2,top + (float)atk.getWidth()/2,paint);
        atkRect.set(left,top,left + atk.getWidth() * 4 ,top + atk.getHeight());

        top += GamePlayConstants.GameResConstants.MAP_META_HEIGHT + atk.getHeight();
        lockCanvas.drawBitmap(
                def,
                left,
                top,
                paint);
        lockCanvas.drawText(String.valueOf(goods.getDef())
                ,left + def.getWidth() * 2,top + (float)def.getWidth()/2,paint);
        defRect.set(left,top,left + def.getWidth() * 4 ,top + def.getHeight());

    }

    private void clear(Canvas lockCanvas) {
        lockCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        lockCanvas.drawColor(Color.WHITE);
    }

}
