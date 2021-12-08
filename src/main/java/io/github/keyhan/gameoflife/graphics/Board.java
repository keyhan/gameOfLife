package io.github.keyhan.gameoflife.graphics;

import io.github.keyhan.gameoflife.engine.LifeEngine;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class Board implements ActionListener{

    // controls the delay between each tick in ms
    private final int DELAY = 1000;
    //private final int DELAY = 25;
    // controls the size of the board
    public static final int TILE_SIZE = 50;
    public static final int ROWS = 12;
    public static final int COLUMNS = 18;
    // controls how many coins appear on the board
    public static final int NUM_COINS = 5;
    // suppress serialization warning
    private static final long serialVersionUID = 490905409104883233L;

    private int[][] boardValues;
    
    // keep a reference to the timer object that triggers actionPerformed() in
    // case we need access to it in another method
   @Getter
    private Timer timer;


    private DefaultTableModel tableModel = new DefaultTableModel();

   @Getter
   private JTable gameTable;

    public Board() {
        //super(data, titles);
        boardValues = LifeEngine.initBoard(ROWS, COLUMNS);
        String[][] stringBoard = convertToString(boardValues);
        //gameTable = new JTable(stringBoard, createTitle(stringBoard));
        tableModel = new DefaultTableModel(stringBoard, createTitle(stringBoard));
        gameTable = new JTable(tableModel);
        int index = 0;
        while (index < gameTable.getColumnModel().getColumnCount()) {
            gameTable.getColumnModel().getColumn(index).setMaxWidth(1);
            ++index;
        }


//        for(int i = 0; i < stringBoard.length; i++) {
//            for (int j = 0; j < stringBoard[i].length; j++) {
//             tableModel.setValueAt(stringBoard[i][j], i ,j);
//            }
//        }
        timer = new Timer(DELAY, this);
        //timer.start();

    }

//    public Board() {
//        // set the game board size
//        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
//        // set the game board background color
//        setBackground(new Color(232, 232, 232));
//
//        // initialize the game state
//        boardValues = LifeEngine.initBoard(ROWS, COLUMNS);
//        String[][] stringBoard = convertToString(boardValues);
//
//
//        // this timer will call the actionPerformed() method every DELAY ms
//        timer = new Timer(DELAY, this);
//        timer.start();
//    }

    private String[] createTitle(String[][] stringBoard) {
        String[] titles = new String[stringBoard[0].length];
        Arrays.fill(titles, "");
        return titles;
    }

//    @Override
//    public void mouseClicked(MouseEvent e) {
//        int row = this.rowAtPoint(e.getPoint());
//        int col = this.columnAtPoint(e.getPoint());
//    }


    @Override
    public void actionPerformed(ActionEvent e) {
//    public void updateTable() {
        //refresh Table
        boardValues = LifeEngine.calculateNextGeneration(boardValues);
        String[][] stringBoard = convertToString(boardValues);
//        for(int i = 0; i < stringBoard.length; i++) {
//            tableModel.insertRow(i, stringBoard[i]);
//        }
        for(int i = 0; i < stringBoard.length; i++) {
            for (int j = 0; j < stringBoard[i].length; j++) {
                tableModel.setValueAt(stringBoard[i][j], i ,j);
            }
        }
        //gameTable = new JTable(stringBoard, createTitle(stringBoard));

        //gameTable.repaint();
    }

    private String[][] convertToString(int [][] intValues) {
        String[][] stringValues = new String[intValues.length][];
        for(int i = 0; i < intValues.length; i++) {
            stringValues[i] = new String[intValues[i].length];
        }
        for(int i = 0; i < intValues.length; i++) {
            for(int j = 0; j < intValues[i].length; j++) {
                if(intValues[i][j] == 0) {
                    stringValues[i][j] = " ";
                } else {
                    stringValues[i][j] = "X";
                }
            }
        }
        return stringValues;
    }
}
