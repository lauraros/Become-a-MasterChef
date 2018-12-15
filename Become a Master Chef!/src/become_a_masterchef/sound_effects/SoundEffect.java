package become_a_masterchef.sound_effects;

//Import sound related libraries 
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/*
 * This enum encapsulates all the sound effects of our game, so as to separate the sound playing
 * codes from the game codes. It allows to simply invoke SoundEffect.SOUND_NAME.play().
 * to play a sound.
 */
public enum SoundEffect {

	// Definition of all our sound effect names and the associated wave file.
	CLICKRIGHT("sounds/service-bell.wav"),
	ITALIAN("sounds/mammamia.wav"),
	CHINESE("sounds/chinese-gong.wav"),
	BONUS("sounds/bonus.wav"),
	MINUS("sounds/lose.wav"),
	CUP("sounds/Cup.wav"),
	WIN("sounds/applause.wav"), 
	LOSE("sounds/gameOver.wav"),
	WRONG("sounds/Down.wav"),
	MOVE("sounds/click.wav");
   
	// Nested class for specifying volume
   public static enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }


   public static Volume volume = Volume.LOW;

   // Each sound effect has its own clip, loaded with its own sound file.
   private Clip clip;

   // Constructor to construct each element of the enum with its own sound file.
   SoundEffect(String soundFileName) {
      try {
         
    	 // Use URL (instead of File) to read from disk and JAR.
         URL url = this.getClass().getClassLoader().getResource(soundFileName);
         
         // Set up an audio input stream piped from the sound file.
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
         
         // Get a clip resource.
         clip = AudioSystem.getClip();
         
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioInputStream);
      } catch (UnsupportedAudioFileException e) {
    	 e.printStackTrace();        
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }

   // Play or Re-play the sound effect from the beginning, by rewinding.
   public void play() {
      if (volume != Volume.MUTE) {
         if (clip.isRunning())
            clip.stop();   // Stop the player if it is still running
         clip.setFramePosition(0); // rewind to the beginning
         clip.start();     // Start playing
      }
   }

   /* Optional static method to pre-load all the sound files 
   so that the play is not paused while loading the file for the first time.*/
   public static void init() {
      values(); // calls the constructor for all the elements
   }

}