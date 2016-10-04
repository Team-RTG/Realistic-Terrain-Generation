package rtg.world.gen.surface;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

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
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

        float h = (simplex.noise2(i / valley, j / valley) + 0.25f) * 65f;
        h = h < 1f ? 1f : h;
        float m = simplex.noise2(i / 12f, j / 12f);
        boolean sand = false;

        Block b;
        for (int k = 255; k > -1; k--) {
            b = primer.getBlockState(x, k, y).getBlock();
            if (b == Blocks.AIR) {
                depth = -1;
            }
            else if (b == Blocks.STONE) {
                depth++;

                if (depth == 0) {
                    if (k > 90f + simplex.noise2(i / 24f, j / 24f) * 10f - h || (m < -0.28f && mix)) {
                        primer.setBlockState(x, k, y, Blocks.SAND.getDefaultState());
                        //base[x * 16 + y] = RealisticBiomeVanillaBase.vanillaDesert;
                        sand = true;
                    }
                    else if (dirt && m < 0.22f || k < 62) {
                        primer.setBlockState(x, k, y, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT));
                    }
                    else {
                        primer.setBlockState(x, k, y, topBlock);
                    }
                }
                else if (depth < 6) {
                    if (sand) {
                        if (depth < 4) {
                            primer.setBlockState(x, k, y, Blocks.SAND.getDefaultState());
                        }
                        else {
                            primer.setBlockState(x, k, y, Blocks.SANDSTONE.getDefaultState());
                        }
                    }
                    else {
                        primer.setBlockState(x, k, y, fillerBlock);
                    }
                }
            }
        }
    }
}
