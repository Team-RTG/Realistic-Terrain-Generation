package rtg.world.biome.deco;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.RandomUtil;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenPond;

/**
 *
 * @author Zeno410
 */
public class DecoPond extends DecoBase {

    private WorldGenerator pondGenerator = new WorldGenPond(Blocks.water);
    public int chunksPerPond;

    @Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed&&ConfigRTG.waterSurfaceLakeChance>0) {


                int i2 = chunkX + rand.nextInt(16);// + 8;
                int i8 = chunkY+ rand.nextInt(16);// + 8;
                int l4 = world.getHeightValue(i2, i8);

                //Surface lakes.
                if (rand.nextInt(chunksPerPond) == 0) {

                    if (l4 > 63) {

                        pondGenerator.generate(world, rand, i2, l4, i8);
                    }
                }
		}
	}
}
