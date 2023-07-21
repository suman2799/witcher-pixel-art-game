package graphics_game_witcher;

import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class SoundMap {
	
	Map<String, AudioInputStream> streamMap = new HashMap<String, AudioInputStream>();
	
	public SoundMap() {
		try {
			streamMap.put("main_menu",AudioSystem.getAudioInputStream(getClass().getResource("/res/sounds/main_menu.wav")));
			streamMap.put("combat",AudioSystem.getAudioInputStream(getClass().getResource("/res/sounds/combat.wav")));
			streamMap.put("igni",AudioSystem.getAudioInputStream(getClass().getResource("/res/sounds/igni.wav")));
			streamMap.put("fireball",AudioSystem.getAudioInputStream(getClass().getResource("/res/sounds/fireball.wav")));
			streamMap.put("spell",AudioSystem.getAudioInputStream(getClass().getResource("/res/sounds/spell.wav")));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Getter method to get Audio Clip
	public AudioInputStream getSound(String filename) {
		AudioInputStream stream = streamMap.get(filename);
		return stream;
	}

}
