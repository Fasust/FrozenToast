package main.tile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.id.IdManager;

public class Tile {
	
	public static Tile[] tiles = new Tile[256];
	public static Tile stoneWallTop = new StoneWallTop(IdManager.StoneWall_top);
	public static Tile stoneFloor = new StoneFlor(IdManager.StoneFlor);
	public static Tile stoneFloorHole_wall = new StoneFlorHole_wall(IdManager.StoneFlorHole_wall);
	public static Tile stoneFloorHole = new StoneFlorHole(IdManager.StoneFlorHole);
	public static Tile stoneFloor_marked = new StoneFlor_marked(IdManager.StoneFlor_marked);
	public static Tile stoneWall_marked_e = new StoneWall_marked_e(IdManager.StoneWall_marked_e);
	public static Tile stoneWall_e = new StoneWallEast(IdManager.StoneWall_e);
	public static Tile stoneWall_s = new StoneWallSouth(IdManager.StoneWall_s);
	public static Tile stoneWall_w = new StoneWallWest(IdManager.StoneWall_w);
	public static Tile stoneWall_oc_se = new StoneWall_oc_se(IdManager.StoneWall_oc_se);
	public static Tile stoneWall_oc_sw = new StoneWall_oc_sw(IdManager.StoneWall_oc_sw);
	public static Tile stoneWall_ic_se = new StoneWall_ic_se(IdManager.StoneWall_ic_se);
	public static Tile stoneWall_ic_sw = new StoneWall_ic_sw(IdManager.StoneWall_ic_sw);
	public static Tile wood_floor = new WoodFloor(IdManager.WoodFloor);
	public static Tile blue_tile = new BlueTile(IdManager.BlueTiel);
	public static Tile metal_floor = new MetalFoor(IdManager.MetalFloor);
	public static Tile StoneWall_window_n = new StoneWall_window_n(IdManager.StoneWall_window_n);
	protected BufferedImage text;
	protected final int id;
	public static final int TILEBREITE = 64,
							TILEHoeHE = 64;
	
	public Tile(BufferedImage text,int id){
		this.text = text;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick(){
		
	}
	public void render(Graphics g, int x, int y){
		g.drawImage(text, x, y,TILEBREITE,TILEHoeHE, null);
	}
	public boolean isSolid(){
		return false;
	}
	public int getId(){
		return id;
	}
}
