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

public class Movement extends ProgramConstructionRPG  
{
    public static HashMap<String, Integer> PlayerInfo = File_read_write.getPlayerInfo();
    public static int ROWS = Inialize_array.GetRows();
    public static int COlS = Inialize_array.GetCols();
    public int currentRow = ;
    public int currentCol;
    public  
    
    public void SetCurrentCol(int Col)
   {
       currentCol = Col;
   }
     public void SetCurrentRow()
   {
       currentRow = Row;
   }
         public static int GetCurrentCol()
    {
        return currentCol;
    }
    public static int GetCurrentRow()
    {
       return currentRow;
    }

    public void moveUp() {
    if (currentRow > 0) {
        array[currentRow][currentCol] = '.';
        currentRow--;
        array[currentRow][currentCol] = '@';
    }
    

    public void moveDown() {
        if (currentRow < ROWS - 1) {
            array[currentRow][currentCol] = '.';
            currentRow++;
            array[currentRow][currentCol] = '@';
        }
    }

    public  void moveLeft() {
        if (currentCol > 0) {
            array[currentRow][currentCol] = '.';
            currentCol--;
            array[currentRow][currentCol] = '@';
        }
    }

    public  void moveRight() {
        if (currentCol < COLS - 1) {
            array[currentRow][currentCol] = '.';
            currentCol++;
            array[currentRow][currentCol] = '@';
        }
    }
}

    

