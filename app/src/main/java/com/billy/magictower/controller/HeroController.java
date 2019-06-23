package com.billy.magictower.controller;

import com.billy.magictower.GamePlayConstants;
import com.billy.magictower.entity.FloorMap;
import com.billy.magictower.entity.HeroAttribute;
import com.billy.magictower.util.ApplicationUtil;

public class HeroController {

    private HeroAttribute heroAttribute;
    private FloorController floorController;

    private int heroI,heroJ;

    public HeroController(FloorController floorController)
    {
        this.floorController = floorController;
        findHeroLocation();
    }

    private void findHeroLocation()
    {
        FloorMap map = floorController.getMap();
        for(int i = 0;i < map.getMap().length;i++)
        {
            if(map.getMap()[i] == GamePlayConstants.GameValueConstants.HERO)
            {
                heroJ = i / GamePlayConstants.MAP_WIDTH;
                heroI = i - (heroJ * GamePlayConstants.MAP_WIDTH);
            }
        }

        ApplicationUtil.log("heroJ",heroJ);
        ApplicationUtil.log("heroI",heroI);
    }

    public void goToTarget(int i,int j)
    {
        floorController.setValueInMap(heroI,heroJ, GamePlayConstants.GameValueConstants.GROUND);
        floorController.setValueInMap(i,j, GamePlayConstants.GameValueConstants.HERO);
        heroJ = j;
        heroI = i;

    }


    public int getSpriteId()
    {
        return 0;
    }



}
