package graphics_game_witcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Health implements VisibleObjects{

	Point start;
	Point end;
	Image hud = null;
	boolean triangle = false;
	
	public Health(Point s, Point e) {
		start = s;
		end = e;
		hud = new ImageIcon(getClass().getResource("/res/hud/hud.png")).getImage();
	}
	
	// Getter function to get health end marker
	public int getHealth() {
		return end.x;
	}
	
	// Function to change health on attack
	public void setHealth() {
		if (triangle == true) {
			end.x -= 40;
		} else {
			triangle = true;
		}
	}
	
	@Override
	public void display(Graphics g) {
		int[] xPoints = {start.x + end.x, start.x + end.x, start.x + end.x + 30}; // X coordinates of the triangle's vertices
        int[] yPoints = {start.y, start.y + 30, start.y}; // Y coordinates of the triangle's vertices
        int nPoints = 3; // Number of vertices
        
		g.setColor(Color.RED);
		if (triangle != true) {
			g.fillPolygon(xPoints, yPoints, nPoints); // Draw the right-angled triangle
		}
        g.fillRect(start.x, start.y, end.x, end.y);
        g.drawImage(hud, 0, 0, 400, 200, null);
	}
}
