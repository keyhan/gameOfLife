package io.github.keyhan.gameoflife;

import io.github.keyhan.gameoflife.graphics.Game;

import javax.swing.*;

public class GameOfLifeApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new Game();
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
