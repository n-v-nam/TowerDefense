package editGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.imageio.ImageIO;
import enemy.Enemy;
import enemy.EnemyGenerator;
import item.AccelerationTower;
import item.BoomItem;
import item.FrozenItem;
import tower.MachineGunTower;
import tower.NormalTower;
import tower.Projectile;
import tower.SniperTower;
import tower.SuperTower;
import tower.Tower;
import tower.Projectile.pType;

public class Play {
	public static ArrayList<Tower>  towerList = new ArrayList<Tower>();
	public static Queue<Enemy> enemyList = new LinkedList<Enemy>();
	public static Queue<Enemy> activeEnemyList = new LinkedList<Enemy>();
	public static ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
	Queue<Enemy> activeEnemySave = new LinkedList<Enemy>();
	Queue<Enemy> enemyListSave = new LinkedList<Enemy>();
	ArrayList<Tower> towerListSave = new ArrayList<Tower>();
	ArrayList<Projectile> pToRemove;
	Queue<Enemy> eFrozen = new LinkedList<Enemy>(); 
	Queue<Enemy> eBoom = new LinkedList<Enemy>(); 
	InfoScreen info = new InfoScreen();
	public int creditsSave = 0, HPSave = 0;
	BufferedImage normalTower, sniperTower, machineGunTower,superTower , normalEnemy, tankerEnemy, smallEnemy, bossEnemy,
	NormalProjectile, SniperProjectile, MachineGunProjectile, SuperProjectile, SelectTower, NoCanAction, FrozenGraphic, StormGraphic, BoomGraphic;
	public static int currentLevel, tickCount, enemySpawnDelay = 30, levelSave, ScoreSave, HighScoreSave = 20;
	public EnemyGenerator generator;
	public static boolean waveIsInProgress = true, gameOver = false, sound = true, upgradeTower = false, noUpgrade = false,
			builtTower = false, refundTower = false, selectPlace = true, X2Speed = false, exit = false,
			accelerationTower = false, stormItem = false, boomItem = false, frozenItem = false, infoScreen = false;
	public double lastTime, lastTime1, lastTime2, lastTime3, count = 0, count2 = 0, xTemp1, yTemp1, xTemp2, yTemp2, xTemp3, yTemp3;
	public static int count1 = 0, HighScore = HighScoreSave;
	
	LoadSound loadSound = new LoadSound();
	
	public void update(Graphics g){
		if(waveIsInProgress){
			if(enemyList.size()!=0){
				addEnemyToActiveEnemyList();
			}
			if(!gameOver){
			updateProjectiles();			
			updateEnemy();
			targetEnemy();
			attackEnemy();
			}
		}
		else {
			drawEnemy(g);
			drawProjectiles(g);
			drawTower(g);
		}
		Mouse(GameStage.xSelect, GameStage.ySelect, g);
		if(Player.getPlayer().HP == 0){
			gameOver = true;
			}
	}
	// vẽ tất cả đối tượng
	public void render(Graphics g){
		
		drawTower(g);
		
		if(waveIsInProgress){
			drawEnemy(g);
			drawProjectiles(g);
		}
		if(gameOver && !exit){
			g.setColor(Color.WHITE);
			g.setFont( new Font("",Font.BOLD, 30));
			g.drawString("GAME OVER", 200, 200);
		}
}
	
