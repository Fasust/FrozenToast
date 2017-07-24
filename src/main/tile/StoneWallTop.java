package main.tile;
import main.gfx.Assets;

public class StoneWallTop extends Tile{

	public StoneWallTop(int id) {
		super(Assets.wall_top, id);
	}

	public boolean isSolid(){
		return true;
	}
}
