package graphics_game_witcher;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

public class Sound {
	
	AudioInputStream stream;
	Clip audioClip;
	FloatControl volumeControl;
	
	public Sound(AudioInputStream stream) {
		this.stream = stream;
		
		try {
			audioClip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		volumeControl = null;
	}
	
	// Function to stop playing audio clip
	public void stop() {
		if (audioClip != null && audioClip.isRunning()) {
			audioClip.stop();
            audioClip.close();
        }
	}
	
	// Setter function to set volume of audio clip
	public void setVolume(int volume) {
        if (volumeControl != null) {
            volumeControl.setValue(mapVolumeIntToFloat(volume));
        }
    }
	
	// Getter function to get current volume of audio clip
	public int getVolume() {
		float volume = volumeControl.getValue();
	    float minVolume = -80.0f;
	    float maxVolume = 6.0206f;
	    float mappedMin = 0;
	    float mappedMax = 100;

	    // Perform the linear mapping
	    float mappedValue = ((volume - minVolume) / (maxVolume - minVolume)) * (mappedMax - mappedMin) + mappedMin;

	    // Convert the float value to int
	    int mappedVolume = (int) mappedValue;

	    return mappedVolume;
	}
	
	// Mapper function to get volume in int
	public float mapVolumeIntToFloat(int volume) {
	    float minVolume = -80.0f;
	    float maxVolume = 6.0206f;
	    float mappedMin = 0;
	    float mappedMax = 100;

	    // Perform the inverse linear mapping
	    float mappedValue = ((volume - mappedMin) / (mappedMax - mappedMin)) * (maxVolume - minVolume) + minVolume;

	    return mappedValue;
	}
	
	// Function to play audio clip
	public void play(boolean loop) {
		try {
			// If clip not opened, open clip
			if (!audioClip.isOpen()) {
				audioClip.open(stream);
			} else {
				// If clip opened and clip is running, stop clip
				if (audioClip.isRunning()) {
					audioClip.stop();
				}
			}
			// Set clip start position to 0
			audioClip.setFramePosition(0);
			
			volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            // Set initial volume (between -80.0 to 6.0206)
            volumeControl.setValue(-19.0f);
            
            // If audio clip needs to loop
			if(loop)
				audioClip.loop(Clip.LOOP_CONTINUOUSLY);
			else
				audioClip.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
