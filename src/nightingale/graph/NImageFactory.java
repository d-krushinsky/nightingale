package nightingale.graph;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public abstract class NImageFactory {

	public static BufferedImage loadFromFile(String path) {
		BufferedImage loadedImage = null;
		try {
			loadedImage = ImageIO.read(NImageFactory.class.getResource(path));
		}catch(Exception firstException) {
			try {
				loadedImage = ImageIO.read(new File(path));
			}catch(Exception secondException) {
				loadedImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return loadedImage;
	}
	
	public static BufferedImage cropFromImage(BufferedImage img, int x, int y, int w, int h) {
		BufferedImage croppedImage = null;
		try {
			croppedImage = img.getSubimage(x, y, w, h);
		}catch(Exception firstException) {
			croppedImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		}
		return croppedImage;
	}
	
	/**
	 * @param img
	 * @param width
	 * @param height
	 * @param scale - Image.SCALE_*
	 * @return
	 */
	public static BufferedImage resize(BufferedImage img, int width, int height, int scale) {
		Image tmp = img.getScaledInstance(width, height, scale);
		BufferedImage expanded = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = expanded.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

		return expanded;
	}
	
}
