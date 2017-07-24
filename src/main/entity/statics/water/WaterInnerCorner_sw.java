package main.entity.statics.water;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;

public class WaterInnerCorner_sw extends Water{

	public WaterInnerCorner_sw(Handler hand, float x, float y) {
		super(hand,IdManager.WaterInnerCorner_sw, x, y);

	}
	@Override
	protected void renderWaterBed(Graphics g){
		g.drawImage(Assets.water_bed_border_ic_sw, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	}
	@Override
	protected void renderWaterBorder(Graphics g) {
		g.drawImage(Assets.water_ic_sw, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	}
}
