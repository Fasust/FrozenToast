package main.entity.statics.Passeges;

import java.awt.Graphics;

import main.Handler;
import main.audio.SoundAssets;
import main.entity.statics.StaticEntity;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class StoneDoor_s extends StaticEntity{
	
	private boolean closed = true;
	private boolean fristOpen = false;
	private boolean fristClose = false;
	
	public StoneDoor_s(Handler hand, float x, float y) {
		super(hand,IdManager.StoneDoor_s, x*Tile.TILEBREITE, y*Tile.TILEHoeHE,Tile.TILEBREITE,Tile.TILEHoeHE);
		this.semiSolid = true;

	}

	@Override
	public void tick() {
		if(closed){
			bounds.x = 0;
			bounds.y = 0;
			bounds.width=breite;
			bounds.height = hoehe;
			this.semiSolid = false;
			
			if(fristClose){
				SoundAssets.door_open.play();
				fristClose =false;
			}
			fristOpen =true;
		}else{
			bounds.x = 0;
			bounds.y = 0;
			bounds.width=0;
			bounds.height = 0;
			this.semiSolid = true;
			if(fristOpen){
				SoundAssets.door_open.play();
				fristOpen =false;
			}
			fristClose =true;
		}
		
	}

	@Override
	public void render(Graphics g) {
		if(closed){
			g.drawImage(Assets.stone_door_s_closed, (int)(x-hand.getCam().getxOffset()), 
					(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		}else{
		g.drawImage(Assets.stone_door_s, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		}
	}
	//getters and setters

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	

}
