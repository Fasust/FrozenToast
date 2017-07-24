package main.entity.statics.water;

import java.awt.Graphics;

import main.Handler;
import main.Level.Level;
import main.audio.SoundAssets;
import main.entity.Entity;
import main.entity.EntityPortManager;
import main.entity.creature.Player;
import main.entity.dynamic.DynamicEntitys;
import main.entity.statics.StaticEntity;
import main.gfx.Animation;
import main.gfx.Assets;
import main.playerStates.IceState;
import main.playerStates.PlayerStates;
import main.state.GameState;
import main.tile.Tile;
import main.util.Timer;
public abstract class Water extends StaticEntity{
	
	protected boolean frozen = false;
	protected boolean iceBelow = false;
	protected EntityPortManager entiPort;
	protected Animation waterAnim;
	private Entity tmpE;
	
	//Player Stat changes
	protected int staminaCoast = PlayerStates.DEF_EXSAUSTIONRATE*10;
	protected int dmg =25;
	
	protected static boolean firstHit = true;
	
	//Decay
	protected Timer timer;
	protected boolean firstInDecay = true;
	protected int decyRate = 4000;
	
	//SOund
	protected static boolean FreezeSoundReady = true;
	protected static boolean firstInfreezeSoundCheck = true;
	protected static Timer freezeTimer;

	public Water(Handler hand, int id, float x, float y) {
		super(hand,id,x*Tile.TILEBREITE, y*Tile.TILEHoeHE, Tile.TILEBREITE, Tile.TILEHoeHE);
		this.hight = 1;
		waterAnim = new Animation(500,Assets.water_anim);
		
		entiPort = new EntityPortManager(hand);
	
				
	}

	@Override
	public void tick() {
		waterAnim.tick();
		
		if(frozen){
			iceDecay();
			bounds.width = 0;
			bounds.height = 0;
		}else{
			bounds.width = breite;
			bounds.height = hoehe;
		}
		checkFreeze();
		checkForIceBelow();
		checkFreezeSound();
		checkPlayerDrowning();
		checkEntityDrowning();
		
	}

	@Override
	public void render(Graphics g) {
		renderWaterBed(g);
		renderWaterAnim(g);
		renderWaterBorder(g);
	}
	
	protected void checkForIceBelow(){
		Entity e;
		e = getEntity(x,y+Tile.TILEHoeHE);
		if(e instanceof Water){
			if(((Water)e).frozen){
				iceBelow = true;
				return;
			}
		}
		iceBelow = false;
	}
	protected void checkFreeze(){
		if(hand.getWorld().getEnti().getPl().getCheckbox(0, 0).intersects(this.getCheckbox(0, 0)) //Player actiivate FreezeRay
				&& hand.getWorld().getEnti().getPl().getIceState().isFreezing()
				&&!frozen
				&&!hand.getWorld().getEnti().getPl().isExausted()){
			
			
			frozen = true;
			firstInDecay = true;
			
			if(FreezeSoundReady){
				SoundAssets.freeze.play();
				FreezeSoundReady = false;
			}	
		}
		if(this.getCheckbox(0, 0).intersects(hand.getWorld().getEnti().getPl().getCollisionBounds(0, 0)) ){ //Player Walking on Ice
			if( hand.getWorld().getEnti().getPl().getCurrentState() instanceof IceState
					&&!hand.getWorld().getEnti().getPl().isExausted()){						//Player also Frozen
				
				firstHit = true;
				frozen = true;
				firstInDecay = true;
				
				if(FreezeSoundReady){
					SoundAssets.freeze.play();
					FreezeSoundReady = false;
					
					hand.getWorld().getEnti().getPl().depledStamina(staminaCoast);
					
					hand.getWorld().getEnti().getPl().getIceState().setStaminaRegenOverrite(true);
				}	
			}
		}else if(!hand.getWorld().getEnti().getPl().isDrowning()){
			firstHit = true;
		}
	}
	protected void iceDecay(){
		if(firstInDecay){
			timer = new Timer(decyRate);
			timer.start();
			
			firstInDecay = false;
		}
		
		if(timer.isFinished()){
			frozen = false;
			firstInDecay = true;
		
		}
	}
	protected void checkPlayerDrowning(){
		if(this.getCollisionBounds(0, 0).intersects(hand.getWorld().getEnti().getPl().getCollisionBounds(0, 0))
				&& !frozen){
			
			
			hand.getWorld().getEnti().getPl().getIceState().setStaminaRegenOverrite(false);
			
			if(firstHit){
				//hand.getWorld().getEnti().getPl().hurt(dmg);
				hand.getWorld().getEnti().getPl().setDrowning(true);
				GameState.cutsean.activatePlayerDrown();
				firstHit = false;
			}
		}
	}
	protected void checkEntityDrowning(){
	
		tmpE = getEntityCollidingWithCheckboxNotIncludingWater();
		if(tmpE != null){
			if(getCollisionBounds(0, 0).intersects(tmpE.getCollisionBounds(0, 0))
					&& !frozen){
				if(tmpE instanceof Player){
					return;
				}else if(tmpE instanceof DynamicEntitys){
					((DynamicEntitys)tmpE).setActiv(false);
					GameState.cutsean.activateEntiDrown(tmpE);
				}
				
				
			}
		}
	}
	protected static void checkFreezeSound(){
		if(!FreezeSoundReady){
			if(firstInfreezeSoundCheck){
				freezeTimer = new Timer(500);
				freezeTimer.start();
				firstInfreezeSoundCheck = false;
				
			}
			if(freezeTimer.isFinished()){
				firstInfreezeSoundCheck = true;
				FreezeSoundReady = true;
				
			}
		}
	}
	//Render
	protected void renderWaterBed(Graphics g){
		g.drawImage(Assets.water_bed, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	}
	protected void renderWaterAnim(Graphics g){
		g.drawImage(waterAnim.getCurrentFrame(), (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		if(frozen){
			if(!iceBelow){
				g.drawImage(Assets.ice_floor, (int)(x-hand.getCam().getxOffset()), 
						(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
			}else{
				g.drawImage(Assets.ice_floor_noBorder, (int)(x-hand.getCam().getxOffset()), 
						(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
			}
		}
	}
	protected abstract void renderWaterBorder(Graphics	g);

}
