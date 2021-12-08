package io.github.keyhan.gameoflife.engine;

import java.util.Random;

public class LifeEngine {

    public static int [][] initBoard(int rows, int columns) {
        Random random = new Random();
        int[][] startBoard = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                startBoard[i][j] = random.nextInt(2);
            }
        }
        return startBoard;
    }

    public static int [][] calculateNextGeneration(int [][] board) {
        int[][] nextBoard = new int [board.length][];
        for(int i = 0; i < board.length; i++) {
            nextBoard[i] = new int[board[i].length];
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] > 1 || board[i][j] < 0) {
                    throw new IllegalStateException(String.format("Cell %s,%s has illegal value: %s", i, j, board[i][j]));
                }
                int sumOfNeighbours = getSumOfNeighboursForPosition(board, i, j);
                if(board[i][j] == 1) { //living cell
                    if (sumOfNeighbours < 2 || sumOfNeighbours > 3) {
                        nextBoard [i][j] = 0;
                    } else {
                        nextBoard [i][j] = 1;
                    }
                } else if(board[i][j] == 0) { //non-living cell
                    if (sumOfNeighbours == 3) {
                        nextBoard [i][j] = 1;
                    } else {
                        nextBoard [i][j] = 0;
                    }
                }
            }
        }
        return nextBoard;
    }

    private static int getSumOfNeighboursForPosition(int[][] board, int i, int j) {
        int sumOfNeighbours = 0;
        if (i-1 >= 0) { //add neighbours from former row
            sumOfNeighbours += board[i - 1][j];
            sumOfNeighbours += getSumFromNeighbouringColumns(board, i - 1, j);
        }
        if (i+1 < board.length) { //add neighbours from next row
            sumOfNeighbours += board[i + 1][j];
            sumOfNeighbours += getSumFromNeighbouringColumns(board, i + 1, j);
        }
        sumOfNeighbours += getSumFromNeighbouringColumns(board, i, j);
        return sumOfNeighbours;
    }

    private static int getSumFromNeighbouringColumns(int[][] board, int i, int j) {
        int sumOfNeighbours = 0;
        if (j -1 >= 0) { //add neighbour before
            sumOfNeighbours += board[i][j - 1];
        }
        if (j +1 < board[i].length) { //add neighbour after
            sumOfNeighbours += board[i][j + 1];
        }
        return sumOfNeighbours;
    }
}
