package main.entity.creature;
import main.Handler;
import main.entity.Entity;
import main.tile.Tile;

public abstract class Creature extends Entity {
	public static final float DEF_SPEED = (float) 3.7;
	public static final int DEF_BREITE = 64,
							DEF_HoeHE = 64;
	
	public static final int DEF_ANIM_SPEED = 300;
	
	//Hitting Tiels
	private boolean hitRight,hitLeft,hitUp,hitDown,eHitX,eHitY;
	
	//Logic HitDetection
	private boolean passThroughSemiSolid = false;
	private boolean walkOnWater = false;
	
	protected float speed,xMove,yMove;
	protected char facing = 's';
	
	public Creature(Handler hand,int id,float x, float y,int breite, int hoehe) {
		super(hand,id, x, y,breite,hoehe);
		this.speed = DEF_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move(){
		eHitX = true;
		eHitY = true;
		if(passThroughSemiSolid){
			if(!checkEntityCollisionNotIncludingSemiSolid(xMove,0f)){
				moveX();
				eHitX = false;
			}
			if(!checkEntityCollisionNotIncludingSemiSolid(0f,yMove)){
				moveY();
				eHitY = false;
			}
		}else if(walkOnWater){
			if(!checkEntityCollisionNotIncludingWater(xMove,0f)){
				moveX();
				eHitX = false;
			}
			if(!checkEntityCollisionNotIncludingWater(0f,yMove)){
				moveY();
				eHitY = false;
			}
		}else{
			if(!checkEntityCollision(xMove,0f)){
				moveX();
				eHitX = false;
			}
			if(!checkEntityCollision(0f,yMove)){
				moveY();
				eHitY = false;
			}
		}
	}
	public void moveX(){
		hitRight = false;
		hitLeft = false;
		hitUp = false;
		hitDown = false;
		
		
		if(xMove > 0){ //moving right
			int tx = (int) (x + xMove + bounds.x+bounds.width)/Tile.TILEBREITE;
			if(!collisionWithTile(tx,(int) (y + bounds.y)/Tile.TILEHoeHE)&&
					!collisionWithTile(tx,(int)(y+bounds.y + bounds.height)/Tile.TILEHoeHE)){
				x+= xMove;
				
			}else{
				x = tx * Tile.TILEBREITE - bounds.x - bounds.width -1;
				hitRight = true;
			}
		}else if(xMove < 0){ //moving left
			int tx = (int) (x + xMove + bounds.x)/Tile.TILEBREITE;
			if(!collisionWithTile(tx,(int) (y + bounds.y)/Tile.TILEHoeHE)&&
					!collisionWithTile(tx,(int)(y+bounds.y + bounds.height)/Tile.TILEHoeHE)){
				x+= xMove;
			
			}else{
				x = tx * Tile.TILEBREITE + Tile.TILEBREITE - bounds.x;
				hitLeft = true;
			}
		}
	}
	public void moveY(){
		if(yMove < 0){ //Up
			int ty = (int) (y +yMove + bounds.y)/ Tile.TILEHoeHE;
			if(!collisionWithTile((int)(x+ bounds.x) / Tile.TILEBREITE,ty)&&
					!collisionWithTile((int)(x+ bounds.x + bounds.width) / Tile.TILEBREITE,ty)){
				y+=yMove;
				
			}else{
				y= ty * Tile.TILEHoeHE + Tile.TILEHoeHE -bounds.y;
				hitUp = true;
			}
			
		}else if(yMove > 0){ //DOwn
			int ty = (int) (y +yMove + bounds.y+ bounds.height)/ Tile.TILEHoeHE;
			if(!collisionWithTile((int)(x+ bounds.x) / Tile.TILEBREITE,ty)&&
					!collisionWithTile((int)(x+ bounds.x + bounds.width) / Tile.TILEBREITE,ty)){
				y+=yMove;
				
			}else{
				y= ty* Tile.TILEHoeHE - bounds.y -bounds.height-1;
				hitDown = true;
			}
		}
	}
	protected boolean collisionWithTile(int x, int y){
		return hand.getWorld().getTile(x, y).isSolid();
	}
	
	
 //Geters and Seters Below
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

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public boolean isHitRight() {
		return hitRight;
	}

	public boolean isHitLeft() {
		return hitLeft;
	}

	public boolean isHitUp() {
		return hitUp;
	}

	public boolean isHitDown() {
		return hitDown;
	}

	public boolean iseHitX() {
		return eHitX;
	}

	public boolean iseHitY() {
		return eHitY;
	}

	public boolean isPassThroughSemiSolid() {
		return passThroughSemiSolid;
	}

	public void setPassThroughSemiSolid(boolean passThroughSemiSolid) {
		this.passThroughSemiSolid = passThroughSemiSolid;
	}

	public boolean isWalkOnWater() {
		return walkOnWater;
	}

	public void setWalkOnWater(boolean walkOnWater) {
		this.walkOnWater = walkOnWater;
	}

}
