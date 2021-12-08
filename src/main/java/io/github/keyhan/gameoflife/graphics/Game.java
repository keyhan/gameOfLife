package io.github.keyhan.gameoflife.graphics;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame{
    private enum GameState {
        STARTED,
        STOPPED
    }

    private GameState gameState;
    JButton startButton = new JButton("Start");
    JButton stopButton = new JButton("Stop");
    JButton clearButton = new JButton("Clear");

    public Game() {
        super("Game Of Life!");
        BorderLayout borderLayout = new BorderLayout();
        JPanel buttonPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        buttonPanel.setLayout(flowLayout);
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        this.setLayout(borderLayout);
        this.gameState = GameState.STOPPED;
        Board board = new Board();
        startButton.addActionListener(e -> {
            if (this.gameState == GameState.STOPPED) {
                this.gameState = GameState.STARTED;
                //board.updateTable();
                //updateScreen();

                board.getTimer().start();
                // Begin a thread which each second updates the board
                // Change the status so the board cannot be
            }
        });

        stopButton.addActionListener(e -> {
            if (this.gameState == GameState.STARTED) {
                this.gameState = GameState.STOPPED;
                // stops the timer.
                board.getTimer().stop();
            }
        });

        clearButton.addActionListener(e -> {
            if (this.gameState == GameState.STOPPED) {
                // clears the board.
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(board.getGameTable(), BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);


    }

    public void updateScreen() {
        this.repaint();
    }
}
