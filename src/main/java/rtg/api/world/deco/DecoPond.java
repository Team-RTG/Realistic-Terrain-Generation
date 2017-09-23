package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenPond;

/**
 * @author Zeno410
 */
public class DecoPond extends DecoBase {

    private int chunksPerPond = 8;
    private int minY = 64;
    private int maxY = 240;
    private int loops = 1;

    private WorldGenerator pondGenerator = new WorldGenPond(Blocks.WATER.getDefaultState());
    private RTGConfig rtgConfig = RTGAPI.config();

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed && rtgConfig.WATER_SURFACE_LAKE_CHANCE.get() > 0 && biome.getConfig().ALLOW_PONDS_WATER.get()) {

            //Surface lakes.
            for (int i = 0; i < this.loops; i++) {

                int i2 = worldX + rand.nextInt(16);// + 8;
                int i8 = worldZ + rand.nextInt(16);// + 8;
                int l4 = rtgWorld.world().getHeight(new BlockPos(i2, 0, i8)).getY();

                if (rand.nextInt(this.chunksPerPond) == 0) {

                    if (l4 >= this.minY && l4 <= this.maxY) {

                        pondGenerator.generate(rtgWorld.world(), rand, new BlockPos(i2, l4, i8));
                    }
                }
            }
        }
    }

    public int getChunksPerPond() {

        return chunksPerPond;
    }

    public DecoPond setChunksPerPond(int chunksPerPond) {

        this.chunksPerPond = chunksPerPond;
        return this;
    }

    public int getMinY() {

        return minY;
    }

    public DecoPond setMinY(int minY) {

        this.minY = minY;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoPond setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public int getLoops() {

        return loops;
    }

    public DecoPond setLoops(int loops) {

        this.loops = loops;
        return this;
    }
}
