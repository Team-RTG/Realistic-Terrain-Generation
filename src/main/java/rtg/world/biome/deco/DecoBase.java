package rtg.world.biome.deco;

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
	
	public DecoBase()
	{
		this.allowed = true;
	}
	
	/**
	 * Generates the decoration.
	 * The parameters are virtually the same as the ones passed to the legacy rDecorate() method.
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
	 */
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		
    }
}