package nightingale.game.phys;

import java.awt.Graphics;
import nightingale.game.NGameObject;
import nightingale.util.NCamera;

public abstract class NHitbox extends NGameObject {
	protected float angle = 0;
	
	public NHitboxPoint[] points;
	public NHitboxLine[] lines;
	
	public float getAngle() { return angle; }
	
	public void setAngle(float angle) { 
		this.angle = angle;
		if(this.angle < 0)
			this.angle += 360;
		else if(this.angle > 360)
			this.angle -= 360;
	}
	
	protected float R = 0;
	public float R() { return R; }
	
	public boolean collideWith(NHitbox hitbox) {
		try {
			for(int i=0;i<lines.length;i++)
				for(int j=0;j<hitbox.lines.length;j++)
					if (NHitboxLine.isLineIntersects(lines[i], hitbox.lines[j]))
						return true;
		}catch(NullPointerException e) {}
		return false;
	}
	
	public void draw(Graphics g, NCamera cam) {
		for(int i=0;i<lines.length;i++)
			if(cam !=null )
				g.drawLine(
					(int)cam.getX(lines[i].a.x), (int)cam.getY(lines[i].a.y),
					(int)cam.getX(lines[i].b.x), (int)cam.getY(lines[i].b.y));
			else
				g.drawLine((int)lines[i].a.x, (int)lines[i].a.y, (int)lines[i].b.x, (int)lines[i].b.y);
	}
	
	public abstract void update();
}