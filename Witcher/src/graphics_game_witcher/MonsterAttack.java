package graphics_game_witcher;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

public class MonsterAttack implements VisibleObjects{

	Point location;
	Point rangeStart = new Point();
	Point rangeEnd = new Point();
	
	Image[] attackFrames;
	
	int width;
	int height;
	int currentFrameIndex;
	int animationTimer;
	
	public MonsterAttack(Point l, Image[] m) {
		location = l;
		attackFrames = m;
		
		currentFrameIndex = 0;
		animationTimer = 0;
		
		width = attackFrames[0].getWidth(null);
		height = attackFrames[0].getHeight(null);
	}
	
	// Getter function to get attack range starting X position
	public int getRangeStartX() {
		return rangeStart.x;
	}
	
	// Getter function to get attack range starting Y position
	public int getRangeStartY() {
		return rangeStart.y;
	}
	
	// Getter function to get attack range ending X position
	public int getRangeEndX() {
		return rangeEnd.x;
	}
	
	// Getter function to get attack range starting Y position
	public int getRangeEndY() {
		return rangeEnd.y;
	}
	
	// Getter function to get Attack Width
	public int getWidth() {
		return width;
	}
	
	// Getter function to get Attack Height
	public int getHeight() {
		return height;
	}
	
	// Find the range of spell attack
	public void findRange() {
		// Starting range
		rangeStart.x = location.x + 14;
		rangeStart.y = location.y + 82;
		
		// Ending range
		rangeEnd.x = location.x + 14;
		rangeEnd.y = location.y + 113;
	}
	
	// Create a timer to advance the animation frames
	public void attackAnimation() {
		if (animationTimer == 50) {
			// Increment the frame index
            currentFrameIndex++;
            // Find range of attack
            findRange();
            
            if (currentFrameIndex >= attackFrames.length) {
                currentFrameIndex = 0; // Loop back to the first frame
            } 
            // Move forward the Attack
            if ((location.x + width) > 0) {
    			location.x -= 20;
    		}            
            animationTimer = 0;	// Reset Timer
		}
		animationTimer += 10;
	}
	
	@Override
	public void display(Graphics g) {		
		if (attackFrames != null && attackFrames.length > 0) {
            Image currentFrame = attackFrames[currentFrameIndex];
            g.drawImage(currentFrame, location.x, location.y, null);
		}
		// Test location of feet start and end
//		g.setColor(Color.RED);
//		g.fillOval(location.x, location.y + 97, 3, 3); // Draw an oval shape
//		g.fillOval(location.x + 14, location.y + 113, 3, 3);
//		g.fillOval(location.x + 14, location.y + 82, 3, 3);
	}
}
