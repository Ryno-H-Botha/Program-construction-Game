/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;

/**
 *
 * @author rynob
 */
public class Inialize_array {

    protected static final int ROWS = 9;
    protected static final int COLS = 13;
    protected static int NewRow = ROWS / 2;
    protected static int NewCol = COLS / 2;
    protected char[][] array = new char[ROWS][COLS];
    protected int currentRow;
    protected int currentCol;
    MonsterMovement monster = new MonsterMovement(this);

    public Inialize_array(int Row, int Col) {
        this.currentRow = Row;
        this.currentCol = Col;
        GenerateArray(Row, Col);
    }

    public void SetPostion(int Row, int Col) {
        this.currentRow = Row;
        this.currentCol = Col;
        GenerateArray(Row, Col);

    }

    public void setCoinPostion(int Row, int Col) {
        array[Row][Col] = 'C';
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public static int getRows() {
        return ROWS;
    }

    public static int getCols() {
        return COLS;
    }

    public static int getNewRow() {
        return NewRow;
    }

    public static int getNewCol() {
        return NewCol;
    }

    public void GenerateArray(int Row, int Col) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                array[i][j] = '.';
            }
        }
        array[Row][Col] = '@';
    }

    public void printArray() {
        char c = array[NewRow][NewCol];
        if (!(c == '@')) {
            array[NewRow][NewCol] = 'H';
        }
        monster.moveMonster();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (i == monster.getCurrentRow() && j == monster.getCurrentCol()) {
                    System.out.print("M ");
                } else {
                    System.out.print(array[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("Uses of Confusion left: " + Abilities.getConfusedUses());
        System.out.println("Uses of Intimidations left: " + Abilities.getIntimidatedUses());
        System.out.println("Monster position: " + monster.getCurrentRow() + ", " + monster.getCurrentCol());
    }
}
