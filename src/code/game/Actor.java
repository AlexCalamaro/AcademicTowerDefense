package code.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import code.gui.TowerDefenseGUI;
/**
 * Actor is the abstract class that all objects which appear on the extend, either directly or indirectly.
 * By forcing them each to extend this class, we allow them all to be placed in a list of type
 * Actor and we allow the game to call the update method of any actor as well as get their location
 * for drawing purposes.
 * 
 * Also, all actors track an image which represents them.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public abstract class Actor
{
	protected Point location;

	protected Dimension size;
	
	/**
	 * A pretty simple constructor, initializes the variables
	 * @param location The starting location of the actor
	 * @param path The path to the image file that represents the actor
	 * @param scaleX The desired size of the actor's image in the X direction.
	 * @param scaleY The desired size of the actor's image in the Y direction.
	 */
	public Actor(Point location, Dimension size)
	{
		this.size=size;
		this.location=location;
	}
	/**
	 * This method is blank, because we want to guarantee that all actors will have an update method
	 * so that it can be called each tick, however since some actors don't actually do anything
	 * we didn't want an abstract method. Hence, the method is concrete but blank so that by default
	 * actors don't do anything, but it can be easily overrided.
	 */
	public void update()
	{
	}
	/**
	 * Same story as with update here.
	 */
	public void paint(Graphics g)
	{
		
	}

	/**
	 * @return Gets the (x,y) location of the actor and returns it.
	 */
	public Point getLocation() {
		return location;
	}
	/**
	 * @param location The place the actor will be located after this method finishes.
	 */
	public void setLocation(Point location) {
		this.location = location;
	}
	/**
	 * 
	 * @return The size of the actor
	 */
	public Dimension getSize() {
		return size;
	}
}
