package nightingale.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import nightingale.input.NInput;

public class NButton extends NUIElement{

	public static enum Status { CALM, FOCUSED, PRESSED }
	
	private Status status = Status.CALM;
	
	public NButton(int x, int y, int w, int h) {
		this.setRealXY(x, y);
		this.setRealSize(w, h);
	}
	
	@Override
	public void perform(NInput in) {
		if(isActive())
			if(contains(in.getMouse().getX(), in.getMouse().getY())) {
				if(status == Status.PRESSED && !in.getMouse().isLeftButtonPressed())
					listener.actionPerform(this);
				status = Status.FOCUSED;
				if(in.getMouse().isLeftButtonPressed()) status = Status.PRESSED;
			}else if(status != Status.PRESSED || !in.getMouse().isLeftButtonPressed()) status = Status.CALM;
	}

	@Override
	public void draw(Graphics g, Graphics2D g2d, AffineTransform at) {
		if(isVisible()) {
			switch(status) {
			case CALM:
				g.setColor(Color.RED);
				break;
			case FOCUSED:
				g.setColor(Color.YELLOW);
				break;
			case PRESSED:
				g.setColor(Color.orange);
				break;
			}
			g.drawRect(getX(), getY(), getWidth(), getHeight());
			g.drawString(name, getX()+getWidth()/2-g.getFontMetrics().stringWidth(name)/2,
					getY()+getHeight()/2+g.getFont().getSize()/2);
		}
	}

}
