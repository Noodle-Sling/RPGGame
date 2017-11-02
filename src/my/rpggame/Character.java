package my.rpggame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.xml.internal.ws.util.StringUtils;

public class Character {
	int maxHealth;
	static int health;
	volatile boolean run;

	private Image imageLeft;
	private Image imageRight;
	private Image image;
	private int x;
	private int y;
	private int dx;
	private int dy;
	private boolean pause;

	private ArrayList<String> characters = new ArrayList<>();

	/* String healthStatement;
	switch (health) {

	}
	 */
	int dmgDice;
	int gold = 0;
	int enemiesKilled = 0;

	Character(String in) {

		characters.add("knight");
		characters.add("mage");

		String input = in;

		switch (input) {
		case "knight": 
			maxHealth=100;
			health=100;
			dmgDice=2;
			break;
		case "mage": 
			maxHealth=50;
			health=50;
			dmgDice=3;
			break;
		default: 
			System.out.println("You didn't select a character.");
			break; 
		} 
		ImageIcon il = new ImageIcon("Resources/" + input + "_left.png");
		imageLeft = il.getImage();
		ImageIcon ir = new ImageIcon("Resources/" +input + "_right.png");
		imageRight = ir.getImage();
		image = imageRight;
		x = 40;
		y = 60;
	}

	public void move() {

		if(x+dx>=750) {
			x = 750;
		} else if(x+dx<=0) {
			x=0;
		} else {
			x += dx;
		}

		if(y+dy>=525) {
			y=525;
		} else if(y+dy<=0) {
			y=0;
		} else {
			y += dy;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}

	public void keyPressed(KeyEvent e) {
		if(!pause) {
			int key = e.getKeyCode();

			if(key == KeyEvent.VK_LEFT) {	
				dx -= 1;
				image = imageLeft;
			}

			if(key == KeyEvent.VK_RIGHT) {
				dx += 1;
				image = imageRight;
			}

			if(key == KeyEvent.VK_UP) {
				dy -= 1;
			}

			if(key == KeyEvent.VK_DOWN) {
				dy += 1;
			}
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

	public void attack(Enemy enemy) {
		for (int i = 0; i < dmgDice; i++) {
			int realDmg = 1 + (int)(5 * Math.random());
			enemy.health -= realDmg;
			System.out.print(realDmg + " Damage! ");
			if (enemy.health > 0) {
				System.out.println("Enemy health: " + enemy.health + ".");
			}
			else {
				killEnemy(enemy);
				return;
			}
		}
		if (enemy.health > 0) {
			health -= enemy.dmg;
			System.out.println("Enemy does " + enemy.dmg + " damage. Player health: " + Character.health + "." );
		}
	}

	void killEnemy(Enemy enemy) {
		System.out.println("You killed the " + enemy.name + "! " + enemy.goldWorth + " gold earned.");
		enemiesKilled++;
		gold += enemy.goldWorth;
	}

	void checkStatus(Character player) {
		System.out.println("Health: " + player.health + "/" + player.maxHealth);
		System.out.println("Number of damage die rolls: " + player.dmgDice);
		System.out.println("Gold: " + player.gold);
		System.out.println("Enemies killed: " + player.enemiesKilled);
	}

	Thread isDead = new Thread(
			new Runnable() {
				public void 	run() {
					while(run) {
						if (health <= 0) { 
							System.out.println("You have died! Please Log out to play again.") ;
							run = false ; }
					}
				}
			});

	public void setPause(boolean z) {
		this.pause = z;
	}

	public BufferedImage scaleImage(int WIDTH, int HEIGHT, String filename) {
		BufferedImage bi = null;
		try {
			ImageIcon ii = new ImageIcon(filename);//path to image
			bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = (Graphics2D) bi.createGraphics();
			g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
			g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bi;
	}


}
