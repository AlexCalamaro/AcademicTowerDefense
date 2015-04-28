package code.projectiles;

import java.awt.Dimension;
import java.awt.Point;

import code.enemies.Enemy;
import code.game.Board;

/**
 * This projectile slows the enemies for a short time 
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class SlowingProjectile extends Projectile {

	
	public static final Dimension DEFAULT_SIZE = new Dimension(20, 10); 
	
	//the amount to slow
	int slowAmount;
	//the duration of the slow
	int duration;
	
	/**
	 * Creates a new Slowing projectile
	 * @param location: the location of the projectile when it is created
	 * @param target: the target the projectile tries to hit
	 * @param damage: the damage dealt to the enemy
	 * @param speed: the speed at which the projectile moves
	 * @param slowAmount: the amount the enemy will be slowed
	 * @param duration: the duration of the DOT in number of ticks
	 */
	public SlowingProjectile(Point location, Enemy target, int damage, int speed, int slowAmount, int duration){
		super(location, DEFAULT_SIZE, "SlowProjectile", target, damage, speed);
		this.slowAmount = slowAmount;
		this.duration =duration;
	}
	
	/**
	 * slows the enemy then deals damage
	 */
	public void collision(Enemy enemy){
		enemy.changeSpeed(-slowAmount, duration);
		enemy.dealDamage(damage);
		Board.getBoard().removeActor(this);
	}
}
