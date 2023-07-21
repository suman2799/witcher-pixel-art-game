package graphics_game_witcher;

import java.awt.Dimension;
import java.awt.Toolkit;

public class HeroJump {

	Hero heroObj = null;
	
	int x;
	int y;
	int jumpTimer;
	int animationTimer = 0;
	
	boolean stateJump;
	
	public HeroJump() {
		stateJump = false;
		jumpTimer = 150;
	}
	
	// Setter method to set hero object
	public void setHeroObj(Hero heroObj) {
		this.heroObj = heroObj;
	}
	
	// Setter method to set jump to right
	public void setJumpRight() {
		x = 10;
		y = -20;
	}
	
	// Setter method to set jump to left
	public void setJumpLeft() {
		x = -10;
		y = -20;
	}
	
	// Setter method to set jump state to true
	public void setJumpState() {
		jumpTimer = 150;
		stateJump = true;
	}
	
	// Function to calculate jump coordinates
	public void jump() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		// If hero reached left end, increase x coordinate to get in jump range
		if (heroObj.location.x == 0) {
			heroObj.location.x += 10;
		}
		
		// If jump timer not zero, and hero within window range
		if ((jumpTimer > 0) && ((heroObj.location.x + heroObj.getWidth()) < (int)d.getWidth()) && (heroObj.location.x > 0)) {
			// Jump diagonally
			heroObj.location.x += x;
			heroObj.location.y += y;
			
			jumpTimer -= 10;	// Jump timer
		} else {
			stateJump = false;	// Conditions fail, then set jump to false
		}
	}

}