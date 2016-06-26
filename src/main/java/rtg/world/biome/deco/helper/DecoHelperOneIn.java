
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
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks)
	{
		if (this.allowed) {

            if (rand.nextInt(this.chances) == 0) {

                this.deco.generate(biome, world, rand, chunkX, chunkY, simplex, cell, strength, river, hasPlacedVillageBlocks);
            }
        }
	}
}