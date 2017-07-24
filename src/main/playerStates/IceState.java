package main.playerStates;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Handler;
import main.audio.SoundAssets;
import main.entity.creature.Player;
import main.entity.statics.multistate.piston.Piston;
import main.gfx.Animation;
import main.gfx.Assets;
import main.tile.Tile;
import main.util.Timer;

public class IceState extends PlayerStates {
	private boolean firstFrozen = true;
	private Animation freeze;
	private boolean freezing = false;
	private boolean firstInFreezing = false;
	private boolean StaminaRegenOverrite = false;
	
	public IceState(Handler hand, Player pl) {
		super(hand, pl,Assets.ping_ice_down);
		freeze = new Animation(50,Assets.frezze_partiel);
		exsaustionRate = PlayerStates.DEF_EXSAUSTIONRATE *2;
	}

	@Override
	public void tick() {
		//movment
		
		checkPistonHit();
		
		speedUp();
		checkWallHits();
		pl.move();
		
		//Input
		getInput();
		
		//Animation
		freeze.tick();
		
		//Interact
		if(!pl.isExausted()){
			interact();
		}else{
			pl.setCheckboxX(-10000);
			pl.setCheckboxY(-10000);
		}
	}

	//Render
	@Override
	public void render(Graphics g) {
		
		renderShadow(g);
		renderPing(g);
		
		if(freezing){
			renderIceRay(g);
		}
	}
	protected void renderPing(Graphics g){
		g.drawImage(getCurrentFrame(), (int)(pl.getX()-hand.getCam().getxOffset()), 
				(int) (pl.getY()-hand.getCam().getyOffset()),pl.getBreite(),pl.getHoehe(), null);
	}
	private void renderIceRay(Graphics g){
		g.drawImage(freeze.getCurrentFrame(),(pl.getCheckbox(0, 0).x -(int)hand.getCam().getxOffset()),
				(pl.getCheckbox(0, 0).y-(int)hand.getCam().getyOffset()), 
				Tile.TILEBREITE, 
				Tile.TILEHoeHE,
				null);
	}
	private BufferedImage getCurrentFrame(){
		switch(pl.getFacing()){
		case 'n':
			return Assets.ping_ice_up;
		case 's':
			return Assets.ping_ice_down;
		case 'e':
			return Assets.ping_ice_right;
		case 'w':
			return Assets.ping_ice_left;
		default:
			return Assets.ping_ice_down;
		}
	}
	
	@Override
	protected void interact(){
		pl.setCheckboxSize(Tile.TILEBREITE);
		
		if(hand.getKeyManager().e){
			switch(pl.getFacing()){
			case 'n':
				pl.setCheckboxX((int) pl.getX()/Tile.TILEBREITE);
				pl.setCheckboxY((int) pl.getY()/Tile.TILEBREITE-pl.getHoehe());
				break;
			case 'e':
				pl.setCheckboxX((int) pl.getX()/Tile.TILEBREITE+pl.getBreite());
				pl.setCheckboxY((int) pl.getY()/Tile.TILEBREITE);
				break;
			case's':
				pl.setCheckboxX((int) pl.getX()/Tile.TILEBREITE);
				pl.setCheckboxY((int) pl.getY()/Tile.TILEBREITE+pl.getHoehe());
				break;
			case'w':
				pl.setCheckboxX((int) pl.getX()/Tile.TILEBREITE-pl.getBreite());
				pl.setCheckboxY((int) pl.getY()/Tile.TILEBREITE);
				break;
			default:
				break;
			}
		}else{
			pl.setCheckboxX(-10000);
			pl.setCheckboxY(-10000);
		}
		
	}
	private void getInput(){
		if(hand.getKeyManager().wasSpacePressed() 
				&& pl.getMana() >= pl.getMaxMana()){
			
			switchPlayerState(pl.getNatState());
			
		}
		if(hand.getKeyManager().e == true  
			&& pl.getStamina() > 0 
			&& !pl.isExausted() ){
			
			if(firstInFreezing){
				SoundAssets.freeze_ray.play();
				firstInFreezing = false;
			}
			
			freezing = true;
			pl.depledStamina(exsaustionRate);
			
		}else{
			freezing = false;
			firstInFreezing = true;
			if(!StaminaRegenOverrite)
				pl.setStaminaRegBlock(false);
		}
	}
	private void checkWallHits(){
		if(pl.isHitUp()||pl.iseHitY()){
			pl.setyMove(0);
			SoundAssets.ice_hit.play();
		}
		if(pl.isHitDown()){
			pl.setyMove(0);
			SoundAssets.ice_hit.play();
		}
		if(pl.isHitRight()||pl.iseHitX()){
			pl.setxMove(0);
			SoundAssets.ice_hit.play();
		}
		if(pl.isHitLeft()){
			pl.setxMove(0);
			SoundAssets.ice_hit.play();
		}
	}
	private void speedUp(){
		if(firstFrozen){
			if(pl.getxMove() != 0){
				speed = Math.abs(pl.getxMove());
			}else if(pl.getyMove() != 0){
				speed = Math.abs(pl.getyMove());
			}
			firstFrozen = false;
		}
		
		if(speed != 0){
			speed += 0.05;
		}
		
		if(pl.getxMove()<0){ //left
			pl.setxMove(-speed);
		}else if(pl.getxMove() > 0){ //right
			pl.setxMove(speed);
		}
		if(pl.getyMove()<0){ //up
			pl.setyMove(-speed);
		}else if(pl.getyMove() > 0){ //down
			pl.setyMove(speed);
		}
	}
	
	
	//Getters and setters

	public boolean isFreezing() {
		return freezing;
	}

	public boolean isFirstFrozen() {
		return firstFrozen;
	}

	public void setFirstFrozen(boolean firstFrozen) {
		this.firstFrozen = firstFrozen;
	}

	public boolean isFirstInFreezing() {
		return firstInFreezing;
	}

	public void setFirstInFreezing(boolean firstInFreezing) {
		this.firstInFreezing = firstInFreezing;
	}

	public void setFreezing(boolean freezing) {
		this.freezing = freezing;
	}

	public boolean isStaminaRegenOverrite() {
		return StaminaRegenOverrite;
	}

	public void setStaminaRegenOverrite(boolean staminaRegenOverrite) {
		StaminaRegenOverrite = staminaRegenOverrite;
		pl.setStaminaRegBlock(staminaRegenOverrite);
	}

	
}
