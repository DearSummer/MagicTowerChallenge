package com.billy.magictower.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.billy.magictower.controller.HeroController;

public class HeroStatusView implements IGameView {

    private HeroController heroController;

    public HeroStatusView(HeroController controller)
    {
        heroController = controller;
    }


    @Override
    public void onDraw(Canvas lockCanvas, Paint paint) {

    }

    @Override
    public void onExit() {

    }
}
