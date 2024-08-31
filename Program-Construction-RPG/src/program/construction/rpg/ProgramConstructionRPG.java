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
    public static int ROWS = Inialize_array.GetRows();
    public static int COLS = Inialize_array.GetCols();
    public static int NewRow = ROWS / 2;
    public static int NewCol = COLS / 2;

    public static String inputfile = "./resources/input.txt";
    public static String savefile = "./resources/Save.txt";
    public static String instfile = "./resources/Inst.txt";
    public static char[][] array = new char[ROWS][COLS];
    
    
    public static void main(String[] args) {
        
        File_read_write NGame = new File_read_write();
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("New game     (n) \nSaved Game   (s) \nInstructions (I)");
        String start = scan.nextLine().toLowerCase();
            Movement game = new Movement();
                            
        int leavue = 0;
        switch (start) {
            case "n":
                    game = new Movement();
                    game.SetPostion(NewRow, NewCol);
                    
                break;
            case "s":
                    NGame.readSaveArrayFile();
                    int SavedRow = NGame.getSavedRows();
                    int SavedCol = NGame.getSavedCols();
                    game = new Movement();
                    game.SetPostion(SavedRow,SavedCol);
                    game.printArray();
                    
                break;
            case "i":
                    NGame.readInstArrayFile();
                    start = scan.nextLine().toLowerCase();
                break;
        }

        while (true) {
            
            System.out.println("Use arrow keys (WASD) to move '@' or 'q' to quit:");
            char input = scan.next().charAt(0);
        
            switch (input) {
                case 'w'://move up
                         game.moveUp();
                    break;
                case 's':
                        game.moveDown();
                    break;
                case 'a':
                        game.moveDown();
                    break;
                case 'd':
                        game.moveRight();
                    break;
                case 'q':
                    System.out.println("Exiting...");
                    leavue = 1;
                    break;
                default:
                    System.out.println("Invalid input. Use WASD keys.");
                    break;
            }
            if (leavue == 1) {
                break;
            }
            game.printArray();
        }
        System.out.println("Save (S)\nDelete (D)");
        char SaveData = scan.next().charAt(0);
        
        System.out.println("You entered:" + SaveData);

        
        switch(SaveData){
            case 's':
                NGame.writeSave();
                System.out.println("Data has been Saved.");
            break;
            case 'd':    
                System.out.println("Data deleted.");
            break;
            default:
                System.out.println("Invalid input.");
            break;
           
        }
    
    }
}
