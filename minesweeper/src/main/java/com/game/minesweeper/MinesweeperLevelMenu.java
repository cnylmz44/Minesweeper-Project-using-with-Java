package com.game.minesweeper;

import javax.swing.*;

import static com.game.minesweeper.GameConstants.*;

public class MinesweeperLevelMenu {

    String[] optionsToChoose = {
            GameLevelEnum.EASY.getLevel(),
            GameLevelEnum.MEDIUM.getLevel(),
            GameLevelEnum.HARD.getLevel()
    };

    public MinesweeperLevelMenu(){
        String selectedLevel = (String) JOptionPane.showInputDialog(
                null,
                MENU_MESSAGE,
                MENU_TITLE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsToChoose,
                optionsToChoose[0]);

        GameSettings gameSettings;

        if (GameLevelEnum.EASY.getLevel().equals(selectedLevel)) {
           gameSettings = new GameSettings(EASY_LEVEL_NUM_ROWS, EASY_LEVEL_NUM_COLS, EASY_LEVEL_MINE_COUNT);
        } else if (GameLevelEnum.MEDIUM.getLevel().equals(selectedLevel)) {
            gameSettings = new GameSettings(MEDIUM_LEVEL_NUM_ROWS, MEDIUM_LEVEL_NUM_COLS, MEDIUM_LEVEL_MINE_COUNT);
        } else if (GameLevelEnum.HARD.getLevel().equals(selectedLevel)) {
            gameSettings = new GameSettings(HARD_LEVEL_NUM_ROWS, HARD_LEVEL_NUM_COLS, HARD_LEVEL_MINE_COUNT);
        } else {
            return;
        }

        Minesweeper minesweeper = new Minesweeper(gameSettings);
    }
}
