package nightingale.state;

import nightingale.graph.NDrawer;

public interface NState extends NDrawer{

	public void update();
	public void install();
	
}
