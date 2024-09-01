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
    public static int ROWS = Inialize_array.getRows();
    public static int COLS = Inialize_array.getCols();
    public static int NewRow = ROWS / 2;
    public static int NewCol = COLS / 2;
    public static String inputfile = "./resources/input.txt";
    public static String savefile = "./resources/Save.txt";
    public static String instfile = "./resources/Inst.txt";
    public static char[][] array = new char[ROWS][COLS];
    public static char ability;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        File_read_write NGame = new File_read_write();
        Movement game = new Movement(NewRow, NewCol);
        Coins cn = new Coins(game);
        Levels level = new Levels(game, cn);

        Inialize_array arrayInitializer = new Inialize_array(NewRow, NewCol);

        NGame.readSaveArrayFile();

        int leavue = 0;
        System.out.println("New game     (n) \nSaved Game   (s) \nInstructions (I)");
        String start = scan.nextLine().toLowerCase();

        if (start.equals("i")) {
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
                level.setLevels(NGame.getSavedLevels());
                int SavedRow = NGame.getSavedRows();
                int SavedCol = NGame.getSavedCols();
                game.setMovesCount(NGame.getSavedMovesCount());
                game.SetPostion(SavedRow, SavedCol);
                NGame.setArrayCoins(game, cn);
                game.printArray();
                break;
        }

        game.printArray(); // Print the array to show the monster

        while (true) {
            System.out.println("Use arrow keys (WASD) to move '@' or 'q' to quit Then press Enter to confiem:");
            char input = scan.next().charAt(0);
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
                    if (input == 'f' || input == 'g' || input == 'c') {
                        ability = input;
                        sendAbilityCommand(ability);
                    } else {
                        System.out.println("Invalid input. Use WASD keys.");
                    }
                    break;
            }
            if (leavue == 1) {
                break;
            }

            System.out.println("Points : " + cn.getPoints() + "  Level: " + level.getLevels() + "  Moves: " + game.getMovesCount());
            level.CheckLevel();
            game.MovesCount++;
            game.printArray();
        }

        System.out.println(
                "Save (S)\nDelete (D)");
        char SaveData = scan.next().charAt(0);

        System.out.println(
                "You entered: " + SaveData);

        switch (SaveData) {
            case 's':
                NGame.setSavedRows(game.getCurrentRow());
                NGame.setSavedCols(game.getCurrentCol());
                NGame.setSavedMovesCount(game.getMovesCount());
                NGame.setSavedPoints(cn.getPoints());
                NGame.setSavedLevels(level.getLevels());

                NGame.saveCoins(game);
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

    public void SaveSetup() {
    }

    public static void sendAbilityCommand(char abili) {
        Abilities ab = new Abilities();

        switch (ability) {
            case 'f':
                ab.freeze(); // Call freeze ability
                break;
            case 'g':
                ab.intimidation(); // Call glue ability
                break;
            case 'c':
                ab.confusion(); // Call confusion ability
                break;
            default:
                System.out.println("Invalid ability command.");
                break;
        }
    }
}
