import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.io.*;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.awt.image.*;

// Creating a class to manage player interaction and the launch the game
public class GameManager {
    // Initializing the static game instance of PokemonRadiance
    public static PokemonRadiance game = new PokemonRadiance();
    // Initializing the static game graphics instance for managing the user interface
    public static GameGraphics gameGraphics = new GameGraphics();
    // Initializing the static instance to hold all Pokémon data
    public static AllPokemon allPokemon = new AllPokemon();
    public static void main(String[] args) {
        // Defining an inner class to handle key press events for user interaction
        class KeyPressListener implements KeyListener {
            // Creating a method that will allow for the operation of key press events
            public void keyPressed(KeyEvent e) {
                // Storing the key code of the pressed key
                int x = e.getKeyCode();
                // Moving the player sprite upward when not in battle
                if (x == KeyEvent.VK_UP && GameGraphics.isBattling == false) {
                    gameGraphics.playerSprite.moveU();
                }
                // Moving the player sprite to the right when not in battle
                if (x == KeyEvent.VK_RIGHT && GameGraphics.isBattling == false) {
                    gameGraphics.playerSprite.moveR();
                }
                // Moving the player sprite to the left when not in battle
                if (x == KeyEvent.VK_LEFT && GameGraphics.isBattling == false) {
                    gameGraphics.playerSprite.moveL();
                }
                // Moving the player sprite downward when not in battle
                if (x == KeyEvent.VK_DOWN && GameGraphics.isBattling == false) {
                    gameGraphics.playerSprite.moveD();
                }
                // Switching Pokémon when the 'A' key is pressed and not in battle
                if (x == KeyEvent.VK_A && GameGraphics.isBattling == false) {
                    game.switchPokemon();
                }
            }
            // Creating an empty implementation for key released so that KeyListener can work properly
            public void keyReleased(KeyEvent e) {}
            // Creating an empty implementation for key typed so that KeyListener can work properly
            public void keyTyped(KeyEvent e) {}
        }
        // Creating a key listener instance to handle key events
        KeyPressListener KPL = new KeyPressListener();
        // Adding the key listener to the game frame for input detection
        gameGraphics.frame.addKeyListener(KPL);
        // Starting the game by running the game logic
        game.runGame();
    }
}