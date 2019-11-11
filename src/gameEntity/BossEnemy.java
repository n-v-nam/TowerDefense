package gameEntity;

public class BossEnemy extends Enemy {

	public BossEnemy() {
		super(name, health, speed, armor, reward, damageToPlayer, type.BOSS);
	}
	private static String   name = "boss";
	private static double   health = 50;
	private static double   armor = 5;
	private static double   reward = 100;
	private static double   speed = 0.5;
	private static int      damageToPlayer = 10;
}
