package editGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import enemy.BossEnemy;
import enemy.NormalEnemy;
import enemy.SmallEnemy;
import enemy.TankerEnemy;
import item.BoomItem;
import tower.MachineGunTower;
import tower.NormalTower;
import tower.SniperTower;
import tower.SuperTower;

public class InfoScreen {
	
	BufferedImage bg, tower1, tower2, tower3, tower4, enemy1, enemy2, enemy3, enemy4, item1, item2, item3, icon;
	
	public void paint(Graphics g) {
		try {
			bg = ImageIO.read(new File("E:\\GameTower\\BG2.jpg"));
			tower1 = ImageIO.read(new File("E:\\GameTower\\Tower1.png"));
			tower2 = ImageIO.read(new File("E:\\GameTower\\Tower2.png"));
			tower3 = ImageIO.read(new File("E:\\GameTower\\Tower3.png"));
			tower4 = ImageIO.read(new File("E:\\GameTower\\Tower4.png"));
			enemy1 = ImageIO.read(new File("E:\\GameTower\\NormalEnemy.png"));
			enemy2 = ImageIO.read(new File("E:\\GameTower\\SmallEnemy.png"));
			enemy3= ImageIO.read(new File("E:\\GameTower\\TankerEnemy.png"));
			enemy4 = ImageIO.read(new File("E:\\GameTower\\BossEnemy.png"));
			item1 = ImageIO.read(new File("E:\\GameTower\\Acceleration.png"));
			item2 = ImageIO.read(new File("E:\\GameTower\\FrozenItem.png"));
			item3 = ImageIO.read(new File("E:\\GameTower\\BoomItem.png"));
			icon = ImageIO.read(new File("E:\\GameTower\\Icon3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(bg, 0, 0, null);
		g.drawImage(icon, 630, 0, null);
		g.drawImage(tower1, 30, 30, null);
		g.drawImage(tower2, 30, 57, null);
		g.drawImage(tower3, 30, 90, null);
		g.drawImage(tower4, 30, 115, null);
		g.drawImage(enemy1, 30, 170, null);
		g.drawImage(enemy2, 30, 200, null);
		g.drawImage(enemy3, 29, 230, null);
		g.drawImage(enemy4, 27, 260, null);
		g.drawImage(item1, 10, 300, null);
		g.drawImage(item2, 10, 360, null);
		g.drawImage(item3, 10, 420, null);
		g.setFont(new Font("Alo", Font.BOLD, 15));
		g.drawString("ENEMY", 30, 160);
		g.drawString("TOWER", 30, 20);
		
		g.setColor(Color.BLUE);
		g.drawString("POWER", 130, 20); 
		g.drawString("RELOAD", 230, 20);
		g.drawString("RANGE", 330, 20);
		g.drawString("SLOW", 430, 20);
		g.drawString("HEALTHY", 130, 160); 
		g.drawString("ARMOR", 230, 160);
		g.drawString("SPEED", 330, 160);
		g.drawString("REWARD", 430, 160);
		g.drawString("D.T.Player", 530, 160);
		g.setFont(new Font("Alo", Font.LAYOUT_NO_LIMIT_CONTEXT, 13));
		g.setColor(Color.BLACK);
		g.drawString(NormalTower.newPower +"                      "+NormalTower.newReloadTime+"                      "+(int)NormalTower.newRange+"                    NO", 140, 40);
		g.drawString(SniperTower.newPower +"                      "+SniperTower.newReloadTime+"                      "+(int)SniperTower.newRange+"                   NO", 140, 70);
		g.drawString(MachineGunTower.newPower +"                      "+MachineGunTower.newReloadTime+"                      "+(int)MachineGunTower.newRange+"                  YES", 140, 100);
		g.drawString(SuperTower.newPower +"                      "+SuperTower.newReloadTime+"                    "+(int)SuperTower.newRange+"                 YES", 140, 130);
		g.drawString(NormalEnemy.HEALTHYSTART +"                      "+NormalEnemy.armor+"                      "+NormalEnemy.speed+"                    "+(int)NormalEnemy.reward + "                         "+NormalEnemy.damageToPlayer , 140, 185);
		g.drawString(SmallEnemy.HEALTHYSTART +"                      "+SmallEnemy.armor+"                      "+ SmallEnemy.speed+"                    "+(int)SmallEnemy.reward + "                         "+SmallEnemy.damageToPlayer , 140, 215);
		g.drawString(TankerEnemy.HEALTHYSTART +"                      "+TankerEnemy.armor+"                      "+TankerEnemy.speed+"                    "+(int)TankerEnemy.reward + "                         "+TankerEnemy.damageToPlayer , 140, 245);
		g.drawString(BossEnemy.HEALTHYSTART +"                      "+BossEnemy.armor+"                      "+BossEnemy.speed+"                    "+(int)BossEnemy.reward + "                         "+BossEnemy.damageToPlayer , 140, 275);
		g.setColor(Color.BLUE);
		g.setFont(new Font("Alo", Font.ROMAN_BASELINE, 13));
		g.drawString(": Tăng tốc độ bắn của tháp lên 2 lần, phạm vi lên 1.5 lần và sát thương lên 2 lần trong 5s", 70, 340);
		g.drawString(": Làm chậm quân địch trong vùng chỉ định đi 2 lần trong 5s", 80, 400);
		g.drawString(": Gây sát thương " +BoomItem.damage+ " cho quân địch trong phạm vi " + BoomItem.range, 80, 460);
	}
}
