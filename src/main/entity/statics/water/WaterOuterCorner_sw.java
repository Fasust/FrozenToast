package main.entity.statics.water;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;

public class WaterOuterCorner_sw extends Water{

	public WaterOuterCorner_sw(Handler hand, float x, float y) {
		super(hand,IdManager.WaterOuterCorner_sw, x, y);

	}
	@Override
	protected void renderWaterBorder(Graphics g) {
		g.drawImage(Assets.water_oc_sw, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	}
}
