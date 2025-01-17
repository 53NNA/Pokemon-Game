import java.util.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.awt.image.*;

// Creating a class to store and manage all Pokémon data
public class AllPokemon {
    // Declaring a static ArrayList to hold Pokémon objects
    public static ArrayList<Pokemon> pokemon = new ArrayList<>();
    // Creating a constructor to initialize the Pokémon list with predefined Pokémon
    public AllPokemon() {
        // Adding a Pokémon object for Bulbasaur to the list
        // Assigning the stats (name, typeOfPokemon, baseHealth, baseAttack, baseDefense, baseSpeed, evolutionLevel, nextEvolutionLevel, catchRate, appearanceRate, evolution, index, and startAttacks)
        pokemon.add(new Pokemon(
            "Bulbasaur", "Grass", 45, 50, 50, 45, 1, 7, 8, 2, 1, 0,
            new Attack[]{new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50)}
        ));
        pokemon.add(new Pokemon(
            "Ivysaur", "Grass", 60, 80, 80, 60, 7, 9, 8, 0, 2, 1,
            new Attack[]{new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50), new Attack("Vine Whip", "Grass", 55), new Attack("Take Down", "Normal", 90)}
        ));
        pokemon.add(new Pokemon(
            "Venusaur", "Grass", 80, 100, 100, 80, 9, 101, 8, 0, 101, 2,
            new Attack[]{new Attack("Razor Leaf", "Grass", 65), new Attack("Double-Edge", "Normal", 120), new Attack("Vine Whip", "Grass", 55), new Attack("Take Down", "Normal", 90)}
        ));
        pokemon.add(new Pokemon(
            "Charmander", "Fire", 50, 65, 50, 65, 1, 6, 8, 1, 4, 3,
            new Attack[]{new Attack("Scratch", "Normal", 50), new Attack("Scratch", "Normal", 50), new Attack("Scratch", "Normal", 50), new Attack("Scratch", "Normal", 50)}
        ));
        pokemon.add(new Pokemon(
            "Charmeleon", "Fire", 65, 80, 65, 80, 6, 9, 8, 0, 5, 4,
            new Attack[]{new Attack("Scratch", "Normal", 50), new Attack("Scratch", "Normal", 50), new Attack("Ember", "Fire", 40), new Attack("Fire Fang", "Fire", 70)}
        ));
        pokemon.add(new Pokemon(
            "Charizard", "Fire", 90, 110, 85, 100, 9, 101, 8, 0, 101, 5,
            new Attack[]{new Attack("Ember", "Fire", 40), new Attack("Fire Fang", "Fire", 70), new Attack("Inferno", "Fire", 120), new Attack("Flame Burst", "Fire", 80)}
        ));
        pokemon.add(new Pokemon(
            "Squirtle", "Water", 50, 65, 65, 40, 1, 7, 8, 2, 7, 6,
            new Attack[]{new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50)}
        ));
        pokemon.add(new Pokemon(
            "Wartortle", "Water", 60, 65, 80, 60, 7, 9, 8, 0, 8, 7,
            new Attack[]{new Attack("Water Gun", "Water", 50), new Attack("Bite", "Dark", 60), new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50)}
        ));
        pokemon.add(new Pokemon(
            "Blastoise", "Water", 80, 85, 105, 80, 9, 101, 8, 0, 101, 8,
            new Attack[]{new Attack("Bite", "Dark", 60), new Attack("Water Pulse", "Water", 70), new Attack("Aqua Tail", "Water", 90), new Attack("Wave Crash", "Water", 120)}
        ));
        pokemon.add(new Pokemon(
            "Fletchling", "Normal", 40, 45, 35, 60, 1, 7, 30, 1, 10, 9,
            new Attack[]{new Attack("Quick Attack", "Normal", 40), new Attack("Quick Attack", "Normal", 40), new Attack("Quick Attack", "Normal", 40), new Attack("Quick Attack", "Fire", 40)}
        ));
        pokemon.add(new Pokemon(
            "Fletchinder", "Normal", 60, 60, 55, 90, 7, 9, 15, 1, 11, 10,
            new Attack[]{new Attack("Quick Attack", "Normal", 40), new Attack("Quick Attack", "Normal", 40), new Attack("Aerial Ace", "Normal", 60), new Attack("Ember", "Fire", 40)}
        ));
        pokemon.add(new Pokemon(
            "Talonflame", "Normal", 80, 80, 70, 130, 9, 101, 5, 2, 101, 11,
            new Attack[]{new Attack("Brave Bird", "Normal", 120), new Attack("Fly", "Normal", 90), new Attack("Aerial Ace", "Normal", 60), new Attack("Ember", "Fire", 40)}
        ));
        pokemon.add(new Pokemon(
            "Turtwig", "Grass", 45, 50, 55, 30, 1, 7, 20, 1, 13, 12,
            new Attack[]{new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50)}
        ));
        pokemon.add(new Pokemon(
            "Grotle", "Grass", 70, 65, 85, 40, 7, 9, 7, 1, 14, 13,
            new Attack[]{new Attack("Razor Leaf", "Grass", 65), new Attack("Bite", "Dark", 60), new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50)}
        ));
        pokemon.add(new Pokemon(
            "Torterra", "Grass", 90, 100, 110, 55, 9, 101, 4, 2, 101, 14,
            new Attack[]{new Attack("Razor Leaf", "Grass", 65), new Attack("Bite", "Dark", 60), new Attack("Crunch", "Dark", 80), new Attack("Leaf Storm", "Grass", 110)}
        ));
        pokemon.add(new Pokemon(
            "Litten", "Fire", 45, 55, 50, 70, 1, 7, 14, 1, 16, 15,
            new Attack[]{new Attack("Scratch", "Normal", 50), new Attack("Scratch", "Normal", 50), new Attack("Scratch", "Normal", 50), new Attack("Scratch", "Normal", 50)}
        ));
        pokemon.add(new Pokemon(
            "Torracat", "Fire", 65, 85, 55, 80, 7, 9, 7, 1, 17, 16,
             new Attack[]{new Attack("Bite", "Dark", 60), new Attack("Fire Fang", "Fire", 70), new Attack("Scratch", "Normal", 50), new Attack("Scratch", "Normal", 50)}
        ));
        pokemon.add(new Pokemon(
            "Incineroar", "Fire", 95, 110, 80, 90, 9, 101, 3, 2, 101, 17,
            new Attack[]{new Attack("Bite", "Dark", 60), new Attack("Fire Fang", "Fire", 70), new Attack("Thrash", "Normal", 120), new Attack("Flamethrower", "Fire", 90)}
        ));
        pokemon.add(new Pokemon(
            "Mudkip", "Water", 50, 60, 50, 40, 1, 7, 17, 1, 19, 18,
            new Attack[]{new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50)}
        ));
        pokemon.add(new Pokemon(
            "Marshtomp", "Water", 70, 75, 55, 50, 7, 9, 9, 1, 20, 19,
            new Attack[]{new Attack("Water Pulse", "Water", 60), new Attack("Water Gun", "Water", 40), new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50)}
        ));
        pokemon.add(new Pokemon(
            "Swampert", "Water", 85, 105, 70, 65, 9, 101, 7, 2, 101, 20,
            new Attack[]{new Attack("Water Pulse", "Water", 60), new Attack("Water Gun", "Water", 40), new Attack("Muddy Water", "Water", 90), new Attack("Hydro Pump", "Water", 110)}
        ));
        pokemon.add(new Pokemon(
            "Frigibax", "Ice", 65, 70, 35, 55, 1, 7, 18, 1, 22, 21,
            new Attack[]{new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50)}
        ));
        pokemon.add(new Pokemon(
            "Arctibax", "Ice", 80, 90, 50, 60, 7, 9, 12, 1, 23, 22,
            new Attack[]{new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50), new Attack("Bite", "Dark", 60), new Attack("Ice Fang", "Ice", 65)}
        ));
        pokemon.add(new Pokemon(
            "Baxcalibur", "Ice", 110, 120, 65, 65, 9, 101, 3, 2, 101, 23,
            new Attack[]{new Attack("Ice Beam", "Ice", 90), new Attack("Crunch", "Dark", 80), new Attack("Bite", "Dark", 60), new Attack("Ice Fang", "Ice", 65)}
        ));
        pokemon.add(new Pokemon(
            "Tynamo", "Electric", 35, 50, 40, 60, 1, 7, 45, 1, 25, 24,
            new Attack[]{new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50)}
        ));
        pokemon.add(new Pokemon(
            "Eelektrik", "Electric", 65, 80, 60, 65, 7, 9, 19, 1, 26, 25,
            new Attack[]{new Attack("Tackle", "Normal", 50), new Attack("Tackle", "Normal", 50), new Attack("Charge Beam", "Electric", 55), new Attack("Headbutt", "Normal", 70)}
        ));
        pokemon.add(new Pokemon(
            "Eelektross", "Electric", 85, 100, 80, 70, 9, 101, 9, 2, 101, 26,
            new Attack[]{new Attack("Zap Cannon", "Electric", 105), new Attack("Thrash", "Normal", 120), new Attack("Charge Beam", "Electric", 55), new Attack("Headbutt", "Normal", 70)}
        ));
        pokemon.add(new Pokemon(
            "Ralts", "Psychic", 30, 25, 30, 40, 1, 7, 26, 1, 28, 27,
            new Attack[]{new Attack("Disarming Voice", "Fairy", 40), new Attack("Disarming Voice", "Fairy", 40), new Attack("Disarming Voice", "Fairy", 40), new Attack("Disarming Voice", "Fairy", 40)}
        ));
        pokemon.add(new Pokemon(
            "Kirlia", "Psychic", 40, 50, 60, 55, 7, 9, 22, 1, 29, 28,
            new Attack[]{new Attack("Psybeam", "Psychic", 65), new Attack("Dream Eater", "Psychic", 100), new Attack("Disarming Voice", "Fairy", 40), new Attack("Disarming Voice", "Fairy", 40)}
        ));
        pokemon.add(new Pokemon(
            "Gardevoir", "Psychic", 70, 70, 85, 85, 9, 101, 13, 2, 101, 29,
            new Attack[]{new Attack("Psybeam", "Psychic", 65), new Attack("Dream Eater", "Psychic", 100), new Attack("Future Sight", "Psychic", 120), new Attack("Moonblast", "Fairy", 105)}
        ));
        pokemon.add(new Pokemon(
            "Sandile", "Dark", 50, 70, 35, 65, 1, 7, 15, 1, 31, 30,
            new Attack[]{new Attack("Bite", "Dark", 60), new Attack("Bite", "Dark", 60), new Attack("Bite", "Dark", 60), new Attack("Bite", "Dark", 60)}
        ));
        pokemon.add(new Pokemon(
            "Krokorok", "Dark", 65, 80, 45, 75, 7, 9, 11, 1, 32, 31,
            new Attack[]{new Attack("Bite", "Dark", 60), new Attack("Bite", "Dark", 60), new Attack("Crunch", "Dark", 80), new Attack("Foul Play", "Dark", 95)}
        ));
        pokemon.add(new Pokemon(
            "Krookodile", "Dark", 95, 120, 80, 90, 9, 101, 5, 2, 101, 32,
            new Attack[]{new Attack("Thrash", "Normal", 120), new Attack("Earthquake", "Normal", 100), new Attack("Crunch", "Dark", 80), new Attack("Foul Play", "Dark", 95)}
        ));
        pokemon.add(new Pokemon(
            "Tinkatink", "Fairy", 50, 45, 70, 60, 1, 7, 14, 1, 34, 33,
            new Attack[]{new Attack("Covet", "Normal", 60), new Attack("Covet", "Normal", 60), new Attack("Covet", "Normal", 60), new Attack("Covet", "Normal", 60)}
        ));
        pokemon.add(new Pokemon(
            "Tinkatuff", "Fairy", 65, 55, 85, 80, 7, 9, 12, 1, 35, 34,
            new Attack[]{new Attack("Draining Kiss", "Fairy", 50), new Attack("Brutal Swing", "Dark", 70), new Attack("Covet", "Normal", 60), new Attack("Covet", "Normal", 60)}
        ));
        pokemon.add(new Pokemon(
            "Tinkaton", "Fairy", 95, 75, 105, 95, 9, 101, 6, 2, 100, 35,
            new Attack[]{new Attack("Draining Kiss", "Fairy", 50), new Attack("Brutal Swing", "Dark", 70), new Attack("Play Rough", "Fairy", 90), new Attack("Skitter Smack", "Grass", 70)}
        ));
    }
    // Creating a method to handle a wild Pokémon encounter
    public void encounterPokemon(int averageLevel) {
        // Generating a random number from 0 to 6 to adjust the level of the encountered Pokémon
        int randomNum1 = new Random().nextInt(7);
        // Initializing a variable to store the level adjustment
        int levelAdjustment = 0;
        // Using a switch statement to determine the level adjustment based on the random number
        switch (randomNum1) {
            // Reducing the level by 3, 2, 1, or nothing, or increasing the level by 1, 2, 3, depending on the case
            case 0:
                levelAdjustment -= 3;
                break;
            case 1:
                levelAdjustment -= 2;
                break;
            case 2:
                levelAdjustment -= 1;
                break;
            case 4:
                levelAdjustment += 1;
                break;
            case 5:
                levelAdjustment += 2;
                break;
            case 6:
                levelAdjustment += 3;
                break;
            default:
                break;
        }
        // Initializing a variable to hold the selected Pokémon
        Pokemon selectedPokemon = null;
        // Generating a random number from 0 to 99 to determine the rarity of the Pokémon encountered
        int randomNum2 = new Random().nextInt(100);
        // Creating an if statement to check if a rare Pokémon is encountered
        if (randomNum2 < 1) {
            // Generating a rare Pokémon and setting its level
            selectedPokemon = generateRarePokemon(averageLevel + levelAdjustment);
        }
        // Creating an else if statement to check if a common Pokémon is encountered
        else if (randomNum2 < 35) {
            // Generating a common Pokémon and setting its level
            selectedPokemon = generateCommonPokemon(averageLevel + levelAdjustment);
        }
        // Creating an if statement to check if a Pokémon was successfully encountered
        if (randomNum2 < 35) {
            // Loading the Pokémon's image from the file system
            String file = "Images/Pokemon/" + selectedPokemon.retrievePokemonName() + ".png";
            BufferedImage icon = new ProcessingImage().scaleImage(75, 75, file);
            // Prompting the player with a dialog box to fight the encountered Pokémon
            int response = JOptionPane.showConfirmDialog(GameGraphics.frame, "You found a wild " + selectedPokemon.retrievePokemonName() + "!\nFight?", "Wild Pokémon Discovered", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(icon));
            // Creating an if statement to check if the player chooses to fight
            if (response == JOptionPane.YES_OPTION) {
                // Initiating a battle with the encountered Pokémon
                GameManager.game.battlePokemon(selectedPokemon);
            }
        }
    }
    // Creating a method to assemble a Pokémon team for a trainer
    public ArrayList<Pokemon> assembleTrainerPokemon(int averageLevel, int teamSize) {
        // Initializing an ArrayList to hold the trainer's Pokémon team
        ArrayList<Pokemon> trainerTeam = new ArrayList<>();
        // Creating a for loop to generate the specified number of Pokémon for the team
        for (int x = 0; x < teamSize; x++) {
            // Generating a random number from 0 to 3 to determine the level adjustment
            int randomNum3 = new Random().nextInt(4);
            // Initializing a variable to store the level adjustment
            int levelAdjustment = 0;
            // Using a switch statement to adjust the level based on the random number
            switch (randomNum3) {
                // Reducing the level by 2, 1, or nothing, or increasing the level by 1, depending on the case
                case 0:
                    levelAdjustment -= 2; // Reducing the level by 2
                    break;
                case 1:
                    levelAdjustment -= 1; // Reducing the level by 1
                    break;
                case 2:
                    levelAdjustment += 1; // Increasing the level by 1
                    break;
                default:
                    break;
            }
            // Generating a random number from 0 to 99 to determine the rarity of the Pokémon
            int randomNum4 = new Random().nextInt(100);
            // Initializing a boolean to ensure the Pokémon's level is valid
            boolean highLevel = true;
            // Initializing a variable to hold the selected Pokémon
            Pokemon selectedPokemon = null;
            // Creating an if statement to check if a rare Pokémon should be added
            if (randomNum4 < 10) {
                // Using a while loop to generate a valid rare Pokémon
                while (highLevel) {
                    selectedPokemon = generateRarePokemon(averageLevel + levelAdjustment);
                    if (averageLevel + 1 >= selectedPokemon.retrieveLevel()) {
                        // Exiting the loop once a valid Pokémon is generated
                        highLevel = false;
                    }
                }
            }
            // Creating an else statement for common Pokémon generation
            else {
                // Using a while loop to generate a valid common Pokémon
                while (highLevel) {
                    selectedPokemon = generateCommonPokemon(averageLevel + levelAdjustment);
                    if (averageLevel + 1 >= selectedPokemon.retrieveLevel()) {
                        // Exiting the loop once a valid Pokémon is generated
                        highLevel = false;
                    }
                }
            }
            // Adding the selected Pokémon to the trainer's team
            trainerTeam.add(selectedPokemon);
        }
        // Returning the trainer's complete team
        return trainerTeam;
    }
    // Creating a method to generate a common Pokémon
    public Pokemon generateCommonPokemon(int level) {
        // Initializing an ArrayList to hold all common Pokémon
        ArrayList<Pokemon> common = new ArrayList<>();
        // Creating a for loop to iterate through all available Pokémon
        for (int x = 0; x < pokemon.size(); x++) {
            // Creating an if statement to check if the Pokémon is common
            if (pokemon.get(x).retrieveAppearanceRate() == 1) {
                // Adding the Pokémon to the common Pokémon list
                common.add(pokemon.get(x));
            }
        }
        // Generating a random index to select a Pokémon from the common list
        int randomNum5 = new Random().nextInt(common.size());
        // Updating the level of the selected Pokémon
        common.get(randomNum5).updateLevel(level);
        // Returning the selected Pokémon
        return common.get(randomNum5);
    }
    // Creating a method to generate a rare Pokémon
    public Pokemon generateRarePokemon(int level) {
        // Initializing an ArrayList to hold all rare Pokémon
        ArrayList<Pokemon> rare = new ArrayList<>();
        // Creating a for loop to iterate through all available Pokémon
        for (int x = 0; x < pokemon.size(); x++) {
            // Creating an if statement to check if the Pokémon is rare
            if (pokemon.get(x).retrieveAppearanceRate() == 2) {
                // Adding the Pokémon to the rare Pokémon list
                rare.add(pokemon.get(x));
            }
        }
        // Generating a random index to select a Pokémon from the rare list
        int randomNum6 = new Random().nextInt(rare.size());
        // Updating the level of the selected Pokémon
        rare.get(randomNum6).updateLevel(level);
        // Returning the selected Pokémon
        return rare.get(randomNum6);
    }
}