package code.gui;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import code.game.Board;
import code.towers.Tower;


/**
 * The panel that holds information about the selected tower. Currently with placeholder text.
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version May 26, 2014
 */
public class UpgradeMenu extends JPanel {
	
	private Image background;
	private String description;
	private static UpgradeMenu theMenu;
	private JButton sellTower;
	private ActionListener currentActionListener;

	/**
	 * Constructor for upgrade menu.
	 * UpgradeMenu both displays description text for each tower, as well as
	 * a "Sell Tower" button when a tower on the board is selected.
	 */
	private UpgradeMenu()
	{
		description = "Please select a tower";
		
		background = TowerDefenseGUI.loadImage("StickyNote3.jpg", 250, 200);
		this.setPreferredSize(new Dimension(250, 200));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 150));
		
		//Sell-Tower button is invisible when a tower is not selected
		sellTower = new JButton("Sell Tower");
		this.add(sellTower);
		sellTower.setVisible(false);
	}
	
	/**
	 * @return Returns the static instance of itself, so that board can indicate that a tower has been clicked
	 */
	public static UpgradeMenu getUpgradeMenu()
	{
		if(theMenu==null)
			theMenu=new UpgradeMenu();
		return theMenu;
	}
	
	/**
	 * Sets description text based on the tower selected by TowerMenu
	 * @param text Text to display to the user
	 */
	public void setDescription(String text)
	{
		this.description = text;
		this.repaint();
	}
	
	/**
	 * Paints description text and does some newline formatting which drawString() doesn't natively support.
	 * It automatically adds newlines at the farthest space character or period in the alloted space to prevent
	 * words being sliced.
	 */
	@Override
	public void paintComponent(Graphics g)
	{		
		g.drawImage(background, 0, 0, null);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		
		int start = 0;
		int end = 30;
		int y = 20;
		int subLen = 0;
		
		while(end < this.description.length())
		{
			String sub = this.description.substring(start, end);
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
			
			g.drawString(sub, 20, y);
			start += subLen;
			end += 30;
			y += 20;
		}
		g.drawString(this.description.substring(start,this.description.length()), 20, y);
	}
	
	public void notifyOfClick(final Tower towerThatWasClicked)
	{
		final int sellPrice = towerThatWasClicked.getCost()/2;
		String priceString = "Sell tower for: $" + Integer.toString(sellPrice);
		sellTower.setText(priceString);
		this.sellTower.setVisible(true);

		if(currentActionListener!=null)
			this.sellTower.removeActionListener(currentActionListener);
		this.currentActionListener=new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				Board b = Board.getBoard();
				b.removeActor(towerThatWasClicked);
				b.increaseMoney(sellPrice);
				sellTower.setVisible(false);
			}	
		};
		this.sellTower.addActionListener(currentActionListener);
	}
}
