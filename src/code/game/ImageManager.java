package code.game;

import java.awt.Image;
import java.util.HashMap;

import code.gui.TowerDefenseGUI;
/**
 * ImageManager is a singleton that holds a HashMap of arbitrary keys to images.
 * 
 * The ImageManager is a class that exists to avoid the following issue:
 * If each individual class holds a seperate instance of their image, the game ends up having to load
 * the same image many times. Because image is a field in Actor and actor has many subclasses, making it 
 * static would not work, as each class requires it to hold a seperate value.
 * 
 * To solve this problem, we created an ImageManager who loads in and holds virtually all the images
 * used by the game. Then, each Actor subclass picks among these images and holds only a pointer to the real image.
 * 
 * This solved the massive lag issues caused by the previous implementation.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class ImageManager 
{
	private static ImageManager manager;
	private HashMap<String, Image> map;
	
	/**
	 * ImageManagers constructor just basically loads in all the images and pots them in the map. 
	 */
	private ImageManager()
	{
		map=new HashMap<String, Image>();
		map.put("basicEnemy", TowerDefenseGUI.loadImage("student.jpg"));
		map.put("regenerateStudent", TowerDefenseGUI.loadImage("regenEnemy.jpg"));
		map.put("fastStudent", TowerDefenseGUI.loadImage("fastEnemy.jpg"));
		map.put("bossStudent", TowerDefenseGUI.loadImage("bossEnemy.jpg"));
		map.put("armorStudent", TowerDefenseGUI.loadImage("armorEnemy.jpg"));
		map.put("basicTower", TowerDefenseGUI.loadImage("BasicTower.jpg"));
		map.put("AOETower", TowerDefenseGUI.loadImage("AOETower.jpg"));
		map.put("DOTTower", TowerDefenseGUI.loadImage("DOTTower.jpg"));
		map.put("ModTower", TowerDefenseGUI.loadImage("ModTower.jpg"));
		map.put("SlowingTower", TowerDefenseGUI.loadImage("SlowingTower.jpg"));
		map.put("StunTower", TowerDefenseGUI.loadImage("StunTower.jpg"));
		map.put("SuperTower", TowerDefenseGUI.loadImage("SuperTower.jpg") );
		map.put("Path", TowerDefenseGUI.loadImage("Tile.jpg"));
		map.put("plainRuler", TowerDefenseGUI.loadImage("plainRuler.jpg") );
		map.put("AOEProjectile", TowerDefenseGUI.loadImage("AOEProjectile.jpg") );
		map.put("DOTProjectile", TowerDefenseGUI.loadImage("DOTProjectile.jpg") );
		map.put("ModProjectile", TowerDefenseGUI.loadImage("ModProjectile.jpg"));
		map.put("SlowProjectile", TowerDefenseGUI.loadImage("SlowProjectile.jpg"));
		map.put("StunProjectile", TowerDefenseGUI.loadImage("StunProjectile.jpg"));
		map.put("Goal", TowerDefenseGUI.loadImage("graduation-cap.jpg"));
		map.put("fastEnemy", TowerDefenseGUI.loadImage("fastEnemy.jpg"));
	}
	/**
	 * Gets the ImageManager as specificed by the singleton design pattern. 
	 * Employs lazy initialization.
	 * @return The one ImageManager
	 */
	public static ImageManager getManager()
	{
		if(manager==null)
			manager=new ImageManager();
		return manager;
	}
	/**
	 * Gets the image assoicated with the given key and scales it appropaitely
	 * @param key The key associated with the desired image
	 * @param desiredX The width you want the image
	 * @param desiredY The height you want the image
	 * @return the scaled image assoicated with the given key
	 */
	public Image getImage(String key, int desiredX, int desiredY)
	{
		return map.get(key).getScaledInstance(desiredX, desiredY, Image.SCALE_SMOOTH);
	}
}
