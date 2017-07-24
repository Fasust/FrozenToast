package main.entity;
import java.awt.Graphics;
import java.awt.Rectangle;
import main.Handler;
import main.entity.creature.Player;
import main.entity.statics.InvisCheckbox;
import main.entity.statics.multistate.piston.Piston;
import main.entity.statics.water.Water;

public abstract class Entity {
	
	protected Handler hand;
	protected String name = "notNamed";
	public int id;
	protected float x, y;
	protected int breite,hoehe;
	protected Rectangle bounds;
	protected Rectangle checkbox;
	
	protected boolean interacted = false;
	
	//Hight
	protected int hight = 5;
	/*
	 * 10 = GUI
	 * 9
	 * 8
	 * 7
	 * 6 = Stone Gates top
	 * 5 = Creature (Dynamic)=
	 * 4 = Holes in wall
	 * 3 = Push Entity  (Dynamic)
	 * 2 = Buttons
	 * 1 = frozen Water
	 * 0
	 */
	
	
	protected boolean semiSolid = false;
	protected boolean CreaturePass = false;
	
	protected boolean isActiv = true;
	
	public Entity(Handler hand,int id,float x, float y,int breite, int hoehe){
		this.x = x;
		this.y = y;
		this.id = id;
		this.breite = breite;
		this.hoehe = hoehe;
		this.hand = hand;
		
		bounds = new Rectangle(0,0,breite,hoehe);
		checkbox = new Rectangle(0,0,breite,hoehe);
	}
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	protected void checkInteraction(){
		if(hand.getWorld().getEnti().getPl().getCheckbox(0, 0).intersects(this.getCollisionBounds(0, 0))){
			interacted = true;
		}else{
			interacted = false;
		}
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset){
		return new Rectangle((int) (x+ bounds.x + xOffset),(int) (y+ bounds.y + yOffset),bounds.width,bounds.height);
	}
	public Rectangle getCollisionBounds(float xOffset, float yOffset,int breite,int hoehe){
		return new Rectangle((int) (x+ bounds.x + xOffset),(int) (y+ bounds.y + yOffset),breite,hoehe);
	}
	public Rectangle getCheckbox(float xOffset, float yOffset){
		return new Rectangle((int) (x+ checkbox.x + xOffset),(int) (y+ checkbox.y + yOffset),checkbox.width,checkbox.height);
	}
	public boolean checkEntityCollision(float xOffset, float yOffset){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if(e.equals(this)|| e.isCreaturePass()){
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset,yOffset))){
				return true;
			}
		}
		return false;
	}
	public boolean checkCollisionWithPlayer(float xOffset, float yOffset){
		if(this.getCollisionBounds(xOffset, xOffset).intersects(hand.getWorld().getEnti().getPl().getCollisionBounds(0,0))){
			return true;
		}
		return false;
	}
	public boolean checkEntityCollisionNotIncludingSemiSolid(float xOffset, float yOffset){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if(e.equals(this)||e.semiSolid){
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects( getCollisionBounds(xOffset,yOffset))){
				return true;
			}
		}
		return false;
	}
	public boolean checkEntityCollisionNotIncludingSemiSolidandPlayer(float xOffset, float yOffset){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if(e.equals(this)||e.semiSolid||e instanceof Player){
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects( getCollisionBounds(xOffset,yOffset))){
				return true;
			}
		}
		return false;
	}
	public boolean checkEntityCollisionNotIncludingWater(float xOffset, float yOffset){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if(e.equals(this)||e instanceof Water|| e.CreaturePass){
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects( getCollisionBounds(xOffset,yOffset))){
				return true;
			}
		}
		return false;
	}
	public boolean isThisCheckboxCollidingWithEntityBounds(){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if(e.equals(this)){
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects(this.getCheckbox(0, 0))){
				return true;
			}
		}
		return false;
	}
	public Entity getEntityCollidingWithCheckbox(){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if(e.equals(this)){
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects(this.getCheckbox(0, 0))){
				return e;
			}
		}
		return null;
	}
	public Entity getEntityCollidingWithCheckboxNotIncludingWater(){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if(e.equals(this)||e instanceof Water){
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects(this.getCheckbox(0, 0))){
				return e;
			}
		}
		return null;
	}
	public Entity getEntitityOverlappingWithThisCheckbox(){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if(e.equals(this)||e instanceof InvisCheckbox){
				continue;
			}
			
			if(this.getCheckbox(0, 0).contains(e.getCollisionBounds(0, 0))){
				return e;
			}
		}
		return null;
	}
	public boolean isEntitityOverlappingWithThisCheckbox(){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if(e.equals(this)||e instanceof InvisCheckbox){
				continue;
			}
			//Rectangle temp  = e.getCollisionBounds(0, 0);
			//if(this.getCheckbox(0, 0).contains(new Rectangle(temp.x+10,temp.y+10,temp.width-20,temp.height-20))){
			if(this.getCheckbox(0, 0).contains(e.getCollisionBounds(0, 0))){
				return true;
				
			}
		}
		return false;
	}	
	public Entity getEntity(float x, float y){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if(e.equals(this)){
				continue;
			}
			if(e.getX() == x && e.getY() == y){
				return e;
			}
		}
		return null;
	}
	public boolean isPistonPushing(){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if(e.equals(this)||!(e instanceof Piston)){
				continue;
			}
			
			if(this.getCheckbox(0, 0).intersects(e.getCheckbox(0, 0))){
				return true;
			}
		}
		return false;
	}
	public Piston getPistonPushing(){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if(e.equals(this)||!(e instanceof Piston)){
				continue;
			}
			
			if(this.getCheckbox(0, 0).intersects(e.getCheckbox(0, 0))){
				return (Piston) e;
			}
		}
		return null;
	}
	public boolean isPistonPushingBounds(){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if(e.equals(this)||!(e instanceof Piston)){
				continue;
			}
			
			if(this.getCollisionBounds(0, 0).intersects(e.getCheckbox(0, 0))){
				return true;
			}
		}
		return false;
	}
	public Piston getPistonPushingBounds(){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if(e.equals(this)||!(e instanceof Piston)){
				continue;
			}
			
			if(this.getCollisionBounds(0, 0).intersects(e.getCheckbox(0, 0))){
				return (Piston) e;
			}
		}
		return null;
	}
	
	//getters and setters below
	public boolean isActiv() {
		return isActiv;
	}
	public void setActiv(boolean isActiv) {
		this.isActiv = isActiv;
		if(!hand.getWorld().getEnti().getEnti().contains(this))
			hand.getWorld().getEnti().addEnti(this, name);
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getBreite() {
		return breite;
	}
	public void setBreite(int breite) {
		this.breite = breite;
	}
	public int getHoehe() {
		return hoehe;
	}
	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}
	public boolean isCreaturePass() {
		return CreaturePass;
	}
	public void setCreaturePass(boolean creaturePass) {
		CreaturePass = creaturePass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSemiSolid() {
		return semiSolid;
	}
	public void setSemiSolid(boolean semiSolid) {
		this.semiSolid = semiSolid;
	}
	public void setCheckboxX(int x){
		checkbox.x = x;
	}
	public void setCheckboxY(int y){
		checkbox.y = y;
	}
	public void setCheckboxSize(int y){
		checkbox.width = y;
		checkbox.height = y;
	}
	public int getCheckboxSize(){
		return checkbox.width;
	}
	public boolean isInteracted() {
		return interacted;
	}
	public void setInteracted(boolean interacted) {
		this.interacted = interacted;
	}
	
}
