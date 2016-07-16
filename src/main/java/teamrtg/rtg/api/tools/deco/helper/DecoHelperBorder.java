package teamrtg.rtg.api.tools.deco.helper;

import java.util.Random;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

/**
 *
 * @author Zeno410
 */
public class DecoHelperBorder extends DecoBase{

	private DecoBase adjusted;
    private float allAbove;
    private float noneBelow;

	public DecoHelperBorder(DecoBase toAdjust, float allAbove, float noneBelow)
	{
		super();
		if (allAbove < noneBelow) throw new RuntimeException("Above and below parameters swapped");
		this.adjusted = toAdjust;
        this.allAbove = allAbove;
        this.noneBelow = noneBelow;
	}

	@Override
	public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator realisticBiomeGenerator, boolean hasPlacedVillageBlocks)
	{
        if (strength < noneBelow) return; // border is too low
        if (strength >= allAbove )  {
            // call with border 1
            adjusted.generate(rtgWorld, rand, chunkX, chunkY, strength, river, realisticBiomeGenerator, hasPlacedVillageBlocks);
        }
        else {
            // call with interpolated border
            float adjustedStrength = (strength - noneBelow)/(allAbove - noneBelow);
            adjusted.generate(rtgWorld, rand, chunkX, chunkY, strength, river, realisticBiomeGenerator, hasPlacedVillageBlocks);
        }

	}
}
