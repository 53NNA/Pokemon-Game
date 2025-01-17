import java.util.*;
import java.io.*;
import java.awt.image.*;
import javax.swing.*;

import javax.swing.ImageIcon;
import javax.swing.Icon;

public class PokemonRadiance {
    // Creating an ArrayList to store the player's Pokémon team
    public ArrayList<Pokemon> team = new ArrayList<Pokemon>();
    // Establishing a static integer variable to store the average level of the player's Pokémon
    public static int averageLevelPlayerPokemon = 5;
    // Creating an instance of the ProcessingImage class to handle image loading and processing
    public ProcessingImage imageLoader = new ProcessingImage();
    // Establishing a method to run the game, which includes displaying introductory messages, instructions, and allowing the player to select their starter Pokémon
    public void runGame(){
        // Creating an ImageIcon object from the PokeBall image using the imageLoader's strToURL method
        ImageIcon icon = new ImageIcon(imageLoader.strToURL("Images/PokeBall.png"));
        // Displaying an introductory message with the game's title and a custom icon
        JOptionPane.showMessageDialog(GameGraphics.frame, "˚✦.⋆✶⋆.✧̣̇˚(Pokemon Radiance)˚✧̣̇.⋆✶⋆.✦˚", "Introduction", JOptionPane.INFORMATION_MESSAGE, icon);
        // Displaying instructions for the game, including how to navigate and interact with the environment
        JOptionPane.showMessageDialog(GameGraphics.frame, "Use the arrow keys to traverse the grounds.\nYou can run into wild Pokémon and you can also battle the trainer.\nPress A to change the order of your team.", "Instructions", JOptionPane.INFORMATION_MESSAGE, icon);
        // Creating an array to store the names of the starter Pokémon
        String[] allStarters = new String[3];
        // Initializing the array with the names of the starter Pokémon
        allStarters[0] = "Bulbasaur (Grass)";
        allStarters[1] = "Charmander (Fire)";
        allStarters[2] = "Squirtle (Water)";
        // Prompting the player to select their starter Pokémon from the array of options
        String starterSelection = (String) JOptionPane.showInputDialog(GameGraphics.frame, "Choose your starter Pokémon.", "Starter", JOptionPane.QUESTION_MESSAGE, icon, allStarters, allStarters[0]);
        // Using a switch statement to determine which starter Pokémon the player selected
        switch (starterSelection) {
            // If the player selected Bulbasaur, adding a new Pokémon with an index of 0 to the team
            case "Bulbasaur (Grass)":
                team.add(new Pokemon(0));
                break;
            // If the player selected Charmander, adding a new Pokémon with an index of 3 to the team
            case "Charmander (Fire)":
                team.add(new Pokemon(3));
                break;
            // If the player selected Squirtle, adding a new Pokémon with an index of 6 to the team
            case "Squirtle (Water)":
                team.add(new Pokemon(6));
                break;
            default:
                break;
        }
        // Updating the level of the first Pokémon in the team to 5
        team.get(0).updateLevel(5);
        // Scaling the image of the selected Pokémon to a size of 85x85 pixels
        BufferedImage image = new ProcessingImage().scaleImage(85,85,"Images/Pokemon/"+team.get(0).retrievePokemonName()+".png");
        // Displaying a message congratulating the player on obtaining their new Pokémon, along with the Pokémon's image
        JOptionPane.showMessageDialog(GameGraphics.frame, "Congratulations! You got a new " + team.get(0).retrievePokemonName() + "!", "New Team Member!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(image));
        // Removing all components from the pokemonList container
        GameGraphics.pokemonList.removeAll();
        // Adding a new JLabel with the image of the selected Pokémon to the pokemonList container
        GameGraphics.pokemonList.add(new JLabel(new ImageIcon(image)));
        // Revalidating the pokemonList container to ensure the new component is displayed correctly
        GameGraphics.pokemonList.revalidate();
    }
    // Establishing a method to handle a battle between the player's Pokémon and a selected Pokémon
    public void battlePokemon(Pokemon selectedPokemon){
        // Stopping the environment sound and playing the battle sound
        Sound.Environment.stop();
        Sound.BattleAgainstPokemon.play();
        // Creating a temporary team to store the player's Pokémon during the battle
        ArrayList<Pokemon> temporaryTeam = new ArrayList<Pokemon>();
        // Adding each Pokémon from the player's team to the temporary team and establishing their temporary health
        for (int y = 0; y < team.size(); y++){
            temporaryTeam.add(team.get(y));
            temporaryTeam.get(y).establishTemporaryHealth(temporaryTeam.get(y).retrieveHealth());
        }
        // Setting the player's current Pokémon to the first Pokémon in the temporary team
        Pokemon player = temporaryTeam.get(0);
        // Displaying the battle display with the player's current Pokémon and the selected Pokémon
        GameManager.gameGraphics.battleDisplay(player,selectedPokemon);
        // Initializing the opponent's remaining health to their full health
        int opponentRemainingHealth = selectedPokemon.retrieveHealth();
        // Initializing a boolean variable to determine whether the battle should continue
        boolean continueBattle = true;
        // Creating an ImageIcon object from the PokeBall image using the imageLoader's strToURL method
        ImageIcon icon = new ImageIcon(imageLoader.strToURL("Images/PokeBall.png"));
        // Displaying a message to start the battle
        JOptionPane.showMessageDialog(GameGraphics.frame, "Go " + player.retrievePokemonName() + "!", "", JOptionPane.INFORMATION_MESSAGE, icon);
        // Entering a while loop to continue the battle until the opponent's Pokémon faints or the player's Pokémon faints
        while(continueBattle){
            // Creating an array of choices for the player to select from
            String[] choices = new String[4];
            choices[0] = "Attack";
            choices[1] = "Throw Pokeball";
            choices[2] = "Switch Pokemon";
            choices[3] = "Run";
            // Prompting the player to select an action from the array of choices
            String choice = (String) JOptionPane.showInputDialog(GameGraphics.pokemonList, "What would you like to do?","Your Turn",JOptionPane.QUESTION_MESSAGE,icon,choices,choices[0]);
            // Creating an if statement to check if the player selects the "Attack" option
            if (choices[0].equals(choice)) {
                // Creating an array of attack choices for the player to select from
                String[] attackChoice = new String[4];
                // Retrieving the player's Pokémon's attacks and adding them to the attack choice array
                Attack[] attacks = player.retrieveAttacks();
                attackChoice[0] = attacks[0].retrieveName() + " (Base Damage: " + attacks[0].retrieveBaseDamage() + ", Type: " + attacks[0].retrieveType() + ")";
                attackChoice[1] = attacks[1].retrieveName() + " (Base Damage: " + attacks[1].retrieveBaseDamage() + ", Type: " + attacks[1].retrieveType() + ")";
                attackChoice[2] = attacks[2].retrieveName() + " (Base Damage: " + attacks[2].retrieveBaseDamage() + ", Type: " + attacks[2].retrieveType() + ")";
                attackChoice[3] = attacks[3].retrieveName() + " (Base Damage: " + attacks[3].retrieveBaseDamage() + ", Type: " + attacks[3].retrieveType() + ")";
                // Prompting the player to select an attack from the attack choice array
                String attackChoiceText = (String) JOptionPane.showInputDialog(GameGraphics.pokemonList, "Which attack would you like to use?","Attack Choice",JOptionPane.QUESTION_MESSAGE,icon,attackChoice,attackChoice[0]);
                // Creating an if statement to check if the player selects an attack
                if (attackChoiceText!=null) {
                    // Creating an if statement to check if the player's Pokémon's speed is greater than the opponent's Pokémon's speed
                    if (player.retrieveSpeed() > selectedPokemon.retrieveSpeed()){
                        // Displaying the player's attack image
                        GameGraphics.attackImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Attacks/PlayerAttack.png")));
                        // Calculating the damage dealt by the player's attack
                        int damage = calculateDamage(player.retrieveLevel(), player.retrieveAttack(), selectedPokemon.retrieveDefense(), Integer.parseInt(attackChoiceText.substring(attackChoiceText.indexOf("Base Damage: ")+13, attackChoiceText.indexOf(","))));
                        // If the damage is less than 1, setting it to 1
                        if (damage < 1) {
                            damage = 1;
                        }
                        // Determining the effectiveness of the attack based on the opponent's Pokémon's type
                        String damageMultiplierText = "";
                        if (attackChoice[0].equals(attackChoiceText)) {
                            damageMultiplierText = attacks[0].determineAttackEffectiveness(selectedPokemon.retrieveTypeOfPokemon());
                        }
                        else if (attackChoice[1].equals(attackChoiceText)) {
                            damageMultiplierText = attacks[1].determineAttackEffectiveness(selectedPokemon.retrieveTypeOfPokemon());
                        }
                        else if (attackChoice[2].equals(attackChoiceText)) {
                            damageMultiplierText = attacks[2].determineAttackEffectiveness(selectedPokemon.retrieveTypeOfPokemon());
                        }
                        else if (attackChoice[3].equals(attackChoiceText)) {
                            damageMultiplierText = attacks[3].determineAttackEffectiveness(selectedPokemon.retrieveTypeOfPokemon());
                        }
                        // Calculating the damage multiplier based on the effectiveness of the attack
                        double damageMultiplier = 1;
                        String message = "";
                        switch(damageMultiplierText){
                            case "super effective":
                                damageMultiplier = 2;
                                message = "It's super effective!";
                                break;
                            case "not very effective":
                                message = "It's not very effective...";
                                damageMultiplier = 0.5;
                                break;
                            case "no effect":
                                message = "It doesn't affect " + selectedPokemon.retrievePokemonName() + "...";
                                damageMultiplier = 0;
                                break;
                            default:
                                damageMultiplier = 1;
                                break;
                        }
                        // Calculating the final damage dealt by the player's attack
                        damage = (int) (damageMultiplier*damage);
                        // If the damage is less than 1 and the attack is not very effective, setting it to 1
                        if (damageMultiplierText.equals("not very effective") && damage==0 && Integer.parseInt(attackChoiceText.substring(attackChoiceText.indexOf("Base Damage: ")+13, attackChoiceText.indexOf(",")))!=0) {
                            damage = 1;
                        }
                        // Subtracting the damage from the opponent's remaining health
                        opponentRemainingHealth -= damage;
                        // If the opponent's remaining health is less than 0, setting it to 0
                        if (opponentRemainingHealth < 0) {
                            opponentRemainingHealth = 0;
                        }
                        // Updating the opponent's health text and health bar
                        GameGraphics.opponentHealthText.setText("HP: " + opponentRemainingHealth + "/" + selectedPokemon.retrieveHealth());
                        GameGraphics.opponentHealthBar.loseHealth(opponentRemainingHealth, selectedPokemon.retrieveHealth());
                        // Displaying a message to show the result of the player's attack
                        JOptionPane.showMessageDialog(GameGraphics.pokemonList, player.retrievePokemonName()+" used " + attackChoiceText.substring(0,attackChoiceText.indexOf("(")-1) + "!\n"+message+"\n" + player.retrievePokemonName() + " did " + damage + " damage.", "", JOptionPane.INFORMATION_MESSAGE, icon);
                        // Removing the player's attack image
                        GameGraphics.attackImage.setIcon(null);
                        // Creating an if statement to check if the opponent's Pokémon has fainted
                        if (opponentRemainingHealth==0) {
                            // Displaying a message to show that the opponent's Pokémon has fainted
                            JOptionPane.showMessageDialog(GameGraphics.pokemonList, "The wild " + selectedPokemon.retrievePokemonName() + " fainted!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                        }
                        else {
                            // Creating an if statement to check if the opponent's Pokémon has not fainted
                            // Retrieving the array of attacks available to the opponent's Pokémon
                            Attack[] opponentAttacks = selectedPokemon.retrieveAttacks();
                            // Generating a random index to select an attack from the opponent's attack array
                            int opponentAttackRandomIndex = new Random().nextInt(4);
                            // Selecting the attack at the randomly generated index
                            Attack opponentAttack = opponentAttacks[opponentAttackRandomIndex];
                            // Displaying the opponent's attack image
                            GameGraphics.attackImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Attacks/OpponentAttack.png")));
                            // Calculating the damage dealt by the opponent's attack
                            int damageDealt = calculateDamage(selectedPokemon.retrieveLevel(), selectedPokemon.retrieveAttack(), player.retrieveDefense(), opponentAttack.retrieveBaseDamage());
                            // If the damage is less than 1, setting it to 1
                            if (damageDealt < 1) {
                                damageDealt = 1;
                            }
                            // Determining the effectiveness of the attack based on the player's Pokémon's type
                            String damageMultiplierTextOpponent = opponentAttack.determineAttackEffectiveness(player.retrieveTypeOfPokemon());
                            // Calculating the damage multiplier based on the effectiveness of the attack
                            double damageMultiplierOpponent = 1;
                            String messageOpponent = "";
                            switch(damageMultiplierTextOpponent){
                                case "super effective":
                                    damageMultiplierOpponent = 2;
                                    messageOpponent = "It's super effective!";
                                    break;
                                case "not very effective":
                                    messageOpponent = "It's not very effective...";
                                    damageMultiplierOpponent = 0.5;
                                    break;
                                case "no effect":
                                    messageOpponent = "It doesn't affect " + player.retrievePokemonName() + "...";
                                    damageMultiplierOpponent = 0;
                                    break;
                                default:
                                    damageMultiplierOpponent = 1;
                                    break;
                            }
                            // Calculating the final damage dealt by the opponent's attack
                            damageDealt = (int) (damageMultiplierOpponent*damageDealt);
                            // If the damage is less than 1 and the attack is not very effective, setting it to 1
                            if (damageMultiplierTextOpponent.equals("not very effective") && damageDealt==0 && opponentAttack.retrieveBaseDamage()!=0) {
                                damageDealt = 1;
                            }
                            // Subtracting the damage from the player's Pokémon's temporary health
                            player.establishTemporaryHealth(player.retrieveTemporaryHealth()-damageDealt);
                            // If the player's Pokémon's temporary health is less than or equal to 0, setting it to 0
                            if (player.retrieveTemporaryHealth() <= 0) {
                                player.establishTemporaryHealth(0);
                            }
                            // Updating the player's Pokémon's health text and health bar
                            GameGraphics.playerHealthText.setText("HP: " + player.retrieveTemporaryHealth() + "/" + player.retrieveHealth());
                            GameGraphics.playerHealthBar.loseHealth(player.retrieveTemporaryHealth(), player.retrieveHealth());
                            // Displaying a message to show the result of the opponent's attack
                            JOptionPane.showMessageDialog(GameGraphics.pokemonList, selectedPokemon.retrievePokemonName()+" used "+ opponentAttack.retrieveName() + "!\n"+messageOpponent+"\n"+selectedPokemon.retrievePokemonName()+" did " + damageDealt + " damage.", "", JOptionPane.INFORMATION_MESSAGE, icon);
                            // Removing the opponent's attack image
                            GameGraphics.attackImage.setIcon(null);
                            // Creating an if statement to check if the player's Pokémon has fainted
                            if (player.retrieveTemporaryHealth()==0) {
                                // Removing the player's Pokémon from the temporary team
                                temporaryTeam.remove(player);
                                // Displaying a message to show that the player's Pokémon has fainted
                                JOptionPane.showMessageDialog(GameGraphics.pokemonList, player.retrievePokemonName() + " fainted!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                // Creating an if statement to check if there are other Pokémon in the temporary team
                                if (temporaryTeam.size()>0) {
                                    // Having the player switch to another Pokémon
                                    String[] others = new String[temporaryTeam.size()];
                                    // Adding all the Pokémon in the temporary team to the ArrayList except the player's Pokémon
                                    for (int y = 0; y < temporaryTeam.size(); y++) {
                                        others[y] = temporaryTeam.get(y).retrievePokemonName();
                                    }
                                    // Asking the player which Pokémon they want to switch into
                                    String switchedPokemon = (String) JOptionPane.showInputDialog(GameGraphics.pokemonList, "Which pokemon would you like to switch in?","",JOptionPane.QUESTION_MESSAGE,icon,others,others[0]);
                                    for (int y = 0; y < temporaryTeam.size(); y++) {
                                        // Switching the player's Pokémon to the selected Pokémon and retrieving the updated temporary team
                                        if (temporaryTeam.get(y).retrievePokemonName().equals(switchedPokemon)) {
                                            player = temporaryTeam.get(y);
                                            GameGraphics.playerPokemonImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Pokemon/" + switchedPokemon + ".png")));
                                            GameGraphics.playerPokemonName.setText(switchedPokemon + " (Level " + player.retrieveLevel() + ")");
                                            GameGraphics.playerHealthText.setText("HP: " + player.retrieveTemporaryHealth() + "/" + player.retrieveHealth());
                                            GameGraphics.playerHealthBar.loseHealth(player.retrieveTemporaryHealth(), player.retrieveHealth());
                                            JOptionPane.showMessageDialog(GameGraphics.pokemonList, "Go " + player.retrievePokemonName() + "!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                        }
                                    }
                                }
                                // If there are no other Pokémon in the temporary team, ending the battle
                                else {
                                    continueBattle = false;
                                    JOptionPane.showMessageDialog(GameGraphics.pokemonList, "The wild " + selectedPokemon.retrievePokemonName() + " got away!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                }
                            }
                        }
                    }
                    // Creating an else statement to handle the case where the player's Pokémon's speed is not greater than the opponent's Pokémon's speed
                    else {
                        // Retrieving the array of attacks available to the opponent's Pokémon
                        Attack[] opponentAttacks = selectedPokemon.retrieveAttacks();
                        // Generating a random index to select an attack from the opponent's attack array
                        int opponentAttackRandomIndex = new Random().nextInt(4);
                        // Selecting the attack at the randomly generated index
                        Attack opponentAttack = opponentAttacks[opponentAttackRandomIndex];
                        // Displaying the opponent's attack image
                        GameGraphics.attackImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Attacks/OpponentAttack.png")));
                        // Calculating the damage dealt by the opponent's attack
                        int damageDealt = calculateDamage(selectedPokemon.retrieveLevel(), selectedPokemon.retrieveAttack(), player.retrieveDefense(), opponentAttack.retrieveBaseDamage());
                        // If the damage is less than 1, setting it to 1
                        if (damageDealt < 1) {
                            damageDealt = 1;
                        }
                        // Determining the effectiveness of the attack based on the player's Pokémon's type
                        String damageMultiplierTextOpponent = opponentAttack.determineAttackEffectiveness(player.retrieveTypeOfPokemon());
                        // Calculating the damage multiplier based on the effectiveness of the attack
                        double damageMultiplierOpponent = 1;
                        String messageOpponent = "";
                        switch(damageMultiplierTextOpponent){
                            case "super effective":
                                damageMultiplierOpponent = 2;
                                messageOpponent = "It's super effective!";
                                break;
                            case "not very effective":
                                messageOpponent = "It's not very effective...";
                                damageMultiplierOpponent = 0.5;
                                break;
                            case "no effect":
                                messageOpponent = "It doesn't affect " + player.retrievePokemonName() + "...";
                                damageMultiplierOpponent = 0;
                                break;
                            default:
                                damageMultiplierOpponent = 1;
                                break;
                        }
                        // Calculating the final damage dealt by the opponent's attack
                        damageDealt = (int) (damageMultiplierOpponent*damageDealt);
                        // If the damage is less than 1 and the attack is not very effective, setting it to 1
                        if (damageMultiplierTextOpponent.equals("not very effective") && damageDealt==0 && opponentAttack.retrieveBaseDamage()!=0) {
                            damageDealt = 1;
                        }
                        // Subtracting the damage from the player's Pokémon's temporary health
                        player.establishTemporaryHealth(player.retrieveTemporaryHealth()-damageDealt);
                        // If the player's Pokémon's temporary health is less than or equal to 0, setting it to 0
                        if (player.retrieveTemporaryHealth() <= 0) {
                            player.establishTemporaryHealth(0);
                        }
                        // Updating the player's Pokémon's health text and health bar
                        GameGraphics.playerHealthText.setText("HP: " + player.retrieveTemporaryHealth() + "/" + player.retrieveHealth());
                        GameGraphics.playerHealthBar.loseHealth(player.retrieveTemporaryHealth(), player.retrieveHealth());
                        // Displaying a message to show the result of the opponent's attack
                        JOptionPane.showMessageDialog(GameGraphics.pokemonList, selectedPokemon.retrievePokemonName()+" used "+ opponentAttack.retrieveName() + "!\n"+messageOpponent+"\n"+selectedPokemon.retrievePokemonName()+" did " + damageDealt + " damage.", "", JOptionPane.INFORMATION_MESSAGE, icon);
                        // Removing the opponent's attack image
                        GameGraphics.attackImage.setIcon(null);
                        // Creating an if statement to check if the player's Pokémon has fainted
                        if (player.retrieveTemporaryHealth()==0) {
                            // Removing the player's Pokémon from the temporary team
                            temporaryTeam.remove(player);
                            // Displaying a message to show that the player's Pokémon has fainted
                            JOptionPane.showMessageDialog(GameGraphics.pokemonList, player.retrievePokemonName() + " fainted!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                            // Creating an if statement to check if there are other Pokémon in the temporary team
                            if (temporaryTeam.size()>0) {
                                // Having the player switch to another Pokémon
                                String[] others = new String[temporaryTeam.size()];
                                // Adding all the Pokémon in the temporary team to the ArrayList except the player's Pokémon
                                for (int y = 0; y < temporaryTeam.size(); y++) {
                                    others[y] = temporaryTeam.get(y).retrievePokemonName();
                                }
                                // Asking the player which Pokémon they want to switch into
                                String switchedPokemon = (String) JOptionPane.showInputDialog(GameGraphics.pokemonList, "Which pokemon would you like to switch in?","",JOptionPane.QUESTION_MESSAGE,icon,others,others[0]);
                                for (int y = 0; y < temporaryTeam.size(); y++) {
                                    // Switching the player's Pokémon to the selected Pokémon and retrieving the updated temporary team
                                    if (temporaryTeam.get(y).retrievePokemonName().equals(switchedPokemon)) {
                                        player = temporaryTeam.get(y);
                                        GameGraphics.playerPokemonImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Pokemon/" + switchedPokemon + ".png")));
                                        GameGraphics.playerPokemonName.setText(switchedPokemon + " (Level " + player.retrieveLevel() + ")");
                                        GameGraphics.playerHealthText.setText("HP: " + player.retrieveTemporaryHealth() + "/" + player.retrieveHealth());
                                        GameGraphics.playerHealthBar.loseHealth(player.retrieveTemporaryHealth(), player.retrieveHealth());
                                        JOptionPane.showMessageDialog(GameGraphics.pokemonList, "Go " + player.retrievePokemonName() + "!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                    }
                                }
                            }
                            // If there are no other Pokémon in the temporary team, ending the battle
                            else {
                                continueBattle = false;
                                JOptionPane.showMessageDialog(GameGraphics.pokemonList, "The wild " + selectedPokemon.retrievePokemonName() + " got away!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                            }
                        }
                        // Creating an else statement to handle the case where the player's Pokémon has not fainted
                        else {
                            // Having the player's Pokémon attack the opponent's Pokémon
                            GameGraphics.attackImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Attacks/PlayerAttack.png")));
                            // Calculating the damage dealt by the player's attack
                            int damage = calculateDamage(player.retrieveLevel(), player.retrieveAttack(), selectedPokemon.retrieveDefense(), Integer.parseInt(attackChoiceText.substring(attackChoiceText.indexOf("Base Damage: ")+13, attackChoiceText.indexOf(","))));
                            // If the damage is less than 1, setting it to 1
                            if (damage < 1) {
                                damage = 1;
                            }
                            // Determining the effectiveness of the attack based on the opponent's Pokémon's type
                            String damageMultiplierText = "";
                            if (attackChoice[0].equals(attackChoiceText)) {
                                damageMultiplierText = attacks[0].determineAttackEffectiveness(selectedPokemon.retrieveTypeOfPokemon());
                            }
                            else if (attackChoice[1].equals(attackChoiceText)) {
                                damageMultiplierText = attacks[1].determineAttackEffectiveness(selectedPokemon.retrieveTypeOfPokemon());
                            }
                            else if (attackChoice[2].equals(attackChoiceText)) {
                                damageMultiplierText = attacks[2].determineAttackEffectiveness(selectedPokemon.retrieveTypeOfPokemon());
                            }
                            else if (attackChoice[3].equals(attackChoiceText)) {
                                damageMultiplierText = attacks[3].determineAttackEffectiveness(selectedPokemon.retrieveTypeOfPokemon());
                            }
                            // Calculating the damage multiplier based on the effectiveness of the attack
                            double damageMultiplier = 1;
                            String message = "";
                            switch(damageMultiplierText){
                                case "super effective":
                                    damageMultiplier = 2;
                                    message = "It's super effective!";
                                    break;
                                case "not very effective":
                                    message = "It's not very effective...";
                                    damageMultiplier = 0.5;
                                    break;
                                case "no effect":
                                    message = "It doesn't affect " + selectedPokemon.retrievePokemonName() + "...";
                                    damageMultiplier = 0;
                                    break;
                                default:
                                    damageMultiplier = 1;
                                    break;
                            }
                            // Calculating the final damage dealt by the player's attack
                            damage = (int) (damageMultiplier*damage);
                            // If the damage is less than 1 and the attack is not very effective, setting it to 1
                            if (damageMultiplierText.equals("not very effective") && damage==0 && Integer.parseInt(attackChoiceText.substring(attackChoiceText.indexOf("Base Damage: ")+13, attackChoiceText.indexOf(",")))!=0) {
                                damage = 1;
                            }
                            // Subtracting the damage from the opponent's remaining health
                            opponentRemainingHealth -= damage;
                            // If the opponent's remaining health is less than 0, setting it to 0
                            if (opponentRemainingHealth < 0) {
                                opponentRemainingHealth = 0;
                            }
                            // Updating the opponent's health text and health bar
                            GameGraphics.opponentHealthText.setText("HP: " + opponentRemainingHealth + "/" + selectedPokemon.retrieveHealth());
                            GameGraphics.opponentHealthBar.loseHealth(opponentRemainingHealth, selectedPokemon.retrieveHealth());
                            // Displaying a message to show the result of the player's attack
                            JOptionPane.showMessageDialog(GameGraphics.pokemonList, player.retrievePokemonName()+" used " + attackChoiceText.substring(0,attackChoiceText.indexOf("(")-1) + "!\n"+message+"\n" + player.retrievePokemonName() + " did " + damage + " damage.", "", JOptionPane.INFORMATION_MESSAGE, icon);
                            // Removing the player's attack image
                            GameGraphics.attackImage.setIcon(null);
                            // Creating an if statement to check if the opponent's Pokémon has fainted
                            if (opponentRemainingHealth==0) {
                                // Displaying a message to show that the opponent's Pokémon has fainted
                                JOptionPane.showMessageDialog(GameGraphics.pokemonList, "The wild " + selectedPokemon.retrievePokemonName() + " fainted!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                            }
                        }
                    }
                }
                // Creating an else if statement to handle the case where the player wants to throw a Poke Ball
                else if (choices[1].equals(choice)) {
                    // Creating an else if statement to handle the case where the player selects the "Throw Poke Ball" option
                    GameGraphics.attackImage.setIcon(new ImageIcon(new ProcessingImage().scaleImage(100, 100, "Images/PokeBall.png")));
                    // Displaying a message to show that the player has thrown a Pokeball
                    JOptionPane.showMessageDialog(GameGraphics.pokemonList, "You threw a Poké Ball!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                    // Calculating the chance of catching the opponent's Pokémon
                    double chance = selectedPokemon.retrieveCatchRate();
                    if (opponentRemainingHealth <= selectedPokemon.retrieveHealth()/2) {
                        chance*=2;
                    }
                    else if (opponentRemainingHealth <= selectedPokemon.retrieveHealth()/10) {
                        chance*=4;
                    }
                    // Generating a random number to determine whether the opponent's Pokémon is caught
                    int x = new Random().nextInt(100);
                    // Removing the Pokeball image
                    GameGraphics.attackImage.setIcon(null);
                    // Creating an if statement to check if the opponent's Pokémon is caught
                    if (x<=chance) {
                        // Displaying a message to show that the opponent's Pokémon has been caught
                        GameGraphics.opponentPokemonImage.setIcon(icon);
                        Sound.BattleAgainstPokemon.stop();
                        Sound.VictoryAgainstTrainer.play();
                        JOptionPane.showMessageDialog(GameGraphics.pokemonList, "Congratulations! You caught the wild " + selectedPokemon.retrievePokemonName() + "!", "Captured!", JOptionPane.INFORMATION_MESSAGE, icon);
                        // Adding the caught Pokémon to the player's team
                        team.add(selectedPokemon);
                        // Creating an if statement to check if the player's team is full
                        if (team.size()==7) {
                            // Having the player release a Pokémon
                            String[] releases = new String[7];
                            for (int y = 0; y < 7; y++) {
                                releases[y] = team.get(y).retrievePokemonName();
                            }
                            String releasedPokemon = (String) JOptionPane.showInputDialog(GameGraphics.pokemonList, "Oh no! You have too many Pokemon!\nWhich pokemon do you want to release?","",JOptionPane.QUESTION_MESSAGE,icon,releases,releases[0]);
                            for (int y = 0; y < 7; y++) {
                                if (releasedPokemon.equals(team.get(y).retrievePokemonName())) {
                                    team.remove(y);
                                    JOptionPane.showMessageDialog(GameGraphics.pokemonList, "You released " + releasedPokemon + ".\nBye bye, " + releasedPokemon + "!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                    break;
                                }
                            }
                        }
                        // Updating the average level of the player's Pokémon
                        int sum = 0;
                        for (int y = 0; y < team.size(); y++) {
                            Pokemon pokemonInTeam = team.get(y);
                            sum += pokemonInTeam.retrieveLevel();
                        }
                        // Updating the average level of the opponent's Pokémon
                        averageLevelPlayerPokemon = sum/team.size();
                        // Ending the battle
                        continueBattle = false;
                    }
                    // Creating an else statement to handle the case where the opponent's Pokémon is not caught
                    else {
                        // Having the opponent's Pokémon attack the player's Pokémon
                        Attack[] opponentAttacks = selectedPokemon.retrieveAttacks();
                        int opponentAttackRandomIndex = new Random().nextInt(4);
                        Attack opponentAttack = opponentAttacks[opponentAttackRandomIndex];
                        // Displaying the opponent's attack image
                        GameGraphics.attackImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Attacks/OpponentAttack.png")));
                        // Calculating the damage dealt by the opponent's attack
                        int damage = calculateDamage(selectedPokemon.retrieveLevel(), selectedPokemon.retrieveAttack(), player.retrieveDefense(), opponentAttack.retrieveBaseDamage());
                        // If the damage is less than 1, setting it to 1
                        if (damage < 1) {
                            damage = 1;
                        }
                        // Determining the effectiveness of the attack based on the player's Pokémon's type
                        String damageMultiplierText = opponentAttack.determineAttackEffectiveness(player.retrieveTypeOfPokemon());
                        // Calculating the damage multiplier based on the effectiveness of the attack
                        double damageMultiplier = 1;
                        String message = "";
                        switch(damageMultiplierText){
                            case "super effective":
                                damageMultiplier = 2;
                                message = "It's super effective!";
                                break;
                            case "not very effective":
                                message = "It's not very effective...";
                                damageMultiplier = 0.5;
                                break;
                            case "no effect":
                                message = "It doesn't affect " + player.retrievePokemonName() + "...";
                                damageMultiplier = 0;
                                break;
                            default:
                                damageMultiplier = 1;
                                break;
                        }
                        // Calculating the final damage dealt by the opponent's attack
                        damage = (int) (damageMultiplier*damage);
                        // If the damage is less than 1 and the attack is not very effective, setting it to 1
                        if (damageMultiplierText.equals("not very effective") && damage==0 && opponentAttack.retrieveBaseDamage()!=0) {
                            damage = 1;
                        }
                        // Subtracting the damage from the player's Pokémon's temporary health
                        player.establishTemporaryHealth(player.retrieveTemporaryHealth()-damage);
                        // If the player's Pokémon's temporary health is less than or equal to 0, setting it to 0
                        if (player.retrieveTemporaryHealth() <= 0) {
                            player.establishTemporaryHealth(0);
                        }
                        // Updating the player's Pokémon's health text and health bar
                        GameGraphics.playerHealthText.setText("HP: " + player.retrieveTemporaryHealth() + "/" + player.retrieveHealth());
                        GameGraphics.playerHealthBar.loseHealth(player.retrieveTemporaryHealth(), player.retrieveHealth());
                        // Displaying a message to show the result of the opponent's attack
                        JOptionPane.showMessageDialog(GameGraphics.pokemonList, selectedPokemon.retrievePokemonName()+" used "+ opponentAttack.retrieveName() + "!\n"+message+"\n"+selectedPokemon.retrievePokemonName()+" did " + damage + " damage.", "", JOptionPane.INFORMATION_MESSAGE, icon);
                        // Removing the opponent's attack image
                        GameGraphics.attackImage.setIcon(null);
                        // Creating an if statement to check if the player's Pokémon has fainted
                        if (player.retrieveTemporaryHealth()==0) {
                            // Removing the player's Pokémon from the temporary team
                            temporaryTeam.remove(player);
                            // Displaying a message to show that the player's Pokémon has fainted
                            JOptionPane.showMessageDialog(GameGraphics.pokemonList, player.retrievePokemonName() + " fainted!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                            // Creating an if statement to check if there are other Pokémon in the temporary team
                            if (temporaryTeam.size()>0) {
                                // Having the player switch to another Pokémon
                                String[] others = new String[temporaryTeam.size()];
                                // Adding all the Pokémon in the temporary team to the ArrayList except the player's Pokémon
                                for (int y = 0; y < temporaryTeam.size(); y++) {
                                    others[y] = temporaryTeam.get(y).retrievePokemonName();
                                }
                                // Asking the player which Pokémon they want to switch into
                                String switchedPokemon = (String) JOptionPane.showInputDialog(GameGraphics.pokemonList, "Which pokemon would you like to switch in?","",JOptionPane.QUESTION_MESSAGE,icon,others,others[0]);
                                for (int y = 0; y < temporaryTeam.size(); y++) {
                                    // Switching the player's Pokémon to the selected Pokémon and retrieving the updated temporary team
                                    if (temporaryTeam.get(y).retrievePokemonName().equals(switchedPokemon)) {
                                        player = temporaryTeam.get(y);
                                        GameGraphics.playerPokemonImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Pokemon/" + switchedPokemon + ".png")));
                                        GameGraphics.playerPokemonName.setText(switchedPokemon + " (Level " + player.retrieveLevel() + ")");
                                        GameGraphics.playerHealthText.setText("HP: " + player.retrieveTemporaryHealth() + "/" + player.retrieveHealth());
                                        GameGraphics.playerHealthBar.loseHealth(player.retrieveTemporaryHealth(), player.retrieveHealth());
                                        JOptionPane.showMessageDialog(GameGraphics.pokemonList, "Go " + player.retrievePokemonName() + "!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                    }
                                }
                            }
                            else {
                                // If there are no other Pokémon in the temporary team, ending the battle
                                continueBattle = false;
                                JOptionPane.showMessageDialog(GameGraphics.pokemonList, "The wild " + selectedPokemon.retrievePokemonName() + " got away!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                            }
                        }
                    }
                }
                // Creating an else if statement to handle the case where the player selects the "Switch Pokemon" option
                else if (choices[2].equals(choice)) {
                    // Creating an ArrayList to store the Pokémon that can be switched into
                    ArrayList<String> othersArray = new ArrayList<String>();
                    // Adding all the Pokémon in the temporary team to the ArrayList except the player's Pokémon
                    for (int y = 0; y < temporaryTeam.size(); y++) {
                        if (!player.retrievePokemonName().equals(temporaryTeam.get(y).retrievePokemonName())) {
                            othersArray.add(temporaryTeam.get(y).retrievePokemonName());
                        }
                    }
                    // Converting the ArrayList to an array to be used with the JOptionPane.showInputDialog method
                    String[] others = new String[temporaryTeam.size()-1];
                    others = othersArray.toArray(others);
                    // Asking the player which Pokémon they want to switch into
                    String switchedPokemon = "";
                    if (others.length>0) {
                        switchedPokemon = (String) JOptionPane.showInputDialog(GameGraphics.pokemonList, "Which pokemon would you like to switch in?","",JOptionPane.QUESTION_MESSAGE,icon,others,others[0]);
                    }
                    // Switching the player's Pokémon to the selected Pokémon and retrieving the updated temporary team
                    for (int y = 0; y < temporaryTeam.size(); y++) {
                        if (temporaryTeam.get(y).retrievePokemonName().equals(switchedPokemon)) {
                            player = temporaryTeam.get(y);
                            GameGraphics.playerPokemonImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Pokemon/" + switchedPokemon + ".png")));
                            GameGraphics.playerPokemonName.setText(switchedPokemon + " (Level " + player.retrieveLevel() + ")");
                            GameGraphics.playerHealthText.setText("HP: " + player.retrieveTemporaryHealth() + "/" + player.retrieveHealth());
                            GameGraphics.playerHealthBar.loseHealth(player.retrieveTemporaryHealth(), player.retrieveHealth());
                            JOptionPane.showMessageDialog(GameGraphics.pokemonList, "Go " + player.retrievePokemonName() + "!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                        }
                    }
                    // Creating an if statement to check if there are other Pokémon in the temporary team
                    if (temporaryTeam.size()>1) {
                        // Having the opponent's Pokémon attack the player's Pokémon
                        Attack[] opponentAttacks = selectedPokemon.retrieveAttacks();
                        int opponentAttackRandomIndex = new Random().nextInt(4);
                        Attack opponentAttack = opponentAttacks[opponentAttackRandomIndex];
                        // Displaying the opponent's attack image
                        GameGraphics.attackImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Attacks/OpponentAttack.png")));
                        // Calculating the damage dealt by the opponent's attack
                        int damage = calculateDamage(selectedPokemon.retrieveLevel(), selectedPokemon.retrieveAttack(), player.retrieveDefense(), opponentAttack.retrieveBaseDamage());
                        // If the damage is less than 1, setting it to 1
                        if (damage < 1) {
                            damage = 1;
                        }
                        // Determining the effectiveness of the attack based on the player's Pokémon's type
                        String damageMultiplierText = opponentAttack.determineAttackEffectiveness(player.retrieveTypeOfPokemon());
                        // Calculating the damage multiplier based on the effectiveness of the attack
                        double damageMultiplier = 1;
                        String message = "";
                        switch(damageMultiplierText){
                            case "super effective":
                                damageMultiplier = 2;
                                message = "It's super effective!";
                                break;
                            case "not very effective":
                                message = "It's not very effective...";
                                damageMultiplier = 0.5;
                                break;
                            case "no effect":
                                message = "It doesn't affect " + player.retrievePokemonName() + "...";
                                damageMultiplier = 0;
                                break;
                            default:
                                damageMultiplier = 1;
                                break;
                        }
                        // Calculating the final damage dealt by the opponent's attack
                        damage = (int) (damageMultiplier*damage);
                        // If the damage is less than 1 and the attack is not very effective, setting it to 1
                        if (damageMultiplierText.equals("not very effective") && damage==0 && opponentAttack.retrieveBaseDamage()!=0) {
                            damage = 1;
                        }
                        // Subtracting the damage from the player's Pokémon's temporary health
                        player.establishTemporaryHealth(player.retrieveTemporaryHealth()-damage);
                        // If the player's Pokémon's temporary health is less than or equal to 0, setting it to 0
                        if (player.retrieveTemporaryHealth() <= 0) {
                            player.establishTemporaryHealth(0);
                        }
                        // Updating the player's Pokémon's health text and health bar
                        GameGraphics.playerHealthText.setText("HP: " + player.retrieveTemporaryHealth() + "/" + player.retrieveHealth());
                        GameGraphics.playerHealthBar.loseHealth(player.retrieveTemporaryHealth(), player.retrieveHealth());
                        // Displaying a message to show the result of the opponent's attack
                        JOptionPane.showMessageDialog(GameGraphics.pokemonList, selectedPokemon.retrievePokemonName()+" used "+ opponentAttack.retrieveName() + "!\n"+message+"\n"+selectedPokemon.retrievePokemonName()+" did " + damage + " damage.", "", JOptionPane.INFORMATION_MESSAGE, icon);
                        // Removing the opponent's attack image
                        GameGraphics.attackImage.setIcon(null);
                        // Creating an if statement to check if the player's Pokémon has fainted
                        if (player.retrieveTemporaryHealth()==0) {
                            // Removing the player's Pokémon from the temporary team
                            temporaryTeam.remove(player);
                            // Displaying a message to show that the player's Pokémon has fainted
                            JOptionPane.showMessageDialog(GameGraphics.pokemonList, player.retrievePokemonName() + " fainted!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                            // Creating an if statement to check if there are other Pokémon in the temporary team
                            if (temporaryTeam.size()>0) {
                                // Having the player switch to another Pokémon
                                String[] othersAndFirst = new String[temporaryTeam.size()];
                                for (int y = 0; y < temporaryTeam.size(); y++) {
                                    othersAndFirst[y] = temporaryTeam.get(y).retrievePokemonName();
                                }
                                // Being given the option to switch to another Pokémon after fainting
                                String switchedPokemonAfterFainted = (String) JOptionPane.showInputDialog(GameGraphics.pokemonList, "Which pokemon would you like to switch in?","",JOptionPane.QUESTION_MESSAGE,icon,others,others[0]);
                                // Switching to the selected Pokémon and updating the player's Pokémon information
                                for (int y = 0; y < temporaryTeam.size(); y++) {
                                    if (temporaryTeam.get(y).retrievePokemonName().equals(switchedPokemonAfterFainted)) {
                                        player = temporaryTeam.get(y);
                                        GameGraphics.playerPokemonImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Pokemon/" + switchedPokemon + ".png")));
                                        GameGraphics.playerPokemonName.setText(switchedPokemonAfterFainted + " (Level " + player.retrieveLevel() + ")");
                                        GameGraphics.playerHealthText.setText("HP: " + player.retrieveTemporaryHealth() + "/" + player.retrieveHealth());
                                        GameGraphics.playerHealthBar.loseHealth(player.retrieveTemporaryHealth(), player.retrieveHealth());
                                        JOptionPane.showMessageDialog(GameGraphics.pokemonList, "Go " + player.retrievePokemonName() + "!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                    }
                                }
                            }
                            // If there are no other Pokémon in the temporary team, ending the battle
                            else {
                                continueBattle = false;
                                JOptionPane.showMessageDialog(GameGraphics.pokemonList, "The wild " + selectedPokemon.retrievePokemonName() + " got away!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                            }
                        }
                    }
                    // Creating an else statement to handle the case where there are no other Pokémon in the temporary team
                    else {
                        // Displaying a message to show that the player cannot switch Pokémon
                        JOptionPane.showMessageDialog(GameGraphics.pokemonList, "You have no other team members left!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                    }
                }
                // Creating an else if statement to handle the case where the player selects the "Run" option
                else if (choices[3].equals(choice)) {
                    JOptionPane.showMessageDialog(GameGraphics.pokemonList, "Got away safely!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                    // Ending the battle
                    continueBattle = false;
                }
            }
        }
        // Displaying the main display after the battle
        GameManager.gameGraphics.mainDisplay();
        // Removing all components from the pokemonList container
        GameGraphics.pokemonList.removeAll();
        // Adding the player's Pokémon to the pokemonList container
        for (int y = 0; y < team.size(); y++) {
            Pokemon pokemonInTeam = team.get(y);
            BufferedImage image = new ProcessingImage().scaleImage(85, 85, "Images/Pokemon/" + pokemonInTeam.retrievePokemonName() + ".png");
            GameGraphics.pokemonList.add(new JLabel(new ImageIcon(image)));
        }
        // Revalidating the pokemonList container
        GameGraphics.pokemonList.revalidate();
        // Updating the average level of the player's Pokémon
        int sum = 0;
        for (int y = 0; y < team.size(); y++) {
            Pokemon pokemonInTeam = team.get(y);
            sum += pokemonInTeam.retrieveLevel();
        }        averageLevelPlayerPokemon = sum/team.size();
    }
    // Establishing a method that will handle the battle against a trainer
    public void trainerBattle() {
        // Stopping the environment sound to prepare for the battle
        Sound.Environment.stop();
        // Playing the battle against trainer sound to set the mood
        Sound.BattleAgainstTrainer.play();
        // Creating an ArrayList to store the temporary team of Pokémon
        ArrayList<Pokemon> temporaryTeam = new ArrayList<Pokemon>();
        // Creating an ArrayList to store the trainer's team of Pokémon
        ArrayList<Pokemon> trainerTeam = GameManager.allPokemon.assembleTrainerPokemon(averageLevelPlayerPokemon, team.size());
        // Selecting the first Pokémon from the trainer's team as the opponent
        Pokemon selectedPokemon = trainerTeam.get(0);
        // Looping through each Pokémon in the player's team
        for (int y = 0; y < team.size(); y++) {
            // Adding the Pokémon to the temporary team
            temporaryTeam.add(team.get(y));
            // Establishing the temporary health of the Pokémon
            temporaryTeam.get(y).establishTemporaryHealth(temporaryTeam.get(y).retrieveHealth());
        }
        // Selecting the first Pokémon from the temporary team as the player's current Pokémon
        Pokemon player = temporaryTeam.get(0);
        // Displaying the battle graphics to show the player and opponent
        GameManager.gameGraphics.battleDisplay(player, selectedPokemon);
        // Initializing the opponent's remaining health
        int opponentRemainingHealth = selectedPokemon.retrieveHealth();
        // Initializing a boolean to determine whether the battle should continue
        boolean continueBattle = true;
        // Creating an ImageIcon for the Poké Ball
        ImageIcon icon = new ImageIcon(imageLoader.strToURL("Images/PokeBall.png"));
        // Displaying a message to start the battle
        JOptionPane.showMessageDialog(GameGraphics.frame, "Go " + player.retrievePokemonName() + "!", "", JOptionPane.INFORMATION_MESSAGE, icon);
        // Displaying a message to inform the player about the rival trainer's team
        JOptionPane.showMessageDialog(GameGraphics.pokemonList, "The rival trainer has " + team.size() + " pokemon.", "", JOptionPane.INFORMATION_MESSAGE, icon);
        // Looping through the battle until it is over
        while (continueBattle) {
            // Creating an array of choices for the player
            String[] choices = new String[2];
            // Setting the first choice to "Attack"
            choices[0] = "Attack";
            // Setting the second choice to "Switch Pokemon"
            choices[1] = "Switch Pokemon";
            // Displaying a dialog box to ask the player for their choice
            String choice = (String) JOptionPane.showInputDialog(GameGraphics.pokemonList, "What would you like to do?", "Your Turn", JOptionPane.QUESTION_MESSAGE, icon, choices, choices[0]);
            // Checking if the player chose to attack
            if (choices[0].equals(choice)) {
                // Creating an array of attack choices for the player
                String[] attackChoice = new String[4];
                // Retrieving the attacks of the player's current Pokémon
                Attack[] attacks = player.retrieveAttacks();
                // Setting the first attack choice to the name of the first attack
                attackChoice[0] = attacks[0].retrieveName() + " (Base Damage: " + attacks[0].retrieveBaseDamage() + ", Type: " + attacks[0].retrieveType() + ")";
                // Setting the second attack choice to the name of the second attack
                attackChoice[1] = attacks[1].retrieveName() + " (Base Damage: " + attacks[1].retrieveBaseDamage() + ", Type: " + attacks[1].retrieveType() + ")";
                // Setting the third attack choice to the name of the third attack
                attackChoice[2] = attacks[2].retrieveName() + " (Base Damage: " + attacks[2].retrieveBaseDamage() + ", Type: " + attacks[2].retrieveType() + ")";
                // Setting the fourth attack choice to the name of the fourth attack
                attackChoice[3] = attacks[3].retrieveName() + " (Base Damage: " + attacks[3].retrieveBaseDamage() + ", Type: " + attacks[3].retrieveType() + ")";
                // Displaying a dialog box to ask the player for their attack choice
                String attackChoiceText = (String) JOptionPane.showInputDialog(GameGraphics.pokemonList, "Which attack would you like to use?", "Attack Choice", JOptionPane.QUESTION_MESSAGE, icon, attackChoice, attackChoice[0]);
                // Checking if the player cancelled the attack choice dialog box
                if (attackChoiceText == null) {
                    // Do nothing
                }
                else {
                    // Checking if the player's Pokémon is faster than the opponent's Pokémon
                    if (player.retrieveSpeed() > selectedPokemon.retrieveSpeed()) {
                        // Displaying the player's attack image
                        GameGraphics.attackImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Attacks/PlayerAttack.png")));
                        // Calculating the damage dealt by the player's attack
                        int damage = calculateDamage(player.retrieveLevel(), player.retrieveAttack(), selectedPokemon.retrieveDefense(), Integer.parseInt(attackChoiceText.substring(attackChoiceText.indexOf("Base Damage: ") + 13, attackChoiceText.indexOf(","))));
                        // Checking if the damage is less than 1
                        if (Integer.parseInt(attackChoiceText.substring(attackChoiceText.indexOf("Base Damage: ") + 13, attackChoiceText.indexOf(","))) == 0) {
                            damage = 0;
                        }
                        else if (damage < 1) {
                            damage = 1;
                        }
                        // Determining the effectiveness of the attack
                        String damageMultiplierText = "";
                        // Checking if the first attack choice matches the player's chosen attack
                        if (attackChoice[0].equals(attackChoiceText)) {
                            // Setting the damage multiplier text to the effectiveness of the first attack
                            damageMultiplierText = attacks[0].determineAttackEffectiveness(selectedPokemon.retrieveTypeOfPokemon());
                        }
                        // Checking if the second attack choice matches the player's chosen attack
                        else if (attackChoice[1].equals(attackChoiceText)) {
                            // Setting the damage multiplier text to the effectiveness of the second attack
                            damageMultiplierText = attacks[1].determineAttackEffectiveness(selectedPokemon.retrieveTypeOfPokemon());
                        }
                        // Checking if the third attack choice matches the player's chosen attack
                        else if (attackChoice[2].equals(attackChoiceText)) {
                            // Setting the damage multiplier text to the effectiveness of the third attack
                            damageMultiplierText = attacks[2].determineAttackEffectiveness(selectedPokemon.retrieveTypeOfPokemon());
                        }
                        // Checking if the fourth attack choice matches the player's chosen attack
                        else if (attackChoice[3].equals(attackChoiceText)) {
                            // Setting the damage multiplier text to the effectiveness of the fourth attack
                            damageMultiplierText = attacks[3].determineAttackEffectiveness(selectedPokemon.retrieveTypeOfPokemon());
                        }
                        // Calculating the damage multiplier
                        double damageMultiplier = 1;
                        // Initializing a message to display to the player
                        String message = "";
                        // Switching on the damage multiplier text to determine the damage multiplier and message
                        switch (damageMultiplierText) {
                            case "super effective":
                                // Setting the damage multiplier to 2 for a super effective attack
                                damageMultiplier = 2;
                                // Setting the message to "It's super effective!"
                                message = "It's super effective!";
                                break;
                            case "not very effective":
                                // Setting the message to "It's not very effective..."
                                message = "It's not very effective...";
                                // Setting the damage multiplier to 0.5 for a not very effective attack
                                damageMultiplier = 0.5;
                                break;
                            case "no effect":
                                // Setting the message to "It doesn't affect " + player.retrievePokemonName() + "..."
                                message = "It doesn't affect " + player.retrievePokemonName() + "...";
                                // Setting the damage multiplier to 0 for an attack with no effect
                                damageMultiplier = 0;
                                break;
                            default:
                                // Setting the damage multiplier to 1 for a normal attack
                                damageMultiplier = 1;
                                break;
                        }
                        // Calculating the final damage
                        damage = (int) (damageMultiplier * damage);
                        // Checking if the damage is less than 1 and the attack is not very effective
                        if (damageMultiplierText.equals("not very effective") && damage == 0 && Integer.parseInt(attackChoiceText.substring(attackChoiceText.indexOf("Base Damage: ") + 13, attackChoiceText.indexOf(","))) != 0) {
                            damage = 1;
                        }
                        // Subtracting the damage from the opponent's remaining health
                        opponentRemainingHealth -= damage;
                        // Checking if the opponent's remaining health is less than 0
                        if (opponentRemainingHealth < 0) {
                            opponentRemainingHealth = 0;
                        }
                        // Updating the opponent's health text and bar
                        GameGraphics.opponentHealthText.setText("HP: " + opponentRemainingHealth + "/" + selectedPokemon.retrieveHealth());
                        GameGraphics.opponentHealthBar.loseHealth(opponentRemainingHealth, selectedPokemon.retrieveHealth());
                        // Displaying a message to inform the player about the attack
                        JOptionPane.showMessageDialog(GameGraphics.pokemonList, player.retrievePokemonName() + " used " + attackChoiceText.substring(0, attackChoiceText.indexOf("(") - 1) + "!\n" + message + "\n" + player.retrievePokemonName() + " did " + damage + " damage.", "", JOptionPane.INFORMATION_MESSAGE, icon);
                        // Removing the player's attack image
                        GameGraphics.attackImage.setIcon(null);
                        // Checking if the opponent's remaining health is greater than 0
                        if (opponentRemainingHealth > 0) {
                            // Displaying the opponent's attack image
                            GameGraphics.attackImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Attacks/OpponentAttack.png")));
                            // Calculating the damage dealt by the opponent's attack
                            int opponentDamage = calculateDamage(selectedPokemon.retrieveLevel(), selectedPokemon.retrieveAttack(), player.retrieveDefense(), attacks[0].retrieveBaseDamage());
                            // Checking if the damage is less than 1
                            if (opponentDamage < 1) {
                                opponentDamage = 1;
                            }
                            // Determining the effectiveness of the opponent's attack
                            String opponentDamageMultiplierText = attacks[0].determineAttackEffectiveness(player.retrieveTypeOfPokemon());
                            // Calculating the damage multiplier
                            double opponentDamageMultiplier = 1;
                            // Initializing a message to display to the player
                            String opponentMessage = "";
                            // Switching on the damage multiplier text to determine the damage multiplier and message
                            switch (opponentDamageMultiplierText) {
                                case "super effective":
                                    // Setting the damage multiplier to 2 for a super effective attack
                                    opponentDamageMultiplier = 2;
                                    // Setting the message to "It's super effective!"
                                    opponentMessage = "It's super effective!";
                                    break;
                                case "not very effective":
                                    // Setting the message to "It's not very effective..."
                                    opponentMessage = "It's not very effective...";
                                    // Setting the damage multiplier to 0.5 for a not very effective attack
                                    opponentDamageMultiplier = 0.5;
                                    break;
                                case "no effect":
                                    // Setting the message to "It doesn't affect " + player.retrievePokemonName() + "..."
                                    opponentMessage = "It doesn't affect " + player.retrievePokemonName() + "...";
                                    // Setting the damage multiplier to 0 for an attack with no effect
                                    opponentDamageMultiplier = 0;
                                    break;
                                default:
                                    // Setting the damage multiplier to 1 for a normal attack
                                    opponentDamageMultiplier = 1;
                                    break;
                            }
                            // Calculating the final damage
                            opponentDamage = (int) (opponentDamageMultiplier * opponentDamage);
                            // Checking if the damage is less than 1 and the attack is not very effective
                            if (opponentDamageMultiplierText.equals("not very effective") && opponentDamage == 0 && attacks[0].retrieveBaseDamage() != 0) {
                                opponentDamage = 1;
                            }
                            // Subtracting the damage from the player's temporary health
                            player.establishTemporaryHealth(player.retrieveTemporaryHealth() - opponentDamage);
                            // Checking if the player's temporary health is less than or equal to 0
                            if (player.retrieveTemporaryHealth() <= 0) {
                                player.establishTemporaryHealth(0);
                            }
                            // Updating the player's health text and bar
                            GameGraphics.playerHealthText.setText("HP: " + player.retrieveTemporaryHealth() + "/" + player.retrieveHealth());
                            GameGraphics.playerHealthBar.loseHealth(player.retrieveTemporaryHealth(), player.retrieveHealth());
                            // Displaying a message to inform the player about the opponent's attack
                            JOptionPane.showMessageDialog(GameGraphics.pokemonList, selectedPokemon.retrievePokemonName() + " used " + attacks[0].retrieveName() + "!\n" + opponentMessage + "\n" + selectedPokemon.retrievePokemonName() + " did " + opponentDamage + " damage.", "", JOptionPane.INFORMATION_MESSAGE, icon);
                            // Removing the opponent's attack image
                            GameGraphics.attackImage.setIcon(null);
                            // Checking if the player's temporary health is 0
                            if (player.retrieveTemporaryHealth() == 0) {
                                // Removing the player's Pokémon from the temporary team
                                temporaryTeam.remove(player);
                                // Displaying a message to inform the player that their Pokémon fainted
                                JOptionPane.showMessageDialog(GameGraphics.pokemonList, player.retrievePokemonName() + " fainted!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                // Checking if there are other Pokémon in the temporary team
                                if (temporaryTeam.size() > 0) {
                                    // Displaying a dialog box to ask the player to switch to another Pokémon
                                    String[] others = new String[temporaryTeam.size()];
                                    for (int y = 0; y < temporaryTeam.size(); y++) {
                                        others[y] = temporaryTeam.get(y).retrievePokemonName();
                                    }
                                    String switchedPokemon = (String) JOptionPane.showInputDialog(GameGraphics.pokemonList, "Which pokemon would you like to switch in?", "", JOptionPane.QUESTION_MESSAGE, icon, others, others[0]);
                                    // Switching to the selected Pokémon
                                    for (int y = 0; y < temporaryTeam.size(); y++) {
                                        if (temporaryTeam.get(y).retrievePokemonName().equals(switchedPokemon)) {
                                            player = temporaryTeam.get(y);
                                            GameGraphics.playerPokemonImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Pokemon/" + switchedPokemon + ".png")));
                                            GameGraphics.playerPokemonName.setText(switchedPokemon + " (Level " + player.retrieveLevel() + ")");
                                            GameGraphics.playerHealthText.setText("HP: " + player.retrieveTemporaryHealth() + "/" + player.retrieveHealth());
                                            GameGraphics.playerHealthBar.loseHealth(player.retrieveTemporaryHealth(), player.retrieveHealth());
                                            JOptionPane.showMessageDialog(GameGraphics.pokemonList, "Go " + player.retrievePokemonName() + "!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                        }
                                    }
                                }
                                else {
                                    // Displaying a message to inform the player that they lost
                                    Sound.BattleAgainstTrainer.stop();
                                    JOptionPane.showMessageDialog(GameGraphics.pokemonList, "You lost.", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                }
                            }
                        }
                        else {
                            // Displaying a message to inform the player that the opponent's Pokémon fainted
                            JOptionPane.showMessageDialog(GameGraphics.pokemonList, selectedPokemon.retrievePokemonName() + " fainted!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                            // Giving the player experience points for defeating the opponent's Pokémon
                            player.gainXP(selectedPokemon.retrieveLevel());
                            // Checking if there is only 1 Pokémon in the trainer's team (that is already defeated)
                            if (trainerTeam.size() == 1) {
                                // Removing the opponent's Pokémon from the trainer's team
                                trainerTeam.remove(selectedPokemon);
                                // Displaying a message to inform the player that they won
                                Sound.BattleAgainstTrainer.stop();
                                Sound.VictoryAgainstTrainer.play();
                                JOptionPane.showMessageDialog(GameGraphics.pokemonList, "Congratulations! You defeated the enemy trainer!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                // Setting the continue battle flag to false
                                continueBattle = false;
                            }
                            // Checking if there are other Pokémon in the trainer's team
                            else {
                                // Removing the opponent's Pokémon from the trainer's team
                                trainerTeam.remove(selectedPokemon);
                                // Selecting the next Pokémon from the trainer's team
                                selectedPokemon = trainerTeam.get(0);
                                // Updating the opponent's health text and bar
                                opponentRemainingHealth = selectedPokemon.retrieveHealth();
                                GameGraphics.opponentPokemonImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Pokemon/" + selectedPokemon.retrievePokemonName() + ".png")));
                                GameGraphics.opponentPokemonName.setText(selectedPokemon.retrievePokemonName() + " (Level " + selectedPokemon.retrieveLevel() + ")");
                                GameGraphics.opponentHealthText.setText("HP: " + opponentRemainingHealth + "/" + selectedPokemon.retrieveHealth());
                                GameGraphics.opponentHealthBar.loseHealth(opponentRemainingHealth, selectedPokemon.retrieveHealth());
                                // Displaying a message to inform the player that the opponent sent out a new Pokémon
                                JOptionPane.showMessageDialog(GameGraphics.pokemonList, "The rival trainer sent out " + selectedPokemon.retrievePokemonName() + "!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                // Displaying a message to inform the player about the opponent's remaining Pokémon
                                JOptionPane.showMessageDialog(GameGraphics.pokemonList, "The rival trainer has " + trainerTeam.size() + " pokemon left.", "", JOptionPane.INFORMATION_MESSAGE, icon);
                            }
                        }
                        // Removing the attack image
                        GameGraphics.attackImage.setIcon(null);
                    }
                    else {
                        // Displaying the opponent's attack image
                        GameGraphics.attackImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Attacks/OpponentAttack.png")));
                        // Calculating the damage dealt by the opponent's attack
                        int opponentDamage = calculateDamage(selectedPokemon.retrieveLevel(), selectedPokemon.retrieveAttack(), player.retrieveDefense(), attacks[0].retrieveBaseDamage());
                        // Checking if the damage is less than 1
                        if (opponentDamage < 1) {
                            opponentDamage = 1;
                        }
                        // Determining the effectiveness of the opponent's attack
                        String opponentDamageMultiplierText = attacks[0].determineAttackEffectiveness(player.retrieveTypeOfPokemon());
                        // Calculating the damage multiplier
                        double opponentDamageMultiplier = 1;
                        // Initializing a message to display to the player
                        String opponentMessage = "";
                        // Switching on the damage multiplier text to determine the damage multiplier and message
                        switch (opponentDamageMultiplierText) {
                            case "super effective":
                                // Setting the damage multiplier to 2 for a super effective attack
                                opponentDamageMultiplier = 2;
                                // Setting the message to "It's super effective!"
                                opponentMessage = "It's super effective!";
                                break;
                            case "not very effective":
                                // Setting the message to "It's not very effective..."
                                opponentMessage = "It's not very effective...";
                                // Setting the damage multiplier to 0.5 for a not very effective attack
                                opponentDamageMultiplier = 0.5;
                                break;
                            case "no effect":
                                // Setting the message to "It doesn't affect " + player.retrievePokemonName() + "..."
                                opponentMessage = "It doesn't affect " + player.retrievePokemonName() + "...";
                                // Setting the damage multiplier to 0 for an attack with no effect
                                opponentDamageMultiplier = 0;
                                break;
                            default:
                                // Setting the damage multiplier to 1 for a normal attack
                                opponentDamageMultiplier = 1;
                                break;
                        }
                        // Calculating the final damage
                        opponentDamage = (int) (opponentDamageMultiplier * opponentDamage);
                        // Checking if the damage is less than 1 and the attack is not very effective
                        if (opponentDamageMultiplierText.equals("not very effective") && opponentDamage == 0 && attacks[0].retrieveBaseDamage() != 0) {
                            opponentDamage = 1;
                        }
                        // Subtracting the damage from the player's temporary health
                        player.establishTemporaryHealth(player.retrieveTemporaryHealth() - opponentDamage);
                        // Checking if the player's temporary health is less than or equal to 0
                        if (player.retrieveTemporaryHealth() <= 0) {
                            player.establishTemporaryHealth(0);
                        }
                        // Updating the player's health text and bar
                        GameGraphics.playerHealthText.setText("HP: " + player.retrieveTemporaryHealth() + "/" + player.retrieveHealth());
                        GameGraphics.playerHealthBar.loseHealth(player.retrieveTemporaryHealth(), player.retrieveHealth());
                        // Displaying a message to inform the player about the opponent's attack
                        JOptionPane.showMessageDialog(GameGraphics.pokemonList, selectedPokemon.retrievePokemonName() + " used " + attacks[0].retrieveName() + "!\n" + opponentMessage + "\n" + selectedPokemon.retrievePokemonName() + " did " + opponentDamage + " damage.", "", JOptionPane.INFORMATION_MESSAGE, icon);
                        // Removing the opponent's attack image
                        GameGraphics.attackImage.setIcon(null);
                        // Checking if the player's temporary health is greater than 0
                        if (player.retrieveTemporaryHealth() > 0) {
                            // Displaying the player's attack image
                            GameGraphics.attackImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Attacks/PlayerAttack.png")));
                            // Calculating the damage dealt by the player's attack
                            int damage = calculateDamage(player.retrieveLevel(), player.retrieveAttack(), selectedPokemon.retrieveDefense(), Integer.parseInt(attackChoiceText.substring(attackChoiceText.indexOf("Base Damage: ") + 13, attackChoiceText.indexOf(","))));
                            // Checking if the damage is less than 1
                            if (Integer.parseInt(attackChoiceText.substring(attackChoiceText.indexOf("Base Damage: ") + 13, attackChoiceText.indexOf(","))) == 0) {
                                damage = 0;
                            }
                            else if (damage < 1) {
                                damage = 1;
                            }
                            // Determining the effectiveness of the player's attack
                            String damageMultiplierText = "";
                            // Checking if the first attack choice matches the player's chosen attack
                            if (attackChoice[0].equals(attackChoiceText)) {
                                // Setting the damage multiplier text to the effectiveness of the first attack
                                damageMultiplierText = attacks[0].determineAttackEffectiveness(selectedPokemon.retrieveTypeOfPokemon());
                            }
                            // Checking if the second attack choice matches the player's chosen attack
                            else if (attackChoice[1].equals(attackChoiceText)) {
                                // Setting the damage multiplier text to the effectiveness of the second attack
                                damageMultiplierText = attacks[1].determineAttackEffectiveness(selectedPokemon.retrieveTypeOfPokemon());
                            }
                            // Checking if the third attack choice matches the player's chosen attack
                            else if (attackChoice[2].equals(attackChoiceText)) {
                                // Setting the damage multiplier text to the effectiveness of the third attack
                                damageMultiplierText = attacks[2].determineAttackEffectiveness(selectedPokemon.retrieveTypeOfPokemon());
                            }
                            // Checking if the fourth attack choice matches the player's chosen attack
                            else if (attackChoice[3].equals(attackChoiceText)) {
                                // Setting the damage multiplier text to the effectiveness of the fourth attack
                                damageMultiplierText = attacks[3].determineAttackEffectiveness(selectedPokemon.retrieveTypeOfPokemon());
                            }
                            // Calculating the damage multiplier
                            double damageMultiplier = 1;
                            // Initializing a message to display to the player
                            String message = "";
                            // Switching on the damage multiplier text to determine the damage multiplier and message
                            switch (damageMultiplierText) {
                                case "super effective":
                                    // Setting the damage multiplier to 2 for a super effective attack
                                    damageMultiplier = 2;
                                    // Setting the message to "It's super effective!"
                                    message = "It's super effective!";
                                    break;
                                case "not very effective":
                                    // Setting the message to "It's not very effective..."
                                    message = "It's not very effective...";
                                    // Setting the damage multiplier to 0.5 for a not very effective attack
                                    damageMultiplier = 0.5;
                                    break;
                                case "no effect":
                                    // Setting the message to "It doesn't affect " + player.retrievePokemonName() + "..."
                                    message = "It doesn't affect " + player.retrievePokemonName() + "...";
                                    // Setting the damage multiplier to 0 for an attack with no effect
                                    damageMultiplier = 0;
                                    break;
                                default:
                                    // Setting the damage multiplier to 1 for a normal attack
                                    damageMultiplier = 1;
                                    break;
                            }
                            // Calculating the final damage
                            damage = (int) (damageMultiplier * damage);
                            // Checking if the damage is less than 1 and the attack is not very effective
                            if (damageMultiplierText.equals("not very effective") && damage == 0 && Integer.parseInt(attackChoiceText.substring(attackChoiceText.indexOf("Base Damage: ") + 13, attackChoiceText.indexOf(","))) != 0) {
                                damage = 1;
                            }
                            // Subtracting the damage from the opponent's remaining health
                            opponentRemainingHealth -= damage;
                            // Checking if the opponent's remaining health is less than 0
                            if (opponentRemainingHealth < 0) {
                                opponentRemainingHealth = 0;
                            }
                            // Updating the opponent's health text and bar
                            GameGraphics.opponentHealthText.setText("HP: " + opponentRemainingHealth + "/" + selectedPokemon.retrieveHealth());
                            GameGraphics.opponentHealthBar.loseHealth(opponentRemainingHealth, selectedPokemon.retrieveHealth());
                            // Displaying a message to inform the player about the attack
                            JOptionPane.showMessageDialog(GameGraphics.pokemonList, player.retrievePokemonName() + " used " + attackChoiceText.substring(0, attackChoiceText.indexOf("(") - 1) + "!\n" + message + "\n" + player.retrievePokemonName() + " did " + damage + " damage.", "", JOptionPane.INFORMATION_MESSAGE, icon);
                            // Removing the player's attack image
                            GameGraphics.attackImage.setIcon(null);
                            // Checking if the opponent's remaining health is 0
                            if (opponentRemainingHealth == 0) {
                                // Displaying a message to inform the player that the opponent's Pokémon fainted
                                JOptionPane.showMessageDialog(GameGraphics.pokemonList, selectedPokemon.retrievePokemonName() + " fainted!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                // Giving the player experience points for defeating the opponent's Pokémon
                                player.gainXP(selectedPokemon.retrieveLevel());
                                // Checking if there are no other Pokémon in the trainer's team
                                if (trainerTeam.size() == 1) {
                                    // Removing the opponent's Pokémon from the trainer's team
                                    trainerTeam.remove(selectedPokemon);
                                    // Displaying a message to inform the player that they won
                                    Sound.BattleAgainstTrainer.stop();
                                    Sound.VictoryAgainstTrainer.play();
                                    JOptionPane.showMessageDialog(GameGraphics.pokemonList, "Congratulations! You defeated the enemy trainer!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                    // Setting the continue battle flag to false
                                    continueBattle = false;
                                }
                                // Creating an else statement in the case that there are other Pokémon in the trainer's team
                                else {
                                    // Removing the opponent's Pokémon from the trainer's team
                                    trainerTeam.remove(selectedPokemon);
                                    // Selecting the next Pokémon from the trainer's team
                                    selectedPokemon = trainerTeam.get(0);
                                    // Updating the opponent's health text and bar
                                    opponentRemainingHealth = selectedPokemon.retrieveHealth();
                                    GameGraphics.opponentPokemonImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Pokemon/" + selectedPokemon.retrievePokemonName() + ".png")));
                                    GameGraphics.opponentPokemonName.setText(selectedPokemon.retrievePokemonName() + " (Level " + selectedPokemon.retrieveLevel() + ")");
                                    GameGraphics.opponentHealthText.setText("HP: " + opponentRemainingHealth + "/" + selectedPokemon.retrieveHealth());
                                    GameGraphics.opponentHealthBar.loseHealth(opponentRemainingHealth, selectedPokemon.retrieveHealth());
                                    // Displaying a message to inform the player that the opponent sent out a new Pokémon
                                    JOptionPane.showMessageDialog(GameGraphics.pokemonList, "The rival trainer sent out " + selectedPokemon.retrievePokemonName() + "!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                    // Displaying a message to inform the player about the opponent's remaining Pokémon
                                    JOptionPane.showMessageDialog(GameGraphics.pokemonList, "The rival trainer has " + trainerTeam.size() + " pokemon left.", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                }
                            }
                        }
                        else {
                            // Removing the player's Pokémon from the temporary team
                            temporaryTeam.remove(player);
                            // Displaying a message to inform the player that their Pokémon fainted
                            JOptionPane.showMessageDialog(GameGraphics.pokemonList, player.retrievePokemonName() + " fainted!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                            // Checking if there are other Pokémon in the temporary team
                            if (temporaryTeam.size() > 0) {
                                // Displaying a dialog box to ask the player to switch to another Pokémon
                                String[] others = new String[temporaryTeam.size()];
                                for (int y = 0; y < temporaryTeam.size(); y++) {
                                    others[y] = temporaryTeam.get(y).retrievePokemonName();
                                }
                                String switchedPokemon = (String) JOptionPane.showInputDialog(GameGraphics.pokemonList, "Which pokemon would you like to switch in?", "", JOptionPane.QUESTION_MESSAGE, icon, others, others[0]);
                                // Switching to the selected Pokémon
                                for (int y = 0; y < temporaryTeam.size(); y++) {
                                    if (temporaryTeam.get(y).retrievePokemonName().equals(switchedPokemon)) {
                                        player = temporaryTeam.get(y);
                                        GameGraphics.playerPokemonImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Pokemon/" + switchedPokemon + ".png")));
                                        GameGraphics.playerPokemonName.setText(switchedPokemon + " (Level " + player.retrieveLevel() + ")");
                                        GameGraphics.playerHealthText.setText("HP: " + player.retrieveTemporaryHealth() + "/" + player.retrieveHealth());
                                        GameGraphics.playerHealthBar.loseHealth(player.retrieveTemporaryHealth(), player.retrieveHealth());
                                        JOptionPane.showMessageDialog(GameGraphics.pokemonList, "Go " + player.retrievePokemonName() + "!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                    }
                                }
                            }
                            // Creating an else statement for the case that there are no more Pokémon in the temporary team
                            else {
                                // Displaying a message to inform the player that they lost
                                Sound.BattleAgainstTrainer.stop();
                                JOptionPane.showMessageDialog(GameGraphics.pokemonList, "You lost.", "", JOptionPane.INFORMATION_MESSAGE, icon);
                            }
                        }
                    }
                }
            }
            // Creating an else if statement to check if the player's choice is to switch to another Pokémon
            else if (choices[1].equals(choice)) {
                // Creating an ArrayList to store the names of the other Pokémon in the temporary team
                ArrayList<String> othersArray = new ArrayList<String>();
                // Looping through each Pokémon in the temporary team
                for (int y = 0; y < temporaryTeam.size(); y++) {
                    // Checking if the current Pokémon is not the player's current Pokémon
                    if (!player.retrievePokemonName().equals(temporaryTeam.get(y).retrievePokemonName())) {
                        // Adding the Pokémon's name to the ArrayList
                        othersArray.add(temporaryTeam.get(y).retrievePokemonName());
                    }
                }
                // Converting the ArrayList to an array
                String[] others = new String[temporaryTeam.size() - 1];
                others = othersArray.toArray(others);
                // Displaying a dialog box to ask the player to switch to another Pokémon
                String switchedPokemon = "";
                if (others.length > 0) {
                    switchedPokemon = (String) JOptionPane.showInputDialog(GameGraphics.pokemonList, "Which pokemon would you like to switch in?", "", JOptionPane.QUESTION_MESSAGE, icon, others, others[0]);
                }
                // Switching to the selected Pokémon
                for (int y = 0; y < temporaryTeam.size(); y++) {
                    if (temporaryTeam.get(y).retrievePokemonName().equals(switchedPokemon)) {
                        player = temporaryTeam.get(y);
                        GameGraphics.playerPokemonImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Pokemon/" + switchedPokemon + ".png")));
                        GameGraphics.playerPokemonName.setText(switchedPokemon + " (Level " + player.retrieveLevel() + ")");
                        GameGraphics.playerHealthText.setText("HP: " + player.retrieveTemporaryHealth() + "/" + player.retrieveHealth());
                        GameGraphics.playerHealthBar.loseHealth(player.retrieveTemporaryHealth(), player.retrieveHealth());
                    }
                }
                // Checking if there is only 1 Pokémon in the trainer's team (that is already defeated)
                if (temporaryTeam.size() > 1) {
                    // Displaying a message to inform the player that they switched to another Pokémon
                    JOptionPane.showMessageDialog(GameGraphics.pokemonList, "Go " + switchedPokemon + "!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                    // Displaying the opponent's attack image
                    GameGraphics.attackImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Attacks/OpponentAttack.png")));
                    // Calculating the damage dealt by the opponent's attack
                    int opponentDamage = calculateDamage(selectedPokemon.retrieveLevel(), selectedPokemon.retrieveAttack(), player.retrieveDefense(), selectedPokemon.attacks[0].retrieveBaseDamage());
                    // Checking if the damage is less than 1
                    if (opponentDamage < 1) {
                        opponentDamage = 1;
                    }
                    // Determining the effectiveness of the opponent's attack
                    String opponentDamageMultiplierText = selectedPokemon.attacks[0].determineAttackEffectiveness(player.retrieveTypeOfPokemon());
                    // Initalizing the damage multiplier
                    double opponentDamageMultiplier = 1;
                    // Initializing a message to display to the player
                    String opponentMessage = "";
                    // Switching on the damage multiplier text to determine the damage multiplier and message
                    switch (opponentDamageMultiplierText) {
                        case "super effective":
                            // Setting the damage multiplier to 2 for a super effective attack
                            opponentDamageMultiplier = 2;
                            // Setting the message to "It's super effective!"
                            opponentMessage = "It's super effective!";
                            break;
                        case "not very effective":
                            // Setting the message to "It's not very effective..."
                            opponentMessage = "It's not very effective...";
                            // Setting the damage multiplier to 0.5 for a not very effective attack
                            opponentDamageMultiplier = 0.5;
                            break;
                        case "no effect":
                            // Setting the message to "It doesn't affect " + player.retrievePokemonName() + "..."
                            opponentMessage = "It doesn't affect " + player.retrievePokemonName() + "...";
                            // Setting the damage multiplier to 0 for an attack with no effect
                            opponentDamageMultiplier = 0;
                            break;
                        default:
                            // Setting the damage multiplier to 1 for an attack of normal effectiveness
                            opponentDamageMultiplier = 1;
                            break;
                    }
                    // Calculating the final damage
                    opponentDamage = (int) (opponentDamageMultiplier * opponentDamage);
                    // Checking if the damage is less than 1 and the attack is not very effective
                    if (opponentDamageMultiplierText.equals("not very effective") && opponentDamage == 0 && selectedPokemon.attacks[0].retrieveBaseDamage() != 0) {
                        opponentDamage = 1;
                    }
                    // Subtracting the damage from the player's temporary health
                    player.establishTemporaryHealth(player.retrieveTemporaryHealth() - opponentDamage);
                    // Checking if the player's temporary health is less than or equal to 0
                    if (player.retrieveTemporaryHealth() <= 0) {
                        player.establishTemporaryHealth(0);
                    }
                    // Updating the player's health text and bar
                    GameGraphics.playerHealthText.setText("HP: " + player.retrieveTemporaryHealth() + "/" + player.retrieveHealth());
                    GameGraphics.playerHealthBar.loseHealth(player.retrieveTemporaryHealth(), player.retrieveHealth());
                    // Displaying a message to inform the player about the opponent's attack
                    JOptionPane.showMessageDialog(GameGraphics.pokemonList, selectedPokemon.retrievePokemonName() + " used " + selectedPokemon.attacks[0].retrieveName() + "!\n" + opponentMessage + "\n" + selectedPokemon.retrievePokemonName() + " did " + opponentDamage + " damage.", "", JOptionPane.INFORMATION_MESSAGE, icon);
                    // Removing the opponent's attack image
                    GameGraphics.attackImage.setIcon(null);
                    // Checking if the player's temporary health is 0
                    if (player.retrieveTemporaryHealth() == 0) {
                        // Removing the player's Pokémon from the temporary team
                        temporaryTeam.remove(player);
                        // Displaying a message to inform the player that their Pokémon fainted
                        JOptionPane.showMessageDialog(GameGraphics.pokemonList, player.retrievePokemonName() + " fainted!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                        // Checking if there are other Pokémon in the temporary team
                        if (temporaryTeam.size() > 0) {
                            // Displaying a dialog box to ask the player to switch to another Pokémon
                            String[] othersAndFirst = new String[temporaryTeam.size()];
                            for (int y = 0; y < temporaryTeam.size(); y++) {
                                othersAndFirst[y] = temporaryTeam.get(y).retrievePokemonName();
                            }
                            String switchedPokemonAfterFainted = (String) JOptionPane.showInputDialog(GameGraphics.pokemonList, "Which pokemon would you like to switch in?", "", JOptionPane.QUESTION_MESSAGE, icon, others, others[0]);
                            // Switching to the selected Pokémon
                            for (int y = 0; y < temporaryTeam.size(); y++) {
                                if (temporaryTeam.get(y).retrievePokemonName().equals(switchedPokemonAfterFainted)) {
                                    player = temporaryTeam.get(y);
                                    GameGraphics.playerPokemonImage.setIcon(new ImageIcon(imageLoader.strToURL("Images/Pokemon/" + switchedPokemon + ".png")));
                                    GameGraphics.playerPokemonName.setText(switchedPokemonAfterFainted + " (Level " + player.retrieveLevel() + ")");
                                    GameGraphics.playerHealthText.setText("HP: " + player.retrieveTemporaryHealth() + "/" + player.retrieveHealth());
                                    GameGraphics.playerHealthBar.loseHealth(player.retrieveTemporaryHealth(), player.retrieveHealth());
                                    JOptionPane.showMessageDialog(GameGraphics.pokemonList, "Go " + player.retrievePokemonName() + "!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                                }
                            }
                        }
                        // If there are no other Pokémon in the temporary team, the player loses
                        else {
                            // Displaying a message to inform the player that they lost
                            continueBattle = false;
                            Sound.BattleAgainstTrainer.stop();
                            JOptionPane.showMessageDialog(GameGraphics.pokemonList, "You lost.", "", JOptionPane.INFORMATION_MESSAGE, icon);
                        }
                    }
                }
                else {
                    // Displaying a message to inform the player that they have no other team members left
                    JOptionPane.showMessageDialog(GameGraphics.pokemonList, "You have no other team members left!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                }
            }
            // Checking if the temporary team is empty or the trainer's team is empty
            if (temporaryTeam.size() == 0 || trainerTeam.size() == 0) {
                continueBattle = false;
            }
        }
        // Displaying the main display
        GameManager.gameGraphics.mainDisplay();
        // Removing all components from the Pokémon list
        GameGraphics.pokemonList.removeAll();
        // Looping through each Pokémon in the player's team
        for (int y = 0; y < team.size(); y++) {
            Pokemon pokemonInTeam = team.get(y);
            // Scaling the Pokémon image
            BufferedImage image = new ProcessingImage().scaleImage(85, 85, "Images/Pokemon/" + pokemonInTeam.retrievePokemonName() + ".png");
            // Adding the Pokémon image to the Pokémon list
            GameGraphics.pokemonList.add(new JLabel(new ImageIcon(image)));
        }
        // Revalidating the Pokémon list
        GameGraphics.pokemonList.revalidate();
        // Calculating the sum of the levels of the Pokémon in the player's team
        int sum = 0;
        for (int y = 0; y < team.size(); y++) {
            Pokemon pokemonInTeam = team.get(y);
            sum += pokemonInTeam.retrieveLevel();
        }
        // Calculating the average level of the Pokémon in the player's team
        averageLevelPlayerPokemon = sum / team.size();
    }
    // Creating a method to calculate the damage dealt by a Pokémon's attack
    public int calculateDamage(int level, int attack, int defense, int baseDamage) {
        return (int) ((2*level+10)/250.0 * attack/(double)defense * baseDamage + 2);
    }
    // Creating a method to switch the player's Pokémon
    public void switchPokemon() {
        // Creating an array to store the names of the other Pokémon in the team
        String[] others = new String[team.size()-1];
        // Looping through each Pokémon in the team, excluding the first one
        for (int y = 0; y < team.size()-1; y++) {
            // Setting the name of the current Pokémon in the array
            others[y] = team.get(y+1).retrievePokemonName();
        }
        // Initializing a variable to store the name of the Pokémon to switch to
        String switchedPokemon = "";

        // Checking if there are other Pokémon in the team
        if (others.length>0) {
            // Displaying a dialog box to ask the player to select a Pokémon to switch to
            switchedPokemon = (String) JOptionPane.showInputDialog(GameGraphics.frame, "Which pokemon would you like to switch into first place?","",JOptionPane.QUESTION_MESSAGE,new ImageIcon(imageLoader.strToURL("Images/PokeBall.png")),others,others[0]);
        }
        // Looping through each Pokémon in the team
        for (int y = 1; y < team.size(); y++) {
            // Checking if the current Pokémon is the one selected to switch to
            if (team.get(y).retrievePokemonName().equals(switchedPokemon)) {
                // Swapping the selected Pokémon with the first Pokémon in the team
                Pokemon temporary = team.get(y);
                team.set(y,team.get(0));
                team.set(0,temporary);
            }
        }
        // Removing all components from the Pokémon list panel
        GameGraphics.pokemonList.removeAll();
        // Looping through each Pokémon in the team
        for (int y = 0; y < team.size(); y++) {
            Pokemon pokemonInTeam = team.get(y);
            // Scaling the image of the current Pokémon
            BufferedImage image = new ProcessingImage().scaleImage(85, 85, "Images/Pokemon/" + pokemonInTeam.retrievePokemonName() + ".png");
            // Adding the scaled image to the Pokémon list panel
            GameGraphics.pokemonList.add(new JLabel(new ImageIcon(image)));
        }
        // Revalidating the Pokémon list panel to update its layout
        GameGraphics.pokemonList.revalidate();
    }
}