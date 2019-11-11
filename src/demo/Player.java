package demo;

public class Player {
	
	public int credits;
	public int HP;
	public static final int STARTINGCREDITS = 150;
	public static final int STARTINGHP = 100;
	private static Player instance = null;
	
	public static synchronized Player getPlayer(){
		if(instance ==null){
			instance = new Player();
		}
		return instance;
	}
	public void reset() {
		credits = STARTINGCREDITS;
		HP = STARTINGHP;
	}
	
	public void addCredits(double x) {
		credits += x;
	}
	public void decreaseHP(int x){
		HP -= x;
	}
}
