package hu.itsh.gyakorlat.szotar.tts;

import com.sun.speech.freetts.*;

import hu.itsh.gyakorlat.szotar.io.excel.Database;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Dictionary;

public class TTS {

	String word = "";
	
	public TTS(String s) {
		

		String voiceName = "kevin16";

		VoiceManager voiceManager = VoiceManager.getInstance();
		Voice helloVoice = voiceManager.getVoice(voiceName);
		Gender voiceGender = Gender.FEMALE;
		if (helloVoice == null) {
			System.err.println("Cannot find a voice named " + voiceName + ".  Please specify a different voice.");

			System.exit(1);
		}
		helloVoice.allocate();
		helloVoice.setRate(100);
		

		helloVoice.speak(s);

		helloVoice.deallocate();

	}


	
}
