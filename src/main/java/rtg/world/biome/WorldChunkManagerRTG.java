package rtg.world.biome;

import gnu.trove.map.hash.TLongObjectHashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class WorldChunkManagerRTG extends WorldChunkManager implements RTGBiomeProvider
{
    /** A GenLayer containing the indices into BiomeGenBase.biomeList[] */
    private GenLayer genBiomes;
    private GenLayer biomeIndexLayer;
    private List biomesToSpawnIn;
    private OpenSimplexNoise simplex;
    private CellNoise cell;
    private float[] borderNoise;
    private TLongObjectHashMap<RealisticBiomeBase> biomeDataMap = new TLongObjectHashMap<RealisticBiomeBase>();
    private BiomeCache biomeCache;
    
    protected WorldChunkManagerRTG()
    {
        
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = new ArrayList();
        borderNoise = new float[256];
    }
    
    public WorldChunkManagerRTG(World par1World,WorldType worldType)
    {

        this();
        long seed = par1World.getSeed();
        if (par1World.provider.dimensionId !=0) throw new RuntimeException();

        simplex = new OpenSimplexNoise(seed);
        cell = new CellNoise(seed, (short) 0);
        cell.setUseDistance(true);
        GenLayer[] agenlayer = GenLayer.initializeAllBiomeGenerators(seed, worldType);
        agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
        this.genBiomes = agenlayer[0]; //maybe this will be needed
        this.biomeIndexLayer = agenlayer[1];
    }
    
    public int[] getBiomesGens(int par1, int par2, int par3, int par4)
    {
        
        int[] d = new int[par3 * par4];
        
        for (int i = 0; i < par3; i++)
        {
            for (int j = 0; j < par4; j++)
            {
                d[j * par3  + i] = getBiomeGenAt(par1 + i, par2 + j).biomeID;
            }
        }
        return d;
    }
    
    public boolean diff(float sample1, float sample2, float base)
    {
        
        if ((sample1 < base && sample2 > base) || (sample1 > base && sample2 < base))
        {
            return true;
        }
        return false;
    }
    
    public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
        {
            par1ArrayOfFloat = new float[par4 * par5];
        }

        int[] aint = this.biomeIndexLayer.getInts(par2, par3, par4, par5);

        for (int i1 = 0; i1 < par4 * par5; ++i1)
        {
            float f = 0;
            try {
                f = (float) RealisticBiomeBase.getBiome(aint[i1]).getIntRainfall() / 65536.0F;
            } catch (Exception e) {
                if (RealisticBiomeBase.getBiome(aint[i1])== null) {
                    throw new RuntimeException("No realistic version of biome "+aint[i1]);
                }
                throw new RuntimeException("problem with biome "+aint[i1]+"from "+e.getMessage());
            }
            if (f > 1.0F)
            {
                f = 1.0F;
            }

            par1ArrayOfFloat[i1] = f;
        }

        return par1ArrayOfFloat;
    }

    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {

        return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }

    @Override
    public BiomeGenBase getBiomeGenAt(int par1, int par2)
    {
        BiomeGenBase result = this.biomeCache.getBiomeGenAt(par1, par2);
        if (result == null) {
            throw new RuntimeException();
        }
        if (result.biomeName == null) {
            result.biomeName = "";
        }
        return result;
    }

    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        int[] aint = this.genBiomes.getInts(par2, par3, par4, par5);

        for (int i1 = 0; i1 < par4 * par5; ++i1)
        {
            par1ArrayOfBiomeGenBase[i1] = RealisticBiomeBase.getBiome(aint[i1]);
        }

        return par1ArrayOfBiomeGenBase;
    }

    public RealisticBiomeBase getBiomeDataAt(int par1, int par2)
    {
        /*long coords = ChunkCoordIntPair.chunkXZ2Int(par1, par2);
        if (biomeDataMap.containsKey(coords)) {
            return biomeDataMap.get(coords);
        }*/

        RealisticBiomeBase output = (RealisticBiomeBase)(this.getBiomeGenAt(par1, par2));
        if (output== null) throw new RuntimeException("no biome "+par1 + " " + par2);

        /*if (biomeDataMap.size() > 4096) {
            biomeDataMap.clear();
        }

        biomeDataMap.put(coords, output);*/

        return output;
    }

    @Override
    public void cleanupCache()
    {
        this.biomeCache.cleanupCache();
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
    
    public float getTemperatureAtHeight(float par1, int par2)
    {
        
        return par1;
    }
    
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        if (par6 && par4 == 16 && par5 == 16 && (par2 & 15) == 0 && (par3 & 15) == 0)
        {
            BiomeGenBase[] abiomegenbase1 = this.biomeCache.getCachedBiomes(par2, par3);
            System.arraycopy(abiomegenbase1, 0, par1ArrayOfBiomeGenBase, 0, par4 * par5);
            return par1ArrayOfBiomeGenBase;
        }
        else
        {
            int[] aint = this.biomeIndexLayer.getInts(par2, par3, par4, par5);

            for (int i1 = 0; i1 < par4 * par5; ++i1)
            {
                try {
                    par1ArrayOfBiomeGenBase[i1] = RealisticBiomeBase.getBiome(aint[i1]);
                } catch (Exception e) {
                    throw new RuntimeException(genBiomes.toString()+ " " + this.biomeIndexLayer.toString());
                }
                if (par1ArrayOfBiomeGenBase[i1] == null) {
                    throw new RuntimeException("missing biome "+aint[i1]);
                }
            }

            return par1ArrayOfBiomeGenBase;
        }
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
    
}
