package com.billy.magictower.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.billy.magictower.R;
import com.billy.magictower.controller.FloorController;
import com.billy.magictower.view.FloorView;
import com.billy.magictower.view.MainGameView;

public class GameActivity extends MTBaseActivity {


    private MainGameView mainGameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        FloorController floorController = new FloorController(this);

        mainGameView = findViewById(R.id.gv_main);
        mainGameView.register(new FloorView(this,floorController,dm.widthPixels));


    }

    @Override
    public void onExit() {
        mainGameView.exitGame();
    }
}
