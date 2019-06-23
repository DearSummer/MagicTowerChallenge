package com.billy.magictower.model;

public class HeroAttribute {
    private int hp;
    private int atk;
    private int def;
    private int yellowKey;
    private int blueKey;
    private int redKey;
    private int coin;

    public HeroAttribute()
    {
        hp = 1000;
        atk = 100;
        def = 100;
        yellowKey = 0;
        blueKey = 0;
        redKey = 0;
        coin = 0;
    }

    public HeroAttribute(int hp, int atk, int def, int yellowKey, int blueKey, int redKey, int coin) {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.yellowKey = yellowKey;
        this.blueKey = blueKey;
        this.redKey = redKey;
        this.coin = coin;
    }

    public void addYelloKey()
    {
        yellowKey++;
    }

    public void addRedKey()
    {
        redKey++;
    }

    public void blueKey()
    {
        blueKey++;
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

    public int getYellowKey() {
        return yellowKey;
    }

    public void setYellowKey(int yellowKey) {
        this.yellowKey = yellowKey;
    }

    public int getBlueKey() {
        return blueKey;
    }

    public void setBlueKey(int blueKey) {
        this.blueKey = blueKey;
    }

    public int getRedKey() {
        return redKey;
    }

    public void setRedKey(int redKey) {
        this.redKey = redKey;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
