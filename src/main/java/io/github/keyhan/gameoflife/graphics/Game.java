package io.github.keyhan.gameoflife.graphics;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame{
    private enum GameState {
        STARTED,
        STOPPED
    }

    private GameState gameState;
    private final static String PLAY_SYMBOL = new String(Character.toChars(9654));
    private final static String PAUSE_SYMBOL = new String(Character.toChars(9208));

    JButton startButton = new JButton(PLAY_SYMBOL);
    JButton stopButton = new JButton(PAUSE_SYMBOL);

    public Game() {
        super("Game Of Life!");
        BorderLayout borderLayout = new BorderLayout();
        JPanel buttonPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        startButton.setFont(new Font("Segoe", Font.PLAIN, 40));
        stopButton.setFont(new Font("Segoe", Font.PLAIN, 40));
        buttonPanel.setLayout(flowLayout);
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        this.setLayout(borderLayout);
        this.gameState = GameState.STOPPED;
        Board board = new Board();
        startButton.addActionListener(e -> {
            if (this.gameState == GameState.STOPPED) {
                this.gameState = GameState.STARTED;
                // Begin a timer which each second updates the board
                board.getTimer().start();
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
            }
        });

        stopButton.addActionListener(e -> {
            if (this.gameState == GameState.STARTED) {
                this.gameState = GameState.STOPPED;
                // stops the timer.
                board.getTimer().stop();
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(board.getGameTable(), BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);

    }
}
