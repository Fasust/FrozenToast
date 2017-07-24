package main.entity.statics;

import java.awt.Graphics;
import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class Sing extends StaticEntity {
	private boolean singInteracted = false;

	public Sing(Handler hand, float x, float y) {
		super(hand,IdManager.Sing, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, Tile.TILEBREITE, Tile.TILEHoeHE);

		//Hitbox
		bounds.y=16;
		bounds.height=Tile.TILEHoeHE/2;
	}

	@Override
	public void tick() {
		checkInteraction();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.sing, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		
	}

}
