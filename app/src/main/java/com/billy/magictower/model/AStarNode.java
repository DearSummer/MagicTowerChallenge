package com.billy.magictower.model;

public class AStarNode {

    private int i,j;
    private boolean walkable;
    private int gCost,hCost;
    private AStarNode parent;

    public AStarNode() {
    }

    public AStarNode getParent() {
        return parent;
    }

    public boolean isTheSame(AStarNode other)
    {
        return i == other.getI() && j == other.getJ();
    }


    public void setParent(AStarNode parent) {
        this.parent = parent;
    }

    public AStarNode(int i, int j, boolean walkable) {
        this.i = i;
        this.j = j;
        this.walkable = walkable;
    }

    public void setPosition(int i,int j)
    {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public int getgCost() {
        return gCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public int gethCost() {
        return hCost;
    }

    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    public int getfCost() {
        return gCost + hCost;
    }


}
