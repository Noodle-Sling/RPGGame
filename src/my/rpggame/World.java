package my.rpggame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class World extends JPanel implements ActionListener{

	private Timer timer;
	private final int DELAY = 10;
	private Image background = new ImageIcon("Resources/background.png").getImage();

	private BufferedImage buffer;
	private int enemyCount;

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
		enemyCount = enemies.size();
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
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(buffer, 0, 0, this);

		Toolkit.getDefaultToolkit().sync();
	}

	private void updateBuffer() {


		if (buffer == null) {

			buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

		}

		Graphics2D g2d = buffer.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2d.drawImage(background, 0, 0, this);

		drawPlayerHealth(g2d);

		g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);

		ArrayList fb = player.getFireballs();

		for(Object f : fb) {
			Fireball k = (Fireball) f;
			g2d.drawImage(k.getImage(), k.getX(), k.getY(), this);
		}

		for(Enemy n : enemies) {
			if(n.getVis()) {
				g2d.drawImage(n.getImage(), n.getX(), n.getY(), this);
				drawEnemyHealth(n, g2d);
			}
			ArrayList ms = n.getArrows();

			for(Object j : ms) {
				Arrow m = (Arrow) j;
				g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}
		}
		
	}

	private void drawPlayerHealth(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		Font f = new Font("Times New Roman", Font.PLAIN, 30);
		g2d.setFont(f);
		g2d.drawString("HEALTH:", 15, 55);
		
		f = new Font("Times New Roman", Font.PLAIN, 20);
		g2d.setFont(f);
		g2d.drawString(player.getHealth() + "/" + player.getMaxHealth(), 255, 52);
		g2d.drawString("ENEMIES LEFT: " + enemyCount, 500, 52);
		
		int startX = 145;
		int healthX = Math.round(player.getHealth()/ player.getMaxHealth() * 100);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(startX, 30, healthX, 30);

		g2d.setColor(Color.RED);
		g2d.fillRect(healthX + startX, 30, 100 - healthX, 30);

		g2d.setColor(Color.BLACK);
		g2d.drawRect(startX, 30, 100, 30);
	}

	private void drawEnemyHealth(Enemy n, Graphics2D g2d) {
		int healthX = Math.round(n.getHealth()/n.getMaxHealth() * 20);
		int startX = n.getWidth()/2 - 5 + n.getX();
		g2d.setColor(Color.GREEN);
		g2d.fillRect(startX, n.getY() - 5, healthX, 5);

		g2d.setColor(Color.RED);
		g2d.fillRect(startX + healthX, n.getY() - 5, 20 - healthX, 5);

		g2d.setColor(Color.BLACK);
		g2d.drawRect(startX, n.getY() - 5, 20, 5);
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
			endGame("lose");
		}
		if(!pause && player.run) {

			updateBuffer();
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
		int count = 0;
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
					f.setVis(false);
					player.attack(n);
				}
			}
		}
		for(int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i).getVis() == false && enemies.get(i).getArrows().size() == 0) {
				enemies.remove(i);
			}
		}
		boolean enemyAlive = false;
		for(Enemy n : enemies) {
			if(n.getVis() == true) {
				enemyAlive = true;
				count++;
			}
		}
		enemyCount = count;
		if(!enemyAlive) {
			endGame("win");
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

	public void endGame(String type) {
		timer.stop();
		updateBuffer();
		repaint();
		updateFireballs();
		updateArrows();
		updateBuffer();
		repaint();
		add(new EndGame(mainMenu, type));
		revalidate();
	}
}
