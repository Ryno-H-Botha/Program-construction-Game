/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;

/**
 *
 * @author Илья Ilya
 */
public class Abilities {

    // State variables to track the status and turns of abilities
    private static boolean isFrozen = false;
    private static boolean isConfused = false;
    private static boolean isIntimidated = false;
    private static int frozenTurns = 0;
    private static int confusedTurns = 0;
    private static int intimidatedTurns = 0;
    private static int frozenUses = 3;
    private static int confusedUses = 3;
    private static int intimidatedUses = 3;

    /**
     * Applies the freeze ability to the monster, setting it to be frozen for 3 turns.
     */
    public static void freeze() {
        isFrozen = true;
        frozenTurns = 3; // Monster is frozen for 3 turns
    }

    /**
     * Applies the intimidation ability to the monster, setting it to be intimidated for 2 turns.
     */
    public static void intimidation() {
        isIntimidated = true;
        intimidatedTurns = 2; // Monster is intimidated for 2 turns
    }

    /**
     * Applies the confusion ability to the monster, setting it to be confused for 3 turns.
     */
    public static void confusion() {
        isConfused = true;
        confusedTurns = 3; // Monster is confused for 3 turns
    }

    /**
     * Executes the ability command based on the input character.
     * @param ability The character representing the ability command.
     */
    public static void sendAbilityCommand(char ability) {

        switch (ability) {
            case 'f': // Freeze ability
                if (frozenUses <= 0) {
                    System.out.println("no uses left");
                    delay(); // Wait for a short period
                } else {
                    freeze(); // Call freeze ability
                    frozenUses--;
                    System.out.println("frozen Uses left =" + frozenUses);
                }
                break;
            case 'g': // Intimidation ability
                if (intimidatedUses <= 0) {
                    System.out.println("no uses left");
                    delay(); // Wait for a short period
                } else {
                    intimidation(); // Call intimidation ability
                    intimidatedUses--;
                    System.out.println("intimidated Uses left =" + intimidatedUses);
                }
                break;
            case 'c': // Confusion ability
                if (confusedUses <= 0) {
                    System.out.println("no uses left");
                    delay(); // Wait for a short period
                } else {
                    confusion(); // Call confusion ability
                    confusedUses--;
                    System.out.println("confused Uses left =" + confusedUses);
                }
                break;
            default:
                System.out.println("Invalid ability command.");
                break;
        }
    }

    /**
     * Decrements the remaining turns for each ability and updates the status.
     * This should be called at each turn to update the state of the abilities.
     */
    public static void decrementAbilityTurns() {
        if (frozenTurns > 0) {
            frozenTurns--;
            if (frozenTurns == 0) {
                isFrozen = false;  // Reset the frozen state
            }
        }
        if (confusedTurns > 0) {
            confusedTurns--;
            if (confusedTurns == 0) {
                isConfused = false; // Reset the confused state
            }
        }
        if (intimidatedTurns > 0) {
            intimidatedTurns--;
            if (intimidatedTurns == 0) {
                isIntimidated = false; // Reset the intimidated state
            }
        }
    }

    /**
     * Pauses the execution for 800 milliseconds.
     */
    private static void delay() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Getters for the status and turns of the abilities
    public static boolean isMonsterFrozen() {
        return isFrozen;
    }

    public static boolean isMonsterConfused() {
        return isConfused;
    }

    public static boolean isMonsterIntimidated() {
        return isIntimidated;
    }

    public static int getFrozenTurns() {
        return frozenTurns;
    }

    public static int getConfusedTurns() {
        return confusedTurns;
    }

    public static int getIntimidatedTurns() {
        return intimidatedTurns;
    }

    public static int getFrozenUses() {
        return frozenUses;
    }

    public static int getConfusedUses() {
        return confusedUses;
    }

    public static int getIntimidatedUses() {
        return intimidatedUses;
    }

    // Setters for the number of uses for each ability
    public void setFrozenUses(int uses) {
        frozenUses = uses;
    }

    public void setConfusedUses(int uses) {
        confusedUses = uses;
    }

    public void setIntimidatedUses(int uses) {
        intimidatedUses = uses;
    }

}
