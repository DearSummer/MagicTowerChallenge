package com.billy.magictower.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.billy.magictower.GamePlayConstants;

import java.util.ArrayList;
import java.util.List;

public class MainGameView extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    private SurfaceHolder holder;
    private Paint paint;

    private boolean playing =true;
    private List<IGameView> gameViewList;

    public MainGameView(Context context) {
        super(context);

        init();
    }

    public MainGameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public MainGameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MainGameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    private void init()
    {
        paint = new Paint();
        gameViewList = new ArrayList<>();

        holder = this.getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void run() {
        while(playing)
        {
            long beforeRender = System.currentTimeMillis();

            logic();
            draw();

            long afterRender = System.currentTimeMillis();
            long sleepTime = GamePlayConstants.GAME_LOOP_TIME - (afterRender - beforeRender);
            if(sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public void exitGame()
    {
        playing = false;
        for(IGameView view : gameViewList)
        {
            view.onExit();
        }
    }

    private void logic()
    {

    }



    private void draw()
    {
        Canvas canvas = holder.lockCanvas();
        if(canvas == null)
            return;
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.drawColor(Color.WHITE);
        for(IGameView view : gameViewList)
        {
            view.onDraw(canvas,paint);
        }
        holder.unlockCanvasAndPost(canvas);
    }

    public void register(IGameView view) {
        if (!gameViewList.contains(view))
            gameViewList.add(view);
    }

}
