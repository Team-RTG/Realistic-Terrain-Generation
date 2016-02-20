package rtg.world.gen.surface.highlands;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import java.util.Random;

public class SurfaceHLVolcanoIsland extends SurfaceBase
{

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
    
    public SurfaceHLVolcanoIsland(BiomeConfig config, Block top, byte topByte, Block filler, byte fillerByte, Block mixTop, byte mixTopByte, Block mixFiller,
        byte mixFillerByte, Block cliff1, byte cliff1Byte, Block cliff2, byte cliff2Byte, float mixWidth, float mixHeight,
        float smallWidth, float smallStrength)
    {
    
        super(config, top, topByte, filler, fillerByte);
        
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
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                             OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    
        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.4f ? true : false;
        boolean mix = false;
        
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
                
                if (cliff)
                {
                    if (depth > -1 && depth < 2)
                    {
                        if (rand.nextInt(3) == 0) {
                            primer.setBlockState((y * 16 + x) * 256 + k, blockCliff2.getStateFromMeta(byteCliff2));
                        }
                        else {
                            primer.setBlockState((y * 16 + x) * 256 + k, blockCliff1.getStateFromMeta(byteCliff1));
                        }
                        
                    }
                    else if (depth < 10)
                    {
                        primer.setBlockState((y * 16 + x) * 256 + k, blockCliff1.getStateFromMeta(byteCliff1));
                    }
                }
                else
                {
                    if (depth == 0 && k > 61)
                    {
                        if (simplex.noise2(i / floMixWidth, j / floMixWidth) + simplex.noise2(i / floSmallWidth, j / floSmallWidth)
                            * floSmallStrength > floMixHeight)
                        {
                            primer.setBlockState((y * 16 + x) * 256 + k, blockMixTop.getStateFromMeta(byteMixTop));
                            
                            mix = true;
                        }
                        else
                        {
                            primer.setBlockState((y * 16 + x) * 256 + k, topBlock);
                        }
                    }
                    else if (depth < 4)
                    {
                        if (mix)
                        {
                            primer.setBlockState((y * 16 + x) * 256 + k, blockMixFiller.getStateFromMeta(byteMixFiller));
                        }
                        else
                        {
                            primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock);
                        }
                    }
                }
            }
        }
    }
}
