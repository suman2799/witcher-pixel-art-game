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
public class GameOver extends JPanel {
	
	Image background;
	
	JButton exitBtn;
	
	public GameOver() {
		background = new ImageIcon(getClass().getResource("/res/backgrounds/end_game.png")).getImage();
		initComponents();
	}
	
	// Getter function to get exit button
	public JButton getExitBtn() {
		return exitBtn;
	}
	
	// Initialize the Components
	private void initComponents() {
  
        // Create Required Buttons
		exitBtn = new JButton("Quit Game");
		
		// Set the layout manager to GridBagLayout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// Add the "Quit Game" button to the background panel
		add(exitBtn, gbc);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
}	