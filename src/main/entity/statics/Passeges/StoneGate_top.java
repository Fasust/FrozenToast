package main.entity.statics.Passeges;

import java.awt.Graphics;

import main.Handler;
import main.entity.statics.StaticEntity;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class StoneGate_top extends StaticEntity{
	
	public StoneGate_top(Handler hand, float x, float y) {
		super(hand,IdManager.StoneGate_top, x*Tile.TILEBREITE, y*Tile.TILEHoeHE,Tile.TILEBREITE,Tile.TILEHoeHE);
		this.CreaturePass = true;
		
		//Hitbox
		bounds.x = 0;
		bounds.y = 32;
		bounds.width= this.breite;
		bounds.height = this.hoehe/2;
		
		
		hight = 6;

	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.wall_top, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		
	}
}
