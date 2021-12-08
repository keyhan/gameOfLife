package io.github.keyhan.gameoflife.graphics;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.Map;

public class Game extends JFrame{
    private enum GameState {
        STARTED,
        STOPPED
    }

    private GameState gameState;
    private final static String PLAY_SYMBOL = new String(Character.toChars(9654));
    private final static String PAUSE_SYMBOL = new String(Character.toChars(9208));

    private final JButton playButton = new JButton(PLAY_SYMBOL);
    private final JButton pauseButton = new JButton(PAUSE_SYMBOL);
    private final JComboBox<String> refreshRateBox = new JComboBox<>();

    private final static Map<String, Integer> DELAY_COMBO_MAP = Map.of(
            "100 ms", 100,
            "200 ms", 200,
            "500 ms", 300,
            "1000 ms", 1000
    );

    public Game() {
        super("Game Of Life!");
        BorderLayout borderLayout = new BorderLayout();
        JPanel buttonPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        playButton.setFont(new Font("Segoe", Font.PLAIN, 20));
        pauseButton.setFont(new Font("Segoe", Font.PLAIN, 20));
        Comparator<String> compByLength = Comparator.comparingInt(String::length)
                .thenComparing(String::compareTo);
        DELAY_COMBO_MAP.keySet().stream().sorted(compByLength).forEach(refreshRateBox::addItem);
        buttonPanel.setLayout(flowLayout);
        buttonPanel.add(refreshRateBox);
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        this.setLayout(borderLayout);
        this.gameState = GameState.STOPPED;
        pauseButton.setEnabled(false);
        Board board = new Board();
        playButton.addActionListener(e -> {
            if (this.gameState == GameState.STOPPED) {
                this.gameState = GameState.STARTED;
                // Begin a timer which each second updates the board
                board.playGame(DELAY_COMBO_MAP.get((String)refreshRateBox.getSelectedItem()));
                playButton.setEnabled(false);
                pauseButton.setEnabled(true);
                refreshRateBox.setEnabled(false);
            }
        });

        pauseButton.addActionListener(e -> {
            if (this.gameState == GameState.STARTED) {
                this.gameState = GameState.STOPPED;
                // stops the timer.
                board.pauseGame();
                playButton.setEnabled(true);
                pauseButton.setEnabled(false);
                refreshRateBox.setEnabled(true);
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(board.getGameTable(), BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);

    }
}
