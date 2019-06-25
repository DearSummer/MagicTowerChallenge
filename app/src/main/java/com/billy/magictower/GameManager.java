package com.billy.magictower;

import com.billy.magictower.controller.IController;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private static volatile  GameManager mInstance = null;
    public static GameManager getInstance()
    {
        if(mInstance == null) {
            synchronized (GameManager.class) {
                if (mInstance == null)
                    mInstance = new GameManager();
            }
        }
        return mInstance;
    }

    private List<IController> controllerList = new ArrayList<>();

    public void register(IController controller)
    {
        controllerList.add(controller);
    }

    public void unregister(IController controller)
    {
        controllerList.remove(controller);
    }

    public void save()
    {
        for (IController controller : controllerList)
        {
            controller.save();
        }
    }

    public void load()
    {
        for (IController controller : controllerList)
        {
            controller.load();
        }
    }




}
