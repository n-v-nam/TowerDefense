 package enemy;

public abstract class Enemy {
	public enum type{NORMAL, TANKER, SMALL, BOSS};
	public int HEALTHYSTART;
	public String name;
	public double health;
	public double speed;
	public double armor;
	public double reward, score;
	public double xLoc = 448, yLoc = 320;
	public type enemyType;
	public double direction = -90;
	public int damageToPlayer;
	public boolean alive = true, visible = true;
	
	public Enemy(String name, double health, int HEALTHYSTART, double speed, double armor, double reward, double score, int damageToPlayer, type enemyType) {
		this.name = name;
		this.health = health;
		this.speed = speed;
		this.armor = armor;
		this.reward = reward;
		this.score = score;
		this.damageToPlayer = damageToPlayer;
		this.enemyType = enemyType;
		this.HEALTHYSTART = HEALTHYSTART ;
	}
	
	public void RoadEnemy() {
		if(xLoc > 352) {
			xLoc -= speed;
			if(xLoc < 352) xLoc = 352;
		}
		else if(xLoc == 352 && yLoc > 256) {
			yLoc -= speed;
			if(yLoc < 256) yLoc = 256;
			direction = 0;
		}
		else if(yLoc == 256 && xLoc > 256) {
			xLoc -= speed;
			if(xLoc < 256) xLoc = 256;
			direction = -90;
		}
		else if(xLoc == 256 && yLoc < 320 && yLoc > 192) {
			yLoc += speed;
			if(yLoc > 320 ) yLoc = 320;
			direction = 180;
		}
		else if(yLoc == 320 && xLoc > 160) {
			xLoc -= speed;
			if(xLoc < 160) xLoc = 160;
			direction = -90;
		}
		else if(xLoc == 160 && yLoc > 192) {
			yLoc -= speed;
			if(yLoc < 192) yLoc = 192;
			direction = 0;
		}
		else if(yLoc == 192 && xLoc < 256) {
			xLoc += speed;
			if(xLoc > 256) xLoc = 256;
			direction = 90;
		}
		else if(xLoc == 256 && yLoc > 32 && yLoc <= 192) {
			yLoc -= speed;
			if(yLoc < 32) yLoc = 32;
			direction = 0;
		}
		else if(yLoc == 32 && xLoc > 160){
			xLoc -= speed;
			if(xLoc < 160) xLoc = 160;
			direction = -90;
		}
		else if( xLoc == 160 && yLoc < 96) {
			yLoc += speed;
			if(yLoc > 96) yLoc = 96;
			direction = 180;
		}
		else if(yLoc == 96 && xLoc > 64) {
			xLoc -= speed;
			if(xLoc < 64) xLoc = 64;
			direction = -90;
		}
		else if(xLoc == 64 && yLoc > 32) {
			yLoc -= speed;
			if(yLoc < 32) yLoc = 32;
			direction = 0;
		}
		else if(yLoc == 32 && xLoc > 0) {
			xLoc -= speed;
			if(xLoc < 0) xLoc = 0;
			direction = -90;
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
