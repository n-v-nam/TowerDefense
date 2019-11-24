package enemy;

public class TankerEnemy extends Enemy {
	public TankerEnemy() {
		super(name, health, HEALTHYSTART, speed, armor, reward, score, damageToPlayer, type.TANKER);
	}
	public static String   name = "tanker";
	public static double   health = 30;
	public static double   armor = 4;
	public static double   reward = 30;
	public static double   speed = 1;
	public static int      damageToPlayer = 3;
	public static double   score = 4;
	public static int HEALTHYSTART = 30;
}
