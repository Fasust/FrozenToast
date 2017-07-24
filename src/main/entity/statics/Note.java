package main.entity.statics;

import java.awt.Graphics;
import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class Note extends StaticEntity {
	private boolean noteInteracted = false;

	public Note(Handler hand, float x, float y) {
		super(hand,IdManager.Note, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, Tile.TILEBREITE, Tile.TILEHoeHE);
	}

	@Override
	public void tick() {
		checkInteraction();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.note, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);

	}

}
