package rtg.world.gen.surface.abyssalcraft;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaExtremeHills;
import rtg.util.noise.CellNoise;
import rtg.util.math.CliffCalculator;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceACDarklandsHighland extends SurfaceBase
{
    
    private IBlockState mixBlockTop;
    private byte mixBlockTopMeta;
    private IBlockState mixBlockFill;
    private byte mixBlockFillMeta;
    private float width;
    private float height;
    private float smallW;
    private float smallS;
    
    public SurfaceACDarklandsHighland(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFill, float mixWidth,
        float mixHeight, float smallWidth, float smallStrength)
    {
    
        super(config, top, filler);

        mixBlockTop = this.getConfigBlock(config, BiomeConfigVanillaExtremeHills.surfaceMixBlockId, BiomeConfigVanillaExtremeHills.surfaceMixBlockMetaId, mixTop);

        mixBlockFill = this.getConfigBlock(config, BiomeConfigVanillaExtremeHills.surfaceMixFillerBlockId, BiomeConfigVanillaExtremeHills.surfaceMixFillerBlockMetaId, mixFill);

        width = mixWidth;
        height = mixHeight;
        smallW = smallWidth;
        smallS = smallStrength;
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
            Block b = primer.getBlockState(x, k, y).getBlock();
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

                            primer.setBlockState(x, k, y, hcCobble(world, i, j, x, y, k));
                        }
                        else {

                            primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                        }
                    }
                    else if (depth < 10)
                    {
                        primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                    }
                }
                else
                {
                    if (depth == 0 && k > 61)
                    {
                        if (simplex.noise2(i / width, j / width) + simplex.noise2(i / smallW, j / smallW) * smallS > height)
                        {
                            primer.setBlockState(x, k, y, mixBlockTop);
                            mix = true;
                        }
                        else
                        {
                            primer.setBlockState(x, k, y, topBlock);
                        }
                    }
                    else if (depth < 4)
                    {
                        if (mix)
                        {
                            primer.setBlockState(x, k, y, mixBlockFill);
                        }
                        else
                        {
                            primer.setBlockState(x, k, y, fillerBlock);
                        }
                    }
                }
            }
        }
    }
}
