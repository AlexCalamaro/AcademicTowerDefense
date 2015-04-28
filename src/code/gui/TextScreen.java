package code.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * TextScreen represents a screen that has a piece of whitepaper background and displays text and has a back button.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class TextScreen extends JPanel 
{
	protected static Image background;
	protected JPanel lastScreen;
	protected TowerDefenseGUI ui;
	protected String displayString;
	/**
	 * Intializes the varianbles
	 * @param text The text displayed on the TextScreen.
	 * @param lastScreen The screen the back button should go to
	 * @param ui The ui in wich the TextScreen exists.
	 */
	public TextScreen(String text, final JPanel lastScreen, final TowerDefenseGUI ui)
	{
		this.ui=ui;
		this.lastScreen=lastScreen;
		this.displayString = text;
		if(background==null)
			background=TowerDefenseGUI.loadImage("LinedPaper.jpg", 800, 800);
		this.setPreferredSize(new Dimension(800, 800));
		JButton back=new JButton("Back"); 
		back.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ui.switchToScreen(lastScreen);
			}
		});
		this.add(back);
	}
	/**
	 * Draws the text screen.
	 * PaintComponent is used over paint to allow the TextScreens background to display in addition to the
	 * buttons and text on it.
	 */
	public void paintComponent(Graphics g)
	{
		g.drawImage(background, 0, 0, null);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));

		int start = 0;
		int end = 78;
		int y = 100;
		int subLen = 0;

		while(end < this.displayString.length())
		{
			String sub = this.displayString.substring(start, end);
			int lastSpace = sub.lastIndexOf(' ');
			int lastPeriod = sub.lastIndexOf('.');

			if (lastPeriod > lastSpace)
			{
				sub = sub.substring(0,lastPeriod);
				subLen = sub.length();
			}
			else
			{
				sub = sub.substring(0,lastSpace);
				subLen = sub.length();
			}

			g.drawString(sub, 125, y);
			start += subLen;
			end += 78;
			y += 21;
		}
		g.drawString(this.displayString.substring(start,this.displayString.length()), 125, y);
	}
}


