package graphics_game_witcher;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

public class Platform implements VisibleObjects {

	Point location;
	Image platform;
	
	public Platform(Point l, Image p) {
		location = l;
		platform = p;
	}
	
	@Override
	public void display(Graphics g) {
		g.drawImage(platform, location.x, location.y, 50, 50, null);
	}
}