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

public class BackgroundPanel extends JPanel {

    private Image backgroundImage;
    private String absolutePath = "I:/Git Project/finaal 1/Program-construction-Game3/Program-Construction-RPG/Resources/grass.jpg";

    // Constructor to load the background image
    public BackgroundPanel() {
        // Try loading the image as a resource
        backgroundImage = new ImageIcon(absolutePath).getImage();
        if (backgroundImage == null) {
            System.err.println("Image not found at absolute path: " + absolutePath);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the image, scaled to fit the panel dimensions
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
