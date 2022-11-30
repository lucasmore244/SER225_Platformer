package Engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.*;

public class Sound {
	private static Clip[] clips = loadClips();
	private static AudioInputStream audio;
	
	public static Clip[] loadClips() {
		Clip[] clips = new Clip[15];
		try {
			audio = AudioSystem.getAudioInputStream(new File("./src/MetroBoominFullSong.wav"));
			clips[0] = AudioSystem.getClip();
			clips[0].open(audio);
			audio = AudioSystem.getAudioInputStream(new File("./src/spaceCadetLyrics.wav"));
			clips[1] = AudioSystem.getClip();
			clips[1].open(audio);
			audio = AudioSystem.getAudioInputStream(new File("./src/levelcompleted.wav"));
			clips[2] = AudioSystem.getClip();
			clips[2].open(audio);
			audio = AudioSystem.getAudioInputStream(new File("./src/levelose.wav"));
			clips[3] = AudioSystem.getClip();
			clips[3].open(audio);
			audio = AudioSystem.getAudioInputStream(new File("./src/MonaLisa.wav"));
			clips[4] = AudioSystem.getClip();
			clips[4].open(audio);
			audio = AudioSystem.getAudioInputStream(new File("./src/BurnaBoy.wav"));
			clips[5] = AudioSystem.getClip();
			clips[5].open(audio);
			audio = AudioSystem.getAudioInputStream(new File("./src/Intro Theme.wav"));
			clips[6] = AudioSystem.getClip();
			clips[6].open(audio);
			audio = AudioSystem.getAudioInputStream(new File("./src/CoinSound.wav"));
			clips[7] = AudioSystem.getClip();
			clips[7].open(audio);
			audio = AudioSystem.getAudioInputStream(new File("./src/JumpingSound.wav"));
			clips[8] = AudioSystem.getClip();
			clips[8].open(audio);
			audio = AudioSystem.getAudioInputStream(new File("./src/Hurt.wav"));
			clips[9] = AudioSystem.getClip();
			clips[9].open(audio);
			audio = AudioSystem.getAudioInputStream(new File("./src/CatHurt.wav"));
			clips[10] = AudioSystem.getClip();
			clips[10].open(audio);
			audio = AudioSystem.getAudioInputStream(new File("./src/Runaway- Kanye West.wav"));
			clips[11] = AudioSystem.getClip();
			clips[11].open(audio);
			audio = AudioSystem.getAudioInputStream(new File("./src/laser sound.wav"));
			clips[12] = AudioSystem.getClip();
			clips[12].open(audio);
			audio = AudioSystem.getAudioInputStream(new File("./src/Unknown Song.wav"));
			clips[13] = AudioSystem.getClip();
			clips[13].open(audio);
			audio = AudioSystem.getAudioInputStream(new File("./src/Fart.wav"));
			clips[14] = AudioSystem.getClip();
			clips[14].open(audio);
		}
		catch (Exception e) {
			System.out.println("Unable to load all sounds!");
			e.printStackTrace();
		}
		return clips;
	}

	public static void play(int i) {
		clips[i].start();
		clips[i].setFramePosition(0);
	}

	public static void loop(int i) {
		clips[i].loop(Clip.LOOP_CONTINUOUSLY);
	}

	public static void stop(int i) {
		clips[i].stop();
	}

	public static void stopAll() {
		for (int i = 0; i < clips.length; i++) {
			clips[i].stop();
		}
	}
	
	public static void playMusic(int i) {
		play(i);
		loop(i);
	}
}
