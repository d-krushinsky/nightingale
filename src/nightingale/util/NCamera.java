package nightingale.util;

public class NCamera {
	//Camera coordinates
	public float x = 0, y = 0;
	//Scale coefficient
	public float delta = 1;
	
	//Return processed X
	public float getX(float x) {
		return ((x*this.delta)+this.x);
	}
	
	//Return real X from processed X
	public float returnX(float x) {
		return (x-this.x)/this.delta;
	}
	
	//Return processed Y
	public float getY(float y) {
		return ((y*this.delta)+this.y);
	}
	
	//Return real Y from processed Y
	public float returnY(float y) {
		return (y-this.y)/this.delta;
	}
	
	//Return processed value
	public float scale(float value) {
		return value*this.delta;
	}
	
	//Return real X from processed X
	public float unscale(float value) {
		return value/this.delta;
	}
}
