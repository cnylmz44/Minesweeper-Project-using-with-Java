package com.game.minesweeper;

public enum GameLevelEnum {
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard");

    private String level;

    GameLevelEnum(String level){
        this.level = level;
    }

    public String getLevel(){
        return level;
    }
}
