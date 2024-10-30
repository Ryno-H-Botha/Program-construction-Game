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
import java.io.PrintWriter;
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

   public Dbase_Save_Files() {
        super(); // Calls the constructor of Database_Setup to establish the connection
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
        CoinCount = 0;
        for (int i = 0; i < game.ROWS; i++) {
            for (int j = 0; j < game.COLS; j++) {
                game.printArray();
                System.out.println("hi");
                char value = game.array[i][j];
                System.out.println(game.array[i][j]);
                if (value == 'C'||value == 'c') {
                    CoinCount++;
                    String R = "Coin" + CoinCount + "Row";
                    String C = "Coin" + CoinCount + "Col";
                    System.out.println("values found Row = "+R+" Col = "+C);
                    insertSaveData(saveName,R, i);
                    insertSaveData(saveName,C,j);
                }

            }
            System.out.println();
        }
        insertSaveData(saveName,"CoinCount",CoinCount);
    }

public void saveDatabaseToFile(int saveValue) {
    // Set the file path based on the provided saveValue
    filePath = SetSaveFile(saveValue);
    saveName = "save_" + saveValue;
    HashMap<String, Integer> resultMap = getDatabaseValuesAsMap();
    writeSave(resultMap);
}

    public void writeSave(HashMap<String, Integer> resultMap) {
        try {
            
            PrintWriter OutScores = new PrintWriter(new FileOutputStream(filePath));  //file write/genarator
            Set eSet = resultMap.entrySet();
            Iterator it = eSet.iterator();
            while (it.hasNext()) {
                OutScores.println(it.next());
            }
            OutScores.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error writeing from file ");
        }
    }

public HashMap<String, Integer> getDatabaseValuesAsMap() {
    String query = "SELECT key_name, value FROM SAVEDATA"; // Adjust the query if needed
    HashMap<String, Integer> resultMap = new HashMap<>();

    // Use try-with-resources for automatic resource management
    try (PreparedStatement preparedStatement = connection.prepareStatement(query,
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        // Iterate through the ResultSet and populate the HashMap
        while (resultSet.next()) {
            String keyName = resultSet.getString("key_name");
            int value = resultSet.getInt("value");
            resultMap.put(keyName, value); // Add the key-value pair to the HashMap
        }

        System.out.println("Data retrieved from the database and stored in HashMap successfully.");
    } catch (SQLException e) {
        System.out.println("Database error: " + e.getMessage());
        e.printStackTrace();
    }

    return resultMap; // Return the populated HashMap
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
