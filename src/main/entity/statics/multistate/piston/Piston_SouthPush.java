package main.entity.statics.multistate.piston;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class Piston_SouthPush extends Piston {

	public Piston_SouthPush(Handler hand, float x, float y) {
		super(hand, IdManager.Piston_SouthPush, x*Tile.TILEBREITE, y*Tile.TILEHoeHE,'s');
		
	}

		@Override
		protected void renderPistonFood(Graphics g) {
			
			g.drawImage(Assets.piston_wall_food_s, (int)(x-hand.getCam().getxOffset()), 
					(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		
		}

		@Override
		protected void renderPistonStock(Graphics g) {
			if(!pushing){
				g.drawImage(Assets.piston_stock_s, (int)(x-hand.getCam().getxOffset()), 
						(int) (y-hand.getCam().getyOffset() )
						,breite,hoehe, null);
			}else{		
				g.drawImage(Assets.piston_stock_s, (int)(x-hand.getCam().getxOffset()), 
						(int) (y-hand.getCam().getyOffset()+headYOffset)
						,breite,hoehe, null);
			}
		}

		@Override
		protected void renderPistonTop(Graphics g) {
			g.drawImage(Assets.piston_wall_top_s, (int)(x-hand.getCam().getxOffset()), 
					(int) (y-hand.getCam().getyOffset())
					,breite,hoehe, null);	
			
		}

		@Override
		protected void renderPistonHead(Graphics g) {
			if(!pushing){
				g.drawImage(Assets.piston_head_s, (int)(x-hand.getCam().getxOffset()), 
						(int) (y-hand.getCam().getyOffset()-Tile.TILEHoeHE/16*1),breite,hoehe, null);
			}else{
			
				g.drawImage(Assets.piston_head_s, (int)(x-hand.getCam().getxOffset()), 
						(int) (y-hand.getCam().getyOffset()+headYOffset)
						,breite,hoehe, null);
			}
		}
	}
	
