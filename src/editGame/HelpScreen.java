package editGame;

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
	
	BufferedImage bg, icon, logo;

	public void paint(Graphics g) {
//		for(int i=0; i<672; i=i+32) {
//			for(int j=0; j<512; j+=32) {
				try {
					bg = ImageIO.read(new File("E:\\GameTower\\BG3.jpg"));
					icon = ImageIO.read(new File("E:\\GameTower\\Icon2.png"));
					logo = ImageIO.read(new File("E:\\GameTower\\Logo.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
//				g.drawImage(bg,i,j,null);
//			}
//		}
		g.drawImage(bg, 0, 0, null);
		g.drawImage(icon, 100, 400, null);
		g.drawImage(logo, 550,400, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("A",Font.PLAIN, 16));
		g.drawString("- Bấm New Game để bắt đầu màn chơi", 10, 60);
		g.drawString("- Bấm Resume để bắt đầu lại map đã lưu...", 10, 90);
		g.drawString("- Bấm StartWave để bắt đầu các đợt tấn công của địch", 10, 120);
		g.drawString("- Bấm chuột trái vào tháp muốn mua , sau đó bấm chuột phải để xây tháp ", 10, 150);
		g.drawString("- Nâng cấp tháp, bán tháp, mua và dùng Item tương tự như trên", 10, 180);
		g.drawString("- Bấm vào x2 để tăng tốc độ game x2 lần", 10, 210);
		g.setFont(new Font("A",Font.BOLD, 18));
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("HƯỚNG DẪN CHƠI GAME:", 10, 30);
		g.setColor(Color.WHITE);
		g.drawString("Bấm SPACE để thoát Hướng dẫn", 10, 300);
	}
}
