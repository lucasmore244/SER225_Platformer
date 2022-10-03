package Engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import SpriteFont.SpriteFont;

public class DisplayTime {
	public Timer timer;
	public int second, minute;
	public SpriteFont displaytimer;

	public DisplayTime() {
		second = 0;
		minute = 0;
		counter();
		timer.start();
	}

	public void counter() {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				second++;
				if(second == 60) {
					second = 0;
					minute ++;
				}
			}
		});
	}

	public String getTime() {
		return (minute + ": " + second);
	}
}
