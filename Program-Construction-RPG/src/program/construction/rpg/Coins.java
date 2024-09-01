/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;

import java.util.Random;

/**
 *
 * @author rynob
 */
public class Coins {

    // Points collected by the player
    public int Points;
    // Number of coins currently on the grid
    public int CoinCount;

    // Reference to the Movement object for accessing the game grid
    private Movement game;

    /**
     * Constructor for the Coins class.
     * @param game An instance of the Movement class, used to interact with the game grid.
     */
    public Coins(Movement game) {
        this.game = game;
    }

    /**
     * Generates a random number of coins and places them on the game grid.
     * The number of coins is between 1 and 9 (inclusive).
     */
    public void generateCoins() {
        Random rand = new Random();
        CoinCount = rand.nextInt(9) + 1; // Random number of coins between 1 and 9
        int coinsPlaced = 0;

        // Place coins on the grid until the desired number is placed
        while (coinsPlaced < CoinCount) {
            int row = rand.nextInt(game.ROWS);
            int col = rand.nextInt(game.COLS);
            // Place a coin if the spot is empty
            if (game.array[row][col] == '.') {
                game.array[row][col] = 'C';
                coinsPlaced++;
            }
        }
    }

    /**
     * Checks if the player has moved to a cell containing a coin and updates points and coin count accordingly.
     * @param move The direction of the player's move ('w', 's', 'a', 'd').
     */
    public void checkCoins(char move) {
        int CurrentRow = game.getCurrentRow();
        int CurrentCol = game.getCurrentCol();

        // Update position based on the move
        switch (move) {
            case 'w': // Move up
                if (CurrentRow > 0) {
                    CurrentRow--;
                    if (game.array[CurrentRow][CurrentCol] == 'C') {
                        // Collect the coin and update points
                        setPoints(getPoints() + 10);
                        game.array[CurrentRow][CurrentCol] = '.'; // Clear the coin from the grid
                        CoinCount--;
                    }
                }
                break;
            case 's': // Move down
                if (CurrentRow < game.ROWS - 1) {
                    CurrentRow++;
                    if (game.array[CurrentRow][CurrentCol] == 'C') {
                        // Collect the coin and update points
                        setPoints(getPoints() + 10);
                        game.array[CurrentRow][CurrentCol] = '.'; // Clear the coin from the grid
                        CoinCount--;
                    }
                }
                break;
            case 'a': // Move left
                if (CurrentCol > 0) {
                    CurrentCol--;
                    if (game.array[CurrentRow][CurrentCol] == 'C') {
                        // Collect the coin and update points
                        setPoints(getPoints() + 10);
                        game.array[CurrentRow][CurrentCol] = '.'; // Clear the coin from the grid
                        CoinCount--;
                    }
                }
                break;
            case 'd': // Move right
                if (CurrentCol < game.COLS - 1) {
                    CurrentCol++;
                    if (game.array[CurrentRow][CurrentCol] == 'C') {
                        // Collect the coin and update points
                        setPoints(getPoints() + 10);
                        game.array[CurrentRow][CurrentCol] = '.'; // Clear the coin from the grid
                        CoinCount--;
                    }
                }
                break;
        }
    }

    // Getter for points
    public int getPoints() {
        return this.Points;
    }

    // Setter for points
    public void setPoints(int Points) {
        this.Points = Points;
    }

    // Getter for the number of coins
    public int getCoinCount() {
        return this.CoinCount;
    }

    // Setter for the number of coins
    public void setCoinCounts(int CoinCount) {
        this.CoinCount = CoinCount;
    }
}
