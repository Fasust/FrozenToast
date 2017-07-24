package main.entity.statics;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class StoneStairs_up extends StaticEntity{
	private boolean stairsStepedOn = false;

	public StoneStairs_up(Handler hand, float x, float y) {
		super(hand,IdManager.StoneStairs_up, x*Tile.TILEBREITE, y*Tile.TILEHoeHE,Tile.TILEBREITE,Tile.TILEHoeHE);

		hight = 2;
		
		//hitbox
		bounds.x = 0;
		bounds.y = 0;
		bounds.width=0;
		bounds.height = 0;
		//checkbox
		checkbox.x =25;
		checkbox.y = 25;
		checkbox.width = Tile.TILEBREITE - 50;
		checkbox.height = Tile.TILEHoeHE - 50;
				
		

	}

	@Override
	public void tick() {
		if(this.getCheckbox(0, 0).intersects(hand.getWorld().getEnti().getPl().getCollisionBounds(0, 0))){
			stairsStepedOn = true;	
		}else{
			stairsStepedOn = false;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.stone_stairs_up, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	
	}
	
	//getters and setters

	public boolean isStairsStepedOn() {
		return stairsStepedOn;
	}

	public void setStairsStepedOn(boolean stairsStepedOn) {
		this.stairsStepedOn = stairsStepedOn;
	}

}
