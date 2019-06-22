package com.billy.magictower.util;

import android.content.Intent;

import com.billy.magictower.activity.MTBaseActivity;

import java.util.LinkedList;
import java.util.List;

public class ApplicationUtil {

    private static List<MTBaseActivity> stack = new LinkedList<>();

    public static void add(MTBaseActivity magicTowerActivity) {
        if (!stack.contains(magicTowerActivity))
            stack.add(magicTowerActivity);
    }

    public static void remove(MTBaseActivity magicTowerActivity) {
        stack.remove(magicTowerActivity);
    }

    public static void exit() {
        for (MTBaseActivity activity : stack) {
            activity.onExit();
            activity.finish();
        }

        stack.clear();
    }

    public static void jump(MTBaseActivity context, Class<? extends MTBaseActivity> target,int code) {
        Intent intent = new Intent(context, target);
        intent.putExtra("code", code);
        context.startActivity(intent);
        context.finish();
    }
}
