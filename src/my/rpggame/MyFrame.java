package my.rpggame;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class MyFrame extends JFrame {
	
	private JPanel contentPane;
	private StartScreen startScreen;
	private String input;
	private JButton btnMage;
	private JButton btnKnight;
	
	private ActionListener knight = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			input = "knight";
			startGame();
		}
	};
	
	private ActionListener mage = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			input = "mage";
			startGame();
		}
	};
	

	public MyFrame() {

		mainMenu();
	}

	private void mainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);
		setTitle("RPG Game");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		startScreen = new StartScreen(knight, mage);
		contentPane.add(startScreen);
		
	}
	
	private void startGame() {
		contentPane.removeAll();
		contentPane.revalidate();
		Character main = new Character(input);
		contentPane.add(new World(main));
		contentPane.requestDefaultFocus();
		
	}

	public static void startGUI() {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MyFrame ex = new MyFrame();
				ex.setVisible(true);		
			}
		});
		
		
	}
}
