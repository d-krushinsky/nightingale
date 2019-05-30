package nightingale.input;

//BAD
public class NMouse {
	protected NKey leftButton = new NKey();
	protected NKey rightButton =  new NKey();
	
	protected int x;
	protected int y;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isLeftButtonPressed() {
		return leftButton.isPressed();
	}
	
	public boolean isRightButtonPressed() {
		return rightButton.isPressed();
	}
	
	public boolean isLeftButtonClicked() {
		return leftButton.isClicked();
	}
	
	public boolean isRightButtonClicked() {
		return rightButton.isClicked();
	}
	
	protected void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
