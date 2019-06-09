package nightingale.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import nightingale.graph.NText;
import nightingale.input.NInput;

public class NButton extends NUIElement{

	public static enum Status { CALM, FOCUSED, PRESSED }
	
	protected String text = "DEFAULT TEXT";
	private Status status = Status.CALM;

	protected NText font = null;
	protected BufferedImage pressedImage = null;
	protected BufferedImage focusedImage = null;
	protected BufferedImage    calmImage = null;
	
	public void setFont(NText font) { this.font = font; }
	
	public void setImages(BufferedImage pressedImage, BufferedImage focusedImage, BufferedImage calmImage) {
		this.pressedImage = pressedImage;
		this.focusedImage = focusedImage;
		this.calmImage = calmImage;
	}
	
	public NButton(int x, int y, int w, int h) {
		this.setRealXY(x, y);
		this.setRealSize(w, h);
	}
	
	public NButton(String text, int x, int y, int w, int h) {
		this.setRealXY(x, y);
		this.setRealSize(w, h);
		this.setText(text);
	}
	
	public String getText() { return text; }
	
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public void perform(NInput in) {
		if(isActive())
			if(contains(in.getMouse().getX(), in.getMouse().getY())) {
				if(status == Status.PRESSED && !in.getMouse().isLeftButtonPressed())
					try {
						listener.actionPerform(this);
					}catch(NullPointerException npe) { }
				status = Status.FOCUSED;
				if(in.getMouse().isLeftButtonPressed()) status = Status.PRESSED;
			}else if(status != Status.PRESSED || !in.getMouse().isLeftButtonPressed()) status = Status.CALM;
	}

	@Override
	public void draw(Graphics g) {
		if(isVisible()) {
			switch(status) {
			case CALM:
				if(calmImage == null) {
					g.setColor(Color.RED);
					g.drawRect(getX(), getY(), getWidth(), getHeight());
				}else g.drawImage(calmImage, getX(), getY(), getWidth(), getHeight(), null);
				break;
			case FOCUSED:
				if(focusedImage == null) {
					g.setColor(Color.YELLOW);
					g.drawRect(getX(), getY(), getWidth(), getHeight());
				}else g.drawImage(focusedImage, getX(), getY(), getWidth(), getHeight(), null);
				break;
			case PRESSED:
				if(pressedImage == null) {
					g.setColor(Color.ORANGE);
					g.drawRect(getX(), getY(), getWidth(), getHeight());
				}else g.drawImage(pressedImage, getX(), getY(), getWidth(), getHeight(), null);
				break;
			}
			
			if(font == null) {
				g.drawString(text, getX()+getWidth()/2-g.getFontMetrics().stringWidth(text)/2,
						getY()+getHeight()/2+g.getFont().getSize()/2);
			}else {
				font.draw(text, 
						getRealX()+getRealWidth()/2-font.getStringWidth(text)/2, 
						getRealY()+getRealHeight()/2-font.getHeight()/2,
						g, cam);
			}
		}
	}

}
