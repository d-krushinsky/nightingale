package nightingale.graph.animation;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import nightingale.graph.NImageFactory;
import nightingale.util.NCamera;

public class NSprite {

	protected NAnimation[] animations;
	
	public NAnimation getAnimation(int index) {
		return animations[index];
	}
	
	public NSprite(String path, int animationsFramesCounts[], int frameWidth, int frameHeight) {
		BufferedImage sheet = NImageFactory.loadFromFile(path);
		animations = new NAnimation[sheet.getHeight()/frameHeight];
		for(int i=0;i<animations.length;i++) {
			animations[i] = new NAnimation(
					sheet, 
					frameWidth, 
					new Rectangle(0, i*frameHeight, frameWidth*animationsFramesCounts[i], frameHeight));
		}
	}

	public void draw(int animation, int frame, int x, int y, Graphics2D g2d, NCamera cam) {
		animations[animation].drawFrame(frame, x, y, g2d, cam);
	}
	
	public void draw(int animation, int frame, Graphics2D g2d, AffineTransform at) {
		animations[animation].drawFrame(frame, g2d, at);
	}
	
}