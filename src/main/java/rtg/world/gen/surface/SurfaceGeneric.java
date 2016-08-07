package rtg.world.gen.surface;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class SurfaceGeneric extends SurfaceBase {

    public SurfaceGeneric(BiomeConfig config, IBlockState top, IBlockState filler) {

        super(config, top, filler);
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState((y * 16 + x) * 256 + k).getBlock();

            if (b == Blocks.air) {
                depth = -1;
            }
            else if (b == Blocks.stone) {
                depth++;

                if (depth == 0 && k > 61) {
                    primer.setBlockState((y * 16 + x) * 256 + k, topBlock);
                }
                else if (depth < 4) {
                    primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock);
                }
            }
        }
    }
}
