package com.billy.magictower.model;

public class Goods {
    private int money,atk,def,hp;

    public Goods(int money, int atk, int def, int hp) {
        this.money = money;
        this.atk = atk;
        this.def = def;
        this.hp = hp;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Goods(int atk, int def, int hp) {
        this.atk = atk;
        this.def = def;
        this.hp = hp;
    }

    public Goods() {
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
