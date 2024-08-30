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

   private static void writeSave(String filename) {
        try {
            PrintWriter OutScores = new PrintWriter(new FileOutputStream(filename));  //file write/genarator
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
        public static void readSaveArrayFile() 
        {
            try {
                BufferedReader inStream = new BufferedReader(new FileReader("./resources/Save.txt"));
                String InLine = inStream.readLine();
                while (InLine != null) {
                    String[] parts = InLine.split(" ");
                    PlayerInfo.put(parts[0],Integer.parseInt(parts[1]));
                    InLine = inStream.readLine();
                }
                inStream.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error reading from file ");
            } catch (IOException e) {
                System.out.println("Error reading from file ");
            }
        }
        public static int getSavedRows()
        {
            readSaveArrayFile();
            int Rows = PlayerInfo.get(Rows);
            return Rows;
        }
        public static int getSavedCols()
        {
            readSaveArrayFile();
            int Cols = PlayerInfo.get(Cols);
            return Cols;
        }
        public static int getSavedPoints()
        {
            readSaveArrayFile();
            int Points = PlayerInfo.get(Points);
            return Points;
        }
        public static void setSavedRows(int Rows)
        {
            readSaveArrayFile();
            int Points = PlayerInfo.replace("Points",Points);
        }
        public static void setSavedCols(int Cols)
        {
            readSaveArrayFile();
            int Points = PlayerInfo.replace("Points",Points);

        }
        public static void setSavedPoints(int Points)
        {
            readSaveArrayFile();
            int Points = PlayerInfo.replace("Points",Points);

        }
}
