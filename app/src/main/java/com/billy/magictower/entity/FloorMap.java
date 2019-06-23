package com.billy.magictower.entity;

public class FloorMap {
    private int level;
    private int[] map;

    public FloorMap() {
    }

    public FloorMap(int level, int[] map) {
        this.level = level;
        this.map = map;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int[] getMap() {
        return map;
    }

    public void setMap(int[] map) {
        this.map = map;
    }
}
