package graphics_game_witcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PauseMenu extends JPanel {
	
	private static final int ALPHA = 125; // how much see-thru. 0 to 255
    private static final Color GP_BG = new Color(0, 0, 0, ALPHA);
	
    JButton resumeBtn;
	JButton exitBtn;
	JButton volumeBtn;
	
	boolean pauseMenuState = false;
	
	public PauseMenu() {
        setOpaque(false); 
        setBackground(GP_BG); 
		initComponents();
	}
	
	// Getter function to get Resume Button
	public JButton getResumeBtn() {
		return resumeBtn;
	}
	
	// Getter function to get Volume Button
	public JButton getVolumeBtn() {
		return volumeBtn;
	}
	
	// Getter function to get Exit Button
	public JButton getExitBtn() {
		return exitBtn;
	}
	
	// Initialize Components
	private void initComponents() {
  
        // Create Required Buttons
		resumeBtn = new JButton("Resume");
		volumeBtn = new JButton("Volume");
		exitBtn = new JButton("Quit Game");
		
		JLabel pausedLabel = new JLabel("PAUSED");
        pausedLabel.setForeground(Color.ORANGE);
        JPanel pausedPanel = new JPanel();
        pausedPanel.setOpaque(false);
        pausedPanel.add(pausedLabel);
       
        // Set the layout manager to GridBagLayout
 		setLayout(new GridBagLayout());
 		GridBagConstraints gbc = new GridBagConstraints();
 		gbc.gridwidth = GridBagConstraints.REMAINDER;
 		gbc.fill = GridBagConstraints.HORIZONTAL;
        
 		// Add the "PAUSED" label
 		gbc.gridy = 0;
		gbc.weighty = 1;
	    gbc.insets = new Insets(5, 5, 5, 5);
        add(pausedPanel);
        
        // Add the "Start New Game" button to the background
 		gbc.gridy = 1;
 		gbc.weighty = 0;
 	    gbc.insets = new Insets(5, 5, 5, 5);
 	    add(resumeBtn, gbc);
        
        // Add the "Volume" button to the background
	    gbc.gridy = 2;
	    gbc.weighty = 0;
	    gbc.insets = new Insets(5, 5, 5, 5);	    
	    add(volumeBtn, gbc);
		
		// Add the "Quit Game" button to the background panel
        gbc.gridy = 3;
	    gbc.weighty = 0;
	    gbc.insets = new Insets(5, 5, 5, 5);
		add(exitBtn, gbc);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// Magic to make it dark without side-effects
		super.paintComponent(g);
		g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
	}
	
}	