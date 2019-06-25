package com.billy.magictower.controller;

import com.billy.magictower.GamePlayConstants;
import com.billy.magictower.activity.MTBaseActivity;
import com.billy.magictower.model.Goods;
import com.billy.magictower.model.ShopCount;
import com.billy.magictower.util.IOUtil;
import com.billy.magictower.util.JsonUtil;

public class ShoppingController  extends  TalkingBaseController  {

    private HeroController heroController;
    private FloorController floorController;

    private ShopCount shopCount;

    public ShoppingController(MTBaseActivity context,HeroController heroController, FloorController floorController)
    {
        this.heroController = heroController;
        this.floorController = floorController;

        shopCount = JsonUtil.parseJson(JsonUtil.loadJsonFromAsset(context,"shop.json"),ShopCount.class);

    }


    private int needMoney = 20;

    public Goods getGoods() {
        return new Goods(needMoney,
                2 * ((floorController.getCurrentFloor() / 10) + 1) * (shopCount.getAtkCount() + 1),
                2 * ((floorController.getCurrentFloor() / 10) + 1) * (shopCount.getDefCount() + 1),
                100 * ((floorController.getCurrentFloor() / 10) + 1) * (shopCount.getHpCount() + 1));
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
                        (2 * ((floorController.getCurrentFloor() / 10) + 1) * (shopCount.getAtkCount() + 1)));
                shopCount.setAtkCount(shopCount.getAtkCount() + 1);
                break;
            case GamePlayConstants.ShoppingStatusCode.DEF:
                heroController.getHeroAttribute().setDef(heroController.getHeroAttribute().getDef() +
                        (2 * ((floorController.getCurrentFloor() / 10) + 1) * (shopCount.getDefCount() + 1)));
                shopCount.setDefCount(shopCount.getDefCount() + 1);
                break;
            case GamePlayConstants.ShoppingStatusCode.HP:
                heroController.getHeroAttribute().setHp(heroController.getHeroAttribute().getHp() +
                        (100 * ((floorController.getCurrentFloor() / 10) + 1) * (shopCount.getHpCount() + 1)));
                shopCount.setHpCount(shopCount.getHpCount() + 1);
                break;
        }

        return GamePlayConstants.ShoppingStatusCode.BUY_SUCCESS;
    }

    @Override
    public void save() {
        super.save();
        IOUtil.save(shopCount,"user_stopCount.json");
    }

    @Override
    public void load() {
        super.load();
        shopCount = IOUtil.load("user_stopCount.json",ShopCount.class);
    }
}
