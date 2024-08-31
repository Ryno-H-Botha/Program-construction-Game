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
    public static int Points;
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
   public void generateCoins()
   {
//        
//        Random rand = new Random();
//        int numberOfCoins = rand.nextInt(9)+1;//so zero wont ever appear
//        int coinsPlaced = 0;
//        while (coinsPlaced < numberOfCoins) {
//            int row = rand.nextInt(game.ROWS);
//            int col = rand.nextInt(game.COLS);
//            // Place a coin if the spot is empty
//            if (game.array[row][col] == '.') {
//                game.array[row][col] = 'C';
//                coinsPlaced++;
//            }
//        }
    }
//    
  public void checkCoins(char move)
   {
//            switch (move) {
//                case 'w'://move up
//                    if(game.array[game.currentRow--][game.currentCol] == 'C')
//                        {
//                            Points += 10;
//                        }
//                    break;
//                case 's':
//                        if(game.array[game.currentRow++][game.currentCol] == 'C')
//                        {
//                            Points += 10;
//                        }
//                    break;
//                case 'a':
//                         if(game.array[game.currentRow][game.currentCol--] == 'C')
//                        {
//                            Points += 10;
//                        }
//                    break;
//                case 'd':
//                         if(game.array[game.currentRow][game.currentCol++] == 'C')
//                        {
//                            Points += 10;
//                        }
//                    break;
//            }
    }
//    
    
    
    
    
    
}
