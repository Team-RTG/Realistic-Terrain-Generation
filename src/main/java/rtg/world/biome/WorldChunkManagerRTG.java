package rtg.world.biome;

import rtg.world.gen.layer.GenLayerEB;
import rtg.util.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import static net.minecraft.world.biome.BiomeGenBase.*;

public class WorldChunkManagerRTG extends WorldChunkManager
{
	
    private PerlinNoise perlin;
    private CellNoise cell;
    
    private CellNoise biomecell;
    
    
	private GenLayer genBiomes;
	/**
	 * A GenLayer containing the indices into BiomeGenBase.biomeList[]
	 */
	private GenLayer biomeIndexLayer;
	/**
	 * The BiomeCache object for this world.
	 */
	private BiomeCache biomeCache;
	/**
	 * A list of biomes that the player can spawn in.
	 */
	private List biomesToSpawnIn;

	protected WorldChunkManagerRTG() {
		this.biomeCache = new BiomeCache(this);
		this.biomesToSpawnIn = new ArrayList();
	}

	public WorldChunkManagerRTG(long par1, WorldType par3WorldType) {
		this();
		GenLayer[] agenlayer = GenLayerEB.initializeAllBiomeGenerators(par1, par3WorldType);
		agenlayer = getModdedBiomeGenerators(par3WorldType, par1, agenlayer);
		this.genBiomes = agenlayer[0];
		this.biomeIndexLayer = agenlayer[1];
	}

	public WorldChunkManagerRTG(World par1World) {
		this(par1World.getSeed(), par1World.getWorldInfo().getTerrainType());
	}

	/**
	 * Gets the list of valid biomes for the player to spawn in.
	 */
	public List getBiomesToSpawnIn() {
		return this.biomesToSpawnIn;
	}

	/**
	 * Returns the BiomeGenBase related to the x, z position on the world.
	 */
	public BiomeGenBase getBiomeGenAt(int par1, int par2) {
		return this.biomeCache.getBiomeGenAt(par1, par2);
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
	
	/**
	 * Returns a list of rainfall values for the specified blocks. Args: listToReuse, x, z, width, length.
	 */
	public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
		IntCache.resetIntCache();

		if(par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5) {
			par1ArrayOfFloat = new float[par4 * par5];
		}

		int[] aint = this.biomeIndexLayer.getInts(par2, par3, par4, par5);

		for(int i1 = 0; i1 < par4 * par5; ++i1) {
			try {
				float f = (float) BiomeGenBase.getBiome(aint[i1]).getIntRainfall() / 65536.0F;

				if(f > 1.0F) {
					f = 1.0F;
				}

				par1ArrayOfFloat[i1] = f;
			} catch(Throwable throwable) {
				CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
				CrashReportCategory crashreportcategory = crashreport.makeCategory("DownfallBlock");
				crashreportcategory.addCrashSection("biome id", Integer.valueOf(i1));
				crashreportcategory.addCrashSection("downfalls[] size", Integer.valueOf(par1ArrayOfFloat.length));
				crashreportcategory.addCrashSection("x", Integer.valueOf(par2));
				crashreportcategory.addCrashSection("z", Integer.valueOf(par3));
				crashreportcategory.addCrashSection("w", Integer.valueOf(par4));
				crashreportcategory.addCrashSection("h", Integer.valueOf(par5));
				throw new ReportedException(crashreport);
			}
		}

		return par1ArrayOfFloat;
	}

	/**
	 * Return an adjusted version of a given temperature based on the y height
	 */
	@SideOnly(Side.CLIENT)
	public float getTemperatureAtHeight(float par1, int par2) {
		return par1;
	}

	/**
	 * Returns an array of biomes for the location input.
	 */
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
		IntCache.resetIntCache();

		if(par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5) {
			par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
		}

		int[] aint = this.genBiomes.getInts(par2, par3, par4, par5);

