package main.tile;
import main.gfx.Assets;

public class StoneWallWest extends Tile{

	public StoneWallWest(int id) {
		super(Assets.wall_w, id);
	}

	public boolean isSolid(){
		return true;
	}
}
