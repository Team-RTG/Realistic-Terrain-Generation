package exterminatorjeff.undergroundbiomes.api;

import exterminatorjeff.undergroundbiomes.api.common.UBDropsRegistry;
import exterminatorjeff.undergroundbiomes.api.common.UBOresRegistry;
import exterminatorjeff.undergroundbiomes.api.common.UBSettings;
import exterminatorjeff.undergroundbiomes.api.common.UBStonesRegistry;
import exterminatorjeff.undergroundbiomes.api.names.ButtonEntry;
import exterminatorjeff.undergroundbiomes.api.names.ItemEntry;
import exterminatorjeff.undergroundbiomes.api.names.SlabEntry;
import exterminatorjeff.undergroundbiomes.api.names.StairsEntry;
import exterminatorjeff.undergroundbiomes.api.names.StoneEntry;
import exterminatorjeff.undergroundbiomes.api.names.WallEntry;

/**
 * Static access to Underground Biomes API.
 * 
 * @author CurtisA, LouisDB
 *
 */
public final class API {
    
    public static String VERSION = "1.0.0";

	public static UBStonesRegistry STONES_REGISTRY;
	public static UBOresRegistry ORES_REGISTRY;
	public static UBDropsRegistry DROPS_REGISTRY;
        public static UBDimensionalStrataColumnProvider STRATA_COLUMN_PROVIDER;
	public static UBSettings SETTINGS;

	/*
	 * Stones
	 */

	public static final StoneEntry IGNEOUS_STONE = new StoneEntry("igneous_stone");
	public static final StoneEntry IGNEOUS_COBBLE = new StoneEntry("igneous_cobble");
	public static final StoneEntry IGNEOUS_BRICK = new StoneEntry("igneous_brick");
	public static final StoneEntry METAMORPHIC_STONE = new StoneEntry("metamorphic_stone");
	public static final StoneEntry METAMORPHIC_COBBLE = new StoneEntry("metamorphic_cobble");
	public static final StoneEntry METAMORPHIC_BRICK = new StoneEntry("metamorphic_brick");
	public static final StoneEntry SEDIMENTARY_STONE = new StoneEntry("sedimentary_stone");

	/*
	 * Slabs
	 */

	public static final SlabEntry IGNEOUS_STONE_SLAB = new SlabEntry(IGNEOUS_STONE);
	public static final SlabEntry IGNEOUS_COBBLE_SLAB = new SlabEntry(IGNEOUS_COBBLE);
	public static final SlabEntry IGNEOUS_BRICK_SLAB = new SlabEntry(IGNEOUS_BRICK);
	public static final SlabEntry METAMORPHIC_STONE_SLAB = new SlabEntry(METAMORPHIC_STONE);
	public static final SlabEntry METAMORPHIC_COBBLE_SLAB = new SlabEntry(METAMORPHIC_COBBLE);
	public static final SlabEntry METAMORPHIC_BRICK_SLAB = new SlabEntry(METAMORPHIC_BRICK);
	public static final SlabEntry SEDIMENTARY_STONE_SLAB = new SlabEntry(SEDIMENTARY_STONE);

	/*
	 * Buttons
	 */

	public static final ButtonEntry IGNEOUS_STONE_BUTTON = new ButtonEntry(IGNEOUS_STONE);
	public static final ButtonEntry IGNEOUS_COBBLE_BUTTON = new ButtonEntry(IGNEOUS_COBBLE);
	public static final ButtonEntry IGNEOUS_BRICK_BUTTON = new ButtonEntry(IGNEOUS_BRICK);
	public static final ButtonEntry METAMORPHIC_STONE_BUTTON = new ButtonEntry(METAMORPHIC_STONE);
	public static final ButtonEntry METAMORPHIC_COBBLE_BUTTON = new ButtonEntry(METAMORPHIC_COBBLE);
	public static final ButtonEntry METAMORPHIC_BRICK_BUTTON = new ButtonEntry(METAMORPHIC_BRICK);
	public static final ButtonEntry SEDIMENTARY_STONE_BUTTON = new ButtonEntry(SEDIMENTARY_STONE);

	/*
	 * Walls
	 */

	public static final WallEntry IGNEOUS_STONE_WALL = new WallEntry(IGNEOUS_STONE);
	public static final WallEntry IGNEOUS_COBBLE_WALL = new WallEntry(IGNEOUS_COBBLE);
	public static final WallEntry IGNEOUS_BRICK_WALL = new WallEntry(IGNEOUS_BRICK);
	public static final WallEntry METAMORPHIC_STONE_WALL = new WallEntry(METAMORPHIC_STONE);
	public static final WallEntry METAMORPHIC_COBBLE_WALL = new WallEntry(METAMORPHIC_COBBLE);
	public static final WallEntry METAMORPHIC_BRICK_WALL = new WallEntry(METAMORPHIC_BRICK);
	public static final WallEntry SEDIMENTARY_STONE_WALL = new WallEntry(SEDIMENTARY_STONE);

	/*
	 * Stairs
	 */

	public static final StairsEntry IGNEOUS_STONE_STAIRS = new StairsEntry(IGNEOUS_STONE);
	public static final StairsEntry IGNEOUS_COBBLE_STAIRS = new StairsEntry(IGNEOUS_COBBLE);
	public static final StairsEntry IGNEOUS_BRICK_STAIRS = new StairsEntry(IGNEOUS_BRICK);
	public static final StairsEntry METAMORPHIC_STONE_STAIRS = new StairsEntry(METAMORPHIC_STONE);
	public static final StairsEntry METAMORPHIC_COBBLE_STAIRS = new StairsEntry(METAMORPHIC_COBBLE);
	public static final StairsEntry METAMORPHIC_BRICK_STAIRS = new StairsEntry(METAMORPHIC_BRICK);
	public static final StairsEntry SEDIMENTARY_STONE_STAIRS = new StairsEntry(SEDIMENTARY_STONE);

	/*
	 * Items
	 */

	public static final ItemEntry LIGNITE_COAL = new ItemEntry("lignite_coal");
	public static final ItemEntry FOSSIL_PIECE = new ItemEntry("fossil_piece");

	/*
	 *
	 */

	private API() {
	}

}
