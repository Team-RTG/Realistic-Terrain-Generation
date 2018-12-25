package rtg.api.world.surface;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.RTGAPI;
import rtg.api.config.BiomeConfig;
import rtg.api.world.RTGWorld;


public class SurfaceRiverOasis extends SurfaceBase {

    // Cut-off noise size. The bigger, the larger the cut-off scale is
    private float cutOffScale;
    // Cut-off noise amplitude. The bigger, the more effect cut-off is going to have in the grass
    private float cutOffAmplitude;

    public SurfaceRiverOasis(BiomeConfig config) {

        super(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState());

        this.cutOffScale = RTGAPI.config().RIVER_CUT_OFF_SCALE.get();
        this.cutOffAmplitude = RTGAPI.config().RIVER_CUT_OFF_AMPLITUDE.get();
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        IBlockState blockState;
        int highestY;

        for (highestY = 255; highestY > 0; highestY--) {
            blockState = primer.getBlockState(x, highestY, z);
            if (blockState.getBlock() != Blocks.AIR) {
                break;
            }
        }

        float amplitude = 0.05f;
        float noiseValue = rtgWorld.simplexInstance(0).noise2f(i / 8f, j / 8f) * amplitude / 1f + rtgWorld.simplexInstance(1).noise2f(i / 3f, j / 3f) * amplitude / 2f;

        // Large scale noise cut-off
        float noiseNeg = rtgWorld.simplexInstance(2).noise2f(i / cutOffScale, j / cutOffScale) * cutOffAmplitude;
        noiseValue -= noiseNeg;

        // Height cut-off
        if (highestY > 62) {
            noiseValue -= (highestY - 62) * (1 / 12f);
        }

        if (river > 0.70 && river + noiseValue > 0.85) {
            for (int k = 255; k > -1; k--) {
                blockState = primer.getBlockState(x, k, z);
                if (blockState.getBlock() == Blocks.AIR) {
                    depth = -1;
                }
                else if (blockState.getMaterial() != Material.WATER) {
                    depth++;

                    if (depth == 0 && k > 61) {
                        primer.setBlockState(x, k, z, Blocks.GRASS.getDefaultState());
                    }
                    else if (depth < 4) {
                        primer.setBlockState(x, k, z, Blocks.DIRT.getDefaultState());
                    }
                    else if (depth > 4) {
                        return;
                    }
                }
            }
        }
    }
}
