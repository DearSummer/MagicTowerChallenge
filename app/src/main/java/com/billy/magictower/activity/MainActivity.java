package com.billy.magictower.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.billy.magictower.GamePlayConstants;
import com.billy.magictower.R;
import com.billy.magictower.util.ApplicationUtil;

public class MainActivity extends MTBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        LinearLayout ll_start = findViewById(R.id.ll_start);
        ll_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationUtil.jump(MainActivity.this,GameActivity.class, GamePlayConstants.GameStatusCode.NEW_GAME);
            }
        });

        LinearLayout ll_load = findViewById(R.id.ll_load);
        ll_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationUtil.jump(MainActivity.this,GameActivity.class,GamePlayConstants.GameStatusCode.LOAD_GAME);
            }
        });

        LinearLayout ll_end = findViewById(R.id.ll_end);
        ll_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationUtil.exit();
            }
        });
    }

    @Override
    public void onExit() {
    }
}