	public void drawTower(Graphics g) {
		
		
		for(Tower t: towerList) {
			try {
				if(t.level == 1) {
					normalTower = ImageIO.read(new File("E:\\GameTower\\Tower1.png"));
					sniperTower = ImageIO.read(new File("E:\\GameTower\\Tower2.png"));
					machineGunTower = ImageIO.read(new File("E:\\GameTower\\Tower3.png"));
					superTower = ImageIO.read(new File("E:\\GameTower\\Tower4.png"));
				}
				if(t.level == 2) {
					normalTower = ImageIO.read(new File("E:\\GameTower\\Tower1.2.png"));
					sniperTower = ImageIO.read(new File("E:\\GameTower\\Tower2.2.png"));
					machineGunTower = ImageIO.read(new File("E:\\GameTower\\Tower3.2.png"));
					superTower = ImageIO.read(new File("E:\\GameTower\\Tower4.2.png"));
				}
				if(t.level == 3) {
					normalTower = ImageIO.read(new File("E:\\GameTower\\Tower1.3.png"));
					sniperTower = ImageIO.read(new File("E:\\GameTower\\Tower2.3.png"));
					machineGunTower = ImageIO.read(new File("E:\\GameTower\\Tower3.3.png"));
					superTower = ImageIO.read(new File("E:\\GameTower\\Tower4.3.png"));
				}
					
			} 
			catch (IOException e) {
				e.printStackTrace();
			};
			switch(t.towerType) {
			case NORMAL:
				g.drawImage(RotateImage.rotated(normalTower, t.getRotationAngleInDegrees()), (int)t.xPos/32*32 + (32-normalTower.getWidth())/2, (int)t.yPos/32*32 + (32-normalTower.getHeight())/2, null);

				break;
			case SNIPERTOWER:
				g.drawImage(RotateImage.rotated(sniperTower, t.getRotationAngleInDegrees()), (int)t.xPos/32*32 + (32-sniperTower.getWidth())/2, (int)t.yPos/32*32 + (32-sniperTower.getHeight())/2, null);
				break;
			case MACHINEGUNTOWER:
				g.drawImage(RotateImage.rotated(machineGunTower, t.getRotationAngleInDegrees()), (int)t.xPos/32*32 +(32-machineGunTower.getWidth())/2, (int)t.yPos/32*32 + (32-machineGunTower.getHeight())/2, null);
				break;
			case SUPERTOWER:
				g.drawImage(RotateImage.rotated(superTower, t.getRotationAngleInDegrees()), (int)t.xPos/32*32 +(32-superTower.getWidth())/2, (int)t.yPos/32*32 + (32-superTower.getHeight())/2, null);
				break;
			default:
				break;
			}
		}
	}
	
	public void drawEnemy(Enemy e, Graphics g) {
		try {
			normalEnemy = ImageIO.read(new File("E:\\GameTower\\NormalEnemy.png"));
			tankerEnemy = ImageIO.read(new File("E:\\GameTower\\TankerEnemy.png"));
			smallEnemy = ImageIO.read(new File("E:\\GameTower\\SmallEnemy.png"));
			bossEnemy = ImageIO.read(new File("E:\\GameTower\\BossEnemy.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		switch(e.enemyType) {
		case NORMAL:
			g.drawImage(RotateImage.rotated(normalEnemy, e.direction), (int)e.xLoc + (32-normalEnemy.getWidth())/2, (int)e.yLoc + (32-normalEnemy.getHeight())/2, null);
			
			break;
		case TANKER:
			g.drawImage(RotateImage.rotated(tankerEnemy, e.direction), (int)e.xLoc + (32-tankerEnemy.getWidth())/2, (int)e.yLoc + (32-tankerEnemy.getHeight())/2 , null);
			break;
		case SMALL:
			g.drawImage(RotateImage.rotated(smallEnemy, e.direction), (int)e.xLoc + (32-smallEnemy.getWidth())/2, (int)e.yLoc + (32-smallEnemy.getHeight())/2, null);
			break;
		case BOSS:
			g.drawImage(RotateImage.rotated(bossEnemy, e.direction), (int)e.xLoc + (32-bossEnemy.getWidth())/2, (int)e.yLoc + (32-bossEnemy.getHeight())/2, null);
			break;
		default:
			break;
		}
	}
	
	public void drawEnemy(Graphics g) {
		for(Enemy e: activeEnemyList) {
			if(e.alive && e.visible) {
				drawEnemy(e,g);
				drawHPEnemy(e, g);
			}
		}
	}
	
	public void drawHPEnemy(Enemy e, Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)e.xLoc + 6, (int)e.yLoc + 6, 20, 2);
		g.setColor(Color.GREEN);
		g.fillRect((int)e.xLoc + 6, (int)e.yLoc + 6,(int)((e.health/e.HEALTHYSTART)*20), 2);
	}
	
