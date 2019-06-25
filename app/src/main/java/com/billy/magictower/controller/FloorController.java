package com.billy.magictower.controller;

import com.billy.magictower.GameManager;
import com.billy.magictower.GamePlayConstants;
import com.billy.magictower.activity.MTBaseActivity;
import com.billy.magictower.model.FloorMap;
import com.billy.magictower.util.IOUtil;
import com.billy.magictower.util.JsonUtil;

public class FloorController implements IController {

    private int level = 0;
    private FloorMap[] localMap;

    public FloorController(MTBaseActivity context) {
        localMap = JsonUtil.getMap(JsonUtil.loadJsonFromAsset(context, "floor.json"));
    }

    public void upStairs()
    {
        level++;
        GameManager.getInstance().save();
    }

    public void downStairs()
    {
        level--;
        GameManager.getInstance().save();
    }

    public int getCurrentFloor()
    {
        return level;
    }


    public int getValueInMap(int i,int j)
    {
        return localMap[level].getMap()[j * GamePlayConstants.MAP_WIDTH + i];
    }

    public void setValueInMap(int i ,int j,int value)
    {
        localMap[level].setValue(i,j,value);
    }

    private void saveGame()
    {
        IOUtil.save(localMap,"user_floor.json");
    }

    private void loadGame()
    {
        localMap = IOUtil.load("user_floor.json", FloorMap[].class);
    }

    public void setLevel(int level)
    {
        this.level = level;
    }
    public FloorMap getMap()
    {
        return localMap[level];
    }


    @Override
    public void save() {
        saveGame();
    }

    @Override
    public void load() {
        loadGame();
    }
}
