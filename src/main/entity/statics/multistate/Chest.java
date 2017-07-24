package main.entity.statics.multistate;

import java.awt.Graphics;
import main.Handler;
import main.entity.statics.StaticEntity;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class Chest extends StaticEntity {
	private boolean chestOpen =false;
	private boolean chestInteracted = false;

	public Chest(Handler hand, float x, float y) {
		super(hand,IdManager.Chest, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, Tile.TILEBREITE, Tile.TILEHoeHE);
		//Hitbox
		bounds.y=32;
		bounds.height=Tile.TILEHoeHE/2;
	}

	@Override
	public void tick() {
		if(hand.getWorld().getEnti().getPl().getCheckbox(0, 0).intersects(this.getCollisionBounds(0, 0))){
			chestInteracted = true;
		}
	}

	@Override
	public void render(Graphics g) {
		if(chestOpen){
			g.drawImage(Assets.chest_open, (int)(x-hand.getCam().getxOffset()), 
					(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		}else{
			g.drawImage(Assets.chest, (int)(x-hand.getCam().getxOffset()), 
					(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		}
		
	}
//getters and setters

	public boolean isChestOpen() {
		return chestOpen;
	}

	public void setChestOpen(boolean chestOpen) {
		this.chestOpen = chestOpen;
	}

	public boolean isChestInteracted() {
		return chestInteracted;
	}

	public void setChestInteracted(boolean chestInteracted) {
		this.chestInteracted = chestInteracted;
	}
	
}
