package code.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

/**
 * MenuPanel is the panel in which all other menu related Jpanels reside.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version May 26, 2014
 */
public class MenuPanel extends JPanel 
{
	/**
	 * This constructor sets up the MenuPanel using a GridBag layout.
	 * 
	 * It has TowerMenu, UpgradeMenu and OptionMenu components
	 */
	public MenuPanel()
	{	
		this.setPreferredSize(new Dimension(250, 800));
		this.setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		this.add(new PlayerStatusDisplay(), c);
		
		UpgradeMenu upgradeMenu = UpgradeMenu.getUpgradeMenu();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		this.add(new TowerMenu(upgradeMenu), c);
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		this.add(upgradeMenu, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		this.add(new OptionMenu(), c);
	}
	
	
}
