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
    private static final String OS = System.getProperty("os.name").toLowerCase();
    public static final boolean IS_MAC = OS.contains("mac");
    private final static String PLAY_SYMBOL = IS_MAC ? "PLAY" : new String(Character.toChars(9654));
    private final static String PAUSE_SYMBOL = IS_MAC ? "PAUSE" : new String(Character.toChars(9208));
    private final static String SHUFFLE_SYMBOL = IS_MAC ? "SHUFFLE" : new String(Character.toChars(128256));

    private final JButton shuffleButton = new JButton(SHUFFLE_SYMBOL);
    private final JButton playButton = new JButton(PLAY_SYMBOL);
    private final JButton pauseButton = new JButton(PAUSE_SYMBOL);
    private final JComboBox<String> refreshRateBox = new JComboBox<>();

    private final static Map<String, Integer> DELAY_COMBO_MAP = Map.of(
            "100 ms", 100,
            "200 ms", 200,
            "500 ms", 300,
            "1000 ms", 1000
    );

    private Board  board;
    private final JPanel boardPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    BorderLayout borderLayout = new BorderLayout();

    public Game() {
        super("Game Of Life!");
        FlowLayout flowLayout = new FlowLayout();
        playButton.setFont(new Font("Segoe", Font.PLAIN, 20));
        pauseButton.setFont(new Font("Segoe", Font.PLAIN, 20));
        shuffleButton.setFont(new Font("Segoe", Font.PLAIN, 20));
        refreshRateBox.setFont(new Font("Segoe", Font.PLAIN, 20));
        Comparator<String> compByLength = Comparator.comparingInt(String::length)
                .thenComparing(String::compareTo);
        DELAY_COMBO_MAP.keySet().stream().sorted(compByLength).forEach(refreshRateBox::addItem);
        buttonPanel.setLayout(flowLayout);
        buttonPanel.add(refreshRateBox);
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(shuffleButton);
        this.gameState = GameState.STOPPED;
        pauseButton.setEnabled(false);
        playButton.addActionListener(e -> {
            if (this.gameState == GameState.STOPPED) {
                // Begin a timer which each second updates the board
                board.playGame(DELAY_COMBO_MAP.get((String)refreshRateBox.getSelectedItem()));
                playButton.setEnabled(false);
                pauseButton.setEnabled(true);
                refreshRateBox.setEnabled(false);
                shuffleButton.setEnabled(false);
                this.gameState = GameState.STARTED;
            }
        });

        pauseButton.addActionListener(e -> {
            if (this.gameState == GameState.STARTED) {
                // stops the timer.
                board.pauseGame();
                playButton.setEnabled(true);
                pauseButton.setEnabled(false);
                refreshRateBox.setEnabled(true);
                shuffleButton.setEnabled(true);
                this.gameState = GameState.STOPPED;
            }
        });

        shuffleButton.addActionListener(e -> setupBoard());
        setupGame();
    }

    private void setupGame(){
        setupBoard();
        this.setLayout(borderLayout);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(boardPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
    }

    private void setupBoard() { 
        if (board != null) {
            boardPanel.remove(board.getGameTable());
        }
        board = new Board();
        boardPanel.add(board.getGameTable());
        board.getGameTable().repaint();
        boardPanel.repaint();
        this.revalidate();
    }
}
