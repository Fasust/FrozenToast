package main.entity.gui;

import java.awt.Graphics;

import main.Handler;
import main.entity.Entity;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class Menu  extends Entity  {
	private String[] options;

	public Menu(Handler hand,float x, float y,int breite,int hoehe,String[] options) {
		super(hand, IdManager.Menu, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, breite, hoehe);
		hight = 10;
		this.options = options;
		
		//Hitbox
		bounds.x=0;
		bounds.y=0;
		bounds.height = 0;
		bounds.height = 0;
		//checkbox
		checkbox.width=0;
		checkbox.height=0;
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		prepMenu(g);
		renderPointer(g);
	}
	private void prepMenu(Graphics g){
		g.drawImage(Assets.chat_box_nw,(int)x,(int)y,Tile.TILEBREITE,Tile.TILEHoeHE, null);
		/*
		g.drawImage(Assets.chat_box_ne,(int)x+breite,hand.getHoehe()-this.hoehe,Tile.TILEBREITE,Tile.TILEHoeHE, null);
		g.drawImage(Assets.chat_box_sw,0,hand.getHoehe()-Tile.TILEHoeHE,Tile.TILEBREITE,Tile.TILEHoeHE, null);
		g.drawImage(Assets.chat_box_se,breite,hand.getHoehe()-Tile.TILEHoeHE,Tile.TILEBREITE,Tile.TILEHoeHE, null);
		g.drawImage(Assets.chat_box_n,Tile.TILEBREITE,hand.getHoehe()-this.hoehe,breite-Tile.TILEBREITE,Tile.TILEHoeHE, null);
		g.drawImage(Assets.chat_box_e,breite,hand.getHoehe()-(this.hoehe-Tile.TILEHoeHE),Tile.TILEBREITE,hoehe-(Tile.TILEHoeHE*2), null);
		g.drawImage(Assets.chat_box_s,Tile.TILEBREITE,hand.getHoehe()-Tile.TILEHoeHE,breite-Tile.TILEBREITE,Tile.TILEHoeHE, null);
		g.drawImage(Assets.chat_box_w,0,hand.getHoehe()-(this.hoehe-Tile.TILEHoeHE),Tile.TILEBREITE,hoehe-Tile.TILEHoeHE*2, null);
		g.drawImage(Assets.chat_box_black,Tile.TILEBREITE,hand.getHoehe()-(this.hoehe-Tile.TILEHoeHE),breite-Tile.TILEBREITE,hoehe-(Tile.TILEHoeHE*2), null);
		*/
	}
	private void renderPointer(Graphics g){
		
	}
}
