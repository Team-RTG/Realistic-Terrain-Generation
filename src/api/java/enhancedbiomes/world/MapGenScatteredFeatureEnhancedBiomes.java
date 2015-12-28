package enhancedbiomes.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import enhancedbiomes.world.biome.EnhancedBiomesSand;
import enhancedbiomes.world.biome.EnhancedBiomesTropical;
import enhancedbiomes.world.biome.EnhancedBiomesWetland;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenScatteredFeatureEnhancedBiomes extends MapGenScatteredFeature
{
    private static List biomelist = Arrays.asList(new BiomeGenBase[] {
    		EnhancedBiomesWetland.biomeMangrove, 
    		EnhancedBiomesWetland.biomeCarr, 
    		EnhancedBiomesSand.biomeRockyDesert, 
    		EnhancedBiomesTropical.biomeRainforest, 
    		EnhancedBiomesTropical.biomeRainforestValley});

    /** contains possible spawns for scattered features */
    private List scatteredFeatureEnhancedBiomesSpawnList;

    /** the maximum distance between scattered features */
    private int maxDistanceBetweenScatteredFeatures;

    /** the minimum distance between scattered features */
    private int minDistanceBetweenScatteredFeatures;

    public MapGenScatteredFeatureEnhancedBiomes()
    {
        this.scatteredFeatureEnhancedBiomesSpawnList = new ArrayList();
        this.maxDistanceBetweenScatteredFeatures = 32;
        this.minDistanceBetweenScatteredFeatures = 8;
        this.scatteredFeatureEnhancedBiomesSpawnList.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 1, 1, 1));
    }

    public MapGenScatteredFeatureEnhancedBiomes(Map par1Map)
    {
        this();
        Iterator var2 = par1Map.entrySet().iterator();

        while (var2.hasNext())
        {
            Entry var3 = (Entry)var2.next();

            if (((String)var3.getKey()).equals("distance"))
            {
                this.maxDistanceBetweenScatteredFeatures = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.maxDistanceBetweenScatteredFeatures, this.minDistanceBetweenScatteredFeatures + 1);
            }
        }
    }

    protected boolean canSpawnStructureAtCoords(int par1, int par2)
    {
        int var3 = par1;
        int var4 = par2;

        if (par1 < 0)
        {
            par1 -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        if (par2 < 0)
        {
            par2 -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        int var5 = par1 / this.maxDistanceBetweenScatteredFeatures;
        int var6 = par2 / this.maxDistanceBetweenScatteredFeatures;
        Random var7 = this.worldObj.setRandomSeed(var5, var6, 14357617);
        var5 *= this.maxDistanceBetweenScatteredFeatures;
        var6 *= this.maxDistanceBetweenScatteredFeatures;
        var5 += var7.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);
        var6 += var7.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);

        if (var3 == var5 && var4 == var6)
        {
            BiomeGenBase var8 = this.worldObj.getWorldChunkManager().getBiomeGenAt(var3 * 16 + 8, var4 * 16 + 8);
            Iterator var9 = biomelist.iterator();

            while (var9.hasNext())
            {
                BiomeGenBase var10 = (BiomeGenBase)var9.next();

                if (var8 == var10)
                {
                    return true;
                }
            }
        }

        return false;
    }

    protected StructureStart getStructureStart(int par1, int par2)
    {
        return new StructureScatteredFeatureEnhancedBiomesStart(this.worldObj, this.rand, par1, par2);
    }

    /**
     * returns possible spawns for scattered features
     */
    public List getScatteredFeatureEnhancedBiomesSpawnList()
    {
        return this.scatteredFeatureEnhancedBiomesSpawnList;
    }

	@Override
	public String func_143025_a() 
	{
		return null;
	}
}
