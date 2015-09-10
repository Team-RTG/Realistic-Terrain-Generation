package rtg.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gnu.trove.map.hash.TLongIntHashMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import net.minecraft.world.ChunkCoordIntPair;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.biomes.realistic.desert.RealisticBiomeDesert;
import rtg.biomes.realistic.desert.RealisticBiomeDesertMountains;
import rtg.biomes.realistic.desert.RealisticBiomeDuneValley;
import rtg.biomes.realistic.desert.RealisticBiomeOasis;
import rtg.biomes.realistic.forest.RealisticBiomeDarkRedwood;
import rtg.biomes.realistic.forest.RealisticBiomeDarkRedwoodPlains;
import rtg.biomes.realistic.forest.RealisticBiomeWoodHills;
import rtg.biomes.realistic.forest.RealisticBiomeWoodMountains;
import rtg.biomes.realistic.land.*;
import rtg.biomes.realistic.red.RealisticBiomeCanyon;
import rtg.biomes.realistic.red.RealisticBiomeMesa;
import rtg.biomes.realistic.red.RealisticBiomeRedDesertMountains;
import rtg.biomes.realistic.red.RealisticBiomeRedOasis;
import rtg.biomes.realistic.savanna.RealisticBiomeCanyonForest;
import rtg.biomes.realistic.savanna.RealisticBiomeDuneValleyForest;
import rtg.biomes.realistic.savanna.RealisticBiomeHotForest;
import rtg.biomes.realistic.savanna.RealisticBiomeMesaPlains;
import rtg.biomes.realistic.savanna.RealisticBiomeSavanna;
import rtg.biomes.realistic.savanna.RealisticBiomeSavannaDunes;
import rtg.biomes.realistic.savanna.RealisticBiomeSavannaForest;
import rtg.biomes.realistic.savanna.RealisticBiomeStoneMountains;
import rtg.biomes.realistic.savanna.RealisticBiomeStoneMountainsCactus;
import rtg.biomes.realistic.vanilla.RealisticBiomeVanillaStoneBeach;
import rtg.support.Support;
import rtg.util.CellNoise;
import rtg.util.PerlinNoise;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.IntCache;

public class ChunkManagerRealistic extends WorldChunkManager
{
    private BiomeCache biomeCache;
    private List biomesToSpawnIn;

    private PerlinNoise perlin;
    private CellNoise cell;
    
    private CellNoise biomecell;
    
    private ArrayList<RealisticBiomeBase> biomes_snow;
    private ArrayList<RealisticBiomeBase> biomes_cold;
    private ArrayList<RealisticBiomeBase> biomes_hot;
    private ArrayList<RealisticBiomeBase> biomes_wet;
    private ArrayList<RealisticBiomeBase> biomes_small;
    private int biomes_snowLength;
    private int biomes_coldLength;
    private int biomes_hotLength;
    private int biomes_wetLength;
    private int biomes_smallLength;
    
    private boolean wetEnabled;
    private boolean smallEnabled;

    private float[] borderNoise;
	
	protected ChunkManagerRealistic()
	{
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = new ArrayList();
    	borderNoise = new float[256];
	}

