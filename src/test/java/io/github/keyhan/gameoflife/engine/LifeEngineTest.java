package io.github.keyhan.gameoflife.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LifeEngineTest {

    //@Test
    public void testLife() throws Exception {
        int[][] initBord = new int[5][5];
        initBord[0] = new int[] {1, 0 ,0 ,1 ,1};
        initBord[1] = new int[] {0, 1 ,1 ,0 ,0};
        initBord[2] = new int[] {1, 0 ,0 ,1 ,0};
        initBord[3] = new int[] {0, 0 ,0 ,1 ,0};
        initBord[4] = new int[] {1, 1 ,1 ,1 ,1};
        int[][] nextGeneration = initBord;
        for (int z = 0; z < 10; z++) {
            StringBuilder str = new StringBuilder();

            nextGeneration = LifeEngine.getNextGeneration(nextGeneration);
            str.append("\n");
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    str.append(nextGeneration[i][j]).append(" ");
                }
                str.append("\n");
            }
            System.out.println("nextGeneration = " + str.toString());
        }

    }

    @Test
    public void testFaultyBoard() throws Exception {
        int[][] initBord = new int[4][4];
        initBord[0] = new int[] {0, 0 ,0 ,0};
        initBord[1] = new int[] {0, 2 ,1 ,0};
        initBord[2] = new int[] {0, 1 ,1 ,0};
        initBord[3] = new int[] {0, 0 ,0 ,0};
        Assertions.assertThrows(IllegalStateException.class, () ->
            LifeEngine.getNextGeneration(initBord));
    }

    @Test
    public void testBlock() throws Exception {
        int[][] initBord = new int[4][4];
        initBord[0] = new int[] {0, 0 ,0 ,0};
        initBord[1] = new int[] {0, 1 ,1 ,0};
        initBord[2] = new int[] {0, 1 ,1 ,0};
        initBord[3] = new int[] {0, 0 ,0 ,0};
        int[][] nextGeneration = LifeEngine.getNextGeneration(initBord);
        Assertions.assertArrayEquals(initBord, nextGeneration);
    }

    @Test
    public void testBeehive() throws Exception {
        int[][] initBord = new int[5][6];
        initBord[0] = new int[] {0, 0 ,0 ,0 ,0, 0};
        initBord[1] = new int[] {0, 0 ,1 ,1 ,0, 0};
        initBord[2] = new int[] {0, 1 ,0 ,0 ,1, 0};
        initBord[3] = new int[] {0, 0 ,1 ,1 ,0, 0};
        initBord[4] = new int[] {0, 0 ,0 ,0 ,0, 0};
        int[][] nextGeneration = LifeEngine.getNextGeneration(initBord);
        Assertions.assertArrayEquals(initBord, nextGeneration);
    }

    @Test
    public void testLoaf() throws Exception {
        int[][] initBord = new int[6][6];
        initBord[0] = new int[] {0, 0 ,0 ,0 ,0, 0};
        initBord[1] = new int[] {0, 0 ,1 ,1 ,0, 0};
        initBord[2] = new int[] {0, 1 ,0 ,0 ,1, 0};
        initBord[3] = new int[] {0, 0 ,1 ,0 ,1, 0};
        initBord[4] = new int[] {0, 0 ,0 ,1 ,0, 0};
        initBord[5] = new int[] {0, 0 ,0 ,0 ,0, 0};
        int[][] nextGeneration = LifeEngine.getNextGeneration(initBord);
        Assertions.assertArrayEquals(initBord, nextGeneration);
    }

    @Test
    public void testBoat() throws Exception {
        int[][] initBord = new int[5][5];
        initBord[0] = new int[] {0, 0 ,0 ,0 ,0};
        initBord[1] = new int[] {0, 1 ,1 ,0 ,0};
        initBord[2] = new int[] {0, 1 ,0 ,1 ,0};
        initBord[3] = new int[] {0, 0 ,1 ,0 ,0};
        initBord[4] = new int[] {0, 0 ,0 ,0 ,0};
        int[][] nextGeneration = LifeEngine.getNextGeneration(initBord);
        Assertions.assertArrayEquals(initBord, nextGeneration);
    }

    @Test
    public void testBlinker() throws Exception {
        int[][] firstBord = new int[5][5];
        firstBord[0] = new int[] {0, 0 ,0 ,0 ,0};
        firstBord[1] = new int[] {0, 0 ,1 ,0 ,0};
        firstBord[2] = new int[] {0, 0 ,1 ,0 ,0};
        firstBord[3] = new int[] {0, 0 ,1 ,0 ,0};
        firstBord[4] = new int[] {0, 0 ,0 ,0 ,0};

        int[][] secondBord = new int[5][5];
        secondBord[0] = new int[] {0, 0 ,0 ,0 ,0};
        secondBord[1] = new int[] {0, 0 ,0 ,0 ,0};
        secondBord[2] = new int[] {0, 1 ,1 ,1 ,0};
        secondBord[3] = new int[] {0, 0 ,0 ,0 ,0};
        secondBord[4] = new int[] {0, 0 ,0 ,0 ,0};

        int[][] nextGeneration = LifeEngine.getNextGeneration(firstBord);
        Assertions.assertArrayEquals(secondBord, nextGeneration);

        nextGeneration = LifeEngine.getNextGeneration(secondBord);
        Assertions.assertArrayEquals(firstBord, nextGeneration);

    }

    @Test
    public void testToad() throws Exception {
        int[][] firstBord = new int[6][6];
        firstBord[0] = new int[] {0, 0 ,0 ,0 ,0, 0};
        firstBord[1] = new int[] {0, 0 ,0 ,0 ,0, 0};
        firstBord[2] = new int[] {0, 0 ,1 ,1 ,1, 0};
        firstBord[3] = new int[] {0, 1 ,1 ,1 ,0, 0};
        firstBord[4] = new int[] {0, 0 ,0 ,0 ,0, 0};
        firstBord[5] = new int[] {0, 0 ,0 ,0 ,0, 0};

        int[][] secondBord = new int[6][6];
        secondBord[0] = new int[] {0, 0 ,0 ,0 ,0, 0};
        secondBord[1] = new int[] {0, 0 ,0 ,1 ,0, 0};
        secondBord[2] = new int[] {0, 1 ,0 ,0 ,1, 0};
        secondBord[3] = new int[] {0, 1 ,0 ,0 ,1, 0};
        secondBord[4] = new int[] {0, 0 ,1 ,0 ,0, 0};
        secondBord[5] = new int[] {0, 0 ,0 ,0 ,0, 0};

        int[][] nextGeneration = LifeEngine.getNextGeneration(firstBord);
        Assertions.assertArrayEquals(secondBord, nextGeneration);

        nextGeneration = LifeEngine.getNextGeneration(secondBord);
        Assertions.assertArrayEquals(firstBord, nextGeneration);
    }

    @Test
    public void testBeacon() throws Exception {
        int[][] firstBord = new int[6][6];
        firstBord[0] = new int[] {0, 0 ,0 ,0 ,0, 0};
        firstBord[1] = new int[] {0, 1 ,1 ,0 ,0, 0};
        firstBord[2] = new int[] {0, 1 ,1 ,0 ,0, 0};
        firstBord[3] = new int[] {0, 0 ,0 ,1 ,1, 0};
        firstBord[4] = new int[] {0, 0 ,0 ,1 ,1, 0};
        firstBord[5] = new int[] {0, 0 ,0 ,0 ,0, 0};

        int[][] secondBord = new int[6][6];
        secondBord[0] = new int[] {0, 0 ,0 ,0 ,0, 0};
        secondBord[1] = new int[] {0, 1 ,1 ,0 ,0, 0};
        secondBord[2] = new int[] {0, 1 ,0 ,0 ,0, 0};
        secondBord[3] = new int[] {0, 0 ,0 ,0 ,1, 0};
        secondBord[4] = new int[] {0, 0 ,0 ,1 ,1, 0};
        secondBord[5] = new int[] {0, 0 ,0 ,0 ,0, 0};

        int[][] nextGeneration = LifeEngine.getNextGeneration(firstBord);
        Assertions.assertArrayEquals(secondBord, nextGeneration);

        nextGeneration = LifeEngine.getNextGeneration(secondBord);
        Assertions.assertArrayEquals(firstBord, nextGeneration);
    }







}