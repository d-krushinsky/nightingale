package nightingale.graph;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

public class nCanvas extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	private Drawer drawer = null;
	
	public nCanvas(Drawer drawer) {
		this.drawer = drawer;
	}
	
	@Override
	public void run() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics        g   = bs.getDrawGraphics();
		AffineTransform at  = new AffineTransform();
		Graphics2D      g2d = (Graphics2D) g;
		
		if(System.getProperty("os.name").toLowerCase().contains("linux") ||
		   System.getProperty("os.name").toLowerCase().contains("mac")) {
			Toolkit.getDefaultToolkit().sync();
		}
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		drawer.draw(g, g2d, at);
		
		g.dispose();
		bs.show();
	}	
}
