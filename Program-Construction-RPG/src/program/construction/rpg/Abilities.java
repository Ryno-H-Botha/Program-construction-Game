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
    private static int frozenUses = 3;
    private static int confusedUses = 3;
    private static int intimidatedUses = 3;

    public static void freeze() {
        isFrozen = true;
        frozenTurns = 3; // Monster is frozen for 5 turns
    }

    public static void intimidation() {
        isIntimidated = true;
        intimidatedTurns = 2; // Monster is intimidated for 2 turns
    }

    public static void confusion() {
        isConfused = true;
        confusedTurns = 3; // Monster is confused for 3 turns
    }

    public static void sendAbilityCommand(char ability) {

        switch (ability) {
            case 'f':
                if (frozenUses <= 0) {
                    System.out.println("no uses left");
                    delay();
                } else {
                    freeze(); // Call freeze ability
                    frozenUses--;
                    System.out.println("frozen Uses left =" + frozenUses);
                }
                break;
            case 'g':
                if (intimidatedUses <= 0) {
                    System.out.println("no uses left");
                    delay();
                } else {
                    intimidation(); // Call glue ability
                    intimidatedUses--;
                    System.out.println("intimidated Uses left =" + intimidatedUses);
                }

                break;
            case 'c':
                if (confusedUses <= 0) {
                    System.out.println("no uses left");
                    delay();
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

    private static void delay() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    public static int getFrozenUses() {
        return frozenUses;
    }

    public static int getConfusedUses() {
        return confusedUses;
    }

    public static int getIntimidatedUses() {
        return intimidatedUses;
    }

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
