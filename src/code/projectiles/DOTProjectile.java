package code.projectiles;

import java.awt.Dimension;
import java.awt.Point;

import code.enemies.Enemy;
import code.game.Board;

/**
 * This projectile deals damage over time. 
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class DOTProjectile extends Projectile {

	
	public static final Dimension DEFAULT_SIZE = new Dimension(20, 10); 
	
	//duration of the DOT
	private int duration;
	
	
	/**
	 * Creates a new DOT projectile
	 * @param location: the location of the projectile when it is created
	 * @param target: the target the projectile tries to hit
	 * @param damage: the damage dealt to the enemy
	 * @param speed: the speed at which the projectile moves
	 * @param duration: the duration of the DOT in number of ticks
	 */
	public DOTProjectile(Point location, Enemy target, int damage, int speed, int duration){
		super(location, DEFAULT_SIZE, "DOTProjectile", target, damage, speed);
		this.duration = duration;
	}
	
	/**
	 * deals damage over time rather than the traditional flat damage
	 */
	public void collision(Enemy enemy){
		enemy.dealDamageOverTime(damage, duration);
		Board.getBoard().removeActor(this);
	}
	
}
