package main.gfx;
import java.awt.image.BufferedImage;

public class Animation {

	private int speed, index;
	private long lastTime,timer;
	private BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] frames){
		this.speed = speed;
		this.frames = frames;
		index = 1;
		lastTime = System.currentTimeMillis();
		timer = 0;
	}
	public void tick(){
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer>speed){
			index++;
			timer=0;
			if(index >= frames.length)
				index =1;
		}
	}
	public void reset(){
		index =1;
	}
	
	 //getters and setter below
	public BufferedImage getCurrentFrame(){
		return frames[index];
	}
	public BufferedImage getReverseCurrentFrame(){
		return frames[(frames.length-1)-(index)];
	}
	public BufferedImage getStillFrame(){
		return frames[0];
	}
	public void setAnimSpeed(int speed){
		this.speed = speed;
	}
	
}
