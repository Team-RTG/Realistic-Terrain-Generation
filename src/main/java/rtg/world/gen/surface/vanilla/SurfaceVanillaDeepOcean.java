package rtg.world.gen.surface.vanilla;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaDeepOcean;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceVanillaDeepOcean extends SurfaceBase
{
    
    private Block mixBlock;
    private byte mixBlockMeta;
    private float width;
    private float height;
    private float mixCheck;
    
    public SurfaceVanillaDeepOcean(BiomeConfig config, Block top, Block filler, Block mix, float mixWidth, float mixHeight)
    {
    
        super(config, top, (byte)0, filler, (byte)0);
        
        mixBlock = this.getConfigBlock(config, BiomeConfigVanillaDeepOcean.surfaceMixBlockId, mix);
        mixBlockMeta = this.getConfigBlockMeta(config, BiomeConfigVanillaDeepOcean.surfaceMixBlockMetaId, (byte)0);
        
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
                    
                    if (mixCheck > height) {
                        blocks[(y * 16 + x) * 256 + k] = mixBlock;
                        metadata[(y * 16 + x) * 256 + k] = mixBlockMeta;
                    }
                    else
                    {
                        blocks[(y * 16 + x) * 256 + k] = topBlock;
                        metadata[(y * 16 + x) * 256 + k] = topBlockMeta;
                    }
                }
                else if (depth < 4 && k < 63)
                {
                    blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                    metadata[(y * 16 + x) * 256 + k] = fillerBlockMeta;
                }
            }
        }
    }
}
