package teamrtg.rtg.mods.vanilla.surfaces;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.util.math.CliffCalculator;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.world.biome.surface.SurfaceBase;

import java.util.Random;

public class SurfaceVanillaJungleM extends SurfaceBase {

    public SurfaceVanillaJungleM(RealisticBiomeBase biome) {
        super(biome);
    }

    @Override
    public void paintSurface(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.4f ? true : false;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, k, y).getBlock();
            if (b == Blocks.AIR) {
                depth = -1;
            } else if (b == Blocks.STONE) {
                depth++;

                if (cliff) {
                    if (depth > -1 && depth < 2) {
                        if (rand.nextInt(3) == 0) {

                            primer.setBlockState(x, k, y, hcCobble());
                        } else {

                            primer.setBlockState(x, k, y, hcStone());
                        }
                    } else if (depth < 10) {
                        primer.setBlockState(x, k, y, hcStone());
                    }
                } else {
                    if (depth == 0 && k > 61) {
                        primer.setBlockState(x, k, y, biome.config.TOP_BLOCK.get());
                    } else if (depth < 4) {
                        primer.setBlockState(x, k, y, biome.config.FILL_BLOCK.get());
                    }
                }
            }
        }
    }
}
