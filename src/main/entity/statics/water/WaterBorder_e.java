package main.entity.statics.water;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;

public class WaterBorder_e extends Water{

	public WaterBorder_e(Handler hand, float x, float y) {
		super(hand,IdManager.WaterBorder_e, x, y);

	}
	@Override
	protected void renderWaterBorder(Graphics g) {
		g.drawImage(Assets.water_e, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	}
}
