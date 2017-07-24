package main.entity.dynamic.push;

import java.awt.Graphics;
import main.Handler;
import main.audio.SoundAssets;
import main.entity.dynamic.DynamicEntitys;
import main.entity.dynamic.slide.FrozenDeadGreenFish;
import main.entity.dynamic.slide.FrozenToast;
import main.tile.Tile;

public abstract class PushEntity extends DynamicEntitys{
	
	private boolean firstTouch = true;
	private boolean firstHit = true;
	
	protected float slideSpeed,xMove,yMove;
	private char lastSlideDirektion;
	
	private boolean firstInFreeze = true;

	public PushEntity(Handler hand,int id, float x, float y, int breite, int hoehe) {
		super(hand,id, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, breite, hoehe);
		xMove = 0;
		yMove = 0;
		
		//Checkbox
		checkbox.x = -3;
		checkbox.y = -3;
		checkbox.width= Tile.TILEBREITE+6;
		checkbox.height = Tile.TILEHoeHE+6;
		
		//hitbox
		bounds.x =2;
		bounds.y =2;
		bounds.width = Tile.TILEBREITE-4;
		bounds.height= Tile.TILEHoeHE-4;
		
		
	}
	@Override
	public void tick() {
		//Movement
		if(isPlayerTouching()){		//PlayerPush
			if(firstTouch){
				SoundAssets.ice_push.play();
			}else{
				firstInFreeze=true;
			}
			slideSpeed = hand.getWorld().getEnti().getPl().getSpeed();
			setPlayerPushSlideDirektion();
			firstTouch = false;
			if(!recoveryMode){
				pushing();
			}else{
				recoveryPushing();
			}
		}
		if(this.isPistonPushing()){//PistonPush
			if(firstTouch){
				SoundAssets.ice_push.play();
			}else{
				firstInFreeze=true;
			}
			slideSpeed = this.getPistonPushing().getPushSpeed();
			setPistonPushSlideDirektion();
			firstTouch = false;
			pushing();
		}
		xMove = 0;
		yMove = 0;
		
		//Freeze
		if(checkPlayerFreeze()){
			freezeEntity();
		}
		
		
		overlappingPlaySavetyFeture();
	}
	@Override
	public abstract void render(Graphics g);
	
	@Override
	public void moveX(){
		if(xMove > 0){ //moving right
			int tx = (int) (x + xMove + bounds.x+bounds.width)/Tile.TILEBREITE;
			if(!collisionWithTile(tx,(int) (y + bounds.y)/Tile.TILEHoeHE)&&
					!collisionWithTile(tx,(int)(y+bounds.y + bounds.height)/Tile.TILEHoeHE)){
				x+= xMove;
				firstHit = true;
				
			}else{
				x = tx * Tile.TILEBREITE - bounds.x - bounds.width -1;
				xMove = 0;
				yMove = 0;
				
				if(firstHit){
					SoundAssets.ice_hit.play();
					firstHit = false;
					firstTouch = true;
				}
			}
		}else if(xMove < 0){ //moving left
			int tx = (int) (x + xMove + bounds.x)/Tile.TILEBREITE;
			if(!collisionWithTile(tx,(int) (y + bounds.y)/Tile.TILEHoeHE)&&
					!collisionWithTile(tx,(int)(y+bounds.y + bounds.height)/Tile.TILEHoeHE)){
				x+= xMove;
				firstHit = true;
			}else{
				x = tx * Tile.TILEBREITE + Tile.TILEBREITE - bounds.x;
				xMove = 0;
				yMove = 0;
				
				
				if(firstHit){
					SoundAssets.ice_hit.play();
					firstHit = false;
					firstTouch = true;
				}
			}
		}
	}
	@Override
	public void moveY(){
		if(yMove < 0){ //Up
			int ty = (int) (y +yMove + bounds.y)/ Tile.TILEHoeHE;
			if(!collisionWithTile((int)(x+ bounds.x) / Tile.TILEBREITE,ty)&&
					!collisionWithTile((int)(x+ bounds.x + bounds.width) / Tile.TILEBREITE,ty)){
				y+=yMove;
				firstHit = true;
			}else{
				y= ty * Tile.TILEHoeHE + Tile.TILEHoeHE -bounds.y;
				xMove = 0;
				yMove = 0;
				
				if(firstHit){
					SoundAssets.ice_hit.play();
					firstHit = false;
					firstTouch = true;
				}
			}
			
		}else if(yMove > 0){ //DOwn
			int ty = (int) (y +yMove + bounds.y+ bounds.height)/ Tile.TILEHoeHE;
			if(!collisionWithTile((int)(x+ bounds.x) / Tile.TILEBREITE,ty)&&
					!collisionWithTile((int)(x+ bounds.x + bounds.width) / Tile.TILEBREITE,ty)){
				y+=yMove;
				firstHit = true;
			}else{
				y= ty* Tile.TILEHoeHE - bounds.y -bounds.height-1;
				xMove = 0;
				yMove = 0;
			
				if(firstHit){
					SoundAssets.ice_hit.play();
					firstHit = false;
					firstTouch = true;
				}
			}
		}
	}
	
