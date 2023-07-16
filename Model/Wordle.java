package Model;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wordle implements Serializable {

    private List<WordleCharAndColor[][]> historyList = new ArrayList<>();

    private WordleCharAndColor[][] grid;

    private char[] currentWord;
    private char[] guess;

    private static final int MAX_COL = 5;
    private static final int MAX_ROW = 6;

    private final Random random = new Random();

    private int currCol;
    private int currRow;
    private boolean gameOver;


    private static Color gray = new Color(120, 124, 126);
    private static Color green = new Color(106, 170, 100);
    private static Color yellow = new Color(201, 180, 88);
    private List<String> wordList = WordleReader.createWordList();

    public Wordle() {
        reset();
    }

    public void setCurrentWord(String word) {

        this.currentWord = word.toCharArray();

    }

    public WordleCharAndColor[][] getGrid() {
        return this.grid;
    }

    public List<WordleCharAndColor[][]> getHistoryList() {
        return this.historyList;
    }

    public WordleCharAndColor[] getFullRow() {
        return grid[this.currRow];

    }

    public int getMaxCol() {

        return MAX_COL;

    }

    public int getMaxRow() {

        return MAX_ROW;

    }

    public Color getGreen() {

        return green;

    }

    public Color getGray() {

        return gray;

    }

    public Color getYellow() {

        return yellow;

    }

    public int getCurrentRow() {

        return this.currRow;

    }

    public int getCurrentColumn() {

        return this.currCol;

    }

    public char[] getCurrentWord() {

        return this.currentWord;

    }

    public boolean checkIfWon() {
        this.historyList = new ArrayList<>();
        return (new String(this.currentWord).equalsIgnoreCase(new String(this.guess)));
    }

    public String generateCurrentWord() {
        String word = wordList.get(random.nextInt(wordList.size()));
        return word;
    }

    public void reset() {
        this.grid = new WordleCharAndColor[MAX_ROW][MAX_COL];
        this.currCol = 0;
        this.currRow = 0;
        this.currentWord = generateCurrentWord().toCharArray();
        //this.currentWord = "aashi".toCharArray();
        this.guess = new char[MAX_COL];
        gameOver = false;
    }

    public void currCol(char c) {
        WordleCharAndColor[][] gridCopy = new WordleCharAndColor[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                gridCopy[i][j] = grid[i][j];
            }
        }
        historyList.add(gridCopy);
        guess[currCol] = c;
        grid[currRow][currCol] = new WordleCharAndColor(c,
                Color.WHITE, Color.BLACK);
        currCol++;
    }

    public boolean currRow() {
        for (int column = 0; column < guess.length; column++) {
            Color backgroundColor = gray;
            Color foregroundColor = Color.WHITE;
            if (guess[column] == currentWord[column]) {
                backgroundColor = green;
            } else if (has(currentWord, guess, column)) {
                backgroundColor = yellow;
            }

            grid[this.currRow][column] = new WordleCharAndColor(guess[column],
                    backgroundColor, foregroundColor);
        }
        currCol = 0;
        currRow++;
        guess = new char[MAX_COL];
        return currRow <= MAX_ROW;
    }


    private boolean has(char[] currentWord, char[] guess, int column) {
        for (int index = 0; index < currentWord.length; index++) {
            if (index != column && guess[column] == currentWord[index]) {
                return true;
            }
        }

        return false;
    }

    public void undo() {
        if (gameOver || historyList.size() == 0) {
            return;
        }
        grid = historyList.remove(historyList.size() - 1);
        if (currCol >= MAX_COL) {
            guess[MAX_COL - 1] = ' ';
        } else {
            guess[currCol] = ' ';
        }
        currCol--;
        this.currCol = Math.max(currCol, 0);
    }

    public char getCharInCell(int r, int c) {
        return grid[r][c].getC();

    }

    public Color getBackColorInCell(int r, int c) {
        return grid[r][c].getBackColor();

    }

    public Color getFrontColorInCell(int r, int c) {
        return grid[r][c].getFrontColor();

    }
}
