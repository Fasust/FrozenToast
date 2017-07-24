package main.entity.dynamic;


import main.Handler;
import main.entity.Entity;
import main.entity.statics.InvisCheckbox;
import main.entity.statics.multistate.piston.Piston;
import main.tile.Tile;
import main.util.Timer;

public abstract class DynamicEntitys extends Entity {
	protected boolean isBeingPorted = false;
	protected boolean firstInSavety = true;
	protected boolean recoveryMode = false;
	private Timer timer;

	public DynamicEntitys(Handler hand,int id, float x, float y, int breite, int hoehe) {
		super(hand,id, x, y, breite, hoehe);
	}
	public abstract void moveX();
	public abstract void moveY();
	
	
	public boolean isPlayerTouching(){
		if(hand.getWorld().getEnti().getPl().getCollisionBounds(0f,0f).intersects(getCheckbox(0,0))){
			return true;
		}else{
			return false;
		}
	}
	
	protected boolean collisionWithTile(int x, int y){
		return hand.getWorld().getTile(x, y).isSolid();
	}
	
	protected void overlappingPlaySavetyFeture(){
		if(this.getCollisionBounds(0, 0).intersects(hand.getWorld().getEnti().getPl().getCollisionBounds(0, 0))){
			if(firstInSavety){
				timer = new Timer(100);
				timer.start();
				
				firstInSavety = false;
			}
			if(timer.isFinished()){
				firstInSavety = true;
				recoveryMode= true;
			}
		}else{
			recoveryMode = false;
		}
	}
	
	
	
	//Getters seteres
	public boolean isBeingPorted() {
		return isBeingPorted;
	}
	public void setBeingPorted(boolean isBeingPorted) {
		this.isBeingPorted = isBeingPorted;
	}
	
}
