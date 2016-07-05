package teamrtg.rtg.api.tools.deco.helper;

/**
 * This deco helper has a one in x chance of called a given deco
 * @author Zeno410
 */
import java.util.Random;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

public class DecoHelperOneIn extends DecoBase
{

	private DecoBase deco;
	private int chances;

	public DecoHelperOneIn(DecoBase deco, int chances)
	{
		super();

		this.deco = deco;
		this.chances = chances;
	}

	@Override
	public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator realisticBiomeGenerator, boolean hasPlacedVillageBlocks)
	{
		if (this.allowed) {

            if (rand.nextInt(this.chances) == 0) {

                this.deco.generate(rtgWorld, rand, chunkX, chunkY, strength, river, realisticBiomeGenerator, hasPlacedVillageBlocks);
            }
        }
	}
}