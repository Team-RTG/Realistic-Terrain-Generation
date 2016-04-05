package teamrtg.rtg.world.biome.surface;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.biome.RealisticBiomeBase;

import java.util.Random;

public class SurfaceDuneValley extends SurfaceBase {
    private float valley;
    private boolean dirt;
    private boolean mix;

    public SurfaceDuneValley(RealisticBiomeBase biome, float valleySize, boolean d, boolean m) {
        super(biome);

        valley = valleySize;
        dirt = d;
        mix = m;
    }

    @Override
    public void paintSurface(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
        float h = (simplex.noise2(i / valley, j / valley) + 0.25f) * 65f;
        h = h < 1f ? 1f : h;
        float m = simplex.noise2(i / 12f, j / 12f);
        boolean sand = false;

        Block b;
        for (int k = 255; k > -1; k--) {
            b = primer.getBlockState(x, k, y).getBlock();
            if (b == Blocks.air) {
                depth = -1;
            } else if (b == Blocks.stone) {
                depth++;

                if (depth == 0) {
                    if (k > 90f + simplex.noise2(i / 24f, j / 24f) * 10f - h || (m < -0.28f && mix)) {
                        primer.setBlockState(x, k, y, Blocks.sand.getDefaultState());
                        //base[x * 16 + y] = RealisticBiomeVanillaBase.vanillaDesert;
                        sand = true;
                    } else if (dirt && m < 0.22f || k < 62) {
                        primer.setBlockState(x, k, y, Blocks.dirt.getStateFromMeta(1));
                    } else {
                        primer.setBlockState(x, k, y, biome.config.TOP_BLOCK.get());
                    }
                } else if (depth < 6) {
                    if (sand) {
                        if (depth < 4) {
                            primer.setBlockState(x, k, y, Blocks.sand.getDefaultState());
                        } else {
                            primer.setBlockState(x, k, y, Blocks.sandstone.getDefaultState());
                        }
                    } else {
                        primer.setBlockState(x, k, y, biome.config.FILL_BLOCK.get());
                    }
                }
            }
        }
    }
}
