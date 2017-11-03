package my.rpggame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class PauseMenu extends JPanel{
	
	private ActionListener mainMenu;
	private ActionListener resume;

	public PauseMenu(ActionListener mainMenu, ActionListener resume) {
		this.mainMenu = mainMenu;
		this.resume = resume;
		initUI();
	}
	
	public void initUI() {
		setOpaque(false);
		setBounds(new Rectangle(0, 0, 800, 600));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{348, 0, 0};
		gbl_panel.rowHeights = new int[]{178, 0, 0, 0, 0, 40, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_panel);

		JLabel lblPause = new JLabel("Pause");
		lblPause.setForeground(Color.WHITE);
		lblPause.setFont(new Font("Lucida Grande", Font.BOLD, 34));
		GridBagConstraints gbc_lblPause = new GridBagConstraints();
		gbc_lblPause.insets = new Insets(0, 0, 5, 0);
		gbc_lblPause.gridx = 1;
		gbc_lblPause.gridy = 1;
		add(lblPause, gbc_lblPause);

		JPanel panelResume = new JPanel();
		panelResume.setBackground(UIManager.getColor("Button.background"));
		GridBagConstraints gbc_panelResume = new GridBagConstraints();
		gbc_panelResume.insets = new Insets(0, 0, 5, 0);
		gbc_panelResume.fill = GridBagConstraints.BOTH;
		gbc_panelResume.gridx = 1;
		gbc_panelResume.gridy = 3;
		add(panelResume, gbc_panelResume);

		JButton btnResume = new JButton("Resume");
		btnResume.addActionListener(resume);
		panelResume.add(btnResume);
		btnResume.setForeground(Color.BLACK);
		btnResume.setBorderPainted(false);
		panelResume.setPreferredSize(panelResume.getPreferredSize());

		JPanel panelMainMenu = new JPanel();
		GridBagConstraints gbc_panelMainMenu = new GridBagConstraints();
		gbc_panelMainMenu.insets = new Insets(0, 0, 5, 0);
		gbc_panelMainMenu.fill = GridBagConstraints.BOTH;
		gbc_panelMainMenu.gridx = 1;
		gbc_panelMainMenu.gridy = 4;
		add(panelMainMenu, gbc_panelMainMenu);

		JButton btnReturnToMain = new JButton("Return to Main Menu");
		btnReturnToMain.addActionListener(mainMenu);
		btnReturnToMain.setBorderPainted(false);
		btnReturnToMain.setContentAreaFilled(false);
		panelMainMenu.add(btnReturnToMain);
		panelMainMenu.setPreferredSize(panelMainMenu.getPreferredSize());
		
		JPanel panelQuit = new JPanel();
		panelQuit.setPreferredSize(new Dimension(184, 39));
		GridBagConstraints gbc_panelQuit = new GridBagConstraints();
		gbc_panelQuit.fill = GridBagConstraints.BOTH;
		gbc_panelQuit.gridx = 1;
		gbc_panelQuit.gridy = 6;
		add(panelQuit, gbc_panelQuit);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setContentAreaFilled(false);
		btnQuit.setBorderPainted(false);
		panelQuit.add(btnQuit);
	}
}
