package main.playerStates;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Handler;
import main.audio.SoundAssets;
import main.entity.creature.Creature;
import main.entity.creature.Player;
import main.entity.statics.multistate.piston.Piston;
import main.gfx.Assets;
import main.tile.Tile;

public abstract class PlayerStates {
	public static final int DEF_EXSAUSTIONRATE = 3;
	
	protected Handler hand;
	protected Player pl;
	protected float speed;
	protected int anim_speed;
	protected BufferedImage guiIcon;
	
	protected boolean interacting = false;
	
	protected int exsaustionRate;
	
	public PlayerStates(Handler hand,Player pl,BufferedImage guiIcon){
		this.hand = hand;
		this.pl= pl;
		this.guiIcon = guiIcon;
		speed = Creature.DEF_SPEED;
		anim_speed = Creature.DEF_ANIM_SPEED/3;
		
		exsaustionRate = DEF_EXSAUSTIONRATE;
	}
	public abstract void tick();
	public abstract void render(Graphics g);
	
	protected void interact(){
		Rectangle tempColis = pl.getCollisionBounds(0,0);
		pl.setCheckboxSize(Player.DEF_PLAYERCHECKBOXSIZE);
		
		if(hand.getKeyManager().e){
			interacting = true;
			switch(pl.getFacing()){
			case 'n':
				pl.setCheckboxX(tempColis.x/Tile.TILEBREITE + tempColis.width /2 -pl.getCheckboxSize()/2);
				pl.setCheckboxY(tempColis.y/Tile.TILEHoeHE - pl.getCheckboxSize());
				
				break;
			case 'e':
				pl.setCheckboxX(tempColis.x /Tile.TILEBREITE+ tempColis.width);
				pl.setCheckboxY(tempColis.y /Tile.TILEHoeHE+ tempColis.height /2 );
				break;
			case's':
				pl.setCheckboxX(tempColis.x /Tile.TILEBREITE+ tempColis.width/2- pl.getCheckboxSize() /2);
				pl.setCheckboxY(tempColis.y/Tile.TILEHoeHE + tempColis.height*2);
				break;
			case'w':
				pl.setCheckboxX(tempColis.x /Tile.TILEBREITE- pl.getCheckboxSize());
				pl.setCheckboxY(tempColis.y /Tile.TILEHoeHE+ tempColis.height /2 );
				break;
			default:
				break;
			}
		}else{
			interacting = false;
			pl.setCheckboxX(-10000);
			pl.setCheckboxY(-10000);
		}
		
	}
	protected void switchPlayerState(PlayerStates NextState){
	
		if(pl.getCurrentState() instanceof IceState){
			pl.getIceState().setFirstFrozen(true);
			pl.getIceState().setFreezing(false);
			pl.setWalkOnWater(false);
			pl.getIceState().setStaminaRegenOverrite(false);
		}
		if(NextState instanceof IceState){
			pl.setWalkOnWater(true);
		}
		
		pl.setCurrentState(NextState);
		pl.setMana(0);
		pl.setStaminaRegBlock(false);
		SoundAssets.curse_switch.play();
	}
	protected void renderShadow(Graphics g){
		g.drawImage(Assets.ping_shadow,
				(int)(pl.getX()-hand.getCam().getxOffset()),
				(int) (pl.getY()-hand.getCam().getyOffset())+5,
				pl.getBreite(),
				pl.getHoehe(),
				null);
		
	}
	protected abstract  void renderPing(Graphics g);
	
	protected void checkPistonHit(){
		if(pl.isPistonPushingBounds()){
			Piston tempPiston = pl.getPistonPushingBounds();
			speed = tempPiston.getPushSpeed();
			
			switch(tempPiston.getPushDirektion()){
			case 'n':
				pl.setyMove(-speed);
				break;
			case 'e':
				pl.setxMove(speed);
				break;
			case 's':
				pl.setyMove(speed);
				break;
			case 'w':
				pl.setxMove(-speed);
				break;
			default:
				break;
			}
		}
	
	}
	
	//Getters and Setters
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public int getAnim_speed() {
		return anim_speed;
	}
	public void setAnim_speed(int anim_speed) {
		this.anim_speed = anim_speed;
	}
	public BufferedImage getGuiIcon() {
		return guiIcon;
	}
	public void setGuiIcon(BufferedImage guiIcon) {
		this.guiIcon = guiIcon;
	}
	
}
