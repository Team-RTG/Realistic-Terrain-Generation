package teamrtg.rtg.api.tools.deco.helper;

/**
 * This deco helper has a one in x chance of called a given deco
 * @author Zeno410
 */
import java.util.Random;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

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
	}

	@Override
	public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator realisticBiomeGenerator, boolean hasPlacedVillageBlocks)
	{
		if (this.allowed) {

			switch (this.chanceType)
			{
				case EQUALS_ZERO:
					
					if (rand.nextInt(this.chance) == 0) {
						this.decoThis.generate(rtgWorld, rand, chunkX, chunkY, strength, river, realisticBiomeGenerator, hasPlacedVillageBlocks);
		            }
					else {
						this.decoThat.generate(rtgWorld, rand, chunkX, chunkY, strength, river, realisticBiomeGenerator, hasPlacedVillageBlocks);
					}
			
					break;
				
				case NOT_EQUALS_ZERO:
					
					if (rand.nextInt(this.chance) != 0) {
						this.decoThis.generate(rtgWorld, rand, chunkX, chunkY, strength, river, realisticBiomeGenerator, hasPlacedVillageBlocks);
		            }
					else {
						this.decoThat.generate(rtgWorld, rand, chunkX, chunkY, strength, river, realisticBiomeGenerator, hasPlacedVillageBlocks);
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