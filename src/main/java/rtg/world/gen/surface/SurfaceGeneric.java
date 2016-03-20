package rtg.world.gen.surface;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

import java.util.Random;

public class SurfaceGeneric extends SurfaceBase {

    public SurfaceGeneric(BiomeConfig config, IBlockState top, IBlockState filler) {
        super(config, top, filler);
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
                    primer.setBlockState(x, k, y, topBlock);
                } else if (depth < 4) {
                    primer.setBlockState(x, k, y, fillerBlock);
                }
            }
        }
    }
}
