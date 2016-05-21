package rtg.world.gen.surface.icmod;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import java.util.Random;

/**
 * Created by VelocityRa on 16/4/2016.
 */
public class SurfaceICIceCream extends SurfaceBase
{
    public SurfaceICIceCream(BiomeConfig config, Block top, Block filler, Block cliff1, Block cliff2)
    {
        super(config, top, (byte)0, filler, (byte)0);
    }

    @Override
    public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
        for(int k = 255; k > -1; k--)
        {
            Block b = blocks[(y * 16 + x) * 256 + k];
            if(b == Blocks.air)
            {
                depth = -1;
            }
            else if(b == Blocks.stone)
            {
                depth++;

                if(depth == 0 && k > 61)
                {
                    blocks[(y * 16 + x) * 256 + k] = topBlock;
                    metadata[(y * 16 + x) * 256 + k] = topBlockMeta;
                }
                else if(depth < 4)
                {
                    blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                    metadata[(y * 16 + x) * 256 + k] = fillerBlockMeta;
                }
            }
        }
    }
}


