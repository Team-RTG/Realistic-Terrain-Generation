package rtg.world.gen.surface.vanilla;

import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceVanillaTaigaM extends SurfaceBase
{
    
    public SurfaceVanillaTaigaM(Block top, Block fill)
    {
    
        super(top, fill);
    }
    
    @Override
    public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand,
        OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    
        float p = simplex.noise2(i / 8f, j / 8f) * 0.5f;
        float c = CliffCalculator.calc(x, y, noise);
        int cliff = 0;
        boolean gravel = false;
        
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
                
                if (depth == 0)
                {
                    if (k < 63)
                    {
                        gravel = true;
                    }
                    
                    if (c > 0.45f && c > 1.5f - ((k - 60f) / 65f) + p)
                    {
                        cliff = 1;
                    }
                    if (c > 1.5f)
                    {
                        cliff = 2;
                    }
                    if (k > 110 + (p * 4) && c < 0.3f + ((k - 100f) / 50f) + p)
                    {
                        cliff = 3;
                    }
                    
                    if (cliff == 1)
                    {
                        blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone;
                    }
                    else if (cliff == 2)
                    {
                        blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
                        metadata[(y * 16 + x) * 256 + k] = 9;
                    }
                    else if (cliff == 3)
                    {
                        blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
                    }
                    else if (simplex.noise2(i / 50f, j / 50f) + p * 0.6f > 0.24f)
                    {
                        blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
                        metadata[(y * 16 + x) * 256 + k] = 2;
                    }
                    else if (k < 63)
                    {
                        blocks[(y * 16 + x) * 256 + k] = Blocks.gravel;
                        gravel = true;
                    }
                    else
                    {
                        blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
                    }
                }
                else if (depth < 6)
                {
                    if (cliff == 1)
                    {
                        blocks[(y * 16 + x) * 256 + k] = Blocks.stone;
                    }
                    else if (cliff == 2)
                    {
                        blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
                        metadata[(y * 16 + x) * 256 + k] = 9;
                    }
                    else if (cliff == 3)
                    {
                        blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
                    }
                    else if (gravel)
                    {
                        blocks[(y * 16 + x) * 256 + k] = Blocks.gravel;
                    }
                    else
                    {
                        blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
                    }
                }
            }
        }
    }
}
