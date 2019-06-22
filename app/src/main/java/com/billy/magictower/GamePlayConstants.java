package com.billy.magictower;

public class GamePlayConstants {
    public static int GAME_LOOP_FRAME_RATE = 30;
    public static long GAME_LOOP_TIME = (long) (((double)1 / GAME_LOOP_FRAME_RATE) * 1000);

    public static class GameStatusCode
    {
        public final static int LOAD_GAME = 0;
        public final static int NEW_GAME = 1;
    }

    public static class GameResContants{
        public final static int MAP_META_WIDTH = 38;
        public final static int MAP_META_HEIGHT = 38;
    }
}
