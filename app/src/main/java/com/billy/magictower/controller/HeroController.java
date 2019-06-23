package com.billy.magictower.controller;

import com.billy.magictower.GamePlayConstants;
import com.billy.magictower.model.AStarNode;
import com.billy.magictower.model.FloorMap;
import com.billy.magictower.model.HeroAttribute;
import com.billy.magictower.util.ApplicationUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HeroController {

    private HeroAttribute heroAttribute;
    private FloorController floorController;

    private int heroI,heroJ;
    private  List<AStarNode> neighbourList;

    public HeroController(FloorController floorController)
    {
        this.floorController = floorController;
        neighbourList = new ArrayList<>();
        findHeroLocation();
    }

    private void findHeroLocation()
    {
        FloorMap map = floorController.getMap();
        for(int i = 0;i < map.getMap().length;i++)
        {
            if(map.getMap()[i] == GamePlayConstants.GameValueConstants.HERO)
            {
                heroJ = i / GamePlayConstants.MAP_WIDTH;
                heroI = i - (heroJ * GamePlayConstants.MAP_WIDTH);
            }
        }

        ApplicationUtil.log("heroJ",heroJ);
        ApplicationUtil.log("heroI",heroI);
    }

    public boolean goToTarget(int i,int j) {
        AStarNode start = new AStarNode(heroI, heroJ, true);
        AStarNode end = new AStarNode(i, j,
                floorController.getValueInMap(i, j) == GamePlayConstants.GameValueConstants.GROUND);
        List<AStarNode> road = findPath(start, end);
        if (road == null) {
            return false;
        }

        floorController.setValueInMap(heroI, heroJ, GamePlayConstants.GameValueConstants.GROUND);
        floorController.setValueInMap(i, j, GamePlayConstants.GameValueConstants.HERO);
        heroJ = j;
        heroI = i;

        return true;
    }

    private List<AStarNode> findPath(AStarNode start, AStarNode end)
    {
        List<AStarNode> road = null;

        List<AStarNode> openSet = new ArrayList<>();
        HashSet<AStarNode> closeSet = new HashSet<>();

        openSet.add(start);
        while (!openSet.isEmpty())
        {
            AStarNode currentNode = openSet.get(0);
            for(int i = 1;i < openSet.size();i++)
            {
                if(currentNode.getfCost() > openSet.get(i).getfCost() ||
                        (currentNode.getfCost() == openSet.get(i).getfCost() && openSet.get(i).gethCost() < currentNode.gethCost()))
                {
                    currentNode = openSet.get(i);
                }
            }

            openSet.remove(currentNode);
            closeSet.add(currentNode);

            if(currentNode.isTheSame(end))
            {
                return getRoad(start,end);
            }

            for(AStarNode node : getNeighbour(currentNode))
            {
                if(!node.isWalkable() || closeSet.contains(node))
                    continue;

                int newCost = currentNode.getgCost() + getDistance(currentNode,end);
                if(newCost < node.getgCost() || !openSet.contains(node))
                {
                    node.setgCost(newCost);
                    node.sethCost(getDistance(node,end));
                    node.setParent(currentNode);
                    if(!openSet.contains(node))
                        openSet.add(node);
                }
            }


        }

        return null;
    }

    private List<AStarNode> getRoad(AStarNode start, AStarNode end)
    {
        List<AStarNode> road = new ArrayList<>();
        AStarNode cur = end;
        while(!cur.isTheSame(end))
        {
            road.add(cur);
            cur = cur.getParent();
        }

        return road;
    }

    private int getDistance(AStarNode start,AStarNode end)
    {
        int dstX = Math.abs(start.getI() - end.getI());
        int dstY = Math.abs(start.getJ() - end.getJ());

        if(dstX > dstY)
            return 14 * dstY + 10 * (dstX - dstY);

        return 14 * dstX + 10 * (dstY - dstX);
    }

    private List<AStarNode> getNeighbour(AStarNode node)
    {
        neighbourList.clear();
        for(int i = -1;i <= 1;i++)
        {
            for(int j = -1;j <= 1;j++)
            {
                if(i ==0 && j == 0)
                    continue;

                int checkX = node.getI() + i;
                if(checkX < 0 || checkX >= GamePlayConstants.MAP_WIDTH)
                    continue;

                int checkY = node.getJ() + j;
                if(checkY < 0 || checkY >= GamePlayConstants.MAP_WIDTH)
                    continue;

                AStarNode n = new AStarNode(checkX,checkY,
                        floorController.getValueInMap(checkX,checkY) == GamePlayConstants.GameValueConstants.GROUND);

                neighbourList.add(n);
            }
        }

        return neighbourList;
    }


    public int getSpriteId()
    {
        return 0;
    }



}
