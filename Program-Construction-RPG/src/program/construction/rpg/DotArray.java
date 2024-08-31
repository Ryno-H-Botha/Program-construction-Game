/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;

/**
 *
 * @author rynob
 */
 import java.util.Scanner;

public class DotArray extends ProgramConstructionRPG{
    private static final int ROWS = 9;
    private static final int COLS = 13;
    private static int currentRow = ROWS / 2;
    private static int currentCol = COLS / 2;
    private static char[][] array = new char[ROWS][COLS];

    public static void main(String[] args) {
        initializeArray();
        printArray();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            moveUp();
            moveDown();
            System.out.println("Use arrow keys (WASD) to move '@' or 'q' to quit:");
            char input = scanner.next().charAt(0);

            switch (input) {
                case 'w':
                    moveUp();
                    break;
                case 's':
                    moveDown();
                    break;
                case 'a':
                    moveLeft();
                    break;
                case 'd':
                    moveRight();
                    break;
                case 'q':
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid input. Use WASD keys.");
                    break;
            }

            printArray();
        }
    }

    public static void initializeArray() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                array[i][j] = '.';
            }
        }
        array[currentRow][currentCol] = '@';
        
    }

    private static void printArray() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void moveUp() {
        if (currentRow > 0) {
            array[currentRow][currentCol] = '.';
            currentRow--;
            array[currentRow][currentCol] = '@';
        }
    }

    private static void moveDown() {
        if (currentRow < ROWS - 1) {
            array[currentRow][currentCol] = '.';
            currentRow++;
            array[currentRow][currentCol] = '@';
        }
    }

    private static void moveLeft() {
        if (currentCol > 0) {
            array[currentRow][currentCol] = '.';
            currentCol--;
            array[currentRow][currentCol] = '@';
        }
    }

    private static void moveRight() {
        if (currentCol < COLS - 1) {
            array[currentRow][currentCol] = '.';
            currentCol++;
            array[currentRow][currentCol] = '@';
        }
    }
}
