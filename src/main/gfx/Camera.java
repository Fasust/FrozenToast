package main.gfx;
import main.Handler;
import main.entity.Entity;
import main.tile.Tile;
import main.util.Timer;

public class Camera {
	private float xOffset, yOffset;
	private Handler hand;
	
	//Logic
	private Boolean firstInScreenShake = true;
	
	private Timer durcTimer;
	private Timer speedTimer;
	private Boolean onOfSwitch = false;
	private Boolean screenShakeFinsihed = false;
	
	public Camera(Handler hand,float xOffset, float yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.hand = hand;
	}
	public void centerOnEntity(Entity e){
		xOffset = e.getX() - hand.getBreite()/2 + e.getBreite()/2;
		yOffset = e.getY() - hand.getHoehe()/2 + e.getHoehe() / 2;
		checkBlankSpace();
	}

	public void move(float xAmt, float yAmt){
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}
	public void checkBlankSpace(){
		if(xOffset < 0)
			xOffset = 0;
		else if(xOffset > hand.getWorld().getBreite() * Tile.TILEBREITE - hand.getBreite())
			xOffset =  hand.getWorld().getBreite() * Tile.TILEBREITE - hand.getBreite();
		if(yOffset <0)
			yOffset =0;
		else if(yOffset > hand.getWorld().getHoehe() * Tile.TILEHoeHE - hand.getHoehe() )
			yOffset = hand.getWorld().getHoehe() * Tile.TILEHoeHE - hand.getHoehe();
	}
	public void screenShake(long durc,long speed,float intensX,float intensY){
		if(firstInScreenShake){
			durcTimer = new Timer(durc);
			speedTimer = new Timer(speed);
			durcTimer.start();
			speedTimer.start();
			firstInScreenShake = false;
			screenShakeFinsihed = false;
		}
		if(!durcTimer.isFinished()){
			
			if(!speedTimer.isFinished()){
				if(onOfSwitch){
					panY(intensY);
					panX(intensX);
					
				}else{
					panY(-intensY);
					panX(-intensX);
				}
				
			}else{
				onOfSwitch();
				speedTimer.start();
			}
		}else{
			screenShakeFinsihed = true;
			firstInScreenShake = true;
		}
	}
	public void panY(float speed){
		yOffset += speed;
	}
	public void panX(float speed){
		xOffset += speed;
	}
	private void onOfSwitch(){
		if(onOfSwitch){
			onOfSwitch = false;
		}else{
			onOfSwitch = true;
		}
	}
	
	//getters and setters below
	public float getxOffset() {
		return xOffset;
	}
	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}
	public float getyOffset() {
		return yOffset;
	}
	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
	public Boolean getScreenShakeFinsihed() {
		return screenShakeFinsihed;
		
	}
	public void setScreenShakeFinsihed(Boolean screenShakeFinsihed) {
		this.screenShakeFinsihed = screenShakeFinsihed;
	}
	
}
