package gameTile;

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
	private static int newRefundValue = 200;
	private static int newUpgradeCost = 150;
	private static int newReloadTime = 4;
	private static double newRange = 150;
	private static int newPower = 10;
}
