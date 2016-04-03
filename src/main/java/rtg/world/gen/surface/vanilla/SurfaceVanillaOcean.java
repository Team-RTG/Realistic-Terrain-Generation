package rtg.world.gen.surface.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;

import java.util.Random;

public class SurfaceVanillaOcean extends SurfaceBase {

    private final int sandMetadata = 0;

    private float width;
    private float height;
    private float mixCheck;

    public SurfaceVanillaOcean(RealisticBiomeBase biome, float mixWidth, float mixHeight) {

        super(biome);

        width = mixWidth;
        height = mixHeight;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                             OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, k, y).getBlock();
            if (b == Blocks.air) {
                depth = -1;
            } else if (b == Blocks.stone) {
                depth++;

                if (depth == 0 && k > 0 && k < 63) {
                    mixCheck = simplex.noise2(i / width, j / width);

                    if (mixCheck > height) // > 0.27f, i / 12f
                    {
                        primer.setBlockState(x, k, y, biome.config.MIX_BLOCK.get());
                    } else {
                        primer.setBlockState(x, k, y, biome.config.TOP_BLOCK.get());
                    }
                } else if (depth < 4 && k < 63) {
                    primer.setBlockState(x, k, y, biome.config.FILL_BLOCK.get());
                } else if (depth == 0 && k < 69) {
                    primer.setBlockState(x, k, y, Blocks.sand.getStateFromMeta(sandMetadata));

                }
            }
        }
    }
}
