/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;
import java.util.Random;
/**
 *
 * @author rynob
 */
public class Coins {
    public int Points;
    public int CoinCount;
    private Movement game;
    public Coins(Movement game)
    {
        this.game = game;
    }
    public int getPoints()
    {
        return this.Points;
    }
    public void setPoints(int Points)
    {
        this.Points = Points;
    }
    public int getCoinCount()
    {
        return this.CoinCount;
    }
    public void setCoinCounts(int CoinCount)
    {
        this.CoinCount = CoinCount;
    }
   public void generateCoins()
   {
        
        Random rand = new Random();
        CoinCount = rand.nextInt(9)+1;//so zero wont ever appear
        int coinsPlaced = 0;
        while (coinsPlaced < CoinCount) {
            int row = rand.nextInt(game.ROWS);
            int col = rand.nextInt(game.COLS);
            // Place a coin if the spot is empty
            if (game.array[row][col] == '.') {
                game.array[row][col] = 'C';
                coinsPlaced++;
            }
        }
    }
//    
  public void checkCoins(char move)
   {
       int CurretnRow = game.GetCurrentRow();
       int CurrentCol = game.GetCurrentCol();
            switch (move) {
                case 'w'://move up
                    if(game.array[CurretnRow--][CurrentCol] == 'C')
                        {
                            Points = Points+10;
                        }
                    break;
                case 's':
                        if(game.array[CurretnRow++][CurrentCol] == 'C')
                        {
                            Points = Points+10;
                        }
                    break;
                case 'a':
                         if(game.array[CurretnRow][CurrentCol--] == 'C')
                        {
                            Points = Points+10;
                        }
                    break;
                case 'd':
                         if(game.array[CurretnRow][CurrentCol++] == 'C')
                        {
                            Points = Points+10;
                        }
                    break;
            }
    }
}
