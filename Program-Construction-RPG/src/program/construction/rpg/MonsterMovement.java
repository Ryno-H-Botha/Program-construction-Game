/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;

/**
 *
 * @author Илья Ilya
 */
import java.util.*;
import java.io.*;
import javax.swing.SwingUtilities;
import program.construction.rpg.Inialize_array;

public class MonsterMovement {

    private Movement Game; // Reference to the Movement object managing the game grid
    private Random Rand = new Random(); // Random number generator for movement
    private int MonsCurrentRow; // Current row position of the monster
    private int MonsCurrentCol; // Current column position of the monster
    private int playerRow; // Current row position of the player
    private int playerCol; // Current column position of the player
    private final double CHANCE_TO_MOVE_AWAY = 1; // Chance for the monster to move away from the player
    private int rowDifference; // Row difference between the monster and player
    private int colDifference; // Column difference between the monster and player
    private boolean WasCoin; // Flag to check if the monster was on a coin position
    private static GameSaveScreen safeScr;

    /**
     * Constructor for the MonsterMovement class.
     *
     * @param Game The Movement object to access the game grid and player
     * position.
     */
    public MonsterMovement(Movement Game) {
        this.Game = Game;
        this.playerCol = Game.getCurrentCol();
        this.playerRow = Game.getCurrentRow();
        WasCoin = false;
    }

    /**
     * Sets a new position for the monster ensuring it doesn't spawn near the
     * player.
     */
    public void setMonsterPosition() {
        int newCol;
        int newRow;
        this.playerRow = Game.getCurrentRow();
        this.playerCol = Game.getCurrentCol();
        this.rowDifference = MonsCurrentRow - playerRow;
        this.colDifference = MonsCurrentCol - playerCol;
        do {
            newCol = Rand.nextInt(Game.getCols()); // Random column
            newRow = Rand.nextInt(Game.getRows()); // Random row
        } while (newCol >= playerCol - 4 && newCol <= playerCol + 4 && newRow >= playerRow - 4 && newRow <= playerRow + 4); // Ensure monster doesn't spawn near the player
        this.MonsCurrentRow = newRow;
        this.MonsCurrentCol = newCol;
        checkMons(); // Place the monster in the new position
    }

    /**
     * Moves the monster based on its status (frozen, confused, intimidated) or
     * defaults to normal movement.
     */
    public void moveMonster() {
        int turnsRemaining = 0;
        String status = "normal";
        this.playerRow = Game.getCurrentRow();
        this.playerCol = Game.getCurrentCol();
        this.rowDifference = MonsCurrentRow - playerRow;
        this.colDifference = MonsCurrentCol - playerCol;

        // Determine monster's status based on abilities
        if (Abilities.isMonsterFrozen()) {
            status = "frozen";
            turnsRemaining = Abilities.getFrozenTurns();
        }
        if (Abilities.isMonsterConfused()) {
            status = "confused";
            turnsRemaining = Abilities.getConfusedTurns();
        }
        if (Abilities.isMonsterIntimidated()) {
            status = "intimidated";
            turnsRemaining = Abilities.getIntimidatedTurns();
        }

        // Move monster based on its status
        switch (status) {
            case "frozen":
                System.out.println("Monster is frozen and cannot move. " + turnsRemaining + " turns remaining.");
                Abilities.decrementAbilityTurns(); // Decrement ability turns
                break;

            case "confused":
                returnCoing(); // Clear current position
                moveRandomly(); // Move randomly
                System.out.println("Monster is confused. " + turnsRemaining + " turns remaining.");
                Abilities.decrementAbilityTurns(); // Decrement ability turns
                break;

            case "intimidated":
                returnCoing(); // Clear current position
                moveAwayFromPlayer(); // Move away from the player
                System.out.println("Monster is intimidated. " + turnsRemaining + " turns remaining.");
                Abilities.decrementAbilityTurns(); // Decrement ability turns
                break;

            default:
                returnCoing(); // Clear current position
                moveBasedOnPlayer(); // Move based on the player's position
                Abilities.decrementAbilityTurns(); // Decrement ability turns
                break;
        }
        checkMons(); // Place monster at the new position
    }

