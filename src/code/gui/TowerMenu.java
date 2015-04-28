package code.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JPanel;

import code.gui.towerButtons.AOETowerButton;
import code.gui.towerButtons.BasicTowerButton;
import code.gui.towerButtons.DOTTowerButton;
import code.gui.towerButtons.ModTowerButton;
import code.gui.towerButtons.SlowingTowerButton;
import code.gui.towerButtons.StunTowerButton;
import code.gui.towerButtons.SuperTowerButton;
import code.towers.AOETower;
import code.towers.BasicTower;
import code.towers.DOTTower;
import code.towers.ModTower;
import code.towers.SlowingTower;
import code.towers.StunTower;
import code.towers.SuperTower;

/**
 * TowerMenu is the menu that will eventually show a grid of tower images with the cost for each. 
 * At present does nothing but display text. 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version May 26, 2014
 *
 */
public class TowerMenu extends JPanel 
{	
	private Image background;
	/**
	 * This constructor just loads in the image and sets a preferred size.
	 */
	public TowerMenu(UpgradeMenu subMenu)
	{
		background = TowerDefenseGUI.loadImage("StickyNote4.jpg", 250, 200);

		this.setPreferredSize(new Dimension(250, 200));
		
		AOETowerButton aoeButton = new AOETowerButton(subMenu);
		BasicTowerButton basicButton = new BasicTowerButton(subMenu);
		DOTTowerButton dotButton = new DOTTowerButton(subMenu);
		ModTowerButton modButton = new ModTowerButton(subMenu);
		SlowingTowerButton slowingButton = new SlowingTowerButton(subMenu);
		StunTowerButton stunButton = new StunTowerButton(subMenu);
		SuperTowerButton superButton = new SuperTowerButton(subMenu);
		
		this.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		this.add(basicButton);
		this.add(modButton);
		this.add(aoeButton);
		this.add(dotButton);
		this.add(slowingButton);
		this.add(stunButton);
		this.add(superButton);
	}
	
	/**
	 * For now, this method just displays a message over the background.
	 * @param g The graphics object on which to paint the TowerMenu.
	 */

	
	public void paintComponent(Graphics g)
	{

		g.drawImage(background, 0, 0, null);

	}

}