		try {
			for(int i1 = 0; i1 < par4 * par5; ++i1) {
				par1ArrayOfBiomeGenBase[i1] = BiomeGenBase.getBiome(aint[i1]);
			}

			return par1ArrayOfBiomeGenBase;
		} catch(Throwable throwable) {
			CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
			CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
			crashreportcategory.addCrashSection("biomes[] size", Integer.valueOf(par1ArrayOfBiomeGenBase.length));
			crashreportcategory.addCrashSection("x", Integer.valueOf(par2));
			crashreportcategory.addCrashSection("z", Integer.valueOf(par3));
			crashreportcategory.addCrashSection("w", Integer.valueOf(par4));
			crashreportcategory.addCrashSection("h", Integer.valueOf(par5));
			throw new ReportedException(crashreport);
		}
	}

	/**
	 * Returns biomes to use for the blocks and loads the other data like temperature and humidity onto the WorldChunkManager Args: oldBiomeList, x, z, width, depth
	 */
	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
		return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
	}

	/**
	 * Return a list of biomes for the specified blocks. Args: listToReuse, x, y, width, length, cacheFlag (if false, don't check biomeCache to avoid infinite loop in BiomeCacheBlock)
	 */
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6) {
		IntCache.resetIntCache();

		if(par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5) {
			par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
		}

		if(par6 && par4 == 16 && par5 == 16 && (par2 & 15) == 0 && (par3 & 15) == 0) {
			BiomeGenBase[] abiomegenbase1 = this.biomeCache.getCachedBiomes(par2, par3);
			System.arraycopy(abiomegenbase1, 0, par1ArrayOfBiomeGenBase, 0, par4 * par5);
			return par1ArrayOfBiomeGenBase;
		}
		else {
			int[] aint = this.biomeIndexLayer.getInts(par2, par3, par4, par5);

			for(int i1 = 0; i1 < par4 * par5; ++i1) {
				par1ArrayOfBiomeGenBase[i1] = BiomeGenBase.getBiome(aint[i1]);
			}

			return par1ArrayOfBiomeGenBase;
		}
	}

	/**
	 * checks given Chunk's Biomes against List of allowed ones
	 */
	public boolean areBiomesViable(int par1, int par2, int par3, List par4List) {
		IntCache.resetIntCache();
		int l = par1 - par3 >> 2;
		int i1 = par2 - par3 >> 2;
		int j1 = par1 + par3 >> 2;
		int k1 = par2 + par3 >> 2;
		int l1 = j1 - l + 1;
		int i2 = k1 - i1 + 1;
		int[] aint = this.genBiomes.getInts(l, i1, l1, i2);

		try {
			for(int j2 = 0; j2 < l1 * i2; ++j2) {
				BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[j2]);

				if(!par4List.contains(biomegenbase)) {
					return false;
				}
			}

			return true;
		} catch(Throwable throwable) {
			CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
			CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
			crashreportcategory.addCrashSection("Layer", this.genBiomes.toString());
			crashreportcategory.addCrashSection("x", Integer.valueOf(par1));
			crashreportcategory.addCrashSection("z", Integer.valueOf(par2));
			crashreportcategory.addCrashSection("radius", Integer.valueOf(par3));
			crashreportcategory.addCrashSection("allowed", par4List);
			throw new ReportedException(crashreport);
		}
	}

	public ChunkPosition findBiomePosition(int p_150795_1_, int p_150795_2_, int p_150795_3_, List p_150795_4_, Random p_150795_5_) {
		IntCache.resetIntCache();
		int l = p_150795_1_ - p_150795_3_ >> 2;
		int i1 = p_150795_2_ - p_150795_3_ >> 2;
		int j1 = p_150795_1_ + p_150795_3_ >> 2;
		int k1 = p_150795_2_ + p_150795_3_ >> 2;
		int l1 = j1 - l + 1;
		int i2 = k1 - i1 + 1;
		int[] aint = this.genBiomes.getInts(l, i1, l1, i2);
		ChunkPosition chunkposition = null;
		int j2 = 0;

		for(int k2 = 0; k2 < l1 * i2; ++k2) {
			int l2 = l + k2 % l1 << 2;
			int i3 = i1 + k2 / l1 << 2;
			BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[k2]);

			if(p_150795_4_.contains(biomegenbase) && (chunkposition == null || p_150795_5_.nextInt(j2 + 1) == 0)) {
				chunkposition = new ChunkPosition(l2, 0, i3);
				++j2;
			}
		}

		return chunkposition;
	}

	/**
	 * Calls the WorldChunkManager's biomeCache.cleanupCache()
	 */
	public void cleanupCache() {
		this.biomeCache.cleanupCache();
	}

	public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original) {
		WorldTypeEvent.InitBiomeGens event = new WorldTypeEvent.InitBiomeGens(worldType, seed, original);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		return event.newBiomeGens;
	}
}
