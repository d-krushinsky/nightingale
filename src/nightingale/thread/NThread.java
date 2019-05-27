package nightingale.thread;

public class NThread extends java.lang.Thread {

	private Runnable fixRunnable;

	public int maxRate = 60;
	private int TICKS;
	
	public NThread(String name, Runnable fixRunnable){
		this.fixRunnable = fixRunnable;
		setName(name);
	}
	
	public int getTicks(){
		return TICKS;
	}

    @Override
	public void run(){
    	long lastTime = System.nanoTime();
    	double nsPerTick = 1000000000/60D;
    	int ticks = 0;
    	TICKS = 0;
    	long lastTimer = System.currentTimeMillis();
    	double delta_ticks = 0;
    	boolean shouldUpdate = false;
    	
    	while(true){
			shouldUpdate = false;
			long now = System.nanoTime();
			delta_ticks += (now - lastTime)/nsPerTick;
			lastTime = now;
			while(delta_ticks >= 1){
				delta_ticks-=(60.0/maxRate);
				shouldUpdate = true;
			}
			if(shouldUpdate){
				ticks++;
				fixRunnable.run();
			}
			try{
				Thread.sleep(2);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			if(System.currentTimeMillis()-lastTimer >= 1000){
				lastTimer+=1000;
				TICKS = ticks;
				ticks = 0;
			}
		}
	}
}