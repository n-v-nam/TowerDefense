package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class HelpScreen extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BufferedImage bg;

	public void paint(Graphics g) {
		for(int i=0; i<672; i=i+32) {
			for(int j=0; j<544; j=j+32) {
				try {
					bg = ImageIO.read(new File("E:\\GameTower\\SandTile.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(bg,i,j,null);
				}
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font("Hello", Font.PLAIN, 20));
		g.drawString("- Bấm Start để bắt đầu màn chơi", 10, 60);
		g.drawString("- Bấm StartWave để bắt đầu các đợt tấn công của địch", 10, 90);
		g.drawString("- Bấm chuột trái vào tháp muốn mua , sau đó bấm chuột phải để xây tháp ", 10, 120);
		g.drawString("- Nâng cấp và bán tháp tương tự như trên", 10, 150);
		g.drawString("- Bấm vào x2 để tăng độc độ game x2 lần", 10, 180);
		
		g.drawString("HƯỚNG DẪN CHƠI GAME:", 10, 30);
		g.setColor(Color.RED);
		g.drawString("Bấm SPACE để thoát Help", 10, 300);
	}
}
