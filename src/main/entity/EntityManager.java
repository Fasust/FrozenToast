package main.entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import main.Handler;
import main.entity.creature.Player;
import main.entity.creature.npc.BrokenToaster;
import main.entity.creature.npc.ChefBot;
import main.entity.creature.npc.GreenFish;
import main.entity.dynamic.push.DeadGreenFish;
import main.entity.dynamic.push.Toast;
import main.entity.dynamic.slide.FrozenDeadGreenFish;
import main.entity.dynamic.slide.FrozenToast;
import main.entity.gui.ChatBox;
import main.entity.gui.GUI;
import main.entity.statics.Bathtub;
import main.entity.statics.Bed;
import main.entity.statics.Hatch;
import main.entity.statics.Note;
import main.entity.statics.Sing;
import main.entity.statics.Sing_post;
import main.entity.statics.Slime;
import main.entity.statics.SpawnEgg;
import main.entity.statics.StoneStairs_down;
import main.entity.statics.StoneStairs_up;
import main.entity.statics.Passeges.StoneDoor_s;
import main.entity.statics.Passeges.StoneDoor_s_iron;
import main.entity.statics.Passeges.StoneDoor_top;
import main.entity.statics.Passeges.StoneGate_s;
import main.entity.statics.Passeges.StoneGate_top;
import main.entity.statics.Passeges.StoneWallHole_e;
import main.entity.statics.Passeges.StoneWallHole_s;
import main.entity.statics.Passeges.StoneWallHole_top;
import main.entity.statics.Passeges.StoneWallHole_w;
import main.entity.statics.multistate.Button;
import main.entity.statics.multistate.Button_red;
import main.entity.statics.multistate.Chest;
import main.entity.statics.multistate.WallButton;
import main.entity.statics.multistate.piston.Piston_EastPush;
import main.entity.statics.multistate.piston.Piston_SouthPush;
import main.entity.statics.multistate.piston.Piston_WestPush;
import main.entity.statics.wall.StoneWallEnd_ne;
import main.entity.statics.wall.StoneWallEnd_nw;
import main.entity.statics.wall.StoneWallTop_Floating;
import main.entity.statics.wall.WallTorch;
import main.entity.statics.water.WaterBed;
import main.entity.statics.water.WaterBorder_e;
import main.entity.statics.water.WaterBorder_n;
import main.entity.statics.water.WaterBorder_s;
import main.entity.statics.water.WaterBorder_w;
import main.entity.statics.water.WaterInnerCorner_ne;
import main.entity.statics.water.WaterInnerCorner_nw;
import main.entity.statics.water.WaterInnerCorner_se;
import main.entity.statics.water.WaterInnerCorner_sw;
import main.entity.statics.water.WaterOuterCorner_ne;
import main.entity.statics.water.WaterOuterCorner_nw;
import main.entity.statics.water.WaterOuterCorner_se;
import main.entity.statics.water.WaterOuterCorner_sw;
import main.entity.statics.water.brige.Brige_e;
import main.entity.statics.water.brige.Brige_n;
import main.entity.statics.water.brige.Brige_s;
import main.entity.statics.water.brige.Brige_w;
import main.id.IdManager;

public class EntityManager {
	private Handler hand;
	
	//Special Entitys
	private Player pl;
	private ChatBox chat;
	private GUI gui;
	
