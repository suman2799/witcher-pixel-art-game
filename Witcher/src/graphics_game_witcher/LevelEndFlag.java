package graphics_game_witcher;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class LevelEndFlag implements VisibleObjects {

	Point location;
	Image[] frames;
	
	int currentFrameIndex;
	int animationTimer;
	
	public LevelEndFlag(Point l) {
		location = l;
		frames = loadFrames("/res/flag/flag_frame", ".png", 17);
		
		currentFrameIndex = 0;
		animationTimer = 0;
	}
	
	// Load the sprite animation frames from files or resources
	public Image[] loadFrames(String location, String extention, int n) {
		Image[] frames = new Image[n];
		
		// Load frames
		for (int i = 0; i < frames.length; i++) {
			frames[i] = new ImageIcon(getClass().getResource(location + (i+1) + extention)).getImage();
		}
		
		return frames;
    }
	
	// Create a timer to advance the animation frames
	public void flagAnimation() {
		if (animationTimer == 50) {
			// Increment the frame index
            currentFrameIndex++;
            if (currentFrameIndex >= frames.length) {
                currentFrameIndex = 0; // Loop back to the first frame
            }
            animationTimer = 0;
		}
		animationTimer += 10;
	}
	
	@Override
	public void display(Graphics g) {		
		if (frames != null && frames.length > 0) {
            Image currentFrame = frames[currentFrameIndex];
            g.drawImage(currentFrame, location.x, location.y, null);
		}
	}
}
