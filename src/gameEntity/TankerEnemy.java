package gameEntity;

public class TankerEnemy extends Enemy {
	public TankerEnemy() {
		super(name, health, speed, armor, reward, damageToPlayer, type.TANKER);
	}
	private static String   name = "tanker";
	private static double   health = 30;
	private static double   armor = 4;
	private static double   reward = 40;
	private static double   speed = 0.5;
	private static int      damageToPlayer = 3;
}
