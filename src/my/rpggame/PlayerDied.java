package my.rpggame;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayerDied extends JPanel {
	
	private ActionListener quit;
	private ActionListener mainMenu;
	
	public PlayerDied(ActionListener main) {
		this.mainMenu = main;
		
		setOpaque(false);
		setBounds(new Rectangle(0, 0, 800, 600));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{246, 0, 0};
		gbl_panel.rowHeights = new int[]{178, 0, 23, 0, 0, 0, 40, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_panel);
		
		JLabel lblYouHaveDied = new JLabel("<html><div style=‘text-align: center;’> You have died. <br> Please return to the main menu or exit the game.  </div></html>\n");
		lblYouHaveDied.setForeground(Color.WHITE);
		lblYouHaveDied.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblYouHaveDied = new GridBagConstraints();
		gbc_lblYouHaveDied.insets = new Insets(0, 0, 5, 0);
		gbc_lblYouHaveDied.gridx = 1;
		gbc_lblYouHaveDied.gridy = 1;
		add(lblYouHaveDied, gbc_lblYouHaveDied);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 3;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{60, 0, 60, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panelMainMenu = new JPanel();
		GridBagConstraints gbc_panelMainMenu = new GridBagConstraints();
		gbc_panelMainMenu.insets = new Insets(0, 0, 0, 5);
		gbc_panelMainMenu.gridx = 1;
		gbc_panelMainMenu.gridy = 0;
		panel_1.add(panelMainMenu, gbc_panelMainMenu);
		
		JButton btnReturnToMain = new JButton("Return to Main Menu");
		btnReturnToMain.addActionListener(mainMenu);
		btnReturnToMain.setForeground(Color.BLACK);
		btnReturnToMain.setContentAreaFilled(false);
		btnReturnToMain.setBorderPainted(false);
		panelMainMenu.add(btnReturnToMain);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 5;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{60, 191, 60, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JPanel panelQuit = new JPanel();
		GridBagConstraints gbc_panelQuit = new GridBagConstraints();
		gbc_panelQuit.insets = new Insets(0, 0, 0, 5);
		gbc_panelQuit.gridx = 1;
		gbc_panelQuit.gridy = 0;
		panel_2.add(panelQuit, gbc_panelQuit);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setBackground(Color.WHITE);
		btnQuit.setForeground(Color.BLACK);
		btnQuit.setContentAreaFilled(false);
		btnQuit.setBorderPainted(false);
		panelQuit.add(btnQuit);
	}

	
}
