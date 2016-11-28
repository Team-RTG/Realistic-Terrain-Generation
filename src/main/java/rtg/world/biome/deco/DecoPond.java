package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.config.ConfigRTG;
import rtg.util.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenPond;

/**
 * @author Zeno410
 */
public class DecoPond extends DecoBase {

    public int chunksPerPond = 8;
    public int minY = 64;
    public int maxY = 240;
    public int loops = 1;

    private WorldGenerator pondGenerator = new WorldGenPond(Blocks.WATER.getDefaultState());

    @Override
    public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed && ConfigRTG.waterSurfaceLakeChance > 0) {

            //Surface lakes.
            for (int i = 0; i < this.loops; i++) {

                int i2 = chunkX + rand.nextInt(16);// + 8;
                int i8 = chunkY + rand.nextInt(16);// + 8;
                int l4 = world.getHeight(new BlockPos(i2, 0, i8)).getY();

                if (rand.nextInt(this.chunksPerPond) == 0) {

                    if (l4 >= this.minY && l4 <= this.maxY) {

                        pondGenerator.generate(world, rand, new BlockPos(i2, l4, i8));
                    }
                }
            }
        }
    }
}
