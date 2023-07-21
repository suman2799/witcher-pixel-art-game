package graphics_game_witcher;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

public class Spell implements VisibleObjects {

	Point location;
	Point rangeStart = new Point();
	Point rangeEnd = new Point();
	
	Image[] frames;
	
	int heroWidth;
	int currentFrameIndex;
	int animationTimer;
	boolean state;
	
	public Spell(Point location, int heroWidth, Image[] spell) {
		this.location = location;
		frames = spell;
		this.heroWidth = heroWidth;
		
		currentFrameIndex = 0;
		animationTimer = 0;
		
		state = false;
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
	
	// Find the range of spell attack
	public void findRange() {
		// Width and height of attack range
		int width = (currentFrameIndex+1)*30;
		int height = (currentFrameIndex+1)*25;
		
		// Starting range
		rangeStart.x = (location.x + heroWidth) + width;
		rangeStart.y = (location.y + 90) - (int)(height/3);
		
		// Ending range
		double rangeEndOffset = ((double)height*(2.0/3.0));
		rangeEnd.x = (location.x + heroWidth) + width;
		rangeEnd.y = (location.y + 90) + (int)rangeEndOffset;
	}
	
	// Spell animation
	public void animation() {
		if (animationTimer == 50) {
			// Increment the frame index
            currentFrameIndex++;
            // Find range of attack
            findRange();
            
            if (currentFrameIndex >= frames.length) {
                currentFrameIndex = 0; // Loop back to the first frame
                state = true;
            }
            animationTimer = 0;
		}
		animationTimer += 10;
	}
	
	@Override
	public void display(Graphics g) {
		if (frames != null && frames.length > 0) {
            Image currentFrame = frames[currentFrameIndex];
            g.drawImage(currentFrame, location.x + heroWidth, location.y, null);
            
            // Track range of attack
//          g.setColor(Color.GREEN);
//          g.fillOval(rangeStart.x, rangeStart.y, 3, 3); // Draw an oval shape
//    		g.fillOval(rangeEnd.x, rangeEnd.y, 3, 3);
        }
	}
	
}
