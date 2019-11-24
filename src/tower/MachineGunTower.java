package tower;

public class MachineGunTower extends Tower{
	public MachineGunTower(double x, double y){
		super(x,y);
		this.buyCost =newBuyCost;
		this.refundValue = newRefundValue;
		this.upgradeCost = newUpgradeCost;
		this.reloadTime = newReloadTime;
		this.range = newRange;
		this.power = newPower;
		this.towerType = type.MACHINEGUNTOWER;
		
	}
	
	public static int newBuyCost = 100;
	public static int newRefundValue = 120;
	public static int newUpgradeCost = 150;
	public static double newReloadTime = 1;
	public static double newRange = 50;
	public static int newPower = 4;
}
