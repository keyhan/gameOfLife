package io.github.keyhan.gameoflife.graphics;

import io.github.keyhan.gameoflife.domain.Organism;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class BoardTest {

    private static final String OS = System.getProperty("os.name").toLowerCase();
    public static final boolean IS_MAC = OS.contains("mac");
    private static final String ORGANISM = IS_MAC ? "x" : new String(Character.toChars(0x1F37E));
    private static final String OLD_ORGANISM = IS_MAC ? "X" : new String(Character.toChars(127877));
    private static final String DEAD_ORGANISM = IS_MAC ? "." : new String(Character.toChars(0x1F32B));
    private static final String EMPTY_CELL = " ";

    @Test
    public void testConvertToStringTable() {

        Set<Organism> initBoard = Set.of(
                new Organism(1, 1),
                new Organism(1, 2),
                new Organism(2, 1),
                new Organism(2, 2)
        );

        Set<Organism> oldBoard = Set.of(
                new Organism(1, 0),
                new Organism(1, 2),
                new Organism(2, 1)
        );

        String[][] expectedBoard = new String[4][4];
        expectedBoard[0] = new String[] {EMPTY_CELL,EMPTY_CELL,EMPTY_CELL,EMPTY_CELL};
        expectedBoard[1] = new String[] {DEAD_ORGANISM, ORGANISM ,OLD_ORGANISM ,EMPTY_CELL};
        expectedBoard[2] = new String[] {EMPTY_CELL, OLD_ORGANISM ,ORGANISM ,EMPTY_CELL};
        expectedBoard[3] = new String[] {EMPTY_CELL,EMPTY_CELL,EMPTY_CELL,EMPTY_CELL};


        Board board = new Board();
        String[][] convertedTable = board.convertToString(initBoard, oldBoard,4,4);
        Assertions.assertArrayEquals(convertedTable, expectedBoard);
    }
    
}
