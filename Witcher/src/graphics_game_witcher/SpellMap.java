package graphics_game_witcher;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class SpellMap {

	Map<String, Image[]> spellMap = new HashMap<String, Image[]>();
	
	public SpellMap() {	
		
		spellMap.put("igni", loadFrames("/res/spells/igni/igni_frame", ".png", 7));
	}
	
	// Load the sprite animation frames from files or resources
	public Image[] loadFrames(String location, String extention, int n) {
		Image[] frames = new Image[n];
		
		// Load seven frames
		for (int i = 0; i < frames.length; i++) {
			frames[i] = new ImageIcon(getClass().getResource(location + (i+1) + extention)).getImage();
		}
		
		return frames;
    }
	
	// Getter function to get Spell
	public Image[] getSpell(String filename) {
		Image[] spell = spellMap.get(filename);
		return spell;
	}
}
