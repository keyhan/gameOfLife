package io.github.keyhan.gameoflife.engine;

import io.github.keyhan.gameoflife.domain.Organism;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class LifeEngineTest {

    //@Test Keeping this for manual testing
    public void testLife() {
        Set<Organism> initBoard = Set.of(
                new Organism(0, 0),
                new Organism(0, 3),
                new Organism(0, 4),
                new Organism(1, 1),
                new Organism(1, 2),
                new Organism(2, 0),
                new Organism(2, 3),
                new Organism(3, 3),
                new Organism(4, 0),
                new Organism(4, 1),
                new Organism(4, 2),
                new Organism(4, 4)
        );
        Set<Organism> nextGeneration = Set.copyOf(initBoard);
        for (int z = 0; z < 10; z++) {
            StringBuilder str = new StringBuilder();

            nextGeneration = LifeEngine.tick(nextGeneration,10,10);
//            str.append("\n");
//            for (int i = 0; i < 5; i++) {
//                for (int j = 0; j < 5; j++) {
//                    if ()
//                    str.append(nextGeneration[i][j]).append(" ");
//                }
//                str.append("\n");
//            }
            System.out.println("nextGeneration = " + nextGeneration);
        }

    }

    @Test
    public void testBlock() {
        Set<Organism> initBoard = Set.of(
                new Organism(1, 1),
                new Organism(1, 2),
                new Organism(2, 1),
                new Organism(2, 2)
                );
        Set<Organism> nextGeneration = LifeEngine.tick(initBoard,5,5);
        Assertions.assertEquals(initBoard, nextGeneration);
    }

    @Test
    public void testBeehive() {
        Set<Organism> initBoard = Set.of(
                new Organism(1, 2),
                new Organism(1, 3),
                new Organism(2, 1),
                new Organism(2, 4),
                new Organism(3, 2),
                new Organism(3, 3)
        );
        Set<Organism> nextGeneration = LifeEngine.tick(initBoard,6,6);
        Assertions.assertEquals(initBoard, nextGeneration);
    }

    @Test
    public void testLoaf() {
        Set<Organism> initBoard = Set.of(
                new Organism(1, 2),
                new Organism(1, 3),
                new Organism(2, 1),
                new Organism(2, 4),
                new Organism(3, 2),
                new Organism(3, 4),
                new Organism(4, 3)
        );
        Set<Organism> nextGeneration = LifeEngine.tick(initBoard,7,7);
        Assertions.assertEquals(initBoard, nextGeneration);
    }

    @Test
    public void testBoat() {
        Set<Organism> initBoard = Set.of(
                new Organism(1, 1),
                new Organism(1, 2),
                new Organism(2, 1),
                new Organism(2, 3),
                new Organism(3, 2)
        );
        Set<Organism> nextGeneration = LifeEngine.tick(initBoard,6,6);
        Assertions.assertEquals(initBoard, nextGeneration);
    }

//    @Test
//    public void testBlinker() {
//        int[][] firstBord = new int[5][5];
//        firstBord[0] = new int[] {0, 0 ,0 ,0 ,0};
//        firstBord[1] = new int[] {0, 0 ,1 ,0 ,0};
//        firstBord[2] = new int[] {0, 0 ,1 ,0 ,0};
//        firstBord[3] = new int[] {0, 0 ,1 ,0 ,0};
//        firstBord[4] = new int[] {0, 0 ,0 ,0 ,0};
//
//        int[][] secondBord = new int[5][5];
//        secondBord[0] = new int[] {0, 0 ,0 ,0 ,0};
//        secondBord[1] = new int[] {0, 0 ,0 ,0 ,0};
//        secondBord[2] = new int[] {0, 1 ,1 ,1 ,0};
//        secondBord[3] = new int[] {0, 0 ,0 ,0 ,0};
//        secondBord[4] = new int[] {0, 0 ,0 ,0 ,0};
//
//        int[][] nextGeneration = LifeEngine.tick(firstBord);
//        Assertions.assertArrayEquals(secondBord, nextGeneration);
//
//        nextGeneration = LifeEngine.tick(secondBord);
//        Assertions.assertArrayEquals(firstBord, nextGeneration);
//
//    }
//
//    @Test
//    public void testToad() {
//        int[][] firstBord = new int[6][6];
//        firstBord[0] = new int[] {0, 0 ,0 ,0 ,0, 0};
//        firstBord[1] = new int[] {0, 0 ,0 ,0 ,0, 0};
//        firstBord[2] = new int[] {0, 0 ,1 ,1 ,1, 0};
//        firstBord[3] = new int[] {0, 1 ,1 ,1 ,0, 0};
//        firstBord[4] = new int[] {0, 0 ,0 ,0 ,0, 0};
//        firstBord[5] = new int[] {0, 0 ,0 ,0 ,0, 0};
//
//        int[][] secondBord = new int[6][6];
//        secondBord[0] = new int[] {0, 0 ,0 ,0 ,0, 0};
//        secondBord[1] = new int[] {0, 0 ,0 ,1 ,0, 0};
//        secondBord[2] = new int[] {0, 1 ,0 ,0 ,1, 0};
//        secondBord[3] = new int[] {0, 1 ,0 ,0 ,1, 0};
//        secondBord[4] = new int[] {0, 0 ,1 ,0 ,0, 0};
//        secondBord[5] = new int[] {0, 0 ,0 ,0 ,0, 0};
//
//        int[][] nextGeneration = LifeEngine.tick(firstBord);
//        Assertions.assertArrayEquals(secondBord, nextGeneration);
//
//        nextGeneration = LifeEngine.tick(secondBord);
//        Assertions.assertArrayEquals(firstBord, nextGeneration);
//    }
//
//    @Test
//    public void testBeacon() {
//        int[][] firstBord = new int[6][6];
//        firstBord[0] = new int[] {0, 0 ,0 ,0 ,0, 0};
//        firstBord[1] = new int[] {0, 1 ,1 ,0 ,0, 0};
//        firstBord[2] = new int[] {0, 1 ,1 ,0 ,0, 0};
//        firstBord[3] = new int[] {0, 0 ,0 ,1 ,1, 0};
//        firstBord[4] = new int[] {0, 0 ,0 ,1 ,1, 0};
//        firstBord[5] = new int[] {0, 0 ,0 ,0 ,0, 0};
//
//        int[][] secondBord = new int[6][6];
//        secondBord[0] = new int[] {0, 0 ,0 ,0 ,0, 0};
//        secondBord[1] = new int[] {0, 1 ,1 ,0 ,0, 0};
//        secondBord[2] = new int[] {0, 1 ,0 ,0 ,0, 0};
//        secondBord[3] = new int[] {0, 0 ,0 ,0 ,1, 0};
//        secondBord[4] = new int[] {0, 0 ,0 ,1 ,1, 0};
//        secondBord[5] = new int[] {0, 0 ,0 ,0 ,0, 0};
//
//        int[][] nextGeneration = LifeEngine.tick(firstBord);
//        Assertions.assertArrayEquals(secondBord, nextGeneration);
//
//        nextGeneration = LifeEngine.tick(secondBord);
//        Assertions.assertArrayEquals(firstBord, nextGeneration);
//    }
//






}