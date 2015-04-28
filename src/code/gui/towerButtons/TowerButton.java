package code.gui.towerButtons;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
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
import code.towers.Tower;

public abstract class TowerButton extends JButton implements MouseListener
{
	/**
	 * Sets up the pause/play button using an anonymous action listener that changes the
	 * internal field isPlaying and the image.
	 * 
	 * @param image Stores the icon for the tower
	 * @param targetX Stores the x-coordinate where we will drop a tower
	 * @param targetY Stores the y-coordinate where we will drop a tower
	 * @param location Stores the position of the top left corner of the current towerButton
	 * @param customCursor Stores the cursor object used to create the perception of "drag and drop"
	 * @param defaultCursor Stores the system default cursor
	 * @param hotspot Stores the point on the cursor icon which performs the "click"
	 */
	private ImageIcon image;
	public int targetX;
	public int targetY;
	private Point location;
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Cursor customCursor;
	Cursor defaultCursor = Cursor.getDefaultCursor();
	Point hotspot = new Point(0,0);
	private Image cursorImage;
	
	
	/**
	 * TowerButton creates a multi-functional button which can drag-and drop towers onto the board
	 *  and update the menu that describes a given tower. It's subclasses are all implementations for
	 *  specific towers.
	 * 
	 * @param menu Stores instance of the relevant UpgradeMenu which needs to be updated when user selects a tower
	 * @param imagePath Stores path to tower icon
	 * @param description Stores string description of a tower's properties
	 */
	public TowerButton(final UpgradeMenu menu, String imagePath, final String description)
	{
		addMouseListener(this);
		
		this.cursorImage = toolkit.getImage(imagePath);
		this.cursorImage = this.cursorImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		
		this.customCursor = this.toolkit.createCustomCursor(this.cursorImage, this.hotspot, "DragCursor");
		
		image = new ImageIcon(TowerDefenseGUI.loadImage(imagePath,50,50));		
		this.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				menu.setDescription("Cost: "+getCost()+"                     "+description);
				
			}
		});
		this.setIcon(image);
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(50,50));
		
	}

	/**
	 * Resets default cursor, and calculates where on the board to add the new tower.
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{		
			setCursor(defaultCursor);
			this.location = this.getLocation();
			e.translatePoint(800 + this.location.x, 200 + this.location.y);
			if(Board.getBoard().isInBounds(e.getPoint()) && Board.getBoard().spendMoney(getCost()))
			{
				Point towerHere = e.getPoint();
				Tower newTower = makeNewTowerAt(towerHere);
				Board.getBoard().addActor(newTower);
			}
	}
	
	protected abstract int getCost();
	
	protected abstract Tower makeNewTowerAt(Point location);

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Sets a custom cursor on mouse-press to simulate drag-and-drop.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		//javaSet custom cursor icon to make drag-and-drop work
		setCursor(this.customCursor);
		
	}

}
