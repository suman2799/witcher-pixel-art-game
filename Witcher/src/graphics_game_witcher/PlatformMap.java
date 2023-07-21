package graphics_game_witcher;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class PlatformMap {

	Map<String, Image> platformMap = new HashMap<String, Image>();
	
	public PlatformMap() {	
		platformMap.put("platform1", new ImageIcon(getClass().getResource("/res/platforms/platform1.png")).getImage());
		platformMap.put("platform2", new ImageIcon(getClass().getResource("/res/platforms/platform2.png")).getImage());
		platformMap.put("platform3", new ImageIcon(getClass().getResource("/res/platforms/platform3.png")).getImage());
	}
	
	// Getter method to get Platform
	public Image getPlatform(String filename) {
		Image platform = platformMap.get(filename);
		return platform;
	}
}
