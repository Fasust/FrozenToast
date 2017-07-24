package main.entity.statics.water;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;

public class WaterInnerCorner_nw extends Water{

	public WaterInnerCorner_nw(Handler hand, float x, float y) {
		super(hand,IdManager.WaterInnerCorner_nw, x, y);

	}
	@Override
	protected void renderWaterBed(Graphics g){
		g.drawImage(Assets.water_bed_border_ic_nw, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	}
	@Override
	protected void renderWaterBorder(Graphics g) {
		g.drawImage(Assets.water_ic_nw, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	}
}
