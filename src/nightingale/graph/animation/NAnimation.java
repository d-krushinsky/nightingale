package nightingale.graph.animation;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import nightingale.graph.NImageFactory;
import nightingale.util.NCamera;

public class NAnimation {

	protected BufferedImage[] frames;
	private int framesCount = 0;
	
	public int getFramesCount() { return framesCount; }
	
	public NAnimation(String path, int frameWidth) {
		BufferedImage sheet = NImageFactory.loadFromFile(path);
		framesCount = sheet.getWidth() / frameWidth;
		frames = new BufferedImage[framesCount];
		for(int i=0;i<framesCount;i++) {
			frames[i] = NImageFactory.cropFromImage(sheet, i*frameWidth, 0, frameWidth, sheet.getHeight());
		}
	}
	
	public NAnimation(String path, int frameWidth, Rectangle clip) {
		BufferedImage sheet = NImageFactory.cropFromImage(
				NImageFactory.loadFromFile(path),
				(int)clip.getX(),
				(int)clip.getY(),
				(int)clip.getWidth(),
				(int)clip.getHeight() );
		framesCount = sheet.getWidth() / frameWidth;
		frames = new BufferedImage[framesCount];
		for(int i=0;i<framesCount;i++) {
			frames[i] = NImageFactory.cropFromImage(sheet, i*frameWidth, 0, frameWidth, sheet.getHeight());
		}
	}
	
	public NAnimation(BufferedImage sheet, int frameWidth) {
		framesCount = sheet.getWidth() / frameWidth;
		frames = new BufferedImage[framesCount];
		for(int i=0;i<framesCount;i++) {
			frames[i] = NImageFactory.cropFromImage(sheet, i*frameWidth, 0, frameWidth, sheet.getHeight());
		}
	}
	
	public NAnimation(BufferedImage sheet, int frameWidth, Rectangle clip) {
		BufferedImage _sheet = NImageFactory.cropFromImage(
				sheet,
				(int)clip.getX(),
				(int)clip.getY(),
				(int)clip.getWidth(),
				(int)clip.getHeight() );
		framesCount = _sheet.getWidth() / frameWidth;
		frames = new BufferedImage[framesCount];
		for(int i=0;i<framesCount;i++) {
			frames[i] = NImageFactory.cropFromImage(_sheet, i*frameWidth, 0, frameWidth, sheet.getHeight());
		}
	}
	
	public void drawFrame(int frame, int x, int y, Graphics2D g2d, NCamera cam) {
		g2d.drawImage(
				frames[frame], 
				(int)cam.getX(x), 
				(int)cam.getY(y), 
				(int)cam.scale(frames[frame].getWidth()), 
				(int)cam.scale(frames[frame].getHeight()),
				null);
	}
	
	public void drawFrame(int frame, Graphics2D g2d, AffineTransform at) {
		g2d.drawImage(frames[frame], at, null);
	}
}
