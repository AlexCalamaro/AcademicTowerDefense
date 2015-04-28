package code.towers;

import java.awt.Dimension;
import java.awt.Point;


/**
 * A basic tower. Does nothing special. Simply creates a concrete subclass of Tower
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class BasicTower extends Tower {
	
	
	//Default values
	public static final Dimension DIMENSIONS = new Dimension(30, 30); 
	public static final String DESCRIPTION = "This proffesor has a hard time making students care," +
			" but does still make graduating harder.";
	
	
	//change these values to balance the game
	public static final int DEFAULT_COST = 100;
	public static final int DEFAULT_COOLDOWN = 35;
	public static final int DEFAULT_RANGE = 200;
	
	public static final int DEFAULT_DAMAGE = 30;
	public static final int DEFAULT_PROJECTILE_SPEED = 15;
	
	/**
	 * @param location: the location of the tower on the Board
	 */
	public BasicTower(Point location){
		super(location, DIMENSIONS, "basicTower", "Scrunched Class", DESCRIPTION, 
				DEFAULT_COST, DEFAULT_COOLDOWN, DEFAULT_RANGE, DEFAULT_DAMAGE, DEFAULT_PROJECTILE_SPEED);
		
	}



	
}
