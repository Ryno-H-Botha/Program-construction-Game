/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program.construction.rpg;

/**
 *
 * @author Илья Ilya
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JFrame {

    public TitleScreen() {
        // Set up the frame
        setTitle("RPG Game Title Screen");
        setSize(1600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create a panel for the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Use GridBagLayout for centering
        panel.setBackground(Color.DARK_GRAY);

        // Create GridBagConstraints for centering
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Column
        gbc.gridy = 0; // Row
        gbc.insets = new Insets(20, 20, 20, 20); // Padding around buttons
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment

        // Create buttons
        JButton newGameButton = new JButton("New Game");
        JButton savedGameButton = new JButton("Load Saved Game");
        JButton instructionsButton = new JButton("Instructions");

        // Set the font of the buttons to bold
        Font originalFont = newGameButton.getFont();
        Font boldFont = originalFont.deriveFont(Font.BOLD, 20); // Use 14-point bold font
        newGameButton.setFont(boldFont);
        savedGameButton.setFont(boldFont);
        instructionsButton.setFont(boldFont);

        // Set text color and background of the buttons
        newGameButton.setForeground(Color.WHITE);
        savedGameButton.setForeground(Color.WHITE);
        instructionsButton.setForeground(Color.WHITE);

        newGameButton.setBackground(Color.RED);
        savedGameButton.setBackground(Color.RED);
        instructionsButton.setBackground(Color.RED);

        // Set preferred size for buttons
        newGameButton.setPreferredSize(new Dimension(250, 80));
        savedGameButton.setPreferredSize(new Dimension(250, 80));
        instructionsButton.setPreferredSize(new Dimension(250, 80));

        // Add action listeners to buttons
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        savedGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSavedGame();
            }
        });

        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInstructions();
            }
        });

        // Add buttons to the panel using GridBagConstraints
        panel.add(newGameButton, gbc);
        gbc.gridy++; // Move to the next row for the next button
        panel.add(savedGameButton, gbc);
        gbc.gridy++; // Move to the next row for the next button
        panel.add(instructionsButton, gbc);

        // Add the panel to the frame
        add(panel);

        // Make the frame visible
        setVisible(true);
    }

    private void startNewGame() {
        // Initialize a new game
        System.out.println("Starting a new game...");
        ProgramConstructionRPG game = new ProgramConstructionRPG();
        game.initializeNewGame();  // Call the new initialization method
        dispose(); // Close the title screen
    }

    private void loadSavedGame() {

        System.out.println("Opening Load Game Screen...");
        new LoadGameScreen(new ProgramConstructionRPG());
        dispose();  // Close the title screen
    }

    private void showInstructions() {
        // Logic to show instructions
        JOptionPane.showMessageDialog(this,
                "Use the following buttons to move the character:"
                + "\n       Use W to move the character Up."
                + "\n       Use A to move the character Left."
                + "\n       Use S to move the character Down."
                + "\n       Use D to move the character Right."
                + "\n"
                + "\nYour goal is to collect all the coins that appear in a 13x9 grid but you aren't alone."
                + "\n"
                + "\nA monster is tryiong to chase you and prevent you from collecting all the coint."
                + "\n"
                + "\nYou can use the following abilities on the monster:"
                + "\n       Press F to use the Frozen ability you have a total of 3 uses per game."
                + "\n           It allows for the caracter to stop the monster for 3 turns."
                + "\n"
                + "\n       Press G to use the Intimidation ability you have a total of 3 uses per game."
                + "\n           It allows for the caracter to stop the monster for 2 turns."
                + "\n"
                + "\n       Press C to use the Confusion ability you have a total of 3 uses per game."
                + "\n           It allows for the caracter to stop the monster for 3 turns."
                + "\n\n"
                + "\nPress Q to quit the game.",
                "Instructions",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Create and show the title screen
        SwingUtilities.invokeLater(() -> new TitleScreen());
    }
}
