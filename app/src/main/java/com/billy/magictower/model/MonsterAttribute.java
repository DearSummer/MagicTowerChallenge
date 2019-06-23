package com.billy.magictower.model;

public class MonsterAttribute {
    private int hp;
    private int atk;
    private int def;
    private int coin;

    public MonsterAttribute() {
    }

    public MonsterAttribute(int hp, int atk, int def, int coin) {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.coin = coin;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
