package gameEntity;

public class NormalEnemy extends Enemy{

	public NormalEnemy() {
		super(name, health, speed, armor, reward, damageToPlayer, type.NORMAL);
	}
	
	private static String   name = "normal";
	private static double   health = 20;
	private static double   armor = 1.5;
	private static double   reward = 30;
	private static double   speed = 1;
	private static int      damageToPlayer = 4;
}
