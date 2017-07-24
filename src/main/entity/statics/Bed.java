package main.entity.statics;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;
import main.state.GameState;
import main.tile.Tile;

public class Bed extends StaticEntity{

	public Bed(Handler hand, float x, float y) {
		super(hand, IdManager.Bed, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, Tile.TILEBREITE, Tile.TILEHoeHE*2);
		//bounds
		bounds.y = hoehe*1/3;
		bounds.height = hoehe*2/3;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bed, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);

	}

}
