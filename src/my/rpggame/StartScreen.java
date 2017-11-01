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

public class StartScreen extends JPanel {

	public JButton btnKnight;
	public JButton btnMage;
	private JLabel text;
	private ActionListener knight;
	private ActionListener mage;
	
	public String input = "";
	
	public StartScreen(ActionListener knight, ActionListener mage) {
		this.knight = knight;
		this.mage = mage;
		initUI();
	}
	
	private void initUI() {
		setBounds(0, 0, 800, 578);
		setBackground(Color.BLACK);
		setFocusable(true);
		GridBagLayout gbl_startScreen = new GridBagLayout();
		gbl_startScreen.columnWidths = new int[]{309, 0, 0, 0, 0, 0};
		gbl_startScreen.rowHeights = new int[]{0, 44, 137, 0, 46, 0, 44, 0, 0};
		gbl_startScreen.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_startScreen.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_startScreen);
		
		JLabel intro = new JLabel("Welcome to our first RPG! Choose your character:");
		GridBagConstraints gbc_lblintro = new GridBagConstraints();
		gbc_lblintro.insets = new Insets(0, 0, 5, 0);
		gbc_lblintro.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblintro.gridx = 4;
		gbc_lblintro.gridy = 4;
		add(intro, gbc_lblintro);
		intro.setForeground(Color.WHITE);
		
		btnKnight = new JButton("Knight");
		btnKnight.addActionListener(knight);
		btnKnight.setForeground(Color.WHITE);
		btnKnight.setBackground(Color.BLACK);
		btnKnight.setBorderPainted(false); 
		btnKnight.setContentAreaFilled(false); 
		btnKnight.setFocusPainted(false); 
		btnKnight.setOpaque(false);
		GridBagConstraints gbc_btnKnight = new GridBagConstraints();
		gbc_btnKnight.insets = new Insets(0, 0, 5, 0);
		gbc_btnKnight.gridx = 4;
		gbc_btnKnight.gridy = 5;
		add(btnKnight, gbc_btnKnight);
		
		btnMage = new JButton("Mage");
		btnMage.addActionListener(mage);
		btnMage.setForeground(Color.WHITE);
		btnMage.setBackground(Color.BLACK);
		btnMage.setBorderPainted(false); 
		btnMage.setContentAreaFilled(false); 
		btnMage.setFocusPainted(false); 
		btnMage.setOpaque(false);
		GridBagConstraints gbc_btnMage = new GridBagConstraints();
		gbc_btnMage.insets = new Insets(0, 0, 5, 0);
		gbc_btnMage.gridx = 4;
		gbc_btnMage.gridy = 6;
		add(btnMage, gbc_btnMage);
	}
}
