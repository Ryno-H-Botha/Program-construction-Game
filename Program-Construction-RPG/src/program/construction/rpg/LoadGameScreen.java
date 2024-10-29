/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Илья Ilya
 */
public class LoadGameScreen extends JFrame {

    private ProgramConstructionRPG gameInstance;

    public LoadGameScreen(ProgramConstructionRPG gameInstance) {
        this.gameInstance = gameInstance;

        setTitle("Select Save Slot");
        setSize(600, 400);
        setLocationRelativeTo(null);  // Center the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Only close this window

        // Set up the panel for save slot buttons
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;

        // Create buttons for save slots
        JButton slot1Button = new JButton("Save Slot 1");
        JButton slot2Button = new JButton("Save Slot 2");
        JButton slot3Button = new JButton("Save Slot 3");

        slot1Button.setFont(new Font("Arial", Font.BOLD, 18));
        slot2Button.setFont(new Font("Arial", Font.BOLD, 18));
        slot3Button.setFont(new Font("Arial", Font.BOLD, 18));

        // Add buttons with action listeners
        gbc.gridy = 0;
        panel.add(slot1Button, gbc);
        gbc.gridy++;
        panel.add(slot2Button, gbc);
        gbc.gridy++;
        panel.add(slot3Button, gbc);

        // Action listeners for loading each slot
        slot1Button.addActionListener(e -> loadGame(1));
        slot2Button.addActionListener(e -> loadGame(2));
        slot3Button.addActionListener(e -> loadGame(3));

        add(panel);
        setVisible(true);
    }

    private void loadGame(int slot) {
        System.out.println("Loading save slot " + slot);
        gameInstance.loadSavedGame(slot);  // Call method in ProgramConstructionRPG
        dispose();  // Close this screen after selection
    }
}
