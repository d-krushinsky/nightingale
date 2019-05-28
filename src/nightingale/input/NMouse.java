package nightingale.input;

//BAD
public class NMouse {
	
	protected int x;
	protected int y;
	
	protected boolean leftButton = false;
	protected boolean rightButton = false;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isLeftButtonPressed() {
		return leftButton;
	}
	
	public boolean isRightButtonPressed() {
		return rightButton;
	}
	
	protected void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
