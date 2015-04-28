package code.towers;

import java.awt.Dimension;
import java.awt.Point;

import code.enemies.Enemy;
import code.game.Board;
import code.projectiles.SlowingProjectile;

/**
 * This tower reduces the speed of all enemies hit
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class SlowingTower extends Tower {

	//default values
	public static final Dimension DIMENSIONS = new Dimension(30, 30); 
	public static final String DESCRIPTION = "This proffesor gives out a lot of reading," +
			" making students stay up late. " +
			"As a result they move more slowly.";
	
	//change these to balance the game;
	public static final int DEFAULT_COST = 750;
	public static final int DEFAULT_COOLDOWN = 45;
	public static final int DEFAULT_RANGE = 100;
	
	public static final int DEFAULT_DAMAGE = 20;
	public static final int DEFAULT_PROJECTILE_SPEED = 10;
	
	//the amount to slow the enemy
	int slowAmount;
	//the duration of the slow
	int duration;
	
	/**
	 * @param location: the location of the tower on the Board
	 */
	public SlowingTower(Point location){
		super(location, DIMENSIONS, "SlowingTower", "History Class", DESCRIPTION, 
				DEFAULT_COST, DEFAULT_COOLDOWN, DEFAULT_RANGE, DEFAULT_DAMAGE, DEFAULT_PROJECTILE_SPEED);
		
		slowAmount = 10;
		duration = 25;
		
	}
	

	/**
	 * creates a slowing projectile aimed at enemy e
	 * @param e: the enemy the projectile is aimed at
	 */
	@Override
	protected void createProjectile(Enemy e) {
		Board.getBoard().addActor(new SlowingProjectile(location, e, damage, projectileSpeed, slowAmount, duration));

	}

}
