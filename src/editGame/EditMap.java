package editGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import tower.MachineGunTower;
import tower.NormalTower;
import tower.SniperTower;
import tower.SuperTower;

public class EditMap extends JPanel{
	private BufferedImage bg1, bg2, bg3, tower1, tower2, tower3, tower4,
	exitButtonMini, StartWave, StopWave, Wave, CreditsGraphic, UpdateTower, SellTower,
	HP, X2Button, X1Button, MusicOn, Silent, Score, start, 
	BoomItem, FrozenItem, AccaccelerationItem, Limit, icon3;
	
  public static int[][] arr = {
		    		
			{2,2,0,0,0,0,0,0,0,0,0,2,0,0,0,3,3,3,3,3,3},
			{1,1,1,2,0,1,1,1,1,0,0,0,0,0,2,3,3,3,3,3,3},
			{0,0,1,0,0,1,0,0,1,0,0,0,0,0,2,3,3,3,3,3,3},
			{0,0,1,1,1,1,0,0,1,0,0,0,0,0,2,3,3,3,3,3,3},
			{0,0,0,0,0,0,0,0,1,2,2,0,2,0,0,3,3,3,3,3,3},
			{0,0,0,0,0,0,0,0,1,2,2,0,0,0,0,3,3,3,3,3,3},
			{0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,3,3,3,3,3,3},
			{0,0,0,0,0,1,0,0,0,0,0,0,2,2,0,3,3,3,3,3,3},
			{0,0,0,0,0,1,0,0,1,1,1,1,0,0,0,3,3,3,3,3,3},
			{0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,3,3,3,3,3,3},
			{0,0,0,0,0,1,1,1,1,0,0,1,1,1,1,3,3,3,3,3,3},
			{0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}
			};
  
  	
	
	long lastTime = 0;

