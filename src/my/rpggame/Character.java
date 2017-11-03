package my.rpggame;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Character {
	private float maxHealth;
	private float health;
	volatile boolean run;

	private Image imageLeft;
	private Image imageRight;
	private Image image;
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int width;
	private int height;
	private boolean pause;

	private ArrayList<String> characters = new ArrayList<>();
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
		width = image.getWidth(null);
		height = image.getHeight(null);
		x = 40;
		y = 60;
		isDead.start();
		run = true;
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

	public void setPause(boolean z) {
		this.pause = z;
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,width,height);
	}
	
	public float getHealth() {
		return health;
	}

	public void damage(float dmg) {
		health -= dmg;
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



	void killEnemy(Enemy enemy) {
		System.out.println("You killed the " + enemy.getName() + "! " + enemy.getGoldWorth() + " gold earned.");
		enemiesKilled++;
		gold += enemy.getGoldWorth();
	}

	Thread isDead = new Thread(
			new Runnable() {
				public void 	run() {
					while(run) {
						if (health <= 0) { 
							run = false ; 
						}
					}
				}
			});
}
