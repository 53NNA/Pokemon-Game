import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.awt.image.*;

// Creating a class to read, modify, and implement all the attributes of a Pokémon
public class Pokemon {
    // Declaring variables to represent the Pokémon's health, attack, defense, speed, level, XP, level at which the Pokémon must be to reach this stage, level at which the Pokémon must be to evolve, catch rate, appearance rate, type of Pokémon, index, evolution, and the current health of the Pokémon in battle
    public int health, attack, defense, speed, level, XP, evolutionLevel, nextEvolutionLevel, lastXP;
    public String pokemonName;
    public double catchRate;
    public int appearanceRate;
    public String typeOfPokemon;
    public int index;
    public int evolution;
    public int temporaryHealth;
    public ImageIcon image;
    //Declaring an array that will contain all the attacks that the Pokémon has
    public Attack[] attacks;
    public Pokemon(String name, String typeOfPokemon, int baseHealth, int baseAttack, int baseDefense, int baseSpeed, int evolutionLevel, int nextEvolutionLevel, double catchRate, int appearanceRate, int evolution, int index, Attack[] startAttacks) {
        // Setting the instance variable pokemonName to the provided parameter name
        pokemonName = name;
        // Retrieving the image of the Pokémon based on the provided name
        String imageFilePath = "Images/Pokemon/" + name + ".png";
        image = new ImageIcon(new ProcessingImage().strToURL(imageFilePath));
        // Setting the instance variables health, attack, defense, speed, and level to the provided parameters baseHealth, baseAttack, baseDefense, baseSpeed, and evolutionLevel
        health = baseHealth;
        attack = baseAttack;
        defense = baseDefense;
        speed = baseSpeed;
        level = evolutionLevel;
        // Initializing XP to 0 and lastXP to 0
        XP = 0;
        lastXP = 0;
        // Setting more instance variables and differentiating them from the ones used in the constructor through the usage of "this."
        this.evolutionLevel = evolutionLevel;
        this.nextEvolutionLevel = nextEvolutionLevel;
        this.catchRate = catchRate;
        this.appearanceRate = appearanceRate;
        this.typeOfPokemon = typeOfPokemon;
        this.index = index;
        this.evolution = evolution;
        // Setting the instance variable attacks to the provided parameter startAttacks
        attacks = startAttacks;
    }
    // Creating a constructor to generate a Pokémon from the AllPokemon that can be manipulated without affecting the AllPokemon
    public Pokemon(int index) {
        // Obtaining all information from the provided index and storing it into the instance variables
        pokemonName = AllPokemon.pokemon.get(index).retrievePokemonName();
        this.image = new ImageIcon(new ProcessingImage().strToURL("Images/Pokemon/" + pokemonName + ".png"));
        this.typeOfPokemon = AllPokemon.pokemon.get(index).retrieveTypeOfPokemon();
        this.evolutionLevel = AllPokemon.pokemon.get(index).retrieveEvolutionLevel();
        this.nextEvolutionLevel = AllPokemon.pokemon.get(index).retrieveNextEvolutionLevel();
        this.catchRate = AllPokemon.pokemon.get(index).retrieveCatchRate();
        this.appearanceRate = AllPokemon.pokemon.get(index).retrieveAppearanceRate();
        this.evolution = AllPokemon.pokemon.get(index).retrieveEvolution();
        this.index = index;
        this.level = AllPokemon.pokemon.get(index).retrieveEvolutionLevel();
        XP = 0;
        lastXP = 0;
        this.health = AllPokemon.pokemon.get(index).retrieveHealth();
        this.attack = AllPokemon.pokemon.get(index).retrieveAttack();
        this.defense = AllPokemon.pokemon.get(index).retrieveDefense();
        this.speed = AllPokemon.pokemon.get(index).retrieveSpeed();
        this.attacks = AllPokemon.pokemon.get(index).retrieveAttacks();
    }
    // Creating another constructor to generate a Pokémon with initalized attributes
    public Pokemon(int index, int level, int XP, int lastXP, int health, int attack, int defense, int speed, Attack[] attacks) {
        pokemonName = AllPokemon.pokemon.get(index).retrievePokemonName();
        this.image = new ImageIcon(new ProcessingImage().strToURL("Images/Pokemon/" + pokemonName + ".png"));
        this.typeOfPokemon = AllPokemon.pokemon.get(index).retrieveTypeOfPokemon();
        this.evolutionLevel = AllPokemon.pokemon.get(index).retrieveEvolutionLevel();
        this.nextEvolutionLevel = AllPokemon.pokemon.get(index).retrieveNextEvolutionLevel();
        this.catchRate = AllPokemon.pokemon.get(index).retrieveCatchRate();
        this.appearanceRate = AllPokemon.pokemon.get(index).retrieveAppearanceRate();
        this.evolution = AllPokemon.pokemon.get(index).retrieveEvolution();
        this.index = index;
        this.level = level;
        this.XP = XP;
        this.lastXP = lastXP;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.attacks = attacks;
    }
    // Creating a method to increase the Pokémon's XP and handle level progression
    public void gainXP(int XP) {
        // Incrementing the Pokémon's XP by the provided XP value
        this.XP += XP;
        // Creating an if statement to check if the Pokémon's level is less than 100 before showing the XP gain message
        if (level < 100) {
            // Displaying the XP gain message to the player
            JOptionPane.showMessageDialog(GameGraphics.pokemonList, pokemonName + " gained " + XP + " XP!", "", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(new ProcessingImage().strToURL("Images/PokeBall.png")));
        }
        // Creating if statements to check for specific XP thresholds required for leveling up at different levels
        if (level < 5) {
            // Triggering level-up if the Pokémon's XP exceeds the last recorded XP by 1
            if (this.XP - lastXP >= 1) {
                levelUp();
            }
        }
        else if (level < 10) {
            // Triggering level-up if the Pokémon's XP exceeds the last recorded XP by more than 10
            if (this.XP - lastXP > 10) {
                levelUp();
            }
        }
        else if (level < 50) {
            // Triggering level-up if the Pokémon's XP exceeds the last recorded XP by more than 30
            if (this.XP - lastXP > 30) {
                levelUp();
            }
        }
        else if (level < 100) {
            // Triggering level-up if the Pokémon's XP exceeds the last recorded XP by more than 100
            if (this.XP - lastXP > 100) {
                levelUp();
            }
        }
    }
    // Creating a method to handle the Pokémon's level-up process, including stat increases and possible evolution
    public void levelUp() {
        // Storing the current XP as the last recorded XP
        lastXP = XP;
        // Increasing the Pokémon's level by 1
        level++;
        // Updating the player's Pokémon name with the new level
        GameGraphics.playerPokemonName.setText(pokemonName + " (Level " + level + ")");
        // Stopping the battle sound and playing the victory sound
        Sound.BattleAgainstTrainer.stop();
        Sound.VictoryAgainstTrainer.play();
        // Displaying the level-up message to the player
        JOptionPane.showMessageDialog(GameGraphics.pokemonList, pokemonName + " leveled up to level " + level + "!", pokemonName + " leveled up!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(new ProcessingImage().strToURL("Images/PokeBall.png")));
        // Creating an if statement to apply stat changes based on the Pokémon's level
        if (level < 7) {
            // Increasing the Pokémon's stats (attack, defense, speed, health) by 1 for levels below 7
            attack += 1;
            defense += 1;
            speed += 1;
            health += 1;
            // Displaying the stat changes message to the player
            JOptionPane.showMessageDialog(GameGraphics.pokemonList, "Attack + 1\nDefense + 1\nSpeed + 1\nHealth + 1", "Stat Changes", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(new ProcessingImage().strToURL("Images/PokeBall.png")));
        }
        else {
            // Increasing the Pokémon's stats (attack, defense, speed, health) by 4 for levels 7 and above
            attack += 4;
            defense += 4;
            speed += 4;
            health += 4;
            // Displaying the stat changes message to the player
            JOptionPane.showMessageDialog(GameGraphics.pokemonList, "Attack + 4\nDefense + 4\nSpeed + 4\nHealth + 4", "Stat Changes", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(new ProcessingImage().strToURL("Images/PokeBall.png")));
        }
        // Creating an if statement to trigger evolution if the Pokémon reaches its next evolution level
        if (level == nextEvolutionLevel) {
            evolve();
        }
        // Stopping the victory sound and resuming the battle sound
        Sound.VictoryAgainstTrainer.stop();
        Sound.BattleAgainstTrainer.play();
    }
    // Creating a method to handle the evolution of the Pokémon
    public void evolve() {
        // Storing the current Pokémon's name temporarily
        String temporaryPlayerPokemonName = pokemonName;
        // Retrieving the evolved Pokémon based on its evolution stage
        Pokemon evolvedPokemon = AllPokemon.pokemon.get(evolution);
        // Updating various attributes of the Pokémon to match the evolved form
        pokemonName = evolvedPokemon.retrievePokemonName();
        typeOfPokemon = evolvedPokemon.retrieveTypeOfPokemon();
        index = evolvedPokemon.retrieveIndex();
        image = evolvedPokemon.retrieveImage();
        health = evolvedPokemon.retrieveHealth();
        attack = evolvedPokemon.retrieveAttack();
        defense = evolvedPokemon.retrieveDefense();
        speed = evolvedPokemon.retrieveSpeed();
        level = evolvedPokemon.retrieveEvolutionLevel();
        evolutionLevel = evolvedPokemon.retrieveEvolutionLevel();
        nextEvolutionLevel = evolvedPokemon.retrieveNextEvolutionLevel();
        catchRate = evolvedPokemon.retrieveCatchRate();
        appearanceRate = evolvedPokemon.retrieveAppearanceRate();
        evolution = evolvedPokemon.retrieveEvolution();
        attacks = evolvedPokemon.retrieveAttacks().clone();
        // Constructing the file path for the evolved Pokémon's image
        String file = "Images/Pokemon/" + pokemonName + ".png";
        // Scaling the image for the evolved Pokémon
        BufferedImage image = new ProcessingImage().scaleImage(100, 100, file);
        // Updating the player’s Pokémon image and name in the game interface
        GameGraphics.playerPokemonImage.setIcon(new ImageIcon(file));
        GameGraphics.playerPokemonName.setText(pokemonName + " (Level " + level + ")");
        // Displaying a message to the player confirming the evolution
        JOptionPane.showMessageDialog(GameGraphics.pokemonList, temporaryPlayerPokemonName + " evolved into " + pokemonName + "!", temporaryPlayerPokemonName + " evolved!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(image));
    }
    // Creating a method to increase the Pokémon’s catch rate by a specified amount
    public void increaseCatchRate(int x) {
        // Checking if the catch rate will exceed 100 after the increment
        if (catchRate + x > 100) {
            // Setting the catch rate to 100 if it exceeds the maximum limit
            catchRate = 100;
        }
        else {
            // Adding the specified value to the catch rate
            catchRate += x;
        }
    }
    // Creating a method to update the Pokémon’s level, ensuring it doesn't exceed 100
    public void updateLevel(int level) {
        // Checking if the level exceeds 100
        if (level > 100) {
            // Setting the level to 100
            this.level = 100;
        }
        else {
            // Ensuring the level is at least the evolution level
            if (level >= evolutionLevel) {
                // Setting the level to the passed value if it's above or equal to the evolution level
                this.level = level;
            }
            else {
                // Setting the level to the evolution level if the passed value is lower
                this.level = evolutionLevel;
            }
        }
        // Updating the Pokémon's stats as it levels up
        for (int x = evolutionLevel; x < this.level; x++) {
            // Increasing stats by 1 if the level is below 7
            if (x < 7) {
                attack += 1;
                defense += 1;
                speed += 1;
                health += 1;
            }
            else {
                // Increasing stats by 4 if the level is 7 or higher
                attack += 4;
                defense += 4;
                speed += 4;
                health += 4;
            }
        }
    }
    // Creating a method to retrieve the Pokémon's health value
    public int retrieveHealth() {
        // Returning the current health of the Pokémon
        return health;
    }
    // Creating a method to retrieve the Pokémon's attack value
    public int retrieveAttack() {
        // Returning the current attack stat of the Pokémon
        return attack;
    }
    // Creating a method to retrieve the Pokémon's defense value
    public int retrieveDefense() {
        // Returning the current defense stat of the Pokémon
        return defense;
    }
    // Creating a method to retrieve the Pokémon's speed value
    public int retrieveSpeed() {
        // Returning the current speed stat of the Pokémon
        return speed;
    }
    // Creating a method to set a temporary health value for the Pokémon
    public void establishTemporaryHealth(int health) {
        // Assigning the provided health value to the temporary health variable
        temporaryHealth = health;
    }
    // Creating a method to retrieve the Pokémon's temporary health value
    public int retrieveTemporaryHealth() {
        // Returning the current temporary health of the Pokémon
        return temporaryHealth;
    }
    // Creating a method to restore the Pokémon's temporary health to its original health
    public void restoreHealth() {
        // Setting the temporary health back to the original health value
        temporaryHealth = health;
    }
    // Creating a method to retrieve the Pokémon's evolution level
    public int retrieveEvolutionLevel() {
        // Returning the current evolution level of the Pokémon
        return evolutionLevel;
    }
    // Creating a method to retrieve the Pokémon's next evolution level
    public int retrieveNextEvolutionLevel() {
        // Returning the level required for the next evolution of the Pokémon
        return nextEvolutionLevel;
    }
    // Creating a method to retrieve the Pokémon's name
    public String retrievePokemonName() {
        // Returning the name of the Pokémon
        return pokemonName;
    }
    // Creating a method to retrieve the Pokémon's catch rate
    public double retrieveCatchRate() {
        // Returning the catch rate of the Pokémon
        return catchRate;
    }
    // Creating a method to retrieve the Pokémon's appearance rate
    public int retrieveAppearanceRate() {
        // Returning the appearance rate of the Pokémon
        return appearanceRate;
    }
    // Creating a method to retrieve the Pokémon's type
    public String retrieveTypeOfPokemon() {
        // Returning the type of the Pokémon (e.g., Fire, Water, etc.)
        return typeOfPokemon;
    }
    // Creating a method to retrieve the Pokémon's level
    public int retrieveLevel() {
        // Returning the current level of the Pokémon
        return level;
    }
    // Creating a method to retrieve the Pokémon's image icon
    public ImageIcon retrieveImage() {
        // Returning the image icon representing the Pokémon
        return image;
    }
    // Creating a method to retrieve the Pokémon's index
    public int retrieveIndex() {
        // Returning the index number associated with the Pokémon
        return index;
    }
    // Creating a method to retrieve the Pokémon's current evolution stage
    public int retrieveEvolution() {
        // Returning the current evolution stage of the Pokémon
        return evolution;
    }
    // Creating a method to check if the Pokémon has an evolution available
    public boolean hasEvolution() {
        // Creating an if statement to check if the Pokémon's next evolution level is less than 101
        if(nextEvolutionLevel < 101) {
            // Returning true if the Pokémon has an available evolution
            return true;
        }
        else {
            // Returning false if the Pokémon does not have an available evolution
            return false;
        }
    }
    // Creating a method to check if the Pokémon is at its first evolution stage
    public boolean isFirstEvolution() {
        // Creating an if statement to check if the Pokémon's evolution level is 1
        if(evolutionLevel == 1) {
            // Returning true if the Pokémon is at its first evolution stage
            return true;
        }
        else {
            // Returning false if the Pokémon is not at its first evolution stage
            return false;
        }
    }
    // Creating a method to retrieve the Pokémon's available attacks
    public Attack[] retrieveAttacks() {
        // Returning the array of attacks available for the Pokémon
        return attacks;
    }
}
