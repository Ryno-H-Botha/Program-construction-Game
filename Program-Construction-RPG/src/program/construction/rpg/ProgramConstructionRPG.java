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
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ProgramConstructionRPG {

    // Scanner for user input
    private static Scanner scan;

    // Objects for managing game files, player movement, monsters, coins, levels, and abilities
    private static File_read_write GameFiles;
    private static Movement Game;
    private static MonsterMovement Mons;
    private static Coins Coin;
    private static Levels Level;
    private static Abilities Ability;

    // Flag to determine when to exit the game loop
    private static boolean leave;

    // Game grid dimensions and initial player position
    public static int ROWS = Inialize_array.getRows();
    public static int COLS = Inialize_array.getCols();
    public static int NewRow = ROWS / 2;
    public static int NewCol = COLS / 2;

    // The game grid
    public static char[][] array = new char[ROWS][COLS];
    
    // File for saving game state
    private static int saveFile;
    private static boolean saveSelected = false;
    
    // Sets of valid inputs for save and game options
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
    
    /**
     * Initializes the game components and the scanner.
     */
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

        // Set up the game components
        Setup();

        // Select game start option
        String start = gameSetSelect();

        if (start.equals("i")) {
            GameFiles.readInstArrayFile(); // Read initial game configuration
            start = scan.nextLine().toLowerCase();
            // Validate user input for game start option
            while (!(start.equals("n") || start.equals("s")) ||start.equals("i")) {
                System.out.println("Invalid input try again.");
                start = scan.nextLine().toLowerCase();
            }
        }

        // Handle the selected game option
        switch (start) {
            case "n":
                Coin.setPoints(0); // Reset points for a new game
                Game.SetPostion(NewRow, NewCol); // Set player position
                Coin.generateCoins(); // Generate initial coins
                Mons.setMonsterPosition(); // Set monster positions
                Game.printArray(); // Print the initial game grid
                break;
            case "s":
                saveSelected = true;
                saveFile = saveFileSelect(); // Select save file
                GameFiles.readSaveArrayFile(saveFile); // Load saved game state
                saveReadSetup(GameFiles, Game, Coin, Mons, Ability, Level); // Setup loaded state
                Game.printArray(); // Print the loaded game grid
                break;
        }

        // Main game loop
        while (true) {
            System.out.println("Use arrow keys (WASD) to move '@' or 'q' to quit Then press Enter to confirm:");
            char input = scan.next().charAt(0);

            // Handle user input for movement and abilities
            switch (input) {
                case 'w': // Move up
                    Coin.checkCoins('w'); // Check for coins
                    Game.moveUp(); // Move player
                    Mons.moveMonster(); // Move monsters
                    break;
                case 's': // Move down
                    Coin.checkCoins('s'); // Check for coins
                    Game.moveDown(); // Move player
                    Mons.moveMonster(); // Move monsters
                    break;
                case 'a': // Move left
                    Coin.checkCoins('a'); // Check for coins
                    Game.moveLeft(); // Move player
                    Mons.moveMonster(); // Move monsters
                    break;
                case 'd': // Move right
                    Coin.checkCoins('d'); // Check for coins
                    Game.moveRight(); // Move player
                    Mons.moveMonster(); // Move monsters
                    break;
                case 'q': // Quit game
                    System.out.println("Exiting...");
                    leave = true;
                    break;
                default:
                    // Handle special abilities or invalid input
                    if (input == 'f' || input == 'g' || input == 'c') {
                        Ability.sendAbilityCommand(input);
                    } else {
                        System.out.println("Invalid input. Use WASD keys.");
                    }
                    break;
            }

            if (leave) {
                break;
            }

            // Display game status and update
            System.out.println("Points : " + Coin.getPoints() + "  Level: " + Level.getLevels() + "  Moves: " + Game.getMovesCount());
            System.out.println("Frozen (F) unused : " + Ability.getFrozenUses() + " Confused (C) unused : " + Ability.getConfusedUses() + " Intimidated (G) unused : " + Ability.getIntimidatedUses());
            Level.CheckLevel(); // Check and update level
            Game.MovesCount++; // Increment move count
            Game.printArray(); // Print updated game grid
        }

        // Handle save or delete action
        System.out.println("Save (S)\nDelete (D)");
        char SaveData = scan.next().charAt(0);

        switch (SaveData) {
            case 's': // Save game state
                if (saveSelected) {
                    GameFiles.readSaveArrayFile(saveFile); // Load existing save file
                    saveDataSetup(GameFiles, Game, Coin, Mons, Ability, Level); // Update save data
                    GameFiles.writeSave(saveFile); // Write updated save file
                    saveSelected = false; // Reset save selection flag
                } else {
                    saveFile = saveFileSelect(); // Select save file
                    GameFiles.readSaveArrayFile(saveFile); // Load selected save file
                    saveDataSetup(GameFiles, Game, Coin, Mons, Ability, Level); // Update save data
                    GameFiles.writeSave(saveFile); // Write updated save file
                }
                System.out.println("Data has been Saved.");
                break;
            case 'd': // Delete game data
                System.out.println("Data deleted.");
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }
    }

    /**
     * Prompts the user to select a save file.
     * @return The selected save file number.
     */
    public static int saveFileSelect() {
        String value = "";
        System.out.println("Which file would you like to use?\nSave (1)\nSave (2)\nSave (3)");
        while (value.isEmpty()) {
            value = scan.nextLine().trim();
        }
        System.out.println("Save selected: Save_" + value); // Debug print
        while (!validOptionsSaves.contains(value)) {
            System.out.println("Invalid input. Please select:\nSave (1)\nSave (2)\nSave (3)");
            value = scan.nextLine().trim();
            System.out.println("Save selected: Save_" + value);
        }
        return Integer.parseInt(value);
    }

    /**
     * Prompts the user to select a game option (new game, saved game, or instructions).
     * @return The selected game option.
     */
    public static String gameSetSelect() {
        System.out.println("New game     (N) \nSaved Game   (S) \nInstructions (I)");
        String value = scan.nextLine().trim().toLowerCase();
        while (!validOptionsGameSets.contains(value)) {
            System.out.println("Invalid input. Please select:\nNew game     (N) \nSaved Game   (S) \nInstructions (I)");
            value = scan.nextLine().trim();
        }
        return value;
    }
   
    /**
     * Sets up the game state from the loaded file data.
     * @param GameFiles The file manager object.
     * @param Game The movement manager object.
     * @param Coin The coins manager object.
     * @param Mons The monster movement manager object.
     * @param Ability The abilities manager object.
     * @param Level The level manager object.
     */
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

    /**
     * Prepares game data to be saved into a file.
     * @param GameFiles The file manager object.
     * @param Game The movement manager object.
     * @param Coin The coins manager object.
     * @param Mons The monster movement manager object.
     * @param Ability The abilities manager object.
     * @param Level The level manager object.
     */
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

