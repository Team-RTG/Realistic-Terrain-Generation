package rtg.api.world.surface;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.RTGAPI;
import rtg.api.config.BiomeConfig;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.IRTGWorld;

public class SurfaceRiverOasis extends SurfaceBase {

    // Cut-off noise size. The bigger, the larger the cut-off scale is
    private float cutOffScale;
    // Cut-off noise amplitude. The bigger, the more effect cut-off is going to have in the grass
    private float cutOffAmplitude;

    public SurfaceRiverOasis(BiomeConfig config) {

        super(config, Blocks.GRASS, (byte) 0, Blocks.DIRT, (byte) 0);

        this.cutOffScale = RTGAPI.config().RIVER_CUT_OFF_SCALE.get();
        this.cutOffAmplitude = RTGAPI.config().RIVER_CUT_OFF_AMPLITUDE.get();
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        Random rand = rtgWorld.rand();
        OpenSimplexNoise simplex = rtgWorld.simplex();

        IBlockState blockState;
        int highestY;

        for (highestY = 255; highestY > 0; highestY--)
        {
            blockState = primer.getBlockState(x, highestY, z);
            if(blockState.getBlock() != Blocks.AIR)
                break;
        }

        float amplitude = 0.25f;
        float noiseValue = 	(simplex.octave(0).noise2(i / 21f, j / 21f) * amplitude/1f);
        noiseValue += 	(simplex.octave(1).noise2(i / 12f, j / 12f) * amplitude/2f);

        // Large scale noise cut-off
        float noiseNeg = (simplex.octave(2).noise2(i / cutOffScale, j / cutOffScale) * cutOffAmplitude);
        noiseValue -= noiseNeg;

        // Height cut-off
        if(highestY > 62)
            noiseValue -= (highestY - 62) * (1 / 20f);

        if(river > 0.50 && river * 1.1 + noiseValue  > 0.79)
        {
            for(int k = 255; k > -1; k--)
            {
                blockState = primer.getBlockState(x, k, z);
                if(blockState.getBlock() == Blocks.AIR)
                {
                    depth = -1;
                }
                else if(blockState.getMaterial() != Material.WATER)
                {
                    depth++;

                    if(depth == 0 && k > 61)
                    {
                        primer.setBlockState(x, k, z, Blocks.GRASS.getDefaultState());
                    }
                    else if(depth < 4)
                    {
                        primer.setBlockState(x, k, z, Blocks.DIRT.getDefaultState());
                    }
                    else if(depth > 4)
                    {
                        return;
                    }
                }
            }
        }
    }
}
