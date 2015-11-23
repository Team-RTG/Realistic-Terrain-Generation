package rtg.world.gen.surface.enhancedbiomes;

import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceEBFens extends SurfaceEBBase
{
    
    private Block cliffBlock1;
    private Block cliffBlock2;
    private byte topByte;
    private byte fillerByte;
    
    public SurfaceEBFens(Block top, Block filler, Block cliff1, Block cliff2, byte modTopByte, byte modFillerByte)
    {
    
        super(top, filler);
        
        cliffBlock1 = cliff1;
        cliffBlock2 = cliff2;
        
        topByte = modTopByte;
        fillerByte = modFillerByte;
    }
    
    @Override
    public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand,
        OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    
        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.4f ? true : false;
        
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
                
                if (shouldReplaceStone()) {
                    blocks[(y * 16 + x) * 256 + k] = EnhancedBiomesBlocks.stoneEB;
                    metadata[(y * 16 + x) * 256 + k] = (byte) 1;
                }
                
                if (cliff && k > 64)
                {
                    if (depth > -1 && depth < 2)
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
                    if (depth == 0 && k > 61)
                    {
                        blocks[(y * 16 + x) * 256 + k] = topBlock;
                        metadata[(y * 16 + x) * 256 + k] = topByte;
                    }
                    else if (depth < 4)
                    {
                        blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                        metadata[(y * 16 + x) * 256 + k] = fillerByte;
                    }
                }
            }
        }
    }
}
