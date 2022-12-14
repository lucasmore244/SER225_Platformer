package MapEditor;

import Level.Map;
import Screens.PlayLevelScreen;
import Utils.Colors;
import javax.swing.*;
import Engine.Sound;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MusicPanel {
	private Map map;
	private JComboBox box;
	private static String[] music = { "Mona Lisa- Lil Wayne", "Space Cadet- Metro Boomin",
			"Space Cadet(INST)- Metro Boomin", "Last Last- Burna Boy", "Runaway- Kanye West", "Unknown Song",
			"Default Song" };
	private JDialog dialog;

	public MusicPanel(JFrame parent) {
		dialog = new JDialog();
		dialog.setResizable(false);
		dialog.setSize(300, 310);
		dialog.setTitle("Music Choice");
		dialog.setModal(true);
		dialog.setLocationRelativeTo(parent);
		JPanel labelPanel = new JPanel(null);
		dialog.setContentPane(labelPanel);
		labelPanel.setBackground(Colors.CORNFLOWER_BLUE);
		box = new JComboBox(music);
		box.setSelectedItem("Default Song");
		box.setLocation(50, 70);
		box.setSize(200, 50);
		box.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (box.getSelectedItem().equals("Mona Lisa- Lil Wayne")) {
						Sound.stopAll();
						Sound.playMusic(4);
					}
					if (box.getSelectedItem().equals("Space Cadet- Metro Boomin")) {
						Sound.stopAll();
						Sound.playMusic(1);
					}
					if (box.getSelectedItem().equals("Space Cadet(INST)- Metro Boomin")) {
						Sound.stopAll();
						Sound.playMusic(0);
					}
					if (box.getSelectedItem().equals("Last Last- Burna Boy")) {
						Sound.stopAll();
						Sound.playMusic(5);
					}
					if (box.getSelectedItem().equals("Runaway- Kanye West")) {
						Sound.stopAll();
						Sound.playMusic(11);
					}
					if (box.getSelectedItem().equals("Default Song")) {
						Sound.stopAll();
						Sound.playMusic(6);
					}
					if (box.getSelectedItem().equals("Unknown Song")) {
						Sound.stopAll();
						Sound.playMusic(13);
					}
				}
			};
		});
		labelPanel.add(box);
		// TODO Auto-generated constructor stub
	}

	public void show() {
		dialog.setVisible(true);
	}
}
