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
// Creating the GameGraphics class to manage and display the game's graphical user interface
public class GameGraphics {
    // Declaring variables for various user interface components and game state
    public static JFrame frame;
    public static JPanel centerPanel = new JPanel();
    public static JPanel pokemonList = new JPanel();
    public static JLabel attackImage, playerHealthText, opponentHealthText, playerPokemonImage, opponentPokemonImage, playerPokemonName, opponentPokemonName;
    public static HealthBar playerHealthBar, opponentHealthBar;
    public static boolean isBattling = false;
    public static final MoveableSprite playerSprite = new MoveableSprite();
    // Creating the constructor to initialize the JFrame and set the game display
    public GameGraphics(){
        // Initializing the frame and setting its properties
        frame = new JFrame();
        // Setting the frame size
        frame.setSize(1150,750);
        // Setting the window title
        frame.setTitle("Pokémon");
        // Calling the method to display the main screen
        mainDisplay();
    }
    // Creating a method to display the main screen with game elements
    public void mainDisplay(){
        // Stopping background music and playing the environment music
        Sound.BattleAgainstPokemon.stop();
        Sound.BattleAgainstTrainer.stop();
        Sound.VictoryAgainstTrainer.stop();
        Sound.Environment.play();
        // Setting the battling flag to false
        isBattling = false;
        // Setting the layout of the frame
        frame.setLayout(new BorderLayout());
        // Ensuring the game closes when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Clearing the center panel and revalidating it
        centerPanel.removeAll();
        centerPanel.revalidate();
        // Adding the player sprite to the center panel
        centerPanel.add(playerSprite);
        // Making the frame focusable
        frame.setFocusable(true);
        // Clearing the Pokemon list panel and revalidating it
        pokemonList.removeAll();
        pokemonList.revalidate();
        // Repainting the center and Pokemon list panels
        centerPanel.repaint();
        pokemonList.repaint();
        // Adding the center panel to the frame
        frame.add(centerPanel, BorderLayout.CENTER);
        // Setting the layout of the Pokemon list panel
        pokemonList.setLayout(new GridLayout(1,6));
        // Scaling the image and adding it to the Pokemon list panel
        BufferedImage image = new ProcessingImage().scaleImage(85, 85, "Images/PokeBall.png");
        pokemonList.add(new JLabel(new ImageIcon(image)));
        // Adding the Pokemon list to the frame
        frame.add(pokemonList, BorderLayout.SOUTH);
        // Creating a bold font for UI text
        Font fontBold = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        // Adding a border and title to the center panel
        centerPanel.setBorder(new TitledBorder(new MatteBorder(15, 15, 15, 15, Color.ORANGE), "Map", 0, 0, fontBold));
        // Adding a border and title to the Pokemon list panel
        pokemonList.setBorder(new TitledBorder(new MatteBorder(15, 15, 15, 15, Color.GREEN), "Your Team", 0, 0, fontBold));
        // Disabling resizing of the window
        frame.setResizable(false);
        // Centering the window on the screen
        frame.setLocationRelativeTo(null);
        // Making the frame visible
        frame.setVisible(true);
    }
    // Creating a method to display the battle screen with player and opponent information
    public void battleDisplay(Pokemon playerPokemonInBattle, Pokemon opponentPokemonInBattle){
        // Creating an instance of the image loader
        ProcessingImage imageLoader = new ProcessingImage();
        // Setting the battling flag to true
        isBattling = true;
        // Clearing the center panel and revalidating it
        centerPanel.removeAll();
        centerPanel.revalidate();
        // Clearing the Pokemon list panel and revalidating it
        pokemonList.removeAll();
        pokemonList.revalidate();
        // Creating a bold font for UI text
        Font fontBold = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        // Adding a border and title to the center panel
        centerPanel.setBorder(new TitledBorder(new MatteBorder(15, 15, 15, 15, Color.LIGHT_GRAY), "Battle", 0, 0, fontBold));
        // Adding a border and title to the Pokemon list panel
        pokemonList.setBorder(new TitledBorder(new MatteBorder(15, 15, 15, 15, Color.DARK_GRAY), "Options", 0, 0, fontBold));
        // Repainting the center and Pokemon list panels
        centerPanel.repaint();
        pokemonList.repaint();
        // Adding the battle background image to the Pokemon list panel
        pokemonList.add(new JLabel(new ImageIcon(imageLoader.scaleImage(275, 275, "Images/PokeBall.png"))));
        // Setting the layout of the center panel for the battle display
        centerPanel.setLayout(new GridLayout(1, 3));
        // Creating a panel for the player’s Pokemon
        JPanel playerPokemon = new JPanel();
        // Creating a label for the attack image
        attackImage = new JLabel();
        // Creating a panel for the opponent’s Pokemon
        JPanel opponentPokemon = new JPanel();
        // Setting the layout of the player’s and opponent’s Pokemon panels
        playerPokemon.setLayout(new BorderLayout());
        opponentPokemon.setLayout(new BorderLayout());
        // Creating a panel for the player’s health bar
        JPanel playerHealthPanel = new JPanel();
        playerHealthPanel.setLayout(new BorderLayout());
        // Creating health bars for the player and opponent
        playerHealthBar = new HealthBar();
        opponentHealthBar = new HealthBar();
        // Displaying the player's health
        playerHealthText = new JLabel("HP: " + playerPokemonInBattle.retrieveHealth() + "/" + playerPokemonInBattle.retrieveHealth());
        playerHealthText.setFont(fontBold);
        // Displaying the player’s Pokemon image
        playerPokemonImage = new JLabel(new ImageIcon(imageLoader.strToURL("Images/Pokemon/" + playerPokemonInBattle.retrievePokemonName() + ".png")));
        // Displaying the opponent’s Pokemon image
        opponentPokemonImage = new JLabel(new ImageIcon(imageLoader.strToURL("Images/Pokemon/" + opponentPokemonInBattle.retrievePokemonName() + ".png")));
        // Creating a panel for the opponent’s health bar
        JPanel opponentHealthPanel = new JPanel();
        opponentHealthPanel.setLayout(new BorderLayout());
        // Displaying the opponent’s health
        opponentHealthText = new JLabel("HP: " + opponentPokemonInBattle.retrieveHealth() + "/" + opponentPokemonInBattle.retrieveHealth());
        opponentHealthText.setFont(fontBold);
        // Adding the player’s health bar and text to the player’s health panel
        playerHealthPanel.add(playerHealthBar, BorderLayout.CENTER);
        playerHealthPanel.add(playerHealthText, BorderLayout.SOUTH);
        // Adding the opponent’s health bar and text to the opponent’s health panel
        opponentHealthPanel.add(opponentHealthBar, BorderLayout.CENTER);
        opponentHealthPanel.add(opponentHealthText, BorderLayout.SOUTH);
        // Displaying the player’s Pokemon name and level
        playerPokemonName = new JLabel(playerPokemonInBattle.retrievePokemonName() + " (Level " + playerPokemonInBattle.retrieveLevel() + ")");
        playerPokemonName.setFont(fontBold);
        // Displaying the opponent’s Pokemon name and level
        opponentPokemonName = new JLabel(opponentPokemonInBattle.retrievePokemonName() + " (Level " + opponentPokemonInBattle.retrieveLevel() + ")");
        opponentPokemonName.setFont(fontBold);
        // Adding the player’s health panel, image, and name to the player’s Pokemon panel
        playerPokemon.add(playerHealthPanel, BorderLayout.NORTH);
        playerPokemon.add(playerPokemonImage, BorderLayout.CENTER);
        playerPokemon.add(playerPokemonName, BorderLayout.SOUTH);
        // Adding the opponent’s health panel, image, and name to the opponent’s Pokemon panel
        opponentPokemon.add(opponentHealthPanel, BorderLayout.NORTH);
        opponentPokemon.add(opponentPokemonImage, BorderLayout.CENTER);
        opponentPokemon.add(opponentPokemonName, BorderLayout.SOUTH);
        // Centering the text for health and Pokemon names
        playerHealthText.setHorizontalAlignment(SwingConstants.CENTER);
        opponentHealthText.setHorizontalAlignment(SwingConstants.CENTER);
        playerPokemonName.setHorizontalAlignment(SwingConstants.CENTER);
        opponentPokemonName.setHorizontalAlignment(SwingConstants.CENTER);
        // Adding the player’s Pokemon panel, attack image, and opponent’s Pokemon panel to the center panel
        centerPanel.add(playerPokemon);
        centerPanel.add(attackImage);
        centerPanel.add(opponentPokemon);
    }
}