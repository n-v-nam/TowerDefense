package gameTile;

import static gameTile.Projectile.pType.SNIPERTOWER;
import gameEntity.Enemy;

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
	public double speed = 20;
	public pType type;
	public Enemy targetEnemy;
	public boolean arrivedAtTarget = false;
	
	public Projectile(double xInit, double yInit, double xDest, double yDest, double power, Enemy targetEnemy, pType type){
		

		this.xInit = xInit;		
		this.xDest = xDest;
		this.yInit= yInit;	
		this.yDest = yDest;
		this.power = power;
		xLoc = xInit + 12*Math.cos(angleOfProjectileInRadians());
		yLoc = yInit + 12*Math.sin(angleOfProjectileInRadians());
		arrivedAtTarget = false;
		this.targetEnemy = targetEnemy;
		this.type = type;
		if(type == SNIPERTOWER || type == pType.SUPERTOWER) speed = 30;
	}
	
	public double angleOfProjectileInDegrees(){
		return (180/Math.PI)*Math.atan2(yDest-yInit, xDest-xInit);
	}

	public double angleOfProjectileInRadians(){
		return Math.atan2(yDest-yInit, xDest-xInit);
	}

	public void move(){
		
		//projectile has hit
		if (Math.abs(xLoc - xDest) < speed/3 || Math.abs(yLoc - yDest)< speed/3){
			arrivedAtTarget = true;
			targetEnemy.takeDamage(power);
			if(type == pType.SUPERTOWER) {
				targetEnemy.speed /= 2;
			}
		}
		else{
			xLoc += speed*Math.cos(angleOfProjectileInRadians());
			yLoc += speed*Math.sin(angleOfProjectileInRadians());
		}
	}
}
