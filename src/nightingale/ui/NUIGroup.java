package nightingale.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nightingale.input.NInput;
import nightingale.util.NCamera;

public class NUIGroup {

	private Map<String, NUIElement> elements = new HashMap<String, NUIElement>();
	
	private NCamera cam = null;
	
	private boolean visible = true;
	private boolean active = true;
	
	// Getters
	public boolean isVisible() { return visible; }
	public boolean isActive()  { return  active; }
	public NCamera getCamera() { return     cam; }
	
	public NUIElement getElement(String name) {
		return elements.get(name);
	}
	
	public ArrayList<NUIElement> getElements(){
		ArrayList<NUIElement> elements = new ArrayList<NUIElement>();
		this.elements.forEach((name, element) -> elements.add(element));
		return elements;
	}
	
	// Setters
	public void setVisible(boolean visible) { this.visible = visible; }
	public void setActive(boolean active) { this.active = active; }
	public void setCamera(NCamera cam) { 
		this.cam = cam;
		elements.forEach( (key, element) -> element.setCamera(cam) );
	}
	
	public void setActionListener(NActionListener listener) {
		elements.forEach((name, element) -> element.setActionListener(listener));
	}
	
	public void addElement(String name, NUIElement element) {
		String _name = name;
		int i = -1;
		do {
			i++;
		} while(elements.containsKey(i==0?_name:_name+"_"+i));
		elements.put(i==0?_name:_name+"_"+i, element);
		elements.get(i==0?_name:_name+"_"+i).name = i==0?_name:_name+"_"+i;
		elements.get(i==0?_name:_name+"_"+i).setCamera(getCamera());
	}
	
	public void removeElement(String name) {
		if(!elements.containsKey(name)) throw new NullPointerException();
		else elements.remove(name);
	}
	
	public void perform(NInput in) {
		if(isActive())
			elements.forEach((name, element) -> element.perform(in));
	}
	
	public void draw(Graphics g, Graphics2D g2d, AffineTransform at) {
		if(isVisible())
			elements.forEach((name, element) -> element.draw(g, g2d, at));
	}
}
