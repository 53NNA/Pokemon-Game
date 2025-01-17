import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Icon;

// Creating a class to represent a movable sprite that can interact with the environment
public class MoveableSprite extends JComponent {
    // Initializing the x-coordinate of the sprite's position to start near the center of the map
    private int x = 486;
    // Declaring a variable to store the image of the sprite for rendering purposes
    private BufferedImage sprite;
    // Initializing the y-coordinate of the sprite's position to match the starting x-coordinate placement
    private int y = 240;
    // Declaring a variable to store the map image that serves as the background
    public BufferedImage map;
    // Creating a constructor to initialize the sprite and map images
    public MoveableSprite() {
        try {
            // Loading the image of the sprite from the resource folder for visual representation
            sprite = ImageIO.read(GameManager.class.getResource("Images/Sprite.png"));
            // Loading the image of the map from the resource folder to serve as the game background
            map = ImageIO.read(GameManager.class.getResource("Images/Map.png"));
        }
        // Catching any IOException that might occur during the loading of the images
        catch (IOException e) {
            // Printing an error message to the console if the images fail to load
            System.out.println("Error:");
            e.printStackTrace();
        }
    }
    // Creating a method to move the sprite to the right when the right arrow key is pressed
    public void moveR() {
        // Checking if the sprite is currently within the zone for trainer interaction
        if (x <= 320 && x > 150 && y > 0 && y < 150) {
            // Increasing the x-coordinate to move the sprite one unit to the right
            x += 64;
            // Repainting the component to visually update the new position of the sprite
            repaint();
            // Displaying a dialog box to the player to confirm if they want to engage in a trainer battle
            int response = JOptionPane.showConfirmDialog(GameGraphics.frame, "Do you want to battle the trainer?", "Rival Trainer Wants to Fight!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("Images/Pokeball.png"));
            // Checking if the player selects "Yes" in the dialog box to accept the trainer battle
            if (response == JOptionPane.YES_OPTION) {
                // Calling the method to initiate a trainer battle
                GameManager.game.trainerBattle();
            }
        }
        // Creating an else if statement to allow movement outside the trainer interaction zone
        else if (x < 1046) {
            // Increasing the x-coordinate to move the sprite one unit to the right
            x += 64;
            // Repainting the component to visually update the new position of the sprite
            repaint();
            // Triggering a random Pokémon encounter outside the trainer zone
            GameManager.allPokemon.encounterPokemon(PokemonRadiance.averageLevelPlayerPokemon);
        }
    }
    // Creating a method to move the sprite to the left when the left arrow key is pressed
    public void moveL() {
        // Checking if the sprite is within the interaction zone for a trainer battle
        if (x <= 320 && x > 150 && y > 0 && y < 150) {
            // Decreasing the x-coordinate to move the sprite one unit to the left
            x -= 64;
            // Repainting the component to visually update the new position of the sprite
            repaint();
            // Displaying a confirmation dialog to the player about engaging in a trainer battle
            int response = JOptionPane.showConfirmDialog(GameGraphics.frame, "Do you want to battle the trainer?", "Rival Trainer Wants to Fight!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("Images/Pokeball.png"));
            // Checking if the player accepts the trainer battle
            if (response == JOptionPane.YES_OPTION) {
                // Calling the method to initiate a trainer battle
                GameManager.game.trainerBattle();
            }
        }
        // Creating an else if statement to allow movement outside the trainer interaction zone
        else if (x > 0) {
            // Decreasing the x-coordinate to move the sprite one unit to the left
            x -= 64;
            // Repainting the component to visually update the new position of the sprite
            repaint();
            // Triggering a random Pokémon encounter during movement outside the interaction zone
            GameManager.allPokemon.encounterPokemon(PokemonRadiance.averageLevelPlayerPokemon);
        }
    }
    // Creating a method to move the sprite upward when the up arrow key is pressed
    public void moveU() {
        // Checking if the sprite is in the trainer interaction zone
        if (x <= 320 && x > 150 && y > 0 && y < 150) {
            // Decreasing the y-coordinate to move the sprite one unit upward
            y -= 64;
            // Repainting the component to visually update the sprite's new position
            repaint();
            // Displaying a dialog box to the player about battling the trainer
            int response = JOptionPane.showConfirmDialog(GameGraphics.frame, "Do you want to battle the trainer?", "Rival Trainer Wants to Fight!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("Images/Pokeball.png"));
            // Checking if the player accepts the trainer battle
            if (response == JOptionPane.YES_OPTION) {
                // Calling the method to initiate a trainer battle
                GameManager.game.trainerBattle();
            }
        }
        // Creating an else if statement to allow movement upward outside the trainer interaction zone
        else if (y > 0) {
            // Decreasing the y-coordinate to move the sprite one unit upward
            y -= 64;
            // Repainting the component to visually update the sprite's new position
            repaint();
            // Triggering a random Pokémon encounter
            GameManager.allPokemon.encounterPokemon(PokemonRadiance.averageLevelPlayerPokemon);
        }
    }
    // Creating a method to move the sprite downward when the down arrow key is pressed
    public void moveD() {
        // Checking if the sprite is within the trainer interaction zone
        if (x <= 320 && x > 150 && y > 0 && y < 150) {
            // Increasing the y-coordinate to move the sprite one unit downward
            y += 64;
            // Repainting the component to visually update the sprite's new position
            repaint();
            // Displaying a dialog box to the player about battling the trainer
            int response = JOptionPane.showConfirmDialog(GameGraphics.frame, "Do you want to battle the trainer?", "Rival Trainer Wants to Fight!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("Images/Pokeball.png"));
            // Checking if the player accepts the trainer battle
            if (response == JOptionPane.YES_OPTION) {
                // Calling the method to initiate a trainer battle
                GameManager.game.trainerBattle();
            }
        }
        // Creating an else if statement to allow movement downward outside the trainer interaction zone
        else if (y < 486) {
            // Increasing the y-coordinate to move the sprite one unit downward
            y += 64;
            // Repainting the component to visually update the sprite's new position
            repaint();
            // Triggering a random Pokémon encounter
            GameManager.allPokemon.encounterPokemon(PokemonRadiance.averageLevelPlayerPokemon);
        }
    }
    // Overriding the actionPerformed method to automatically repaint the component as needed
    public void actionPerformed(ActionEvent e) {
        // Repainting the component to ensure it stays visually updated
        repaint();
    }
    // Overriding the paintComponent method to draw both the map and the sprite
    public void paintComponent(Graphics graphicsContext) {
        // Ensuring the superclass paint method is called for proper component rendering
        super.paintComponent(graphicsContext);
        // Drawing the map image at the top-left corner of the component
        graphicsContext.drawImage(map, 0, 0, null);
        // Drawing the sprite image at its current x and y coordinates
        graphicsContext.drawImage(sprite, x, y, null);
    }
    // Creating a method to return the preferred size of the component
    public Dimension getPreferredSize() {
        // Checking if the sprite image has not been successfully loaded
        if (sprite == null) {
            // Returning default dimensions in case the sprite image is missing
            return new Dimension(1110, 550);
        }
        // Returning the dimensions of the map if the sprite is loaded successfully
        else {
            return new Dimension(map.getWidth(null), map.getHeight(null));
        }
    }
}

