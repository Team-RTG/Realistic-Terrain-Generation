
package rtg.world.biome.deco.helper;

import java.util.Random;
import net.minecraft.world.World;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.realistic.RealisticBiomeBase;

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
        if (!toAdjust.properlyDefined()) throw new RuntimeException();
	}

	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
        if (strength < noneBelow) return; // border is too low
        if (strength >= allAbove )  {
            // call with border 1
            adjusted.generate(biome, world, rand, chunkX, chunkY, simplex, cell, strength, river);
        }
        else {
            // call with interpolated border
            float adjustedStrength = (strength - noneBelow)/(allAbove - noneBelow);
            adjusted.generate(biome, world, rand, chunkX, chunkY, simplex, cell, adjustedStrength, river);
        }

	}
}
