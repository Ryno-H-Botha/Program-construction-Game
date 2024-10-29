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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Илья Ilya
 */
public class LoadGameScreen extends JFrame {

    private ProgramConstructionRPG gameInstance;

    public LoadGameScreen(ProgramConstructionRPG gameInstance) {
        this.gameInstance = gameInstance;

        setTitle("Select Save Slot");
        setSize(1600, 900);
        setLocationRelativeTo(null);  // Center the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Only close this window
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.CENTER;

        //Create a label
        JLabel label = new JLabel("Please select a slot to load your game?");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(label, gbc);

        JLabel label2 = new JLabel("Or press Back to return to the title screen.");
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
        JButton slot4Button = new JButton("Back");

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
        slot1Button.addActionListener(e -> loadGame(1));
        slot2Button.addActionListener(e -> loadGame(2));
        slot3Button.addActionListener(e -> loadGame(3));
        slot4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TitleScreen();
                dispose();
            }
        });

        add(panel);
        setVisible(true);
    }

    private void loadGame(int slot) {
        System.out.println("Loading save slot " + slot);
        gameInstance.loadSavedGame(slot);  // Call method in ProgramConstructionRPG
        dispose();  // Close this screen after selection
    }
    
        public static void main(String[] args) {
        // Create and show the title screen
        SwingUtilities.invokeLater(() -> new LoadGameScreen(new ProgramConstructionRPG()));
    }
}
