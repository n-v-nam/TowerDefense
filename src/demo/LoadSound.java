package demo;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;

public class LoadSound {
	
	public void playSound(String soundName, double dB)
	 {
	   try 
	   {
	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( )); 
	    Clip clip = AudioSystem.getClip();
	    clip.open(audioInputStream);
	    setVol(dB, clip);
	    clip.start();
	   }
	   catch(Exception ex)
	   {
	     System.out.println("Error with playing sound.");
	     ex.printStackTrace( );
	   }
	 }
	public static void setVol(double vol, Clip clip) {
		FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		float dB = (float)(Math.log(vol)/Math.log(10)*20);
		gain.setValue(dB);
	}
	
}
