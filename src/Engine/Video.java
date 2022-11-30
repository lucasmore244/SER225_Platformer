package Engine;

//import javax
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class Video {
	JPanel contentPane;
	private JLabel imageLabel = new JLabel();
//	private JLabel headerLabel = new JLabel();
	private JDialog dialog;

	public Video(JFrame parent) {
		
		try {
			dialog = new JDialog();
			dialog.setResizable(false);
			dialog.setSize(800, 605);
			dialog.setModal(true);
			dialog.setLocationRelativeTo(parent);
			JPanel contentPane = new JPanel(null);
			dialog.setContentPane(contentPane);
			dialog.setLayout(new BorderLayout());
			contentPane.setBackground(Color.BLACK);
			// add the image label
			ImageIcon ii = new ImageIcon(
					new ImageIcon("./src/Video.gif").getImage().getScaledInstance(800, 305, Image.SCALE_DEFAULT));
			imageLabel.setIcon(ii);
			contentPane.add(imageLabel, BorderLayout.CENTER);
			int delay = 8000;
			Timer timer = new Timer(delay, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dialog.dispose();
				}
			});
			timer.setRepeats(false);
			timer.start();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void show() {
		dialog.setVisible(true);
	}
}