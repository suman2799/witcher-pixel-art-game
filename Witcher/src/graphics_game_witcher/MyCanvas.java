package graphics_game_witcher;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyCanvas extends JPanel{
	
	List<VisibleObjects> objects = new ArrayList<VisibleObjects>();
	SoundMap audioPlayer = new SoundMap();
	
	Image background;
	Sound stream;
	
	public MyCanvas() {
		background = new ImageIcon(getClass().getResource("/res/backgrounds/bg1.png")).getImage();
		stream = new Sound(audioPlayer.getSound("combat"));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
		for(VisibleObjects o : objects) 
			o.display(g);
	}
}
