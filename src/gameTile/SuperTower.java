package gameTile;

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
	private static int newRefundValue = 150;
	private static int newUpgradeCost = 250;
	private static double newReloadTime = 2;
	private static double newRange = 150;
	private static double newPower = 10;
}