	//edit map game
	public void paint(Graphics g) {
		try {
			bg1 = ImageIO.read(new File("E:\\GameTower\\Map2.jpg"));
			bg2 = ImageIO.read(new File("E:\\GameTower\\BG2.jpg"));
			bg3 = ImageIO.read(new File("E:\\GameTower\\TowerMenuGraphic.png"));
			tower1 = ImageIO.read(new File("E:\\GameTower\\Tower1.png"));
			tower2 = ImageIO.read(new File("E:\\GameTower\\Tower2.png"));
			tower3 = ImageIO.read(new File("E:\\GameTower\\Tower3.png"));
			tower4 = ImageIO.read(new File("E:\\GameTower\\Tower4.png"));
			exitButtonMini = ImageIO.read(new File("E:\\GameTower\\ExitButtonMini.png"));
			StartWave = ImageIO.read(new File("E:\\GameTower\\PauseWave.png"));
			StopWave = ImageIO.read(new File("E:\\GameTower\\ResumeWave.png"));
			CreditsGraphic = ImageIO.read(new File("E:\\GameTower\\CurrencyGraphic.png"));
			UpdateTower = ImageIO.read(new File("E:\\GameTower\\UpgradeGraphic.png"));
			SellTower = ImageIO.read(new File("E:\\GameTower\\RefundTower.png"));
			Wave = ImageIO.read(new File("E:\\GameTower\\WaveGraphic.png"));
			HP = ImageIO.read(new File("E:\\GameTower\\Heart.png"));
			X2Button = ImageIO.read(new File("E:\\GameTower\\X2Speed.png"));
			X1Button = ImageIO.read(new File("E:\\GameTower\\X1Speed.png"));
			MusicOn = ImageIO.read(new File("E:\\GameTower\\Music.jpg"));
			Silent = ImageIO.read(new File("E:\\GameTower\\Mute.jpg"));
			Score = ImageIO.read(new File("E:\\GameTower\\ScoreGraphic.png"));
			Limit = ImageIO.read(new File("E:\\GameTower\\Limit.png"));
			BoomItem = ImageIO.read(new File("E:\\GameTower\\BoomItem.png"));
			FrozenItem = ImageIO.read(new File("E:\\GameTower\\FrozenItem.png"));
			AccaccelerationItem = ImageIO.read(new File("E:\\GameTower\\Acceleration.png"));
			start = ImageIO.read(new File("E:\\GameTower\\Start.png"));
			icon3 = ImageIO.read(new File("E:\\GameTower\\Icon3.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		for(int i = 0; i<16; i++) {
//			for(int j=0; j<21; j++) {
//				if(arr[i][j] == 3) g.drawImage(bg2, j*32, i*32, null);
//			}
//		}
		g.drawImage(bg2, 0, 0, null);
		g.drawImage(bg1, 0, 0, null);
		g.drawImage(bg3, 480, 0, null);
		g.drawImage(tower1, 520, 80, null);
		g.drawImage(tower2, 606, 75, null);
		g.drawImage(tower3, 525, 155, null);
		g.drawImage(tower4, 606, 155, null);
		g.drawImage(UpdateTower, 505, 222, null);
		g.drawImage(SellTower, 585, 220, null);
		g.drawImage(CreditsGraphic, 2, 440, null);
		g.drawImage(exitButtonMini, 520, 455, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("NewellsHand", Font.BOLD, 10));
		if(Play.waveIsInProgress) {
			g.drawImage(StopWave, 585, 330, null);
			
			g.drawString("StoptWave", 585, 380);
		}
		else{
			g.drawImage(start, 453, 332, null);
			g.drawImage(StartWave, 585, 335, null);
			g.drawString("StartWave", 585, 380);
		}
		g.drawImage(Wave, 510, 400, null);
		g.drawImage(HP, 20, 417, null);
		if(Play.X2Speed) {
			g.drawImage(X2Button, 510 , 330, null);
			g.drawString("X2 Speed", 510, 380);
		}
		else{
			g.drawImage(X1Button, 510 , 330, null);
			g.drawString("X1 Speed", 510, 380);
		}
		
		g.drawString("$" + NormalTower.newBuyCost, 523, 120);
		g.drawString("$" + SniperTower.newBuyCost, 605, 120);
		g.drawString("$" + MachineGunTower.newBuyCost , 523, 200);
		g.drawString("$" + SuperTower.newBuyCost, 605, 200);
		g.setColor(Color.BLACK);
		g.drawString("Normal Tower", 502, 135);
		g.drawString("Sniper Tower", 586, 135);
		g.drawString("MachineGun", 502, 215);
		g.drawString("Super Tower", 586, 215);
		g.drawString("Rufund Tower", 585, 294);
		g.drawString("Upgrade Tower", 500, 294);
		g.setColor(Color.WHITE);
		g.setFont(new Font("NewellsHand", Font.ROMAN_BASELINE, 11));
		g.drawString("BoomItem", 400, 458);
		g.drawString("$150", 410, 470);
		g.drawString("FrozenItem", 280, 458);
		g.drawString("$100", 290, 470);
		g.drawString("Acceleration Tower", 150, 458);
		g.drawString("$100", 170, 470);
		g.setFont(new Font("NewellsHand", Font.ITALIC, 18));
		g.drawString(":" + Player.getPlayer().HP, 42, 430);
		g.drawString(": $" + Player.getPlayer().credits, 45, 465);
		g.setColor(Color.BLUE);
		g.drawString(":" + Play.currentLevel, 580, 418);
		g.drawImage(start, 0, 36, null);
		if(Play.sound) g.drawImage(MusicOn, 0, 0, null);
		else g.drawImage(Silent, 0, 0, null);
		g.drawImage(Score, 10, 385, null);
		g.setColor(Color.WHITE);
		if(Player.getPlayer().score < Play.HighScore) g.drawString(":"+  (int)Player.getPlayer().score +"/"+ Play.HighScore, 75, 402);
		if(Player.getPlayer().score >= Play.HighScore) {
			if(lastTime == 0) lastTime = System.currentTimeMillis();
			if(System.currentTimeMillis() - lastTime <= 5000 ) {
				g.setColor(Color.RED);
				g.drawString("Chúc mừng, bạn đã vượt qua kỉ lục trước đó", 100, 350);
			}
			g.setColor(Color.WHITE);
			g.drawString(":"+  (int)Player.getPlayer().score, 80, 402);
		}
		g.drawImage(Limit, 108, 384, null);
		g.drawImage(Limit, 455, 384, null);
		g.drawImage(BoomItem, 390, 388, null);
		g.drawImage(FrozenItem, 270, 385, null);
		g.drawImage(AccaccelerationItem, 150, 388, null);
		g.drawImage(icon3, 448, 0, null);
	}
		
}
