package rtg.world.biome;

import gnu.trove.map.hash.TLongObjectHashMap;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.BlockPos;
import org.apache.logging.log4j.Level;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexCellularNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import net.minecraftforge.fml.common.FMLLog;

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
    private SimplexCellularNoise simplexCell;
    private SimplexCellularNoise.NoiseInstance2[] riverCellNoiseInstances;
    private OpenSimplexNoise.NoiseInstance2[] riverOpenSimplexNoiseInstances;
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
        if (par1World.provider.getDimensionId() !=0) throw new RuntimeException();

        simplex = new OpenSimplexNoise(seed);
        cell = new CellNoise(seed, (short) 0);
        cell.setUseDistance(true);
        simplexCell = new SimplexCellularNoise(seed);
        riverCellNoiseInstances = new SimplexCellularNoise.NoiseInstance2[] {
        		new SimplexCellularNoise.NoiseInstance2(simplexCell, 0, 1)
        };
        riverOpenSimplexNoiseInstances = new OpenSimplexNoise.NoiseInstance2[] {
        		new OpenSimplexNoise.NoiseInstance2(simplex, -1, -1, -1, 0, 1)
        };
        GenLayer[] agenlayer = GenLayer.initializeAllBiomeGenerators(seed, worldType, "");
        agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
        this.genBiomes = agenlayer[0]; //maybe this will be needed
        this.biomeIndexLayer = agenlayer[1];
    }

    @Override
    public int[] getBiomesGens(int par1, int par2, int par3, int par4)
    {
        
        int[] d = new int[par3 * par4];
        
        for (int i = 0; i < par3; i++)
        {
            for (int j = 0; j < par4; j++)
            {
                d[j * par3  + i] = getBiomeGenAt(new BlockPos(par1 + i, 0, par2 + j)).biomeID;
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
    public BiomeGenBase getBiomeGenAt(BlockPos blockPos)
    {
        BiomeGenBase result = this.biomeCache.getBiomeCacheBlock(blockPos.getX(), blockPos.getZ()).getBiomeGenAt(blockPos.getX(), blockPos.getZ());
        
        if (result == null) {
            throw new RuntimeException();
        }
        
        if (result.biomeName == null) {
            result.biomeName = "";
            FMLLog.log(Level.WARN, "Biome %d has no name.", result.biomeID);
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

    @Override
    public RealisticBiomeBase getBiomeDataAt(BlockPos blockPos)
    {
        /*long coords = ChunkCoordIntPair.chunkXZ2Int(par1, par2);
        if (biomeDataMap.containsKey(coords)) {
            return biomeDataMap.get(coords);
        }*/

        RealisticBiomeBase output = (RealisticBiomeBase)(this.getBiomeGenAt(blockPos));
        if (output== null) throw new RuntimeException("no biome "+blockPos.getX() + " " + blockPos.getZ());

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

    public float getNoiseAt(BlockPos blockPos)
    {
        
        float river = getRiverStrength(blockPos) + 1f;
        if (river < 0.5f)
        {
            return 59f;
        }
        
        return getBiomeDataAt(blockPos).rNoise(simplex, cell, blockPos.getX(), blockPos.getZ(), 1f, river);
    }
    
	private static double cellBorder(double[] results, double width, double depth) {
		double c = results[1] - results[0];
		if (c < width) {
			return ((c / width) - 1) * depth;
		} else {
			return 0;
		}
	}
    
    public float calculateRiver(int x, int y, float st, float biomeHeight)
    {
        
        if (st < 0f && biomeHeight > 59f)
        {
        	//New river curve function. No longer creates worldwide curve correlations along cardinal axes.
        	double[] simplexResults = new double[2];
        	OpenSimplexNoise.noise(x / 240.0, y / 240.0, riverOpenSimplexNoiseInstances, simplexResults);
            double pX = x + simplexResults[0] * 220f;
            double pY = y + simplexResults[1] * 220f;

            //New cellular noise.
            //TODO move the initialization of the results in a way that's more efficient but still thread safe.
            double[] results = SimplexCellularNoise.initResultArray(riverCellNoiseInstances);
            SimplexCellularNoise.resetResultArray(riverCellNoiseInstances, results);
            SimplexCellularNoise.eval(pX / 1875.0, pY / 1875.0, riverCellNoiseInstances, results);
            float r = (float) cellBorder(results, 30.0 / 1300.0, 1.0);
            
            return (biomeHeight * (r + 1f))
                + ((59f + simplex.noise2(x / 12f, y / 12f) * 2f + simplex.noise2(x / 8f, y / 8f) * 1.5f) * (-r));
        }
        else
        {
            return biomeHeight;
        }
    }

    @Override
    public float getRiverStrength(BlockPos blockPos)
    {
        int x = blockPos.getX();
        int y = blockPos.getZ();
    	//New river curve function. No longer creates worldwide curve correlations along cardinal axes.
    	double[] simplexResults = new double[2];
    	OpenSimplexNoise.noise( x / 240.0, y / 240.0, riverOpenSimplexNoiseInstances, simplexResults);
        double pX = x + simplexResults[0] * 220f;
        double pY = y + simplexResults[1] * 220f;
        
        //New cellular noise.
        //TODO move the initialization of the results in a way that's more efficient but still thread safe.
        double[] results = SimplexCellularNoise.initResultArray(riverCellNoiseInstances);
        SimplexCellularNoise.resetResultArray(riverCellNoiseInstances, results);
        SimplexCellularNoise.eval(pX / 1875.0, pY / 1875.0, riverCellNoiseInstances, results);
        return (float) cellBorder(results, 30.0 / 300.0, 1.0);
    }
    	
    public boolean isBorderlessAt(int x, int y)
    {
        
        int bx, by;
        
        for (bx = -2; bx <= 2; bx++)
        {
            for (by = -2; by <= 2; by++)
            {
                borderNoise[getBiomeDataAt(new BlockPos(x + bx * 16, 0, y + by * 16)).biomeID] += 0.04f;
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
    
    public boolean areBiomesViable(int x, int y, int par3, List par4List) {

        float centerNoise = getNoiseAt(new BlockPos(x, 0, y));
        if (centerNoise < 62) {
            return false;
        }

        float lowestNoise = centerNoise;
        float highestNoise = centerNoise;
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (i != 0 && j != 0) {
                    float n = getNoiseAt(new BlockPos(x + i * 16,0 ,y + j * 16));
                    if (n < lowestNoise) {
                        lowestNoise = n;
                    }
                    if (n > highestNoise) {
                        highestNoise = n;
                    }
                }
            }
        }

        if (highestNoise - lowestNoise < 22) {
            return true;
        }

        return false;
    }
    
}
