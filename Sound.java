import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

// Creating a class to operate sound playback
public class Sound {
    // Declaring constants for the audio clips and specifying the file path
    public static final Sound BattleAgainstPokemon = new Sound("/BattleAgainstPokemon.wav");
    public static final Sound BattleAgainstTrainer = new Sound("/BattleAgainstTrainer.wav");
    public static final Sound Environment = new Sound("/Environment.wav");
    public static final Sound VictoryAgainstTrainer = new Sound("/VictoryAgainstTrainer.wav");
    // Declaring a variable to store the audio clip for playback
    public Clip clip;
    // Creating a constructor to initialize the sound object with the given file path
    public Sound(String fileName) {
        try {
            // Obtaining an audio input stream from the specified file path
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource(fileName));
            // Initializing the clip to play the sound
            clip = AudioSystem.getClip();
            // Opening the clip with the audio input stream for playback preparation
            clip.open(audioInputStream);
        }
        // Catching and printing exceptions if file loading or audio processing fails
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Creating a method to play the sound in a continuous loop
    public void play() {
        try {
            // Using a new thread to play the sound without blocking the main thread
            new Thread() {
                // Overriding the run method to define the behavior of the thread
                public void run() {
                    // Setting the clip to loop continuously for seamless playback
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
            }.start(); // Starting the thread for playback
        }
        // Catching and printing exceptions if playback fails
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // Creating a method to stop the sound playback
    public void stop() {
        // Stopping the clip to halt the current playback
        clip.stop();
    }
    // Creating a method to restart the sound playback from the beginning
    public void restart() {
        // Stopping the current playback to reset the clip
        clip.stop();
        // Resetting the clip's frame position to the beginning
        clip.setFramePosition(0);
        // Starting the playback from the beginning
        clip.start();
    }
}
