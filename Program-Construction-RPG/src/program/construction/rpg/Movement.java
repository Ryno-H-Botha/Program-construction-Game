/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;

/**
 *
 * @author rynob
 */
import java.util.*;
import java.io.*;

import static program.construction.rpg.Inialize_array.COLS;
import static program.construction.rpg.Inialize_array.ROWS;

public class Movement extends Inialize_array  
{
     // Move up method
    public  void moveUp() {
        if (currentRow > 0) {
            array[currentRow][currentCol] = '.';  // Clear current position
            currentRow--;
            array[currentRow][currentCol] = '@';  // Move the player up
        }
    }

    // Move down method
    public  void moveDown() {
        if (currentRow < ROWS - 1) {
            array[currentRow][currentCol] = '.';  // Clear current position
            currentRow++;
            array[currentRow][currentCol] = '@';  // Move the player down
        }
    }

    // Move left method
    public  void moveLeft() {
        if (currentCol > 0) {
            array[currentRow][currentCol] = '.';  // Clear current position
            currentCol--;
            array[currentRow][currentCol] = '@';  // Move the player left
        }
    }

    // Move right method
    public  void moveRight() {
        if (currentCol < COLS - 1) {
            array[currentRow][currentCol] = '.';  // Clear current position
            currentCol++;
            array[currentRow][currentCol] = '@';  // Move the player right
        }
    }
}