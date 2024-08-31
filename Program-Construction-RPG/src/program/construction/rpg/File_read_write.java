/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.*;
import java.io.*;

/**
 *
 * @author rynob
 */
public class File_read_write {

    public static HashMap<String, Integer> PlayerInfo = new HashMap();
    public static int SavedRow;
    public static int SavedCol;
    public static int SavedPoints;

    public void writeSave() {
        try {
            PrintWriter OutScores = new PrintWriter(new FileOutputStream("./resources/Save.txt"));  //file write/genarator
            Set eSet = PlayerInfo.entrySet();
            Iterator it = eSet.iterator();
            while (it.hasNext()) {
                OutScores.println(it.next());
            }

            if (OutScores != null) {
                OutScores.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file ");
        } catch (IOException e) {
            System.out.println("Error reading from file ");
        }
    }

    public static void readInstArrayFile() {
        try {
            BufferedReader inStream = new BufferedReader(new FileReader("./resources/Inst.txt"));
            String InLine = inStream.readLine();

            while (InLine != null) {
                System.out.println(InLine);
                InLine = inStream.readLine();
            }

            inStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file ");
        } catch (IOException e) {
            System.out.println("Error reading from file ");
        }

    }
    public static void readSaveArrayFile() {
        try {
            BufferedReader inStream = new BufferedReader(new FileReader("./resources/Save.txt"));
            String InLine = inStream.readLine();
            while (InLine != null) {
                String[] parts = InLine.split("=");
                PlayerInfo.put(parts[0], Integer.parseInt(parts[1]));
                InLine = inStream.readLine();
            }
            SavedRow = PlayerInfo.get("Row");
            SavedCol = PlayerInfo.get("Col");
            SavedPoints = PlayerInfo.get("Points");
            inStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file ");
        } catch (IOException e) {
            System.out.println("Error reading from file ");
        }
    }

    public static int getSavedRows() {
        return SavedRow;
    }

    public static int getSavedCols() {
        return SavedCol;
    }

    public static int getSavedPoints() {
        return SavedPoints;
    }

    public static void setSavedRows(int Rows) {
        PlayerInfo.replace("Points", Rows);
    }

    public static void setSavedCols(int Cols) {

        PlayerInfo.replace("Points", Cols);

    }

    public static void setSavedPoints(int Points) {

        PlayerInfo.replace("Points", Points);

    }
}
