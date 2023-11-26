package com.game.minesweeper;

public class GameConstants {

    // Common Game Settings
    public static final String GAME_NAME = "Minesweeper";
    public static final String GAME_OVER_TEXT = "Game Over!";
    public static final String MINES_CLEARED_TEXT = "Mines Cleared!";
    public static final String TILE_FONT_NAME = "Arial Unicode MS";
    public static final String TEXT_LABEL_FONT_NAME = "Arial";
    public static final String FLAG_TEXT = "🚩";
    public static final String MINE_TEXT = "💣";
    public static final String BLANK_TEXT = "";
    public static final int TILE_FONT_SIZE = 20;
    public static final int TILE_SIZE = 35;
    public static final int TEXT_LABEL_FONT_SIZE = 10;

    // Easy Level Game Settings
    public static final int EASY_LEVEL_NUM_ROWS = 8;
    public static final int EASY_LEVEL_NUM_COLS = 10;
    public static final int EASY_LEVEL_MINE_COUNT = 10;

    // Medium Level Game Settings
    public static final int MEDIUM_LEVEL_NUM_ROWS = 14;
    public static final int MEDIUM_LEVEL_NUM_COLS = 18;
    public static final int MEDIUM_LEVEL_MINE_COUNT = 40;

    // Hard Level Game Settings
    public static final int HARD_LEVEL_NUM_ROWS = 20;
    public static final int HARD_LEVEL_NUM_COLS = 24;
    public static final int HARD_LEVEL_MINE_COUNT = 99;

    // Menu Settings
    public static final String MENU_TITLE = "Choose Level";
    public static final String MENU_MESSAGE = "What level do you want to play at?";

    private GameConstants(){

    }

}
