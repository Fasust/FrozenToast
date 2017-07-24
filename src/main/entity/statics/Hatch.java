package main.entity.statics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Handler;
import main.gfx.Animation;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;
import main.util.Timer;

public class Hatch extends StaticEntity{

	private Animation anim;
	private boolean startSwitching = false;
	private boolean firstInSwitching = true;

	private boolean open = false;
	private Timer timer;

	
	public Hatch(Handler hand, float x, float y) {
		super(hand, IdManager.Hatch, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, Tile.TILEBREITE, Tile.TILEHoeHE);
		anim = new Animation(150,Assets.stone_hatch);
		this.hight = 1;
		
		bounds.width = 0;
		bounds.height = 0;
	}

	@Override
	public void tick() {
		if(startSwitching){
			anim.tick();
			startSwitching();
		}
		
	
		
	}

	@Override
	public void render(Graphics g) {

			g.drawImage(getCurrentFrame(), (int)(x-hand.getCam().getxOffset()), 
					(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		
		
	}
	private BufferedImage getCurrentFrame(){
		if(startSwitching){
			if(open){
				return anim.getReverseCurrentFrame();
			}else{
				return anim.getCurrentFrame();
			}
		}else{
			if(open){
				return Assets.stone_hatch[Assets.stone_hatch.length-1];
			}else{
				return anim.getStillFrame();
			}
		}
	}
	private void startSwitching(){
		if(firstInSwitching){
			timer = new Timer(200);
			timer.start();
			
			firstInSwitching=false;
			
		}
		if(timer.isFinished()){
			firstInSwitching = true;
			startSwitching = false;
			switchState();
		}
	}
	private void switchState(){
		if(open){
			open = false;
			anim.reset();
		}else{
			open = true;
			anim.reset();
		}
	}
	
	public void switchOpenClose(){
		anim.reset();
		startSwitching = true;
	}

	//Getters and setters

	public boolean isOpen() {
		return open;
	}
	
}
