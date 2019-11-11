package demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.imageio.ImageIO;
import gameEntity.Enemy;
import gameEntity.EnemyGenerator;
import gameTile.MachineGunTower;
import gameTile.NormalTower;
import gameTile.Projectile;
import gameTile.Projectile.pType;
import gameTile.SniperTower;
import gameTile.SuperTower;
import gameTile.Tower;

public class Play {
	public static ArrayList<Tower>  towerList = new ArrayList<Tower>();
	public static Queue<Enemy> enemyList = new LinkedList<Enemy>();
	public static Queue<Enemy> activeEnemyList = new LinkedList<Enemy>();
	public static ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
	Queue<Enemy> enemyListSave, enemyActiveSave;
	ArrayList<Tower> towerListSave;
	ArrayList<Projectile> projectileSave;
	ArrayList<Projectile> pToRemove;
	BufferedImage normalTower, sniperTower, machineGunTower,superTower , normalEnemy, tankerEnemy, smallEnemy, bossEnemy, NormalProjectile, SniperProjectile, MachineGunProjectile, SuperProjectile, SelectTower;
	public static int currentLevel, tickCount, enemySpawnDelay = 20;
	public EnemyGenerator generator;
	public static boolean waveIsInProgress = true, gameOver = false, sound = true, upgradeTower = false, noUpgrade = false, builtTower = false, refundTower = false, selectPlace = true, X2Speed = false, clickPause = false;
	public double lastTime, count = 0;
	public static int count1 = 0;
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
		addSelectTowerToTowerList(GameStage.xSelect, GameStage.ySelect, g);
		if(Player.getPlayer().HP <=0){
			gameOver = true;
			}
	}
	
	public void render(Graphics g){
		
		drawTower(g);
		
		if(waveIsInProgress){
			drawEnemy(g);
			drawProjectiles(g);
		}
		if(gameOver) System.out.println("Game Over");
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
			}
		}
	}
	
	private void drawProjectiles(Graphics g){
		for(Projectile p: projectileList){
			
			try {
				NormalProjectile = ImageIO.read(new File("E:\\GameTower\\Projectile3.png"));
				SniperProjectile = ImageIO.read(new File("E:\\GameTower\\Projectile2.png"));
				MachineGunProjectile = ImageIO.read(new File("E:\\GameTower\\Projectile3.png"));
				SuperProjectile = ImageIO.read(new File("E:\\GameTower\\Projectile2.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			switch(p.type){
			case NORMAL:
				g.drawImage(RotateImage.rotated(NormalProjectile, p.angleOfProjectileInDegrees()),(int)p.xLoc, (int)p.yLoc, null);
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
		switch(t.towerType){
		case NORMAL:
			break;
		case SNIPERTOWER:
			projType = pType.SNIPERTOWER;
			break;
		case MACHINEGUNTOWER:
			projType = pType.MACHINEGUNTOWER;
			break;
		case SUPERTOWER:
			projType = pType.SUPERTOWER;
			break;
		default:
			break;
		}
		Projectile projectile = new Projectile(t.xPos, t.yPos, (double)t.targetEnemy.xLoc, (double)t.targetEnemy.yLoc, t.power, t.targetEnemy, projType);
		projectileList.add(projectile);
	}
	
	public void addSelectTowerToTowerList(int x, int y, Graphics g) {
		try {
			SelectTower = ImageIO.read(new File("E:\\GameTower\\TileSelectGraphic.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(mouseOnMap(x, y) && canPlaceTowerHere(x, y)) {
			if(selectPlace) g.drawImage(SelectTower, x/32*32, y/32*32, null);
			
			Tower t = new SniperTower(x, y);
			if(GameStage.xClick >= 590 && GameStage.xClick <= 650 && GameStage.yClick >= 68 && GameStage.yClick <= 148) {
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
				t = new NormalTower(x, y);
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
			
			if(GameStage.xClick >= 506 && GameStage.xClick <= 568 && GameStage.yClick >= 165 && GameStage.yClick <= 225) {
				t = new MachineGunTower(x, y);
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
			if(GameStage.xClick >= 590 && GameStage.xClick <= 650 && GameStage.yClick >= 165 && GameStage.yClick <= 225) {
				t = new SuperTower(x, y);
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
		if(GameStage.xClick >= 590 && GameStage.xClick <= 630 && GameStage.yClick >= 335 && GameStage.yClick <= 370) {
			if(!waveIsInProgress) {
				waveIsInProgress = true;
				projectileList = new ArrayList<Projectile>();
				reloadTowers();
				createEnemyListforLevel();
			}	
			else waveIsInProgress = false;
			GameStage.xSelect = 0; GameStage.ySelect = 0;
			GameStage.xClick = 0; GameStage.yClick = 0;	
		}
		
		if(GameStage.xClick >= 510 && GameStage.xClick <= 560 && GameStage.yClick >= 335 && GameStage.yClick <= 370 && waveIsInProgress) {
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
		
		if(upgradeTower) {
			g.setColor(Color.WHITE);
			g.drawString("Tháp đã được nâng cấp", 240, 410);
			if(System.currentTimeMillis() - lastTime >= 1000) upgradeTower = false;
		}
		if(refundTower) {
			g.setColor(Color.WHITE);
			g.drawString("Tháp đã được bán", 240, 410);
			if(System.currentTimeMillis() - lastTime >= 1000) refundTower = false;
		}
		if(builtTower) {
			g.setColor(Color.WHITE);
			g.drawString("Tháp đã được mua", 240, 410);
			if(System.currentTimeMillis() - lastTime >= 1000) builtTower = false;
		}
		if(noUpgrade) {
			g.setColor(Color.WHITE);
			g.drawString("Không thể nâng cấp", 240, 410);
			if(System.currentTimeMillis() - lastTime >= 1000) noUpgrade = false;
		}
		
	}
	
	public Tower getNearestTower(int x, int y){
		double distanceApproximation = 32;
		Tower nearestTower=null;
		for(Tower t: towerList){
			if(Math.abs(t.xPos-x)+ Math.abs(t.yPos-y) < distanceApproximation){
				nearestTower = t;
				distanceApproximation = Math.abs(t.xPos-x)+Math.abs(t.xPos-y);
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
	
	public void updateEnemy(){
		boolean enemyAreStillVisible= false;
		ArrayList<Enemy> enemyToRemove = new ArrayList<Enemy>();
		
		for(Enemy s : activeEnemyList){
			
			//di chuyển địch theo đường
			if(s.alive)
				s.RoadEnemy();
			else{
				Player.getPlayer().addCredits((int)s.reward);
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


			if(!enemyAreStillVisible && enemyList.size()==0){
				waveIsInProgress = false;
				currentLevel++;
				X2Speed = false;
				++count;
			}
	}
	
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
		if(x <= 480 && y <= 384 && x*y != 0){
			System.out.println("Mouse On Map !");
			return true;
		}
		else
			return false;
	}
	
	// kiểm tra vị trí mua tháp?
	public boolean canPlaceTowerHere(int x, int y) {
		
		// kiểm tra chỗ này có thể xây tháp hay không?
		if (EditMap.arr[x/32][y/32] != 0){
			return false;
		}
		
		//kiểm tra đã có tháp ở đâu chưa?
		for(Tower t: towerList){
			if(t.xPos/32 == x/32 && y/32 == t.yPos/32){
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
		gameOver=false;
		
	}
	
}
