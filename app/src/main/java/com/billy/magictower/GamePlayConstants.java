package com.billy.magictower;

import java.util.HashMap;

public class GamePlayConstants {
    public final static int GAME_LOOP_FRAME_RATE = 30;
    public final static long GAME_LOOP_TIME = (long) (((double)1 / GAME_LOOP_FRAME_RATE) * 1000);

    public final static int MAP_WIDTH = 11;


    public static class GameStatusCode
    {
        public final static int LOAD_GAME = 0;
        public final static int NEW_GAME = 1;
    }

    public static class GameResConstants {
        public final static int MAP_META_WIDTH = 32;
        public final static int MAP_META_HEIGHT = 32;

        public final static int MAP_SPRITE_WIDTH_COUNT = 11;
        public final static int MAP_SPRITE_HEIGHT_COUNT = 12;

        public final static int HERO_SPRITE_WIDTH_COUNT = 14;

    }

    public static class GameValueConstants
    {
        public final static int GROUND = 0;
        public final static int WALL = 1;
        public final static int YELLOW_DOOR = 2;
        public final static int YELLOW_KEY = 3;
        public final static int LITTLE_BLOOD = 4;
        public final static int BIG_BLOOD = 5;
        public final static int ATTACK_BUFF = 6;
        public final static int DEFENSE_BUFF = 7;
        public final static int BLUE_DOOR = 8;
        public final static int RED_DOOR = 9;
        public final static int UP_STAIR = 10;
        public final static int DOWN_STAIR =11;
        public final static int SKELETON = 12;
        public final static int SKELETON_KING = 13;
        public final static int WIZARD = 14;
        public final static int LITTLE_BAT = 15;
        public final static int GREEN_SLIME = 16;
        public final static int RED_SLIME = 17;
        public final static int HERO = 99;

        public final static HashMap<Integer,Integer> valueMap = new HashMap<Integer, Integer>()
        {
            {
                put(GROUND,0);
                put(WALL,1);
                put(YELLOW_DOOR,12);
                put(YELLOW_KEY,18);
                put(BIG_BLOOD,23);
                put(ATTACK_BUFF,24);
                put(BLUE_DOOR,13);
                put(DEFENSE_BUFF,25);
                put(RED_DOOR,14);
                put(UP_STAIR,10);
                put(DOWN_STAIR,11);
                put(LITTLE_BLOOD,22);
                put(SKELETON_KING,54);
                put(SKELETON,52);
                put(WIZARD,55);
                put(LITTLE_BAT,51);
                put(GREEN_SLIME,49);
                put(RED_SLIME,50);
            }
        };
    }

    public static class MoveStatusCode
    {
        public final static int MOVE_SUCCESS_CODE = 0;
        public final static int NO_YELLOW_KEY = 1;
        public final static int NO_RED_KEY = 2;
        public final static int NO_BLUE_KEY = 3;
        public final static int CANT_REACH = 4;
    }
}
