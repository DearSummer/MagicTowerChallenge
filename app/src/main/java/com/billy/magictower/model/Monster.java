package com.billy.magictower.model;

public class Monster {
    private int id;
    private MonsterAttribute monster;

    public Monster() {
    }

    public Monster(int id, MonsterAttribute monster) {
        this.id = id;
        this.monster = monster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MonsterAttribute getMonster() {
        return monster;
    }

    public void setMonster(MonsterAttribute monster) {
        this.monster = monster;
    }
}
