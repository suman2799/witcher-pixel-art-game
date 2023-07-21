package graphics_game_witcher;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {
	
	SoundMap audioPlayer = new SoundMap();
	Sound stream = null;
	
	Image background;
	
	JButton newGameBtn;
	JButton volumeBtn;
	JButton exitBtn;
	
	public MainMenu() {
		background = new ImageIcon(getClass().getResource("/res/backgrounds/main_menu.png")).getImage();
		initComponents();
	}
	
	// Getter function to get New Game Button
	public JButton getNewGameBtn() {
		return newGameBtn;
	}
	
	// Getter function to get Volume Button
	public JButton getVolumeBtn() {
		return volumeBtn;
	}
	
	// Getter function to get Quit Game Button
	public JButton getExitBtn() {
		return exitBtn;
	}
	
	// Getter function to get Main Menu Theme
	public Sound getStream() {
		return stream;
	}
	
	// Function to initialize different components
	private void initComponents() {
  
        // Create Required Buttons
		newGameBtn = new JButton("Start New Game");
		volumeBtn = new JButton("Volume");
		exitBtn = new JButton("Quit Game");
		
		// Set the layout manager to GridBagLayout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// Add the "Start New Game" button to the background
		gbc.gridy = 0;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.PAGE_END;
	    gbc.insets = new Insets(5, 5, 5, 5);
	    add(newGameBtn, gbc);
		
		// Add the "Volume" button to the background
	    gbc.gridy = 1;
	    gbc.weighty = 0;
	    gbc.insets = new Insets(5, 5, 5, 5);	    
	    add(volumeBtn, gbc);
		
		// Add the "Quit Game" button to the background
		gbc.gridy = 2;
	    gbc.weighty = 0;
	    gbc.insets = new Insets(5, 5, 150, 5);
	    add(exitBtn, gbc);
		
		// Add background theme to main menu
		stream = new Sound(audioPlayer.getSound("main_menu"));
		stream.play(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
}	