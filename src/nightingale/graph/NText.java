package nightingale.graph;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import nightingale.util.NCamera;

public class NText {
	private BufferedImage[] symbols;
	private String alphabet;
	private String path;
	private int width, height;
	
	public int getWidth()  { return  width; }
	public int getHeight() { return height; }
	
	public int getStringWidth(String text) { return text.length() * width; }
	
	public NText(String alphabet, int width, int height, String path) {
		this.width  = width;
		this.height = height;
		this.alphabet = alphabet;
		this.path = path;
		symbols = new BufferedImage[alphabet.length()];
		BufferedImage font = NImageFactory.loadFromFile(path);
		int symbolsInRow = font.getWidth()/width;
		int x = 0, y = 0;
		for(int i=0;i<symbols.length;i++) {
			symbols[i] = NImageFactory.cropFromImage(font, x, y, width, height);
			x += width;
			if(i != 0 && (i+1) % symbolsInRow == 0) {
				x = 0;
				y += height;
			}
		}
	}
	
	public NText recreate() {
		return new NText(alphabet, width, height, path);
	}
	
	public void expand(float m) {
		width = (int)(width*m);
		height = (int)(height*m);
		for(int i=0;i<symbols.length;i++)
			symbols[i] = NImageFactory.resize(symbols[i],	width, height, Image.SCALE_SMOOTH);
	}
	
	public void draw(String text, int x, int y, Graphics g, NCamera cam) {
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