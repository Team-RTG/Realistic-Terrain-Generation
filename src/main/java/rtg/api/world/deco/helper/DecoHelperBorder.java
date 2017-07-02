package rtg.api.world.deco.helper;

import java.util.Random;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.deco.DecoBase;

/**
 * @author Zeno410
 */
public class DecoHelperBorder extends DecoBase {

    private DecoBase adjusted;
    private float allAbove;
    private float noneBelow;

    public DecoHelperBorder(DecoBase toAdjust, float allAbove, float noneBelow) {

        super();
        if (allAbove < noneBelow) {
            throw new RuntimeException("Above and below parameters swapped");
        }
        this.adjusted = toAdjust;
        this.allAbove = allAbove;
        this.noneBelow = noneBelow;
        if (!toAdjust.properlyDefined()) {
            throw new RuntimeException();
        }
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (strength < noneBelow) {
            return; // border is too low
        }
        if (strength >= allAbove) {
            // call with border 1
            adjusted.generate(biome, rtgWorld, rand, chunkX, chunkY, strength, river, hasPlacedVillageBlocks);
        }
        else {
            // call with interpolated border
            float adjustedStrength = (strength - noneBelow) / (allAbove - noneBelow);
            adjusted.generate(biome, rtgWorld, rand, chunkX, chunkY, adjustedStrength, river, hasPlacedVillageBlocks);
        }

    }
}
