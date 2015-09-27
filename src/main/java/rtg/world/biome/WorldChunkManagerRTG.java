package rtg.world.biome;

import gnu.trove.map.hash.TLongObjectHashMap;

import java.util.List;
import java.util.Random;

import rtg.config.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.PerlinNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBase;
import rtg.world.gen.layer.GenLayerRTG;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;

public class WorldChunkManagerRTG extends WorldChunkManager
{
    private PerlinNoise perlin;
    private CellNoise cell;
    private CellNoise biomecell;
    private TLongObjectHashMap<RealisticBiomeBase> biomeDataMap = new TLongObjectHashMap<RealisticBiomeBase>();
    
	public WorldChunkManagerRTG(World world)
	{
		super();
		
	    GenLayer[] agenlayer = GenLayerRTG.initializeAllBiomeGenerators(world.getSeed(), world.getWorldInfo().getTerrainType());
	    agenlayer = getModdedBiomeGenerators(world.getWorldInfo().getTerrainType(), world.getSeed(), agenlayer);
	    ObfuscationReflectionHelper.setPrivateValue(WorldChunkManager.class, this, agenlayer[0], "genBiomes", "field_76944_d");
	    ObfuscationReflectionHelper.setPrivateValue(WorldChunkManager.class, this, agenlayer[1], "biomeIndexLayer", "field_76945_e");
	}
    
    @Override
	public ChunkPosition findBiomePosition(int x, int z, int radius, List biomesToSpawnIn, Random random)
    {
    	int spawnSearchRadius = ConfigRTG.spawnSearchRadius;
    	
    	return super.findBiomePosition(x, z, spawnSearchRadius, biomesToSpawnIn, random);
    }
    
    public float getRiverStrength(int x, int y)
    {
    	return cell.border((x + (perlin.noise1(y / 240f) * 220f)) / 1250D, (y + (perlin.noise1(x / 240f) * 220f)) / 1250D, 50D / 300D, 1f);
    }
    
    public RealisticBiomeBase getBiomeDataAt(int par1, int par2)
    {
    	return RealisticBiomeVanillaBase.vanillaIcePlains;
    }
	
    public RealisticBiomeBase getBiomeDataAt(int par1, int par2, float ocean)
    {	
		return RealisticBiomeVanillaBase.vanillaIcePlains;
    }
    
    public float calculateRiver(int x, int y, float st, float biomeHeight)
    {
    	if(st < 0f && biomeHeight > 59f)
    	{
    		float pX = x + (perlin.noise1(y / 240f) * 220f);
    		float pY = y + (perlin.noise1(x / 240f) * 220f);
    		float r = cell.border(pX / 1250D, pY / 1250D, 50D / 1300D, 1f);
    		return (biomeHeight * (r + 1f)) + ((59f + perlin.noise2(x / 12f, y / 12f) * 2f + perlin.noise2(x / 8f, y / 8f) * 1.5f) * (-r));
    	}
    	else
    	{
    		return biomeHeight;
    	}
    }
    
    public float getOceanValue(int x, int y)
    {
		float base = -(-0f);
		float sample1 = perlin.noise2(x / 1200f, y / 1200f) + base;
		float sample2 = 0f, sa = 0f, highest = 0f;
		
		if(sample1 == 0f)
		{
			highest = 1f;
		}
		
		if(diff(sample1, sample2 = perlin.noise2((x - 100f) / 1200f, y / 1200f) + base, base))
		{
			sa = sample1 * (1 / Math.abs(sample1 - sample2));
			highest = 1f - Math.abs(sa) > highest ? 1f - Math.abs(sa) : highest;
		}
		else if(diff(sample1, sample2 = perlin.noise2((x + 100f) / 1200f, y / 1200f) + base, base))
		{
			sa = sample1 * (1 / Math.abs(sample1 - sample2));
			highest = 1f - Math.abs(sa) > highest ? 1f - Math.abs(sa) : highest;
		}
		
		if(diff(sample1, sample2 = perlin.noise2(x / 1200f, (y + 100f) / 1200f) + base, base))
		{
			sa = sample1 * (1 / Math.abs(sample1 - sample2));
			highest = 1f - Math.abs(sa) > highest ? 1f - Math.abs(sa) : highest;
		}
		else if(diff(sample1, sample2 = perlin.noise2(x / 1200f, (y - 100f) / 1200f) + base, base))
		{
			sa = sample1 * (1 / Math.abs(sample1 - sample2));
			highest = 1f - Math.abs(sa) > highest ? 1f - Math.abs(sa) : highest;
		}
		
		if(sample1 > 0f)
		{
			highest = 2f - highest;
		}
		
    	return highest;
    }
    
	public boolean diff(float sample1, float sample2, float base)
	{
		if((sample1 < base && sample2 > base) || (sample1 > base && sample2 < base))
		{
			return true;
		}
		return false;
	}
}
