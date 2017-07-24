package main.entity.statics.water.brige;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;

public class Brige_w extends Brige {

	public Brige_w(Handler hand, float x, float y) {
		super(hand,IdManager.Brige_w, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void renderWaterBorder(Graphics g) {
		g.drawImage(Assets.water_w, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		
		g.drawImage(Assets.water_br_w, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		
	}

}
