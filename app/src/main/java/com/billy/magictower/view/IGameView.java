package com.billy.magictower.view;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface IGameView {
    void onDraw(Canvas lockCanvas, Paint paint);
    void onExit();
}
