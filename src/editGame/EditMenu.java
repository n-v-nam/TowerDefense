package editGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class EditMenu extends JPanel{
		
		private BufferedImage bg, saveButton, help;
		
		public void paint(Graphics g) {
			try {
				help = ImageIO.read(new File("E:\\GameTower\\HelpButton.png"));
				bg = ImageIO.read(new File("E:\\GameTower\\BG1.jpg"));
				saveButton = ImageIO.read(new File("E:\\GameTower\\Resume.png"));
				g.drawImage(bg,0,0,null);
				bg = ImageIO.read(new File("E:\\GameTower\\logo2.png"));
				g.drawImage(bg, 200, 20, null);
				g.drawImage(saveButton, 360, 220, null);
				bg = ImageIO.read(new File("E:\\GameTower\\New Game.png"));
				g.drawImage(bg, 30, 220, null);
				bg = ImageIO.read(new File("E:\\GameTower\\ExitButton.png"));
				g.drawImage(bg, 190, 310, null);
				g.setColor(Color.WHITE);
				g.setFont(new Font("NewellsHand", Font.PLAIN, 18));
				g.drawString("Java Code by Nam Rồ K63UET", 10, 460);
				g.drawString("OOP - UET - VNU", 10, 480);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}

