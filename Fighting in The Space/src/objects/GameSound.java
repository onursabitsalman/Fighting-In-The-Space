package objects;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
 import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameSound {

	public static Clip clip;
	
	public GameSound() {
		
		try {
			
			URL url = this.getClass().getClassLoader().getResource("StarWars.wav");
			clip = AudioSystem.getClip();

			AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			clip.open(audio);
			clip.start();
			
		}
		
		catch(UnsupportedAudioFileException uae) {
			
			System.out.println(uae); 
			
		}
		
		catch(IOException ioe) {
			
			System.out.println(ioe); 
			
		} 
		
		catch(LineUnavailableException lua) { 
			
			System.out.println(lua); 
			
		}
	}	
}