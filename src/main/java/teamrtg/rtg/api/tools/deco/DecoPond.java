package teamrtg.rtg.api.tools.deco;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;
import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.api.tools.feature.WorldGenPond;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

/**
 *
 * @author Zeno410
 */
public class DecoPond extends DecoBase {

    private WorldGenerator pondGenerator = new WorldGenPond(Blocks.WATER.getDefaultState());
    public int chunksPerPond;

    @Override
	public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator realisticBiomeGenerator, boolean hasPlacedVillageBlocks)
	{
		if (this.allowed && Mods.RTG.config.SURFACE_WATER_LAKE_CHANCE.get() > 0) {


                int i2 = chunkX + rand.nextInt(16);// + 8;
                int i8 = chunkY+ rand.nextInt(16);// + 8;
                int l4 = rtgWorld.world.getHeight(new BlockPos(i2, 0, i8)).getY();

                //Surface lakes.
                if (rand.nextInt(chunksPerPond) == 0) {

                    if (l4 > 63) {

                        pondGenerator.generate(rtgWorld.world, rand, new BlockPos(i2, l4, i8));
                    }
                }
		}
	}
}
