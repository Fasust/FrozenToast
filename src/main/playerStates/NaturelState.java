package main.playerStates;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.Handler;
import main.audio.SoundAssets;
import main.entity.creature.Creature;
import main.entity.creature.Player;
import main.event.Event;
import main.gfx.Animation;
import main.gfx.Assets;
import main.gfx.ImageLoader;
import main.tile.Tile;

public class NaturelState extends PlayerStates {
		//Animation
		private Animation animDown;
		private Animation animUp;
		private Animation animRight;
		private Animation animLeft;
		
		private Animation fish;
		
		private boolean holdingOnHead = true;
		private int renderWight = pl.getBreite();
		private int renderOffset = 0;
	
	public NaturelState(Handler hand, Player pl) {
		super(hand, pl,Assets.ping_down[0]);
		
		//Animation
		animDown = new Animation(anim_speed,Assets.ping_down);
		animUp = new Animation(anim_speed,Assets.ping_up);
		animRight = new Animation(anim_speed,Assets.ping_right);
		animLeft = new Animation(anim_speed,Assets.ping_left);
		
		fish = new Animation(anim_speed*4, Assets.fish_green);
	}

	@Override
	public void tick() {
		
		//animation
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		
		fish.tick();
		
		//movment
		checkPistonHit();
		
		if(!pl.isPistonPushingBounds())
			getInput();
		
		pl.move();
		
		//Interact
		interact();
		
		
	}
	
	//Render
	@Override
	public void render(Graphics g) {
		
		renderShadow(g);
		
		if(holdingOnHead){
			 
			renderHolding(g);	
		}else{
			renderPing(g);
		}
	
		
		/*
		g.setColor(Color.cyan);
		g.fillRect((int)(pl.getCheckbox(0, 0).x-hand.getCam().getxOffset()),
				(int) (pl.getCheckbox(0, 0).y-hand.getCam().getyOffset()),
				pl.getCheckbox(0, 0).width, 
				pl.getCheckbox(0, 0).height);
		
		g.setColor(Color.YELLOW);
		g.fillRect((int)(pl.getCollisionBounds(0, 0).x-hand.getCam().getxOffset()),
				(int)(pl.getCollisionBounds(0, 0).y-hand.getCam().getyOffset()), 
				pl.getCollisionBounds(0, 0).width, 
				pl.getCollisionBounds(0, 0).height);
		
		*/
	}
	private void renderHolding(Graphics g){
		g.drawImage(fish.getCurrentFrame(),
				(int)(pl.getX()-hand.getCam().getxOffset()),
				(int) (pl.getY()-hand.getCam().getyOffset()-(Tile.TILEHoeHE/16*15)),
				Tile.TILEBREITE,
				Tile.TILEHoeHE,
				null);	
		
		g.drawImage(getCurrentHoldingFrame(),
				(int)(pl.getX()-hand.getCam().getxOffset()+renderOffset),
				(int) (pl.getY()-hand.getCam().getyOffset()),
				renderWight,
				pl.getHoehe(),
				null);
		
		
		g.drawImage(getOverlayFrame(),
				(int)(pl.getX()-hand.getCam().getxOffset()),
				(int) (pl.getY()-hand.getCam().getyOffset()-Tile.TILEHoeHE),
				pl.getBreite(),
				pl.getHoehe()*2,
				null);
	}
	protected void renderPing(Graphics g){
		g.drawImage(getCurrentAnimationFrame(),
				(int)(pl.getX()-hand.getCam().getxOffset()),
				(int) (pl.getY()-hand.getCam().getyOffset()),
				pl.getBreite(),
				pl.getHoehe(),
				null);
		
	}
	private BufferedImage getOverlayFrame(){
		if(pl.getxMove() < 0){
			return Assets.ping_overlay_hold_leftside;
		}else if(pl.getxMove() > 0){
			return  Assets.ping_overlay_hold_rightside;
		}else if(pl.getyMove()>0){
			return  Assets.ping_overlay_hold_front;
		}else if(pl.getyMove() < 0){
			return  Assets.ping_overlay_hold_front;
		}else{
			switch(pl.getFacing()){
			case 'n':
				return Assets.ping_overlay_hold_front;
			case 's':
				return  Assets.ping_overlay_hold_front;
			case 'e':
				return Assets.ping_overlay_hold_rightside;
			case 'w':
				return Assets.ping_overlay_hold_leftside;
			default:
				return Assets.ping_overlay_hold_front;
			}
		}
	}	
	private BufferedImage getCurrentAnimationFrame(){

		if(pl.getxMove() < 0){
			return animLeft.getCurrentFrame();
		}else if(pl.getxMove() > 0){
			return animRight.getCurrentFrame();
		}else if(pl.getyMove()>0){
			return animDown.getCurrentFrame();
		}else if(pl.getyMove() < 0){
			return animUp.getCurrentFrame();
		}else{
			switch(pl.getFacing()){
			case 'n':
				if(interacting){
					return Assets.ping_up_interact;
				}
				return animUp.getStillFrame();
			case 's':
				if(interacting){
					return Assets.ping_down_interact;
				}
				return animDown.getStillFrame();
			case 'e':
				if(interacting){
					return Assets.ping_right_interact;
				}
				return animRight.getStillFrame();
			case 'w':
				if(interacting){
					return Assets.pint_left_interact;
				}
				return animLeft.getStillFrame();
			default:
				return animDown.getStillFrame();
			}
		}
	}
	private BufferedImage getCurrentHoldingFrame(){
		if(pl.getxMove() < 0){
			renderOffset = 0;
			renderWight = pl.getBreite();
			return animLeft.getCurrentFrame();
		}else if(pl.getxMove() > 0){
			renderOffset = 0;
			renderWight = pl.getBreite();
			return animRight.getCurrentFrame();
		}else if(pl.getyMove()>0){
			renderOffset = Tile.TILEBREITE/16*2;
			renderWight = pl.getBreite() -Tile.TILEBREITE/16*4;
			return ImageLoader.cropImage(animDown.getCurrentFrame(), 2, 0,12,16);
		}else if(pl.getyMove() < 0){
			renderOffset = Tile.TILEBREITE/16*2;
			renderWight = pl.getBreite() -Tile.TILEBREITE/16*4;
			return ImageLoader.cropImage(animUp.getCurrentFrame(), 2, 0,12,16);
		}else{
			switch(pl.getFacing()){
			case 'n':
				renderOffset = Tile.TILEBREITE/16*2;
				renderWight = pl.getBreite() -Tile.TILEBREITE/16*4;
				return ImageLoader.cropImage(animUp.getStillFrame(), 2, 0,12,16);			
			case 's':
				renderOffset = Tile.TILEBREITE/16*2;
				renderWight = pl.getBreite() -Tile.TILEBREITE/16*4;
				return ImageLoader.cropImage(animDown.getStillFrame(), 2, 0,12,16);			
			case 'e':
				renderOffset = 0;
				renderWight = pl.getBreite();
				return animRight.getStillFrame();
			case 'w':
				renderOffset = 0;
				renderWight = pl.getBreite();
				return animLeft.getStillFrame();
			default:
				renderOffset = Tile.TILEBREITE/16*2;
				renderWight = pl.getBreite() -Tile.TILEBREITE/16*4;
				return ImageLoader.cropImage(animUp.getStillFrame(), 2, 0,12,16);			
			}
		}
	}
	
