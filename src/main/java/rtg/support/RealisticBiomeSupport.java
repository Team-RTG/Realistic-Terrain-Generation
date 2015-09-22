package rtg.support;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.support.edit.EditBase;
import rtg.surface.SurfaceBase;
import rtg.terrain.TerrainBase;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.PerlinNoise;

public class RealisticBiomeSupport extends RealisticBiomeBase
{
	public BiomeGenBase customBiome;
	public TerrainBase terrain;
	
	public SurfaceBase[] surfaces;
	public int surfacesLength;
	
	public EditBase[] edits;
	public int editLength;
	
	public RealisticBiomeSupport(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase[] s, EditBase[] e)
	{
		super(b, riverbiome);
		customBiome = b;
		terrain = t;
		
		surfaces = s;
		surfacesLength = s.length;
		
		if(e != null)
		{
			edits = e;
			editLength = e.length;
		}
		else
		{
			editLength = 0;
		}
	}
	
	public RealisticBiomeSupport(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s, EditBase e)
	{
		this(b, riverbiome, t, new SurfaceBase[]{s}, e != null ? new EditBase[]{e} : null);
	}
	
	public RealisticBiomeSupport(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		this(b, riverbiome, t, s, null);
	}

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
    	if(strength > 0.3f)
    	{
        	customBiome.decorate(world, rand, chunkX, chunkY);
    	}

    	for(int e = 0; e < editLength; e++)
    	{
    		edits[e].decorate(world, rand, chunkX, chunkY, perlin, cell, strength, river);
    	}
    }

    @Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
		return terrain.generateNoise(perlin, cell, x, y, ocean, border, river);
    }
    
    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    	for(int s = 0; s < surfacesLength; s++)
    	{
    		surfaces[s].paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, perlin, cell, noise, river, base);
    	}
    }
}
