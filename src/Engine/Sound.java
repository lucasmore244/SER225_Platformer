package Engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[15];
	File file[] = new File[15];

	public Sound() {
		file[0] = new File("./src/MetroBoominFullSong.wav");
		file[1] = new File("./src/spaceCadetLyrics.wav");
		file[2] = new File("./src/levelcompleted.wav");
		file[3] = new File("./src/levelose.wav");
		file[4] = new File("./src/MonaLisa.wav");
		file[5] = new File("./src/BurnaBoy.wav");
		file[6] = new File("./src/Intro Theme.wav");
		file[7] = new File("./src/CoinSound.wav");
		file[8] = new File("./src/JumpingSound.wav");
		file[9] = new File("./src/Hurt.wav");
		file[10] = new File("./src/CatHurt.wav");
		file[11] = new File("./src/Runaway- Kanye West.wav");
		file[12] = new File("./src/laser sound.wav");
		file[13] = new File("./src/Unknown Song.wav");
	}

	public void setFile(int i) {
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(file[i]);
			clip = AudioSystem.getClip();
			clip.open(audio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {
		clip.start();
	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		if (clip == null) {
		} else {
			clip.stop();
		}
	}
}