	//Input
	private void getInput(){
		pl.setxMove(0);
		pl.setyMove(0);
		
		if(hand.getKeyManager().up){
			pl.setyMove(-speed);
			pl.setFacing('n');
		}
		if(hand.getKeyManager().down){
			pl.setyMove(speed);
			pl.setFacing('s');
		}
		if(hand.getKeyManager().left){
			pl.setxMove(-speed);
			pl.setFacing('w');
		}
		if(hand.getKeyManager().right){
			pl.setxMove(speed);
			pl.setFacing('e');
		}
		if(hand.getKeyManager().shift 
				&& pl.getStamina() > 0 
				&& !pl.isExausted()
				&&(pl.getxMove() != 0|| pl.getyMove() != 0)){
			
			speed = Creature.DEF_SPEED*2 -1;
			animDown.setAnimSpeed(anim_speed/2);
			animUp.setAnimSpeed(anim_speed/2);
			animRight.setAnimSpeed(anim_speed/2);
			animLeft.setAnimSpeed(anim_speed/2);
			
			pl.depledStamina(exsaustionRate);
		}else{
			speed = Creature.DEF_SPEED;
			animDown.setAnimSpeed(anim_speed);
			animUp.setAnimSpeed(anim_speed);
			animRight.setAnimSpeed(anim_speed);
			animLeft.setAnimSpeed(anim_speed);
			pl.setStaminaRegBlock(false);
		}
		if(hand.getKeyManager().strg ){
			this.holdingOnHead = true;
		}else{
			this.holdingOnHead = false;
		}
		if(hand.getKeyManager().wasSpacePressed() 
				&& pl.getMana() >= pl.getMaxMana()
				&&Event.GUI_OPTAINED){
			switchPlayerState(pl.getIceState());
		}
		
	}
	
	//Getters and Setters
	public void setAnimSpeed(int speed){
		anim_speed = speed;
		animDown.setAnimSpeed(speed);
		animUp.setAnimSpeed(speed);
		animRight.setAnimSpeed(speed);
		animLeft.setAnimSpeed(speed);
	}

}
