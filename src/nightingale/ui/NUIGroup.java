package nightingale.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;

import nightingale.util.NCamera;

public class NUIGroup {

	private Map<String, NUIElement> elements = new HashMap<String, NUIElement>();
	
	private NCamera cam = null;
	
	private boolean visible = true;
	private boolean active = true;
	
	// group getters
	public boolean isVisible() { return visible; }
	public boolean isActive()  { return  active; }
	public NCamera getCamera() { return     cam; }
	
	// group setters
	public void setVisible(boolean visible) { this.visible = visible; }
	public void setActive(boolean active) { this.active = active; }
	public void setCamera(NCamera cam) { this.cam = cam; }
	
	public void addElement(String name, NUIElement element) {
		String _name = name;
		int i = -1;
		do {
			i++;
		} while(elements.containsKey(i==0?_name:_name+"_"+i));
		elements.put(i==0?_name:_name+"_"+i, element);
	}
	
	public void removeElement(String name) {
		if(!elements.containsKey(name)) throw new NullPointerException();
		else elements.remove(name);
	}
	
	public void perform() {
		elements.forEach((name, element) -> element.perform());
	}
	
	public void draw(Graphics g, Graphics2D g2d, AffineTransform at) {
		elements.forEach((name, element) -> element.draw(g, g2d, at));
	}
}
