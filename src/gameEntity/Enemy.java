package gameEntity;

public abstract class Enemy {
	public enum type{NORMAL, TANKER, SMALL, BOSS};
	public String name;
	public double health;
	public double speed;
	public double armor;
	public double reward;
	public double xLoc = 32, yLoc = 352;
	public type enemyType;
	public double direction = 0;
	public int damageToPlayer;
	public boolean alive = true, visible = true;
	
	public Enemy(String name, double health, double speed, double armor, double reward, int damageToPlayer, type enemyType) {
		this.name = name;
		this.health = health;
		this.speed = speed;
		this.armor = armor;
		this.reward = reward;
		this.damageToPlayer = damageToPlayer;
		this.enemyType = enemyType;
	}
	
	public void RoadEnemy() {
		if(yLoc > 288) {
			yLoc -= speed;
			if(yLoc < 288) yLoc = 288;
		}
		else if(yLoc == 288 && xLoc < 384) {
			xLoc += speed;
			if(xLoc > 384) xLoc = 384;
			direction = 90;
		}
		else if(xLoc == 384 && yLoc > 224) {
			yLoc -= speed;
			if(yLoc < 224) yLoc = 224;
			direction = 0;
		}
		else if(yLoc == 224 && xLoc > 128) {
			xLoc -= speed;
			if(xLoc < 128 ) xLoc = 128;
			direction = -90;
		}
		else if(xLoc == 128 && yLoc > 32) {
			yLoc -= speed;
			if(yLoc < 32) yLoc = 32;
			direction = 0;
		}
		else if(yLoc == 32 && xLoc < 446) {
			xLoc += speed;
			direction = 90;
		}
		else {
			visible = false;
		}
	}
	
	public void takeDamage(double damage) {
		health -= damage/armor;
		if(health <= 0) {
			 alive = false;
		}
	}
	
}
