package graphics_game_witcher;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

public class Monster implements VisibleObjects {

	Point location;
	Image[] standingFrames;
	
	int monsterCode;
	int width;
	int height;
	
	int currentFrameIndex;
	int animationTimer;
	
	public Monster(Point l, Image[] m, int c) {
		location = l;
		standingFrames = m;
		
		monsterCode = c;
		width = standingFrames[0].getWidth(null);
		height = standingFrames[0].getHeight(null);
		
		currentFrameIndex = 0;
		animationTimer = 0;
	}
	
	// Getter function to get monster end X value
	public int getMonsterEndX() {
		return location.x + width;
	}
	
	// Getter function to get monster end Y value
	public int getMonsterEndY() {
		return location.y + height;
	}
	
	// Create a timer to advance the animation frames
	public void standingAnimation() {
		if (animationTimer == 100) {
			// Increment the frame index
            currentFrameIndex++;
            if (currentFrameIndex >= standingFrames.length) {
                currentFrameIndex = 0; // Loop back to the first frame
            }
            animationTimer = 0;
		}
		animationTimer += 10;
	}
	
	@Override
	public void display(Graphics g) {		
		if (standingFrames != null && standingFrames.length > 0) {
            Image currentFrame = standingFrames[currentFrameIndex];
            g.drawImage(currentFrame, location.x, location.y, null);
		}
	}
}