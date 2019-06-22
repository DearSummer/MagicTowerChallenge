package com.billy.magictower.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.billy.magictower.util.ApplicationUtil;

public abstract class MTBaseActivity extends AppCompatActivity implements IGameProcess {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationUtil.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ApplicationUtil.remove(this);
    }


}
