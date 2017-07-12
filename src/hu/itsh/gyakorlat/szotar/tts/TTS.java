package hu.itsh.gyakorlat.szotar.tts;

import com.sun.speech.freetts.*;

import hu.itsh.gyakorlat.szotar.dictionaries.Database;
import hu.itsh.gyakorlat.szotar.dictionaries.Dictionary;

public class TTS {

	private static final String VOICE_NAME = "kevin16";
	private VoiceManager voiceManager;
	private Voice voice;
	
	public TTS() {
		voiceManager = VoiceManager.getInstance();
		voice = voiceManager.getVoice(VOICE_NAME);
		if (voice == null) {
			System.err.println("Cannot find a voice named " + VOICE_NAME + ".  Please specify a different voice.");

			System.exit(1);
		}
		voice.allocate();
		voice.setRate(100);
		

	}
	
	public void speak(String string) {
		voice.speak(string);
	}

	
	public void deallocate() {
		voice.deallocate();
	}
	
	public boolean isValid() {
		return voice.isLoaded();
	}

	
}
