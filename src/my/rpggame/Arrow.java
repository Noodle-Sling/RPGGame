package my.rpggame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Arrow {

	private int x;
	private int y;
	private int width;
	private int height;
	private boolean vis;

	private final int BOARD_WIDTH = 800;
	private final int ARROW_SPEED = -1;

	private Image image;


	public Arrow(int x, int y) {
		this.x = x;
		this.y = y;

		initArrow();
	}

	private void initArrow() {
		ImageIcon ii = new ImageIcon("Resources/arrow.png");
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		vis = true;
	}

	public void move() {
		x += ARROW_SPEED;

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
