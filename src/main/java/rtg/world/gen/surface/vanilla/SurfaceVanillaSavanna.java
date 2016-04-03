package rtg.world.gen.surface.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSavanna;
import rtg.util.CanyonColour;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

public class SurfaceVanillaSavanna extends SurfaceBase {

    private Block mixBlock;
    private byte mixBlockMeta;
    private float width;
    private float height;

    public SurfaceVanillaSavanna(BiomeConfig config, Block top, Block filler, Block mix, float mixWidth, float mixHeight)
    {

    	super(config, top, (byte)0, filler, (byte)0);

        mixBlock = this.getConfigBlock(config, BiomeConfigVanillaSavanna.surfaceMixBlockId, mix);
        mixBlockMeta = this.getConfigBlockMeta(config, BiomeConfigVanillaSavanna.surfaceMixBlockMetaId, (byte)0);

        width = mixWidth;
        height = mixHeight;
    }

    @Override
    public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {

        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.4f;

        for (int k = 255; k > -1; k--) {
        	Block b = blocks[(y * 16 + x) * 256 + k];
            if (b == Blocks.air) {
                depth = -1;
            } else if (b == Blocks.stone) {
                depth++;

                if (cliff) {
        			blocks[(y * 16 + x) * 256 + k] = CanyonColour.SAVANNA.getBlockForHeight(i, k,j);
        		    metadata[(y * 16 + x) * 256 + k] = CanyonColour.SAVANNA.getMetaForHeight(i, k,j);
                } else {
                    if (depth == 0 && k > 61) {
                        if (simplex.noise2(i / width, j / width) > height) // > 0.27f, i / 12f
                        {
                            blocks[(y * 16 + x) * 256 + k] = mixBlock;
                            metadata[(y * 16 + x) * 256 + k] = mixBlockMeta;
                        } else {
                            blocks[(y * 16 + x) * 256 + k] = topBlock;
                            metadata[(y * 16 + x) * 256 + k] = topBlockMeta;
                        }
                    } else if (depth < 4) {
                        blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                        metadata[(y * 16 + x) * 256 + k] = fillerBlockMeta;
                    }
                }
            }
        }
    }
}