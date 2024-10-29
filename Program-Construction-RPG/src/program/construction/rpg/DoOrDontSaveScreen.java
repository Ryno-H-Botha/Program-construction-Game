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
public class DoOrDontSaveScreen extends JFrame {

    public ProgramConstructionRPG gameInstance;
    private GUI gameGUI;

    public DoOrDontSaveScreen(ProgramConstructionRPG gameInstance) {
        this.gameInstance = gameInstance;
        setTitle("Progress Colector");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setupUI();
    }

    private void setupUI() {
        // Set up the panel for save slot buttons
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        panel.setPreferredSize(new Dimension(600, 400));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.CENTER;

         //Create a label
        JLabel label = new JLabel("Do you want to save your progress?");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(label, gbc);
        
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        
        // Create buttons for save slots
        JButton slot1Button = new JButton("YES");
        JButton slot2Button = new JButton("NO");

        slot1Button.setFont(new Font("Arial", Font.BOLD, 24));
        slot2Button.setFont(new Font("Arial", Font.BOLD, 24));
        
        slot1Button.setPreferredSize(new Dimension(250, 60));
        slot2Button.setPreferredSize(new Dimension(250, 60));

        // Add buttons with action listeners
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(slot1Button, gbc);
        gbc.gridx = 1;
        panel.add(slot2Button, gbc);

        // Action listeners for loading each slot
        slot1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> {
                    GameSaveScreen saveScreen = new GameSaveScreen(DoOrDontSaveScreen.this.gameInstance);
                    saveScreen.setVisible(true);
                });
            }
        });
        slot2Button.addActionListener(e -> dispose());

        add(panel);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        // Create and show the title screen
        SwingUtilities.invokeLater(() -> new DoOrDontSaveScreen(new ProgramConstructionRPG()));
    }
}
