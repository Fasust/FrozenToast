package main.util;

public class Timer {
	private long speed;
	private long start = 0 ,now;
	
	public Timer(long speed){
		this.speed = speed;
	}
	public void start(){
		start = System.currentTimeMillis();
		now = 0;
	}
	public boolean isFinished(){
		now = System.currentTimeMillis();
		
		if(start + speed < now){
			return true;
		}else{
			return false;
		}
	}
	public long getCurrentDuration(){
		now = System.currentTimeMillis();
		return now - start;
	}
	public void setSpeed(long speed){
		this.speed = speed;
	}
}
