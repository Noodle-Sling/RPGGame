package my.rpggame;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Enemy implements ActionListener{
	private String name;
	private float health;
	private float dmg;
	private float goldWorth;

	private int x;
	private int y;
	private final float dx = 1;
	private final float dy = 1;
	private int width;
	private int height;
	private boolean vis;

	private boolean up;
	private boolean pause;

	private Image image;
	public Timer timer;
	private final int SHOOT_DELAY = 2000;

	private ArrayList<Arrow> arrows = new ArrayList<>();

	Enemy(int x, int y) {

		int random = 1;

		switch (random) {
		case 1: 
			name = "Skeleton" ;
			health=20;
			dmg = 10;
			goldWorth = 10 ;
			break;
		case 2: 
			name = "Wolf";
			health=25;
			dmg=8;
			goldWorth=3;
			break;
		default: 
			break; } 
		ImageIcon ii = new ImageIcon("Resources/" + name.toLowerCase() + ".png");
		image = ii.getImage();
		
		if (Math.round(Math.random()) == 1) {
			up = true;
		}
		else {
			up = false;
		}
		this.x = x;
		this.y = y;
		width = image.getWidth(null);
		height = image.getHeight(null);
		vis =  true;
		timer = new Timer(SHOOT_DELAY, this);
		timer.start();
	}

	public Image getImage() {
		return image;
	}

	public void damage(float damage) {
		health -= damage;
		if(health <= 0) {
			vis = false;
		}
	}
	
	public boolean getVis() {
		return vis;
	}

	public float getHealth() {
		return health;
	}

	public float getDmg() {
		return dmg;
	}

	public String getName() {
		return name;
	}

	public float getGoldWorth() {
		return goldWorth;
	}

	public void setPause(boolean z) {
		pause = z;
	}

	public void move() {
		if(!pause) {
			if(x <= -50) {
				x = 800;
			}
			if(y <= 0) {
				up = false;
			}
			else if(y >= 525) {
				up = true;
			}

			if(up) {
				y -= dy;
			}
			else {
				y += dx;
			}
			x -= dx;
		}
	}

	public int getX() {
		return Math.round(x);
	}

	public int getY() {
		return Math.round(y);
	}

	public void fire() {
		if(!pause && vis) {
			arrows.add(new Arrow(x - width + 30, y + height /2 ));
		}
	}

	public ArrayList<Arrow> getArrows() {
		return arrows;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		fire();
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,width,height);
	}

	public void attack(Character player) {
		player.damage(dmg);
	}






}