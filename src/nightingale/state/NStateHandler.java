package nightingale.state;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.HashMap;

public class NStateHandler {

	protected HashMap<String, NState> states = new HashMap<String, NState>();
	protected NState currentState = null;
	
	public void addState(String name, NState state) {
		states.put(name, state);
		if(currentState == null)
			currentState = states.get(name);
	}
	
	public NState getCurrentState() { return currentState; }
	
	public void setState(String name) {
		if(states.containsKey(name))
			currentState = states.get(name);
		//else
		//	NLogger.push("States map does not contains '"+name+"' state.");
	}
	
	public void updateCurrentState() throws NullPointerException{
		currentState.update();
	}
	
	public void drawCurrentState(Graphics g, Graphics2D g2d, AffineTransform at) throws NullPointerException{
		currentState.draw(g, g2d, at);
	}
	
}
