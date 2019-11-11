package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

import gameTile.MachineGunTower;
import gameTile.NormalTower;
import gameTile.SniperTower;
import gameTile.SuperTower;

public class EditMap extends JPanel{
	private BufferedImage bg1, bg2, bg3, bg4, tower1, tower2, tower3, tower4, exitButtonMini, StartWave, StopWave, Wave, CreditsGraphic, UpdateTower, SellTower, HP, X2Button, X1Button, RockTile, CactusTile, MusicOn, Silent;
	
	public static int[][] arr = new int[21][17];

	//edit map game
	public void paint(Graphics g) {
		try {
			bg1 = ImageIO.read(new File("E:\\GameTower\\Cross.png"));
			bg2 = ImageIO.read(new File("E:\\GameTower\\SandTile.png"));
			bg3 = ImageIO.read(new File("E:\\GameTower\\TowerMenuGraphic.png"));
			bg4 = ImageIO.read(new File("E:\\GameTower\\WAll.png"));
			tower1 = ImageIO.read(new File("E:\\GameTower\\Tower1.png"));
			tower2 = ImageIO.read(new File("E:\\GameTower\\Tower2.png"));
			tower3 = ImageIO.read(new File("E:\\GameTower\\Tower3.png"));
			tower4 = ImageIO.read(new File("E:\\GameTower\\Tower4.png"));
			exitButtonMini = ImageIO.read(new File("E:\\GameTower\\ExitButtonMini.png"));
			StartWave = ImageIO.read(new File("E:\\GameTower\\PauseWave.png"));
			StopWave = ImageIO.read(new File("E:\\GameTower\\ResumeWave.png"));
			CreditsGraphic = ImageIO.read(new File("E:\\GameTower\\CurrencyGraphic.png"));
			UpdateTower = ImageIO.read(new File("E:\\GameTower\\UpgradeButtonGraphic.png"));
			SellTower = ImageIO.read(new File("E:\\GameTower\\SellButtonGraphic.png"));
			Wave = ImageIO.read(new File("E:\\GameTower\\WaveGraphic.png"));
			HP = ImageIO.read(new File("E:\\GameTower\\Heart.png"));
			X2Button = ImageIO.read(new File("E:\\GameTower\\X2Speed.png"));
			X1Button = ImageIO.read(new File("E:\\GameTower\\X1Speed.png"));
			RockTile = ImageIO.read(new File("E:\\GameTower\\RockTile.png"));
			CactusTile = ImageIO.read(new File("E:\\GameTower\\CactusTile.png"));
			MusicOn = ImageIO.read(new File("E:\\GameTower\\Music.jpg"));
			Silent = ImageIO.read(new File("E:\\GameTower\\Mute.jpg"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i=0; i<21; ++i) {
			for(int j=0; j<17; ++j) {
				arr[i][j] = 0;
				g.drawImage(bg4, i*32, j*32, null);
			}
		}
		for(int i=4; i<15; ++i) {
			arr[i][1] = 1;
		}
		for(int i=1; i<13; ++i) {
			arr[i][9] = 1;
		}
		for(int i=4; i<13; ++i) {
			arr[i][7] = 1; 
		}
		
		arr[1][11] = 1; arr[1][10] = 1; arr[12][8] = 1; arr[4][6] = 1; arr[4][5] = 1; arr[4][4] = 1; arr[4][3] = 1; arr[4][2] = 1;
		arr[2][5] = 2; arr[3][7] = 2; arr[8][4] = 2; arr[10][0] = 3; arr[13][5] = 3;
		
		for(int i=0; i<15; ++i) {
			for(int j=0; j<12; ++j) {
				if(arr[i][j] == 0) g.drawImage(bg2, i*32, j*32, null);
				if(arr[i][j] == 1) g.drawImage(bg1, i*32, j*32, null);
				if(arr[i][j] == 2) g.drawImage(RockTile, i*32, j*32, null);
				if(arr[i][j] == 3) g.drawImage(CactusTile, i*32, j*32, null);
			}
		}
		g.drawImage(bg3, 480, 0, null);
		g.drawImage(tower1, 520, 80, null);
		g.drawImage(tower2, 606, 75, null);
		g.drawImage(tower3, 525, 155, null);
		g.drawImage(tower4, 606, 155, null);
		g.drawImage(UpdateTower, 500, 217, null);
		g.drawImage(SellTower, 585, 217, null);
		g.drawImage(CreditsGraphic, 2, 440, null);
		g.drawImage(exitButtonMini, 548, 455, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("NewellsHand", Font.ROMAN_BASELINE, 10));
		if(Play.waveIsInProgress) {
			g.drawImage(StopWave, 585, 330, null);
			g.drawString("StoptWave", 585, 380);
		}
		else{
			g.drawImage(StartWave, 585, 335, null);
			g.drawString("StartWave", 585, 380);
		}
		g.drawImage(Wave, 510, 380, null);
		g.drawImage(HP, 20, 400, null);
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
		g.drawString("Normal Tower", 502, 135);
		g.drawString("Sniper Tower", 586, 135);
		g.drawString("MachineGun", 502, 215);
		g.drawString("Super Tower", 586, 215);
		g.drawString("Rufund Tower", 585, 294);
		g.drawString("Upgrade Tower", 500, 294);
		g.setFont(new Font("NewellsHand", Font.ITALIC, 18));
		g.drawString(":" + Player.getPlayer().HP, 42, 413);
		g.drawString(": $" + Player.getPlayer().credits, 45, 465);
		g.setColor(Color.RED);
		g.drawString("" + Play.currentLevel, 600, 418);
		if(Play.sound) g.drawImage(MusicOn, 0, 0, null);
		else g.drawImage(Silent, 0, 0, null);
	}
		
}
