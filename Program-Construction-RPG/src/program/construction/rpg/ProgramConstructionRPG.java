/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package program.construction.rpg;

/**
 *
 * @author rynob
 */
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import javax.swing.SwingUtilities;

public class ProgramConstructionRPG {

    // Scanner for user input
    private static Scanner scan;

    // Objects for managing game files, player movement, monsters, coins, levels, and abilities
    private static Dbase_Save_Files GameFiles;
    private static Movement Game;
    private static MonsterMovement Mons;
    private static Coins Coin;
    private static Levels Level;
    private static Abilities Ability;
    private static Database_Setup DBase;
    private static GameSaveScreen GSS;
    private GUI gameGUI;

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
    private static String saveName;
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
        GameFiles = new Dbase_Save_Files();
        Game = new Movement();
        Mons = new MonsterMovement(Game);
        Coin = new Coins(Game);
        Ability = new Abilities();
        Level = new Levels(Game, Coin, Mons, Ability);
        leave = false;
        DBase = new Database_Setup();
    }

    public void initializeNewGame() {
        Setup();
        Coin.setPoints(0);
        Game.SetPostion(NewRow, NewCol);
        Coin.generateCoins();
        Mons.setMonsterPosition();
        // Use SwingUtilities.invokeLater to open GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            gameGUI = new GUI(Game, this);
            if (gameGUI != null) {
                gameGUI.updateGrid(50);
            }
        });
    }

    public void initializeSavedGame() {
        Setup();

        saveFile = saveFileSelect();

        saveReadSetup(GameFiles, Game, Coin, Mons, Ability, Level, DBase);

        SwingUtilities.invokeLater(() -> {
            gameGUI = new GUI(Game, this);
            if (gameGUI != null) {
                gameGUI.updateGrid(50);
            }
        });
    }

    public void loadSavedGame(int slot) {
        Setup();

        saveFile = slot;  // Set selected save slot
        saveName = "Save_" + saveFile;

        saveReadSetup(GameFiles, Game, Coin, Mons, Ability, Level, DBase);

        SwingUtilities.invokeLater(() -> {
            gameGUI = new GUI(Game, this);
            if (gameGUI != null) {
                gameGUI.updateGrid(50);
            }
        });
    }

    public void processCommand(char input) {
        // Process input for movement or ability use

        switch (input) {

            case 'w':
                Coin.checkCoins('w');
                Game.moveUp();
                Mons.moveMonster();
                System.out.println("a key has been detected");
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
            case 'f':
            case 'g':
            case 'c':
                Ability.sendAbilityCommand(input);
                break;
            case 'q':
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input. Use WASD keys.");
                break;
        }

        // Move monster after player's action
        Mons.moveMonster();

        // Check if the monster has caught the player
        if (Mons.getMonsCurrentRow() == Game.getCurrentRow() && Mons.getMonsCurrentCol() == Game.getCurrentCol()) {
            System.out.println("The monster has caught you!");

            // Open the GameSaveScreen without closing the main window
        SwingUtilities.invokeLater(() -> {
            DoOrDontSaveScreen saveScreen = new DoOrDontSaveScreen(this,Game);
            saveScreen.setVisible(true);
            gameGUI.dispose();
        });
        }

        // Update the GUI to reflect changes in game state
        Level.CheckLevel(); // Check and update level
        Game.MovesCount++; // Increment move count
        Game.printArray(); // Print updated game grid
        gameGUI.updateGrid(50);
    }

    /**
     * Prompts the user to select a save file.
     *
     * @return The selected save file number.
     */
    public static int saveFileSelect() {
        Setup();
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
     * Prompts the user to select a game option (new game, saved game, or
     * instructions).
     *
     * @return The selected game option.
     */
    public static String gameSetSelect() {
        Setup();
        System.out.println("New game     (N) \nSaved Game   (S) \nInstructions (I)");
        String value = scan.nextLine().trim().toLowerCase();
        while (!validOptionsGameSets.contains(value)) {
            System.out.println("Invalid input. Please select:\nNew game     (N) \nSaved Game   (S) \nInstructions (I)");
            value = scan.nextLine().trim();
        }
        return value;
    }
    
    public void saveGame(Movement Game, int slot) throws SQLException {

                saveDataSetup(GameFiles, Game, Coin, Mons, Ability, Level, DBase,slot);
        GSS.dispose();
    }
    
    /**
     * Sets up the game state from the loaded file data.
     *
     * @param GameFiles The file manager object.
     * @param Game The movement manager object.
     * @param Coin The coins manager object.
     * @param Mons The monster movement manager object.
     * @param Ability The abilities manager object.
     * @param Level The level manager object.
     * @param DBase the Database_Setup object
     */
    public static void saveReadSetup(Dbase_Save_Files GameFiles, Movement Game, Coins Coin, MonsterMovement Mons, Abilities Ability, Levels Level, Database_Setup DBase) {

        int SavedRow = DBase.getValue(saveName, "Row");
        System.out.println("Retrieved Row from DB: " + SavedRow);
        int SavedCol = DBase.getValue(saveName, "Col");
        System.out.println("Retrieved Col  from DB: " + SavedCol);
        Coin.setPoints(DBase.getValue(saveName, "Points"));
        Level.setLevels(DBase.getValue(saveName, "Levels"));
        Game.setMovesCount(DBase.getValue(saveName, "MovesCount"));
        Ability.setFrozenUses(DBase.getValue(saveName, "FrozenUses"));
        Ability.setConfusedUses(DBase.getValue(saveName, "ConfusedUses"));
        Ability.setIntimidatedUses(DBase.getValue(saveName, "IntimidatedUses"));
        Mons.setMonsCurrentRow(DBase.getValue(saveName, "MonsCurrentRow"));
        Mons.setMonsCurrentCol(DBase.getValue(saveName, "MonsCurrentCol"));
        Game.SetPostion(SavedRow, SavedCol);
        GameFiles.setArrayCoins(Game, Coin, saveName);
        Mons.checkMons();
    }

    /**
     * Prepares game data to be saved into a file.
     *
     * @param GameFiles The file manager object.
     * @param Game The movement manager object.
     * @param Coin The coins manager object.
     * @param Mons The monster movement manager object.
     * @param Ability The abilities manager object.
     * @param Level The level manager object.
     * @param DBase the Database_Setup objectdas
     * @param slot
     * @throws java.sql.SQLException
     */
    public static void saveDataSetup(Dbase_Save_Files GameFiles, Movement Game, Coins Coin, MonsterMovement Mons, Abilities Ability, Levels Level, Database_Setup DBase,int slot) throws SQLException {
        
        GameFiles.saveToDatabase(slot);
        saveName = "save_"+slot;
        DBase.insertSaveData(saveName, "Row", Game.getCurrentRow());
        DBase.insertSaveData(saveName, "Col", Game.getCurrentCol());
        DBase.insertSaveData(saveName, "Points", Coin.getPoints());
        DBase.insertSaveData(saveName, "Levels", Level.getLevels());
        DBase.insertSaveData(saveName, "MovesCount", Game.getMovesCount());
        DBase.insertSaveData(saveName, "FrozenUses", Abilities.getFrozenUses());
        DBase.insertSaveData(saveName, "ConfusedUses", Abilities.getConfusedUses());
        DBase.insertSaveData(saveName, "IntimidatedUses", Abilities.getIntimidatedUses());
        DBase.insertSaveData(saveName, "MonsCurrentRow", Mons.getMonsCurrentRow());
        DBase.insertSaveData(saveName, "MonsCurrentCol", Mons.getMonsCurrentCol());
        GameFiles.saveCoins(Game, saveName);
        System.out.println("all data saved to database");
        GameFiles.saveDatabaseToFile(slot);
        System.out.println("all data saved to txt file");
    }
}
