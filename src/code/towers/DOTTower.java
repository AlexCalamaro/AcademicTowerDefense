package code.towers;

import java.awt.Dimension;
import java.awt.Point;

import code.enemies.Enemy;
import code.game.Board;
import code.projectiles.DOTProjectile;

/**
 * This Tower does damage over time. Dealing damage per tick rather than a set damage 
 * on impact.
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class DOTTower extends Tower {

	//Default values
	public static final Dimension DIMENSIONS = new Dimension(30, 30); 
	public static final String DESCRIPTION = "This class gives out papers, causing students" +
			" to take damage over time.";
	
	//change these to balance the game
	public static final int DEFAULT_COST = 600;
	public static final int DEFAULT_COOLDOWN = 75;
	public static final int DEFAULT_RANGE = 100;
	
	public static final int DEFAULT_DAMAGE = 1;
	public static final int DEFAULT_PROJECTILE_SPEED = 10;
	
	int duration;
	
	
	/**
	 * @param location: the location of the tower on the Board
	 */
	public DOTTower(Point location){
		super(location, DIMENSIONS, "DOTTower", "English Class", DESCRIPTION,
				DEFAULT_COST, DEFAULT_COOLDOWN, DEFAULT_RANGE, DEFAULT_DAMAGE, DEFAULT_PROJECTILE_SPEED);
		
		//number of ticks to deal damage
		duration = 100;
		
	}
	
	
	/**
	 * creates a DOT projectile aimed at enemy e
	 * @param e: the enemy the projectile is aimed at
	 */
	@Override
	protected void createProjectile(Enemy e) {
		Board.getBoard().addActor(new DOTProjectile(location, e, damage, projectileSpeed, duration));

	}

}
