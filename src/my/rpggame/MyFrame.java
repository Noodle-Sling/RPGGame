package my.rpggame;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyFrame extends JFrame {
	
	Image img;

	public MyFrame(Character main) {

		initUI(main);
	}

	private void initUI(Character main) {
		add(new World(main));
		setSize(800, 600);
		setResizable(false);

		setTitle("RPG Game");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void startGUI(Character main) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				MyFrame ex = new MyFrame(main);
				ex.setVisible(true);
			}
		});
	}
}
