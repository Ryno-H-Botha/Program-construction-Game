/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;

/**
 *
 * @author rynob // user Name = user1 //password = 123
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;

public class Database_Setup {

    private static final String DB_URL = "jdbc:derby://localhost:1527/Program-construction-Game-assignment-2";
    private static final String DB_USER = "user1";
    private static final String DB_PASSWORD = "123";
   
    public static String savedfile1 = "./resources/Save_1.txt";
    public static String savedfile2 = "./resources/Save_2.txt";
    public static String savedfile3 = "./resources/Save_3.txt";
    
    private  Statement statement;
    protected  Connection connection;
    protected String saveName;
    protected  String filePath;
    // Constructor to establish database connection
    public Database_Setup() {
        try {
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        if (connection != null && !connection.isClosed()) {
            System.out.println("Connected to the database successfully.");
             this.initializeDataBase();

        }
        
    } catch (SQLException e) {
        System.out.println("Failed to connect to the database: " + e.getMessage());
        e.printStackTrace();
    }
    }
public void initializeDataBase() {
    try {
        if (checkExistedTable("SAVEDATA")) { 
            // Clear data if the table exists
            clearTable("SAVEDATA");
            System.out.println("Table SAVEDATA cleared successfully.");
        } else {
            // Create table if it doesn’t exist
            statement = connection.createStatement();
            statement.execute("CREATE TABLE SAVEDATA (save_name VARCHAR(50) NOT NULL, key_name VARCHAR(50) NOT NULL, value INT NOT NULL)");
            System.out.println("Table SAVEDATA created successfully.");
        }
        
        // Load save files to the database after ensuring the table is ready
        saveToDatabase(1);
        saveToDatabase(2);
        saveToDatabase(3);
    } catch (SQLException ex) {
        System.out.println("Error initializing the database: " + ex.getMessage());
        ex.printStackTrace();
    }
}
    // Method to read from save file and store into database
    public void saveToDatabase(int saveValue) {
         filePath = SetSaveFile(saveValue);
         saveName = "save_" + saveValue;
        try ( BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0];
                    int value = Integer.parseInt(parts[1]);
                    insertSaveData(saveName, key, value);
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert each key-value pair from save file into the database
    public void insertSaveData(String saveName, String key, int value) throws SQLException {
      // First, delete existing data for the given saveName and key
    String deleteSQL = "DELETE FROM SAVEDATA WHERE save_name = ? AND key_name = ?";
    try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL)) {
        deleteStatement.setString(1, saveName.toUpperCase());
        deleteStatement.setString(2, key.toUpperCase());
        deleteStatement.executeUpdate();
    }
    // Now, insert the new data
    String insertSQL = "INSERT INTO SAVEDATA (save_name, key_name, value) VALUES (?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
        preparedStatement.setString(1, saveName.toUpperCase());
        preparedStatement.setString(2, key.toUpperCase());
        preparedStatement.setInt(3, value);
        preparedStatement.executeUpdate();
    }
}

    // Method to retrieve a value from the database by its key
public int getValue(String saveName, String key) {
    String query = "SELECT value FROM SAVEDATA WHERE save_name = ? AND key_name = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, saveName.toUpperCase());
        preparedStatement.setString(2, key.toUpperCase());
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        // Check if a value exists for the given saveName and key
        if (resultSet.next()) {
            return resultSet.getInt("value");
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving value from database: " + e.getMessage());
        e.printStackTrace();
    }
    // Return a default value if no matching entry is found
    return -1;
}

    public static String SetSaveFile(int Save) {
        if (Save == 1) {
            return savedfile1;
        } else if (Save == 2) {
            return savedfile2;
        } else {
            return savedfile3;
        }
    }
public boolean checkExistedTable(String name) {
    try {
        DatabaseMetaData dbMetaData = connection.getMetaData();
        ResultSet rs = dbMetaData.getTables(null, null, name.toUpperCase(), new String[]{"TABLE"});
        boolean tableExists = rs.next();
        rs.close();
        return tableExists;
    } catch (SQLException ex) {
        System.out.println("Error checking for existing table: " + ex.getMessage());
        return false;
    }
}
    public void clearTable(String name) throws SQLException {
    try (Statement stmt = connection.createStatement()) {
        stmt.executeUpdate("DELETE FROM " + name);
        System.out.println("Table " + name + " has been cleared.");
    }
}

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
