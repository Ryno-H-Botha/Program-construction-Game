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
       int CurrentRow = game.GetCurrentRow();
       int CurrentCol = game.GetCurrentCol();
            switch (move) {
                case 'w'://move up
                     CurrentRow--;
                        char w = game.array[CurrentRow][CurrentCol];
                         if(CurrentRow >= 0 && w == 'C')
                        {
                            setPoints(getPoints()+10);
                        }
                    break;
                case 's':
                    CurrentRow++;
                    char s = game.array[CurrentRow][CurrentCol]; 
                        if(CurrentRow < game.ROWS && s == 'C')
                        {
                            setPoints(getPoints()+10);
                        }
                    break;
                case 'a':
                        CurrentCol--;
                         char a = game.array[CurrentRow][CurrentCol];
                         if(CurrentCol >= 0&&a == 'C')
                        {
                            setPoints(getPoints()+10);
                        }
                    break;
                case 'd':
                        CurrentCol++;
                        char d = game.array[CurrentRow][CurrentCol++];
                         if(CurrentCol < game.COLS && d == 'C')
                        {
                            setPoints(getPoints()+10);
                        }
                    break;
            }
    }
}
