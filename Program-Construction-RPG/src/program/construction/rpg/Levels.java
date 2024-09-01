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

    private Movement Game;
    private Coins Coin;
    private MonsterMovement Mons;
    private Abilities Ability;
    private int Levels;

    public Levels(Movement game, Coins cn, MonsterMovement mons, Abilities Ability) {
        this.Game = game;
        this.Coin = cn;
        this.Mons = mons;
        this.Ability = Ability;
    }

    public int getLevels() {
        return this.Levels;
    }

    public void setLevels(int Levels) {
        this.Levels = Levels;
    }

    public void CheckLevel() {
        if (Coin.CoinCount == 0 && Game.currentRow == Game.NewRow && Game.currentCol == Game.NewCol) {
            System.out.println("Congratulaions you finished \nLevel: " + Levels + " Next Level starting Good luck");
            Levels++;
            System.out.println("Uses of Confusion left: " + Abilities.getConfusedUses());
            System.out.println("Uses of Intimidations left: " + Abilities.getIntimidatedUses());
            System.out.println("Monster position: " + Mons.getMonsCurrentRow() + ", " + Mons.getMonsCurrentRow());
            Game.array[Mons.getMonsCurrentRow()][Mons.getMonsCurrentCol()] = '.';
            Mons.setMonsterPosition();
            Coin.generateCoins();
            if (Levels == 1) {// not needed but better for Game experience
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
