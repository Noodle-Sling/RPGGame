package my.rpggame;

import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JPanel;

public class World extends JPanel implements ActionListener{
	
	private Timer timer;
	private final int DELAY = 10;
	Character mainCharacter;
	
	public World(Character main) {
		this.mainCharacter = main;
		initWorld();
	}
	
	private void initWorld() {
		
		addKeyListener(new TAdapter())
	}

}
