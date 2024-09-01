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
    
    private static Scanner scan ;
    private static File_read_write GameFiles;
    private static Movement Game ;
    private static MonsterMovement Mons ;
    private static Coins Coin ;
    private static Levels Level ;
    private static Abilities Ability ;
    private static boolean leave;
    
    public static int ROWS = Inialize_array.getRows();
    public static int COLS = Inialize_array.getCols();
    public static int NewRow = ROWS / 2;
    public static int NewCol = COLS / 2;
    public static String inputfile = "./resources/input.txt";
    public static String savefile = "./resources/Save.txt";
    public static String instfile = "./resources/Inst.txt";
    public static char[][] array = new char[ROWS][COLS];
    
    private static void Setup()
    {
        scan = new Scanner(System.in);
        GameFiles = new File_read_write();
        Game = new Movement();
        Mons = new MonsterMovement(Game);
        Coin = new Coins(Game);
        Level = new Levels(Game, Coin,Mons);
        Ability = new Abilities();
        GameFiles.readSaveArrayFile();
        leave = false;
    }
    
    
    public static void main(String[] args) {
        
        Setup();
        
        
        System.out.println("New game     (n) \nSaved Game   (s) \nInstructions (I)");
        String start = scan.nextLine().toLowerCase();
        while(!(start.contentEquals("i")||start.contentEquals("n")||start.contentEquals("s")))
        {
            System.out.println("Invalid input: \nNew game     (n) \nSaved Game   (s) \nInstructions (I)");
            start = scan.nextLine().toLowerCase();
        }
        if (start.equals("i")) {
            GameFiles.readInstArrayFile();
            start = scan.nextLine().toLowerCase();
        }
        switch (start) {
            case "i":
            {
                
            }
            case "n":
                Coin.setPoints(0);
                Game.SetPostion(NewRow, NewCol);
                Coin.generateCoins();
                Mons.setMonsterPosition();
                Game.printArray();
                break;
            case "s":
                Coin.setPoints(GameFiles.getSavedPoints());
                Level.setLevels(GameFiles.getSavedLevels());
                int SavedRow = GameFiles.getSavedRows();
                int SavedCol = GameFiles.getSavedCols();
                Game.setMovesCount(GameFiles.getSavedMovesCount());
                Game.SetPostion(SavedRow, SavedCol);
                GameFiles.setArrayCoins(Game, Coin);
                Game.printArray();
                break;
        }

        Game.printArray(); // Print the array to show the monster

        while (true) {
            System.out.println("Use arrow keys (WASD) to move '@' or 'q' to quit Then press Enter to confirm:");
            char input = scan.next().charAt(0);
            switch (input) {
                case 'w'://move up
                    Coin.checkCoins('w');
                    Game.moveUp();
                    Mons.moveMonster();
                    break;
                case 's':
                    Coin.checkCoins('s');
                    Game.moveDown();
                    Mons.moveMonster();
                    break;
                case 'a':
                    Coin.checkCoins('a');
                    Game.moveLeft();
                    Mons.moveMonster();
                    break;
                case 'd':
                    Coin.checkCoins('d');
                    Game.moveRight();
                    Mons.moveMonster();
                    break;
                case 'q':
                    System.out.println("Exiting...");
                    leave = true;
                    break;
                default:
                    if (input == 'f' || input == 'g' || input == 'c') {
                         
                        Ability.sendAbilityCommand(input);
                    } else {
                        System.out.println("Invalid input. Use WASD keys.");
                    }
                    break;
            }
            if (leave == true) {
                break;
            }

            System.out.println("Points : " + Coin.getPoints() + "  Level: " + Level.getLevels() + "  Moves: " + Game.getMovesCount());
            Level.CheckLevel();
            Game.MovesCount++;
            Game.printArray();
        }

        System.out.println(
                "Save (S)\nDelete (D)");
        char SaveData = scan.next().charAt(0);

        System.out.println(
                "You entered: " + SaveData);

        switch (SaveData) {
            case 's':
                GameFiles.setSavedRows(Game.getCurrentRow());
                GameFiles.setSavedCols(Game.getCurrentCol());
                GameFiles.setSavedMovesCount(Game.getMovesCount());
                GameFiles.setSavedPoints(Coin.getPoints());
                GameFiles.setSavedLevels(Level.getLevels());

                GameFiles.saveCoins(Game);
                GameFiles.writeSave();
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

    public void SaveSetup(Movement game, Coins cn,MonsterMovement mons) {
        
    }

    
}
