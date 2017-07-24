package main.entity.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Handler;
import main.entity.Entity;
import main.event.Event;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;
import main.util.Timer;

public class GUI extends Entity{
	private int redBarLenght = Tile.TILEBREITE/16*79;
	private int greenBarLenght = Tile.TILEBREITE/16*63;
	private int blueBarLenght = Tile.TILEHoeHE/16*24;
	
	private boolean guiFade = false;
	
	private boolean greenBallFade = false;
	private boolean blueBallFade = false;
	private boolean redBallFade = false;
	private boolean tempBallFade = false;
	
	private boolean firstInBallFade = true;
	private Timer timer;

	public GUI(Handler hand) {
		super(hand,IdManager.GUI, 0, 0, Tile.TILEBREITE*8, Tile.TILEHoeHE*2);
		hight = 10;
		
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
		redBarLenght = ((hand.getWorld().getEnti().getPl().getHp())*(Tile.TILEBREITE/16*79)/hand.getWorld().getEnti().getPl().getMaxHp());
		greenBarLenght = ((hand.getWorld().getEnti().getPl().getStamina())*(Tile.TILEBREITE/16*63)/hand.getWorld().getEnti().getPl().getMaxStamina());
		blueBarLenght = ((hand.getWorld().getEnti().getPl().getMana())*(Tile.TILEHoeHE/16*24)/hand.getWorld().getEnti().getPl().getMaxMana());
		
		if(blueBarLenght < Tile.TILEHoeHE/16*24){
			guiFade = true;
		}else{
			guiFade = false;
		}
		if(hand.getWorld().getEnti().getPl().isExausted()){
			greenBallFade = ballFade(500);
			blueBallFade = ballFade(500);
		}else{
			greenBallFade = false;
			blueBallFade = false;
			firstInBallFade = true;
		}
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.gui_main,0,0,Tile.TILEBREITE*9,Tile.TILEHoeHE*2, null);
		
		g.drawImage(Assets.gui_red_bar,Tile.TILEBREITE*2+Tile.TILEBREITE/16*9,Tile.TILEHoeHE/16 *2,redBarLenght,Tile.TILEHoeHE/16 *4, null);
		g.drawImage(Assets.gui_green_bar,Tile.TILEBREITE*2+Tile.TILEBREITE/16*9,Tile.TILEHoeHE/16 *11,greenBarLenght,Tile.TILEHoeHE/16 *4, null);
		g.drawImage(Assets.gui_blue_bar,Tile.TILEBREITE*2,Tile.TILEHoeHE/16 *28,Tile.TILEBREITE/16*2,-blueBarLenght, null);
		
		g.drawImage(hand.getWorld().getEnti().getPl().getSelectedCurse().getGuiIcon()
				,Tile.TILEBREITE/2
				,Tile.TILEHoeHE/2,Tile.TILEBREITE
				,Tile.TILEHoeHE
				,null);
		
		if(guiFade){
			g.drawImage(Assets.gui_fade,0,0,Tile.TILEBREITE*2,Tile.TILEHoeHE*2, null);
		}
		if(greenBallFade){
			g.drawImage(Assets.gui_ball_fade,5*Tile.TILEBREITE+Tile.TILEBREITE/16*31,Tile.TILEHoeHE/16*11,Tile.TILEBREITE/16*4,Tile.TILEHoeHE/16*4, null);
		}
		if(blueBallFade){
			g.drawImage(Assets.gui_ball_fade,3*Tile.TILEBREITE-Tile.TILEBREITE/16*10,2*Tile.TILEHoeHE-Tile.TILEHoeHE/16*7,Tile.TILEBREITE/16*4,Tile.TILEHoeHE/16*4, null);
		}
		if(redBallFade){
			g.drawImage(Assets.gui_ball_fade,3*Tile.TILEBREITE-Tile.TILEBREITE/16*10,2*Tile.TILEHoeHE-Tile.TILEHoeHE/16*7,Tile.TILEBREITE/16*4,Tile.TILEHoeHE/16*4, null);
		}
		if(!Event.GUI_OPTAINED){
			g.drawImage(Assets.gui_gold_block,0,0,Tile.TILEBREITE*3,Tile.TILEHoeHE*2, null);
		}
	}
	
	private boolean ballFade(int x){
		if(firstInBallFade){
			timer = new Timer(x);
			timer.start();
			firstInBallFade =false;
		}
		if(timer.isFinished()){
			if(tempBallFade){
				timer.setSpeed(x*2);
				timer.start();
				tempBallFade= false;
			}else{
				timer.setSpeed(x);
				timer.start();
				tempBallFade = true;
			}
		}
		
		return tempBallFade;
		
	}
}
