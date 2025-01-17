import java.util.ArrayList;
import java.util.Collections;

// Creating a class to store and manage everything relating to type effectiveness in association with attacks
public class Attack {
    // Declaring the name of the attack
    public String attackName;
    // Declaring the type of the attack
    public String attackType;
    // Declaring the base damage of the attack
    public int attackBaseDamage;
    // Declaring ArrayLists to categorize the effectiveness of the attack type
    public ArrayList<String> superEffective = new ArrayList<>();
    public ArrayList<String> normalEffective = new ArrayList<>();
    public ArrayList<String> notVeryEffective = new ArrayList<>();
    public ArrayList<String> noEffect = new ArrayList<>();
    // Creating a constructor to initialize the attack details and assign effectiveness categories
    public Attack(String name, String type, int baseDamage) {
        attackName = name;
        attackType = type;
        attackBaseDamage = baseDamage;
        // Using a method to sorting the effectiveness based on the attack's type
        sortEffectivenessMethod(type);
    }
    // Creating a method to retrieve the name of the attack
    public String retrieveName() {
        // Returning the name of the attack
        return attackName;
    }
    // Creating a method to retrieve the type of the attack
    public String retrieveType() {
        // Returning the type of the attack
        return attackType;
    }
    // Creating a method to retrieve the base damage of the attack
    public int retrieveBaseDamage() {
        // Returning the base damage of the attack
        return attackBaseDamage;
    }
    // Creating a method to sort effectiveness based on the provided Pokémon type
    public void sortEffectivenessMethod(String type) {
        // Using a switch statement to determine the type and populate the effectiveness arrays accordingly
        switch (type) {
            // Creating cases for the "Normal", "Grass", "Fire", "Water", "Ice", "Electric", "Psychic", "Dark", and "Fairy" types
            case "Normal":
                // Populating the associated effectiveness arrays for the types
                populatingEffectivenessArrays(
                        // Creating an array to store the super-effective types
                        new String[]{},
                        // Creating an array to store the normal-effective types
                        new String[]{"Normal", "Grass", "Fire", "Water", "Ice", "Electric", "Psychic", "Dark", "Fairy"},
                        // Creating an array to store the not-very-effective types
                        new String[]{},
                        // Creating an array to store the no-effect types
                        new String[]{}
                );
                break;
            case "Grass":
                populatingEffectivenessArrays(
                    new String[]{"Water"},
                    new String[]{"Normal", "Ice", "Electric", "Psychic", "Dark", "Fairy"},
                    new String[]{"Grass", "Fire"},
                    new String[]{}
                );
                break;
            case "Fire":
                populatingEffectivenessArrays(
                    new String[]{"Grass", "Ice"},
                    new String[]{"Normal", "Electric", "Psychic", "Dark", "Fairy"},
                    new String[]{"Fire", "Water"},
                    new String[]{}
                );
                break;
            case "Water":
                populatingEffectivenessArrays(
                    new String[]{"Fire"},
                    new String[]{"Normal", "Ice", "Electric", "Psychic", "Dark", "Fairy"},
                    new String[]{"Grass", "Water"},
                    new String[]{}
                );
                break;
            case "Ice":
                populatingEffectivenessArrays(
                    new String[]{"Grass"},
                    new String[]{"Normal", "Electric", "Psychic", "Dark", "Fairy"},
                    new String[]{"Fire", "Water", "Ice"},
                    new String[]{}
                );
                break;
            case "Electric":
                populatingEffectivenessArrays(
                    new String[]{"Water"},
                    new String[]{"Normal", "Fire", "Ice", "Psychic", "Dark", "Fairy"},
                    new String[]{"Grass", "Electric"},
                    new String[]{}
                );
                break;
            case "Psychic":
                populatingEffectivenessArrays(
                    new String[]{},
                    new String[]{"Normal", "Grass", "Fire", "Water", "Ice", "Electric", "Fairy"},
                    new String[]{"Psychic"},
                    new String[]{"Dark"}
                );
                break;
            case "Dark":
                populatingEffectivenessArrays(
                    new String[]{"Psychic", "Fairy"},
                    new String[]{"Normal", "Grass", "Fire", "Water", "Ice", "Electric"},
                    new String[]{"Dark"},
                    new String[]{}
                );
                break;
            case "Fairy":
                populatingEffectivenessArrays(
                    new String[]{"Ice", "Dark"},
                    new String[]{"Normal", "Grass", "Water", "Electric", "Psychic", "Fairy"},
                    new String[]{"Fire"},
                    new String[]{}
                );
                break;
            default:
                break;
        }
    }
    // Creating a method to populate the effectiveness arrays with the provided data
    public void populatingEffectivenessArrays(String[] superE, String[] normalE, String[] notVeryE, String[] noE) {
        // Adding the provided super-effective, normal-effective, not-very-effective, no-effect types to the superEffective, normalEffective, notVeryEffective, and noEffect ArrayLists
        Collections.addAll(superEffective, superE);
        Collections.addAll(normalEffective, normalE);
        Collections.addAll(notVeryEffective, notVeryE);
        Collections.addAll(noEffect, noE);
    }
    // Creating a method to determine the attack's effectiveness based on the Pokémon's type
    public String determineAttackEffectiveness(String typeOfPokemon) {
        // Creating a series of if, else if, and else statements to check if the Pokémon's type is in the superEffective, normalEffective, notVeryEffective, or noEffect ArrayLists
        // Returning the appropriate effectiveness statements based on the type of the attack
        if (superEffective.contains(typeOfPokemon)) {
            return "super effective";
        }
        else if (normalEffective.contains(typeOfPokemon)) {
            return "normal";
        }
        else if (notVeryEffective.contains(typeOfPokemon)) {
            return "not very effective";
        }
        else {
            return "no effect";
        }
    }
}