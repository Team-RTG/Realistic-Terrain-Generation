    package rtg.world.gen.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.logging.log4j.Level;

import rtg.config.rtg.ConfigRTG;
import cpw.mods.fml.common.FMLLog;

import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

import net.minecraftforge.common.BiomeDictionary;

/**
 * Author: Choonster (https://github.com/Choonster)
 * Source: https://github.com/Choonster/TestMod2/blob/1575b85ad8949381215f3aeb6ca76ea2368074de/src/main/java/com/choonster/testmod2/world/gen/structure/MapGenScatteredFeatureModBiomes.java
 * Modified by: WhichOnesPink (https://github.com/whichonespink44)
 * 
 * Allows scattered features (jungle/desert temples, witch huts) to spawn in modded biomes, equivalent to the vanilla biomes,
 * i.e. any biome registered as JUNGLE, SANDY or SWAMP
 * http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/modification-development/2471489-jungle-and-desert-temple-spawn-biome
 * 
 * This class was modified by WhichOnesPink on 2015-11-05 to allow the spawning of scattered features ONLY
 * in biomes that have been registered with ALL of the BiomeDictionary types of their vanilla counterparts.
 * For example, desert temples don't generate in SANDY biomes - they are only allowed to generate in biomes that
 * have been registered as HOT + DRY + SANDY. 
 */
public class MapGenScatteredFeatureRTG extends MapGenScatteredFeature
{
    private static List biomelist = Arrays.asList(new BiomeGenBase[] {BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.swampland});

    /** contains possible spawns for scattered features */
    private List scatteredFeatureSpawnList;
    
    /** the maximum distance between scattered features */
    private int maxDistanceBetweenScatteredFeatures;
    
    /** the minimum distance between scattered features */
    private int minDistanceBetweenScatteredFeatures;
    
    public MapGenScatteredFeatureRTG()
    {
        int minDistance = ConfigRTG.minDistanceScatteredFeatures;
        int maxDistance = ConfigRTG.maxDistanceScatteredFeatures;
        
        if (minDistance > maxDistance) {
            minDistance = 8;
            maxDistance = 32;
        }
        
        this.scatteredFeatureSpawnList = new ArrayList();
        this.maxDistanceBetweenScatteredFeatures = maxDistance;
        this.minDistanceBetweenScatteredFeatures = minDistance;
        this.scatteredFeatureSpawnList.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 1, 1, 1));
    }

    public MapGenScatteredFeatureRTG(Map p_i2061_1_)
    {
        this();
        Iterator iterator = p_i2061_1_.entrySet().iterator();

        while (iterator.hasNext())
        {
            Entry entry = (Entry)iterator.next();

            if (((String)entry.getKey()).equals("distance"))
            {
                this.maxDistanceBetweenScatteredFeatures = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.maxDistanceBetweenScatteredFeatures, this.minDistanceBetweenScatteredFeatures + 1);
            }
        }
    }

    @Override
    public String func_143025_a()
    {
        return "Temple";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int p_75047_1_, int p_75047_2_)
    {
        int k = p_75047_1_;
        int l = p_75047_2_;

        if (p_75047_1_ < 0)
        {
            p_75047_1_ -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        if (p_75047_2_ < 0)
        {
            p_75047_2_ -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        int i1 = p_75047_1_ / this.maxDistanceBetweenScatteredFeatures;
        int j1 = p_75047_2_ / this.maxDistanceBetweenScatteredFeatures;
        Random random = this.worldObj.setRandomSeed(i1, j1, 14357617);
        i1 *= this.maxDistanceBetweenScatteredFeatures;
        j1 *= this.maxDistanceBetweenScatteredFeatures;
        i1 += random.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);
        j1 += random.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);

        if (k == i1 && l == j1)
        {
            BiomeGenBase biomegenbase = this.worldObj.getWorldChunkManager().getBiomeGenAt(k * 16 + 8, l * 16 + 8);

            if (biomegenbase != null) {
                
                //Desert temple.
                if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.HOT) && BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.DRY) && BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.SANDY)) {
                    return true;
                }
                
                //Jungle temple.
                if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.HOT) && BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.WET) && BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.JUNGLE)) {
                    return true;
                }
                
                //Witch hut.
                if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.WET) && BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.SWAMP)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    protected StructureStart getStructureStart(int p_75049_1_, int p_75049_2_)
    {
        return new MapGenScatteredFeatureRTG.Start(this.worldObj, this.rand, p_75049_1_, p_75049_2_);
    }

    @Override
    public boolean func_143030_a(int p_143030_1_, int p_143030_2_, int p_143030_3_)
    {
        StructureStart structurestart = this.func_143028_c(p_143030_1_, p_143030_2_, p_143030_3_);

        if (structurestart != null && structurestart instanceof MapGenScatteredFeatureRTG.Start && !structurestart.getComponents().isEmpty())
        {
            StructureComponent structurecomponent = (StructureComponent)structurestart.getComponents().getFirst();
            return structurecomponent instanceof ComponentScatteredFeaturePieces.SwampHut;
        }
        else
        {
            return false;
        }
    }

    /**
     * returns possible spawns for scattered features
     */
    @Override
    public List getScatteredFeatureSpawnList()
    {
        return this.scatteredFeatureSpawnList;
    }

    public static class Start extends MapGenScatteredFeature.Start
        {
            public Start() {}

            public Start(World worldIn, Random random, int chunkX, int chunkZ) {
                
                super(worldIn, random, chunkX, chunkZ);

                LinkedList arrComponents = new LinkedList();

                BiomeGenBase biomegenbase = worldIn.getBiomeGenForCoords(chunkX * 16 + 8, chunkZ * 16 + 8);

                if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.HOT) && BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.DRY) && BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.SANDY)) {
                    ComponentScatteredFeaturePieces.DesertPyramid desertpyramid = new ComponentScatteredFeaturePieces.DesertPyramid(random, chunkX * 16, chunkZ * 16);
                    arrComponents.add(desertpyramid);
                }

                if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.HOT) && BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.DENSE) && BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.WET) && BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.JUNGLE)) {
                    ComponentScatteredFeaturePieces.JunglePyramid junglepyramid = new ComponentScatteredFeaturePieces.JunglePyramid(random, chunkX * 16, chunkZ * 16);
                    arrComponents.add(junglepyramid);
                }

                if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.WET) && BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.SWAMP)) {
                    ComponentScatteredFeaturePieces.SwampHut swamphut = new ComponentScatteredFeaturePieces.SwampHut(random, chunkX * 16, chunkZ * 16);
                    arrComponents.add(swamphut);
                }

                this.components.clear();
                
                if (arrComponents.size() > 0) {
                    this.components.add(arrComponents.get(random.nextInt(arrComponents.size())));
                }
                
                if (ConfigRTG.enableDebugging) {
                    FMLLog.log(Level.INFO, "Scattered feature candidate at %d, %d", chunkX * 16, chunkZ * 16);
                }
                
                this.updateBoundingBox();
            }
        }
}