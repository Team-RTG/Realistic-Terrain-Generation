package rtg.world.biome.realistic;

import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.PerlinNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.realistic.vanilla.*;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBase extends BiomeBase
{
	private static final RealisticBiomeBase[] biomeList = new RealisticBiomeBase[256];
	
	public final BiomeGenBase baseBiome;
	public final BiomeGenBase riverBiome;
	
	public TerrainBase terrain;
	
	public SurfaceBase[] surfaces;
	public int surfacesLength;
		
	public RealisticBiomeBase(BiomeGenBase biome)
	{
		this(biome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE));
	}
	
	public RealisticBiomeBase(BiomeGenBase biome, BiomeGenBase river)
	{
		super(biome.biomeID);
		
		biomeList[biome.biomeID] = this;
		
		baseBiome = biome;
		riverBiome = river;
	}
	
	public static RealisticBiomeBase getBiome(int id)
	{
		return biomeList[id];
	}
	
	
	
	
	
	
	
	
	
	public RealisticBiomeBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase[] s)
	{
		this(b, riverbiome);

		terrain = t;
		
		surfaces = s;
		surfacesLength = s.length;
	}
	
	public RealisticBiomeBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		this(b, riverbiome, t, new SurfaceBase[]{s});
	}
	
	
	
	
	
	//======================================================================================================================================
	
	
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
    	if(strength > 0.3f)
    	{
    		baseBiome.decorate(world, rand, chunkX, chunkY);
    	}
    }
    
    public void generateMapGen(Block[] blocks, byte[] metadata, Long seed, World world, WorldChunkManagerRTG cmr, Random mapRand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float noise[])
    {
        int k = 5;
        mapRand.setSeed(seed);
        long l = (mapRand.nextLong() / 2L) * 2L + 1L;
        long l1 = (mapRand.nextLong() / 2L) * 2L + 1L;
        for(int baseX = chunkX - k; baseX <= chunkX + k; baseX++)
        {
            for(int baseY = chunkY - k; baseY <= chunkY + k; baseY++)
            {
            	mapRand.setSeed((long)baseX * l + (long)baseY * l1 ^ seed);
                rMapGen(blocks, metadata, world, cmr, mapRand, baseX, baseY, chunkX, chunkY, perlin, cell, noise);
            }
        }
    }
    
    public void rMapGen(Block[] blocks, byte[] metadata, World world, WorldChunkManagerRTG cmr, Random mapRand, int chunkX, int chunkY, int baseX, int baseY, PerlinNoise perlin, CellNoise cell, float noise[])
    {
    }
    
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
		return terrain.generateNoise(perlin, cell, x, y, ocean, border, river);
    }
    
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    	for(int s = 0; s < surfacesLength; s++)
    	{
    		surfaces[s].paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, perlin, cell, noise, river, base);
    	}
    }
    
    public float r3Dnoise(float z)
    {
    	return 0f;
    }
}
