package io.github.keyhan.gameoflife.engine;

import lombok.experimental.UtilityClass;

import java.util.Random;


@UtilityClass
public class LifeEngine {

    public static int [][] seedSystem(int rows, int columns) {
        Random random = new Random();
        int[][] startBoard = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                startBoard[i][j] = random.nextInt(100) > 10 ? 0 : 1;
            }
        }
        return startBoard;
    }

    public static int [][] tick(int [][] board) {
        int[][] nextBoard = new int [board.length][];
        for(int i = 0; i < board.length; i++) {
            nextBoard[i] = new int[board[i].length];
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] > 1 || board[i][j] < 0) {
                    throw new IllegalStateException(String.format("Cell %s,%s has illegal value: %s", i, j, board[i][j]));
                }
                int sumOfNeighbours = getSumOfNeighbours(board, i, j);
                nextBoard[i][j] = getNextValue(board[i][j], sumOfNeighbours);
            }
        }
        return nextBoard;
    }

    private static int getNextValue(int currentValue, int sumOfNeighbours) {
        if(currentValue == 1) { //living cell
            if (sumOfNeighbours < 2 || sumOfNeighbours > 3) {
                return 0;
            } else {
                return 1;
            }
        } else { //non-living cell
            if (sumOfNeighbours == 3) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private static int getSumOfNeighbours(int[][] board, int i, int j) {
        int sumOfNeighbours = 0;
        if ( i == 0) {
            sumOfNeighbours += board[board.length-1][j];
            sumOfNeighbours += getSumFromNeighbouringColumns(board,board.length - 1,j);
        }
        if (i == board.length -1) { //add neighbours from next row
            sumOfNeighbours += board[0][j];
            sumOfNeighbours += getSumFromNeighbouringColumns(board,0,j);
        }
        if (i+1 < board.length) { //add neighbours from next row
            sumOfNeighbours += board[i + 1][j];
            sumOfNeighbours += getSumFromNeighbouringColumns(board, i + 1, j);
        }
        if (i-1 >= 0) { //add neighbours from former row
            sumOfNeighbours += board[i - 1][j];
            sumOfNeighbours += getSumFromNeighbouringColumns(board, i - 1, j);
        }
        sumOfNeighbours += getSumFromNeighbouringColumns(board, i, j);
        return sumOfNeighbours;
    }

    private static int getSumFromNeighbouringColumns(int[][] board, int i, int j) {
        int sumOfNeighbours = 0;
        if (j == 0) {
            sumOfNeighbours += board[i][board[i].length - 1];
        }
        if (j == board[i].length - 1) {
            sumOfNeighbours += board[i][0];
        }
        if (j -1 >= 0) { //add neighbour before
            sumOfNeighbours += board[i][j - 1];
        }
        if (j +1 < board[i].length) { //add neighbour after
            sumOfNeighbours += board[i][j + 1];
        }
        return sumOfNeighbours;
    }
}
