package exterminatorjeff.undergroundbiomes.api.common;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

/**
 * 
 * Allows mods to UBifiy their ores.<br>
 * <br>
 * The setupOre methods must be called after UB pre-init<br>
 * The requestOreSetup methods must be called before UB pre-init<br>
 * 
 * @author CurtisA, LouisDB
 *
 */
public interface UBOresRegistry {

	/**
	 * Create UBified versions for the given ore.
	 * 
	 * @param baseOre
	 */
	void setupOre(Block baseOre);

	/**
	 * Create UBified versions for the given ore with the given metadata value.
	 * 
	 * @param baseOre
	 * @param baseOreMeta
	 */
	void setupOre(Block baseOre, int baseOreMeta);

	/**
	 * Request creation of UBified versions for the given ore.
	 * 
	 * @param baseOre
	 */
	void requestOreSetup(Block baseOre);

	/**
	 * Request creation of UBified versions for the given ore with the given
	 * metadata value.
	 * 
	 * @param baseOre
	 * @param baseOreMeta
	 */
	void requestOreSetup(Block baseOre, int baseOreMeta);

	/**
	 * Register an overlay for the given ore.
	 * 
	 * @param baseOre
	 * @param overlayLocation
	 */
	void registerOreOverlay(Block baseOre, ResourceLocation overlayLocation);

	/**
	 * Register an overlay for the given ore.
	 * 
	 * @param baseOre
	 * @param baseOreMeta
	 * @param overlayLocation
	 */
	void registerOreOverlay(Block baseOre, int baseOreMeta, ResourceLocation overlayLocation);

}
