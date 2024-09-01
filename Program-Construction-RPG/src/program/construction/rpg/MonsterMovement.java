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

    private Movement Game; // Reference to the Inialize_array object
    private Random Rand = new Random();
    private int MonsCurrentRow;
    private int MonsCurrentCol;
    private int playerRow;
    private int playerCol;
    private final double CHANCE_TO_MOVE_AWAY = 1;
    private int rowDifference;
    private int colDifference;
    private boolean WasCoin ;
    public MonsterMovement(Movement Game) {
        this.Game = Game;
        this.playerCol = Game.getCurrentCol();
        this.playerRow = Game.getCurrentRow();
        WasCoin = false;
    }

    public void setMonsterPosition() {
        int newCol;
        int newRow;
        this. playerRow = Game.getCurrentRow();
        this. playerCol = Game.getCurrentCol();
        this.rowDifference = MonsCurrentRow - playerRow;
        this.colDifference = MonsCurrentCol - playerCol;
        do {
            newCol = Rand.nextInt(Game.getCols());
            newRow = Rand.nextInt(Game.getRows());
        } while (newCol >= playerCol - 4 && newCol <= playerCol + 4 && newRow >= playerRow - 4 && newRow <= playerRow + 4); // Ensure monster doesn't spawn on player
        this.MonsCurrentRow = newRow;
        this.MonsCurrentCol = newCol;
        checkMons();
    }

  

    public void moveMonster() {
        int turnsRemaining = 0;
        String status = "normal";
        this. playerRow = Game.getCurrentRow();
        this. playerCol = Game.getCurrentCol();
        this.rowDifference = MonsCurrentRow - playerRow;
        this.colDifference = MonsCurrentCol - playerCol;
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
                returnCoing(); // Clear current position
                moveRandomly();
                System.out.println("Monster is confused. " + turnsRemaining + " turns remaining.");
                Abilities.decrementAbilityTurns();
                break;

            case "intimidated":
                returnCoing(); // Clear current position
                moveAwayFromPlayer();
                System.out.println("Monster is intimidated. " + turnsRemaining + " turns remaining.");
                Abilities.decrementAbilityTurns();
                break;

            default:
                returnCoing(); // Clear current position
                moveBasedOnPlayer();
                Abilities.decrementAbilityTurns();
                break;
        }
        checkMons();

        System.out.println("Monster moved to (" + MonsCurrentRow + "," + MonsCurrentCol + ")");//dont need this
    }
    public void checkMons()
    {
        if (MonsCurrentRow == playerRow && MonsCurrentCol == playerCol) {
            System.out.println("Game Over! The monster caught the player!");
            System.exit(0); // End the Game
        }
        // Check if the new position contains a coin or if it's out of bounds before placing the monster
        if (Game.array[MonsCurrentRow][MonsCurrentCol] == 'C') {
            WasCoin = true;
        } 
        Game.array[MonsCurrentRow][MonsCurrentCol] = 'M'; // Place the monster at the new position
    }
    
    public void returnCoing()
    {
        if(WasCoin == true)
        {
            Game.array[MonsCurrentRow][MonsCurrentCol] = 'C';
            WasCoin = false;
        }
        else
        {
            Game.array[MonsCurrentRow][MonsCurrentCol] = '.';
        }
    }
    
    private void moveBasedOnPlayer() {
        boolean hasMoved = false;

        if (Math.abs(rowDifference) <= 5 && Math.abs(colDifference) <= 5) {
            // Decide whether to move towards or away from the player
            if (Rand.nextInt(9) == CHANCE_TO_MOVE_AWAY) {
                moveAwayFromPlayer();
                // Attempt to move away from the player
//                if (Math.abs(rowDifference) > Math.abs(colDifference)) {//check if row diffrenc greater than col
//                    if (rowDifference > 0 && MonsCurrentRow < Game.getRows() - 1) {
//                        MonsCurrentRow++; // Move down away from the player
//                        hasMoved = true;
//                    } else if (rowDifference < 0 && MonsCurrentRow > 0) {
//                        MonsCurrentRow--; // Move up away from the player
//                        hasMoved = true;
//                    }
//                } else {// row isn't greater
//                    if (colDifference > 0 && MonsCurrentCol < Game.getCols() - 1) {
//                        MonsCurrentCol++; // Move right away from the player
//                        hasMoved = true;
//                    } else if (colDifference < 0 && MonsCurrentCol > 0) {
//                        MonsCurrentCol--; // Move left away from the player
//                        hasMoved = true;
//                    }
//                }
            }

            if (!hasMoved) {
                // Attempt to move towards the player
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
            // Random movement if the player is outside the 5-tile radius or movement was not successful
            moveRandomly();
        }
    }

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

    private void moveRandomly() {
        int move = Rand.nextInt(4); // 4 because we have four directions
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
    public int getMonsCurrentRow() {
        return MonsCurrentRow;
    }

    public int getMonsCurrentCol() {
        return MonsCurrentCol;
    }
}
