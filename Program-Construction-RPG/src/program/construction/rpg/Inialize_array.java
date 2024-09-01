/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;

/**
 *
 * @author rynob
 */
public class Inialize_array {

    // Constants defining the dimensions of the array
    protected static final int ROWS = 9;
    protected static final int COLS = 13;

    // Default position for placing objects on the array
    protected static int NewRow = ROWS / 2;
    protected static int NewCol = COLS / 2;

    // The game grid
    protected char[][] array = new char[ROWS][COLS];
    
    // Current position of the player
    protected int currentRow;
    protected int currentCol;
    
    /**
     * Sets the position of the player and initializes the array.
     * @param Row The row to set the player at.
     * @param Col The column to set the player at.
     */
    public void SetPostion(int Row, int Col) {
        this.currentRow = Row;
        this.currentCol = Col;
        GenerateArray(Row, Col);
    }

    /**
     * Sets the position of a coin on the array.
     * @param Row The row to place the coin at.
     * @param Col The column to place the coin at.
     */
    public void setCoinPostion(int Row, int Col) {
        array[Row][Col] = 'C'; // 'C' represents a coin
    }

    /**
     * Generates the initial array state with '.' and places the player at the specified position.
     * @param Row The row to place the player.
     * @param Col The column to place the player.
     */
    public void GenerateArray(int Row, int Col) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                array[i][j] = '.'; // Initialize all positions with '.'
            }
        }
        array[Row][Col] = '@'; // '@' represents the player
    }

    /**
     * Prints the current state of the array to the console.
     * Replaces the player's position with 'H' for display if it's not already '@'.
     */
    public void printArray() {
        char c = array[NewRow][NewCol];
        if (!(c == '@')) {
            array[NewRow][NewCol] = 'H'; // 'H' represents the previous position of the player
        }
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(array[i][j] + " "); // Print each character with a space
            }
            System.out.println(); // New line after each row
        }
    }

    /**
     * Gets the current column position of the player.
     * @return The current column.
     */
    public int getCurrentCol() {
        return currentCol;
    }

    /**
     * Gets the current row position of the player.
     * @return The current row.
     */
    public int getCurrentRow() {
        return currentRow;
    }

    /**
     * Gets the number of rows in the array.
     * @return The number of rows.
     */
    public static int getRows() {
        return ROWS;
    }

    /**
     * Gets the number of columns in the array.
     * @return The number of columns.
     */
    public static int getCols() {
        return COLS;
    }

    /**
     * Gets the default row position.
     * @return The default row position.
     */
    public static int getNewRow() {
        return NewRow;
    }

    /**
     * Gets the default column position.
     * @return The default column position.
     */
    public static int getNewCol() {
        return NewCol;
    }

}
