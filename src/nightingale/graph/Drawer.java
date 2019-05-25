package nightingale.graph;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public interface Drawer {
	public void draw(Graphics g, Graphics2D g2d, AffineTransform at);
}
