package rtg.world.biome.deco;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.world.World;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 * Documentation for the new biome system can be found here:
 * https://teamrtg.gitbooks.io/rtg-code-documentation/content/biome_decoration.html
 * 
 * @author WhichOnesPink
 *
 */
public class DecoBase
{
    
	/**
	 * If false, the deco won't get generated during chunk decoration.
	 * Currently, the only deco that uses allow=false is the DecoBaseBiomeDecorations deco, and it only gets
	 * set to false when we need to generate ores in biomes that don't let the base biome handle decoration at all.
	 */
	public boolean allowed;
	public ArrayList<DecoType> decoTypes;
	public boolean checkRiver;
	public float minRiver; // Minimum river value required to generate.
	public float maxRiver; // Maximum river value required to generate.
	
	public DecoBase()
	{
		this.allowed = true;
		this.decoTypes = new ArrayList<DecoType>();
		this.checkRiver = false;
		this.minRiver = -2f;
		this.maxRiver = 2f;
	}

    public boolean properlyDefined() {
        // this procedure should return true if the deco can respond properly to a generate() call
        // in particular it should not crash.
        return true;
    }
	/**
	 * Performs pre-generation checks to determine if the deco is allowed to generate.
	 * 
	 * @param biome
	 * @param world
	 * @param rand
	 * @param chunkX
	 * @param chunkY
	 * @param simplex
	 * @param cell
	 * @param strength
	 * @param river
	 * @param hasPlacedVillageBlocks
	 */
	public boolean preGenerate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks)
	{
		if (this.checkRiver) {
			
			if (river > this.maxRiver || river < this.minRiver) {
				return false;
			}
		}
		
		return true;
    }
	
	/**
	 * Generates the decoration.
	 * This method should be overridden in the individual deco objects.
	 * 
	 * @param biome
	 * @param world
	 * @param rand
	 * @param chunkX
	 * @param chunkY
	 * @param simplex
	 * @param cell
	 * @param strength
	 * @param river
	 * @param hasPlacedVillageBlocks
	 */
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks)
	{
		
    }
	
	/**
	 * Enum to classify the various decos.
	 * 
	 * @author WhichOnesPink
	 *
	 */
	public enum DecoType
	{
		BASE_BIOME_DECORATION,
		BOULDER,
		COBWEB,
		CACTUS,
		DEAD_BUSH,
		DESERT_WELL,
		FALLEN_TREE,
		FERN,
		FERN_DOUBLE,
		FLOWER,
		GRASS,
		GRASS_DOUBLE,
		LILYPAD,
		MUSHROOM,
		PUMPKIN,
		REED,
		ROCK_SPIRE,
		SHRUB,
		TEST,
		TREE,
		VINE
	}
	
	/**
	 * Adds one or more deco types.
	 * 
	 * @param decos
	 */
    public void addDecoTypes(DecoType... decos)
    {
    	for (int i = 0; i < decos.length; i++) {
    		this.decoTypes.add(decos[i]);
    	}
    }
}