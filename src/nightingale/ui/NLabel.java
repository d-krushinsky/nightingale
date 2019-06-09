package nightingale.ui;

import java.awt.Color;
import java.awt.Graphics;

import nightingale.graph.NText;
import nightingale.input.NInput;

public class NLabel extends NUIElement{

	protected String text = "DEFAULT TEXT";
	
	public String getText() { return text; }
	
	public void setText(String text) {
		this.text = text;
	}
	
	protected NText font = null;
	public void setFont(NText font) { this.font = font; }
	
	public NLabel(String text, int x, int y, int width, int height) {
		setXY(x, y);
		setSize(width, height);
		setText(text);
	}
	
	@Override
	public void perform(NInput input) { }

	@Override
	public void draw(Graphics g) {
		if(font == null) {
			g.setColor(Color.WHITE);
			g.drawRect(getX(), getY(), getWidth(), getHeight());
			g.drawString(text, getX()+(getWidth()/2-g.getFontMetrics().stringWidth(text)/2),
					getY()+getHeight()/2+g.getFont().getSize()/2);
		}else {
			font.draw(text, 
					getRealX()+getRealWidth()/2-font.getStringWidth(text)/2, 
					getRealY()+getRealHeight()/2-font.getHeight()/2,
					g, cam);
		}
	}
}
