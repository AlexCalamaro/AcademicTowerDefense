package code.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
/**
 * ImageActors are any actors who are represented on screen by an image. This applies to most actors in the game at present.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class ImageActor extends Actor 
{
	protected Image image;

	/**
	 * 
	 * @param location The location the enemy should start 
	 * @param size The size the enemy should be
	 * @param key the key associated with the actors image in the ImageManager.
	 */
	public ImageActor(Point location, Dimension size, String key) 
	{
		super(location, size);
		this.image=ImageManager.getManager().getImage(key, size.width, size.height);
	}
	
	/**
	 * The default drawing method of actor. Draws the image at (0,0). 
	 * Note that the world will shift the coordinate plain using the location field such that
	 * the (0,0) point of any actor's coordinate system is at his location from the perspective of
	 * the board.
	 * @param g The graphics object to do the drawing on
	 */
	public void paint(Graphics g)
	{
		g.drawImage(image, 0, 0, null);
	}

}
