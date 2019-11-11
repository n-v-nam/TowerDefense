package gameEntity;

public class SmallEnemy extends Enemy {
	public SmallEnemy() {
		super(name, health, speed, armor, reward, damageToPlayer, type.SMALL);
	}
	private static String   name = "small";
	private static double   health = 15;
	private static double   armor = 1;
	private static double   reward = 20;
	private static double   speed = 2;
	private static int      damageToPlayer = 5;
}
