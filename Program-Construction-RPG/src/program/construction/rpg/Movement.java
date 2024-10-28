/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;

/**
 *
 * @author rynob
 */
import static program.construction.rpg.Inialize_array.COLS;
import static program.construction.rpg.Inialize_array.ROWS;

public class Movement extends Inialize_array {

    // Counter to track the number of moves made by the player
    public int MovesCount = 1;

    /**
     * Moves the player up one step if not already at the top boundary.
     */
    public void moveUp() {
        if (currentRow > 0) { // Check if the player is not on the top edge
            array[currentRow][currentCol] = '.';  // Clear the player's current position
            currentRow--; // Move the player up by decrementing the row index
            array[currentRow][currentCol] = '@';  // Set the new position of the player
        }
    }

    /**
     * Moves the player down one step if not already at the bottom boundary.
     */
    public void moveDown() {
        if (currentRow < ROWS - 1) { // Check if the player is not on the bottom edge
            array[currentRow][currentCol] = '.';  // Clear the player's current position
            currentRow++; // Move the player down by incrementing the row index
            array[currentRow][currentCol] = '@';  // Set the new position of the player
        }
    }

    /**
     * Moves the player left one step if not already at the left boundary.
     */
    public void moveLeft() {
        if (currentCol > 0) { // Check if the player is not on the left edge
            array[currentRow][currentCol] = '.';  // Clear the player's current position
            currentCol--; // Move the player left by decrementing the column index
            array[currentRow][currentCol] = '@';  // Set the new position of the player
        }
    }

    /**
     * Moves the player right one step if not already at the right boundary.
     */
    public void moveRight() {
        if (currentCol < COLS - 1) { // Check if the player is not on the right edge
            array[currentRow][currentCol] = '.';  // Clear the player's current position
            currentCol++; // Move the player right by incrementing the column index
            array[currentRow][currentCol] = '@';  // Set the new position of the player
        }
    }

    /**
     * Gets the count of moves made by the player.
     * @return The number of moves made.
     */
    public int getMovesCount() {
        return this.MovesCount;
    }

    /**
     * Sets the count of moves made by the player.
     * @param MovesCount The new number of moves.
     */
    public void setMovesCount(int MovesCount) {
        this.MovesCount = MovesCount;
    }
    
    public char[][] getArray() {
        return array; // Ensure this returns the updated array
    }
}