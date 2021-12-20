package io.github.keyhan.gameoflife.engine;

import io.github.keyhan.gameoflife.domain.Organism;
import lombok.experimental.UtilityClass;

import java.util.*;


@UtilityClass
public class LifeEngine {


    public Set<Organism> seedSystem(int rows, int columns) {
        Random random = new Random();
        Set<Organism> organisms = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (random.nextInt(100) <= 10) {
                    organisms.add(new Organism(i, j));
                }
            }
        }
        return organisms;
    }

    public static Set<Organism> tick(Set<Organism> organisms, int maxCols, int maxRows) {
        Set<Organism> newOrganisms = new HashSet<>();
        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < maxCols; j++) {
                int sumOfNeighbours = getSumOfNeighbours(organisms, i, j, maxCols, maxRows);
                Organism organism = new Organism(i, j);
                boolean isAlive = organisms.contains(organism);
                boolean shouldBeAlive = shouldBeAlive(isAlive, sumOfNeighbours);
                if (shouldBeAlive) {
                    newOrganisms.add(organism);
                }
            }
        }
        return newOrganisms;
    }

    private static boolean shouldBeAlive(boolean isAlive, int sumOfNeighbours) {
        if (isAlive) { //living cell
            return sumOfNeighbours >= 2 && sumOfNeighbours <= 3;
        } else { //non-living cell
            return sumOfNeighbours == 3;
        }
    }

    private static int getSumOfNeighbours(Set<Organism> organisms, int i, int j, int maxCols, int maxRows) {
        int sumOfNeighbours = 0;
//        if (i == 0) {
//            sumOfNeighbours += organisms.contains(new Organism(i, maxCols - 1)) ? 1 : 0;
//            sumOfNeighbours += getSumFromNeighbouringColumns(organisms, maxRows - 1, j, maxCols);
//        }
//        if (i == maxRows - 1) { //add neighbours from next row
//            sumOfNeighbours += organisms.contains(new Organism(0, j)) ? 1 : 0;
//            sumOfNeighbours += getSumFromNeighbouringColumns(organisms, 0, j, maxCols);
//        }
        if (i + 1 < maxRows) { //add neighbours from next row
            sumOfNeighbours += organisms.contains(new Organism(i + 1, j)) ? 1 : 0;
            sumOfNeighbours += getSumFromNeighbouringColumns(organisms, i + 1, j, maxCols);
        }
        if (i - 1 >= 0) { //add neighbours from former row
            sumOfNeighbours += organisms.contains(new Organism(i - 1, j)) ? 1 :0;
            sumOfNeighbours += getSumFromNeighbouringColumns(organisms, i - 1, j, maxCols);
        }
        sumOfNeighbours += getSumFromNeighbouringColumns(organisms, i, j, maxCols);
        return sumOfNeighbours;
    }

    private static int getSumFromNeighbouringColumns(Set<Organism> organisms, int i, int j, int maxCols) {
        int sumOfNeighbours = 0;
//        if (j == 0) {
//            sumOfNeighbours += organisms.contains(new Organism(i , maxCols - 1)) ? 1 :0;
//        }
//        if (j == maxCols - 1) {
//            sumOfNeighbours += organisms.contains(new Organism(i , 0)) ? 1 :0;
//        }
        if (j - 1 >= 0) { //add neighbour before
            sumOfNeighbours += organisms.contains(new Organism(i , j - 1)) ? 1 :0;
        }
        if (j + 1 < maxCols) { //add neighbour after
            sumOfNeighbours += organisms.contains(new Organism(i , j + 1)) ? 1 :0;
        }
        return sumOfNeighbours;
    }
}
