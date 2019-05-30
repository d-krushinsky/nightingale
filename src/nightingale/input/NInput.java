package nightingale.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class NInput implements KeyListener, MouseListener, MouseMotionListener{
	private NMouse mouse = null;
	
	public void setMouse(NMouse mouse) {
		this.mouse = mouse;
	}
	
	public NMouse getMouse() {
		return mouse;
	}
	
	@Override
	public final void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);
	}

	@Override
	public final void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouse.move(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse.move(e.getX(), e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			mouse.leftButton.toggle(true);
		if(e.getButton() == MouseEvent.BUTTON3)
			mouse.rightButton.toggle(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			mouse.leftButton.toggle(false);
		if(e.getButton() == MouseEvent.BUTTON3)
			mouse.rightButton.toggle(false);
	}
	
	/**
	 * @param keyCode
	 * @param isPressed
	 */
	public abstract void toggleKey(int keyCode, boolean isPressed);
	
	
	//Unuse
	@Override
	public final void mouseClicked(MouseEvent arg0) {}
	@Override
	public final void mouseEntered(MouseEvent arg0) {}
	@Override
	public final void mouseExited(MouseEvent arg0) {}
	@Override
	public final void keyTyped(KeyEvent e) {}
}
