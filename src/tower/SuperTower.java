package tower;

public class SuperTower extends Tower {

	public SuperTower(double xPos, double yPos) {
		super(xPos, yPos);
		this.buyCost = newBuyCost;
		this.upgradeCost = newUpgradeCost;
		this.refundValue = newRefundValue;
		this.reloadTime = newReloadTime;
		this.range = newRange;
		this.power = newPower;
		this.towerType = type.SUPERTOWER;
	}
	public static int newBuyCost = 300;
	public static int newRefundValue = 150;
	public static int newUpgradeCost = 250;
	public static double newReloadTime = 2;
	public static double newRange = 100;
	public static int newPower = 12;
}
