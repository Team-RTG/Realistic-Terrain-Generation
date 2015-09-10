package rtg.coast.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.deco.DecoBlob;
import rtg.deco.DecoFlowers;
import rtg.deco.DecoGrass;
import rtg.deco.DecoLog;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.PerlinNoise;

public class CoastVanillaExtremeHills extends RealisticBiomeBase
{
	public CoastVanillaExtremeHills() 
	{
		super(0, RTGBiomes.baseColdPlains);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {

    }

	@Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
		river = river > 0.5f ? 1f : river * 2f;
		
		float h = ocean < 0.5f ? ocean * 14f : 7f;
		
		if(ocean < 1.9f)
		{
			float st = (1.9f - ocean) * 20f;
			st = st > 1f ? 1f : st;
			
			h += perlin.noise2(x / 12f, y / 12f) * 1.5f;
			h += perlin.noise2(x / 20f, y / 20f) * 3f;
		}
		
		return 55f + h;
    }
    
    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    	
    }
}
