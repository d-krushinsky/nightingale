package nightingale.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import nightingale.input.NInput;
import nightingale.util.NCamera;

public abstract class NUIElement {
	protected String name = "DEFAULT_ELEMENT";
	
	protected int x, y;
	protected int width, height;
	
	protected NCamera cam = null;
	protected NActionListener listener = new NActionListener() {
		@Override
		public void actionPerform(NUIElement element) {}
	};
	
	protected boolean visible = true;
	protected boolean active  = true;
	
	// Getters
	public String getName() { return name; }
	
	public int getX() {
		return (cam != null)?((int)cam.getX(x)):(x);
	}
	
	public int getY() {
		return (cam != null)?((int)cam.getY(y)):(y);
	}
	
	public int getWidth() {
		return (cam != null)?((int)cam.scale(width)):(width);
	}
	
	public int getHeight() {
		return (cam != null)?((int)cam.scale(height)):(height);
	}
	
	public int getRealX()      { return      x; }
	public int getRealY()      { return      y; }
	public int getRealWidth()  { return  width; }
	public int getRealHeight() { return height; }
	
	public boolean isVisible() { return visible; }
	public boolean isActive()  { return  active; }
	
	public NCamera getCamera() {
		return cam;
	}
	
	// Setters
	public void setX(int x) {
		if( cam != null ) this.x = (int)cam.returnX(x);
		else this.x = x;
	}
	
	public void setY(int y) {
		if( cam != null ) this.y = (int)cam.returnY(y);
		else this.y = y;
	}
	
	public void setXY(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public void setWidth(int width) {
		if( cam != null ) this.width = (int)cam.unscale(width);
		else this.width = width;
	}
	
	public void setHeight(int height) {
		if( cam != null ) this.height = (int)cam.unscale(height);
		else this.height = height;
	}
	
	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	public void setRealX(int x) { this.x = x; }
	public void setRealY(int y) { this.y = y; }
	
	public void setRealXY(int x, int y) {
		setRealX(x);
		setRealY(y);
	}
	
	public void setRealWidth(int width) { this.width = width; }
	public void setRealHeight(int height) { this.height = height; }
	
	public void setRealSize(int width, int height) {
		setRealWidth(width);
		setRealHeight(height);
	}
	
	public void setVisible(boolean visible) { this.visible = visible; }
	public void setActive(boolean active) { this.active = active; }
	
	public void setCamera(NCamera cam) { this.cam = cam; }
	
	public void setActionListener(NActionListener listener) {
		this.listener = listener;
	}
	
	public boolean contains(int x, int y) {
		if( new Rectangle(getX(), getY(), getWidth(), getHeight()).contains(x, y) ) return true;
		return false;
	}
	
	public boolean containsByRealSize(int x, int y) {
		if( new Rectangle(getRealX(), getRealY(), getRealWidth(), getRealHeight()).contains(x, y) ) return true;
		return false;
	}
	
	public abstract void perform(NInput input);
	public abstract void draw(Graphics g, Graphics2D g2d, AffineTransform at);
}
