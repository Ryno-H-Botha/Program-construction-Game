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
public class FreezePower {
        public int Points;
    public int freezeCount;
    
    private Movement game;
    public FreezePower(Movement game)
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
    public int getfreezeCount()
    {
        return this.freezeCount;
    }
    public void setfreezeCount(int freezeCount)
    {
        this.freezeCount = freezeCount;
    }
   public void generateCoins()
   {
        Random rand = new Random();
        freezeCount = rand.nextInt(2);//so zero wont ever appear
        int freezePlaced = 0;
        while (freezePlaced < freezeCount) {
            int row = rand.nextInt(game.ROWS);
            int col = rand.nextInt(game.COLS);
            // Place a coin if the spot is empty
            if (game.array[row][col] == '.') {
                game.array[row][col] = 'C';
                freezePlaced++;
            }
        }
    }
   
//    
    public void checkCoins(char move) {
      int CurrentRow = game.getCurrentRow();
      int CurrentCol = game.getCurrentCol();
      
      switch (move) {
          case 'w': // move up
              if (CurrentRow > 0) {
                  CurrentRow--; // Update row first
                  if (game.array[CurrentRow][CurrentCol] == 'C') {
                      setPoints(getPoints() + 10);
                      game.array[CurrentRow][CurrentCol] = '.'; // Clear the coin after collecting
                      freezeCount--;
                  }
              }
              break;
          case 's': // move down
              if (CurrentRow < game.ROWS - 1) {
                  CurrentRow++; // Update row first
                  if (game.array[CurrentRow][CurrentCol] == 'C') {
                      setPoints(getPoints() + 10);
                      game.array[CurrentRow][CurrentCol] = '.'; // Clear the coin after collecting
                      freezeCount--;
                  }
              }
              break;
          case 'a': // move left
              if (CurrentCol > 0) {
                  CurrentCol--; // Update column first
                  if (game.array[CurrentRow][CurrentCol] == 'C') {
                      setPoints(getPoints() + 10);
                      game.array[CurrentRow][CurrentCol] = '.'; // Clear the coin after collecting
                      freezeCount--;
                  }
              }
              break;
          case 'd': // move right
              if (CurrentCol < game.COLS - 1) {
                  CurrentCol++; // Update column first
                  if (game.array[CurrentRow][CurrentCol] == 'C') {
                      setPoints(getPoints() + 10);
                      game.array[CurrentRow][CurrentCol] = '.'; // Clear the coin after collecting
                      freezeCount--;
                  }
              }
              break;
      }
    }
    
}
