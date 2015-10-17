package rtg.world.gen.surface.vanilla;

import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceVanillaOcean extends SurfaceBase
{
    
    private Block mixBlock;
    private float width;
    private float height;
    private float mixCheck;
    
    public SurfaceVanillaOcean(Block top, Block filler, Block mix, float mixWidth, float mixHeight)
    {
    
        super(top, filler);
        
        mixBlock = mix;
        
        width = mixWidth;
        height = mixHeight;
    }
    
    @Override
    public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand,
        OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    
        for (int k = 255; k > -1; k--)
        {
            Block b = blocks[(y * 16 + x) * 256 + k];
            if (b == Blocks.air)
            {
                depth = -1;
            }
            else if (b == Blocks.stone)
            {
                depth++;
                
                if (depth == 0 && k > 0 && k < 63)
                {
                    mixCheck = simplex.noise2(i / width, j / width);
                    
                    if (mixCheck > height) // > 0.27f, i / 12f
                    {
                        blocks[(y * 16 + x) * 256 + k] = mixBlock;
                    }
                    else
                    {
                        blocks[(y * 16 + x) * 256 + k] = topBlock;
                    }
                }
                else if (depth < 4)
                {
                    blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                }
            }
        }
    }
}