    public ChunkManagerRealistic(World par1World)
    {
        this();
        long seed = par1World.getSeed();
        
    	perlin = new PerlinNoise(seed);
    	cell = new CellNoise(seed, (short)0);
    	cell.setUseDistance(true);
    	biomecell = new CellNoise(seed, (short)0);
		
    	biomes_snow = new ArrayList<RealisticBiomeBase>();
    	biomes_cold = new ArrayList<RealisticBiomeBase>();
    	biomes_hot = new ArrayList<RealisticBiomeBase>();
    	biomes_wet = new ArrayList<RealisticBiomeBase>();
    	biomes_small = new ArrayList<RealisticBiomeBase>();

    	biomes_snow.add(RealisticBiomeBase.polar);
    	biomes_snow.add(RealisticBiomeBase.snowHills);
    	biomes_snow.add(RealisticBiomeBase.snowRivers);
    	biomes_snow.add(RealisticBiomeBase.snowLakes);
    	biomes_snow.add(RealisticBiomeBase.redwoodSnow);
    	
    	biomes_cold.add(RealisticBiomeBase.tundraHills);
    	biomes_cold.add(RealisticBiomeBase.tundraPlains);
    	biomes_cold.add(RealisticBiomeBase.taigaHills);
    	biomes_cold.add(RealisticBiomeBase.taigaPlains);
    	biomes_cold.add(RealisticBiomeBase.redwood);
    	biomes_cold.add(RealisticBiomeBase.darkRedwood);
    	biomes_cold.add(RealisticBiomeBase.darkRedwoodPlains);
    	biomes_cold.add(RealisticBiomeBase.woodhills);
    	biomes_cold.add(RealisticBiomeBase.woodmountains);
    	
    	biomes_hot.add(RealisticBiomeBase.duneValleyForest);
    	biomes_hot.add(RealisticBiomeBase.savanna);
    	biomes_hot.add(RealisticBiomeBase.savannaForest);
    	biomes_hot.add(RealisticBiomeBase.savannaDunes);
    	biomes_hot.add(RealisticBiomeBase.stoneMountains);
    	biomes_hot.add(RealisticBiomeBase.stoneMountainsCactus);
    	biomes_hot.add(RealisticBiomeBase.hotForest);
    	biomes_hot.add(RealisticBiomeBase.hotRedwood);
    	biomes_hot.add(RealisticBiomeBase.canyonForest);
    	biomes_hot.add(RealisticBiomeBase.mesaPlains);
    	biomes_hot.add(RealisticBiomeBase.desert);
    	biomes_hot.add(RealisticBiomeBase.desertMountains);
    	biomes_hot.add(RealisticBiomeBase.duneValley);
    	biomes_hot.add(RealisticBiomeBase.oasis);
    	biomes_hot.add(RealisticBiomeBase.redDesertMountains);
    	biomes_hot.add(RealisticBiomeBase.redDesertOasis);
    	biomes_hot.add(RealisticBiomeBase.canyon);
    	biomes_hot.add(RealisticBiomeBase.mesa);
    	
    	biomes_snow.addAll(Support.biomes_snow);
    	biomes_cold.addAll(Support.biomes_cold);
    	biomes_hot.addAll(Support.biomes_hot);
    	biomes_wet.addAll(Support.biomes_wet);
    	biomes_small.addAll(Support.biomes_small);
    	
    	biomes_snowLength = biomes_snow.size();
    	biomes_coldLength = biomes_cold.size();
    	biomes_hotLength = biomes_hot.size();
    	biomes_wetLength = biomes_wet.size();
    	biomes_smallLength = biomes_small.size();
    	
    	wetEnabled = false;
    	if(biomes_wetLength > 0)
    	{
    		wetEnabled = true;
    	}
    	
    	smallEnabled = false;
    	if(biomes_smallLength > 1)
    	{
    		smallEnabled = true;
    	}
    }    
	
    public int[] getBiomesGens(int par1, int par2, int par3, int par4)
    {	
    	int[] d = new int[par3 * par4];
    	
		for(int i = 0; i < par3; i++)
		{
			for(int j = 0; j < par4; j++)
    		{
    			d[j * par3 + i] = getBiomeGenAt(par1 + i, par2 + j).biomeID;
    		}
		}
    	return d;
    }
    
