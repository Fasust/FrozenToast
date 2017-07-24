package main.entity.statics.multistate.piston;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class Piston_EastPush extends Piston {

	public Piston_EastPush(Handler hand, float x, float y) {
		super(hand, IdManager.Piston_EastPush, x*Tile.TILEBREITE, y*Tile.TILEHoeHE,'e');
		
	}

		@Override
		protected void renderPistonFood(Graphics g) {
			
			g.drawImage(Assets.piston_wall_food_e, (int)(x-hand.getCam().getxOffset()), 
					(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		
		}

		@Override
		protected void renderPistonStock(Graphics g) {
			if(!pushing){
				g.drawImage(Assets.piston_stock_e, (int)(x-hand.getCam().getxOffset()), 
						(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
			}else{		
				g.drawImage(Assets.piston_stock_e, (int)(x-hand.getCam().getxOffset() +headXOffset), 
						(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
			}
		}

		@Override
		protected void renderPistonTop(Graphics g) {
			g.drawImage(Assets.piston_wall_top_e, (int)(x-hand.getCam().getxOffset()), 
					(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);	
			
		}

		@Override
		protected void renderPistonHead(Graphics g) {
			if(!pushing){
				g.drawImage(Assets.piston_head_e, (int)(x-hand.getCam().getxOffset()), 
						(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
			}else{
			
				g.drawImage(Assets.piston_head_e, (int)(x-hand.getCam().getxOffset()+ headXOffset), 
						(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
			}
		}
	}
	
