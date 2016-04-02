package rtg.world.gen.surface;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;

import java.util.Random;

public class SurfaceGeneric extends SurfaceBase {

    public SurfaceGeneric(RealisticBiomeBase biome) {
        super(biome);
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, k, y).getBlock();

            if (b == Blocks.air) {
                depth = -1;
            } else if (b == Blocks.stone) {
                depth++;

                if (depth == 0 && k > 61) {
                    primer.setBlockState(x, k, y, biome.FILL_BLOCK.get());
                } else if (depth < 4) {
                    primer.setBlockState(x, k, y, biome.TOP_BLOCK.get());
                }
            }
        }
    }
}
