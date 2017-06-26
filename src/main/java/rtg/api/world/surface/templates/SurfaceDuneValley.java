package rtg.api.world.surface.templates;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.IRTGWorld;
import rtg.api.world.surface.SurfaceBase;

public class SurfaceDuneValley extends SurfaceBase {

    private float valley;
    private boolean dirt;
    private boolean mix;

    public SurfaceDuneValley(BiomeConfig config, IBlockState top, IBlockState fill, float valleySize, boolean d, boolean m) {

        super(config, top, fill);

        valley = valleySize;
        dirt = d;
        mix = m;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        Random rand = rtgWorld.rand();
        OpenSimplexNoise simplex = rtgWorld.simplex();
        float h = (simplex.noise2(i / valley, j / valley) + 0.25f) * 65f;
        h = h < 1f ? 1f : h;
        float m = simplex.noise2(i / 12f, j / 12f);
        boolean sand = false;

        Block b;
        for (int k = 255; k > -1; k--) {
            b = primer.getBlockState(x, k, z).getBlock();
            if (b == Blocks.AIR) {
                depth = -1;
            }
            else if (b == Blocks.STONE) {
                depth++;

                if (depth == 0) {
                    if (k > 90f + simplex.noise2(i / 24f, j / 24f) * 10f - h || (m < -0.28f && mix)) {
                        primer.setBlockState(x, k, z, Blocks.SAND.getDefaultState());
                        //base[x * 16 + z] = RealisticBiomeVanillaBase.vanillaDesert;
                        sand = true;
                    }
                    else if (dirt && m < 0.22f || k < 62) {
                        primer.setBlockState(x, k, z, BlockUtil.getStateDirt(1));
                    }
                    else {
                        primer.setBlockState(x, k, z, topBlock);
                    }
                }
                else if (depth < 6) {
                    if (sand) {
                        if (depth < 4) {
                            primer.setBlockState(x, k, z, Blocks.SAND.getDefaultState());
                        }
                        else {
                            primer.setBlockState(x, k, z, Blocks.SANDSTONE.getDefaultState());
                        }
                    }
                    else {
                        primer.setBlockState(x, k, z, fillerBlock);
                    }
                }
            }
        }
    }
}
