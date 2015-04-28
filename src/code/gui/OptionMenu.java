package code.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import code.game.Board;
import code.wave.WaveManager;

/**
 * OptionMenu is the panel that gives the user options related to game settings, such as pause, play
 * and starting the next wave. Volume, if we decide to implement sound, would also go here.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version May 26, 2014
 *
 */
public class OptionMenu extends JPanel 
{
	public static final boolean PLAY_PAUSE_BUTTON_START_STATE=true;

	/**
	 * Parameterless constructor for an OptionMenu, which displays the game-start/pause-play buttons.
	 * It uses FlowLayout for simple layout management and can start each level of the game
	 */
	public OptionMenu()
	{
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(250, 200));
		this.setLayout(new FlowLayout(0,0,75));
		
		PausePlayButton pauseButton = new PausePlayButton(PLAY_PAUSE_BUTTON_START_STATE);
		this.add(pauseButton);
		
		JButton startLevelButton = new JButton(new ImageIcon("StartLevelButton.jpg"));
		startLevelButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				WaveManager.getWaveManager().startWave();
			}
		});
		startLevelButton.setBackground(Color.WHITE);
		startLevelButton.setPreferredSize(new Dimension(181,50));
		this.add(startLevelButton);
		
		
	}

}
