package main.entity.statics.wall;

import java.awt.Graphics;

import main.Handler;
import main.entity.statics.StaticEntity;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class StoneWallEnd_ne extends StaticEntity{

	public StoneWallEnd_ne(Handler hand, float x, float y) {
		super(hand,IdManager.StoneWallEnd_ne, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, Tile.TILEBREITE, Tile.TILEHoeHE);
		hight = 6;
		//Hitbox
		bounds.x = 0;
		bounds.y = 32;
		bounds.width= this.breite;
		bounds.height = this.hoehe/2;
		
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.wall_end_e, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	}

}
