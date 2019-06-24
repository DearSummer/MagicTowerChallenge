package com.billy.magictower.controller;

import android.annotation.SuppressLint;

import com.billy.magictower.GamePlayConstants;
import com.billy.magictower.activity.MTBaseActivity;
import com.billy.magictower.model.AStarNode;
import com.billy.magictower.model.FloorMap;
import com.billy.magictower.model.HeroAttribute;
import com.billy.magictower.model.Monster;
import com.billy.magictower.model.MonsterAttribute;
import com.billy.magictower.util.ApplicationUtil;
import com.billy.magictower.util.JsonUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class HeroController {


    private HeroAttribute heroAttribute;
    private FloorController floorController;

    private Map<Integer, MonsterAttribute> monsterMap;

    private int heroSpriteId = 0,heroStatus = GamePlayConstants.HeroStatusCode.HERO_NORMAL;
    private int particleId = 0;
    private Random random;

    private int heroI,heroJ;

    private List<Integer> equipmentList;



    public HeroController(MTBaseActivity context,FloorController floorController)
    {
        this.floorController = floorController;
        findHeroLocation();
        newGame(context);
        initMonsterAttribute(context);
        random = new Random();
        equipmentList = new ArrayList<>();
    }

    public List<Integer> getEquipmentList()
    {
        return equipmentList;
    }

    public void addEquipment(int id) {
        if (!equipmentList.contains(id))
            equipmentList.add(id);
    }

    @SuppressLint("UseSparseArrays")
    private void initMonsterAttribute(MTBaseActivity context) {
        Monster[] monster = JsonUtil.getMonster(JsonUtil.loadJsonFromAsset(context, "monster.json"));
        monsterMap = new HashMap<>();
        for (Monster monster1 : monster) {
            monsterMap.put(monster1.getId(), monster1.getMonster());
        }
    }

    public int heroStatus()
    {
        return heroStatus;
    }

    private void newGame(MTBaseActivity context) {
        heroAttribute = JsonUtil.getHeroAttribute(JsonUtil.loadJsonFromAsset(context, "hero.json"));
    }


    public MonsterAttribute getMonster(int id) {
        return monsterMap.get(id);
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

    public int goToTarget(int i,int j) {
        boolean canMoveTo = canBeTarget(new AStarNode(i,j,false));
        if(!canMoveTo)
            return GamePlayConstants.MoveStatusCode.CANT_REACH;
        AStarNode start = new AStarNode(heroI, heroJ, true);
        AStarNode end = new AStarNode(i, j, true);
        List<AStarNode> road = findPath(start, end);
        if (road == null) {
            return GamePlayConstants.MoveStatusCode.CANT_REACH;
        }

        for(AStarNode node : road)
        {
            int code = move(node.getI(),node.getJ());
            if(code != GamePlayConstants.MoveStatusCode.MOVE_SUCCESS_CODE)
                return code;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return GamePlayConstants.MoveStatusCode.MOVE_SUCCESS_CODE;
    }

    private int move(int i ,int j) {
        int value = floorController.getValueInMap(i, j);
        switch (value) {
            case GamePlayConstants.GameValueConstants.YELLOW_KEY:
                heroAttribute.addYellowKey();
                break;
            case GamePlayConstants.GameValueConstants.YELLOW_DOOR:
                if (heroAttribute.getYellowKey() > 0) {
                    heroAttribute.setYellowKey(heroAttribute.getYellowKey() - 1);
                } else {
                    return GamePlayConstants.MoveStatusCode.NO_YELLOW_KEY;
                }
                break;
            case GamePlayConstants.GameValueConstants.BLUE_KEY:
                heroAttribute.addBlueKey();
                break;
            case GamePlayConstants.GameValueConstants.BLUE_DOOR:
                if (heroAttribute.getBlueKey() > 0) {
                    heroAttribute.setRedKey(heroAttribute.getBlueKey() - 1);
                } else {
                    return GamePlayConstants.MoveStatusCode.NO_BLUE_KEY;
                }
                break;
            case GamePlayConstants.GameValueConstants.RED_DOOR:
                if (heroAttribute.getRedKey() > 0) {
                    heroAttribute.setRedKey(heroAttribute.getRedKey() - 1);
                } else {
                    return GamePlayConstants.MoveStatusCode.NO_RED_KEY;
                }
                break;
            case GamePlayConstants.GameValueConstants.RED_KEY:
                heroAttribute.addRedKey();
                break;
            case GamePlayConstants.GameValueConstants.BIG_BLOOD:
                heroAttribute.setHp(heroAttribute.getHp() +
                        (2 * GamePlayConstants.GameValueConstants.BASE_BLOOD_ATTRIBUTE * ((floorController.getCurrentFloor()) / 10 + 1)));
                break;
            case GamePlayConstants.GameValueConstants.LITTLE_BLOOD:
                heroAttribute.setHp(heroAttribute.getHp() +
                        (GamePlayConstants.GameValueConstants.BASE_BLOOD_ATTRIBUTE * ((floorController.getCurrentFloor()) / 10 + 1)));
                break;
            case GamePlayConstants.GameValueConstants.ATTACK_BUFF:
                heroAttribute.setAtk(heroAttribute.getAtk() +
                        (GamePlayConstants.GameValueConstants.BUFF_BASE_ATTRIBUTE * ((floorController.getCurrentFloor()) / 10 + 1)));
                break;
            case GamePlayConstants.GameValueConstants.DEFENSE_BUFF:
                heroAttribute.setDef(heroAttribute.getDef() +
                        (GamePlayConstants.GameValueConstants.BUFF_BASE_ATTRIBUTE * ((floorController.getCurrentFloor()) / 10 + 1)));
                break;
            case GamePlayConstants.GameValueConstants.UP_STAIR:
                floorController.upStairs();
                findHeroLocation();
                return GamePlayConstants.MoveStatusCode.MOVE_FLOOR;
            case GamePlayConstants.GameValueConstants.DOWN_STAIR:
                floorController.downStairs();
                findHeroLocation();
                return GamePlayConstants.MoveStatusCode.MOVE_FLOOR;
            case GamePlayConstants.GameValueConstants.STORE_LEFT:
            case GamePlayConstants.GameValueConstants.STORE_MID:
            case GamePlayConstants.GameValueConstants.STORE_RIGHT:
                return GamePlayConstants.MoveStatusCode.SHOPPING;
            case GamePlayConstants.GameValueConstants.NPC_ELDER:
                floorController.setValueInMap(i, j, GamePlayConstants.GameValueConstants.GROUND);
                return GamePlayConstants.MoveStatusCode.TALKING_WITH_ELDER;

        }

        if (value >= GamePlayConstants.GameValueConstants.MONSTER_ID_BEGIN &&
                value < GamePlayConstants.GameValueConstants.MONSTER_ID_END) {
            if (fight(value) != GamePlayConstants.MoveStatusCode.FIGHT_SUCCESS)
                return GamePlayConstants.MoveStatusCode.FIGHT_DIE;
        }

        if (i - heroI > 0)
            heroSpriteId = GamePlayConstants.GameValueConstants.heroRight.get(random.nextInt(3));
        else if (i - heroI < 0)
            heroSpriteId = GamePlayConstants.GameValueConstants.heroLeft.get(random.nextInt(3));
        else if (j - heroJ > 0)
            heroSpriteId = GamePlayConstants.GameValueConstants.heroForward.get(random.nextInt(3));
        else if (j - heroJ < 0)
            heroSpriteId = GamePlayConstants.GameValueConstants.heroBack.get(random.nextInt(3));

        floorController.setValueInMap(heroI, heroJ, GamePlayConstants.GameValueConstants.GROUND);
        floorController.setValueInMap(i, j, GamePlayConstants.GameValueConstants.HERO);
        heroJ = j;
        heroI = i;

        return GamePlayConstants.MoveStatusCode.MOVE_SUCCESS_CODE;
    }

    private int fight(int monster)
    {
        MonsterAttribute enemy = monsterMap.get(monster);
        assert enemy != null;
        while(enemy.getHp() >= 0)
        {
            heroStatus = GamePlayConstants.HeroStatusCode.HERO_FIGHTING;
            particleId = GamePlayConstants.GameValueConstants.fightingParticle.get(random.nextInt(
                    GamePlayConstants.GameValueConstants.fightingParticle.size()
            ));
            int heroDamage = heroAttribute.getAtk() - enemy.getDef();
            if(heroDamage < 0)
                heroDamage = 0;
            enemy.setHp(enemy.getHp() - heroDamage);

            int enemyDamage = enemy.getAtk() - heroAttribute.getDef();
            if(enemyDamage < 0)
                enemyDamage = 0;
            heroAttribute.setHp(heroAttribute.getHp() - enemyDamage);

            if(heroAttribute.getHp() < 0){
                heroStatus = GamePlayConstants.HeroStatusCode.HERO_NORMAL;
                return GamePlayConstants.MoveStatusCode.FIGHT_DIE;
            }


            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        heroStatus = GamePlayConstants.HeroStatusCode.HERO_NORMAL;
        heroAttribute.setCoin(heroAttribute.getCoin() + enemy.getCoin());
        return GamePlayConstants.MoveStatusCode.FIGHT_SUCCESS;
    }


    private List<AStarNode> findPath(AStarNode start, AStarNode end)
    {

        List<AStarNode> openSet = new ArrayList<>();
        List<AStarNode> closeSet = new ArrayList<>();

        openSet.add(start);
        while (!openSet.isEmpty())
        {
            int index = 0;
            AStarNode currentNode = openSet.get(index);
            for(int i = 1;i < openSet.size();i++)
            {
                if(currentNode.getfCost() > openSet.get(i).getfCost() ||
                        (currentNode.getfCost() == openSet.get(i).getfCost() && openSet.get(i).gethCost() < currentNode.gethCost()))
                {
                    currentNode = openSet.get(i);
                    index = i;
                }
            }

            openSet.remove(index);
            closeSet.add(currentNode);

            if(currentNode.isTheSame(end))
            {
                return getRoad(start,currentNode);
            }

            for(AStarNode node : getNeighbour(currentNode))
            {
                if(!node.isWalkable() || containsNode(closeSet,node))
                    continue;

                int newCost = currentNode.getgCost() + getDistance(currentNode,end);
                if(newCost < node.getgCost() || !containsNode(openSet,node))
                {
                    node.setgCost(newCost);
                    node.sethCost(getDistance(node,end));
                    node.setParent(currentNode);
                    if(!containsNode(openSet,node))
                        openSet.add(node);
                }
            }


        }

        return null;
    }

    private boolean containsNode(List<AStarNode> closeSet,AStarNode target)
    {
        for(AStarNode node : closeSet)
        {
            if(node.isTheSame(target))
                return true;
        }

        return false;
    }

    private boolean canBeTarget(AStarNode node) {
        int value = floorController.getValueInMap(node.getI(), node.getJ());
        return value != GamePlayConstants.GameValueConstants.WALL;
    }

    private List<AStarNode> getRoad(AStarNode start, AStarNode end)
    {
        List<AStarNode> road = new ArrayList<>();
        AStarNode cur = end;
        while(!cur.isTheSame(start))
        {
            road.add(cur);
            cur = cur.getParent();
        }
        Collections.reverse(road);
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
        List<AStarNode> neighbourList = new ArrayList<>();
        for(int i = -1;i <= 1;i++) {
            if (i == 0)
                continue;

            int checkX = node.getI() + i;
            if (checkX >= 0 && checkX < GamePlayConstants.MAP_WIDTH) {
                neighbourList.add(new AStarNode(checkX, node.getJ(),
                        canBeTarget(node)));
            }

            int checkY = node.getJ() + i;
            if (checkY >= 0 && checkY < GamePlayConstants.MAP_WIDTH) {
                neighbourList.add(new AStarNode(node.getI(), checkY,
                        canBeTarget(node)));
            }

        }

        return neighbourList;
    }


    public int getSpriteId()
    {
        return heroSpriteId;
    }

    public int getParticleId()
    {
        return particleId;
    }


    public HeroAttribute getHeroAttribute()
    {
        return heroAttribute;
    }


}
