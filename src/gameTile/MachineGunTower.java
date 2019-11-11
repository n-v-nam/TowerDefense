package gameTile;

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
	private static int newRefundValue = 120;
	private static int newUpgradeCost = 150;
	private static double newReloadTime = 0.5;
	private static double newRange = 80;
	private static int newPower = 4;
}
