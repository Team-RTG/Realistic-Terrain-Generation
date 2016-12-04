package exterminatorjeff.undergroundbiomes.api.common;

import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

/**
 * Underground Biomes settings.<br>
 * See the UB configuration file for more information.
 * 
 * @author LouisDB
 *
 */
public interface UBSettings {

	/*
	 * General
	 */

	boolean crashOnProblems();

	boolean realistic();

	boolean ubifyRecipes();

	boolean ubifyOres();

	int regularStoneCrafting();

	float hardnessModifier();

	float resistanceModifier();

	int biomeSize();

	int generationHeight();

	boolean regularStoneBiomes();

	boolean harmoniousStrata();

	Set<Integer> includedDimensions();

	Set<Integer> excludedDimensions();

	boolean dimensionSpecificSeeds();

	boolean ubifyVillages();

	boolean buttonsOn();

	boolean igneousButtonsOn();

	boolean metamorphicButtonsOn();

	boolean sedimentaryButtonsOn();

	boolean stoneButtonsOn();

	boolean cobbleButtonsOn();

	boolean brickButtonsOn();

	boolean stairsOn();

	boolean igneousStairsOn();

	boolean metamorphicStairsOn();

	boolean sedimentaryStairsOn();

	boolean stoneStairsOn();

	boolean cobbleStairsOn();

	boolean brickStairsOn();

	boolean wallsOn();

	boolean igneousWallsOn();

	boolean metamorphicWallsOn();

	boolean sedimentaryWallsOn();

	boolean stoneWallsOn();

	boolean cobbleWallsOn();

	boolean brickWallsOn();

	int buttonRecipeResult();

	boolean disableVanillaStoneVariants();

	/*
	 * Client
	 */

	boolean alternativeSlabTextures();
        
        boolean generationAllowed(IBlockState block);

}
