/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package program.construction.rpg;

import java.util.*;
import java.io.*;



/**
 *
 * @author rynob
 */
public class ProgramConstructionRPG {

    /**
     * @param args the command line arguments
     */
    private static final int ROWS = 9;
    private static final int COLS = 13;
    public static int currentRow = ROWS / 2;
    public static int currentCol = COLS / 2;
    public static char[][] array = new char[ROWS][COLS];
    public static void main(String[] args) 
    { readArrayFile();
      printArray();
    }
        // TODO code application logic here
    
    

    public static void readArrayFile(){
        try{
        BufferedReader inStream = new BufferedReader(new FileReader("./resources/input.txt"));
            String OutString = "";
            String InLine = inStream.readLine();
            int row = 0; 
            while (InLine != null && row< ROWS ) {
                String[] element = InLine.split(",");
                for (int col = 0; col < COLS; col++) {
                    array[row][col] = element[col].charAt(0);
                    
                }
                row++;
            }

            inStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file ");
        } catch (IOException e) {
            System.out.println("Error reading from file ");
        }
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
    
        
        
        
        
        
 