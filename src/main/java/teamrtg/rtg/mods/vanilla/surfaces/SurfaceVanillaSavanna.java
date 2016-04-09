package teamrtg.rtg.mods.vanilla.surfaces;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.util.math.CanyonColour;
import teamrtg.rtg.util.math.CliffCalculator;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.world.biome.surface.SurfaceBase;

import java.util.Random;

public class SurfaceVanillaSavanna extends SurfaceBase {


    private float width;
    private float height;

    public SurfaceVanillaSavanna(RealisticBiomeBase biome, float mixWidth, float mixHeight) {

        super(biome);

        width = mixWidth;
        height = mixHeight;
    }

    @Override
    public void paintSurface(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                             OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.4f;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, k, y).getBlock();
            if (b == Blocks.air) {
                depth = -1;
            } else if (b == Blocks.stone) {
                depth++;

                if (cliff) {
                    primer.setBlockState(x, k, y, CanyonColour.SAVANNA.getAt(i, k, j));
                } else {
                    if (depth == 0 && k > 61) {
                        if (simplex.noise2(i / width, j / width) > height) // > 0.27f, i / 12f
                        {
                            primer.setBlockState(x, k, y, biome.config.MIX_BLOCK.get());
                        } else {
                            primer.setBlockState(x, k, y, biome.config.TOP_BLOCK.get());
                        }
                    } else if (depth < 4) {
                        primer.setBlockState(x, k, y, biome.config.FILL_BLOCK.get());
                    }
                }
            }
        }
    }
}
