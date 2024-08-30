/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;

import java.util.*;
import java.io.*;

/**
 *
 * @author rynob
 */
public class Inialize_array {
    private static final int ROWS = 9;
    private static final int COLS = 13;
    public static int NewRow = ROWS / 2;
    public static int NewCol = COLS / 2;
    public static char[][] array = new char[ROWS][COLS];
    public int currentRow;
    public int currentCol;
    public static int GetRows()
    {
       return ROWS;
    }
    public static int GetCols()
    {
        return COLS;
    }
    public static int GetNewRow()
    {
       return NewRow ;
    }
    public static int GetNewCol()
    {
        return NewCol;
    }

    
    public static void GenerateArray(int RowPos, int ColPos) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                array[i][j] = '.';
            }
        }
        array[RowPos][ColPos] = '@';
    }
    private static void printArray() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
