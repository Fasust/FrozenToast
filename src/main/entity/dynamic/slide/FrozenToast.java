package main.entity.dynamic.slide;

import java.awt.Graphics;
import main.Handler;
import main.entity.dynamic.slide.SlideEntity;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class FrozenToast extends SlideEntity {
	

	public FrozenToast(Handler hand, float x, float y) {
		super(hand,IdManager.FrozenToast, x, y,Tile.TILEBREITE,Tile.TILEHoeHE);
		
		hight = 3;
	}
	public void render(Graphics g) {
		
		g.drawImage(Assets.toast_frozen,(int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	}
}
