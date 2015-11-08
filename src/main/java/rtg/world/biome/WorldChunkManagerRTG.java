package rtg.world.biome;

import gnu.trove.map.hash.TLongObjectHashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rtg.config.vanilla.ConfigVanilla;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.RealisticBiomePool;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBase;

import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;

public class WorldChunkManagerRTG extends WorldChunkManager
{
    private BiomeCache biomeCache;
    private List biomesToSpawnIn;
    private OpenSimplexNoise simplex;
    private CellNoise cell;
    private CellNoise biomecell;  
    private float[] borderNoise;
    private Random rand;   
    private TLongObjectHashMap<RealisticBiomeBase> biomeDataMap = new TLongObjectHashMap<RealisticBiomeBase>();
    private RealisticBiomePool biomePool;
    
    protected WorldChunkManagerRTG()
    {
        
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = new ArrayList();
        borderNoise = new float[256];
    }
    
    public WorldChunkManagerRTG(World par1World)
    {
        
        this();
        long seed = par1World.getSeed();
        
        simplex = new OpenSimplexNoise(seed);
        cell = new CellNoise(seed, (short) 0);
        cell.setUseDistance(true);
        biomecell = new CellNoise(seed, (short) 0);
        rand = new Random(seed);
        biomePool = new RealisticBiomePool(biomecell, rand);
    }
    
    public int[] getBiomesGens(int par1, int par2, int par3, int par4)
    {
        
        int[] d = new int[par3 * par4];
        
        for (int i = 0; i < par3; i++)
        {
            for (int j = 0; j < par4; j++)
            {
                d[j * par3 + i] = getBiomeGenAt(par1 + i, par2 + j).biomeID;
            }
        }
        return d;
    }
    
    public RealisticBiomeBase[] getBiomesGensData(int par1, int par2, int par3, int par4)
    {
        
        RealisticBiomeBase[] data = new RealisticBiomeBase[par3 * par4];
        
        for (int i = 0; i < par3; i++)
        {
            for (int j = 0; j < par4; j++)
            {
                data[j * par3 + i] = getBiomeDataAt(par1 + i, par2 + j);
            }
        }
        return data;
    }
    
    public boolean diff(float sample1, float sample2, float base)
    {
        
        if ((sample1 < base && sample2 > base) || (sample1 > base && sample2 < base))
        {
            return true;
        }
        return false;
    }
    
    public BiomeGenBase getBiomeGenAt(int par1, int par2)
    {
        
        return getBiomeDataAt(par1, par2).baseBiome;
    }
    
    public RealisticBiomeBase getBiomeDataAt(int par1, int par2)
    {
        
        long coords = ChunkCoordIntPair.chunkXZ2Int(par1, par2);
        if (biomeDataMap.containsKey(coords)) {
            return biomeDataMap.get(coords);
        }
        
        RealisticBiomeBase output = biomePool.chooseBiome(par1, par2);
        
        /**
         * Do we only want to generate a single biome for the whole world?
         */
        String generateOnlyThisVanillaBiome = ConfigVanilla.generateOnlyThisVanillaBiome;
        
        if (generateOnlyThisVanillaBiome.length() > 0)
        {
            output = RealisticBiomeVanillaBase.getRealisticVanillaBiomeFromVanillaVariableName(generateOnlyThisVanillaBiome);
        }
        
        // output = RealisticBiomeBase.vanillaExtremeHillsPlus;
        
        if (biomeDataMap.size() > 4096) {
            biomeDataMap.clear();
        }
        
        biomeDataMap.put(coords, output);

        return output;
    }
    
    public float getNoiseAt(int x, int y)
    {
        
        float river = getRiverStrength(x, y) + 1f;
        if (river < 0.5f)
        {
            return 59f;
        }
        
        return getBiomeDataAt(x, y).rNoise(simplex, cell, x, y, 1f, river);
    }
    
    public float calculateRiver(int x, int y, float st, float biomeHeight)
    {
        
        if (st < 0f && biomeHeight > 59f)
        {
            float pX = x + (simplex.noise1(y / 240f) * 220f);
            float pY = y + (simplex.noise1(x / 240f) * 220f);
            float r = cell.border(pX / 1250D, pY / 1250D, 50D / 1300D, 1f);
            return (biomeHeight * (r + 1f))
                + ((59f + simplex.noise2(x / 12f, y / 12f) * 2f + simplex.noise2(x / 8f, y / 8f) * 1.5f) * (-r));
        }
        else
        {
            return biomeHeight;
        }
    }
    
    public float getRiverStrength(int x, int y)
    {
        
        return cell
            .border((x + (simplex.noise1(y / 240f) * 220f)) / 1250D, (y + (simplex.noise1(x / 240f) * 220f)) / 1250D, 50D / 300D, 1f);
    }
    
    public boolean isBorderlessAt(int x, int y)
    {
        
        int bx, by;
        
        for (bx = -2; bx <= 2; bx++)
        {
            for (by = -2; by <= 2; by++)
            {
                borderNoise[getBiomeDataAt(x + bx * 16, y + by * 16).biomeID] += 0.04f;
            }
        }
        
        by = 0;
        for (bx = 0; bx < 256; bx++)
        {
            if (borderNoise[bx] > 0.98f)
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
            float var8 = (float) BiomeGenBase.getBiome(var6[var7]).getIntRainfall() / 65536.0F;
            
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
        
        float centerNoise = getNoiseAt(x, y);
        if (centerNoise < 62)
        {
            return false;
        }
        
        float lowestNoise = centerNoise;
        float highestNoise = centerNoise;
        for (int i = -2; i <= 2; i++)
        {
            for (int j = -2; j <= 2; j++)
            {
                if (i != 0 && j != 0)
                {
                    float n = getNoiseAt(x + i * 16, y + j * 16);
                    if (n < lowestNoise) {
                        lowestNoise = n;
                    }
                    if (n > highestNoise) {
                        highestNoise = n;
                    }
                }
            }
        }
        
        if (highestNoise - lowestNoise < 22)
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
