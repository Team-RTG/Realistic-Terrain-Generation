package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.world.World;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 * This deco replaces the cumbersome rDecorateSeedBiome & rOreGenSeedBiome logic.
 * Instead of having to remember when to use (and not use) rDecorateSeedBiome/rOreGenSeedBiome,
 * now all you have to do is add this configured deco to the realistic biome wherever you want the base biome
 * to decorate itself. You no longer need to worry about ore gen because that gets handled automatically.
 * 
 * @author WhichOnesPink
 *
 */
public class DecoBaseBiomeDecorations extends DecoBase
{
	
	/**
	 * This optional setting is useful when you want the base biome to decorate a majority of the biome's chunks.
	 * Only used if greater than 0
	 */
	public int equalsZeroChance;
	
	/**
	 * This optional setting is useful when you want the base biome to decorate a minority of the biome's chunks.
	 * Only used if greater than 0
	 */
	public int notEqualsZeroChance;
	
	/**
	 * How many times per chunk do we want the base biome to decorate itself? (Usually only once)
	 */
	public int loops;
	
	/**
	 * Height restriction.
	 */
	public int maxY;
	
	public DecoBaseBiomeDecorations()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.equalsZeroChance = 0; // Only used if greater than 0
		this.notEqualsZeroChance = 0; // Only used if greater than 0
		this.loops = 1; // You almost always want to loop only once.
		this.maxY = 255; // No height limit by default.
		
		this.addDecoTypes(DecoType.BASE_BIOME_DECORATION);
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks)
	{
		if (this.allowed) {
            
			for (int i = 0; i < loops; i++) {
				
	            int intX = chunkX + rand.nextInt(16);// + 8;
	            int intZ = chunkY + rand.nextInt(16);// + 8;
	            int intY = world.getHeightValue(intX, intZ);
	            
	            if (intY <= this.maxY) {
	            	
					if (this.equalsZeroChance > 0) {
						
				        if (rand.nextInt(this.equalsZeroChance) == 0) {
				        	biome.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, biome.baseBiome);
				        }
				        else {
				        	biome.rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, biome.baseBiome);
				        }
					}
					else if (this.notEqualsZeroChance > 0) {
						
				        if (rand.nextInt(this.notEqualsZeroChance) != 0) {
				        	biome.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, biome.baseBiome);
				        }
				        else {
				        	biome.rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, biome.baseBiome);
				        }
					}
					else {
						
						biome.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, biome.baseBiome);
					}
	            }
	            else {
	            	biome.rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, biome.baseBiome);
	            }
			}
		}
		else {
			biome.rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, biome.baseBiome);
		}
	}
}
