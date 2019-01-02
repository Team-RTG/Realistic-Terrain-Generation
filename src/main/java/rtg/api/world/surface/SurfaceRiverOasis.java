package rtg.api.world.surface;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.world.RTGWorld;


public class SurfaceRiverOasis extends SurfaceBase {

    public SurfaceRiverOasis(BiomeConfig config) {
        super(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState());
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        final float cutOffScale     = rtgWorld.getGeneratorSettings().riverCutOffScale;
        final float cutOffAmplitude = rtgWorld.getGeneratorSettings().riverCutOffAmpl;
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
