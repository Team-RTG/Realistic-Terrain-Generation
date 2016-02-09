package rtg.world.gen.surface.extrabiomes;

import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceEBXLExtremeJungle extends SurfaceBase
{
    
    public SurfaceEBXLExtremeJungle(Block top, Block filler)
    {
        super(top, filler);
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
                }
                else if(depth < 4)
                {
                    blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                }
            }
        }
    }
}
