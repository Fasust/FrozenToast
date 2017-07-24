package main.entity.statics.water;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;

public class WaterInnerCorner_se extends Water{

	public WaterInnerCorner_se(Handler hand, float x, float y) {
		super(hand,IdManager.WaterInnerCorner_se, x, y);

	}
	@Override
	protected void renderWaterBed(Graphics g){
		g.drawImage(Assets.water_bed_border_ic_se, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	}
	@Override
	protected void renderWaterBorder(Graphics g) {
		g.drawImage(Assets.water_ic_se, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	}
}
