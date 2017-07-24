package main.entity.statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Handler;
import main.entity.Entity;
import main.id.IdManager;
import main.tile.Tile;

public class InvisCheckbox extends StaticEntity{

	public InvisCheckbox(Handler hand, float x, float y, int breite, int hoehe) {
		super(hand,IdManager.InvisCheckbox, x*Tile.TILEBREITE, y*Tile.TILEHoeHE,breite*Tile.TILEBREITE,hoehe*Tile.TILEHoeHE);
		//hitbox
		bounds.x = 0;
		bounds.y = 0;
		bounds.width=0;
		bounds.height = 0;

	}

	public Entity getEntitityFalling(){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if(e.equals(this)||e instanceof InvisCheckbox){
				continue;
			}
			Rectangle temp  = e.getCollisionBounds(0, 0);
			if(this.getCheckbox(0, 0).contains(new Rectangle(temp.x+10,temp.y+10,temp.width-20,temp.height-20))){
				return e;
			}
		}
		return null;
	}
	public boolean isEntitityFalling(){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if(e.equals(this)||e instanceof InvisCheckbox){
				continue;
			}
			Rectangle temp  = e.getCollisionBounds(0, 0);
			if(this.getCheckbox(0, 0).contains(new Rectangle(temp.x+10,temp.y+10,temp.width-20,temp.height-20))){
				return true;
				
			}
		}
		return false;
	}
	@Override
	public void tick() {
	
	}

	@Override
	public void render(Graphics g) {
		/*
		g.setColor(Color.cyan);
		g.fillRect((int)(getCheckbox(0, 0).x-hand.getCam().getxOffset()),
				(int) (getCheckbox(0, 0).y-hand.getCam().getyOffset()),
				getCheckbox(0, 0).width, 
				getCheckbox(0, 0).height);
				*/
	}
	


	
	

}

	
