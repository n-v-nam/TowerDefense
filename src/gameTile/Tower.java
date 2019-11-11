package gameTile;


import demo.Player;
import gameEntity.Enemy;

public abstract class Tower {
	 public enum type{NORMAL, SNIPERTOWER, MACHINEGUNTOWER, SUPERTOWER};
	
	 public double range;
	 public double power;
	 public double reloadTime;
	 public int refundValue;
	 public double buyCost;
	 public double upgradeCost;
	 public double xPos, yPos;
	 public double angleOfRotation;
	 public double lastAttackTime;
	 public Enemy targetEnemy;
	 public type towerType;
	 public int level =1, maxLevel = 3;
	 
	 public Tower(double xPos, double yPos ) {
		 this.xPos = xPos;
		 this.yPos = yPos;
		 this.lastAttackTime = 0;
		 angleOfRotation = 0;
		 lastAttackTime = 0;
	 }
	 // điều kiện có thể tấn công địch
	 public boolean canAttack() {
		 if( (System.currentTimeMillis()-lastAttackTime)/1000.0 >= reloadTime){

				return true;
			}
			else
				return false;
	 }
	 // bán tháp
	 public void refundTower() {
		 Player.getPlayer().addCredits(refundValue);
	 }
	 // tìm góc xoay tháp ( đơn vị độ)
	 public double getRotationAngleInDegrees(){
			if(targetEnemy != null)
				angleOfRotation = (180/Math.PI)*Math.atan2(targetEnemy.yLoc-yPos, targetEnemy.xLoc-xPos);
			return angleOfRotation;
	}
	 public void upgrade() {
			level++;
			Player.getPlayer().addCredits(-1*upgradeCost);
			power *= 1.25;
			reloadTime /= 1.25 ;	
			range *= 1.25;
			refundValue += 50;
			upgradeCost += 50;
			System.out.println(towerType +"Tower was upgrade !");
	 }
}
