package main.Level;

import java.awt.Graphics;

import main.Game;
import main.Handler;
import main.entity.Entity;
import main.entity.creature.npc.ChefBot;
import main.entity.dynamic.DynamicEntitys;
import main.entity.dynamic.push.DeadGreenFish;
import main.entity.dynamic.push.Toast;
import main.entity.dynamic.slide.FrozenDeadGreenFish;
import main.entity.dynamic.slide.FrozenToast;
import main.entity.dynamic.slide.SlideEntity;
import main.entity.statics.Bathtub;
import main.entity.statics.Bed;
import main.entity.statics.Hatch;
import main.entity.statics.InvisCheckbox;
import main.entity.statics.Sing;
import main.entity.statics.SpawnEgg;
import main.entity.statics.Passeges.StoneDoor_s;
import main.entity.statics.Passeges.StoneDoor_top;
import main.entity.statics.Passeges.StoneWallHole_s;
import main.entity.statics.Passeges.StoneWallHole_top;
import main.entity.statics.multistate.Button;
import main.entity.statics.multistate.Button_red;
import main.entity.statics.multistate.Chest;
import main.entity.statics.multistate.WallButton;
import main.entity.statics.multistate.piston.Piston_EastPush;
import main.entity.statics.multistate.piston.Piston_SouthPush;
import main.entity.statics.multistate.piston.Piston_WestPush;
import main.event.Event;
import main.gfx.Assets;
import main.state.GameState;
import main.tile.Tile;
import main.world.World;

public class LevelTest extends Level {
		
	//Logic____________________________
	private boolean b0first = true;
	private boolean b1first = true;
	//________________________________
	
	 //Buttons
	private Button_red b0 = (Button_red) world.getEnti().getEntiHash().get("b0");
	private WallButton b1 = (WallButton) world.getEnti().getEntiHash().get("b1");
	private Button b2 = (Button) world.getEnti().getEntiHash().get("b2");
	private Button b3 = (Button) world.getEnti().getEntiHash().get("b3");
	
	//Door
	private StoneDoor_s d0 = (StoneDoor_s) world.getEnti().getEntiHash().get("d0");
	private StoneDoor_s d1 = (StoneDoor_s) world.getEnti().getEntiHash().get("d1");
	
	//Hatch
	private Hatch ha0 = (Hatch) world.getEnti().getEntiHash().get("ha0");
	private Hatch ha1 = (Hatch) world.getEnti().getEntiHash().get("ha1");
	private Hatch ha2 = (Hatch) world.getEnti().getEntiHash().get("ha2");
	
	//Chefbot
	private ChefBot che0 = (ChefBot) world.getEnti().getEntiHash().get("che0");
	
	//Chest
	private Chest cs0 =  (Chest) world.getEnti().getEntiHash().get("cs0");
	
	//Sing
	private Sing si0 = (Sing) world.getEnti().getEntiHash().get("si0");
	
	//Piston
	private Piston_EastPush pi0 =  (Piston_EastPush) world.getEnti().getEntiHash().get("pi0");
	private Piston_SouthPush pi1 =  (Piston_SouthPush) world.getEnti().getEntiHash().get("pi1");
	private Piston_WestPush pi2 =  (Piston_WestPush) world.getEnti().getEntiHash().get("pi2");
	
	//Bathtung
	private Bathtub bath0 =  (Bathtub) world.getEnti().getEntiHash().get("bath0");
	
	//Bed
	private Bed bed0 =  (Bed) world.getEnti().getEntiHash().get("bed0");

	//InvisCheckbox
	private InvisCheckbox cb1;
	private InvisCheckbox cb2;
	
	//Spawn
	private boolean firstSpawnSet = false;
	private SpawnEgg sp0 =  (SpawnEgg) world.getEnti().getEntiHash().get("sp0");
	
	public LevelTest(Handler hand) {
		super(hand,new World(hand,"res/worlds/world_test"));
		if(Game.DEBUG)
			Event.GUI_OPTAINED = true;
		
		//Checkbox
		cb1 = new InvisCheckbox(hand, 14, 15, 3, 10);
		world.getEnti().addEnti(cb1,"cb1");
		
		cb2 = new InvisCheckbox(hand, 17, 16, 6, 4);
		world.getEnti().addEnti(cb2,"cb2");
	}
	@Override
	public void tick() {
		world.tick();
		
		if(!firstSpawnSet){
			world.getEnti().getPl().setSpawn();
			sp0.setPopUpText("Respawn Point wurde Gesetzt");
			
			GameState.cutsean.showKeyPrompts(16,8,true);
		
			firstSpawnSet = true;
			
			che0.giveHatch(ha0);
			che0.giveHatch(ha1);
			che0.giveHatch(ha2);
			
			bed0.setY(bed0.getY()-15);
		
		}
		
		buttonsTick();
		chestTick();
		checkboxTick();
		singTick();
	}
	
	@Override
	public void render(Graphics g) {
		world.render(g);	
	}
	
	private void buttonsTick(){
		if(b0.isButtonPressed()){
			
			if(!hand.getCam().getScreenShakeFinsihed() && d0.isClosed()){
				hand.getCam().screenShake(350, 20, 0, 0.5f);
				
			}else{
				d0.setClosed(false);
				if(b0first){
					hand.getCam().setScreenShakeFinsihed(false);
					b0first = false;
				}
			}
		
		}
		if(b1.isButtonPressed() && b1first){
			GameState.cutsean.showKeyPrompts(false);
			d1.setClosed(false);
		}
		if(b2.isButtonPressed()){
			entiPort.portPlayer(9, 18, this, "port");
		}
		if(b3.isButtonPressed()){
			pi0.setPushing(true);
			pi1.setPushing(true);
			pi2.setPushing(true);
		}else{
			pi0.setPushing(false);
			pi1.setPushing(false);
			pi2.setPushing(false);
		}
	
	}
	private void chestTick(){
		if(cs0.isChestInteracted() && !cs0.isChestOpen()){
			if(!hand.getCam().getScreenShakeFinsihed()){
				hand.getCam().screenShake(370, 25, 1, 1);
			
			}else{
				GameState.cutsean.activatePlayerOptainObject(Assets.gui_mini,"\"One Donut to ruel them all\"\n\ndruecke die Leertatse\num den Donut zu verwenden.");
				cs0.setChestOpen(true);
				hand.getCam().setScreenShakeFinsihed(false);
				Event.GUI_OPTAINED = true;
			
			}
		}
	}
	private void checkboxTick(){
		if(cb1.isEntitityFalling()){	
			entiPort.portEntity(3,2,cb1.getEntitityFalling(), this ,"fall");
		}else if(cb2.isEntitityFalling()){
			entiPort.portEntity(3,1,cb2.getEntitityFalling(), this ,"fall");
		}
	}
	private void singTick(){
		if(si0.isInteracted()){
			world.getEnti().getChat().giveText("12345678901234567890123456789012 cut 2\n3\n4\n5\n6\n7\n12345678901234567890123456789012 cut");
			world.getEnti().getChat().setShowText(true);
		}
	}
}


