package rtg.world.gen.surface.vanilla;

import java.util.Random;

import net.minecraft.world.chunk.ChunkPrimer;
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
    private final int sandMetadata = 0;
    
    public SurfaceVanillaOcean(Block top, Block filler, Block mix, float mixWidth, float mixHeight)
    {
    
        super(top, filler);
        
        mixBlock = mix;
        
        width = mixWidth;
        height = mixHeight;
    }
    
    @Override
    public void paintTerrain(ChunkPrimer primer, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand,
                             OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    
        for (int k = 255; k > -1; k--)
        {
            Block b = primer.getBlockState((y * 16 + x) * 256 + k).getBlock();
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
                        primer.setBlockState((y * 16 + x) * 256 + k, mixBlock.getDefaultState());
                    }
                    else
                    {
                        primer.setBlockState((y * 16 + x) * 256 + k, topBlock.getDefaultState());
                    }
                }
                else if (depth < 4 && k < 63)
                {
                    primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock.getDefaultState());
                }

                else if (depth == 0 && k <69){
                    primer.setBlockState((y * 16 + x) * 256 + k, Blocks.sand.getDefaultState());
                    metadata[(y * 16 + x) * 256 + k] = sandMetadata;

                }
            }
        }
    }
}
