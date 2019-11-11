package demo;

import java.awt.Graphics;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;


public class EditGame extends JPanel implements Runnable {
	Clip clip, clip1;
	EditMap editMap = new EditMap();
	EditMenu editMenu = new EditMenu();
	Play play = new Play();
	LoadSound sound = new LoadSound();
	HelpScreen helpScreen = new HelpScreen();
	boolean start = false, exit = false, help = false, CheckMenu = true;
	public static boolean test = true;
	double lastTime = System.currentTimeMillis();
	long time = 0;
	
	public EditGame() {
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g) {
		
		if(CheckMenu) {
			editMenu.paint(g);
		}
		if(GameStage.xClick >= 225 && GameStage.xClick <= 450 && GameStage.yClick >= 140 && GameStage.yClick <= 190) {
			start = true;
			clip.stop();
			try 
			   {
			    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("E:\\GameTower\\Sound\\TowerDefenseOST.wav").getAbsoluteFile()); 
			    clip1 = AudioSystem.getClip();
			    clip1.open(audioInputStream);
			    LoadSound.setVol(0.25, clip1);
			    clip1.start();
			    clip1.loop(clip1.LOOP_CONTINUOUSLY);
			    GameStage.xClick = 0;
			   }
			   catch(Exception ex)
			   {
			     System.out.println("Error with playing sound.");
			     ex.printStackTrace( );
			   }
		}
		if(!Play.sound && Play.count1 > 0) {
			time = clip1.getMicrosecondPosition();
			clip1.stop();
			test = true;
		}
		if(Play.sound && Play.count1 > 0 && test ) {
			clip1.setMicrosecondPosition(time);
			clip1.start();
			test = false;
		}
		
		else if(GameStage.xClick >= 225 && GameStage.xClick <= 450 && GameStage.yClick >= 220 && GameStage.yClick <= 270 && !start) {
			help = true;
		}
		
		else if (GameStage.xClick >= 180 && GameStage.xClick <= 450 && GameStage.yClick >= 300 && GameStage.yClick <= 350 && !start && !help) {
			exit = true;
		}
		if(start) {
			editMap.paint(g);
			CheckMenu = false;
			play.render(g);
			play.update(g);
			if(GameStage.xClick >= 550 && GameStage.xClick <= 650 && GameStage.yClick >= 460 && GameStage.yClick <= 510) {
				start = false;
				CheckMenu = true;
			}
		}
		if(help) {
			helpScreen.paint(g);
			CheckMenu = false;
			if(GameStage.exitHelp) {
				GameStage.exitHelp = false;
				help = false;
				CheckMenu = true;
				GameStage.xClick = 0; GameStage.xSelect = 0;
				GameStage.yClick = 0; GameStage.ySelect = 0;
			}
		}
		if(exit) System.exit(0);	
	}

	@Override
	public void run() {
		try 
		   {
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("E:\\GameTower\\Sound\\MusicBG.wav").getAbsoluteFile()); 
		    clip = AudioSystem.getClip();
		    clip.open(audioInputStream);
		    sound.setVol(0.25, clip);
		    clip.loop(clip.LOOP_CONTINUOUSLY);
		    clip.start();
		   }
		   catch(Exception ex)
		   {
		     System.out.println("Error with playing sound.");
		     ex.printStackTrace( );
		   }
		play.restartGame();
		
		while(true) {
			repaint();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
