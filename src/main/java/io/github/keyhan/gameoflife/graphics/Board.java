package io.github.keyhan.gameoflife.graphics;

import io.github.keyhan.gameoflife.domain.Organism;
import io.github.keyhan.gameoflife.engine.LifeEngine;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Board implements ActionListener{

    private static final String OS = System.getProperty("os.name").toLowerCase();
    public static final boolean IS_MAC = OS.contains("mac");
    private static final String ORGANISM = IS_MAC ? "x" : new String(Character.toChars(0x1F37E));
    private static final String OLD_ORGANISM = IS_MAC ? "X" : new String(Character.toChars(127877));
    private static final String DEAD_ORGANISM = IS_MAC ? "." : new String(Character.toChars(0x1F32B));
    private static final String EMPTY_CELL = " ";
    // controls the size of the board
    public static final int ROWS = 50;
    public static final int COLUMNS = 50;

    private Set<Organism> boardValues;
    
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
        String[][] stringBoard = convertToString(boardValues, null, ROWS, COLUMNS);
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
        Set<Organism> oldValues = Set.copyOf(boardValues);
        boardValues = LifeEngine.tick(boardValues, ROWS, COLUMNS);
        String[][] stringBoard = convertToString(boardValues, oldValues, ROWS, COLUMNS);
        for(int i = 0; i < stringBoard.length; i++) {
            for (int j = 0; j < stringBoard[i].length; j++) {
                tableModel.setValueAt(stringBoard[i][j], i ,j);
            }
        }
    }

    String[][] convertToString(Set<Organism> values,Set<Organism> oldValues, int maxCols, int maxRows) {
        String[][] stringValues = new String[maxRows][maxCols];
        for(int i = 0; i < maxRows; i++) {
            for(int j = 0; j < maxCols; j++) {
                Organism organism = new Organism(i, j);
                if(values.contains(organism)) {
                    if (oldValues != null && oldValues.contains(organism)) {
                        stringValues[i][j] = OLD_ORGANISM;
                    } else {
                        stringValues[i][j] = ORGANISM;
                    }
                } else {
                    if (oldValues != null && oldValues.contains(organism)) {
                        stringValues[i][j] = DEAD_ORGANISM;
                    } else {
                        stringValues[i][j] = EMPTY_CELL;
                    }
                }
            }
        }
        return stringValues;
    }
}
