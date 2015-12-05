package rtg.world.gen.surface.enhancedbiomes;

import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.enhancedbiomes.RealisticBiomeEBRockyHills;
import rtg.world.biome.realistic.enhancedbiomes.RealisticBiomeEBRockyHills;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceEBRockyHills extends SurfaceEBBase
{
    private boolean beach;
    private Block beachBlock;
    private float min;
    
    private float sCliff = 1.5f;
    private float sHeight = 60f;
    private float sStrength = 65f;
    private float cCliff = 1.5f;
    
    private Block mix;
    private float mixHeight;
    
    private Block mixBlock1;
    private Block mixBlock2;
    private byte topByte;
    private byte fillerByte;
    private byte mixByte;
    private byte mix2Byte;
    
    public SurfaceEBRockyHills(Block top, byte modTopByte, Block fill, byte modFillerByte, boolean genBeach, Block genBeachBlock, float minCliff, float stoneCliff,
        float stoneHeight, float stoneStrength, float clayCliff, Block mixBlock, byte modMixByte, Block modMixBlock2, byte modMix2Byte, float mixSize)
    {
    
        super(top, fill);
        beach = genBeach;
        beachBlock = genBeachBlock;
        min = minCliff;
        
        sCliff = stoneCliff;
        sHeight = stoneHeight;
        sStrength = stoneStrength;
        cCliff = clayCliff;
        
        mix = mixBlock;
        mixBlock2 = modMixBlock2;
        mixHeight = mixSize;
        
        topByte = modTopByte;
        fillerByte = modFillerByte;
        mixByte = modMixByte;
        mix2Byte = modMix2Byte;
    }
    
    @Override
    public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand,
        OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    
        float c = CliffCalculator.calc(x, y, noise);
        int cliff = 0;
        boolean gravel = false;
        boolean m = false;
        
        Block b;
        for (int k = 255; k > -1; k--)
        {
            b = blocks[(y * 16 + x) * 256 + k];
            if (b == Blocks.air)
            {
                depth = -1;
            }
            else if (b == Blocks.stone)
            {
                depth++;

                if (shouldReplaceStone()) {
                    blocks[(y * 16 + x) * 256 + k] = RealisticBiomeEBRockyHills.ebDominantStoneBlock[0];
                    metadata[(y * 16 + x) * 256 + k] = RealisticBiomeEBRockyHills.ebDominantStoneMeta[0];
                }
                
                if (depth == 0)
                {
                    if (k < 63)
                    {
                        if (beach)
                        {
                            gravel = true;
                        }
                    }
                    
                    float p = simplex.noise3(i / 8f, j / 8f, k / 8f) * 0.5f;
                    if (c > min && c > sCliff - ((k - sHeight) / sStrength) + p)
                    {
                        cliff = 1;
                    }
                    if (c > cCliff)
                    {
                        cliff = 2;
                    }
                    
                    if (cliff == 1)
                    {
                        if (rand.nextInt(3) == 0) {
                            blocks[(y * 16 + x) * 256 + k] = topBlock;
                            metadata[(y * 16 + x) * 256 + k] = topByte;
                        }
                        else {
                            blocks[(y * 16 + x) * 256 + k] = mixBlock1;
                            metadata[(y * 16 + x) * 256 + k] = mixByte;
                        }
                    }
                    else if (cliff == 2)
                    {
                        blocks[(y * 16 + x) * 256 + k] = mixBlock2;
                        metadata[(y * 16 + x) * 256 + k] = mix2Byte;
                    }
                    else if (k < 63)
                    {
                        if (beach)
                        {
                            blocks[(y * 16 + x) * 256 + k] = beachBlock;
                            gravel = true;
                        }
                        else if (k < 62)
                        {
                            blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                            metadata[(y * 16 + x) * 256 + k] = fillerByte;
                        }
                        else
                        {
                            blocks[(y * 16 + x) * 256 + k] = mixBlock2;
                            metadata[(y * 16 + x) * 256 + k] = mix2Byte;
                        }
                    }
                    else if (simplex.noise2(i / 12f, j / 12f) > mixHeight)
                    {
                        blocks[(y * 16 + x) * 256 + k] = mix;
                        metadata[(y * 16 + x) * 256 + k] = mixByte;
                        m = true;
                    }
                    else
                    {
                        blocks[(y * 16 + x) * 256 + k] = topBlock;
                        metadata[(y * 16 + x) * 256 + k] = topByte;
                    }
                }
                else if (depth < 6)
                {
                    if (cliff == 1)
                    {
                        blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                        metadata[(y * 16 + x) * 256 + k] = fillerByte;
                    }
                    else if (cliff == 2)
                    {
                        blocks[(y * 16 + x) * 256 + k] = mixBlock2;
                        metadata[(y * 16 + x) * 256 + k] = mix2Byte;
                    }
                    else if (gravel)
                    {
                        blocks[(y * 16 + x) * 256 + k] = beachBlock;
                    }
                    else if (m)
                    {
                        blocks[(y * 16 + x) * 256 + k] = mixBlock2;
                        metadata[(y * 16 + x) * 256 + k] = mix2Byte;
                    }
                    else
                    {
                        blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                        metadata[(y * 16 + x) * 256 + k] = fillerByte;
                    }
                }
            }
        }
    }
}