    /**
     * Checks the monster's position to determine if it has caught the player.
     */
    public void checkMons() {       
        // Check if the new position contains a coin or if it's out of bounds before placing the monster
        if (Game.array[MonsCurrentRow][MonsCurrentCol] == 'C') {
            WasCoin = true;
        }
        Game.array[MonsCurrentRow][MonsCurrentCol] = 'M'; // Place the monster at the new position
    }

    /**
     * Restores the previous coin position if the monster was on a coin.
     */
    public void returnCoing() {
        if (WasCoin) {
            Game.array[MonsCurrentRow][MonsCurrentCol] = 'C'; // Restore the coin
            WasCoin = false;
        } else {
            Game.array[MonsCurrentRow][MonsCurrentCol] = '.'; // Clear the monster's previous position
        }
    }

    /**
     * Moves the monster based on its proximity to the player.
     */
    private void moveBasedOnPlayer() {
        boolean hasMoved = false;

        // Move the monster towards or away from the player based on their relative position
        if (Math.abs(rowDifference) <= 5 && Math.abs(colDifference) <= 5) {
            if (Rand.nextInt(9) == CHANCE_TO_MOVE_AWAY) {
                moveAwayFromPlayer(); // Move away from the player
            }
            if (!hasMoved) {
                if (Math.abs(rowDifference) > Math.abs(colDifference)) {
                    if (rowDifference > 0 && MonsCurrentRow > 0) {
                        MonsCurrentRow--; // Move up towards the player
                    } else if (rowDifference < 0 && MonsCurrentRow < Game.getRows() - 1) {
                        MonsCurrentRow++; // Move down towards the player
                    }
                } else {
                    if (colDifference > 0 && MonsCurrentCol > 0) {
                        MonsCurrentCol--; // Move left towards the player
                    } else if (colDifference < 0 && MonsCurrentCol < Game.getCols() - 1) {
                        MonsCurrentCol++; // Move right towards the player
                    }
                }
            }
        } else {
            moveRandomly(); // Random movement if the player is outside the 5-tile radius or movement was not successful
        }
    }

    /**
     * Moves the monster away from the player.
     */
    private void moveAwayFromPlayer() {
        if (Math.abs(rowDifference) > Math.abs(colDifference)) {
            if (rowDifference > 0 && MonsCurrentRow < Game.getRows() - 1) {
                MonsCurrentRow++; // Move down away from the player
            } else if (rowDifference < 0 && MonsCurrentRow > 0) {
                MonsCurrentRow--; // Move up away from the player
            }
        } else {
            if (colDifference > 0 && MonsCurrentCol < Game.getCols() - 1) {
                MonsCurrentCol++; // Move right away from the player
            } else if (colDifference < 0 && MonsCurrentCol > 0) {
                MonsCurrentCol--; // Move left away from the player
            }
        }
    }

    /**
     * Moves the monster randomly.
     */
    private void moveRandomly() {
        int move = Rand.nextInt(4); // 4 directions
        switch (move) {
            case 0: // Move up
                if (MonsCurrentRow > 0) {
                    MonsCurrentRow--;
                }
                break;
            case 1: // Move down
                if (MonsCurrentRow < Game.getRows() - 1) {
                    MonsCurrentRow++;
                }
                break;
            case 2: // Move left
                if (MonsCurrentCol > 0) {
                    MonsCurrentCol--;
                }
                break;
            case 3: // Move right
                if (MonsCurrentCol < Game.getCols() - 1) {
                    MonsCurrentCol++;
                }
                break;
        }
    }

    // Getters and setters for monster's position
    public int getMonsCurrentRow() {
        return MonsCurrentRow;
    }

    public int getMonsCurrentCol() {
        return MonsCurrentCol;
    }

    public void setMonsCurrentRow(int Row) {
        MonsCurrentRow = Row;
    }

    public void setMonsCurrentCol(int Col) {
        MonsCurrentCol = Col;
    }
}
