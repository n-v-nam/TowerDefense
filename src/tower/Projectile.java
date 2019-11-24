package tower;

import static tower.Projectile.pType.SNIPERTOWER;

import enemy.Enemy;

public class Projectile {
	public enum pType{
		NORMAL, SNIPERTOWER, MACHINEGUNTOWER, SUPERTOWER
	}
	public double xLoc;
	public double yLoc;
	public double xDest;
	public double yDest;
	public double xInit;
	public double yInit;
	public double power;
	public double speed = 15;
	public pType type;
	public Enemy targetEnemy;
	public boolean arrivedAtTarget = false;
	
	
	public Projectile(double xInit, double yInit, double xDest, double yDest, double power, Enemy targetEnemy, pType type){
		

		this.xInit = xInit;		
		this.xDest = xDest;
		this.yInit= yInit;	
		this.yDest = yDest;
		this.power = power;
		this.type = type;
		if(type == SNIPERTOWER || type == pType.SUPERTOWER) speed = 5;
		xLoc = xInit  + 12*Math.cos(Math.atan2(yDest- yInit, xDest-xInit));
		yLoc = yInit  + 12*Math.sin(Math.atan2(yDest- yInit, xDest-xInit));
		arrivedAtTarget = false;
		this.targetEnemy = targetEnemy;
	}
	
	public double angleOfProjectileInDegrees(){
		return (180/Math.PI)*Math.atan2(yDest- yLoc, xDest-xLoc);
	}

	public double angleOfProjectileInRadians(){
		return Math.atan2(yDest-yLoc, xDest-xLoc);
	}

	public void move(){
		
		//đạn đã đến vị trí địch
		if (Math.abs(xLoc - xDest) < speed/2 || Math.abs(yLoc - yDest) < speed/2){
			xLoc = targetEnemy.xLoc + 10;
			yLoc = targetEnemy.yLoc + 10;
			arrivedAtTarget = true;
			targetEnemy.takeDamage(power);
			if(type == pType.SUPERTOWER) {
				targetEnemy.speed *= 0.675;
				if(targetEnemy.speed < 0.5) targetEnemy.speed = 0.5;
			}
			if(type == pType.MACHINEGUNTOWER) {
				targetEnemy.speed *= 0.9;
				if(targetEnemy.speed < 0.5) targetEnemy.speed = 0.5;
			}
		}
		else{
			xLoc += speed*Math.cos(angleOfProjectileInRadians());
			yLoc += speed*Math.sin(angleOfProjectileInRadians());
			
		}
	}
}
