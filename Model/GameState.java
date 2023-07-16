package Model;


import java.io.Serializable;

public class GameState implements Serializable {
    private Wordle wordle;
    private boolean gameOver;
    private String status;
    private boolean rowCompleted;
    private int wrongGuessCount;

    public GameState(Wordle wordle, boolean gameOver, String status,
                     boolean rowCompleted, int wrongGuessCount) {
        this.wordle = wordle;
        this.gameOver = gameOver;
        this.status = status;
        this.rowCompleted = rowCompleted;
        this.wrongGuessCount = wrongGuessCount;
    }

    public Wordle getWordle() {

        return wordle;

    }

    public boolean isGameOver() {

        return gameOver;

    }

    public String getStatus() {

        return status;

    }

    public boolean isRowCompleted() {

        return rowCompleted;

    }
    public int getWrongGuessCount() {

        return wrongGuessCount;

    }
}
