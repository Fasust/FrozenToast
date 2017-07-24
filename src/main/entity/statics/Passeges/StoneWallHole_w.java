package main.entity.statics.Passeges;

import java.awt.Graphics;

import main.Handler;
import main.entity.statics.StaticEntity;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class StoneWallHole_w extends StaticEntity{

	public StoneWallHole_w(Handler hand, float x, float y) {
		super(hand,IdManager.StoneWallHole_w, x*Tile.TILEBREITE, y*Tile.TILEHoeHE,Tile.TILEBREITE,Tile.TILEHoeHE);
		this.semiSolid = true;
		
		hight = 4;
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.wall_w_hole, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	
	}

}
