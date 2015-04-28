package code.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import code.game.GameEngine;

/**
 * The PausePlayButton is a custom JButton that changes from a pause symbol to a play symbol
 * each time the user clicks it. It also will eventually starts the game or stops it when the user
 * clicks the button by calling the setStatus method of Game.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version May 26, 2014
 */
public class PausePlayButton extends JButton 
{
	private boolean isPlaying;
	private ImageIcon playImage;
	private ImageIcon pauseImage;

	/**
	 * Sets up the pause/play button using an anonymous action listener that changes the
	 * internal field isPlaying and the image.
	 */
	public PausePlayButton(boolean startState)
	{
		playImage=new ImageIcon("PlayButton.jpg");
		pauseImage=new ImageIcon("PauseButton.jpg");
		this.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				isPlaying=!isPlaying;
				if(!isPlaying)
				{
					setIcon(playImage);
					GameEngine.getEngine().stop();
				}
				else
				{
					setIcon(pauseImage);
					GameEngine.getEngine().start();
				}
			}
		});
		if(startState==false)
		{
			this.setIcon(playImage);
			GameEngine.getEngine().stop();
		}
		else
		{
			this.setIcon(pauseImage);
			GameEngine.getEngine().start();
		}
		isPlaying=startState;
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(50,50));
	}


}
