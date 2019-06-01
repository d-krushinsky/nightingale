package nightingale.game.map;

import nightingale.util.NCamera;

public class NMap {
	protected float x, y;
	protected float width, height;
	
	protected NMapTile[][] tiles;
	
	// Getters
	public float getX() { return x; }
	public float getX(NCamera cam) { return cam.getX(getX()); }
		
	public float getY() { return y; }
	public float getY(NCamera cam) { return cam.getY(getY()); }
	
	public float getWidth() { return width; }
	public float getWidth(NCamera cam) { return cam.scale(getWidth()); }
		
	public float getHeight() { return height; }
	public float getHeight(NCamera cam) { return cam.scale(getHeight()); }
	
	// Setters
	public void setX(float x) { 
		this.x = x;
		for(int i=0;i<tiles.length;i++)
			for(int j=0;j<tiles[0].length;j++)
				tiles[i][j].setX(getX()+(j*tiles[i][j].getWidth()));
	}
	
	public void setX(float x, NCamera cam) {
		this.x = cam.returnX(x);
		for(int i=0;i<tiles.length;i++)
			for(int j=0;j<tiles[0].length;j++)
				tiles[i][j].setX(getX()+(j*tiles[i][j].getWidth()), cam);
	}
	
	public void setY(float y) { 
		this.y = y;
		for(int i=0;i<tiles.length;i++)
			for(int j=0;j<tiles[0].length;j++)
				tiles[i][j].setY(getY()+(i*tiles[i][j].getHeight()));
	}
	
	public void setY(float y, NCamera cam) {
		this.y = y;
		for(int i=0;i<tiles.length;i++)
			for(int j=0;j<tiles[0].length;j++)
				tiles[i][j].setY(getY()+(i*tiles[i][j].getHeight()), cam);
	}
	
	protected void setWidth(float width) { this.width = width; }
	protected void setWidth(float width, NCamera cam) { this.width = cam.unscale(width); }
	
	protected void setHeight(float height) { this.height = height; }
	protected void setHeight(float height, NCamera cam) { this.height = cam.unscale(height); }
	
	public NMap(int[][] tilesTypes, int[] impassableTypes, int x, int y, int tileWidth, int tileHeight) {
		tiles = new NMapTile[tilesTypes.length][tilesTypes[0].length];
		for(int i=0;i<tiles.length;i++) {
			for(int j=0;j<tiles[0].length;j++) {
				tiles[i][j] = new NMapTile(tilesTypes[i][j], i, j);
				tiles[i][j].setWidth(tileWidth);
				tiles[i][j].setHeight(tileHeight);
				if( impassableTypes != null)
					for(int _i=0;_i<impassableTypes.length;_i++)
						if(impassableTypes[_i]==tiles[i][j].getType()) {
							tiles[i][j].walkable = false;
							break;
						}
			}
		}
		setX(x);
		setY(y);
		setWidth(tileWidth*tiles[0].length);
		setHeight(tileHeight*tiles.length);
	}	
}