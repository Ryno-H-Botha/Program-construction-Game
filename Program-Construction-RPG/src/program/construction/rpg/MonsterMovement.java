/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;

/**
 *
 * @author rynob
 */
import java.util.*;
import java.io.*;
import program.construction.rpg.Inialize_array;

public class MonsterMovement {

    private Inialize_array pos; // Reference to the Inialize_array object
    private Random rad = new Random();
    private int currentRow;
    private int currentCol;

    private final double CHANCE_TO_MOVE_AWAY = 0.6;

    public MonsterMovement(Inialize_array pos) {
        this.pos = pos;
        setPosition();
        placeMon();
    }

    public void setPosition() {
        int newCol;
        int newRow;
        int playerRow = pos.getCurrentRow();
        int playerCol = pos.getCurrentCol();
        do {
            newCol = rad.nextInt(pos.getCols());
            newRow = rad.nextInt(pos.getRows());
        } while (newCol >= playerCol - 4 && newCol <= playerCol + 4 && newRow >= playerRow - 4 && newRow <= playerRow + 4); // Ensure monster doesn't spawn on player

        this.currentRow = newRow;
        this.currentCol = newCol;
    }

    public void placeMon() {
        // Only place the monster if it's not on the player's position
        if (currentRow == pos.getCurrentRow() && currentCol == pos.getCurrentCol()) {
            System.out.println("Monster is on the player's position.");
        } else {
            pos.array[currentRow][currentCol] = 'M';
        }
    }

    public void moveMonster() {
        int turnsRemaining = 0;
        String status = "normal";

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

        switch (status) {
            case "frozen":
                System.out.println("Monster is frozen and cannot move. " + turnsRemaining + " turns remaining.");
                Abilities.decrementAbilityTurns();
                break;

            case "confused":
                pos.array[currentRow][currentCol] = '.'; // Clear current position
                moveRandomly();
                System.out.println("Monster is confused. " + turnsRemaining + " turns remaining.");
                Abilities.decrementAbilityTurns();
                break;

            case "intimidated":
                pos.array[currentRow][currentCol] = '.'; // Clear current position
                moveAwayFromPlayer();
                System.out.println("Monster is intimidated. " + turnsRemaining + " turns remaining.");
                Abilities.decrementAbilityTurns();
                break;

            default:
                pos.array[currentRow][currentCol] = '.'; // Clear current position
                moveBasedOnPlayer();
                Abilities.decrementAbilityTurns();
                break;
        }

        int playerRow = pos.getCurrentRow();
        int playerCol = pos.getCurrentCol();

        if (currentRow == playerRow && currentCol == playerCol) {
            System.out.println("Game Over! The monster caught the player!");
            System.exit(0); // End the game
        }

        // Check if the new position contains a coin or if it's out of bounds before placing the monster
        if (pos.array[currentRow][currentCol] != 'C') {
            pos.array[currentRow][currentCol] = 'M'; // Place the monster at the new position
        } else {
            System.out.println("Monster cannot move to a coin's position or player position.");
        }

        System.out.println("Monster moved to (" + currentRow + "," + currentCol + ")");
    }

    private void moveBasedOnPlayer() {
        int playerRow = pos.getCurrentRow();
        int playerCol = pos.getCurrentCol();
        int rowDifference = currentRow - playerRow;
        int colDifference = currentCol - playerCol;

        boolean hasMoved = false;

        if (Math.abs(rowDifference) <= 5 && Math.abs(colDifference) <= 5) {
            // Decide whether to move towards or away from the player
            if (rad.nextDouble() < CHANCE_TO_MOVE_AWAY) {
                // Attempt to move away from the player
                if (Math.abs(rowDifference) > Math.abs(colDifference)) {
                    if (rowDifference > 0 && currentRow < pos.getRows() - 1) {
                        currentRow++; // Move down away from the player
                        hasMoved = true;
                    } else if (rowDifference < 0 && currentRow > 0) {
                        currentRow--; // Move up away from the player
                        hasMoved = true;
                    }
                } else {
                    if (colDifference > 0 && currentCol < pos.getCols() - 1) {
                        currentCol++; // Move right away from the player
                        hasMoved = true;
                    } else if (colDifference < 0 && currentCol > 0) {
                        currentCol--; // Move left away from the player
                        hasMoved = true;
                    }
                }
            }

            if (!hasMoved) {
                // Attempt to move towards the player
                if (Math.abs(rowDifference) > Math.abs(colDifference)) {
                    if (rowDifference > 0 && currentRow > 0) {
                        currentRow--; // Move up towards the player
                    } else if (rowDifference < 0 && currentRow < pos.getRows() - 1) {
                        currentRow++; // Move down towards the player
                    }
                } else {
                    if (colDifference > 0 && currentCol > 0) {
                        currentCol--; // Move left towards the player
                    } else if (colDifference < 0 && currentCol < pos.getCols() - 1) {
                        currentCol++; // Move right towards the player
                    }
                }
            }
        } else {
            // Random movement if the player is outside the 5-tile radius or movement was not successful
            moveRandomly();
        }
    }

    private void moveAwayFromPlayer() {
        int playerRow = pos.getCurrentRow();
        int playerCol = pos.getCurrentCol();
        int rowDifference = currentRow - playerRow;
        int colDifference = currentCol - playerCol;

        if (Math.abs(rowDifference) > Math.abs(colDifference)) {
            if (rowDifference > 0 && currentRow < pos.getRows() - 1) {
                currentRow++; // Move down away from the player
            } else if (rowDifference < 0 && currentRow > 0) {
                currentRow--; // Move up away from the player
            }
        } else {
            if (colDifference > 0 && currentCol < pos.getCols() - 1) {
                currentCol++; // Move right away from the player
            } else if (colDifference < 0 && currentCol > 0) {
                currentCol--; // Move left away from the player
            }
        }
    }

    private void moveRandomly() {
        int move = rad.nextInt(4); // 4 because we have four directions
        switch (move) {
            case 0: // Move up
                if (currentRow > 0) {
                    currentRow--;
                }
                break;
            case 1: // Move down
                if (currentRow < pos.getRows() - 1) {
                    currentRow++;
                }
                break;
            case 2: // Move left
                if (currentCol > 0) {
                    currentCol--;
                }
                break;
            case 3: // Move right
                if (currentCol < pos.getCols() - 1) {
                    currentCol++;
                }
                break;
        }
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }
}
