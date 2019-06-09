package nightingale.ui;

import java.awt.Color;
import java.awt.Graphics;

import nightingale.input.NInput;

public class NLabel extends NUIElement{

	protected String text = "DEFAULT TEXT";
	
	public String getText() { return text; }
	
	public void setText(String text) {
		this.text = text;
	}
	
	public NLabel(String text, int x, int y, int width, int height) {
		setXY(x, y);
		setSize(width, height);
		setText(text);
	}
	
	@Override
	public void perform(NInput input) { }

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.drawString(text, getX()+(getWidth()/2-g.getFontMetrics().stringWidth(text)/2),
				getY()+getHeight()/2+g.getFont().getSize()/2);
	}
}
