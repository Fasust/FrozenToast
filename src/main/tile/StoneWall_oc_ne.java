package main.tile;
import main.gfx.Assets;

public class StoneWall_oc_ne extends Tile{

	public StoneWall_oc_ne(int id) {
		super(Assets.wall_oc_ne, id);
	}

	public boolean isSolid(){
		return true;
	}
}
