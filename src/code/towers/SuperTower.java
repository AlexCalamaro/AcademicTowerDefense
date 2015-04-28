package code.towers;

import java.awt.Dimension;
import java.awt.Point;


/**
 * This tower acts as a regulare tower, but attacks much faster and does much more damage.
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class SuperTower extends Tower {

	//Defaut values
	public static final Dimension DIMENSIONS = new Dimension(30, 30); 
	public static final String DESCRIPTION = "This is the ultimate obsticle before graduation " +
			"Students will be hard pressed to get past this class.";
	
	//change these to balance the game
	public static final int DEFAULT_COST = 2500;
	public static final int DEFAULT_COOLDOWN = 5;
	public static final int DEFAULT_RANGE = 450;
	
	public static final int DEFAULT_DAMAGE = 50;
	public static final int DEFAULT_PROJECTILE_SPEED = 30;

	/**
	 * @param location: the location of the tower on the Board
	 */
	public SuperTower(Point location){
		super(location, DIMENSIONS, "SuperTower", "Comprehensive Exercise", DESCRIPTION,
				DEFAULT_COST, DEFAULT_COOLDOWN, DEFAULT_RANGE, DEFAULT_DAMAGE, DEFAULT_PROJECTILE_SPEED);
		
	}

}
