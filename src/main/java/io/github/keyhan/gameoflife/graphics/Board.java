package io.github.keyhan.gameoflife.graphics;

import io.github.keyhan.gameoflife.engine.LifeEngine;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Board implements ActionListener{

    private static final String ORGANISM = new String(Character.toChars(10084));
    private static final String EMPTY_CELL = " ";
    // controls the size of the board
    public static final int ROWS = 70;
    public static final int COLUMNS = 70;

    private int[][] boardValues;
    
    // keep a reference to the timer object that triggers actionPerformed() in
    // case we need access to it in another method
   @Getter
    private final Timer timer;


    private final DefaultTableModel tableModel;

   @Getter
   private final JTable gameTable;

    public Board() {
        boardValues = LifeEngine.seedSystem(COLUMNS, ROWS);
        String[][] stringBoard = convertToString(boardValues);
        tableModel = new DefaultTableModel(stringBoard, createTitle(stringBoard));
        gameTable = new JTable(tableModel);
        int index = 0;
        while (index < gameTable.getColumnModel().getColumnCount()) {
            gameTable.getColumnModel().getColumn(index).setMaxWidth(1);
            ++index;
        }


        // controls the refresh rate
        int DELAY = 100;
        timer = new Timer(DELAY, this);

    }

    private String[] createTitle(String[][] stringBoard) {
        String[] titles = new String[stringBoard[0].length];
        Arrays.fill(titles, "");
        return titles;
    }


    @Override
    public void actionPerformed(ActionEvent e) { //Updates the board after each interval
        boardValues = LifeEngine.getNextGeneration(boardValues);
        String[][] stringBoard = convertToString(boardValues);
        for(int i = 0; i < stringBoard.length; i++) {
            for (int j = 0; j < stringBoard[i].length; j++) {
                tableModel.setValueAt(stringBoard[i][j], i ,j);
            }
        }
    }

    String[][] convertToString(int [][] intValues) {
        String[][] stringValues = new String[intValues.length][];
        for(int i = 0; i < intValues.length; i++) {
            stringValues[i] = new String[intValues[i].length];
        }
        for(int i = 0; i < intValues.length; i++) {
            for(int j = 0; j < intValues[i].length; j++) {
                if(intValues[i][j] == 0) {
                    stringValues[i][j] = EMPTY_CELL;
                } else {
                    stringValues[i][j] = ORGANISM;
                }
            }
        }
        return stringValues;
    }
}
