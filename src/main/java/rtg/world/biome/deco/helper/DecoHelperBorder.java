package rtg.world.biome.deco.helper;

import rtg.api.world.RTGWorld;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.realistic.RealisticBiomeBase;

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
    public void generate(RealisticBiomeBase biome, RTGWorld rtgWorld, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (strength < noneBelow) {
                return; // border is too low
            }
            if (strength >= allAbove) {
                // call with border 1
                adjusted.generate(biome, rtgWorld, worldX, worldZ, strength, river, hasPlacedVillageBlocks);
            }
            else {
                // call with interpolated border
                float adjustedStrength = (strength - noneBelow) / (allAbove - noneBelow);
                adjusted.generate(biome, rtgWorld, worldX, worldZ, adjustedStrength, river, hasPlacedVillageBlocks);
            }
        }
    }
}
