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
    public static void main(String[] args) {
        // TODO code application logic here
        try{
        BufferedReader InPut = new BufferedReader(new FileReader("./Resources/input.txt"));//file reader
        PrintWriter OutPut = new PrintWriter(new FileOutputStream("./Resources/output.txt"));  //file write/genarator
        int colum = 0;
        String In_Line = null;
        while ((In_Line = InPut.readLine()) != null) {
                String[] parts = In_Line.split(",");
               
            }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file ");
        } catch (IOException e) {
            System.out.println("Error reading from file ");
        }
        
    }
    
}
