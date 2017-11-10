package my.rpggame;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Character {
	private float maxHealth;
	private float health;
	public volatile boolean run;
	private float dmg;
	private boolean fired;
	private String direction;

	private String type;

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

	private ArrayList<Fireball> fireballs = new ArrayList<>();

	Character(String in) {

		String input = in;
		type = input.toLowerCase();

		switch (input) {
		case "knight": 
			maxHealth=100;
			health=100;
			dmg = 8;
			break;
		case "mage": 
			maxHealth=50;
			health=50;
			dmg = 10;
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
		direction = "right";
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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
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

	public float getMaxHealth() {
		return maxHealth;
	}

	public void damage(float dmg) {
		health -= dmg;
	}

	public float getDamage() {
		return dmg;
	}

	public void attack(Enemy enemy) {
		enemy.damage(dmg);
	}

	public void keyPressed(KeyEvent e) {
		if(!pause) {
			int key = e.getKeyCode();

			if(key == KeyEvent.VK_LEFT) {	
				dx -= 1;
				image = imageLeft;
				direction = "left";
			}

			if(key == KeyEvent.VK_RIGHT) {
				dx += 1;
				image = imageRight;
				direction = "right";
			}

			if(key == KeyEvent.VK_UP) {
				dy -= 1;
			}

			if(key == KeyEvent.VK_DOWN) {
				dy += 1;
			}

			if(key == KeyEvent.VK_SPACE) {
				if(!fired) {
					if(type.equals("mage")) {
						fire();
					}
					else if(type.equals("knight")) {
						swing();
					}
					fired = true;
				}
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

		if (key == KeyEvent.VK_SPACE) {
			fired = false;
		}
	}

	public void fire() {
		if(!pause) {
			if(direction.equals("left")) {
				fireballs.add(new Fireball(x, y+height/2, direction));
			}
			else {
				fireballs.add(new Fireball(x + width, y + height /2, direction));
			}
		}
	}

	public void swing() {

	}

	public ArrayList<Fireball> getFireballs() {
		return fireballs;
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
