package my.rpggame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Insets;

public class World extends JPanel implements ActionListener{

	private Timer timer;
	private final int DELAY = 10;
	Character player;
	public boolean pause;

	private ActionListener resume = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			initUI();
		}
	};

	private ActionListener mainMenu;

	public World(Character main, ActionListener mainMenu) {
		this.player = main;
		this.resume = resume;
		this.mainMenu = mainMenu;
		setLayout(null);
		addKeyListener(new TAdapter());

		initUI();
		timer = new Timer(DELAY, this);
		timer.start();
	}

	private void initUI() {
		pause = false;
		player.setPause(false);
		removeAll();
		revalidate();
		requestDefaultFocus();
		setBounds(new Rectangle(0, 0, 800, 600));
		setFocusable(true);
		setBackground(Color.BLACK);
	}

	private void pauseMenu() {

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(new Rectangle(0, 0, 800, 600));
		add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{348, 0, 0};
		gbl_panel.rowHeights = new int[]{178, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("Pause");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 34));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		JPanel panelResume = new JPanel();
		panelResume.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelResume = new GridBagConstraints();
		gbc_panelResume.insets = new Insets(0, 0, 5, 0);
		gbc_panelResume.fill = GridBagConstraints.BOTH;
		gbc_panelResume.gridx = 1;
		gbc_panelResume.gridy = 3;
		panel.add(panelResume, gbc_panelResume);

		JButton btnResume = new JButton("Resume");
		btnResume.addActionListener(resume);
		panelResume.add(btnResume);
		btnResume.setForeground(Color.BLACK);
		btnResume.setBorderPainted(false);
		panelResume.setPreferredSize(panelResume.getPreferredSize());

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 4;
		panel.add(panel_1, gbc_panel_1);

		JButton btnReturnToMain = new JButton("Return to Main Menu");
		btnReturnToMain.addActionListener(mainMenu);
		btnReturnToMain.setBorderPainted(false);
		btnReturnToMain.setContentAreaFilled(false);
		panel_1.add(btnReturnToMain);
		panel_1.setPreferredSize(panel_1.getPreferredSize());


	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		doDrawing(g);

		Toolkit.getDefaultToolkit().sync();
	}

	private void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		player.move();
		repaint();
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE && pause == false) {
				pauseMenu();
				pause = true;
				player.setPause(true);
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
	}
}
