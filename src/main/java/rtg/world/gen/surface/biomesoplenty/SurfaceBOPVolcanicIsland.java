package rtg.world.gen.surface.biomesoplenty;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;

public class SurfaceBOPVolcanicIsland extends SurfaceBase
{
    
    private Block blockMixTop;
    private byte byteMixTop;
    private Block blockMixFiller;
    private byte byteMixFiller;
    private float floMixWidth;
    private float floMixHeight;
    private float floSmallWidth;
    private float floSmallStrength;
    
    public SurfaceBOPVolcanicIsland(BiomeConfig config, Block top, byte topByte, Block filler, byte fillerByte, Block mixTop, byte mixTopByte, Block mixFiller,
        byte mixFillerByte, float mixWidth, float mixHeight, float smallWidth, float smallStrength)
    {
    
        super(config, top, topByte, filler, fillerByte);
        
        blockMixTop = mixTop;
        byteMixTop = mixTopByte;
        blockMixFiller = mixFiller;
        byteMixFiller = mixFillerByte;
        
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
                            
                            primer.setBlockState((y * 16 + x) * 256 + k, hcCobble(world, i, j, x, y, k));
                        }
                        else {
                            
                            primer.setBlockState((y * 16 + x) * 256 + k, hcStone(world, i, j, x, y, k));
                        }
                    }
                    else if (depth < 10)
                    {
                        primer.setBlockState((y * 16 + x) * 256 + k, hcStone(world, i, j, x, y, k));
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
