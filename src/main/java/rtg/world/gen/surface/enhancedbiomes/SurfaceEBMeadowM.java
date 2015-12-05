package rtg.world.gen.surface.enhancedbiomes;

import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.enhancedbiomes.RealisticBiomeEBMeadowM;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceEBMeadowM extends SurfaceEBBase
{
    private static Block ebStoneBlock = Blocks.stone;
    private static byte ebStoneByte = (byte)0;
    
    private Block blockTop;
    private byte byteTop;
    private Block blockFiller;
    private byte byteFiller;
    private Block blockMixTop;
    private byte byteMixTop;
    private Block blockMixFiller;
    private byte byteMixFiller;
    private Block blockCliff1;
    private byte byteCliff1;
    private Block blockCliff2;
    private byte byteCliff2;
    private float floMixWidth;
    private float floMixHeight;
    private float floSmallWidth;
    private float floSmallStrength;
    
    public SurfaceEBMeadowM(Block top, byte topByte, Block filler, byte fillerByte, Block mixTop, byte mixTopByte, Block mixFiller,
        byte mixFillerByte, Block cliff1, byte cliff1Byte, Block cliff2, byte cliff2Byte, float mixWidth, float mixHeight,
        float smallWidth, float smallStrength)
    {
    
        super(top, filler);
        
        blockTop = top;
        byteTop = topByte;
        blockFiller = filler;
        byteFiller = fillerByte;
        
        blockMixTop = mixTop;
        byteMixTop = mixTopByte;
        blockMixFiller = mixFiller;
        byteMixFiller = mixFillerByte;
        
        blockCliff1 = cliff1;
        byteCliff1 = cliff1Byte;
        
        blockCliff2 = cliff2;
        byteCliff2 = cliff2Byte;
        
        floMixWidth = mixWidth;
        floMixHeight = mixHeight;
        floSmallWidth = smallWidth;
        floSmallStrength = smallStrength;
    }
    
    @Override
    public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand,
        OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    
        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.4f ? true : false;
        boolean mix = false;
        
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
                    blocks[(y * 16 + x) * 256 + k] = RealisticBiomeEBMeadowM.ebDominantStoneBlock[0];
                    metadata[(y * 16 + x) * 256 + k] = RealisticBiomeEBMeadowM.ebDominantStoneMeta[0];
                }
                
                if (cliff)
                {
                    if (depth > -1 && depth < 2)
                    {
                        if (rand.nextInt(3) == 0) {
                            blocks[(y * 16 + x) * 256 + k] = blockCliff2;
                            metadata[(y * 16 + x) * 256 + k] = byteCliff2;
                        }
                        else {
                            blocks[(y * 16 + x) * 256 + k] = blockCliff1;
                            metadata[(y * 16 + x) * 256 + k] = byteCliff1;
                        }
                        
                    }
                    else if (depth < 10)
                    {
                        blocks[(y * 16 + x) * 256 + k] = blockCliff1;
                        metadata[(y * 16 + x) * 256 + k] = byteCliff1;
                    }
                }
                else
                {
                    if (depth == 0 && k > 61)
                    {
                        if (simplex.noise2(i / floMixWidth, j / floMixWidth) + simplex.noise2(i / floSmallWidth, j / floSmallWidth)
                            * floSmallStrength > floMixHeight)
                        {
                            blocks[(y * 16 + x) * 256 + k] = blockMixTop;
                            metadata[(y * 16 + x) * 256 + k] = byteMixTop;
                            
                            mix = true;
                        }
                        else
                        {
                            blocks[(y * 16 + x) * 256 + k] = topBlock;
                            metadata[(y * 16 + x) * 256 + k] = byteTop;
                        }
                    }
                    else if (depth < 4)
                    {
                        if (mix)
                        {
                            blocks[(y * 16 + x) * 256 + k] = blockMixFiller;
                            metadata[(y * 16 + x) * 256 + k] = byteMixFiller;
                        }
                        else
                        {
                            blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                            metadata[(y * 16 + x) * 256 + k] = byteFiller;
                        }
                    }
                }
            }
        }
    }
}
