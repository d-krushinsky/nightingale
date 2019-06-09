package nightingale.graph;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import nightingale.util.NCamera;

public class NText {

	private BufferedImage[] symbols;
	private String alphabet;
	private int width, height;
	
	public NText(String alphabet, int width, int height, String path) {
		this.width  = width;
		this.height = height;
		this.alphabet = alphabet;
		symbols = new BufferedImage[alphabet.length()];
		BufferedImage font = NImageFactory.loadFromFile(path);
		int symbolsInRow = font.getWidth()/width;
		int x = 0, y = 0;
		for(int i=0;i<symbols.length;i++) {
			symbols[i] = NImageFactory.cropFromImage(font, x, y, width, height);
			if(i % symbolsInRow == 0) {
				x = 0;
				y += height;
			}else {
				x += width;
			}
		}
	}
	
	public void draw(String text, int x, int y, Graphics g, Graphics2D g2d, AffineTransform at, NCamera cam) {
		int i = 0;
		for(char symbol : text.toCharArray()) {
			g.drawImage(
					symbols[alphabet.indexOf(symbol)],
					(int)cam.getX(x+(width*i)),
					(int)cam.getY(y),
					(int)cam.scale(width),
					(int)cam.scale(height),
					null);
			i++;
		}
	}
	
}
