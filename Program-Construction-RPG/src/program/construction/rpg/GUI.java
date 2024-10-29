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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI extends JFrame {

    private Movement game; // Reference to the Movement class
    private ProgramConstructionRPG gameFrameWork; // Reference to the game logic
    private JLabel[][] gridCells; // 2D array for grid cells
    private JPanel gridPanel; // Panel to hold the grid
    private Timer timer; // Timer for refreshing the grid

    public GUI(Movement game, ProgramConstructionRPG gameInstance) {
        this.game = game;
        this.gameFrameWork = gameInstance;
        gridCells = new JLabel[ProgramConstructionRPG.ROWS][ProgramConstructionRPG.COLS];

        // Set up the main frame
        setTitle("Coin Hauler");
        setSize(1600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Create the grid panel with black background
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(ProgramConstructionRPG.ROWS, ProgramConstructionRPG.COLS));
        gridPanel.setOpaque(false); // Make gridPanel transparent to show background

        // Initialize grid cells
        for (int row = 0; row < ProgramConstructionRPG.ROWS; row++) {
            for (int col = 0; col < ProgramConstructionRPG.COLS; col++) {
                gridCells[row][col] = new JLabel();
                gridCells[row][col].setHorizontalAlignment(SwingConstants.CENTER);
                gridCells[row][col].setVerticalAlignment(SwingConstants.CENTER);
                gridCells[row][col].setOpaque(false); // Make the label opaque
                gridCells[row][col].setForeground(Color.WHITE); // Set text color to white
                gridPanel.add(gridCells[row][col]);
            }
        }

        // Create the background panel with the image
        BackgroundPanel backgroundPanel = new BackgroundPanel(); // Assuming BackgroundPanel handles image loading
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(gridPanel, BorderLayout.CENTER); // Add gridPanel to backgroundPanel

        // Add KeyListener to the gridPanel for capturing player actions
        gridPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char input = Character.toLowerCase(e.getKeyChar());
                switch (input) {
                    case 'w': // Movement keys
                    case 'a': // Movement keys
                    case 's': // Movement keys
                    case 'd': // Movement keys
                    case 'f': // Ability keys
                    case 'g': // Ability keys
                    case 'c': // Ability keys
                    case 'q': // Quit key
                        gameInstance.processCommand(input); // Pass the key to game logic
                        break;
                    default:
                        break;
                }
            }
        });

         // Ensure gridPanel is focusable and has focus for key events
        gridPanel.setFocusable(true);
        gridPanel.requestFocusInWindow();

        // Add the background panel to the frame
        add(backgroundPanel, BorderLayout.CENTER);

        // Start the timer to update the grid periodically
        timer = new Timer(100, e -> updateGrid(50));
        timer.start();

        setVisible(true);
    }

    // Method to update the grid based on the game state
    public void updateGrid(int fontSize) {
        // Access the current game array directly from the Movement instance
        char[][] array = game.getArray(); // Use the game instance reference

        // Update the grid cells based on the current game state
        for (int row = 0; row < ProgramConstructionRPG.ROWS; row++) {
            for (int col = 0; col < ProgramConstructionRPG.COLS; col++) {
                char cellValue = array[row][col];
                JLabel cell = gridCells[row][col];

                // Set cell display based on game character (player, monster, coin, etc.)
                switch (cellValue) {
                    case '@':
                        cell.setText("@");  // Player icon
                        break;
                    case 'M':
                        cell.setText("M");  // Monster icon
                        break;
                    case 'C':
                        cell.setText("C");  // Coin icon
                        break;
                    case 'H':
                        cell.setText("H");  // Home icon
                        break;
                    case '.':
                        cell.setText(".");  // Empty space
                        break;
                    default:
                        cell.setText("");  // Default case for any unexpected characters
                        break;
                }
                cell.setFont(new Font("Arial", Font.BOLD, fontSize));
            }
        }

        // Repaint the grid panel to reflect changes
        gridPanel.revalidate();
        gridPanel.repaint();
    }
}
