package exterminatorjeff.undergroundbiomes.api.names;

/**
 * 
 * @author LouisDB
 *
 */
public final class StoneEntry extends BlockEntry {

	protected SlabEntry slab;
	protected ButtonEntry button;
	protected WallEntry wall;
	protected StairsEntry stairs;

	public StoneEntry(String internalName) {
		super(internalName);
	}

	public SlabEntry getSlab() {
		return slab;
	}

	public ButtonEntry getButton() {
		return button;
	}

	public WallEntry getWall() {
		return wall;
	}

	public StairsEntry getStairs() {
		return stairs;
	}

}
