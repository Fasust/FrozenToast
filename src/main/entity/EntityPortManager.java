package main.entity;

import main.Handler;
import main.Level.Level;
import main.audio.SoundAssets;
import main.entity.creature.Player;
import main.entity.dynamic.push.PushEntity;
import main.entity.dynamic.slide.SlideEntity;
import main.entity.statics.SpawnEgg;
import main.gfx.Assets;
import main.gfx.Cutsean;
import main.state.GameState;
import main.tile.Tile;
import main.util.Timer;

public class EntityPortManager {
	//Player Port
	protected Timer timer;
	protected long transSpeed = GameState.DEF_TRANSITION_SPEED;
	private boolean firstInPlayerPort = true;
	
	//Enti Port
	protected Timer pushTimer;
	protected Timer slideTimer;
	private boolean firstInSlidePort = true;
	private boolean firstInPushPort = true;
	
	private Handler hand;
	
	public EntityPortManager(Handler hand){
		this.hand = hand;
	}
	
	//Port Player
	public void portPlayer(Level level,String fallOrPort){
		if(!hand.getWorld().getEnti().getPl().isBeingPorted()){
			if(firstInPlayerPort){
				timer = new Timer(transSpeed);
				firstInPlayerPort =false;
				
				if(fallOrPort == "port" ||fallOrPort == "fall")
					SoundAssets.switch_level.play();
				if(fallOrPort == "fall")
					GameState.cutsean.activatePlayerFall(400);
				
			}
			
			
			timer.start();
			hand.getWorld().getEnti().getPl().setBlocked(true);
			hand.getWorld().getEnti().getPl().setIsBeingPorted(true);
			hand.getGame().getGameState().startTransition(transSpeed);
			
		}
		if(timer.isFinished()){
			Player temp = Level.getLevel().getWorld().getEnti().getPl();
			hand.setWorld(level.getWorld());
			Level.setLevel(level);
			level.getWorld().getEnti().setPl(temp);
			
			firstInPlayerPort =true;
			
			temp.setSpawning(false);
			hand.getWorld().getEnti().getPl().resetStatusEffects();
			
			if(fallOrPort == "fall")
				SoundAssets.entity_landing_from_fall.play();
				GameState.cutsean.activatePlayerDropOnFloor(200);
				
			if(fallOrPort == "spawn")
				GameState.cutsean.startPlayerHatch(SpawnEgg.currentPlayerSpawn);
		}
		
	}
	public void portPlayer(float x, float y,Level level,String fallOrPort){
		if(!hand.getWorld().getEnti().getPl().isBeingPorted()){
			if(firstInPlayerPort){
				timer = new Timer(transSpeed);
				firstInPlayerPort =false;
				
				if(fallOrPort == "port" ||fallOrPort == "fall")
					SoundAssets.switch_level.play();
				if(fallOrPort == "fall")
					GameState.cutsean.activatePlayerFall(400);
				
			}
			timer.start();
			hand.getWorld().getEnti().getPl().setIsBeingPorted(true);
			hand.getWorld().getEnti().getPl().setBlocked(true);
			hand.getGame().getGameState().startTransition(transSpeed);
			
		}
		
		if(timer.isFinished()){
	
			Player temp = Level.getLevel().getWorld().getEnti().getPl();
			temp.setX(x*Tile.TILEBREITE);
			temp.setY(y*Tile.TILEHoeHE);
			
			Level.setLevel(level);
			hand.setWorld(level.getWorld());
			
			level.getWorld().getEnti().setPl(temp);
			
			temp.setSpawning(false);
			hand.getWorld().getEnti().getPl().resetStatusEffects();
			
			firstInPlayerPort =true;
			
			if(fallOrPort == "fall"){
				SoundAssets.entity_landing_from_fall.play();
				GameState.cutsean.activatePlayerDropOnFloor(200);
			}
			if(fallOrPort == "spawn" && SpawnEgg.currentPlayerSpawn != null)
				GameState.cutsean.startPlayerHatch(SpawnEgg.currentPlayerSpawn);

		}
	}
	
