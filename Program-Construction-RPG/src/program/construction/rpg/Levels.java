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

    private Movement game;
    private Coins cn;
    private int Levels;

    public Levels(Movement game, Coins cn) {
        this.game = game;
        this.cn = cn;
    }

    public int getLevels() {
        return this.Levels;
    }

    public void setLevels(int Levels) {
        this.Levels = Levels;
    }

    public void CheckLevel() {
        if (cn.CoinCount == 0 && game.currentRow == game.NewRow && game.currentCol == game.NewCol) {
            System.out.println("Congratulaions you finished \nLevel: " + Levels + " Next Level starting Good luck");
            Levels++;
            if (Levels == 1) {// not needed but better for game experience
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cn.generateCoins();
        }
    }

}
