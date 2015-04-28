package code.towers;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import code.enemies.Enemy;
import code.game.Board;
import code.projectiles.StunProjectile;

/**
 * This tower stuns all enemies in its AOE and deals damage.
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class StunTower extends Tower {

	//Deafult values
	public static final Dimension DIMENSIONS = new Dimension(30, 30); 
	public static final String DESCRIPTION = "This class gives out test periodically, causing students" +
			" to be stunned for a short time.";
	
	
	//change these to balance the game
	public static final int DEFAULT_COST = 750;
	public static final int DEFAULT_COOLDOWN = 100;
	public static final int DEFAULT_RANGE = 100;
	
	public static final int DEFAULT_DAMAGE = 5;
	public static final int DEFAULT_PROJECTILE_SPEED = 30;
	
	//duration of the slow
	int duration;
	
	public StunTower(Point location){
		super(location, DIMENSIONS, "StunTower", "Physics Class", DESCRIPTION,
				DEFAULT_COST, DEFAULT_COOLDOWN, DEFAULT_RANGE, DEFAULT_DAMAGE, DEFAULT_PROJECTILE_SPEED);
		
		duration = 50;
		
	}
	
	/**
	 * fires at all enemies within range
	 */
	@Override
	public void fire(){
		ArrayList<Enemy> enemies = getTargets();
		for(Enemy e: enemies){
			createProjectile(e);
		}
	}
	
	/**
	 * creates a stun projectile aimed at enemy e
	 * @param e: the enemy to be aimed at
	 */
	@Override
	protected void createProjectile(Enemy e) {
		Board.getBoard().addActor(new StunProjectile(location, e, damage, projectileSpeed, duration));

	}
}
