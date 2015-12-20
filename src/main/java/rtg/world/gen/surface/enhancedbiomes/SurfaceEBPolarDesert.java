package rtg.world.gen.surface.enhancedbiomes;

import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SnowHeightCalculator;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceEBPolarDesert extends SurfaceEBBase
{
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

    public SurfaceEBPolarDesert(Block top, byte topByte, Block filler, byte fillerByte, Block mixTop, byte mixTopByte, Block mixFiller,
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
    
        boolean water = false;
        boolean riverPaint = false;
        boolean grass = false;
        
        if(river > 0.05f && river + (simplex.noise2(i / 10f, j / 10f) * 0.1f) > 0.86f)
        {
            riverPaint = true;
            
            if(simplex.noise2(i / 12f, j / 12f) > 0.25f)
            {
                grass = true;
            }
        }
        
        Block b;
        for(int k = 255; k > -1; k--)
        {
            b = blocks[(y * 16 + x) * 256 + k];
            if(b == Blocks.air)
            {
                depth = -1;
            }
            else if(b == Blocks.stone)
            {
                depth++;

                if (shouldReplaceStone()) {
                    blocks[(y * 16 + x) * 256 + k] = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, hcStone(world, i, j, x, y, k));
                    metadata[(y * 16 + x) * 256 + k] = EBAPI.ebStonify(EBAPI.RHYOLITE, hcStoneMeta(world, i, j, x, y, k));
                }

                if(riverPaint)
                {
                    if(grass && depth < 4)
                    {
                        blocks[(y * 16 + x) * 256 + k] = blockFiller;
                        metadata[(y * 16 + x) * 256 + k] = byteFiller;
                    }
                    else if(depth == 0)
                    {
                        if (rand.nextInt(2) == 0) {
                            
                            blocks[(y * 16 + x) * 256 + k] = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, hcStone(world, i, j, x, y, k));
                            metadata[(y * 16 + x) * 256 + k] = EBAPI.ebStonify(EBAPI.RHYOLITE, hcStoneMeta(world, i, j, x, y, k));
                        }
                        else {
                            
                            blocks[(y * 16 + x) * 256 + k] = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, hcCobble(world, i, j, x, y, k));
                            metadata[(y * 16 + x) * 256 + k] = EBAPI.ebStonify(EBAPI.RHYOLITE, hcCobbleMeta(world, i, j, x, y, k));
                        }
                    }
                }
                else if(depth > -1 && depth < 9)
                {
                    blocks[(y * 16 + x) * 256 + k] = blockTop;
                    metadata[(y * 16 + x) * 256 + k] = byteTop;
                    
                    if(depth == 0 && k > 61 && k < 254)
                    {
                        SnowHeightCalculator.calc(x, y, k, blocks, metadata, noise);
                    }
                }
            }
            else if(!water && b == Blocks.water)
            {
                blocks[(y * 16 + x) * 256 + k] = Blocks.ice;
                water = true;
            }
        }
    }
}
