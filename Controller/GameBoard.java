package Controller;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import Model.*;

public class GameBoard extends JPanel {

    private Wordle wordle; // model for the game
    private JLabel status; // current status text

    // Game constants
    public static final int BOARD_WIDTH = 500;
    public static final int BOARD_HEIGHT = 600;
    private boolean gameOver;
    private int wrongGuessCount = 0;
    private boolean rowCompleted;

    public GameBoard(JLabel statusInit) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);

        wordle = new Wordle();
        status = statusInit;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (gameOver) {
                    return;
                }
                char c = e.getKeyChar();
                if ((c >= 97 && c <= 122) || (c == KeyEvent.VK_ENTER)) {
                    if (c == KeyEvent.VK_ENTER && rowCompleted) {
                        if (wordle.getCurrentColumn() >= (wordle.getMaxCol() - 1)) {
                            WordleCharAndColor[] currRowCells = wordle.getFullRow();
                            int numGreenCells = 0;
                            for (WordleCharAndColor wc : currRowCells) {
                                if (wc != null && wc.getBackColor().equals(wordle.getGreen())) {
                                    numGreenCells++;
                                }
                            }
                            updateStatus();

                            wordle.currRow();
                            rowCompleted = false;
                        }
                    } else if (c != KeyEvent.VK_ENTER && !rowCompleted) {
                        int currentColumnBefore = wordle.getCurrentColumn();
                        wordle.currCol(e.getKeyChar());
                        int currentColumnAfter = wordle.getCurrentColumn();
                        if (currentColumnBefore == wordle.getMaxCol() - 1 &&
                                currentColumnAfter == wordle.getMaxCol()) {
                            rowCompleted = true;
                        }
                    }
                    repaint();
                }
            }
        });
    }

    public void save() {
        GameState gameState = new GameState(this.wordle, this.gameOver, this.status.getText(),
                rowCompleted, wrongGuessCount);
        try {
            FileUtil.save(gameState);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Could not save: "
                    + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        wordle = new Wordle();
        reset();
    }

    public void load() {
        GameState gameState;
        try {
            gameState = FileUtil.load();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not load",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.wordle = gameState.getWordle();
        this.gameOver = gameState.isGameOver();
        this.status.setText(gameState.getStatus());
        this.rowCompleted = gameState.isRowCompleted();
        this.wrongGuessCount = gameState.getWrongGuessCount();

        refreshGui();
    }

    public void undo() {
        wordle.undo();
        rowCompleted = false;
        refreshGui();
    }

    private void refreshGui() {
        repaint();
        requestFocusInWindow();
    }

    public void reset() {
        gameOver = false;
        wrongGuessCount = 0;
        rowCompleted = false;

        wordle.reset();
        status.setText("Play Now");
        refreshGui();
    }

    private void updateStatus() {
        if (wordle.checkIfWon()) {
            gameOver = true;
            status.setText("You have won!");
            return;
        } else {
            wrongGuessCount++;
        }

        if (this.wordle.getCurrentRow() == wordle.getMaxRow() ||
                wrongGuessCount == wordle.getMaxRow()) {
            gameOver = true;
            status.setText("You have lost. Correct word is: " +
                    new String(wordle.getCurrentWord()));
        } else {
            status.setText("Keep playing. Number of Words Guessed: " + wrongGuessCount);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draws board grid
        g.drawLine(100, 0, 100, 600);
        g.drawLine(200, 0, 200, 600);
        g.drawLine(300, 0, 300, 600);
        g.drawLine(400, 0, 400, 600);
        g.drawLine(500, 0, 500, 600);
        g.drawLine(600, 0, 600, 600);
        g.drawLine(0, 100, 600, 100);
        g.drawLine(0, 200, 600, 200);
        g.drawLine(0, 300, 600, 300);
        g.drawLine(0, 400, 600, 400);
        g.drawLine(0, 500, 600, 500);

        WordleCharAndColor[][] grid = wordle.getGrid();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                WordleCharAndColor wc = grid[row][col];
                if (wc != null) {
                    String character = String.valueOf(wc.getC());
                    g.setColor(wc.getBackColor());
                    g.fillRect(col * 100, row * 100, 100, 100);
                    g.setColor(wc.getFrontColor());
                    g.drawString(character, col * 100 + 50, row * 100 + 50);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
