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
    private static Scanner scan;
    private static File_read_write GameFiles;
    private static Movement Game;
    private static MonsterMovement Mons;
    private static Coins Coin;
    private static Levels Level;
    private static Abilities Ability;
    private static boolean leave;

    public static int ROWS = Inialize_array.getRows();
    public static int COLS = Inialize_array.getCols();
    public static int NewRow = ROWS / 2;
    public static int NewCol = COLS / 2;

    public static char[][] array = new char[ROWS][COLS];
    private static int saveFile;
    private static boolean saveSelected = false;
    
    private static final Set<String> validOptionsSaves = new HashSet<>();
    static {
        validOptionsSaves.add("1");
        validOptionsSaves.add("2");
        validOptionsSaves.add("3");
    }
    private static final Set<String> validOptionsGameSets = new HashSet<>();
    static {
        validOptionsGameSets.add("n");
        validOptionsGameSets.add("s");
        validOptionsGameSets.add("i");
    }
    
    private static void Setup() {
        scan = new Scanner(System.in);
        GameFiles = new File_read_write();
        Game = new Movement();
        Mons = new MonsterMovement(Game);
        Coin = new Coins(Game);
        Ability = new Abilities();
        Level = new Levels(Game, Coin, Mons, Ability);
        leave = false;
    }

    public static void main(String[] args) {

        Setup();
        String start = gameSetSelect();
        if (start.equals("i")) {
            GameFiles.readInstArrayFile();
            start = scan.nextLine().toLowerCase();
            while(!(start.contentEquals("n")||start.contentEquals("s"))||start.contentEquals("i"))
            {
                System.out.println("Invalid input try again.");
                start = scan.nextLine().toLowerCase();
            }
        }
        switch (start) {
            case "n":
                Coin.setPoints(0);
                Game.SetPostion(NewRow, NewCol);
                Coin.generateCoins();
                Mons.setMonsterPosition();
                Game.printArray();
                break;
            case "s":
                saveSelected = true;
                saveFile = saveFileSelect();
                GameFiles.readSaveArrayFile(saveFile);
                saveReadSetup(GameFiles, Game, Coin, Mons, Ability, Level);
                Game.printArray();
                break;
        }
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
            System.out.println("Frozen (F) unused : "+Ability.getFrozenUses()+" Confused (C) unused : "+Ability.getConfusedUses()+" Intimidated (G) unused : "+Ability.getIntimidatedUses());
            Level.CheckLevel();
            Game.MovesCount++;
            Game.printArray();
        }

        System.out.println("Save (S)\nDelete (D)");
        char SaveData = scan.next().charAt(0);

        switch (SaveData) {
            case 's':
                if(saveSelected == true)
                {   
                    saveDataSetup(GameFiles, Game, Coin, Mons, Ability, Level);//write data
                    System.out.println("Save "+saveFile+" hase been updated");
                }
                else{
                    saveFile = saveFileSelect();
                    GameFiles.readSaveArrayFile(saveFile);//fill and set hash
                    saveDataSetup(GameFiles, Game, Coin, Mons, Ability, Level);//write data
                    GameFiles.writeSave(saveFile);
                }
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
    public static int saveFileSelect() {
        String value = "";
        System.out.println("Which file would you like to use?\nSave (1)\nSave (2)\nSave (3)");
        while(value.contentEquals(""))
        {
            value = scan.nextLine().trim();
        }
        System.out.println("Save selected: Save_" + value); // Debug print
        while(!validOptionsSaves.contains(value)) {
            System.out.println("Invalid input. Please select:\nSave (1)\nSave (2)\nSave (3)");
            value = scan.nextLine().trim();
            System.out.println("Save selected: Save_" + value);
        }
        return Integer.parseInt(value);
    }
    public static String gameSetSelect() {
        System.out.println("New game     (N) \nSaved Game   (S) \nInstructions (I)");
        String value = scan.nextLine().trim().toLowerCase();
        while (!validOptionsGameSets.contains(value)) {
            System.out.println("Invalid input. Please select:\nNew game     (N) \nSaved Game   (S) \nInstructions (I)");
            value = scan.nextLine().trim();
        }

        return value;
    }
   
    public static void saveReadSetup(File_read_write GameFiles, Movement Game, Coins Coin, MonsterMovement Mons, Abilities Ability, Levels Level) {
        int SavedRow = GameFiles.getSavedRows();
        int SavedCol = GameFiles.getSavedCols();
        Coin.setPoints(GameFiles.getSavedPoints());
        Level.setLevels(GameFiles.getSavedLevels());
        Game.setMovesCount(GameFiles.getSavedMovesCount());
        Ability.setFrozenUses(GameFiles.getSavedFrozenUses());
        Ability.setConfusedUses(GameFiles.getSavedConfusedUses());
        Ability.setIntimidatedUses(GameFiles.getSavedIntimidatedUses());
        Mons.setMonsCurrentRow(GameFiles.getSavedMonsCurrentRow());
        Mons.setMonsCurrentCol(GameFiles.getSavedMonsCurrentCol());
        Game.SetPostion(SavedRow, SavedCol);
        GameFiles.setArrayCoins(Game, Coin);
        Mons.checkMons();
    }

    public static void saveDataSetup(File_read_write GameFiles, Movement Game, Coins Coin, MonsterMovement Mons, Abilities Ability, Levels Level) {
        GameFiles.setSavedRows(Game.getCurrentRow());
        GameFiles.setSavedCols(Game.getCurrentCol());
        GameFiles.setSavedMovesCount(Game.getMovesCount());
        GameFiles.setSavedPoints(Coin.getPoints());
        GameFiles.setSavedLevels(Level.getLevels());
        GameFiles.setSavedFrozenUses(Ability.getFrozenUses());
        GameFiles.setSavedConfusedUses(Ability.getConfusedUses());
        GameFiles.setSavedIntimidatedUses(Ability.getIntimidatedUses());
        GameFiles.setSavedMonsCurrentRow(Mons.getMonsCurrentRow());
        GameFiles.setSavedMonsCurrentCol(Mons.getMonsCurrentCol());
        GameFiles.saveCoins(Game);
    }

}
