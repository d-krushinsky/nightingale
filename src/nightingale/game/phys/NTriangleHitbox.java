package nightingale.game.phys;

public class NTriangleHitbox extends NHitbox{

	private NHitboxPoint[] delta = new NHitboxPoint[3];
	
	public NTriangleHitbox(float x1, float y1, float x2, float y2, float x3, float y3) {
		points = new NHitboxPoint[3];
		lines = new NHitboxLine[3];
		
		points[0] = new NHitboxPoint(x1, y1);
		points[1] = new NHitboxPoint(x2, y2);
		points[2] = new NHitboxPoint(x3, y3);
		
		lines[0] = new NHitboxLine(points[0], points[1]);
		lines[1] = new NHitboxLine(points[1], points[2]);
		lines[2] = new NHitboxLine(points[2], points[0]);
		
		float minx = points[0].x;
		float maxx = points[0].x;
		float miny = points[0].y;
		float maxy = points[0].y;
		for(int i=0;i<points.length;i++) {
			if(minx > points[i].x) minx = points[i].x;
			if(maxx < points[i].x) maxx = points[i].x;
			if(miny > points[i].y) miny = points[i].y;
			if(maxy < points[i].y) maxy = points[i].y;
		}
		
		setX(minx);
		setY(miny);
		setWidth(maxx - minx);
		setHeight(maxy - miny);
		
		for(int i=0;i<delta.length;i++)
			delta[i] = new NHitboxPoint(points[i].x - getX(), points[i].y - getY());
		
		/*
		      a*b*c                a*b*c
		 R = ------- = -------------------------------
		       4*S      4 * sqrt(p*(p-a)*(p-b)*(p-c))
		 */
		
		float p = (lines[0].length()+lines[1].length()+lines[2].length())/2;
		
		R = (float) ( (lines[0].length()*lines[1].length()*lines[2].length())
			/ (4 * Math.sqrt( p * (p - lines[0].length()) * (p-lines[1].length()) * (p - lines[2].length()) )) );
		
		update();
	}
	
	@Override
	public void update() {
		for(int i=0;i<points.length;i++) {
			points[i].x = getX() + delta[i].x;
			points[i].y = getY() + delta[i].y;
		}
		
		//angle
		for(int i=0;i<points.length;i++){
			points[i].angle = (float)(Math.atan2(getCenterY()-points[i].y, getCenterX()-points[i].x));
			points[i].x = getCenterX() - (float)(R*Math.cos(
			(Math.PI*angle/180)+points[i].angle));
			points[i].y = getCenterY() - (float)(R*Math.sin(
			(Math.PI*angle/180)+points[i].angle));
		}
		
		lines[0].setPoints(points[0], points[1]);
		lines[1].setPoints(points[1], points[2]);
		lines[2].setPoints(points[2], points[0]);
	}
	
}
