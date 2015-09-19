package rtg.biomes.realistic.ocean;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.RTGBiomes;
import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.vanilla.VanillaBiomes.Climate;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.SurfaceBase;
import rtg.surface.SurfaceIslandMountainStone;
import rtg.util.CellNoise;
import rtg.util.PerlinNoise;

public class RealisticBiomeIslandTundra extends RealisticBiomeBase
{
	private SurfaceBase surface;
	
	public RealisticBiomeIslandTundra() 
	{
		super(0, VanillaBiomes.climatizedBiome(BiomeGenBase.ocean, Climate.COLD));
		
		surface = new SurfaceIslandMountainStone(Blocks.grass, Blocks.dirt, 67, Blocks.sand, 0f);
	}

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
    	
    }

    @Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
    	float h = 0f;
    	
    	if(border > 0.9f)
    	{
    		h = (border - 0.9f) * (150f + perlin.noise2(x / 45f, y / 45f) * 50f);
    	}
    	
    	return 66f + h;
    }
    
    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    	surface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, perlin, cell, noise, river, base);
    }
}
