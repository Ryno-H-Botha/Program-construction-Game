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
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author rynob
 */
public class Dbase_Save_Files extends Database_Setup{

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

    public void writeSave(int Save) {
        try {
            String FileName = SetSaveFile(Save);
            PrintWriter OutScores = new PrintWriter(new FileOutputStream(FileName));  //file write/genarator
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
//
//    public static void readInstArrayFile() {
//        try {
//            BufferedReader inStream = new BufferedReader(new FileReader("./resources/Inst.txt"));
//            String InLine = inStream.readLine();
//            while (InLine != null) {
//                System.out.println(InLine);
//                InLine = inStream.readLine();
//                try {
//                    Thread.sleep(800);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            inStream.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("Error reading from file ");
//        } catch (IOException e) {
//            System.out.println("Error reading from file ");
//        }
//
//    }

              

    public void saveCoins(Movement game,String saveName) throws SQLException {
        for (int i = 0; i < game.ROWS; i++) {
            for (int j = 0; j < game.COLS; j++) {
                char value = game.array[i][j];
                if (value == 'C') {
                    CoinCount++;
                    String R = "Coin" + CoinCount + "Row";
                    String C = "Coin" + CoinCount + "Col";
                    insertSaveData(saveName,R, i);
                    insertSaveData(saveName,C,j);
                    PlayerInfo.put(C, j);
                }

            }
            System.out.println();
        }
        PlayerInfo.put("CoinCount", CoinCount);
    }
    // Method to save database data to a specified save.txt file
    public void saveDatabaseToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            String querySQL = "SELECT key_name, value FROM SaveData WHERE save_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(querySQL)) {
                preparedStatement.setString(1, saveName);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String key = resultSet.getString("key_name");
                    int value = resultSet.getInt("value");
                    writer.println(key + "=" + value);
                }
                System.out.println("Data saved to " + filePath + " successfully.");
            } catch (SQLException e) {
                System.out.println("Error querying database for save data.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Error writing to save file.");
            e.printStackTrace();
        }
    }
    
    
    public void setArrayCoins(Movement game, Coins cn,String saveName) {
        cn.CoinCount = getValue(saveName,"CoinCount");
        for (int i = 1; i <= cn.CoinCount; i++) {

            String R = "Coin" + i + "Row";
            String C = "Coin" + i + "Col";
            int Row = getValue(saveName,R);
            int Col = getValue(saveName,C);
            game.array[Row][Col] = 'C';
        }
    }

}
