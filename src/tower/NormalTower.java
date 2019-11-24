package tower;

public class NormalTower extends Tower {

	public NormalTower(double xPos, double yPos) {
		super(xPos, yPos);
		this.buyCost = newBuyCost;
		this.upgradeCost = newUpgradeCost;
		this.refundValue = newRefundValue;
		this.reloadTime = newReloadTime;
		this.range = newRange;
		this.power = newPower;
		this.towerType = type.NORMAL;
	}
	public static int newBuyCost = 50;
	public static int newRefundValue = 40;
	public static int newUpgradeCost = 50;
	public static double newReloadTime = 1.5;
	public static double newRange = 70;
	public static int newPower = 6;
}
