package code.enemies;


import java.awt.Image;
import java.awt.Point;
import java.awt.Dimension;

import code.game.Actor;
import code.game.Board;
import code.game.ImageActor;
import code.game.Path;

/**
 * This is the abstract class extends the actor class and is used to create enemies. 
 * It holds a name, health, speed and rewarded money after being killed.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public abstract class Enemy extends ImageActor{
	
	protected String name;
	protected int originalHealth;
	protected int health;
	protected int originalSpeed;
	protected int speed;
	protected int originalArmor;
	protected int armor;
	protected int bounty;
	protected Boolean xaxis = false;
	protected Boolean yaxis = false;
	protected Boolean xright = false;
	protected Boolean ydown = false;
	protected int damageTicks=0;
	protected int damagePerTick=0;
	protected int stunTicks=0;
	protected int speedTicks=0;
	protected int speedChange=0;
	protected int armorTicks=0;
	protected int armorChange=0;
	
	/**
	 * constructor of the enemy class
	 * @param location enemy's spawning location
	 * @param size: enemy's size
	 * @param key: enemy's image path
	 * @param name: enemy's name
	 * @param health: enemy's current health
	 * @param speed: enemy's current speed
	 * @param armor: enemy's current armor
	 * @param bounty: enemy's rewards
	 * @param originalHealth: enemy's spawning health
	 * @param originalSpeed: enemy's spawning speed
	 * @param originalArmor: enemy's spawning armor
	 */
	
	public Enemy(Point location, Dimension size, String key, String name, int health, int speed, int armor, int bounty) {
		super(location, size, key);
		this.name = name;
		this.health = health;
		this.originalHealth = health;
		this.speed = speed;
		this.originalSpeed = speed;
		this.armor = armor;
		this.originalArmor = armor;
		this.bounty=bounty;
	} 
	
	/**
	 * a method that updates enemy's current status,
	 * whether in reducing speed, armor, stun or continuously damaged 
	 * and updates current location
	 */
	public void update()
	{
		if(damageTicks>0)
		{
			damageTicks--;
			this.dealDamage(damagePerTick);
		}
		if (speedTicks>0){
			speedTicks--;
			if(speedTicks==0){
				if (stunTicks>0){
					speed = 0;
				}
				else{
					speed=originalSpeed;
				}
			}
			else if (speedTicks>0){
				modifySpeed(speedChange);
			}
		}
		if (armorTicks>0){
			armorTicks--;
			if(armorTicks==0){
				armor=originalArmor;
			}
			else if (armorTicks>0){
				modifyArmor(armorChange);
			}
		}
		if(stunTicks>0)
		{
			stunTicks--;
			if(stunTicks==0)
			{
				speed=originalSpeed;
			}
		}
		
		Path path=Path.getPath();
		int curX = location.x;
		int curY= location.y;
		for(int i = 0; i < path.getPoint().length;){
			if (location.equals(path.getPoint()[0])){
				if (path.getPoint()[0].x != path.getPoint()[1].x){
					xaxis = true;
					if ((path.getPoint()[1].x - path.getPoint()[0].x) >0){
						xright = true;
					}
					else if((path.getPoint()[1].x - path.getPoint()[0].x) <0){
						xright = false;
					}
				}
				else if(path.getPoint()[0].y != path.getPoint()[1].y){
					yaxis = true;
					if ((path.getPoint()[1].y - path.getPoint()[0].y) >0){
						ydown = true;
					}
					else if((path.getPoint()[1].y - path.getPoint()[0].y) <0){
						ydown = false;
					}
				}
			}
			else if (location.equals(path.getPoint()[path.getPoint().length-1])){
				removeSelf();
			}
			else if ((curY <= path.getPoint()[i].y+originalSpeed)&&(curY>=path.getPoint()[i].y-originalSpeed)){
				if (yaxis == false && xaxis == false){
					xaxis = true;
				}
				if((path.getPoint()[i+1].x - curX) > 0 ){
					xright = true;
					if(((path.getPoint()[i+1].x - curX) > 0)&&((path.getPoint()[i+1].x-curX<=speed))){
						if (i+2 == path.getPoint().length){
							xright=true;
						}
						else if((path.getPoint()[i+2].y-path.getPoint()[i+1].y >0)){
							xaxis=false;
							yaxis=true;
							ydown=true;
						}
						else if((path.getPoint()[i+2].y-path.getPoint()[i+1].y <0)){
							xaxis=false;
							yaxis=true;
							ydown=false;
						}
					}
				}
				else if ((path.getPoint()[i+1].x - curX) < 0 ){
					xright = false;
					if(((path.getPoint()[i+1].x - curX) < 0)&&((path.getPoint()[i+1].x-curX>=(-speed)))){
						if (i+2 == path.getPoint().length){
							xright=false;
						}
						else if((path.getPoint()[i+2].y-path.getPoint()[i+1].y >0)){
							xaxis=false;
							yaxis=true;
							ydown=true;
						}
						else if((path.getPoint()[i+2].y-path.getPoint()[i+1].y <0)){
							xaxis=false;
							yaxis=true;
							ydown=false;
						}
					}
				}
				break;
			}
			else if (curX <= (path.getPoint()[i].x+originalSpeed)&&(curX>=(path.getPoint()[i].x-originalSpeed))){
				if (yaxis == false && xaxis == false){
					yaxis = true;
				}
				if((path.getPoint()[i+1].y - curY) > 0 ){
					ydown = true;
					if(((path.getPoint()[i+1].y - curY) > 0)&&((path.getPoint()[i+1].y-curY<=speed))){
						if (i+2 == path.getPoint().length){
							ydown=true;
						}
						else if((path.getPoint()[i+2].x-path.getPoint()[i+1].x >0)){
							yaxis=false;
							xaxis=true;
							xright=true;
						}
						else if((path.getPoint()[i+2].x-path.getPoint()[i+1].x <0)){
							yaxis=false;
							xaxis=true;
							xright=false;
						}
					}
				}
				else if((path.getPoint()[i+1].y - curY) < 0 ){
					ydown = false;
					if(((path.getPoint()[i+1].y - curY) < 0)&&((path.getPoint()[i+1].y-curY>=(-speed)))){
						if (i+2 == path.getPoint().length){
							ydown=false;
						}
						else if((path.getPoint()[i+2].x-path.getPoint()[i+1].x >0)){
							yaxis=false;
							xaxis=true;
							xright=true;
						}
						else if((path.getPoint()[i+2].x-path.getPoint()[i+1].x <0)){
							yaxis=false;
							xaxis=true;
							xright=false;
						}
					}
				}
				
				break;
			}
			
			i++;
		}
		
		if (xaxis){
			if (xright){
				location.x += speed;
			}
			else{
				location.x -= speed;
			}
		}
		else if(yaxis){
			if (ydown){
				location.y += speed;
			}
			else{
				location.y -= speed;
			}
		}
	}
	
	/**
	 * this method is called by projectile when collide
	 * it takes in two parameters used to change enemy's speed
	 * @param changeFactor: the amount to reduce speed, negative
	 * @param numTicks: the lasting time of this effect 
	 */
	public void changeSpeed(int changeFactor, int numTicks){
		speedTicks= numTicks;
		speedChange=changeFactor;
	}
	
	/**
	 * this method is called by the update method
	 * to update enemy's current speed
	 * @param factor: the amount to reduce speed, negative
	 */
	public void modifySpeed(int factor){
		speed += factor;
		if (speed <1){
			speed = 1;
		}
	}
	
	/**
	 * this method is called by projectile when collide
	 * it takes in two parameters used to change enemy's armor
	 * @param changeArmor: the amount to reduce armor, positive
	 * @param numTicks: the lasting time of this effect
	 */
	public void reduceArmor(int changeArmor, int numTicks){
		armorTicks = numTicks;
		armorChange = changeArmor;
	}
	
	/**
	 * this method is called by the update method
	 * to update enemy's current armor
	 * @param factor: the amount to reduce armor, positive
	 */
	public void modifyArmor(int factor){
		armor-= armor;
		if (armor <0){
			armor =0;
		}
	}
	
	/**
	 * this method is called by projectiles when collide
	 * it takes in the time to stun enemy(reduce speed to zero)
	 * @param factor: the lasting time of this effect
	 */
	public void stun(int numTicks){
		this.stunTicks=numTicks;
		speed=0;
	}
	
	/**
	 * this method is called by projectiles when collide
	 * it takes in two parameters used to deal damage per tick
	 * @param damagePerTick: amount of damage per tick, positive
	 * @param numTick: the lasting time of this effect
	 */
	public void dealDamageOverTime(int damagePerTick, int numTicks){
		damageTicks+=numTicks;
		this.damagePerTick=damagePerTick;
	}
	
	/**
	 * this method is called by projectiles when collide
	 * it takes in the damage needed to be dealt on enemy
	 * it calls the removeSelf method when the enemy dies
	 * and calls the board to increase money
	 * @param damage: amount of damage, positive
	 */
	public void dealDamage(int damage){
		health -= (damage-armor);
		if (health <= 0){
			removeSelf();
			Board.getBoard().increaseMoney(bounty);
		}
	}
	
	/**
	 * this method calls the board to remove this enemy object
	 */
	public void removeSelf(){
		Board.getBoard().removeActor(this);
	}

}
