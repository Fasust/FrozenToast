package main.tile;
import main.gfx.Assets;

public class StoneWallNorth extends Tile{

	public StoneWallNorth(int id) {
		super(Assets.wall_n, id);
	}

	public boolean isSolid(){
		return true;
	}
}
