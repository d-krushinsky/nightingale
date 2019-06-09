package nightingale.graph.animation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import nightingale.util.NCamera;

public class NAnimator {

	private NAnimation animation;
	protected int frame = 0;
	private int add = 1;
	protected float animationSpeed = 0;
	private long startTime = 0;
	private long delta = 0;
	protected boolean loop = true;
	
	protected boolean stoped = false;
	
	protected float x = 0;
	protected float y = 0;
	
	public float getX() { return x; }
	public float getY() { return y; }
	
	public void setX(float x) {	this.x = x; }
	public void setY(float y) { this.y = y; }
	
	public boolean isStoped() { return stoped; }
	
	public NAnimator(NAnimation animation, float speedInSeconds) {
		this.animation = animation;
		this.animationSpeed = speedInSeconds;
	}
	
	public void start() {
		delta = 0;
		frame = 0;
		stoped = false;
		startTime = System.currentTimeMillis();
	}
	
	public void stop() {
		stoped = true;
	}
	
	public void resume() {
		stoped = false;
	}
	
	public void update() {
		if(stoped) {
			delta = System.currentTimeMillis() - startTime;
		}else if( System.currentTimeMillis() - (startTime+delta) >= (animationSpeed*1000) ) {
			delta = 0;
			frame+=add;
			if(frame >= animation.getFramesCount()) {
				if(loop)
					frame = 0;
				else {
					add*=-1;
					frame+=add;
				}
			}else if(frame<0) {
				add*=-1;
				frame+=add;
			}
			startTime = System.currentTimeMillis();
		}
	}
	
	public void draw(Graphics g, Graphics2D g2d, AffineTransform at, NCamera cam) {
		synchronized(animation) {
			animation.drawFrame(frame, (int)x, (int)y, g, g2d, at, cam);
		}
	}
	
}
