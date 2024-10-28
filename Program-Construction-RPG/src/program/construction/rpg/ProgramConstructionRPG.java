/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package program.construction.rpg;

/**
 *
 * @author rynob
 */
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import javax.swing.SwingUtilities;

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

    public void initializeNewGame() {
        Setup();
        Coin.setPoints(0);
        Game.SetPostion(NewRow, NewCol);
        Coin.generateCoins();
        Mons.setMonsterPosition();

        // Use SwingUtilities.invokeLater to open GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            gameGUI = new GUI(Game, this);
            gameGUI.updateGrid();           // Initial display in GUI
        });
    }

    public void initializeSavedGame() {
        Setup();
        saveFile = saveFileSelect();
        GameFiles.readSaveArrayFile(saveFile);
        saveReadSetup(GameFiles, Game, Coin, Mons, Ability, Level);

        SwingUtilities.invokeLater(() -> {
            gameGUI = new GUI(Game, this);
            gameGUI.updateGrid();           // Display loaded grid in GUI
        });
    }

    public void loadSavedGame(int slot) {
    Setup();
    saveFile = slot;  // Set selected save slot
    GameFiles.readSaveArrayFile(saveFile);  // Load the save data
    saveReadSetup(GameFiles, Game, Coin, Mons, Ability, Level);

    SwingUtilities.invokeLater(() -> {
        gameGUI = new GUI(Game, this);
        gameGUI.updateGrid();  // Display loaded grid in GUI
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
            System.exit(0); // End game
        }

        // Update the GUI to reflect changes in game state
        Level.CheckLevel(); // Check and update level
        Game.MovesCount++; // Increment move count
        Game.printArray(); // Print updated game grid
        gameGUI.updateGrid();
    }

    /**
     * Prompts the user to select a save file.
     *
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
     * Prompts the user to select a game option (new game, saved game, or
     * instructions).
     *
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
     *
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
     *
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