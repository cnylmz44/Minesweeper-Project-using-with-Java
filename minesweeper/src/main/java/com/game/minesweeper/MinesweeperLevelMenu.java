package com.game.minesweeper;

import javax.swing.*;

public class MinesweeperLevelMenu {
    String[] optionsToChoose = {
            GameLevelEnum.EASY.getLevel(),
            GameLevelEnum.MEDIUM.getLevel(),
            GameLevelEnum.HARD.getLevel()
    };

    public MinesweeperLevelMenu(){
        String selectedLevel = (String) JOptionPane.showInputDialog(
                null,
                "What level do you want to play at?",
                "Choose Level",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsToChoose,
                optionsToChoose[0]);

        GameSettings gameSettings;

        if (GameLevelEnum.EASY.getLevel().equals(selectedLevel)) {
           gameSettings = new GameSettings(8, 10, 10);
        } else if (GameLevelEnum.MEDIUM.getLevel().equals(selectedLevel)) {
            gameSettings = new GameSettings(14, 18, 40);
        } else if (GameLevelEnum.HARD.getLevel().equals(selectedLevel)) {
            gameSettings = new GameSettings(20, 24, 99);
        } else {
            return;
        }

        Minesweeper minesweeper = new Minesweeper(gameSettings);
    }
}
