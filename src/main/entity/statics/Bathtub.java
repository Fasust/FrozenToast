package main.entity.statics;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;
import main.state.GameState;
import main.tile.Tile;

public class Bathtub extends StaticEntity{

	public Bathtub(Handler hand, float x, float y) {
		super(hand, IdManager.Bathtub, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, Tile.TILEBREITE*3, Tile.TILEHoeHE*2);
		//bounds
		bounds.y = hoehe*1/3;
		bounds.height = hoehe*2/3;
	}

	@Override
	public void tick() {
		this.checkInteraction();
		if(this.interacted){
			hand.getWorld().getEnti().getChat().giveText("Ein sehr schoenes Desing\n\n\n\n\n\n\n...dennoch weckt es \nBoese Erinnerungen");
			hand.getWorld().getEnti().getChat().setShowText(true);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bathtub, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);

	}

}
