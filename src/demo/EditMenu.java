package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EditMenu extends JPanel{
		
		private BufferedImage bg, icon1;
		
		public void paint(Graphics g) {
			try {
				for(int i=0; i<672; i=i+32) {
					for(int j=0; j<544; j=j+32) {
						bg = ImageIO.read(new File("E:\\GameTower\\Cross.png"));
						icon1 = ImageIO.read(new File("E:\\GameTower\\Icon2.png"));
						g.drawImage(bg,i,j,null);
					}
				g.drawImage(icon1, 100, 50, null);
				bg = ImageIO.read(new File("E:\\GameTower\\logo.png"));
				g.drawImage(bg, 190, 40, null);
				bg = ImageIO.read(new File("E:\\GameTower\\StartGameButton.png"));
				g.drawImage(bg, 190, 140, null);
				bg = ImageIO.read(new File("E:\\GameTower\\ExitButton.png"));
				g.drawImage(bg, 190, 300, null);
				bg = ImageIO.read(new File("E:\\GameTower\\HelpButton.png"));
				g.drawImage(bg, 190, 220, null);
				g.setColor(Color.BLACK);
				g.setFont(new Font("NewellsHand", Font.PLAIN, 18));
				g.drawString("Java Code by Nam Rồ K63UET", 10, 440);
				g.drawString("OOP - UET - VNU", 10, 460);
				g.setColor(Color.WHITE);
				g.setFont(new Font("NewellsHand", Font.ITALIC, 18));
				g.drawString("CHÚC BẠN CHƠI GAME VUI VẺ", 200, 400);
				g.setColor(Color.BLACK);
				g.setFont(new Font("NewellsHand", Font.LAYOUT_NO_START_CONTEXT, 15));
				g.drawString("*** Nếu lần đầu chơi hãy đọc phần Help trước khi bắt đầu", 280, 475);
				}
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}

