package nightingale.game.phys;

public class NHitboxLine {
	public NHitboxPoint a, b;
	
	protected NHitboxLine(NHitboxPoint a, NHitboxPoint b) {
		this.a = a;
		this.b = b;
	}
	
	public float length() {
		return (float)( Math.sqrt( Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y, 2) ) );
	}
	
	protected void setPoints(NHitboxPoint a, NHitboxPoint b) {
		this.a = a;
		this.b = b;
	}
	
	public static boolean isLineIntersects(NHitboxLine l1, NHitboxLine l2) {
		double common = (l1.b.x - l1.a.x)*(l2.b.y - l2.a.y)
				-(l1.b.y - l1.a.y)*(l2.b.x - l2.a.x);

			if( common == 0 ) return false;

			double rH = (l1.a.y - l2.a.y)*(l2.b.x - l2.a.x)
				   -(l1.a.x - l2.a.x)*(l2.b.y - l2.a.y);
			double sH = (l1.a.y - l2.a.y)*(l1.b.x - l1.a.x)
				   -(l1.a.x - l2.a.x)*(l1.b.y - l1.a.y);
			double r = rH / common;
			double s = sH / common;

			if( r>= 0 && r<= 1 && s >= 0 & s<= 1) 
				return true;
			else return false;
	}
}
