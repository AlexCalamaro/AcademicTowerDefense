package code.towers;

import java.awt.Dimension;
import java.awt.Point;

import code.enemies.Enemy;
import code.game.Board;
import code.projectiles.ModProjectile;

/**
 * This tower reduces armor, making other towers do more damage.
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class ModTower extends Tower {

	//default values
	public static final Dimension DIMENSIONS = new Dimension(30, 30); 
	public static final String DESCRIPTION = "This class gives out lots of home work " +
			"causing all other work to be more effective.";
	
	//change these to balance the game
	public static final int DEFAULT_COST = 250;
	public static final int DEFAULT_COOLDOWN = 20;
	public static final int DEFAULT_RANGE = 150;
	
	public static final int DEFAULT_DAMAGE = 10;
	public static final int DEFAULT_PROJECTILE_SPEED = 10;
	
	//the amount of armor to reduce
	private int modifier;
	//the duration of the reduction
	private int duration;
	
	
	/**
	 * @param location: the location of the tower on the Board
	 */
	public ModTower(Point location){
		super(location, DIMENSIONS, "ModTower", "Math Class", DESCRIPTION,
				DEFAULT_COST, DEFAULT_COOLDOWN, DEFAULT_RANGE, DEFAULT_DAMAGE, DEFAULT_PROJECTILE_SPEED);
		
		
		modifier = 4;
		duration = 10;
		
	}
	
	/**
	 * creates a modProjectile aimed at enemy e
	 * @param e: the enemy the projectile is aimed at
	 */
	@Override
	protected void createProjectile(Enemy e) {
		Board.getBoard().addActor(new ModProjectile(location, e, damage, projectileSpeed, modifier, duration));
	}

}
