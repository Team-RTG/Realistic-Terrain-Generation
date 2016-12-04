package exterminatorjeff.undergroundbiomes.api.names;

/**
 * 
 * @author LouisDB
 *
 */
public final class WallEntry extends BlockEntry {

	public WallEntry(StoneEntry baseStoneEntry) {
		super(baseStoneEntry.internalName + "_wall");
		baseStoneEntry.wall = this;
	}

}
