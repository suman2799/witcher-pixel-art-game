package graphics_game_witcher;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class MonsterAttackMap {

	Map<String, Image[]> monsterAttackMap = new HashMap<String, Image[]>();
	
	public MonsterAttackMap() {	
		monsterAttackMap.put("leshyAttack", loadAttackFrames("/res/spells/leshy/leshy_attack_frame", ".png", 5));
		monsterAttackMap.put("witchAttack", loadAttackFrames("/res/spells/witch/witch_attack_frame", ".png", 9));
	}
	
	// Load the attack sprite animation frames from files or resources
	public Image[] loadAttackFrames(String location, String extention, int n) {
		Image[] attackFrames = new Image[n];
		
		// Load seven frames
		for (int i = 0; i < attackFrames.length; i++) {
			attackFrames[i] = new ImageIcon(getClass().getResource(location + (i+1) + extention)).getImage();
		}
		
		return attackFrames;
    }
	
	// Getter method to get Monster Attack for a Monster
	public Image[] getMonsterAttack(String filename) {
		Image[] monsterAttack = monsterAttackMap.get(filename);
		return monsterAttack;
	}
}