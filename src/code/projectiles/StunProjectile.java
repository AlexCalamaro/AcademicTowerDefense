package code.projectiles;

import java.awt.Dimension;
import java.awt.Point;

import code.enemies.Enemy;
import code.game.Board;

/**
 * This projectile stuns the enemies for a short time 
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class StunProjectile extends Projectile {

	public static final Dimension DEFAULT_SIZE = new Dimension(20, 10); 
	
	//the duration of the stun
	private int duration;
	
	/**
	 * Creates a new stun projectile
	 * @param location: the location of the projectile when it is created
	 * @param target: the target the projectile tries to hit
	 * @param damage: the damage dealt to the enemy
	 * @param speed: the speed at which the projectile moves
	 * @param duration: the duration of the stun in number of ticks
	 */
	public StunProjectile(Point location, Enemy target, int damage, int speed, int duration){
		super(location, DEFAULT_SIZE, "StunProjectile", target, damage, speed);
		this.duration = duration;
	}
	
	/**
	 * deals damage and then stuns the enemy
	 */
	public void collision(Enemy enemy){
		enemy.dealDamage(damage);
		enemy.stun(duration);
		Board.getBoard().removeActor(this);
	}
	
}
