# Wordle

This is a Java implementation of the popular game Wordle. The game features a 5x6 grid where players attempt to guess a 5-letter word within six attempts.

## Core Concepts

1. **File I/O**: Used to save and load game states, as well as read a CSV file containing a list of 5-letter words. The `FileUtil` class handles the creation and reading of game state files, while `WordleReader` reads the word list.
2. **Collections**: Utilized in implementing the undo functionality. A history list stores the grid states after each guess, enabling users to undo their last guess.
3. **2D Array**: The main game grid is a 2D array of `WordleCharAndColor` objects, which store the character and color data for each cell.
4. **JUnit Testable Component**: The game includes a suite of 11 test cases that test the main functionalities of the `Wordle` class, as well as the encapsulation of the 2D array and history grid.

## Implementation Overview

- `Wordle.java`: Contains the main game logic, managing game state variables and implementing game functions such as reset and undo.
- `RunWordle.java`: Sets up the user interface, creating the game frame and buttons, and setting up their action listeners.
- `WordleCharAndColor.java`: A helper class used to create the main game grid, storing the character and color data for each cell.
- `WordleReader.java`: Implements File I/O to read in the CSV file containing the word list.
- `GameBoard.java`: Connects the front-end and back-end, reflecting user inputs in the game state and updating the user interface to match.
- `GameState.java`: Stores the current game state, including the `Wordle` object and various game variables, for saving and loading.
- `FileUtil.java`: Implements File I/O to save and load game states.

## Design Evaluation

This implementation features a strong separation of functionality, with different classes handling different aspects of the game. Private state is well-encapsulated, with instance variables generally being private and accessed through getter methods. Future refactoring may focus on making the `paintComponent` method more concise.

## Playing the Game

To play the game, run the `RunWordle.java` file. This will open a new window with the game grid and control buttons. Enter your guesses using the keyboard and see how quickly you can guess the word!
