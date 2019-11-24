package tower;

public class SniperTower extends Tower {
	public SniperTower(double x, double y){
		super(x,y);
		this.buyCost = newBuyCost;
		this.refundValue = newRefundValue;
		this.upgradeCost = newUpgradeCost;
		this.reloadTime = newReloadTime;
		this.range = newRange;
		this.power = newPower;
		this.towerType = type.SNIPERTOWER;
	}
	
	public static int newBuyCost = 150;
	public static int newRefundValue = 200;
	public static int newUpgradeCost = 150;
	public static int newReloadTime = 2;
	public static double newRange = 100;
	public static int newPower = 10;
}
