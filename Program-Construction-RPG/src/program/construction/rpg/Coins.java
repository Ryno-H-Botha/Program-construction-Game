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

    public int Points;
    public int CoinCount;

    private Movement game;

    public Coins(Movement game) {
        this.game = game;
    }

    public int getPoints() {
        return this.Points;
    }

    public void setPoints(int Points) {
        this.Points = Points;
    }

    public int getCoinCount() {
        return this.CoinCount;
    }

    public void setCoinCounts(int CoinCount) {
        this.CoinCount = CoinCount;
    }

    public void generateCoins() {
        Random rand = new Random();
        CoinCount = rand.nextInt(9) + 1;//so zero wont ever appear
        int coinsPlaced = 0;
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

//    
    public void checkCoins(char move) {
        int CurrentRow = game.getCurrentRow();
        int CurrentCol = game.getCurrentCol();

        switch (move) {
            case 'w': // move up
                if (CurrentRow > 0) {
                    CurrentRow--; // Update row first
                    if (game.array[CurrentRow][CurrentCol] == 'C') {
                        setPoints(getPoints() + 10);
                        game.array[CurrentRow][CurrentCol] = '.'; // Clear the coin after collecting
                        CoinCount--;
                    }
                }
                break;
            case 's': // move down
                if (CurrentRow < game.ROWS - 1) {
                    CurrentRow++; // Update row first
                    if (game.array[CurrentRow][CurrentCol] == 'C') {
                        setPoints(getPoints() + 10);
                        game.array[CurrentRow][CurrentCol] = '.'; // Clear the coin after collecting
                        CoinCount--;
                    }
                }
                break;
            case 'a': // move left
                if (CurrentCol > 0) {
                    CurrentCol--; // Update column first
                    if (game.array[CurrentRow][CurrentCol] == 'C') {
                        setPoints(getPoints() + 10);
                        game.array[CurrentRow][CurrentCol] = '.'; // Clear the coin after collecting
                        CoinCount--;
                    }
                }
                break;
            case 'd': // move right
                if (CurrentCol < game.COLS - 1) {
                    CurrentCol++; // Update column first
                    if (game.array[CurrentRow][CurrentCol] == 'C') {
                        setPoints(getPoints() + 10);
                        game.array[CurrentRow][CurrentCol] = '.'; // Clear the coin after collecting
                        CoinCount--;
                    }
                }
                break;
        }
    }
}
