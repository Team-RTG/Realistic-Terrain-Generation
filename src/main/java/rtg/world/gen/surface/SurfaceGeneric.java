package rtg.world.gen.surface;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.config.BiomeConfig;
import rtg.util.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;

public class SurfaceGeneric extends SurfaceBase {

    public SurfaceGeneric(BiomeConfig config, IBlockState top, IBlockState filler) {

        super(config, top, filler);
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, k, y).getBlock();

            if (b == Blocks.AIR) {
                depth = -1;
            }
            else if (b == Blocks.STONE) {
                depth++;

                if (depth == 0 && k > 61) {
                    primer.setBlockState(x, k, y, topBlock);
                }
                else if (depth < 4) {
                    primer.setBlockState(x, k, y, fillerBlock);
                }
            }
        }
    }
}
