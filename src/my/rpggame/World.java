package my.rpggame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;

public class World extends JPanel implements ActionListener{

	private Timer timer;
	private final int DELAY = 10;

	private final int[][] pos = {
			{790, 259}, {660, 50}, {540, 90},
			{790, 220}, {790, 20}, {740, 180}
	};

	public Character player;
	public boolean pause;
	public ArrayList<Enemy> enemies = new ArrayList<>();

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

		gameLoop();


	}

	private void initUI() {
		pause = false;
		player.setPause(false);
		for(Enemy n : enemies) {
			n.setPause(false);
		}
		removeAll();
		revalidate();
		requestDefaultFocus();
		setBounds(new Rectangle(0, 0, 800, 600));
		setFocusable(true);
		setBackground(Color.BLACK);
	}

	private void pauseMenu() {

		add(new PauseMenu(mainMenu, resume));
		revalidate();


	}

	private void initEnemies() {
		for(int[] p : pos) {
			enemies.add(new Enemy(p[0], p[1]));
		}
	}

	private void gameLoop() {
		initUI();
		initEnemies();
		timer = new Timer(DELAY, this);
		timer.start();
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
		ArrayList fb = player.getFireballs();

		for(Object f : fb) {
			Fireball k = (Fireball) f;
			g2d.drawImage(k.getImage(), k.getX(), k.getY(), this);
		}

		for(Enemy n : enemies) {
			if(n.getVis()) {
				g2d.drawImage(n.getImage(), n.getX(), n.getY(), this);
			}
			ArrayList ms = n.getArrows();

			for(Object j : ms) {
				Arrow m = (Arrow) j;
				g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}
		}

	}

	private void updateFireballs() {
		ArrayList fb = player.getFireballs();
		for(int i = 0; i < fb.size(); i++) {

			Fireball m = (Fireball) fb.get(i);

			if(m.isVisible()) {
				m.move();
			}
			else {
				fb.remove(i);
			}
		}
	}

	private void updateArrows() {
		for(Enemy n : enemies) {
			ArrayList<Arrow> ms = n.getArrows();
			for(int i = 0; i < ms.size(); i++) {

				Arrow m = (Arrow) ms.get(i);

				if(m.isVisible()) {
					m.move();
				}
				else {
					ms.remove(i);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!player.run) {
			timer.stop();
			repaint();
			updateArrows();
			endGame();
		}
		if(!pause && player.run) {

			player.move();
			for(Enemy n : enemies) {
				n.move();
			}
			updateFireballs();
			updateArrows();
			checkCollisions();

			repaint();
		}
	}

	public void checkCollisions() {
		Rectangle r1 = player.getBounds();
		ArrayList<Fireball> fb = player.getFireballs();
		for(Enemy n : enemies) {
			Rectangle r2 = n.getBounds();
			ArrayList<Arrow> ms = n.getArrows();
			for(Arrow j : ms) {
				Rectangle r3 = j.getBounds();

				if(r1.intersects(r3)) {
					n.attack(player);
					j.setVis(false);

					System.out.println("Hit. Health: " + player.getHealth());
				}
			}
			for(Fireball f : fb) {
				Rectangle r4 = f.getBounds();

				if(r2.intersects(r4) && n.getVis()) {
					player.attack(n);
					f.setVis(false);
				}
			}
		}
		for(int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i).getVis() == false && enemies.get(i).getArrows().size() == 0) {
				enemies.remove(i);
			}
		}
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE && pause == false) {
				pauseMenu();
				pause = true;
				player.setPause(true);
				for(Enemy n : enemies) {
					n.setPause(true);
				}
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
	}

	public void endGame() {
		add(new PlayerDied(mainMenu));
		revalidate();
	}
}
