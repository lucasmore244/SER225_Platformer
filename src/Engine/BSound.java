package Engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class BSound {
	
	private static Clip clip;
	private static File file[] = new File[15];
	private static AudioInputStream audio;

	public BSound() {
		file[0] = new File("./src/levelcompleted.wav");
		file[1] = new File("./src/levelose.wav");
		file[2] = new File("./src/CoinSound.wav");
		file[3] = new File("./src/JumpingSound.wav");
		file[4] = new File("./src/Hurt.wav");
		file[5] = new File("./src/CatHurt.wav");
		file[6] = new File("./src/laser sound.wav");
		file[7] = new File("./src/Fart.wav");	
	}
	
	public static void setFile(int i) {
		try {
			System.out.println(file[i]);
			audio = AudioSystem.getAudioInputStream(file[i]);
			clip = AudioSystem.getClip();
			clip.open(audio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void play() {
		clip.start();
	}


	public static void stop() {
		if (clip == null) {
			System.out.println("sound null");
		} else {
			clip.stop();
			System.out.println("Testi");
		}
	}

	public static void playSE(int i) {
		setFile(i);
		play();
	}
	
}