	private void drawProjectiles(Graphics g){
		for(Projectile p: projectileList){
			
			try {
				NormalProjectile = ImageIO.read(new File("E:\\GameTower\\Projectile3.png"));
				SniperProjectile = ImageIO.read(new File("E:\\GameTower\\Projectile1.png"));
				MachineGunProjectile = ImageIO.read(new File("E:\\GameTower\\Projectile2.png"));
				SuperProjectile = ImageIO.read(new File("E:\\GameTower\\Projectile2.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			switch(p.type){
			case NORMAL:
				g.drawImage(RotateImage.rotated(NormalProjectile, p.angleOfProjectileInDegrees()),(int)p.xLoc, (int)p.yLoc , null);
				if(sound) loadSound.playSound("E:\\GameTower\\Sound\\Sound1.wav",1);
				break;
			case SNIPERTOWER:
				g.drawImage(RotateImage.rotated(SniperProjectile, p.angleOfProjectileInDegrees()),(int)p.xLoc, (int)p.yLoc, null);
				if(sound) loadSound.playSound("E:\\GameTower\\Sound\\Sound2.wav",1);
				break;
			case MACHINEGUNTOWER:
				g.drawImage(RotateImage.rotated(MachineGunProjectile, p.angleOfProjectileInDegrees()),(int)p.xLoc, (int)p.yLoc, null);
				if(sound) loadSound.playSound("E:\\GameTower\\Sound\\Sound3.wav",1);
				break;
			case SUPERTOWER:
				g.drawImage(RotateImage.rotated(SuperProjectile, p.angleOfProjectileInDegrees()),(int)p.xLoc, (int)p.yLoc, null);
				if(sound) loadSound.playSound("E:\\GameTower\\Sound\\Sound4.wav",1);
			default:
				break;
			}
		}
	}
	
	public void targetEnemy(){
		for(Tower t : towerList){
			t.targetEnemy = null;
			for(Enemy e: activeEnemyList){
				if(e.alive && e.visible){
					//calculate distance
					double xDist= Math.abs(e.xLoc - t.xPos);
					double yDist= Math.abs(e.yLoc - t.yPos);
					double dist = Math.sqrt((xDist*xDist)+(yDist*yDist));
					if(dist < t.range){
						t.targetEnemy = e;
					}
				}

			}
		}
	}
	
	public void attackEnemy(){
		for(Tower t: towerList){
			if(t.targetEnemy != null && t.canAttack()){
				attackEnemy(t);
				t.lastAttackTime = System.currentTimeMillis();
			}
		}
	}

	public void attackEnemy(Tower t){
		pType projType = pType.NORMAL;
		Projectile projectile = new Projectile(t.xPos + (32-normalTower.getWidth())/2, t.yPos + (32-normalTower.getHeight())/2, (double)t.targetEnemy.xLoc + 5, (double)t.targetEnemy.yLoc + 5, t.power, t.targetEnemy, projType);
		switch(t.towerType){
		case NORMAL:
			projectile = new Projectile(t.xPos + (32-normalTower.getWidth())/2, t.yPos + (32-normalTower.getHeight())/2, (double)t.targetEnemy.xLoc + 5, (double)t.targetEnemy.yLoc + 5, t.power, t.targetEnemy, projType);
			break;
		case SNIPERTOWER:
			projType = pType.SNIPERTOWER;
			projectile = new Projectile(t.xPos + (32-sniperTower.getWidth())/2, t.yPos + (32-sniperTower.getHeight())/2, (double)t.targetEnemy.xLoc + 5, (double)t.targetEnemy.yLoc + 5, t.power, t.targetEnemy, projType);
			break;
		case MACHINEGUNTOWER:
			projType = pType.MACHINEGUNTOWER;
			projectile = new Projectile(t.xPos + (32-machineGunTower.getWidth())/2, t.yPos + (32-machineGunTower.getHeight())/2, (double)t.targetEnemy.xLoc + 5, (double)t.targetEnemy.yLoc + 5, t.power, t.targetEnemy, projType);
			break;
		case SUPERTOWER:
			projType = pType.SUPERTOWER;
			projectile = new Projectile(t.xPos + (32-superTower.getWidth())/2, t.yPos + (32-superTower.getHeight())/2, (double)t.targetEnemy.xLoc + 5, (double)t.targetEnemy.yLoc + 5, t.power, t.targetEnemy, projType);
			break;
		default:
			break;
		}
		projectileList.add(projectile);
	}
	
	
	//Xử lý chuột trong màn chơi
	public void Mouse(int x, int y, Graphics g) {
		try {
			SelectTower = ImageIO.read(new File("E:\\GameTower\\TileSelectGraphic.png"));
			NoCanAction = ImageIO.read(new File("E:\\GameTower\\NoCanPlaceTower.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(mouseOnMap(x, y) && canPlaceTowerHere(x, y)) {
			
			if(selectPlace) g.drawImage(SelectTower, x/32*32, y/32*32, null);
			
			Tower t = new SniperTower(x/32*32, y/32*32);
			if(GameStage.xClick >= 590 && GameStage.xClick <= 650 && GameStage.yClick >= 60 && GameStage.yClick <= 120) {
				if(Player.getPlayer().credits >= t.buyCost) {
					towerList.add(t);
					builtTower = true;
					selectPlace = false;
					lastTime = System.currentTimeMillis();
					Player.getPlayer().addCredits((-1)*t.buyCost);
					GameStage.xSelect = 0; GameStage.ySelect = 0;
					GameStage.xClick = 0; GameStage.yClick = 0;
				}
			}
			

			if(GameStage.xClick >= 506 && GameStage.xClick <= 568 && GameStage.yClick >= 68 && GameStage.yClick <= 148) {
				t = new NormalTower(x/32*32, y/32*32);
				if(Player.getPlayer().credits >= t.buyCost) {
					towerList.add(t);
					builtTower = true;
					selectPlace = false;
					lastTime = System.currentTimeMillis();
					Player.getPlayer().addCredits((-1)*t.buyCost);
					GameStage.xSelect = 0; GameStage.ySelect = 0;
					GameStage.xClick = 0; GameStage.yClick = 0;
				}
			}
			
			if(GameStage.xClick >= 506 && GameStage.xClick <= 568 && GameStage.yClick >= 142 && GameStage.yClick <= 200) {
				t = new MachineGunTower(x/32*32, y/32*32);
				if(Player.getPlayer().credits >= t.buyCost) {
					towerList.add(t);
					builtTower = true;
					selectPlace = false;
					lastTime = System.currentTimeMillis();
					Player.getPlayer().addCredits((-1)*t.buyCost);
					GameStage.xSelect = 0; GameStage.ySelect = 0;
					GameStage.xClick = 0; GameStage.yClick = 0;
				}
			}
			if(GameStage.xClick >= 590 && GameStage.xClick <= 650 && GameStage.yClick >= 140 && GameStage.yClick <= 200) {
				t = new SuperTower(x/32*32, y/32*32);
				if(Player.getPlayer().credits >= t.buyCost) {
					towerList.add(t);
					builtTower = true;
					selectPlace = false;
					lastTime = System.currentTimeMillis();
					Player.getPlayer().addCredits((-1)*t.buyCost);
					GameStage.xSelect = 0; GameStage.ySelect = 0;
					GameStage.xClick = 0; GameStage.yClick = 0;
				}
			}
		}
		
		selectPlace = true;
		if(GameStage.xClick >= 590 && GameStage.xClick <= 630 && GameStage.yClick >= 335 && GameStage.yClick <= 370 && !exit) {
			++count2;
			if(!waveIsInProgress && count2 == 1) {
				waveIsInProgress = true;
				projectileList = new ArrayList<Projectile>();
				reloadTowers();
				createEnemyListforLevel();
			}
			
			if(waveIsInProgress && count2%2 == 1 && count2 > 1) {
				waveIsInProgress = false;
				sound = false;
			}
			
			if(!waveIsInProgress && count2%2 == 0 && count2 > 1) {
				waveIsInProgress = true;
				sound = true;
			}
			
			GameStage.xSelect = 0; GameStage.ySelect = 0;
			GameStage.xClick = 0; GameStage.yClick = 0;	
		}
		
		if(GameStage.xClick >= 510 && GameStage.xClick <= 560 && GameStage.yClick >= 335 && GameStage.yClick <= 370 && waveIsInProgress && !exit) {
			++count;
			if(count%2 == 1) {
				for(Enemy e: activeEnemyList) {
					e.speed *= 2;
				}
				for(Enemy e: enemyList) {
					e.speed *= 2;
				}
				for(Tower t: towerList) {
					t.reloadTime /= 2;
				}
				for(Projectile p: projectileList) {
					p.speed *= 2;
				}
				X2Speed = true;
				System.out.println("X2 Speed: On");
			}
			else{
				for(Enemy e: activeEnemyList) {
					e.speed /= 2;
				}
				for(Enemy e: enemyList) {
					e.speed /= 2;
				}
				for(Tower t: towerList) {
					t.reloadTime *= 2;
				}
				for(Projectile p: projectileList) {
					p.speed /= 2;
				}
				X2Speed = false;
				System.out.println("X2 Speed: Off");
			}
			GameStage.xSelect = 0; GameStage.ySelect = 0;
			GameStage.xClick = 0; GameStage.yClick = 0;
		}
		if(mouseOnMap(x, y)) {
			if(GameStage.xClick >= 510 && GameStage.xClick <= 570 && GameStage.yClick >= 220 && GameStage.yClick <= 280){
				System.out.println("Plesea, Select a tower to update");
				Tower t = getNearestTower(x,y);
				if(t!=null && t.level < t.maxLevel && Player.getPlayer().credits >= t.upgradeCost) {
					System.out.println("Update tower !");
					t.upgrade();
					upgradeTower = true;
					lastTime = System.currentTimeMillis();
					}
				else{
					noUpgrade = true;
					lastTime = System.currentTimeMillis();
				}
			}
			if(GameStage.xClick >= 590 && GameStage.xClick <= 650 && GameStage.yClick >= 220 && GameStage.yClick <= 280){
				System.out.println("Plesea, Select a tower to refund");
				Tower t = getNearestTower(x,y);
				if(t!=null){
					System.out.println("Refund tower !");
					t.refundTower();			
					towerList.remove(t);
					refundTower = true;
					lastTime = System.currentTimeMillis();
				}
			}
			
			if(GameStage.xClick >= 155 && GameStage.xClick <= 210 && GameStage.yClick >= 390 && GameStage.yClick <= 440 && !accelerationTower) {
				System.out.println("AccelerationTower");
				Tower t = getNearestTower(x, y);
				if(t != null) {
					accelerationTower = true;
					t.reloadTime /= AccelerationTower.buffReload;
					t.power *= AccelerationTower.buffDamage;
					t.range *= AccelerationTower.buffRange;
					lastTime1 = System.currentTimeMillis();
					xTemp1 = x;
					yTemp1 = y;
					Player.getPlayer().addCredits((-1)*AccelerationTower.cost);
				}
			}
			
			if(GameStage.xClick >= 275 && GameStage.xClick <= 330 && GameStage.yClick >= 390 && GameStage.yClick <= 440 && !frozenItem) {
				System.out.println("Frozen");
			  if(Player.getPlayer().credits >= FrozenItem.cost) {
				frozenItem = true;
				xTemp2 = x;
				yTemp2 = y;
				Player.getPlayer().addCredits((-1)*FrozenItem.cost);
				for(Enemy e: activeEnemyList) {;
					if(Math.sqrt((e.xLoc - x)*(e.xLoc - x) + (e.yLoc - y)*(e.yLoc - y)) <= FrozenItem.range) {
						eFrozen.add(e);
						e.speed *= FrozenItem.frozen;
					}
				}
				lastTime2 = System.currentTimeMillis();
			  }
			}
			
			if(GameStage.xClick >= 395 && GameStage.xClick <= 450 && GameStage.yClick >= 390 && GameStage.yClick <= 440 && !boomItem) {
				
				if(Player.getPlayer().credits >= BoomItem.cost ) {
					xTemp3 = x;
					yTemp3 = y;
					Player.getPlayer().addCredits((-1)*BoomItem.cost);
					lastTime3 = System.currentTimeMillis();
					boomItem = true;
					loadSound.playSound("E:\\GameTower\\Sound\\Boom.wav", 2);
					for(Enemy e: activeEnemyList) {;
						if(Math.sqrt((e.xLoc - x)*(e.xLoc - x) + (e.yLoc - y)*(e.yLoc - y)) <= BoomItem.range) {
							e.takeDamage(BoomItem.damage);
							eBoom.add(e);
						}
					}
				}
			}
			GameStage.xSelect = 0; GameStage.ySelect = 0;
			GameStage.xClick = 0; GameStage.yClick = 0;
		}
		if(GameStage.xClick < 25 && GameStage.yClick < 25 && GameStage.xClick*GameStage.yClick > 0) {
			++count1; 
			if(sound) {
				sound = false;
			}
			else{
				sound = true;
			}
			GameStage.xClick = 0; GameStage.yClick = 0;
		}
		
		if(GameStage.xClick >= 520 && GameStage.xClick <= 600 && GameStage.yClick >= 460 && GameStage.yClick <= 510) {
			waveIsInProgress = false;
			exit = true;
			GameStage.xSelect = 0; GameStage.ySelect = 0;
			GameStage.xClick = 0; GameStage.yClick = 0;
		}
		
		if(GameStage.xClick >= 448 && GameStage.xClick <= 480 && GameStage.yClick >= 0 && GameStage.yClick <= 30) {
			infoScreen = true;
			GameStage.xSelect = 0; GameStage.ySelect = 0;
			GameStage.xClick = 0; GameStage.yClick = 0;
		}
		if(infoScreen) {
			info.paint(g);
			if(GameStage.xClick >= 630 && GameStage.xClick <= 660 && GameStage.yClick >= 0 && GameStage.yClick <= 30) infoScreen = false;
		}
		if(exit) {
			sound = false;
			count1 = 1;
			g.setColor(Color.WHITE);
			g.setFont(new Font("",Font.PLAIN,15));
			g.drawString("Bạn có muốn lưu màn chơi không?", 180, 200);
			g.setFont(new Font("",Font.BOLD,18));
			g.setColor(Color.RED);
			g.drawString("Save and Exit       Resume          Exit", 150, 250);
			if(GameStage.xClick >= 150 && GameStage.xClick <= 280 && GameStage.yClick >= 230 && GameStage.yClick <= 270) {
				saveGame();
				
				EditGame.CheckMenu = true;
				EditGame.start = false;
				exit = false;
				}
			if(GameStage.xClick >= 300 && GameStage.xClick <= 380 && GameStage.yClick >= 230 && GameStage.yClick <= 270) {
				waveIsInProgress = true;
				count2 = 2;
				if(activeEnemyList.size() == 0){
					--currentLevel;
				}
				exit = false;
				sound = true;
			}
			if(GameStage.xClick >= 410 && GameStage.xClick <= 470 && GameStage.yClick >= 230 && GameStage.yClick <= 270) {
				EditGame.CheckMenu = true;
				EditGame.start = false;
				levelSave = 0;
				HighScoreSave = HighScore;
				exit = false;
			}
			GameStage.xSelect = 0; GameStage.ySelect = 0;
			GameStage.xClick = 0; GameStage.yClick = 0;
			
		}
		
		if(upgradeTower) {
			g.setColor(Color.BLUE);
			g.drawString("Tháp đã được nâng cấp", 240, 370);
			if(System.currentTimeMillis() - lastTime >= 2000) upgradeTower = false;
		}
		if(refundTower) {
			g.setColor(Color.BLUE);
			g.drawString("Tháp đã được bán", 240, 370);
			if(System.currentTimeMillis() - lastTime >= 2000) refundTower = false;
		}
		if(builtTower) {
			g.setColor(Color.BLUE);
			g.drawString("Tháp đã được xây dựng...", 240, 370);
			if(System.currentTimeMillis() - lastTime >= 2000) builtTower = false;
		}
		if(noUpgrade) {
			g.setColor(Color.RED);
			g.drawString("Không thể nâng cấp", 240, 370);
			if(System.currentTimeMillis() - lastTime >= 2000) noUpgrade = false;
		}
		if(accelerationTower && System.currentTimeMillis() - lastTime1 > AccelerationTower.time ) {
			accelerationTower = false;
			Tower t = getNearestTower((int)xTemp1,(int)yTemp1);
			t.reloadTime *= AccelerationTower.buffReload;
			t.power /= AccelerationTower.buffDamage;
			t.range /= AccelerationTower.buffRange;
		}
		if(accelerationTower && System.currentTimeMillis() - lastTime1 <= AccelerationTower.time ) {
			g.setColor(Color.RED);
			int a = ((int)xTemp1)/32*32;
			int b = ((int)yTemp1)/32*32;
			g.drawLine(a,b,a+32,b); g.drawLine(a, b, a, b+32);
			g.drawLine(a, b+32, a+32, b+32); g.drawLine(a+32, b, a+32, b+32);
			g.drawImage(NoCanAction, 160, 388, null);
			
		}
		if(frozenItem && System.currentTimeMillis() - lastTime2 > FrozenItem.time) {
			
			for(Enemy e: eFrozen) {
				e.speed /= FrozenItem.frozen;
				eFrozen.remove(e);
			}
			frozenItem = false;
		}
		
		if(frozenItem && System.currentTimeMillis() - lastTime2 <= FrozenItem.time) {
			try {
				FrozenGraphic = ImageIO.read(new File("E:\\GameTower\\RangeGraphic.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(System.currentTimeMillis() - lastTime2 < 200) g.drawImage(FrozenGraphic,(int)xTemp2 - 51, (int)yTemp2 - 51, null);
			g.drawImage(NoCanAction, 270, 385, null);
		}
		if(boomItem && System.currentTimeMillis() - lastTime3 > BoomItem.time) {
			for(Enemy e: eBoom) {
				eFrozen.remove(e);
			}
		}
		
		if(boomItem && System.currentTimeMillis() - lastTime3 > 5000) {
			boomItem = false;
		}
		if(boomItem && System.currentTimeMillis() - lastTime3 <= 5000) {
			g.drawImage(NoCanAction, 390, 388, null);
		}
		
		if(boomItem && System.currentTimeMillis() - lastTime3 <= 500) {
			try {
				if(System.currentTimeMillis() - lastTime3 <= 150)
					BoomGraphic = ImageIO.read(new File("E:\\GameTower\\BoomGraphic2.png"));
				else if(System.currentTimeMillis() - lastTime3 <= 300) {
					BoomGraphic = ImageIO.read(new File("E:\\GameTower\\BoomGraphic1.png"));
				}
				else BoomGraphic = ImageIO.read(new File("E:\\GameTower\\BoomGraphic.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			g.drawImage(BoomGraphic,(int)xTemp3/32*32 + (32-BoomGraphic.getWidth())/2, (int)yTemp3/32*32 + (32-BoomGraphic.getHeight())/2, null);
		}
	}
	//xác định tháp gần nhất
	public Tower getNearestTower(int x, int y){
		Tower nearestTower=null;
		for(Tower t: towerList){
			if(t.xPos == x/32*32 && t.yPos == y/32*32){
				nearestTower = t;
			}

		}
		return nearestTower;
	}
	
	public void reloadTowers(){
		for(Tower t : towerList){
			t.lastAttackTime = 0;
		}
	}
	
	// tạo những đợt quân địch mới
	public void createEnemyListforLevel(){
		generator = new EnemyGenerator(currentLevel);
		generator.createEnemyQueue();
		generator.RandomizeCritterQueue();
		enemyList = generator.getEnemyQueue();
		activeEnemyList = new LinkedList<Enemy>();
		activeEnemyList.add(enemyList.poll());
	}
	public void addEnemyToActiveEnemyList(){
		tickCount++;
		if(tickCount>enemySpawnDelay){
			activeEnemyList.add(enemyList.poll());
			tickCount=0;	
		}
	}
	//cập nhật trạng thái địch
	public void updateEnemy(){
		boolean enemyAreStillVisible= false;
		ArrayList<Enemy> enemyToRemove = new ArrayList<Enemy>();
		
		for(Enemy s : activeEnemyList){
			
			//di chuyển địch theo đường
			if(s.alive)
				s.RoadEnemy();
			else{
				Player.getPlayer().addCredits((int)s.reward);
				Player.getPlayer().addScore(s.score);
				if(Player.getPlayer().score >= HighScore) {
					HighScore =(int) Player.getPlayer().score;
				}
				System.out.println(HighScore);
				enemyToRemove.add(s);
			}
			if(s.visible) {
				enemyAreStillVisible=true;
			}
			else {
				Player.getPlayer().decreaseHP(s.damageToPlayer);
				enemyToRemove.add(s);
				if(sound) loadSound.playSound("E:\\GameTower\\Sound\\EnemyToEnd.wav",1);
			}
		}
		
		//xóa enemy đã tiêu diệt or quá vị trí đích
			for(Enemy s : enemyToRemove){
				activeEnemyList.remove(s);
			}

			if(!enemyAreStillVisible && enemyList.size() == 0){
				waveIsInProgress = false;
				currentLevel++;
				projectileList = new ArrayList<Projectile>();
				count2 = 0;
				if(X2Speed) {
					X2Speed = false;
					count++;
					for(Enemy e: activeEnemyList) {
						e.speed /= 2;
					}
					for(Enemy e: enemyList) {
						e.speed /= 2;
						}
					for(Tower t: towerList) {
						t.reloadTime *= 2;
					}
					for(Projectile p: projectileList) {
						p.speed /= 2;
					}
					X2Speed = false;
					System.out.println("X2 Speed: Off");
				}
			}
	}
	//cập nhật trạng thái đạn
	private void updateProjectiles(){
		
		//update đường đạn bay
		pToRemove = new ArrayList<Projectile>();
		for(Projectile p: projectileList){
			if(!p.arrivedAtTarget)
				p.move();
			else
				pToRemove.add(p);
		}

		for(Projectile p: pToRemove){
			projectileList.remove(p);
		}
	}
	
	// kiểm tra chuột click trên map ?
	public boolean mouseOnMap(int x, int y){
		if(x <= 480 && y <= 384 && x*y > 0){
			System.out.println("Mouse On Map !");
			return true;
		}
		else
			return false;
	}
	
	// kiểm tra vị trí mua tháp?
	public boolean canPlaceTowerHere(int x, int y) {
		
		// kiểm tra chỗ này có thể xây tháp hay không?
		if (EditMap.arr[y/32][x/32] != 0){
			System.out.println("đây là đường đi hoặc vật cản");
			return false;
		}
		
		//kiểm tra đã có tháp ở đây chưa?
		for(Tower t: towerList){
			if(t.xPos/32 == x/32 && t.yPos/32 == y/32){
				System.out.println("Đã có tháp xây ở vị trí này");
				return false;
			}
		}
		System.out.println("Can Place Tower Here");
		return true;
	}
	
	public void restartGame() {
		currentLevel = 1;
		Player.getPlayer().reset();
		waveIsInProgress = false;
		enemyList = new LinkedList<Enemy>();
		activeEnemyList = new LinkedList<Enemy>();
		towerList =  new ArrayList<Tower>();
		projectileList = new ArrayList<Projectile>();
		gameOver=false;
		count2 = 0;
	}
	
	public void saveGame() {
		levelSave = currentLevel;
		enemyListSave = enemyList;
		activeEnemySave = activeEnemyList;
		towerListSave = towerList;
		HPSave = Player.getPlayer().HP;
		creditsSave = Player.getPlayer().credits;
		ScoreSave = (int) Player.getPlayer().score;
		
	}
	public void resumeGame() {
		if(levelSave == 0) {
			restartGame();
			count2 = 0;
		}
		else {
			currentLevel = levelSave;
			enemyList = enemyListSave;
			activeEnemyList = activeEnemySave;
			towerList = towerListSave;
			Player.getPlayer().HP = HPSave;
			Player.getPlayer().credits = creditsSave;
			Player.getPlayer().score = ScoreSave;
			gameOver = false;
		}
		
	}
	
}
