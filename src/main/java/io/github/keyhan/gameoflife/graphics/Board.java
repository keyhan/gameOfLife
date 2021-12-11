package io.github.keyhan.gameoflife.graphics;

import io.github.keyhan.gameoflife.engine.LifeEngine;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Board implements ActionListener{

    private static final String ORGANISM = new String(Character.toChars(0x1F37E));
    private static final String OLD_ORGANISM = new String(Character.toChars(127877));
    private static final String DEAD_ORGANISM = new String(Character.toChars(0x1F32B));
    private static final String EMPTY_CELL = " ";
    // controls the size of the board
    public static final int ROWS = 50;
    public static final int COLUMNS = 50;

    private int[][] boardValues;
    
    // keep a reference to the timer object that triggers actionPerformed() but also accessed
    // to start and pause the game
    private Timer timer;


    private DefaultTableModel tableModel;

   @Getter
   private JTable gameTable;

    public Board() {
        initBoard();
    }

    public void initBoard() { 
        boardValues = LifeEngine.seedSystem(COLUMNS, ROWS);
        String[][] stringBoard = convertToString(boardValues, null);
        tableModel = new DefaultTableModel(stringBoard, createEmptyTitleForBoard(stringBoard));
        gameTable = new JTable(tableModel);
        int index = 0;
        while (index < gameTable.getColumnModel().getColumnCount()) {
            gameTable.getColumnModel().getColumn(index).setMaxWidth(1);
            ++index;
        }
        gameTable.repaint();
    }

    public void playGame(int delay) {
        timer = new Timer(delay, this);
        timer.start();
    }

    public void pauseGame() {
        timer.stop();
    }


    private String[] createEmptyTitleForBoard(String[][] stringBoard) {
        String[] titles = new String[stringBoard[0].length];
        Arrays.fill(titles, "");
        return titles;
    }


    @Override
    public void actionPerformed(ActionEvent e) { //Updates the board after each interval
        int[][] oldValues = boardValues.clone();
        boardValues = LifeEngine.tick(boardValues);
        String[][] stringBoard = convertToString(boardValues, oldValues);
        for(int i = 0; i < stringBoard.length; i++) {
            for (int j = 0; j < stringBoard[i].length; j++) {
                tableModel.setValueAt(stringBoard[i][j], i ,j);
            }
        }
    }

    String[][] convertToString(int [][] intValues,int [][] oldValues) {
        String[][] stringValues = new String[intValues.length][];
        for(int i = 0; i < intValues.length; i++) {
            stringValues[i] = new String[intValues[i].length];
        }
        for(int i = 0; i < intValues.length; i++) {
            for(int j = 0; j < intValues[i].length; j++) {
                if(intValues[i][j] == 0) {
                    if (oldValues != null && oldValues[i][j] == 1) {
                        stringValues[i][j] = DEAD_ORGANISM;
                    } else {
                        stringValues[i][j] = EMPTY_CELL;
                    }
                } else {
                    if (oldValues!=null && oldValues[i][j] == 0) {
                        stringValues[i][j] = ORGANISM;
                    } else if(oldValues != null) {
                        stringValues[i][j] = OLD_ORGANISM;
                    } else {
                        stringValues[i][j] = ORGANISM;
                    }
                }
            }
        }
        return stringValues;
    }
}
