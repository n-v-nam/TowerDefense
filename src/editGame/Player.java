package editGame;

public class Player {
	
	public int credits;
	public int HP;
	public static final int STARTINGCREDITS = 30000;
	public static final int STARTINGHP = 100;
	private static Player instance = null;
	public double score;
	
	public static synchronized Player getPlayer(){
		if(instance ==null){
			instance = new Player();
		}
		return instance;
	}
	public void reset() {
		credits = STARTINGCREDITS;
		HP = STARTINGHP;
		score = 0;
	}
	
	public void addCredits(double x) {
		credits += x;
	}
	public void decreaseHP(int x){
		HP -= x;
		if(HP < 0) HP = 0;
	}
	public void addScore(double x) {
		score += x;
	}
}
