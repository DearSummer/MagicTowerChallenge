package com.billy.magictower.controller;

import com.billy.magictower.GamePlayConstants;
import com.billy.magictower.model.HeroAttribute;
import com.billy.magictower.model.MonsterAttribute;
import com.billy.magictower.model.MonsterManual;

import java.util.ArrayList;
import java.util.List;

public class EquipmentController extends TalkingBaseController {

    private HeroController heroController;
    private FloorController floorController;

    private List<MonsterManual> monsterManualList;

    public EquipmentController(HeroController heroController,FloorController floorController)
    {
        this.heroController = heroController;
        this.floorController = floorController;

        monsterManualList = new ArrayList<>();
    }

    @Override
    public void start() {
        super.start();
        loadMonsterManual();
    }

    private void loadMonsterManual() {
        monsterManualList.clear();
        for (int i = 0; i < floorController.getMap().getMap().length; i++) {
            if (floorController.getMap().getMap()[i] >= GamePlayConstants.GameValueConstants.MONSTER_ID_BEGIN &&
                    floorController.getMap().getMap()[i] < GamePlayConstants.GameValueConstants.MONSTER_ID_END) {
                monsterManualList.add(
                        new MonsterManual(
                                GamePlayConstants.GameValueConstants.valueMap.get(floorController.getMap().getMap()[i]),
                                heroController.getMonster(floorController.getMap().getMap()[i]),
                                calculeLoseBlood(heroController.getMonster(floorController.getMap().getMap()[i]))));
            }
        }
    }

    private int calculeLoseBlood(MonsterAttribute monsterAttribute)
    {
        HeroAttribute heroAttribute = heroController.getHeroAttribute();
        int monsterHp = monsterAttribute.getHp(), monsterAtk = monsterAttribute.getAtk(),
                monsterDef = monsterAttribute.getDef();
        int heroAtk = heroAttribute.getAtk(),
                heroDef = heroAttribute.getDef();

        int heroDamage = heroAtk - monsterDef;
        if(heroDamage <= 0)
            return GamePlayConstants.BattleCode.CANT_ATK;


        int monsterDamage = monsterAtk - heroDef;
        if(monsterDamage < 0)
            monsterDamage = 0;

        int heroHp = 0;
        while(monsterHp >= 0)
        {
            monsterHp -= heroDamage;
            heroHp += monsterDamage;
        }

        if(heroHp >= heroAttribute.getHp())
            return GamePlayConstants.BattleCode.CANT_ATK;

        return heroHp;
    }


    public List<MonsterManual> getMonsterManualList()
    {
        return monsterManualList;
    }




}
