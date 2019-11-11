package demo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class GameStage extends JFrame {
	public static int xClick = 0, yClick= 0, xSelect = 0, ySelect = 0;
	public static boolean exitHelp = false;
    EditGame editMap = new EditGame();
    LoadSound loadSound = new LoadSound();
	
	public GameStage() {
		setTitle("Tower Defense");
		setSize(672, 512);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setVisible(true);
		setResizable(false);
		add(editMap);
		
		

		addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked (MouseEvent e) {
            	System.out.println(e.getButton());
            	if(e.getButton() == 1) {
            		xClick = e.getX() - 3;
            		yClick = e.getY() - 26;
            		if(Play.sound) loadSound.playSound("E:\\GameTower\\Sound\\Clicked.wav",1);
            		System.out.println("L:" +xClick +","+ yClick);
            	}
                
                if(e.getButton() == 3) {
                	xSelect = e.getX() - 3;
                	ySelect = e.getY() - 26;
                	System.out.println("R:" + xSelect +","+ySelect);
                }
            }
		});
		
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == ' ') exitHelp = true;
			}
			
		});
	}
	
	public static void main(String args[]) {
		GameStage gameStage = new GameStage();
	}
}

