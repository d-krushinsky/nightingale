package nightingale.input;

public class NKey{
	private boolean pressed = false;
	private boolean clicked = false;
	
	public boolean isPressed() {
		return pressed;
	}
	
	public boolean isClicked(){
		boolean result = false;
		if(pressed & !clicked) {
			result = true;
			clicked = true;
		}
		return result;
	}
	
	public void toggle(boolean isPressed) {
		pressed = isPressed;
		if(!pressed) clicked = false;
	}
}
