package View;

import javax.swing.*;
import java.awt.*;
import Controller.*;

public class RunWordle implements Runnable {

    public void run() {
        final JFrame frame = new JFrame("Wordle");
        frame.setLocation(300, 300);

        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        status_panel.add(status);

        final GameBoard board = new GameBoard(status);
        frame.add(board, BorderLayout.CENTER);

        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        control_panel.add(reset);

        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(e -> JOptionPane.showMessageDialog(frame,
                "Welcome to Wordle! In this game, the computer will\n " +
                        "randomly generate a 5 letter word that you should try\n " +
                        "to guess within 6 tries. Once you input 5 letters by\n " +
                        "typing each character through your keyboard, press Enter\n " +
                        "to see which letters are correct and which are not. If\n " +
                        "the background of a box is green, that means you have\n " +
                        "the right letter in the right spot. If the background is\n " +
                        "yellow, you have the right letter but in the wrong spot.\n " +
                        "And if the background is gray, you have neither, so try\n " +
                        "again! After 6 tries, if you haven’t guessed the word,\n " +
                        "the game will tell you what the correct word was. If you’d\n " +
                        "like to save the game mid-way and come back to it exactly\n " +
                        "as it was before, just press save and load! And you can\n " +
                        "undo your guesses so long as they are in the row that you\n " +
                        "are currently working on. In this version of Wordle, you\n " +
                        "can input words that might not be in the word list, or an\n " +
                        "arbitrary set of 5 characters, as this could be a potential\n " +
                        "strategy to lock in a couple characters in place. But you\n " +
                        "must be wise in which characters you choose to put on the\n " +
                        "board as there are only 6 tries per game. Good luck and\n " +
                        "have fun!\n"));
        control_panel.add(instructions);

        final JButton save = new JButton("Save");
        save.addActionListener(e -> board.save());
        control_panel.add(save);

        final JButton load = new JButton("Load");
        load.addActionListener(e -> board.load());
        control_panel.add(load);

        final JButton undo = new JButton("Undo");
        undo.addActionListener(e -> board.undo());
        control_panel.add(undo);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        board.reset();
    }
}
