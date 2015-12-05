package rtg.world.gen.surface.enhancedbiomes;

import java.util.Random;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.enhancedbiomes.RealisticBiomeEBEphemeralLake;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceEBEphemeralLake extends SurfaceEBBase
{
    private Block mixBlock;
    private Block cliffBlock1;
    private Block cliffBlock2;
    private float width;
    private float height;
    private byte fillerByte;
    private byte mixByte;
    
    public SurfaceEBEphemeralLake(Block top, Block filler, Block mix, Block cliff1, Block cliff2, float mixWidth, float mixHeight, byte modFillerByte, byte modMixByte)
    {
        super(top, filler);
        
        mixBlock = mix;
        cliffBlock1 = cliff1;
        cliffBlock2 = cliff2;
        
        width = mixWidth;
        height = mixHeight;
        
        fillerByte = modFillerByte;
        mixByte = modMixByte;
    }
    
    @Override
    public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.4f ? true : false;
        
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

                if (shouldReplaceStone()) {
                    blocks[(y * 16 + x) * 256 + k] = RealisticBiomeEBEphemeralLake.ebDominantStoneBlock[0];
                    metadata[(y * 16 + x) * 256 + k] = RealisticBiomeEBEphemeralLake.ebDominantStoneMeta[0];
                }
                
                if(cliff)
                {
                    if(depth > -1 && depth < 2)
                    {
                        blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? cliffBlock2 : cliffBlock1; 
                    }
                    else if (depth < 10)
                    {
                        blocks[(y * 16 + x) * 256 + k] = cliffBlock1;
                    }
                }
                else
                {
                    if(depth == 0 && k > 61)
                    {
                        if(simplex.noise2(i / width, j / width) > height) // > 0.27f, i / 12f
                        {
                            blocks[(y * 16 + x) * 256 + k] = mixBlock;
                            metadata[(y * 16 + x) * 256 + k] = mixByte;
                        }
                        else
                        {
                            blocks[(y * 16 + x) * 256 + k] = topBlock;
                            metadata[(y * 16 + x) * 256 + k] = 0;
                        }
                    }
                    else if(depth < 4)
                    {
                        blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                        metadata[(y * 16 + x) * 256 + k] = fillerByte;
                    }
                }
            }
        }
    }
}

