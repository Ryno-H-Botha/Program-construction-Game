/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;

/**
 *
 * @author rynob
 */
public class Levels {

    // Instance variables
    private Movement Game;  // Game state management
    private Coins Coin;     // Coin management
    private MonsterMovement Mons;  // Monster movement management
    private Abilities Ability;     // Ability management
    private int Levels;     // Current level

    /**
     * Constructor for the Levels class.
     * @param game The Movement object for game state management.
     * @param cn The Coins object for managing coins.
     * @param mons The MonsterMovement object for managing monster positions.
     * @param Ability The Abilities object for managing abilities.
     */
    public Levels(Movement game, Coins cn, MonsterMovement mons, Abilities Ability) {
        this.Game = game;
        this.Coin = cn;
        this.Mons = mons;
        this.Ability = Ability;
    }

    /**
     * Gets the current level.
     * @return The current level.
     */
    public int getLevels() {
        return this.Levels;
    }

    /**
     * Sets the current level.
     * @param Levels The level to set.
     */
    public void setLevels(int Levels) {
        this.Levels = Levels;
    }

    /**
     * Checks if the level is complete and advances to the next level if so.
     */
    public void CheckLevel() {
        // Check if all coins are collected and player is at the new level's start position
        if (Coin.CoinCount == 0 && Game.currentRow == Game.NewRow && Game.currentCol == Game.NewCol) {
            // Notify player of level completion and level advancement
            System.out.println("Congratulations, you finished \nLevel: " + Levels + " Next Level starting. Good luck!");
            Levels++;  // Move to the next level
            
            // Display remaining uses of abilities
            System.out.println("Uses of Frozen left: " + Abilities.getFrozenUses());
            System.out.println("Uses of Confusion left: " + Abilities.getConfusedUses());
            System.out.println("Uses of Intimidations left: " + Abilities.getIntimidatedUses());

            // Clear monster's current position on the game grid and reset its position
            Game.array[Mons.getMonsCurrentRow()][Mons.getMonsCurrentCol()] = '.';
            Mons.setMonsterPosition();  // Place the monster at a new position
            
            // Generate new coins for the next level
            Coin.generateCoins();
            
            // Optional delay for a better game experience at level 1
            if (Levels == 1) {
                try {
                    Thread.sleep(3000);  // Pause for 3 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();  // Handle interruption
                }
            }
        }
    }
}

