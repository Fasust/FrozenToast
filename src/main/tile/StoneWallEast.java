package main.tile;
import main.gfx.Assets;

public class StoneWallEast extends Tile{

	public StoneWallEast(int id) {
		super(Assets.wall_e, id);
	}

	public boolean isSolid(){
		return true;
	}
}