    public RealisticBiomeBase[] getBiomesGensData(int par1, int par2, int par3, int par4)
    {	
    	RealisticBiomeBase[] data = new RealisticBiomeBase[par3 * par4];
    	
		for(int i = 0; i < par3; i++)
		{
			for(int j = 0; j < par4; j++)
    		{
    			data[j * par3 + i] = getBiomeDataAt(par1 + i, par2 + j);
    		}
		}
    	return data;
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

    public BiomeGenBase getBiomeGenAt(int par1, int par2)
    {
    	return getBiomeDataAt(par1, par2, getOceanValue(par1, par2)).baseBiome;
    }
    
    public RealisticBiomeBase getBiomeDataAt(int par1, int par2)
    {
    	return getBiomeDataAt(par1, par2, getOceanValue(par1, par2));
    }

	private TLongObjectHashMap<RealisticBiomeBase> biomeDataMap = new TLongObjectHashMap<RealisticBiomeBase>();

    public RealisticBiomeBase getBiomeDataAt(int par1, int par2, float ocean)
    {
    	//return RealisticBiomeBase.vanillaPlains;
    	
    	/*
    	if(par1 + par2 < 0)
    	{
    		return RealisticBiomeBase.islandTropicalVolcano;
    	}
    	else
    	{
    		return RealisticBiomeBase.ocean;
    	}
    	*/
    	
    	/*
    	if(ocean >= 1.99f)
    	{
        	return RealisticBiomeBase.hotForest;
    	}
    	else if(ocean <= 0.01f)
    	{
        	return RealisticBiomeBase.ocean;
    	}
    	else
    	{
        	return RealisticBiomeBase.coastDunes;
    	}*/
    	
    	/*
    	if(par1 + par2 > 0f)
    	{
        	return RealisticBiomeBase.savannaDunes;
    	}
    	else
    	{
        	return RealisticBiomeBase.mesa;
    	}
    	*/

		long coords = ChunkCoordIntPair.chunkXZ2Int(par1, par2);

		if (biomeDataMap.containsKey(coords)) {
			return biomeDataMap.get(coords);
		}

		RealisticBiomeBase output = null;
    	
    	float b = (biomecell.noise((par1 + 4000f) / 1200D, par2 / 1200D, 1D) * 0.5f) + 0.5f;
    	b = b < 0f ? 0f : b >= 0.9999999f ? 0.9999999f : b;

    	float s = smallEnabled ? (biomecell.noise(par1 / 140D, par2 / 140D, 1D) * 0.5f) + 0.5f : 0f;
    	if(smallEnabled && s > 0.975f)
    	{
        	float h = (s - 0.975f) * 40f;
        	h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
        	h *= biomes_smallLength;
        	output = biomes_small.get((int)(h));
    	}
    	else if((wetEnabled && b < 0.25f) || (!wetEnabled && b < 0.33f))
    	{
        	float h = (biomecell.noise(par1 / 450D, par2 / 450D, 1D) * 0.5f) + 0.5f;
        	h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
        	
        	h *= biomes_snowLength;
			output = biomes_snow.get((int)(h));
    	}
    	else if((wetEnabled && b < 0.50f) || (!wetEnabled && b < 0.66f))
    	{
        	float h = (biomecell.noise(par1 / 450D, par2 / 450D, 1D) * 0.5f) + 0.5f;
        	h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
        	
        	h *= biomes_coldLength;
			output = biomes_cold.get((int)(h));
    	}
    	else if((wetEnabled && b < 0.75f) || (!wetEnabled && b < 1f))
    	{
        	float h = (biomecell.noise(par1 / 450D, par2 / 450D, 1D) * 0.5f) + 0.5f;
        	h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
        	
        	h *= biomes_hotLength;
        	output = biomes_hot.get((int)(h));
    	}
    	else if(wetEnabled)
    	{
        	float h = (biomecell.noise(par1 / 450D, par2 / 450D, 1D) * 0.5f) + 0.5f;
        	h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
        	
        	h *= biomes_wetLength;
			output = biomes_wet.get((int)(h));
    	}
    	else
    	{
        	float h = (biomecell.noise(par1 / 450D, par2 / 450D, 1D) * 0.5f) + 0.5f;
        	h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
        	
        	h *= biomes_hotLength;
			output = biomes_hot.get((int)(h));
    	}

    	/*###########################################################################################
    	 * @todo Add a config option for single-biome worlds. - Pink
    	 ###########################################################################################*/
    	
    	//output = RealisticBiomeBase.vanillaForest;
    	
		if (biomeDataMap.size() > 4096) {
			biomeDataMap.clear();
		}

		biomeDataMap.put(coords, output);
		return output;

    	/*if(par1 + par2 < 0)
    	{
    		return RealisticBiomeBase.landTaigaFields;
		}
		else
		{
			return RealisticBiomeBase.landTaigaHills;
		}*/
    	
    	/*
    	float h = (biomecell.noise(par1 / 450D, par2 / 450D, 1D) * 0.5f) + 0.5f;
    	h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;

    	float temp = 0.5f + (perlin.noise2((par1 + 2000f) / 2000f, par2 / 2000f) * 1.1f);
    	float hum = 0.5f + (perlin.noise2((par1 - 2000f) / 2000f, par2 / 2000f) * 1.1f);
    	
    	temp = temp > 1f ? 1f : temp < 0f ? 0f : temp;
    	hum = hum > 1f ? 1f : hum < 0f ? 0f : hum;
    	
    	if((1f - temp) + hum > 1f)
    	{
    		hum -= temp;
    		temp += hum;
    	}
    	
    	if(temp < 0.15f)
    	{
    		h *= 2f;
    		return biomes_polar[(int)(h)];
    	}
    	else if(hum < 0.2f)
    	{
    		h *= 8f;
    		return biomes_tundra[(int)(h)];
    	}
    	else if(temp < 0.5f)
    	{
    		h *= 5f;
    		return biomes_snow[(int)(h)];
    	}
    	else if(temp > 0.85f && hum > 0.85f)
    	{
    		return RealisticBiomeBase.landRedwoodSpikes;
    	}
    	else
    	{
    		h *= 9f;
    		return biomes_taiga[(int)(h)];
    	}
    	*/
    	
    	//int x = (int)(temp * 7f);
    	//int y = (int)(hum * 7f);
    	
    	//x = x < 0 ? 0 : x > 6 ? 6 : x;
    	//y = y < 0 ? 0 : y > 6 ? 6 : y;
    	
    	/*if(par1 % 100 == 0 && par2 % 100 == 0)
    	{
        	System.out.println(par1 + " " + par2 + " " + x + " " + y + " - " + temp + " " + hum);
    	}*/
    	
    	//return biomes[x * 7 + y];
    	
    	/*ocean = ocean > 1f ? 1f : ocean < 0f ? 0f : ocean;
    	
    	if(ocean < 0.45f)
    	{
    		return biomeLayerOcean.getBiome(temp, hum);
    	}
    	else if (ocean > 0.55f)
    	{
    		return biomeLayerLand.getBiome(temp, hum);
    	}
    	else
    	{
    		return biomeLayerCoast.getBiome(temp, hum);
    	}*/
    }
    
    public float getNoiseAt(int x, int y)
    {
    	float river = getRiverStrength(x, y) + 1f;
    	if(river < 0.5f)
    	{
    		return 59f;
    	}
    	
    	float ocean = getOceanValue(x, y);
    	return getBiomeDataAt(x, y, ocean).rNoise(perlin, cell, x, y, ocean, 1f, river);
    }
    
    public float getNoiseWithRiverOceanAt(int x, int y, float river, float ocean)
    {
    	return getBiomeDataAt(x, y, ocean).rNoise(perlin, cell, x, y, ocean, 1f, river);
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
    
    public float getRiverStrength(int x, int y)
    {
    	return cell.border((x + (perlin.noise1(y / 240f) * 220f)) / 1250D, (y + (perlin.noise1(x / 240f) * 220f)) / 1250D, 50D / 300D, 1f);
    }
    
    public boolean isBorderlessAt(int x, int y)
    {
    	int bx, by;
    	
        for(bx = -2; bx <= 2; bx++)
        {
        	for(by = -2; by <= 2; by++)
        	{
        		borderNoise[getBiomeDataAt(x + bx * 16, y + by * 16).biomeID] += 0.04f;
        	}
        }
        
        by = 0;
        for(bx = 0; bx < 256; bx++)
        {
        	if(borderNoise[bx] > 0.98f)
        	{
        		by = 1;
        	}
        	borderNoise[bx] = 0;
        }
        
    	return by == 1 ? true : false;
    }
    
    public List getBiomesToSpawnIn()
    {
        return this.biomesToSpawnIn;
    }

    public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
    {
        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
        {
            par1ArrayOfFloat = new float[par4 * par5];
        }

		int var6[] = getBiomesGens(par2, par3, par4, par5);

        for (int var7 = 0; var7 < par4 * par5; ++var7)
        {
            float var8 = (float)BiomeGenBase.getBiome(var6[var7]).getIntRainfall() / 65536.0F;

            if (var8 > 1.0F)
            {
                var8 = 1.0F;
            }

            par1ArrayOfFloat[var7] = var8;
        }

        return par1ArrayOfFloat;
    }

    public float getTemperatureAtHeight(float par1, int par2)
    {
        return par1;
    }

    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }
    	
		int var7[] = getBiomesGens(par2, par3, par4, par5);

        for (int var8 = 0; var8 < par4 * par5; ++var8)
        {
            par1ArrayOfBiomeGenBase[var8] = BiomeGenBase.getBiome(var7[var8]);
        }

        return par1ArrayOfBiomeGenBase;
    }

    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }

    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6)
    {
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }
    	
		int var7[] = getBiomesGens(par2, par3, par4, par5);

        for (int var8 = 0; var8 < par4 * par5; ++var8)
        {
            par1ArrayOfBiomeGenBase[var8] = BiomeGenBase.getBiome(var7[var8]);
        }

        return par1ArrayOfBiomeGenBase;
    }

    public boolean areBiomesViable(int x, int y, int par3, List par4List)
    {
    	float centerNoise = getNoiseAt(x,y);
    	if(centerNoise < 62)
    	{
    		return false;
    	}
    	
    	float lowestNoise = centerNoise;
    	float highestNoise = centerNoise;
    	for(int i = -2; i <= 2; i++)
    	{
    		for(int j = -2; j <= 2; j++)
    		{
    			if(i != 0 && j != 0)
    			{
    				float n = getNoiseAt(x + i * 16, y + j * 16);
    				if(n < lowestNoise) { lowestNoise = n; }
    				if(n > highestNoise) { highestNoise = n; }
    			}
    		}
    	}
    	
    	if(highestNoise - lowestNoise < 22)
    	{
    		return true;
    	}
    	
    	return false;
    }

    public ChunkPosition findBiomePosition(int p_150795_1_, int p_150795_2_, int p_150795_3_, List p_150795_4_, Random p_150795_5_)
    {
    	return null;
    }

    public void cleanupCache()
    {
        this.biomeCache.cleanupCache();
    }
}
