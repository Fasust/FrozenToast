package main.entity.statics.water;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;

public class WaterBorder_s extends Water{

	public WaterBorder_s(Handler hand, float x, float y) {
		super(hand,IdManager.WaterBorder_s, x, y);

	}
	@Override
	protected void renderWaterBorder(Graphics g) {
		g.drawImage(Assets.water_s, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	}
}
