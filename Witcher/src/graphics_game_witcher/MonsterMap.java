package graphics_game_witcher;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class MonsterMap {

	Map<String, Image[]> monsterMap = new HashMap<String, Image[]>();
	
	public MonsterMap() {	
		monsterMap.put("fiend", loadStandingFrames("/res/characters/fiend/fiend_standing_frame", ".png", 7));
		monsterMap.put("leshy", loadStandingFrames("/res/characters/leshy/leshy_standing_frame", ".png", 7));
		monsterMap.put("witch", loadStandingFrames("/res/characters/witch/witch_standing_frame", ".png", 7));
	}
	
	// Load the standing sprite animation frames from files or resources
	public Image[] loadStandingFrames(String location, String extention, int n) {
		Image[] standingFrames = new Image[n];
		
		// Load seven frames
		for (int i = 0; i < standingFrames.length; i++) {
			standingFrames[i] = new ImageIcon(getClass().getResource(location + (i+1) + extention)).getImage();
		}
		
		return standingFrames;
    }
	
	// Getter method to get Monster
	public Image[] getMonster(String filename) {
		Image[] monster = monsterMap.get(filename);
		return monster;
	}
}
