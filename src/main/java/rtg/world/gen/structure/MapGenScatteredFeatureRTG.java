package rtg.world.gen.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.logging.log4j.Level;

import rtg.config.rtg.ConfigRTG;

import com.google.common.collect.ImmutableList;

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

public class MapGenScatteredFeatureRTG extends MapGenScatteredFeature
{
    public final List<BiomeDictionary.Type> biomeTypes = ImmutableList.of(BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.SWAMP);
    
    private static List biomelist = Arrays.asList(new BiomeGenBase[] {BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.jungle,
        BiomeGenBase.jungleHills, BiomeGenBase.swampland});
    
    /** contains possible spawns for scattered features */
    private List scatteredFeatureSpawnList;
    
    /** the maximum distance between scattered features */
    private int maxDistanceBetweenScatteredFeatures;
    
    /** the minimum distance between scattered features */
    private int minDistanceBetweenScatteredFeatures;
    
    private static final String __OBFID = "CL_00000471";
    
    public MapGenScatteredFeatureRTG()
    {
        super();
        
        int minDistanceScatteredFeatures = ConfigRTG.minDistanceScatteredFeatures;
        int maxDistanceScatteredFeatures = ConfigRTG.maxDistanceScatteredFeatures;
        
        if (minDistanceScatteredFeatures > maxDistanceScatteredFeatures) {
            minDistanceScatteredFeatures = 8;
            maxDistanceScatteredFeatures = 32;
        }
        
        this.scatteredFeatureSpawnList = new ArrayList();
        this.minDistanceBetweenScatteredFeatures = minDistanceScatteredFeatures;
        this.maxDistanceBetweenScatteredFeatures = maxDistanceScatteredFeatures;
        this.scatteredFeatureSpawnList.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 1, 1, 1));
    }
    
    public MapGenScatteredFeatureRTG(Map p_i2061_1_)
    {
        this();
        Iterator iterator = p_i2061_1_.entrySet().iterator();
        
        while (iterator.hasNext())
        {
            Entry entry = (Entry) iterator.next();
            
            if (((String) entry.getKey()).equals("distance"))
            {
                this.maxDistanceBetweenScatteredFeatures =
                    MathHelper.parseIntWithDefaultAndMax((String) entry.getValue(), this.maxDistanceBetweenScatteredFeatures,
                        this.minDistanceBetweenScatteredFeatures + 1);
            }
        }
    }
    
    @Override
    public String func_143025_a()
    {
        return "Temple";
    }
    
    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        int intX = chunkX;
        int intZ = chunkZ;
        
        if (chunkX < 0)
        {
            chunkX -= this.maxDistanceBetweenScatteredFeatures - 1;
        }
        
        if (chunkZ < 0)
        {
            chunkZ -= this.maxDistanceBetweenScatteredFeatures - 1;
        }
        
        int i1 = chunkX / this.maxDistanceBetweenScatteredFeatures;
        int j1 = chunkZ / this.maxDistanceBetweenScatteredFeatures;
        Random random = this.worldObj.setRandomSeed(i1, j1, 14357617);
        i1 *= this.maxDistanceBetweenScatteredFeatures;
        j1 *= this.maxDistanceBetweenScatteredFeatures;
        i1 += random.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);
        j1 += random.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);
        
        if (intX == i1 && intZ == j1)
        {
            BiomeGenBase biomegenbase = this.worldObj.getWorldChunkManager().getBiomeGenAt(intX * 16 + 8, intZ * 16 + 8);
            
            Iterator iterator = biomelist.iterator();
            
            while (iterator.hasNext())
            {
                BiomeGenBase biomegenbase1 = (BiomeGenBase) iterator.next();
                
                if (biomegenbase == biomegenbase1)
                {
                    return true;
                }
            }
            
            if (biomegenbase != null) {
                for (BiomeDictionary.Type type : biomeTypes) {
                    if (BiomeDictionary.isBiomeOfType(biomegenbase, type)) {
                        return true;
                    }
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
        
        if (structurestart != null && structurestart instanceof MapGenScatteredFeatureRTG.Start
            && !structurestart.getComponents().isEmpty())
        {
            StructureComponent structurecomponent = (StructureComponent) structurestart.getComponents().getFirst();
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
    
    public static class Start extends StructureStart
    {
        
        private static final String __OBFID = "CL_00000472";
        
        public Start() {}
        
        public Start(World p_i2060_1_, Random p_i2060_2_, int p_i2060_3_, int p_i2060_4_)
        {
            super(p_i2060_3_, p_i2060_4_);
            BiomeGenBase biomegenbase = p_i2060_1_.getBiomeGenForCoords(p_i2060_3_ * 16 + 8, p_i2060_4_ * 16 + 8);
            
            if (!BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.JUNGLE))
            {
                if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.SWAMP))
                {
                    ComponentScatteredFeaturePieces.SwampHut swamphut =
                        new ComponentScatteredFeaturePieces.SwampHut(p_i2060_2_, p_i2060_3_ * 16, p_i2060_4_ * 16);
                    this.components.add(swamphut);
                    
                    FMLLog.log(Level.INFO, "Witch Hut generated at %d, %d", p_i2060_3_ * 16, p_i2060_4_ * 16);
                }
                else if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.SANDY))
                {
                    ComponentScatteredFeaturePieces.DesertPyramid desertpyramid =
                        new ComponentScatteredFeaturePieces.DesertPyramid(p_i2060_2_, p_i2060_3_ * 16, p_i2060_4_ * 16);
                    this.components.add(desertpyramid);
                    
                    FMLLog.log(Level.INFO, "Desert Temple generated at %d, %d", p_i2060_3_ * 16, p_i2060_4_ * 16);
                }
            }
            else
            {
                ComponentScatteredFeaturePieces.JunglePyramid junglepyramid =
                    new ComponentScatteredFeaturePieces.JunglePyramid(p_i2060_2_, p_i2060_3_ * 16, p_i2060_4_ * 16);
                this.components.add(junglepyramid);
                
                FMLLog.log(Level.INFO, "Jungle Temple generated at %d, %d", p_i2060_3_ * 16, p_i2060_4_ * 16);
            }
            
            this.updateBoundingBox();
        }
    }
}
