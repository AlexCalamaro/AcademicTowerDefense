package code.enemies;

import java.awt.Dimension;
import java.awt.Point;

/**
* This is a subclass of the enemy class and is used to create basic enemies. 
* It holds a name, health, speed and rewarded money after being killed.
* @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
* @version June 9, 2014
*/
public class BasicEnemy extends Enemy 
{
	/**
	 * constructor that uses the enemy class' constructor
	 */
	public BasicEnemy(Point location) 
	{
		super(location, new Dimension(50,50), "basicEnemy", "Normal Student", 50, 5, 2, 20);
	}
	

}
