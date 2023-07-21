package graphics_game_witcher;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Hero implements VisibleObjects {

	Point location;
	int feetStartX;
	int feetStartY;
	int feetEndX;
	int feetEndY;
	
	Image[] standingFrames = null;
	Image[] igniFrames = null;
	
	int width;
	int height;
	
	int currentFrameIndex = 0;
	int igniCurrentFrameIndex = 0;
	int animationTimer = 0;
	int igniAnimationTimer = 0;
	
	boolean stateIgni = false;
	
	public Hero(Point l) {
		this.location = l;
		
		standingFrames = loadFrames("/res/hero/standing/hero_standing_frame", ".png", 7);
		igniFrames = loadFrames("/res/hero/igni/hero_igni_frame", ".png", 7);
		
		width = standingFrames[0].getWidth(null);
		height = standingFrames[0].getHeight(null);
	}
	
	// Getter function to get feet starting X position
	public int getFeetStartX() {
		feetStartX = location.x + 5;
		return feetStartX;
	}
	
	// Getter function to get feet starting Y position
	public int getFeetStartY() {
		feetStartY = location.y + 175;
		return feetStartY;
	}
	
	// Getter function to get feet ending X position
	public int getFeetEndX() {
		feetEndX = location.x + 115;
		return feetEndX;
	}
	
	// Getter function to get feet ending Y position
	public int getFeetEndY() {
		feetEndY = location.y + 175;
		return feetEndY;
	}
	
	// Getter function to get hero width
	public int getWidth() {
		return width;
	}
	
	// Getter function to get hero height
	public int getHeight() {
		return height;
	}
	
	// Create a integer timer to advance the standing animation frames
	public void standingAnimation() {
		if (animationTimer == 100) {
			// Increment the frame index
            currentFrameIndex++;
            if (currentFrameIndex >= standingFrames.length) {
                currentFrameIndex = 0; // Loop back to the first frame
            }
            animationTimer = 0;	// Reset timer
		}
		animationTimer += 10;	// Since game timer is 10milisecond, increase by 10
	}
	
	// Create a integer timer to advance the igni animation frames
	public void igniAnimation() {		
		if (igniAnimationTimer == 50) {
			// Increment the frame index
            igniCurrentFrameIndex++;
            if (igniCurrentFrameIndex >= igniFrames.length) {
                igniCurrentFrameIndex = 0; // Loop back to the first frame
                stateIgni = false;
            }
            igniAnimationTimer = 0;	// Reset timer
		}
		igniAnimationTimer += 10;	// Since game timer is 10milisecond, increase by 10
	}
	
	
	// Load the standing sprite animation frames from files or resources
	public Image[] loadFrames(String location, String extention, int n) {
		Image[] frames = new Image[n];
		
		// Load seven frames
		for (int i = 0; i < frames.length; i++) {
			frames[i] = new ImageIcon(getClass().getResource(location + (i+1) + extention)).getImage();
		}
		
		return frames;
	}
	
	@Override
	public void display(Graphics g) {
		// If state of igni attack == true, display this sprite
		if (igniFrames != null && igniFrames.length > 0 && stateIgni == true) {
			Image currentFrame = igniFrames[igniCurrentFrameIndex];
            g.drawImage(currentFrame, location.x-90, location.y, null);
		}
		// If state of igni attack == false, display this standing sprite
		if (standingFrames != null && standingFrames.length > 0 && stateIgni == false) {
            Image currentFrame = standingFrames[currentFrameIndex];
            g.drawImage(currentFrame, location.x, location.y, null);
        }
		// Test location of feet start and end
//		g.setColor(Color.RED);
//		g.fillOval(location.x + 5, location.y+175, 3, 3); // Draw an oval shape
//		g.fillOval(location.x + width, location.y + height, 3, 3);
	}
	
}