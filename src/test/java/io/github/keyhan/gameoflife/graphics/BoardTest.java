package io.github.keyhan.gameoflife.graphics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    public void testConvertToStringTable() { 

        String ORGANISM = new String(Character.toChars(0x1F37E));
        final String OLD_ORGANISM = new String(Character.toChars(127877));


        int[][] initBord = new int[4][4];
        initBord[0] = new int[] {0, 0 ,0 ,0};
        initBord[1] = new int[] {0, 1 ,1 ,0};
        initBord[2] = new int[] {0, 1 ,1 ,0};
        initBord[3] = new int[] {0, 0 ,0 ,0};

        int[][] oldBord = new int[4][4];
        oldBord[0] = new int[] {0, 0 ,0 ,0};
        oldBord[1] = new int[] {0, 0 ,1 ,0};
        oldBord[2] = new int[] {0, 1 ,0 ,0};
        oldBord[3] = new int[] {0, 0 ,0 ,0};

        String[][] expectedBoard = new String[4][4];
        expectedBoard[0] = new String[] {" ", " " ," " ," "};
        expectedBoard[1] = new String[] {" ", ORGANISM ,OLD_ORGANISM ," "};
        expectedBoard[2] = new String[] {" ", OLD_ORGANISM ,ORGANISM ," "};
        expectedBoard[3] = new String[] {" ", " " ," " ," "};


        Board board = new Board();
        String[][] convertedTable = board.convertToString(initBord, oldBord);
        Assertions.assertArrayEquals(convertedTable, expectedBoard);
    }
    
}
