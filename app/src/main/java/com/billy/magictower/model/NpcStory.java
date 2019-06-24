package com.billy.magictower.model;

public class NpcStory {
    private int id;
    private int floor;
    private String str;
    private int gif;

    public int getGif() {
        return gif;
    }

    public NpcStory(int id, int floor, String str, int gif) {
        this.id = id;
        this.floor = floor;
        this.str = str;
        this.gif = gif;
    }

    public void setGif(int gif) {
        this.gif = gif;
    }

    public NpcStory() {
    }

    public NpcStory(int id, int floor, String str) {
        this.id = id;
        this.floor = floor;
        this.str = str;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
