package code.towers;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import code.enemies.Enemy;
import code.game.Board;


/**
 * This tower attacks a certain number of enemies within its AOE
 *  dealing large amounts of Damage
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class AOETower extends Tower {

	//Default values
	public static final Dimension DIMENSIONS = new Dimension(30, 30); 
	public static final String DESCRIPTION = "This class gives out group projects " +
			"dealing large amounts of damage to all students within range.";
	
	
	//Change these to balance the game
	public static final int DEFAULT_COST = 500;
	public static final int DEFAULT_COOLDOWN = 65;
	public static final int DEFAULT_RANGE = 130;
	
	public static final int DEFAULT_DAMAGE = 100;
	public static final int DEFAULT_PROJECTILE_SPEED = 20;
	
	private int numTargetsToHit;
	
	
	/**
	 * creates a normal projectile aimed at enemy e
	 * @param location: the location of the tower on the board
	 */
	public AOETower(Point location){
		super(location, DIMENSIONS, "AOETower", "Computer Science Class", DESCRIPTION,
				DEFAULT_COST, DEFAULT_COOLDOWN, DEFAULT_RANGE, DEFAULT_DAMAGE, DEFAULT_PROJECTILE_SPEED);
		
		numTargetsToHit = 4;
		
	}

	
	/**
	 * fires a a certain number of targets 
	 */
	@Override
	public void fire(){
		ArrayList<Enemy> enemies = getTargets();
		for(int i = 0; i < numTargetsToHit; i++){
			if(enemies.size() > i)
			{
				createProjectile(enemies.get(i));
			}
		}
	}
	
}
