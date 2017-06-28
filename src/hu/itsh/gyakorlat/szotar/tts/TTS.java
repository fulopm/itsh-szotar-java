package hu.itsh.gyakorlat.szotar.tts;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import hu.itsh.gyakorlat.szotar.io.excel.Database;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Dictionary;

public class TTS {

	String word = "";
	
	public TTS(String s) {
		

		String voiceName = "kevin16";

		VoiceManager voiceManager = VoiceManager.getInstance();
		Voice helloVoice = voiceManager.getVoice(voiceName);
		if (helloVoice == null) {
			System.err.println("Cannot find a voice named " + voiceName + ".  Please specify a different voice.");

			System.exit(1);
		}
		helloVoice.allocate();

		helloVoice.speak(s);

		helloVoice.deallocate();

	}


	
}
