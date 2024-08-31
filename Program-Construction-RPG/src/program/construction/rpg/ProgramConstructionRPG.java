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
        Scanner scan = new Scanner(System.in);
        File_read_write NGame = new File_read_write();
        NGame.readSaveArrayFile();
        
        Movement game = new Movement();
        int leavue = 0;
        System.out.println("New game     (n) \nSaved Game   (s) \nInstructions (I)");
        String start = scan.nextLine().toLowerCase();
        
        
        
        Coins cn = new Coins(game);
            if(start.equals("i")) 
            {
                NGame.readInstArrayFile();
                start = scan.nextLine().toLowerCase();
            }
        switch (start) {
            case "n":
                cn.setPoints(0);
                game.SetPostion(NewRow, NewCol);
                cn.generateCoins();
                game.printArray();
                break;
            case "s":
                cn.setPoints(NGame.getSavedPoints());
                int SavedRow = NGame.getSavedRows();
                int SavedCol = NGame.getSavedCols();
                game.SetPostion(SavedRow, SavedCol);
                NGame.setCoins(game);
                game.printArray();
                break;
        }

            while (true) {
            System.out.println("Use arrow keys (WASD) to move '@' or 'q' to quit:");
            char input = scan.next().charAt(0);
            System.out.println("Points = " + cn.getPoints());
            switch (input) {
                case 'w'://move up
                    cn.checkCoins('w');
                    game.moveUp();
                    break;
                case 's':
                    cn.checkCoins('s');
                    game.moveDown();
                    break;
                case 'a':
                    cn.checkCoins('a');
                    game.moveLeft();
                    break;
                case 'd':
                    cn.checkCoins('d');
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

        System.out.println (
        "Save (S)\nDelete (D)");
            char SaveData = scan.next().charAt(0);

        System.out.println (
        "You entered:" + SaveData);


            switch (SaveData) {
            case 's':
                NGame.setSavedRows(game.GetCurrentRow());
                NGame.setSavedCols(game.GetCurrentCol());
                NGame.setSavedPoints(cn.getPoints());
                NGame.getCoins(game);
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
