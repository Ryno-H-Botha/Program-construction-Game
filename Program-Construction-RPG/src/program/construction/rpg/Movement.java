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
public class Movement {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Initialize the array
        int[] array = {1, 2, 3, 4, 5};

        // Display the initial array
        displayArray(array);

        // Loop to get user input and alter the array
        while (true) {
            System.out.println("Enter a command (format: index value) or 'exit' to quit:");
            String input = scan.nextLine();

            // Check if the user wants to exit
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            // Parse the input
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Invalid command. Please use the format: index value");
                continue;
            }

            try {
                int index = Integer.parseInt(parts[0]);
                int value = Integer.parseInt(parts[1]);

                // Check if the index is valid
                if (index < 0 || index >= array.length) {
                    System.out.println("Index out of bounds. Please enter a valid index.");
                    continue;
                }

                // Modify the array
                array[index] = value;

                // Display the updated array
                displayArray(array);

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values for index and value.");
            }
        }

        scan.close();
    }

    private static void displayArray(int[] array) {
        System.out.print("Array: ");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

    

