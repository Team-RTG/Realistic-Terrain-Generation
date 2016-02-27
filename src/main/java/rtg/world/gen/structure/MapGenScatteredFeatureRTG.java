    package rtg.world.gen.structure;

    import net.minecraft.entity.monster.EntityWitch;
    import net.minecraft.util.BlockPos;
    import net.minecraft.util.MathHelper;
    import net.minecraft.world.World;
    import net.minecraft.world.biome.BiomeGenBase;
    import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces;
    import net.minecraft.world.gen.structure.MapGenScatteredFeature;
    import net.minecraft.world.gen.structure.StructureComponent;
    import net.minecraft.world.gen.structure.StructureStart;
    import net.minecraftforge.common.BiomeDictionary;
    import net.minecraftforge.fml.common.FMLLog;
    import org.apache.logging.log4j.Level;
    import rtg.config.rtg.ConfigRTG;

    import java.util.*;
    import java.util.Map.Entry;

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
 * in biomes that have been registered with multiple BiomeDictionary types that are shared by their vanilla counterparts.
 * For example, desert temples don't generate in SANDY biomes - they are only allowed to generate in biomes that
 * have been registered as HOT + DRY + SANDY.
 * 
 * This class has also been modified to resolve a very specific use case involving Thaumcraft world gen:
 * https://github.com/Team-RTG/Realistic-Terrain-Generation/issues/249
 * 
 */
public class MapGenScatteredFeatureRTG extends MapGenScatteredFeature
{
    private static List biomelist = Arrays.asList(BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.swampland);

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

            if (entry.getKey().equals("distance"))
            {
                this.maxDistanceBetweenScatteredFeatures = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.maxDistanceBetweenScatteredFeatures, this.minDistanceBetweenScatteredFeatures + 1);
            }
        }
    }

    @Override
    public String getStructureName()
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
            BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(new BlockPos(k * 16 + 8, 0,l * 16 + 8));

            if (biomegenbase != null) {
                
                //Desert temple.
                if (canSpawnDesertTemple(biomegenbase)) {
                    return true;
                }
                
                //Jungle temple.
                if (canSpawnJungleTemple(biomegenbase)) {
                    return true;
                }
                
                //Witch hut.
                if (canSpawnWitchHut(biomegenbase)) {
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
    public boolean func_175795_b(BlockPos pos)
    {
        StructureStart structurestart = this.func_175797_c(pos);
        if (structurestart != null && structurestart instanceof MapGenScatteredFeatureRTG.Start && !structurestart.getComponents().isEmpty())
        {
            StructureComponent structurecomponent = structurestart.getComponents().getFirst();
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

            BiomeGenBase biomegenbase = worldIn.getBiomeGenForCoords(new BlockPos(chunkX * 16 + 8, 0,chunkZ * 16 + 8));

            if (canSpawnDesertTemple(biomegenbase)) {
                ComponentScatteredFeaturePieces.DesertPyramid desertpyramid = new ComponentScatteredFeaturePieces.DesertPyramid(random, chunkX * 16, chunkZ * 16);
                arrComponents.add(desertpyramid);
            }

            if (canSpawnJungleTemple(biomegenbase)) {
                ComponentScatteredFeaturePieces.JunglePyramid junglepyramid = new ComponentScatteredFeaturePieces.JunglePyramid(random, chunkX * 16, chunkZ * 16);
                arrComponents.add(junglepyramid);
            }

            if (canSpawnWitchHut(biomegenbase)) {
                ComponentScatteredFeaturePieces.SwampHut swamphut = new ComponentScatteredFeaturePieces.SwampHut(random, chunkX * 16, chunkZ * 16);
                arrComponents.add(swamphut);
            }

            this.components.clear();
            
            if (arrComponents.size() > 0) {
                this.components.add((StructureComponent) arrComponents.get(random.nextInt(arrComponents.size())));
            }
            
            Logger.debug("Scattered feature candidate at %d, %d", chunkX * 16, chunkZ * 16);
            
            this.updateBoundingBox();
        }
    }
    
    private static boolean canSpawnDesertTemple(BiomeGenBase b)
    {
        boolean canSpawn = false;
        
        if (BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.HOT) && BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.DRY) && BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.SANDY)) {
            canSpawn = true;
        }
        
        return canSpawn;
    }
    
    private static boolean canSpawnJungleTemple(BiomeGenBase b)
    {
        boolean canSpawn = false;
        
        if (BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.HOT) && BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.WET) && BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.JUNGLE)) {
            canSpawn = true;
        }
        
        return canSpawn;
    }
    
    private static boolean canSpawnWitchHut(BiomeGenBase b)
    {
        boolean canSpawn = false;
        
        if (BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.WET) && BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.SWAMP)) {
            canSpawn = true;
        }
        
        return canSpawn;
    }
}