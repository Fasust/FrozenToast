package main.entity.statics.multistate.piston;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class Piston_WestPush extends Piston {

	public Piston_WestPush(Handler hand, float x, float y) {
		super(hand, IdManager.Piston_WestPush, x*Tile.TILEBREITE, y*Tile.TILEHoeHE,'w');
		
	}

		@Override
		protected void renderPistonFood(Graphics g) {
			
			g.drawImage(Assets.piston_wall_food_w, (int)(x-hand.getCam().getxOffset()), 
					(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		
		}

		@Override
		protected void renderPistonStock(Graphics g) {
			if(!pushing){
				g.drawImage(Assets.piston_stock_w, (int)(x-hand.getCam().getxOffset()), 
						(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
			}else{		
				g.drawImage(Assets.piston_stock_w, (int)(x-hand.getCam().getxOffset() -headXOffset), 
						(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
			}
		}

		@Override
		protected void renderPistonTop(Graphics g) {
			g.drawImage(Assets.piston_wall_top_w, (int)(x-hand.getCam().getxOffset()), 
					(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);	
			
		}

		@Override
		protected void renderPistonHead(Graphics g) {
			if(!pushing){
				g.drawImage(Assets.piston_head_w, (int)(x-hand.getCam().getxOffset()), 
						(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
			}else{
			
				g.drawImage(Assets.piston_head_w, (int)(x-hand.getCam().getxOffset()- headXOffset), 
						(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
			}
		}
	}
	
