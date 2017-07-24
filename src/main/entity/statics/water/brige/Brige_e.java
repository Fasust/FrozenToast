package main.entity.statics.water.brige;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;

public class Brige_e extends Brige {

	public Brige_e(Handler hand, float x, float y) {
		super(hand,IdManager.Brige_e, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void renderWaterBorder(Graphics g) {
		g.drawImage(Assets.water_e, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		
		g.drawImage(Assets.water_br_e, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		
	}

}
