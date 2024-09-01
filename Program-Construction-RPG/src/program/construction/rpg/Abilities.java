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

    private static boolean isFrozen = false;
    private static boolean isConfused = false;
    private static boolean isIntimidated = false;
    private static int frozenTurns = 0;
    private static int confusedTurns = 0;
    private static int intimidatedTurns = 0;
    private static int frozenUses = 0;
    private static int confusedUses = 0;
    private static int intimidatedUses = 0;

    public static void freeze() {
        isFrozen = true;
        frozenTurns = 5; // Monster is frozen for 5 turns
    }

    public static void intimidation() {
        isIntimidated = true;
        intimidatedTurns = 5; // Monster is intimidated for 5 turns
    }

    public static void confusion() {
        isConfused = true;
        confusedTurns = 5; // Monster is confused for 5 turns
    }

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
    
    public static int getFrozenUses() {
        return frozenUses;
    }

    public static int getConfusedUses() {
        return confusedUses;
    }

    public static int getIntimidatedUses() {
        return intimidatedUses;
    }

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

}
