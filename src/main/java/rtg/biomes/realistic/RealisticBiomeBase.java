package rtg.biomes.realistic;

import java.util.Random;

import rtg.api.RTGBiomes;
import rtg.biomes.realistic.land.*;
import rtg.biomes.realistic.coast.*;
import rtg.biomes.realistic.desert.RealisticBiomeDesert;
import rtg.biomes.realistic.desert.RealisticBiomeDesertMountains;
import rtg.biomes.realistic.desert.RealisticBiomeDuneValley;
import rtg.biomes.realistic.desert.RealisticBiomeOasis;
import rtg.biomes.realistic.forest.RealisticBiomeDarkRedwood;
import rtg.biomes.realistic.forest.RealisticBiomeDarkRedwoodPlains;
import rtg.biomes.realistic.forest.RealisticBiomeWoodHills;
import rtg.biomes.realistic.forest.RealisticBiomeWoodMountains;
import rtg.biomes.realistic.ocean.*;
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
import rtg.biomes.realistic.vanilla.*;
import rtg.surface.SurfaceBase;
import rtg.terrain.TerrainBase;
import rtg.util.CellNoise;
import rtg.util.PerlinNoise;
import rtg.world.ChunkManagerRealistic;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBase
{
	private static final RealisticBiomeBase[] biomeList = new RealisticBiomeBase[256];
	private static int nextBiomeID = 0;
	
	public static RealisticBiomeBase test = new RealisticBiomeTest();
	public static RealisticBiomeBase river = new RealisticBiomeTestRiver();
	
	public static RealisticBiomeBase ocean = new RealisticBiomeOceanTest();
	public static RealisticBiomeBase coast = new RealisticBiomeCoastTest();
	
	//POLAR =========================================================================================
	public static RealisticBiomeBase polar = new RealisticBiomePolar();
	
	//SNOW ==========================================================================================
	public static RealisticBiomeBase snowHills = new RealisticBiomeSnowHills();
	public static RealisticBiomeBase snowRivers = new RealisticBiomeSnowRivers();
	public static RealisticBiomeBase snowLakes = new RealisticBiomeSnowLakes();	
	public static RealisticBiomeBase redwoodSnow = new RealisticBiomeRedwoodSnow();
	
	//PINE ===========================================================================================
	public static RealisticBiomeBase tundraHills = new RealisticBiomeTundraHills();
	public static RealisticBiomeBase tundraPlains = new RealisticBiomeTundraPlains();	
	public static RealisticBiomeBase taigaHills = new RealisticBiomeTaigaHills();
	public static RealisticBiomeBase taigaPlains = new RealisticBiomeTaigaPlains();
	
	//FOREST =========================================================================================
	public static RealisticBiomeBase redwood = new RealisticBiomeRedwood();
	public static RealisticBiomeBase darkRedwood = new RealisticBiomeDarkRedwood();
	public static RealisticBiomeBase darkRedwoodPlains = new RealisticBiomeDarkRedwoodPlains();
	public static RealisticBiomeBase woodhills = new RealisticBiomeWoodHills();
	public static RealisticBiomeBase woodmountains = new RealisticBiomeWoodMountains();
	
	//SAVANNA ========================================================================================
	public static RealisticBiomeBase duneValleyForest = new RealisticBiomeDuneValleyForest();	
	public static RealisticBiomeBase savanna = new RealisticBiomeSavanna();
	public static RealisticBiomeBase savannaForest = new RealisticBiomeSavannaForest();
	public static RealisticBiomeBase savannaDunes = new RealisticBiomeSavannaDunes();
	public static RealisticBiomeBase stoneMountains = new RealisticBiomeStoneMountains();
	public static RealisticBiomeBase stoneMountainsCactus = new RealisticBiomeStoneMountainsCactus();
	public static RealisticBiomeBase hotForest = new RealisticBiomeHotForest();
	public static RealisticBiomeBase hotRedwood = new RealisticBiomeHotRedwood();
	public static RealisticBiomeBase canyonForest = new RealisticBiomeCanyonForest();
	public static RealisticBiomeBase mesaPlains = new RealisticBiomeMesaPlains();
	
	//DESERT =========================================================================================
	public static RealisticBiomeBase desert = new RealisticBiomeDesert();
	public static RealisticBiomeBase desertMountains = new RealisticBiomeDesertMountains();
	public static RealisticBiomeBase duneValley = new RealisticBiomeDuneValley();	
	public static RealisticBiomeBase oasis = new RealisticBiomeOasis();
	
	//RED ============================================================================================
	public static RealisticBiomeBase redDesertMountains = new RealisticBiomeRedDesertMountains();
	public static RealisticBiomeBase redDesertOasis = new RealisticBiomeRedOasis();
	public static RealisticBiomeBase canyon = new RealisticBiomeCanyon();
	public static RealisticBiomeBase mesa = new RealisticBiomeMesa();
	
	//SWAMP ==========================================================================================
	
	//TROPICAL =======================================================================================
	
	//JUNGLE =========================================================================================
	public static RealisticBiomeBase rainForestHigh = new RealisticBiomeHighRainforest();
	public static RealisticBiomeBase jungleHills = new RealisticBiomeJungleHills();
	public static RealisticBiomeBase jungleCanyon = new RealisticBiomeJungleCanyon();
	public static RealisticBiomeBase redwoodJungle = new RealisticBiomeRedwoodJungle(); 
	
	//COAST =========================================================================================
	public static RealisticBiomeBase coastIce = new RealisticBiomeCoastIce();
	
	public static RealisticBiomeBase coastColdSlope = new RealisticBiomeCoastColdSlope();
	public static RealisticBiomeBase coastColdCliff = new RealisticBiomeCoastColdCliff();
	public static RealisticBiomeBase coastDunes = new RealisticBiomeCoastDunes();
	
	public static RealisticBiomeBase coastMangrove = new RealisticBiomeCoastMangrove();
	public static RealisticBiomeBase coastOasis = new RealisticBiomeCoastOasis();
	
	//OCEAN =========================================================================================
	public static RealisticBiomeBase islandTropical = new RealisticBiomeIslandTropical();
	
	
	//VANILLA =======================================================================================
	public static RealisticBiomeBase vanillaBeach = new RealisticBiomeVanillaBeach();
	public static RealisticBiomeBase vanillaColdBeach = new RealisticBiomeVanillaColdBeach();
	public static RealisticBiomeBase vanillaDesert = new RealisticBiomeVanillaDesert();
	public static RealisticBiomeBase vanillaDesertHills = new RealisticBiomeVanillaDesertHills();
	public static RealisticBiomeBase vanillaExtremeHills = new RealisticBiomeVanillaExtremeHills();
	public static RealisticBiomeBase vanillaExtremeHillsPlus = new RealisticBiomeVanillaExtremeHillsPlus();
	public static RealisticBiomeBase vanillaPlains = new RealisticBiomeVanillaPlains();
	public static RealisticBiomeBase vanillaStoneBeach = new RealisticBiomeVanillaStoneBeach();
	
	// ==============================================================================================
	
	public final int biomeID;
	public final int subID;
	public final BiomeGenBase baseBiome;
	public final RealisticBiomeBase beachBiome;
	public final BiomeGenBase riverBiome;
	
	public RealisticBiomeBase(int sub, BiomeGenBase biome)
	{
		this(sub, biome, coastIce, RTGBiomes.baseRiverTemperate);
	}
	
	public RealisticBiomeBase(int sub, BiomeGenBase biome, RealisticBiomeBase coast, BiomeGenBase river)
	{
		biomeID = nextBiomeID;
		biomeList[biomeID] = this;
		nextBiomeID++;
		
		subID = sub;
		baseBiome = biome;
		beachBiome = coast;
		riverBiome = river;
	}
	
	public static RealisticBiomeBase getBiome(int id)
	{
		return biomeList[id];
	}
	
	
	//======================================================================================================================================
	
	
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
    }
    
    public void generateMapGen(Block[] blocks, byte[] metadata, Long seed, World world, ChunkManagerRealistic cmr, Random mapRand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float noise[])
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
    
    public void rMapGen(Block[] blocks, byte[] metadata, World world, ChunkManagerRealistic cmr, Random mapRand, int chunkX, int chunkY, int baseX, int baseY, PerlinNoise perlin, CellNoise cell, float noise[])
    {
    }
    
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
    	return 63f;
    }
    
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
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

        		if(depth == 0)
        		{
    				if(k < 62)
    				{
    					blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
    				}
    				else
    				{
    					blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
    				}
        		}
        		else if(depth < 6)
        		{
        			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
        		}
            }
		}
    }
    
    public float r3Dnoise(float z)
    {
    	return 0f;
    }
}
