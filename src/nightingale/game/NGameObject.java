package nightingale.game;

import java.awt.Rectangle;

import nightingale.util.NCamera;

public class NGameObject {

	protected float x, y;
	protected float width, height;
	
	protected boolean collidable = true;
	protected boolean gravity = false;
	
	// Getters
	public float getX() { return x; }
	public float getX(NCamera cam) { return cam.getX(getX()); }
	
	public float getY() { return y; }
	public float getY(NCamera cam) { return cam.getY(getY()); }
	
	public float getWidth() { return width; }
	public float getWidth(NCamera cam) { return cam.scale(getWidth()); }
	
	public float getHeight() { return height; }
	public float getHeight(NCamera cam) { return cam.scale(getHeight()); }
	
	public float getCenterX() { return getX()+getWidth()/2; }
	public float getCenterX(NCamera cam) { return getX(cam)+getWidth(cam)/2; }
	
	public float getCenterY() { return getY()+getHeight()/2; }
	public float getCenterY(NCamera cam) { return getY(cam)+getHeight(cam)/2; }
	
	public boolean isCollidable() { return collidable; }
	public boolean isGravityWorks() { return gravity; }
	
	public float getDistanceTo(NGameObject obj) {
		return (float)( Math.sqrt( Math.pow(getCenterX()-obj.getCenterX(), 2) +
				Math.pow(getCenterY()-obj.getCenterY(), 2) ) );
	}
	
	public float getDistanceTo(NGameObject obj, NCamera cam) {
		return (float)( Math.sqrt( Math.pow(getCenterX(cam)-obj.getCenterX(cam), 2) +
				Math.pow(getCenterY(cam)-obj.getCenterY(cam), 2) ) );
	}
	
	// Setters
	public void setX(float x) { this.x = x; }
	public void setX(float x, NCamera cam) { this.x = cam.returnX(x); }
	
	public void setY(float y) { this.y = y; }
	public void setY(float y, NCamera cam) { this.y = cam.returnY(y); }
	
	public void setWidth(float width) { this.width = width; }
	public void setWidth(float width, NCamera cam) { this.width = cam.unscale(width); }
	
	public void setHeight(float height) { this.height = height; }
	public void setHeight(float height, NCamera cam) { this.height = cam.unscale(height); }
	
	public void setCollidable(boolean collidable) { this.collidable = collidable; }
	public void setGravity(boolean gravity) { this.gravity = gravity; }
	
	// Constructors
	public NGameObject() {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}
	
	public NGameObject(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Rectangle toRect() { return new Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight()); }
	public Rectangle toRect(NCamera cam) { 
		return new Rectangle((int)getX(cam), (int)getY(cam), (int)getWidth(cam), (int)getHeight(cam));
	}
}
