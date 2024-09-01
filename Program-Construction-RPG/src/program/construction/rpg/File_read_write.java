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

    public static int SavedRow;
    public static int SavedCol;
    public static int SavedPoints;
    public static int SavedLevels;
    public static int SavedMovesCount;
    public static int SavedFrozenUses;
    public static int SavedConfusedUses;
    public static int SavedIntimidatedUses;
    public static int SavedMonsCurrentRow;
    public static int SavedMonsCurrentCol;
    public static int CoinCount = 0;
    public static HashMap<String, Integer> PlayerInfo = new HashMap<>();

    public void writeSave() {
        try {
            PrintWriter OutScores = new PrintWriter(new FileOutputStream("./resources/Save.txt"));  //file write/genarator
            Set eSet = PlayerInfo.entrySet();
            Iterator it = eSet.iterator();
            while (it.hasNext()) {
                OutScores.println(it.next());
            }
            OutScores.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error writeing from file ");
        }
    }

    public static void readInstArrayFile() {
        try {
            BufferedReader inStream = new BufferedReader(new FileReader("./resources/Inst.txt"));
            String InLine = inStream.readLine();

            while (InLine != null) {
                System.out.println(InLine);
                InLine = inStream.readLine();
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
            readHash();
            inStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file ");
        } catch (IOException e) {
            System.out.println("Error reading from file ");
        }
    }

    public static void readHash() {
        SavedRow = PlayerInfo.get("Row");
        SavedCol = PlayerInfo.get("Col");
        SavedPoints = PlayerInfo.get("Points");
        SavedLevels = PlayerInfo.get("Levels");
        SavedMovesCount = PlayerInfo.get("MovesCount");
        SavedFrozenUses = PlayerInfo.get("FrozenUses");
        SavedConfusedUses = PlayerInfo.get("ConfusedUses");
        SavedIntimidatedUses = PlayerInfo.get("IntimidatedUses");
        SavedMonsCurrentRow = PlayerInfo.get("MonsCurrentRow");
        SavedMonsCurrentCol = PlayerInfo.get("MonsCurrentCol");
    }

    public void saveCoins(Movement game) {

        for (int i = 0; i < game.ROWS; i++) {
            for (int j = 0; j < game.COLS; j++) {
                char value = game.array[i][j];
                if (value == 'C') {
                    CoinCount++;
                    String R = "Coin" + CoinCount + "Row";
                    String C = "Coin" + CoinCount + "Col";
                    PlayerInfo.put(R, i);
                    PlayerInfo.put(C, j);
                }

            }
            System.out.println();
        }
        PlayerInfo.put("CoinCount", CoinCount);
    }

    public void setArrayCoins(Movement game, Coins cn) {
        cn.CoinCount = PlayerInfo.get("CoinCount");
        for (int i = 1; i <= cn.CoinCount; i++) {

            String R = "Coin" + i + "Row";
            String C = "Coin" + i + "Col";
            int Row = PlayerInfo.get(R);
            int Col = PlayerInfo.get(C);
            game.array[Row][Col] = 'C';
        }
    }

    //all gets
    public static int getSavedRows() {
        return SavedRow;
    }

    public static int getSavedCols() {
        return SavedCol;
    }

    public static int getMonstSavedRows() {
        return SavedRow;
    }

    public static int getMonstSavedCols() {
        return SavedCol;
    }

    public static int getSavedPoints() {
        return SavedPoints;
    }

    public static int getSavedLevels() {
        return SavedLevels;
    }

    public static int getSavedMovesCount() {
        return SavedMovesCount;
    }

    public static int getSavedFrozenUses() {
        return SavedFrozenUses;
    }

    public static int getSavedConfusedUses() {
        return SavedConfusedUses;
    }

    public static int getSavedIntimidatedUses() {
        return SavedIntimidatedUses;
    }

    public int getSavedMonsCurrentRow() {
        return SavedMonsCurrentRow;
    }

    public int getSavedMonsCurrentCol() {
        return SavedMonsCurrentCol;
    }

    //all sets 
    public void setSavedRows(int Rows) {
        PlayerInfo.put("Row", Rows);
    }

    public void setSavedCols(int Cols) {

        PlayerInfo.put("Col", Cols);

    }

    public void setMonstSavedRows(int Rows) {
        PlayerInfo.put("MonstRow", Rows);
    }

    public void setMonstSavedCols(int Cols) {

        PlayerInfo.put("MonstCol", Cols);
    }

    public void setSavedPoints(int Points) {

        PlayerInfo.put("Points", Points);

    }

    public void setSavedLevels(int Levels) {
        PlayerInfo.put("Levels", Levels);
    }

    public void setSavedMovesCount(int MovesCount) {
        PlayerInfo.put("MovesCount", MovesCount);
    }

    public void setSavedFrozenUses(int uses) {
        PlayerInfo.put("FrozenUses", uses);
    }

    public void setSavedConfusedUses(int uses) {
        PlayerInfo.put("ConfusedUses", uses);
    }

    public void setSavedIntimidatedUses(int uses) {
        PlayerInfo.put("IntimidatedUses", uses);
    }

    public void setSavedMonsCurrentRow(int Row) {
        PlayerInfo.put("MonsCurrentRow", Row);
    }

    public void setSavedMonsCurrentCol(int Col) {
        PlayerInfo.put("MonsCurrentCol", Col);
    }
}
