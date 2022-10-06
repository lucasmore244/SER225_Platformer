package Engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.Timer;
import SpriteFont.SpriteFont;

public class DisplayTime {
	public Timer timer;
	public int second, minute;
	public SpriteFont displaytimer;
	public DecimalFormat dformat = new DecimalFormat("00");
	public String dsecond, dminute;
	public String defaulttimer;

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
				dsecond = dformat.format(second);
				dminute = dformat.format(minute);
				if (second == 60) {
					second = 0;
					minute++;
					dsecond = dformat.format(second);
					dminute = dformat.format(minute);
				}
			}
		});
	}

	public String getTime() {
		if(dminute == null || dsecond == null) {
			return "00:00";
		}	else {
				return (dminute + ":" + dsecond);		
			}
		
	}
}
