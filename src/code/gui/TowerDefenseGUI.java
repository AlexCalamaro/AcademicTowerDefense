package code.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import code.game.Board;
import code.game.GameEngine;

/**
 * TowerDefenseGui is the Jframe that represents one complete copy of the entire game.
 * 
 * Creating one pops up the game window and creating multiple creates multiple completely seperate
 * games. Hence it is not a singleton.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class TowerDefenseGUI extends JFrame
{
	/**
	 * Sets up the game to have a startscreen and makes it visible.
	 */
	public TowerDefenseGUI() 
	{
		super("Road to Graduation");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		this.switchToScreen(new StartScreen(this));
	}
	/**
	 * Switches the game to display an entirely different Screen in it's content pane.
	 * This method is used to switch from the StartSreen to the GameScreen for example.
	 * @param screen The Jpanel to swtich to.
	 */
	public void switchToScreen(JPanel screen)
	{
		this.getContentPane().removeAll();
		this.getContentPane().add(screen);
		this.pack();
		this.setVisible(true);
		this.repaint();
	}
	/**
	 * Creates an instance of the game.
	 * @param args Command line arguments, does nothing at present.
	 */
	public static void main(String[] args)
	{
		TowerDefenseGUI gui=new TowerDefenseGUI();
	}
/**
 * Loads the given Image into the game.
 * @param path The files sytem location of the desired Image.
 * @return An image typer object containing the image at the given file location.
 */
	public static Image loadImage(String path)
	{
		Image image=null;
		try
		{
			image=ImageIO.read(new File(path));
		}
		catch(IOException e)
		{
			System.out.println("Fatal Error: Failed to load image: "+path);
			System.exit(1);
		}
		return image;
	}
	/**
	 * Loads the given image, scales it and returns it.
	 * @param path The path to the file that contains the image
	 * @param desiredX The desired X size of the image in pixels.
	 * @param desiredY The desired Y size of the image in pixels.  
	 * @return A scaled Image object holding the image located at the given path.
	 */
	public static Image loadImage(String path, int desiredX, int desiredY)
	{
		return loadImage(path).getScaledInstance(desiredX, desiredY, Image.SCALE_SMOOTH);
	}
	
	
}
