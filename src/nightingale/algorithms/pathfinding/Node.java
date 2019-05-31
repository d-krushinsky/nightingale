package nightingale.algorithms.pathfinding;

public class Node {

	public boolean walkable = true;
	public int i, j;
	public Node parent = null;
	
	public Node(int i, int j, boolean walkable) {
		this.i = i;
		this.j = j;
		this.walkable = walkable;
	}
}
