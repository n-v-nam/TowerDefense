package gameTile;

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
	private static int newRefundValue = 40;
	private static int newUpgradeCost = 50;
	private static double newReloadTime = 1;
	private static double newRange = 100;
	private static double newPower = 5;
}
