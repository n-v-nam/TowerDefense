package enemy;

public class NormalEnemy extends Enemy{

	public NormalEnemy() {
		super(name, health, HEALTHYSTART, speed, armor, reward, score, damageToPlayer, type.NORMAL);
	}
	
	public static String   name = "normal";
	public static double   health = 20;
	public static double   armor = 1.5;
	public static double   reward = 20;
	public static double   speed = 1.5;
	public static int      damageToPlayer = 4;
	public static double   score = 2;
	public static int HEALTHYSTART = 20;
}
