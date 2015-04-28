package code.gui.towerButtons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import code.game.Board;
import code.gui.TowerDefenseGUI;
import code.gui.UpgradeMenu;
import code.towers.AOETower;
import code.towers.DOTTower;
import code.towers.Tower;

/**
 * The TowerButton is a custom button which opens the appropriate tower
 * description menu when the TowerButton is clicked.
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version May 26, 2014
 */
public class DOTTowerButton extends TowerButton
{
	public DOTTowerButton(final UpgradeMenu menu) {
		super(menu, "DOTTower.jpg", DOTTower.DESCRIPTION);
	}
	/**
	 * Sets up the pause/play button using an anonymous action listener that changes the
	 * internal field isPlaying and the image.
	 */
	
	protected Tower makeNewTowerAt(Point location)
	{
		return new DOTTower(location);
	}
	protected int getCost()
	{
		return DOTTower.DEFAULT_COST;
	}

}