	//Entity Storage
	private List<Entity> enti;
	private HashMap<String,Entity> entiHash;
	private Iterator<Entity> it;
	private Comparator<Entity> renderSort = new Comparator<Entity>(){
	 
		public int compare(Entity a, Entity b){
			
			if(b.hight > a.hight){
				return -1;
			}else if(b.hight < a.hight){
				return 1;
			}else if(a.getY() + a.getHoehe() < b.getY() + b.getHoehe()){ //wenn a hoeher als b	
				return -1; //render b ueber a
			}else{
				return 1; //sonst: render a ueber b
			}
		}
	};
		
		
	public EntityManager(Handler hand, Player pl){
		this.hand = hand;
		
		//Entity Storage
		enti = new ArrayList<Entity>();
		entiHash = new HashMap<String,Entity>();
		it = enti.iterator();
		
		//Special Entitys
		this.pl = pl;
		addEnti(pl, "pl");
		
		
		this.chat = new ChatBox(hand);
		addEnti(chat, "chat");
		
		
		this.gui = new GUI(hand);
		addEnti(gui, "gui");
		
	}
	public void tick(){
		enti.sort(renderSort);
		it = enti.iterator();
		while(it.hasNext()){
			Entity e = it.next();
			
			if(!e.isActiv){
				it.remove();
				entiHash.remove(e);
			}
			e.tick();
		}
	}
	public void render(Graphics g){
		for(Entity e : enti){
			e.render(g);
		}
	}
	public void addEnti(Entity e,String name){
		enti.add(e);
		entiHash.put(name, e);
		e.name = name;
		
		it = enti.iterator();
	}
	public void addEnti(int id, String name,float x, float y){
		Entity newEnti = null;
		switch(id){
		case IdManager.StoneDoor_top: newEnti = new StoneDoor_top(hand,x,y);
			break;
		case IdManager.Button: newEnti = new Button(hand,x,y);
			break;
		case IdManager.Button_red: newEnti = new Button_red(hand,x,y);
			break;
		case IdManager.Chest: newEnti = new Chest(hand,x,y);
			break;
		case IdManager.Sing: newEnti = new Sing(hand,x,y);
			break;
		case IdManager.Sing_Post: newEnti = new Sing_post(hand,x,y);
			break;
		case IdManager.FrozenDeadGreenFish: newEnti = new FrozenDeadGreenFish(hand,x,y);
			break;
		case IdManager.StoneWallHole_s: newEnti = new StoneWallHole_s(hand,x,y);
			break;
		case IdManager.StoneWallHole_e: newEnti = new StoneWallHole_e(hand,x,y);
			break;
		case IdManager.StoneWallHole_w: newEnti = new StoneWallHole_w(hand,x,y);
			break;
		case IdManager.StoneWallHole_top: newEnti = new StoneWallHole_top(hand,x,y);
			break;
		case IdManager.StoneDoor_s: newEnti = new StoneDoor_s(hand,x,y);
			break;
		case IdManager.DeadGreenFish: newEnti = new DeadGreenFish(hand,x,y);
			break;
		case IdManager.StoneStairs_down: newEnti = new StoneStairs_down(hand,x,y);
			break;
		case IdManager.StoneStairs_up: newEnti = new StoneStairs_up(hand,x,y);
			break;
		case IdManager.WallButton: newEnti = new WallButton(hand,x,y);
			break;
		case IdManager.StoneGate_s: newEnti = new StoneGate_s(hand,x,y);
			break;
		case IdManager.StoneGate_top: newEnti = new StoneGate_top(hand,x,y);
			break;
		case IdManager.Note: newEnti = new Note(hand,x,y);
			break;
		case IdManager.Toast: newEnti = new Toast(hand,x,y);
			break;
		case IdManager.FrozenToast: newEnti = new FrozenToast(hand,x,y);
			break;
		case IdManager.GreenFish: newEnti = new GreenFish(hand,x,y);
			break;	
		case IdManager.StoneDoor_s_iron: newEnti = new StoneDoor_s_iron(hand,x,y);
			break;
		case IdManager.Slime: newEnti = new Slime(hand,x,y);
			break;
		case IdManager.WallTorch: newEnti = new WallTorch(hand,x,y);
			break;
		case IdManager.WaterOuterCorner_nw: newEnti = new WaterOuterCorner_nw(hand,x,y);
			break;
		case IdManager.WaterOuterCorner_ne: newEnti = new WaterOuterCorner_ne(hand,x,y);
			break;
		case IdManager.WaterOuterCorner_se: newEnti = new WaterOuterCorner_se(hand,x,y);
			break;
		case IdManager.WaterOuterCorner_sw: newEnti = new WaterOuterCorner_sw(hand,x,y);
			break;
		case IdManager.WaterBorder_w: newEnti = new WaterBorder_w(hand,x,y);
			break;
		case IdManager.WaterBorder_n: newEnti = new WaterBorder_n(hand,x,y);
			break;
		case IdManager.WaterBorder_s: newEnti = new WaterBorder_s(hand,x,y);
			break;
		case IdManager.WaterBorder_e: newEnti = new WaterBorder_e(hand,x,y);
			break;
		case IdManager.WaterInnerCorner_se: newEnti = new WaterInnerCorner_se(hand,x,y);
			break;
		case IdManager.WaterInnerCorner_sw: newEnti = new WaterInnerCorner_sw(hand,x,y);
			break;
		case IdManager.WaterInnerCorner_ne: newEnti = new WaterInnerCorner_ne(hand,x,y);
			break;
		case IdManager.WaterInnerCorner_nw: newEnti = new WaterInnerCorner_nw(hand,x,y);
			break;
		case IdManager.WaterBed: newEnti = new WaterBed(hand,x,y);
			break;
		case IdManager.Brige_n: newEnti = new Brige_n(hand,x,y);
			break;
		case IdManager.Brige_e: newEnti = new Brige_e(hand,x,y);
			break;
		case IdManager.Brige_s: newEnti = new Brige_s(hand,x,y);
			break;
		case IdManager.Brige_w: newEnti = new Brige_w(hand,x,y);
			break;
		case IdManager.StoneWallEnd_ne: newEnti = new StoneWallEnd_ne(hand,x,y);
			break;
		case IdManager.StoneWallEnd_nw: newEnti = new StoneWallEnd_nw(hand,x,y);
			break;
		case IdManager.StoneWallTop_Floating: newEnti = new StoneWallTop_Floating(hand,x,y);
			break;
		case IdManager.SpawnEgg: newEnti = new SpawnEgg(hand,x,y);
			break;
		case IdManager.Piston_EastPush: newEnti = new Piston_EastPush(hand,x,y);
			break;
		case IdManager.Piston_WestPush: newEnti = new Piston_WestPush(hand,x,y);
			break;
		case IdManager.Piston_SouthPush: newEnti = new Piston_SouthPush(hand,x,y);
			break;
		case IdManager.BrokenToaster: newEnti = new BrokenToaster(hand,x,y);
			break;
		case IdManager.ChefBot: newEnti = new ChefBot(hand,x,y);
			break;
		case IdManager.Hatch: newEnti = new Hatch(hand,x,y);
			break;
		case IdManager.Bathtub: newEnti = new Bathtub(hand,x,y);
			break;
		case IdManager.Bed: newEnti = new Bed(hand,x,y);
			break;
		}
		if(newEnti !=null){
			newEnti.name = name;
			
			enti.add(newEnti);
			entiHash.put(name, newEnti);
			
			it = enti.iterator();
		}
	}
	
	//getters and setters below
	
	public Handler getHand() {
		return hand;
	}
	public HashMap<String, Entity> getEntiHash() {
		return entiHash;
	}
	public ChatBox getChat() {
		return chat;
	}
	public void setChat(ChatBox chat) {
		this.chat = chat;
	}
	public void setHand(Handler hand) {
		this.hand = hand;
	}
	public Player getPl() {
		return pl;
	}
	public void setPl(Player pl) {
		entiHash.remove(this.getPl());
		enti.remove(this.getPl());
		this.pl = pl;
		addEnti(pl, "pl");
	}
	public List<Entity> getEnti() {
		return enti;
	}
	public void setEnti(List<Entity> enti) {
		this.enti = enti;
	}
	public GUI getGui() {
		return gui;
	}
	public void setGui(GUI gui) {
		this.gui = gui;
	}
	
}
