package com.billy.magictower.controller;

import com.billy.magictower.GamePlayConstants;
import com.billy.magictower.model.Goods;

public class ShoppingController {

    private HeroController heroController;
    private FloorController floorController;

    private int atkCount,defCount,hpCount;

    public ShoppingController(HeroController heroController,FloorController floorController)
    {
        this.heroController = heroController;
        this.floorController = floorController;

    }


    private boolean showing = false;
    private int needMoney = 20;

    public Goods getGoods() {
        return new Goods(needMoney,
                2 * ((floorController.getCurrentFloor() / 10) + 1) * (atkCount + 1),
                2 * ((floorController.getCurrentFloor() / 10) + 1) * (defCount + 1),
                100 * ((floorController.getCurrentFloor() / 10) + 1) * (hpCount + 1));
    }

    public int buy(int value)
    {
        end();
        if(heroController.getHeroAttribute().getCoin() < needMoney)
        {
            return GamePlayConstants.ShoppingStatusCode.MONEY_NOT_ENOUGH;
        }
        if(value == GamePlayConstants.ShoppingStatusCode.NOT_NEED)
            return GamePlayConstants.ShoppingStatusCode.BUY_SUCCESS;
        heroController.getHeroAttribute().setCoin(heroController.getHeroAttribute().getCoin() - needMoney);
        needMoney *= 2;
        switch (value)
        {
            case GamePlayConstants.ShoppingStatusCode.ATK:
                heroController.getHeroAttribute().setAtk(heroController.getHeroAttribute().getAtk() +
                        (2 * ((floorController.getCurrentFloor() / 10) + 1) * (atkCount + 1)));
                atkCount++;
                break;
            case GamePlayConstants.ShoppingStatusCode.DEF:
                heroController.getHeroAttribute().setDef(heroController.getHeroAttribute().getDef() +
                        (2 * ((floorController.getCurrentFloor() / 10) + 1) * (defCount + 1)));
                defCount++;
                break;
            case GamePlayConstants.ShoppingStatusCode.HP:
                heroController.getHeroAttribute().setHp(heroController.getHeroAttribute().getHp() +
                        (100 * ((floorController.getCurrentFloor() / 10) + 1) * (hpCount + 1)));
                hpCount++;
                break;
        }

        return GamePlayConstants.ShoppingStatusCode.BUY_SUCCESS;
    }



    public void startShopping()
    {
        showing = true;
    }

    public void end()
    {
        showing = false;
    }


    public boolean isShopping(){
        return showing;
    }
}
