package io.github.keyhan.gameoflife.graphics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private static final String OS = System.getProperty("os.name").toLowerCase();
    public static final boolean IS_MAC = OS.contains("mac");
    private static final String ORGANISM = IS_MAC ? "x" : new String(Character.toChars(0x1F37E));
    private static final String OLD_ORGANISM = IS_MAC ? "X" : new String(Character.toChars(127877));
    private static final String DEAD_ORGANISM = IS_MAC ? "." : new String(Character.toChars(0x1F32B));
    private static final String EMPTY_CELL = " ";

    @Test
    public void testConvertToStringTable() {

        int[][] initBord = new int[4][4];
        initBord[0] = new int[] {0, 0 ,0 ,0};
        initBord[1] = new int[] {0, 1 ,1 ,0};
        initBord[2] = new int[] {0, 1 ,1 ,0};
        initBord[3] = new int[] {0, 0 ,0 ,0};

        int[][] oldBord = new int[4][4];
        oldBord[0] = new int[] {0, 0 ,0 ,0};
        oldBord[1] = new int[] {1, 0 ,1 ,0};
        oldBord[2] = new int[] {0, 1 ,0 ,0};
        oldBord[3] = new int[] {0, 0 ,0 ,0};

        String[][] expectedBoard = new String[4][4];
        expectedBoard[0] = new String[] {EMPTY_CELL,EMPTY_CELL,EMPTY_CELL,EMPTY_CELL};
        expectedBoard[1] = new String[] {DEAD_ORGANISM, ORGANISM ,OLD_ORGANISM ,EMPTY_CELL};
        expectedBoard[2] = new String[] {EMPTY_CELL, OLD_ORGANISM ,ORGANISM ,EMPTY_CELL};
        expectedBoard[3] = new String[] {EMPTY_CELL,EMPTY_CELL,EMPTY_CELL,EMPTY_CELL};


        Board board = new Board();
        String[][] convertedTable = board.convertToString(initBord, oldBord);
        Assertions.assertArrayEquals(convertedTable, expectedBoard);
    }
    
}
