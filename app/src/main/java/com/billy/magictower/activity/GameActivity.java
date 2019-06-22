package com.billy.magictower.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.billy.magictower.R;
import com.billy.magictower.view.FloorView;
import com.billy.magictower.view.MainGameView;

public class GameActivity extends MTBaseActivity {


    private MainGameView mainGameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mainGameView = findViewById(R.id.gv_main);
        mainGameView.register(new FloorView(this));
    }

    @Override
    public void onExit() {
        mainGameView.exitGame();
    }
}
