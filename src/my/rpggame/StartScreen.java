package my.rpggame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class StartScreen extends JPanel {

	public JButton btnKnight;
	public JButton btnMage;
	private JLabel text;
	private ActionListener knight;
	private ActionListener mage;
	
	public String input = "";
	private JPanel panelKnight;
	private JPanel panelMage;
	private JPanel panelQuit;
	private JButton btnQuit;
	
	public StartScreen(ActionListener knight, ActionListener mage) {
		this.knight = knight;
		this.mage = mage;
		initUI();
	}
	
	private void initUI() {
		setBounds(0, 0, 800, 600);
		setBackground(Color.BLACK);
		setFocusable(true);
		GridBagLayout gbl_startScreen = new GridBagLayout();
		gbl_startScreen.columnWidths = new int[]{256, 0, 0};
		gbl_startScreen.rowHeights = new int[]{44, 194, 46, 0, 44, 44, 0, 0};
		gbl_startScreen.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_startScreen.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_startScreen);
		
		JLabel intro = new JLabel("Welcome to our first RPG! Choose your character:");
		GridBagConstraints gbc_lblintro = new GridBagConstraints();
		gbc_lblintro.insets = new Insets(0, 0, 5, 0);
		gbc_lblintro.gridx = 1;
		gbc_lblintro.gridy = 2;
		add(intro, gbc_lblintro);
		intro.setForeground(Color.WHITE);
		
		
		panelKnight = new JPanel();
		FlowLayout fl_panelKnight = (FlowLayout) panelKnight.getLayout();
		fl_panelKnight.setVgap(0);
		fl_panelKnight.setHgap(0);
		GridBagConstraints gbc_panelKnight = new GridBagConstraints();
		gbc_panelKnight.insets = new Insets(0, 0, 5, 0);
		gbc_panelKnight.gridx = 1;
		gbc_panelKnight.gridy = 3;
		add(panelKnight, gbc_panelKnight);
		
		btnKnight = new JButton("Knight");
		panelKnight.add(btnKnight);
		btnKnight.addActionListener(knight);
		btnKnight.setForeground(Color.BLACK);
		btnKnight.setBackground(Color.BLACK);
		btnKnight.setBorderPainted(false); 
		btnKnight.setContentAreaFilled(false); 
		btnKnight.setFocusPainted(false); 
		btnKnight.setOpaque(false);
		panelKnight.setPreferredSize(panelKnight.getPreferredSize());
		
		panelMage = new JPanel();
		FlowLayout fl_panelMage = (FlowLayout) panelMage.getLayout();
		fl_panelMage.setVgap(0);
		fl_panelMage.setHgap(0);
		panelMage.setPreferredSize(new Dimension(85, 29));
		GridBagConstraints gbc_panelMage = new GridBagConstraints();
		gbc_panelMage.insets = new Insets(0, 0, 5, 0);
		gbc_panelMage.gridx = 1;
		gbc_panelMage.gridy = 4;
		add(panelMage, gbc_panelMage);
		
		btnMage = new JButton("Mage");
		panelMage.add(btnMage);
		btnMage.addActionListener(mage);
		btnMage.setForeground(Color.BLACK);
		btnMage.setBackground(Color.BLACK);
		btnMage.setBorderPainted(false); 
		btnMage.setContentAreaFilled(false); 
		btnMage.setFocusPainted(false); 
		btnMage.setOpaque(false);
		panelMage.setPreferredSize(panelMage.getPreferredSize());
		
		panelQuit = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelQuit.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panelQuit.setPreferredSize(new Dimension(85, 29));
		GridBagConstraints gbc_panelQuit = new GridBagConstraints();
		gbc_panelQuit.gridx = 1;
		gbc_panelQuit.gridy = 6;
		add(panelQuit, gbc_panelQuit);
		
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setOpaque(false);
		btnQuit.setForeground(Color.BLACK);
		btnQuit.setFocusPainted(false);
		btnQuit.setContentAreaFilled(false);
		btnQuit.setBorderPainted(false);
		btnQuit.setBackground(Color.BLACK);
		panelQuit.add(btnQuit);
	}
}
