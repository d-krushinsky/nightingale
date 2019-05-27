package nightingale.graph;

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
	
}
