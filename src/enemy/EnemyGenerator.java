package enemy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class EnemyGenerator {
	
	private Queue<Enemy> 		EnemyQueue 	= new LinkedList<Enemy>();
	private int 				level;
	
	public EnemyGenerator(int level) {
		this.level = level;
	}
	int[][] CritterStream = {		
			{2,1,0,0},
			{5,2,0,0},
			{6,2,1,0},
			{6,3,2,1},
			{6,3,3,2},
			{8,4,4,3},
			{9,5,5,4},
			{10,6,6,5},	
			{12,7,7,5},
			{15,8,8,6},
			{16,8,9,7},
			{20,8,9,8},
	
							};
	
	//tự động sinh ra địch cho các level 
	public int[][] addCritterList(int lvlStart){
		int[][] cListToAppend = new int[1000][4];
		for(int i=lvlStart;i<1000+lvlStart;i++){
			for(int j=0;j<4;j++){
				cListToAppend[i-lvlStart][j] = (i/3) + (4-j)*5;
			}
		}
		return cListToAppend;
	}

	public void createEnemyQueue(){
		int[][] critterStreamToAppend = addCritterList(CritterStream.length);
		int[][] fullCritterStream = new int[CritterStream.length+critterStreamToAppend.length][4];

		for(int i=0;i<fullCritterStream.length;i++){
			if(i<CritterStream.length){
				fullCritterStream[i]=CritterStream[i];
			}
			else{
				fullCritterStream[i]=critterStreamToAppend[i-CritterStream.length];
			}
		}
		for(int x = 0; x < 4 ; x++)
		{
			for(int y = 0; y < fullCritterStream[level][x] ; y++){	

				Enemy e = addEnemy(x);
				EnemyQueue.add(e);

			}
		}


	}
	
	
	//Xáo trộn thứ tự các quân địch
	public void RandomizeCritterQueue()
	{
		Collections.shuffle((LinkedList<Enemy>) EnemyQueue);
	}

	//xác định cụ thể các loại kẻ địch
	private Enemy addEnemy(int x){
		if(x==0){
			Enemy e = new NormalEnemy();
			return e;
		}
		if(x==1){
			Enemy e = new SmallEnemy();
			return e;
		}
		if(x==2){
			Enemy e = new TankerEnemy();
			return e;
		}
		if(x==3){
			Enemy e = new BossEnemy();
			return e;
		}
		return null;
	}

	public Queue<Enemy> getEnemyQueue() {
		return EnemyQueue;
	}
}
