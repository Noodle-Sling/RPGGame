package my.rpggame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Fireball {

	private int x;
	private int y;
	private int width;
	private int height;
	private boolean vis;

	private final int BOARD_WIDTH = 800;
	private int FIREBALL_SPEED = 4;

	private Image image;


	public Fireball(int x, int y, String direction) {
		this.x = x;
		this.y = y;
		if(direction.equals("left")) {
			FIREBALL_SPEED *= -1;
		}

		initFireball();
	}

	private void initFireball() {
		ImageIcon ii = new ImageIcon("Resources/fireball.png");
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		vis = true;
	}

	public void move() {
		x += FIREBALL_SPEED;

		if(x < 0) {
			vis = false;
		}
	}
	
	public boolean isVisible() {
		return vis;
	}
	
	public Image getImage() {
		return image;
	}
	
	public int getX() {
		return Math.round(x);
	}
	
	public int getY() {
		return Math.round(y);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,width, height);
	}
	
	public void setVis(boolean z) {
		vis = z;
	}
}
