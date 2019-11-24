package editGame;

import java.awt.Graphics;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;


public class EditGame extends JPanel implements Runnable {
	
	static Clip clip, clip1;
	EditMap editMap = new EditMap();
	EditMenu editMenu = new EditMenu();
	InfoScreen infoScreen = new InfoScreen();
	Play play = new Play();
	LoadSound sound = new LoadSound();
	HelpScreen helpScreen = new HelpScreen();
	static boolean start = false, help = true, CheckMenu = true;
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
		if(GameStage.xClick >= 60 && GameStage.xClick <= 290 && GameStage.yClick >= 220 && GameStage.yClick <= 260 && !start) {
			System.out.println("New Game");
			Play.sound = true;
			play.restartGame();
			start = true;
			help = true;
			GameStage.exitHelp = false;
			clip.stop();
			
			try 
			   {
			    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("E:\\GameTower\\Sound\\TowerDefenseOST.wav").getAbsoluteFile()); 
			    clip1 = AudioSystem.getClip();
			    clip1.open(audioInputStream);
			    sound.setVol(0.5, clip1);
			    clip1.start();
			    clip1.loop(10000);
			    GameStage.xClick = 0;
			   }
			   catch(Exception ex)
			   {
			     ex.printStackTrace( );
			   }
		}
		
		if(GameStage.xClick >= 390 && GameStage.xClick <= 620 && GameStage.yClick >= 220 && GameStage.yClick <= 360 && !start) {
			System.out.println("Resume Game");
			Play.sound = true;
			start = true;
			play.resumeGame();
			clip.stop();
			try 
			   {
			    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("E:\\GameTower\\Sound\\TowerDefenseOST.wav").getAbsoluteFile()); 
			    clip1 = AudioSystem.getClip();
			    clip1.open(audioInputStream);
			    sound.setVol(0.5, clip1);
			    clip1.start();
			    clip1.loop(100);
			    GameStage.xClick = 0;
			   }
			   catch(Exception ex)
			   {
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
		
		else if (GameStage.xClick >= 220 && GameStage.xClick <= 450 && GameStage.yClick >= 310 && GameStage.yClick <= 370 && !start) {
			System.exit(0);
		}
		if(start) {
			if(help) {
				helpScreen.paint(g);
				if(GameStage.exitHelp) help = false;
			}
			else {
				editMap.paint(g);
				CheckMenu = false;
				play.render(g);
				play.update(g);
			}
		}
	}

	@Override
	public void run() {
		try 
		   {
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("E:\\GameTower\\Sound\\MusicBG.wav").getAbsoluteFile()); 
		    clip = AudioSystem.getClip();
		    clip.open(audioInputStream);
		    sound.setVol(0.3, clip);
		    clip.loop(Clip.LOOP_CONTINUOUSLY);
		    clip.start();
		   }
		   catch(Exception ex)
		   {
			  ex.printStackTrace( );
		   }
		while(true) {
			repaint();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
