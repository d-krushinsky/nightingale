package nightingale.game.map;

import nightingale.algorithms.pathfinding.Node;
import nightingale.util.NCamera;

public class NMapTile extends Node{

	protected float x, y;
	protected float width, height;
	
	protected int type = 0;
	protected int i, j;
	
	// Getters
	public int getType() { return type; }
	
	public float getX() { return x; }
	public float getX(NCamera cam) { return cam.getX(getX()); }
	
	public float getY() { return y; }
	public float getY(NCamera cam) { return cam.getY(getY()); }
	
	public float getWidth() { return width; }
	public float getWidth(NCamera cam) { return cam.scale(getWidth()); }
	
	public float getHeight() { return height; }
	public float getHeight(NCamera cam) { return cam.scale(getHeight()); }
	
	// Setters
	public void setX(float x) { this.x = x; }
	public void setX(float x, NCamera cam) { this.x = cam.returnX(x); }
	
	public void setY(float y) { this.y = y; }
	public void setY(float y, NCamera cam) { this.y = cam.returnY(y); }
	
	public void setWidth(float width) { this.width = width; }
	public void setWidth(float width, NCamera cam) { this.width = cam.unscale(width); }
	
	public void setHeight(float height) { this.height = height; }
	public void setHeight(float height, NCamera cam) { this.height = cam.unscale(height); }
	


	
	public NMapTile(int type, int i, int j) {
		super(i, j, true);
		this.type = type;
		this.i = i;
		this.j = j;
	}
	
	// Getters
	public int I() { return i; }
	public int J() { return j; }
	
	// DO NOTHING
	public void setGravity(boolean gravity) {}
	
}
