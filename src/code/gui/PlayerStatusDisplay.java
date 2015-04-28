package code.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import code.game.Board;
import code.game.PlayerStatObserver;

/**
 * The PlayerStatus display shows game relevant information the player might wish to know.
 * Specifically, it shows the number of lives the player has remaining and how much money they
 * currently have. 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version May 26, 2014
 */
public class PlayerStatusDisplay extends JPanel implements PlayerStatObserver
{
	private int money;
	private int lives;
	private int level;
	private Image background;
	private Image heart;

	/**
	 * Creates the display and initializes the display to the given values.
	 * @param money The amount of money the player has upon creation
	 * @param lives The amount of lives the player has upon creation
	 */
	public PlayerStatusDisplay()
	{
		background = TowerDefenseGUI.loadImage("StickyNote1.jpg", 250, 200);
		this.setPreferredSize(new Dimension(250, 200));
		heart = TowerDefenseGUI.loadImage("Heart.jpg", 36, 36);
		Board.getBoard().signUpToObserve(this);
	}
	/**
	 * Updates the PlayerStatusDisplay to the new values of money and lives. This is a fairly simple
	 * implementation of the observer design pattern, and this method should be called whenever these values
	 * display to that, from the user perspective, the menu is updated in real time.
	 * @param money The new value of money
	 * @param lives The new number of lives the player has.
	 */
	public void notify(int lives, int money, int level)
	{
		this.money=money;
		this.lives=lives;
		this.level=level;
		this.repaint();
	}
	
	/**
	 * Draws the player status display, using the heart image to represent lives.
	 * @param g The graphics on which to draw this Display.
	 */
	public void paint(Graphics g)
	{
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		g.drawImage(background,  0,  0,  null);
		String status="$ : "+money;
		g.setColor(Color.RED);
		g.drawString(status, 30, 80);
		g.drawImage(heart, 20, 110, null);
		g.drawString(": " + lives, 65, 140);
		if(level>0)
			g.drawString("Level: "+level, 30, 180);
	}
	
}
