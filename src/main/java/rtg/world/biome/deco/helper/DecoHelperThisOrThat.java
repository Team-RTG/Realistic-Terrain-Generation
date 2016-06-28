
package rtg.world.biome.deco.helper;

/**
 * This deco helper has a one in x chance of called a given deco
 * @author Zeno410
 */
import java.util.Random;

import net.minecraft.world.World;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.realistic.RealisticBiomeBase;

public class DecoHelperThisOrThat extends DecoBase
{

	public int chance;
	public ChanceType chanceType;
	private DecoBase decoThis;
	private DecoBase decoThat;

	public DecoHelperThisOrThat(int chance, ChanceType chanceType, DecoBase decoThis, DecoBase decoThat)
	{
		super();

		this.chance = chance;
		this.chanceType = chanceType;
		this.decoThis = decoThis;
		this.decoThat = decoThat;
        if (!decoThis.properlyDefined()) {
            throw new RuntimeException();
        }
        if (!decoThat.properlyDefined()) {
            throw new RuntimeException();
        }
	}

	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks)
	{
		if (this.allowed) {

			switch (this.chanceType)
			{
				case EQUALS_ZERO:
					
					if (rand.nextInt(this.chance) == 0) {
						this.decoThis.generate(biome, world, rand, chunkX, chunkY, simplex, cell, strength, river, hasPlacedVillageBlocks);
		            }
					else {
						this.decoThat.generate(biome, world, rand, chunkX, chunkY, simplex, cell, strength, river, hasPlacedVillageBlocks);
					}
			
					break;
				
				case NOT_EQUALS_ZERO:
					
					if (rand.nextInt(this.chance) != 0) {
						this.decoThis.generate(biome, world, rand, chunkX, chunkY, simplex, cell, strength, river, hasPlacedVillageBlocks);
		            }
					else {
						this.decoThat.generate(biome, world, rand, chunkX, chunkY, simplex, cell, strength, river, hasPlacedVillageBlocks);
					}
			
					break;
				
				default:
					break;
				
			}
        }
	}
	
	public enum ChanceType
	{
		EQUALS_ZERO,
		NOT_EQUALS_ZERO;
	}
}