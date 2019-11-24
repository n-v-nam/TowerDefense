package enemy;

public class SmallEnemy extends Enemy {
	public SmallEnemy() {
		super(name, health, HEALTHYSTART, speed, armor, reward, score, damageToPlayer, type.SMALL);
	}
	public static String   name = "small";
	public static double   health = 15;
	public static double   armor = 1;
	public static double   reward = 10;
	public static double   speed = 2;
	public static int      damageToPlayer = 5;
	public static double   score = 2;
	public static int HEALTHYSTART = 15;
}
