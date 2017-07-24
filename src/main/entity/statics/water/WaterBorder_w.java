package main.entity.statics.water;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;

public class WaterBorder_w extends Water{

	public WaterBorder_w(Handler hand, float x, float y) {
		super(hand,IdManager.WaterBorder_w, x, y);

	}
	@Override
	protected void renderWaterBorder(Graphics g) {
		g.drawImage(Assets.water_w, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	}
}
