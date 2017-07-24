package main.tile;
import main.gfx.Assets;

public class StoneWall_oc_sw extends Tile{

	public StoneWall_oc_sw(int id) {
		super(Assets.wall_oc_sw, id);
	}

	public boolean isSolid(){
		return true;
	}
}
