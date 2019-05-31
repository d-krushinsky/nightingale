package nightingale.algorithms.pathfinding;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {
	/**
	 * @param nodeTable
	 * @param start
	 * @param end
	 * @param canWalkDiagonals
	 * @return path(List of nodes)
	 */
	public static List<Node> search(Node[][] nodeTable, Node start, Node end, boolean canWalkDiagonals){
		List<Node> result = new ArrayList<Node>();
		for(int i=0;i<nodeTable.length;i++) {
			for(int j=0;j<nodeTable[0].length;j++) {
				nodeTable[i][j].parent = null;
			}
		}
		
		boolean finded = false;
		List<Node> open = new ArrayList<Node>();
		List<Node> closed = new ArrayList<Node>();
		closed.add(start);
		Node current = start;
		current.parent = start;
		
		int n = nodeTable.length;
		int k = nodeTable[0].length;
		
		while(!finded){
			int i = current.i;
			int j = current.j;
			
			if(i>0 && nodeTable[i-1][j].walkable && !open.contains(nodeTable[i-1][j]) && !closed.contains(nodeTable[i-1][j])) open.add(nodeTable[i-1][j]);
			if(i<n-1 && nodeTable[i+1][j].walkable && !open.contains(nodeTable[i+1][j]) && !closed.contains(nodeTable[i+1][j])) open.add(nodeTable[i+1][j]);
			if(j>0 && nodeTable[i][j-1].walkable && !open.contains(nodeTable[i][j-1]) && !closed.contains(nodeTable[i][j-1])) open.add(nodeTable[i][j-1]);
			if(j<k-1 && nodeTable[i][j+1].walkable && !open.contains(nodeTable[i][j+1]) && !closed.contains(nodeTable[i][j+1])) open.add(nodeTable[i][j+1]);
			if(canWalkDiagonals) {
				if(i>0 && j>0 && nodeTable[i-1][j-1].walkable && !open.contains(nodeTable[i-1][j-1]) && !closed.contains(nodeTable[i-1][j-1])) open.add(nodeTable[i-1][j-1]);
				if(i<n-1 && j>0 && nodeTable[i+1][j-1].walkable && !open.contains(nodeTable[i+1][j-1]) && !closed.contains(nodeTable[i+1][j-1])) open.add(nodeTable[i+1][j-1]);
				if(i>0 && j<k-1 && nodeTable[i-1][j+1].walkable && !open.contains(nodeTable[i-1][j+1]) && !closed.contains(nodeTable[i-1][j+1])) open.add(nodeTable[i-1][j+1]);
				if(i<n-1 && j<k-1 && nodeTable[i+1][j+1].walkable && !open.contains(nodeTable[i+1][j+1]) && !closed.contains(nodeTable[i+1][j+1])) open.add(nodeTable[i+1][j+1]);
			}
			
			double min = -1;
			Node mNode = null;
			for(Node no : open){
				double length = Math.sqrt(Math.pow(end.j-no.j, 2)+Math.pow(end.i-no.i, 2));
				if(no.parent == null) no.parent = current;
				if(min==-1 || min>length){
					min = length;
					mNode = no;
				}
			}

			if( mNode != null){
				closed.add(current);
				open.remove(current);
				current = mNode;
			}else
				finded = true;

			if(current == end) {
				boolean finished = false;
				Node no = current;
				while(!finished){
					if(no == start) {
						finished = true;
					}else{
						result.add(no);
						no = no.parent;
					}
				}
				finded = true;
			}
		}
		
		List<Node> _result = new ArrayList<Node>();
		for(int i=result.size()-1;i>0;i--)
			_result.add(result.get(i));
		
		return _result;
	}
}
