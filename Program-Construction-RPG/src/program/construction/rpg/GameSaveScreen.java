/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Илья Ilya
 */

public class GameSaveScreen extends JFrame {

    public ProgramConstructionRPG gameInstance;
    public  Movement Game;

    public GameSaveScreen(ProgramConstructionRPG gameInstance, Movement Game) {
        this.gameInstance = gameInstance;
        this.Game = Game;
        setTitle("Select Save Slot");
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setupUI(Game);
    }

    private void setupUI(Movement Game) {
        // Set up the panel for save slot buttons
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.CENTER;

        //Create a label
        JLabel label = new JLabel("Please select a slot to save your game?");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(label, gbc);
        
        JLabel label2 = new JLabel("Or press Cancel if you changed your mind.");
        label2.setFont(new Font("Arial", Font.BOLD, 24));
        label2.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++; 
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(label2, gbc);
        
        gbc.gridwidth = 1;
        
        // Create buttons for save slots
        JButton slot1Button = new JButton("Save Slot 1");
        JButton slot2Button = new JButton("Save Slot 2");
        JButton slot3Button = new JButton("Save Slot 3");
        JButton slot4Button = new JButton("Cancel");

        slot1Button.setFont(new Font("Arial", Font.BOLD, 20));
        slot2Button.setFont(new Font("Arial", Font.BOLD, 20));
        slot3Button.setFont(new Font("Arial", Font.BOLD, 20));
        slot4Button.setFont(new Font("Arial", Font.BOLD, 20));
        
        slot1Button.setPreferredSize(new Dimension(250, 60));
        slot2Button.setPreferredSize(new Dimension(250, 60));
        slot3Button.setPreferredSize(new Dimension(250, 60));
        slot4Button.setPreferredSize(new Dimension(200, 50));

        // Add buttons with action listeners
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(slot1Button, gbc);
        gbc.gridy++;
        panel.add(slot2Button, gbc);
        gbc.gridy++;
        panel.add(slot3Button, gbc);
        gbc.gridy++;
        panel.add(slot4Button, gbc);

        // Action listeners for loading each slot
        slot1Button.addActionListener(e -> {
            try {
                gameInstance.saveGame(Game,1);
            } catch (SQLException ex) {
                Logger.getLogger(GameSaveScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        slot2Button.addActionListener(e -> {
            try {
                gameInstance.saveGame(Game,2);
            } catch (SQLException ex) {
                Logger.getLogger(GameSaveScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        slot3Button.addActionListener(e -> {
            try {
                gameInstance.saveGame(Game,3);
            } catch (SQLException ex) {
                Logger.getLogger(GameSaveScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        slot4Button.addActionListener(e -> dispose());

        add(panel);
        setVisible(true);
    }
    }
    