	private boolean checkPlayerFreeze(){
		return hand.getWorld().getEnti().getPl().getCheckbox(0,0).intersects(this.getCollisionBounds(0, 0))&&
				hand.getWorld().getEnti().getPl().getIceState().isFreezing()&&
				firstInFreeze;
	}
	private void freezeEntity(){
		if(this instanceof Toast){
			this.setActiv(false);
			SoundAssets.freeze.play();
			FrozenToast ft0s = new FrozenToast(hand,this.x/Tile.TILEBREITE,this.y/Tile.TILEHoeHE);
			hand.getWorld().getEnti().addEnti(ft0s, name);
			
			firstInFreeze=false;
		}
		if(this instanceof DeadGreenFish){
			
			this.setActiv(false);
			FrozenDeadGreenFish fdf0s = new FrozenDeadGreenFish(hand,this.x/Tile.TILEBREITE,this.y/Tile.TILEHoeHE);
			hand.getWorld().getEnti().addEnti(fdf0s, name);
			firstInFreeze=false;
		}
	}
	public void setPlayerPushSlideDirektion(){
		char CurrentSlideDriek = hand.getWorld().getEnti().getPl().getFacing();
		switch(CurrentSlideDriek){
		case 'n':
			if(hand.getWorld().getEnti().getPl().getCollisionBounds(0, 0).getMaxY()>y+this.bounds.height)
				yMove = -slideSpeed;
			break;
		case 'e':
			if(hand.getWorld().getEnti().getPl().getCollisionBounds(0, 0).getMaxX()-2<x)
				xMove = slideSpeed;
			break;
		case 's':
			if(hand.getWorld().getEnti().getPl().getCollisionBounds(0, 0).getMinY()<y)
				yMove = slideSpeed;
			break;
		case 'w':
			if(hand.getWorld().getEnti().getPl().getCollisionBounds(0, 0).getMinX()>x+this.bounds.width)
				xMove = -slideSpeed;
			break;
		}
		if(lastSlideDirektion != CurrentSlideDriek){
			SoundAssets.ice_push.play();
		}
		lastSlideDirektion = CurrentSlideDriek; 
	}
	public void setPistonPushSlideDirektion(){
		char CurrentSlideDriek = getPistonPushing().getPushDirektion();
		switch(CurrentSlideDriek){
		case 'n':
				yMove = -slideSpeed;
			break;
		case 'e':
				xMove = slideSpeed;
			break;
		case 's':
				yMove = slideSpeed;
			break;
		case 'w':
				xMove = -slideSpeed;
			break;
		}
		if(lastSlideDirektion != CurrentSlideDriek){
			SoundAssets.ice_push.play();
		}
		lastSlideDirektion = CurrentSlideDriek; 
	}
	public void pushing(){
		if(!checkEntityCollisionNotIncludingSemiSolid(xMove,0f)){
			moveX();
		}else{
			xMove = 0;
			yMove = 0;
			if(firstHit){
				SoundAssets.ice_hit.play();
				firstHit = false;
				firstTouch = true;
			}
		}
		if(!checkEntityCollisionNotIncludingSemiSolid(0f,yMove)){
			moveY();
		}else{
			yMove = 0;
			xMove = 0;
			if(firstHit){
				SoundAssets.ice_hit.play();
				firstHit = false;
				firstTouch = true;
			}
		}
	}
	public void recoveryPushing(){
		if(!this.checkEntityCollisionNotIncludingSemiSolidandPlayer(xMove,0f)){
			xMove = 5;
			moveX();
		}else{
			xMove = 0;
			yMove = 0;
			if(firstHit){
				SoundAssets.ice_hit.play();
				firstHit = false;
				firstTouch = true;
			}
		}
		if(!checkEntityCollisionNotIncludingSemiSolidandPlayer(0f,yMove)){
			yMove = 5;
			moveY();
			
		}else{
			yMove = 0;
			xMove = 0;
			if(firstHit){
				SoundAssets.ice_hit.play();
				firstHit = false;
				firstTouch = true;
			}
		}
	}
	
	public void stopSlideing(){
		xMove = 0;
		yMove = 0;
		firstTouch = true;
	}
	
	//getters and setters
	
	public float getSlideSpeed() {
		return slideSpeed;
	}
	public void setSlideSpeed(float slideSpeed) {
		this.slideSpeed = slideSpeed;
	}
	public float getxMove() {
		return xMove;
	}
	public void setxMove(float xMove) {
		this.xMove = xMove;
	}
	public float getyMove() {
		return yMove;
	}
	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	

}
