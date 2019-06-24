package com.billy.magictower.model;

import android.support.annotation.NonNull;

public class MonsterManual {
    private int spriteId;
    private MonsterAttribute monsterAttribute;
    private int damage;

    public MonsterManual() {
    }

    public MonsterManual(int spriteId, MonsterAttribute monsterAttribute) {
        this.spriteId = spriteId;
        this.monsterAttribute = monsterAttribute;
    }

    public MonsterManual(int spriteId, MonsterAttribute monsterAttribute, int damage) {
        this.spriteId = spriteId;
        this.monsterAttribute = monsterAttribute;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpriteId() {
        return spriteId;
    }

    public void setSpriteId(int spriteId) {
        this.spriteId = spriteId;
    }

    public MonsterAttribute getMonsterAttribute() {
        return monsterAttribute;
    }

    public void setMonsterAttribute(MonsterAttribute monsterAttribute) {
        this.monsterAttribute = monsterAttribute;
    }

    @NonNull
    @Override
    public String toString() {
        return  "血量 :" + monsterAttribute.getHp() +
                " 攻击 :" +
                monsterAttribute.getAtk() +
                " 防御 :" +
                monsterAttribute.getDef() +
                " 金币 :" +
                monsterAttribute.getCoin() +
                " 你会受到" +
                damage +
                "点伤害";
    }
}
