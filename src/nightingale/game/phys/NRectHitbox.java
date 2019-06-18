package nightingale.game.phys;

public class NRectHitbox extends NHitbox{

	public NRectHitbox(float x, float y, float w, float h) {
		super();
		setX(x);
		setY(y);
		setWidth(w);
		setHeight(h);
		
		points = new NHitboxPoint[4];
		lines = new NHitboxLine[4];
		
		for(int i=0;i<points.length;i++)
			points[i] = new NHitboxPoint(0, 0);
		for(int i=0;i<lines.length;i++)
			lines[i] = new NHitboxLine(null, null);
		
		R = (float)Math.sqrt( Math.pow(width/2 , 2) + Math.pow(height/2, 2) );
		update();
	}
	
	@Override
	public void update()  {
		//A
		points[0].x = getCenterX() -  width/2;
		points[0].y = getCenterY() - height/2;				        
		//B
		points[1].x = getCenterX() +  width/2;
		points[1].y = getCenterY() - height/2;
		//C
		points[2].x = getCenterX() +  width/2;
		points[2].y = getCenterY() + height/2;
		//D
		points[3].x = getCenterX() -  width/2;
		points[3].y = getCenterY() + height/2;
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
		lines[2].setPoints(points[2], points[3]);
		lines[3].setPoints(points[3], points[0]);
	}
}