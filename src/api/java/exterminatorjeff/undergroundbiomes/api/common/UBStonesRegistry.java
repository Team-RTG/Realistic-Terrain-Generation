package exterminatorjeff.undergroundbiomes.api.common;

import java.util.List;

import exterminatorjeff.undergroundbiomes.api.enums.UBStoneStyle;
import exterminatorjeff.undergroundbiomes.api.enums.UBStoneType;
import exterminatorjeff.undergroundbiomes.api.names.ButtonEntry;
import exterminatorjeff.undergroundbiomes.api.names.SlabEntry;
import exterminatorjeff.undergroundbiomes.api.names.StairsEntry;
import exterminatorjeff.undergroundbiomes.api.names.StoneEntry;
import exterminatorjeff.undergroundbiomes.api.names.WallEntry;

/**
 * Utility methods to access Underground Biomes content.
 * 
 * @author LouisDB
 *
 */
public interface UBStonesRegistry {

	List<StoneEntry> allStones();

	List<StoneEntry> stonesFor(UBStoneType type);

	List<StoneEntry> stonesFor(UBStoneType type1, UBStoneType type2);

	List<StoneEntry> stonesFor(UBStoneStyle style);

	List<StoneEntry> stonesFor(UBStoneStyle style1, UBStoneStyle style2);

	StoneEntry stoneFor(UBStoneType type, UBStoneStyle style);

	List<SlabEntry> allSlabs();

	List<SlabEntry> slabsFor(UBStoneType type);

	List<SlabEntry> slabsFor(UBStoneType type1, UBStoneType type2);

	List<SlabEntry> slabsFor(UBStoneStyle style);

	List<SlabEntry> slabsFor(UBStoneStyle style1, UBStoneStyle style2);

	SlabEntry slabFor(UBStoneType type, UBStoneStyle style);

	List<ButtonEntry> allButtons();

	List<ButtonEntry> buttonsFor(UBStoneType type);

	List<ButtonEntry> buttonsFor(UBStoneType type1, UBStoneType type2);

	List<ButtonEntry> buttonsFor(UBStoneStyle style);

	List<ButtonEntry> buttonsFor(UBStoneStyle style1, UBStoneStyle style2);

	ButtonEntry buttonFor(UBStoneType type, UBStoneStyle style);

	List<StairsEntry> allStairs();

	List<StairsEntry> stairsFor(UBStoneType type);

	List<StairsEntry> stairsFor(UBStoneType type1, UBStoneType type2);

	List<StairsEntry> stairsFor(UBStoneStyle style);

	List<StairsEntry> stairsFor(UBStoneStyle style1, UBStoneStyle style2);

	StairsEntry stairsFor(UBStoneType type, UBStoneStyle style);

	List<WallEntry> allWalls();

	List<WallEntry> wallsFor(UBStoneType type);

	List<WallEntry> wallsFor(UBStoneType type1, UBStoneType type2);

	List<WallEntry> wallsFor(UBStoneStyle style);

	List<WallEntry> wallsFor(UBStoneStyle style1, UBStoneStyle style2);

	WallEntry wallFor(UBStoneType type, UBStoneStyle style);

}