	//Port Entity
	public void portEntity(Entity e, Level level,String fallOrPort){
		if(e instanceof Player){
			portPlayer(level,fallOrPort);
		}else{
			if(fallOrPort == "fall"){
				if(e instanceof SlideEntity){
					portSlideEnti((SlideEntity) e,level);
	
				}else if(e instanceof PushEntity){
					portPushEnti((PushEntity) e,level);
				}
		
			}
		}
	}
	public void portEntity(float x, float y,Entity e, Level level,String fallOrPort){
		if(e instanceof Player){
			portPlayer(x,y,level,fallOrPort);
		}else{
			if(fallOrPort == "fall"){
				if(e instanceof SlideEntity){
					portSlideEnti(x,y, (SlideEntity) e,level);
	
				}else if(e instanceof PushEntity){
					portPushEnti(x,y,(PushEntity) e,level);
				}
		
				
			}
		}
	}
	private void portPushEnti(PushEntity e, Level level){
		if(firstInPushPort){
				pushTimer = new Timer(200);
				pushTimer.start();
				e.setBeingPorted(true);
				firstInPushPort = false;
		}
		
		e.setyMove(Cutsean.DEF_FALLINGSPEED);
		e.pushing();
		if(pushTimer.isFinished() || e.getyMove() == 0){
			hand.getWorld().getEnti().getEnti().remove(e);
			hand.getWorld().getEnti().getEntiHash().remove(e);
			level.getWorld().getEnti().addEnti(e, e.getName());
			
			e.setBeingPorted(false);
			firstInPushPort = true;
			SoundAssets.entity_landing_from_fall.play();
		}
	}
	private void portPushEnti(float x, float y,PushEntity e, Level level){
		if(firstInPushPort){
				pushTimer = new Timer(200);
				pushTimer.start();
				e.setBeingPorted(true);
				firstInPushPort = false;
		}
		
		e.setyMove(Cutsean.DEF_FALLINGSPEED);
		e.pushing();
		
		if(pushTimer.isFinished() || e.getyMove() == 0){
			hand.getWorld().getEnti().getEnti().remove(e);
			hand.getWorld().getEnti().getEntiHash().remove(e);
			e.setX(x*Tile.TILEBREITE);
			e.setY(y*Tile.TILEHoeHE);
			level.getWorld().getEnti().addEnti(e, e.getName());
			
			e.setBeingPorted(false);
			firstInPushPort = true;
			SoundAssets.entity_landing_from_fall.play();
		}
	}
	private void portSlideEnti(SlideEntity e, Level level){
		if(firstInSlidePort){
			slideTimer = new Timer(200);
			slideTimer.start();
			e.setBeingPorted(true);
			firstInSlidePort = false;
		}
		e.setyMove(Cutsean.DEF_FALLINGSPEED);
		e.sliding();
		if(slideTimer.isFinished()||e.getyMove() == 0){
			hand.getWorld().getEnti().getEnti().remove(e);
			hand.getWorld().getEnti().getEntiHash().remove(e);
			level.getWorld().getEnti().addEnti(e, e.getName());
			
			e.setBeingPorted(false);
			firstInSlidePort = true;
			SoundAssets.entity_landing_from_fall.play();
		}
	}
	private void portSlideEnti(float x, float y,SlideEntity e, Level level){
		if(firstInSlidePort){
			slideTimer = new Timer(200);
			slideTimer.start();
			e.setBeingPorted(true);
			firstInSlidePort = false;
		}
		e.setyMove(Cutsean.DEF_FALLINGSPEED);
		e.sliding();
		if(slideTimer.isFinished() || e.getyMove() == 0){
			hand.getWorld().getEnti().getEnti().remove(e);
			hand.getWorld().getEnti().getEntiHash().remove(e);
			e.setX(x*Tile.TILEBREITE);
			e.setY(y*Tile.TILEHoeHE);
			level.getWorld().getEnti().addEnti(e, e.getName());
			
			e.setBeingPorted(false);
			firstInSlidePort = true;
			SoundAssets.entity_landing_from_fall.play();
		}
	}
	
	//Getters and Setters Below

	public long getTransSpeed() {
		return transSpeed;
	}

	public void setTransSpeed(long transSpeed) {
		this.transSpeed = transSpeed;
	}
		
}
