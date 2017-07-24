package main.tile;
import main.gfx.Assets;

public class StoneWall_oc_nw extends Tile{

	public StoneWall_oc_nw(int id) {
		super(Assets.wall_oc_nw, id);
	}

	public boolean isSolid(){
		return true;
	}
}
