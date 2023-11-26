package com.game.minesweeper;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class Minesweeper {
    private static final String GAME_NAME = "Minesweeper";
    private static final int TILE_SIZE = 35;
    private static int NUM_ROWS;
    private static int NUM_COLS;
    private static int MINE_COUNT;
    private static int BOARD_WIDTH;
    private static int BOARD_HEIGHT;
    private static final String FLAG_TEXT = "🚩";
    private static final String MINE_TEXT = "💣";
    private static final String BLANK_TEXT = "";
    private static final String GAME_OVER_TEXT = "Game Over!";
    private static final String MINES_CLEARED_TEXT = "Mines Cleared!";
    private static final String TILE_FONT_NAME = "Arial Unicode MS";
    private static final String TEXT_LABEL_FONT_NAME = "Arial";
    private static final int TILE_FONT_SIZE = 20;
    private static final int TEXT_LABEL_FONT_SIZE = 10;

    private JFrame frame;
    private JLabel textLabel;
    private JPanel textPanel;
    private JPanel boardPanel;

    private MineTile[][] board;
    private List<MineTile> mineList;
    private int tilesClicked = 0;
    private boolean gameOver = false;

    public Minesweeper(GameSettings gameSettings){
        initialize(gameSettings);
        setDefaultFrameParameters();
        setDefaultLabelParameters();
        addTextPanelToFrame();
        addBoardPanelToFrame();
        setMineTiles();

        frame.setVisible(true);
        setMines();
    }

    private void initialize(GameSettings gameSettings) {
        NUM_ROWS = gameSettings.getNumRows();
        NUM_COLS = gameSettings.getNumCols();
        MINE_COUNT = gameSettings.getMineCount();

        BOARD_WIDTH = NUM_COLS * TILE_SIZE;
        BOARD_HEIGHT = NUM_ROWS * TILE_SIZE;

        frame = new JFrame(GAME_NAME);
        textLabel = new JLabel();
        textPanel = new JPanel();
        boardPanel = new JPanel();

        board = new MineTile[NUM_ROWS][NUM_COLS];
        mineList = new ArrayList<>();
    }

    private void setDefaultFrameParameters(){
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }

    private void setDefaultLabelParameters(){
        textLabel.setFont(new Font(TEXT_LABEL_FONT_NAME, Font.BOLD, TEXT_LABEL_FONT_SIZE));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Mine Count : " + MINE_COUNT);
        textLabel.setOpaque(true);
    }

    private void addTextPanelToFrame(){
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);
    }

    private void addBoardPanelToFrame(){
        boardPanel.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        frame.add(boardPanel);
    }

    private void setMineTiles() {
        for (int row = 0; row < NUM_ROWS; row++){
            for (int column = 0; column < NUM_COLS; column++){
                MineTile newTile = new MineTile(row, column);
                board[row][column] = newTile;

                newTile.setFocusable(false);
                newTile.setMargin(new Insets(0, 0, 0, 0));
                newTile.setFont(new Font(TILE_FONT_NAME, Font.PLAIN, TILE_FONT_SIZE));

                newTile.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (gameOver){
                            return;
                        }

                        MineTile tile = (MineTile) e.getSource();

                        // left click
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (tile.getText().equals(BLANK_TEXT)) {
                                if (mineList.contains(tile)) {
                                    revealMines();
                                }
                                else {
                                    checkMine(tile.getRow(), tile.getColumn());
                                }
                            }
                        }

                        // right click
                        else if (e.getButton() == MouseEvent.BUTTON3) {
                            if (tile.getText().equals(BLANK_TEXT) && tile.isEnabled()){
                                tile.setText(FLAG_TEXT);
                            } else if (tile.getText().equals(FLAG_TEXT)) {
                                tile.setText(BLANK_TEXT);
                            }
                        }
                    }
                });

                boardPanel.add(newTile);
            }
        }
    }

    private void setMines(){
        Random random = new Random();
        mineList = new ArrayList<>();
        int mineLeft = MINE_COUNT;

        while (mineLeft > 0) {
            int row = random.nextInt(NUM_ROWS);
            int column = random.nextInt(NUM_COLS);

            MineTile tile = board[row][column];

            if (!mineList.contains(tile)) {
                mineList.add(tile);
                mineLeft -= 1;
            }
        }
    }

    private void revealMines(){
        for (MineTile tile : mineList) {
            tile.setText(MINE_TEXT);
        }

        gameOver = true;
        textLabel.setText(GAME_OVER_TEXT);
    }

    private void checkMine(int row, int column) {
        if (row < 0 || row >= NUM_ROWS || column < 0 || column >= NUM_COLS) {
            return;
        }

        MineTile tile = board[row][column];

        if (!tile.isEnabled()){
            return;
        }

        tile.setEnabled(false);
        tilesClicked += 1;

        int minesFound = 0;

        //top 3
        minesFound += countMine(row-1, column-1);  //top left
        minesFound += countMine(row-1, column);    //top
        minesFound += countMine(row-1, column+1);  //top right

        //left and right
        minesFound += countMine(row, column-1);    //left
        minesFound += countMine(row, column+1);    //right

        //bottom 3
        minesFound += countMine(row+1, column-1);  //bottom left
        minesFound += countMine(row+1, column);    //bottom
        minesFound += countMine(row+1, column+1);  //bottom right

        if (minesFound > 0) {
            tile.setText(Integer.toString(minesFound));
        } else {
            tile.setText(BLANK_TEXT);

            //top 3
            checkMine(row-1, column-1);    //top left
            checkMine(row-1, column);      //top
            checkMine(row-1, column+1);    //top right

            //left and right
            checkMine(row, column-1);      //left
            checkMine(row, column+1);      //right

            //bottom 3
            checkMine(row+1, column-1);    //bottom left
            checkMine(row+1, column);      //bottom
            checkMine(row+1, column+1);    //bottom right
        }

        if (tilesClicked == NUM_ROWS * NUM_COLS - mineList.size()) {
            gameOver = true;
            textLabel.setText(MINES_CLEARED_TEXT);
        }
    }

    private int countMine(int row, int column) {
        if (row < 0 || row >= NUM_ROWS || column < 0 || column >= NUM_COLS) {
            return 0;
        }

        if (mineList.contains(board[row][column])) {
            return 1;
        }

        return 0;
    }
}