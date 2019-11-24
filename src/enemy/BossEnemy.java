package enemy;

public class BossEnemy extends Enemy {

	public BossEnemy() {
		super(name, health, HEALTHYSTART, speed, armor, reward, score, damageToPlayer, type.BOSS);
	}
	public static String   name = "boss";
	public static double   health = 60;
	public static double   armor = 6;
	public static double   reward = 50;
	public static double   speed = 0.75;
	public static int      damageToPlayer = 10;
	public static double   score = 20;
	public static int HEALTHYSTART = 60;
}
