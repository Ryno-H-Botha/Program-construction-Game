/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package program.construction.rpg;

import java.util.*;
import java.io.*;

/**
 *
 * @author rynob
 */
public class ProgramConstructionRPG {

    /**
     * @param args the command line arguments
     */
    public static int ROWS = Inialize_array.GetRows();
    public static int COLS = Inialize_array.GetCols();
    public static int NewRow = ROWS / 2;
    public static int NewCol = COLS / 2;
    private static int currentRow;
    private static int currentCol;
    private static int currentPoints;
    public static String inputfile = "./resources/input.txt";
    public static String savefile = "./resources/Save.txt";
    public static String instfile = "./resources/Inst.txt";
    public static char[][] array = new char[ROWS][COLS];
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<String, Integer> PlayerInfo = new HashMap();
        System.out.println("New game     (n) \nSaved Game   (s) \nInstructions (I)");
        String start = scan.nextLine().toLowerCase();
        Movement move = new Movement(currentRow,currentCol);
        boolean valid = false;
        int leavue = 0;
        switch (start) {
            case "n":
                readArrayFile(savefile, "save", PlayerInfo);
                currentRow = NewRow;
                currentCol = NewCol;
                currentPoints = 0;
                GenerateArray(NewRow, NewCol);
                printArray();
                break;
            case "s":
                readArrayFile(savefile, "save", PlayerInfo);
                currentRow = PlayerInfo.get("Row");
                currentCol = PlayerInfo.get("Col");
                currentPoints = PlayerInfo.get("points");
                GenerateArray(currentRow, currentCol);
                printArray();
                break;
            case "i":
                readArrayFile(instfile, "inst", PlayerInfo);
                start = scan.nextLine().toLowerCase();
                break;
        }

        while (true) {
            System.out.println("Use arrow keys (WASD) to move '@' or 'q' to quit:");
            char input = scan.next().charAt(0);
        
            switch (input) {
                case 'w'://move up
                    if (currentRow > 0) {
                        array[currentRow][currentCol] = '.';
                        currentRow--;
                        array[currentRow][currentCol] = '@';
                    }
                    break;
                case 's':
                    if (currentRow < ROWS - 1) {
                        array[currentRow][currentCol] = '.';
                        currentRow++;
                        array[currentRow][currentCol] = '@';
                    }
                    break;
                case 'a':
                    if (currentCol > 0) {
                        array[currentRow][currentCol] = '.';
                        currentCol--;
                        array[currentRow][currentCol] = '@';
                    }
                    break;
                case 'd':
                    if (currentCol < COLS - 1) {
                        array[currentRow][currentCol] = '.';
                        currentCol++;
                        array[currentRow][currentCol] = '@';
                    }
                    break;
                case 'q':
                    System.out.println("Exiting...");
                    leavue = 1;
                    break;

                default:
                    System.out.println("Invalid input. Use WASD keys.");
                    break;
            }
            if (leavue == 1) {
                break;
            }
            printArray();
        }
        System.out.println("Save (S)\nDelete (D)");
        char SaveData = scan.next().charAt(0);
        
        System.out.println("You entered:" + SaveData);

        
        switch(SaveData){
            case 's':
                writeSave(PlayerInfo);
                System.out.println("Data has been Saved.");
            break;
            case 'd':    
                System.out.println("Data deleted.");
            break;
            default:
                System.out.println("Invalid input.");
            break;
           
        }
    
    }
    // TODO code application logic here

    public static void GenerateArray(int RowPos, int ColPos) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                array[i][j] = '.';
            }
        }
        array[RowPos][ColPos] = '@';
    }

    public static void readArrayFile(String filename, String state, HashMap<String, Integer> PlayerInfo) {

        try {
            BufferedReader inStream = new BufferedReader(new FileReader(filename));
            String InLine = inStream.readLine();

            switch (state) {
                case "inst":
                    while (InLine != null) {
                        System.out.println(InLine);
                        InLine = inStream.readLine();
                    }
                    break;
                case "save":
                    while (InLine != null) {
                        String[] parts = InLine.split(" ");
                        PlayerInfo.put(parts[0], Integer.parseInt(parts[1]));
                        InLine = inStream.readLine();
                    }

            }

            inStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file ");
        } catch (IOException e) {
            System.out.println("Error reading from file ");
        }
    }

    private static void printArray() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void writeSave(HashMap<String, Integer> PlayerInfo) {
        try {
            PrintWriter OutScores = new PrintWriter(new FileOutputStream(savefile));  //file write/genarator
            Set eSet = PlayerInfo.entrySet();
                Iterator it = eSet.iterator();
                while (it.hasNext()) {
                    OutScores.println(it.next());
                }

        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file ");
        } catch (IOException e) {
            System.out.println("Error reading from file ");
        }
    }
}
