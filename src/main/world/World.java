package main.world;
import java.awt.Graphics;
import main.Handler;
import main.entity.EntityManager;
import main.entity.creature.Player;
import main.id.IdManager;
import main.tile.Tile;
import main.util.Util;

public class World {

	private Handler hand;
	private int hoehe,breite;
	private int spawnX,spawnY;
	private int[][] tiles;
	
	//Entities
	private EntityManager enti;
	
	public World(Handler hand,String path){
		this.hand = hand;
		enti = new EntityManager(hand,new Player(hand,0,0));
		loadWorld(path);
		
		//add Entititys
		enti.getPl().setX(spawnX);
		enti.getPl().setY(spawnY);
		
	}
	public void tick(){
		enti.tick();
	}
	public void render(Graphics g){
		//get the area you need to render
		int xStart = Math.max(0,(int) hand.getCam().getxOffset()/Tile.TILEBREITE);
		int xEnd = (int) Math.min(breite, (hand.getCam().getxOffset() + hand.getBreite()) /Tile.TILEBREITE +1);
		int yStart = Math.max(0,(int) hand.getCam().getyOffset()/Tile.TILEHoeHE);
		int yEnd = (int) Math.min(hoehe, (hand.getCam().getyOffset() + hand.getHoehe()) /Tile.TILEHoeHE +1);;
		
		//render it
		for(int x=xStart; x < xEnd;x++){
			for(int y=yStart;y < yEnd;y++){
				getTile(x,y).render(g,(int)(x*Tile.TILEBREITE-hand.getCam().getxOffset()),
										(int)(y*Tile.TILEHoeHE-hand.getCam().getyOffset()));
			}
		}
		enti.render(g);
	}
	private void loadWorld(String path){
		String file = Util.loadFile(path);
		String[] tokens = file.split("\\s+");
		breite = Util.parseInt(tokens[0]);
		hoehe = Util.parseInt(tokens[1]);
		spawnX = Util.parseInt(tokens[2]);
		spawnY = Util.parseInt(tokens[3]);
		
		tiles = new int[breite][hoehe];
		for(int x=0; x < breite;x++){
			for(int y=0;y < hoehe;y++){
				String temp = tokens[(x+y*breite)+4];
				if(temp.contains(":")){		//entitys
					String[] idPlusName = temp.split(":");
					tiles[x][y] = Util.parseInt(idPlusName[0]);
					int id = Util.parseInt(idPlusName[1]);
					String name = idPlusName[2];
					enti.addEnti(id, name, x, y);
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) == IdManager.WallTorch){ //torch
						enti.addEnti(IdManager.WallTorch, "torch", x, y);
						tiles[x][y] = 3;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) == IdManager.WaterOuterCorner_nw){ //water_oc_nw
						enti.addEnti(IdManager.WaterOuterCorner_nw, "water_oc_nw", x, y);
						tiles[x][y] = 1;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) == IdManager.WaterOuterCorner_ne){ //water_oc_ne
						enti.addEnti(IdManager.WaterOuterCorner_ne, "water_oc_ne", x, y);
						tiles[x][y] = 1;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) == IdManager.WaterOuterCorner_se){ //water_oc_se
						enti.addEnti(IdManager.WaterOuterCorner_se, "water_oc_se", x, y);
						tiles[x][y] = 1;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) == IdManager.WaterOuterCorner_sw){ //water_oc_sw
						enti.addEnti(IdManager.WaterOuterCorner_sw, "water_oc_sw", x, y);
						tiles[x][y] = 1;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) == IdManager.WaterBorder_w){ //Water_Border_w
						enti.addEnti(IdManager.WaterBorder_w, "Water_Border_w", x, y);
						tiles[x][y] = 1;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) ==  IdManager.WaterBorder_n){ //Water_Border_n
						enti.addEnti(IdManager.WaterBorder_n, "Water_Border_n", x, y);
						tiles[x][y] = 1;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) ==  IdManager.WaterBorder_s){ //Water_Border_s
						enti.addEnti(IdManager.WaterBorder_s, "Water_Border_s", x, y);
						tiles[x][y] = 1;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) ==  IdManager.WaterBorder_e){ //Water_Border_e
						enti.addEnti(IdManager.WaterBorder_e, "Water_Border_e", x, y);
						tiles[x][y] = 1;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) == IdManager.WaterInnerCorner_se){ //water_ic_se
						enti.addEnti(IdManager.WaterInnerCorner_se, "water_ic_se", x, y);
						tiles[x][y] = 1;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) == IdManager.WaterInnerCorner_sw){ //water_ic_sw
						enti.addEnti(IdManager.WaterInnerCorner_sw, "water_ic_sw", x, y);
						tiles[x][y] = 1;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) == IdManager.WaterInnerCorner_ne){ //water_ic_ne
						enti.addEnti( IdManager.WaterInnerCorner_ne, "water_ic_ne", x, y);
						tiles[x][y] = 1;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) == IdManager.WaterInnerCorner_nw){ //water_ic_nw
						enti.addEnti(IdManager.WaterInnerCorner_nw, "water_ic_nw", x, y);
						tiles[x][y] = 1;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) == IdManager.WaterBed){ //Water_Bed
						enti.addEnti(IdManager.WaterBed, "Water_Bed", x, y);
						tiles[x][y] = 1;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) == IdManager.Brige_n){ //bridge_n
						enti.addEnti(IdManager.Brige_n, "bridge_n", x, y);
						tiles[x][y] = 1;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) == IdManager.Brige_e){ //bridge_e
						enti.addEnti(IdManager.Brige_e, "bridge_e", x, y);
						tiles[x][y] = 1;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) == IdManager.Brige_s){ //bridge_s
						enti.addEnti( IdManager.Brige_s, "bridge_s", x, y);
						tiles[x][y] = 1;
				}else if(Util.parseInt(tokens[(x+y*breite)+4]) == IdManager.Brige_w){ //bridge_w
						enti.addEnti(IdManager.Brige_w, "bridge_w", x, y);
						tiles[x][y] = 1;
				}else{
						tiles[x][y] = Util.parseInt(tokens[(x+y*breite)+4]);
					
				}
			}
		}
	}

	public Tile getTile(int x, int y){
		if(x <0 || y < 0 || x >= breite || y >= hoehe){
			return Tile.stoneWallTop; //def tile
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null){
			return Tile.stoneWallTop; //def tile
		}
		return t;
	}
	
	//getters and setters below
	public int getBreite(){
		return breite;
	}
	public EntityManager getEnti() {
		return enti;
	}
	public int getHoehe(){
		return hoehe;
	}
	public int getSpawnX(){
		return spawnX;
	}
	public int getSpawnY(){
		return spawnY;
	}
}

