package main.entity.statics.water.brige;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;

public class Brige_n extends Brige {

	public Brige_n(Handler hand, float x, float y) {
		super(hand,IdManager.Brige_n, x, y);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void renderWaterBed(Graphics g){
		g.drawImage(Assets.water_bed_border_ic_n, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	}
	@Override
	protected void renderWaterBorder(Graphics g) {
		g.drawImage(Assets.water_n, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		
		g.drawImage(Assets.water_br_n, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		
	}


}
