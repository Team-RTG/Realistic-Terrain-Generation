package rtg.world.gen.structure;

import java.util.*;
import java.util.Map.Entry;

import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

import net.minecraftforge.common.BiomeDictionary;

import com.google.common.collect.Lists;

import rtg.api.RTGAPI;
import rtg.util.Logger;

/**
 * Author: Choonster (https://github.com/Choonster)
 * Source: https://github.com/Choonster/TestMod2/blob/1575b85ad8949381215f3aeb6ca76ea2368074de/src/main/java/com/choonster/testmod2/world/gen/structure/MapGenScatteredFeatureModBiomes.java
 * Modified by: WhichOnesPink (https://github.com/whichonespink44)
 * <p>
 * Allows scattered features (jungle/desert temples, witch huts) to spawn in modded biomes, equivalent to the vanilla biomes,
 * i.e. any biome registered as JUNGLE, SANDY or SWAMP
 * http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/modification-development/2471489-jungle-and-desert-temple-spawn-biome
 * <p>
 * This class was modified by WhichOnesPink on 2015-11-05 to allow the spawning of scattered features ONLY
 * in biomes that have been registered with multiple BiomeDictionary types that are shared by their vanilla counterparts.
 * For example, desert temples don't generate in SANDY biomes - they are only allowed to generate in biomes that
 * have been registered as HOT + DRY + SANDY.
 * <p>
 * This class has also been modified to resolve a very specific use case involving Thaumcraft world gen:
 * https://github.com/Team-RTG/Realistic-Terrain-Generation/issues/249
 */
public class MapGenScatteredFeatureRTG extends MapGenScatteredFeature
{
    private static final List<Biome> BIOMELIST = Arrays.<Biome>asList(new Biome[] {Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.JUNGLE, Biomes.JUNGLE_HILLS, Biomes.SWAMPLAND, Biomes.ICE_PLAINS, Biomes.COLD_TAIGA});
    private final List<Biome.SpawnListEntry> scatteredFeatureSpawnList;
    private int maxDistanceBetweenScatteredFeatures;
    private final int minDistanceBetweenScatteredFeatures;

    public MapGenScatteredFeatureRTG()
    {
        int minDistance = RTGAPI.config().MIN_DISTANCE_SCATTERED_FEATURES.get();
        int maxDistance = RTGAPI.config().MAX_DISTANCE_SCATTERED_FEATURES.get();

        if (minDistance > maxDistance) {
            minDistance = 8;
            maxDistance = 32;
        }

        this.scatteredFeatureSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
        this.maxDistanceBetweenScatteredFeatures = maxDistance;
        this.minDistanceBetweenScatteredFeatures = minDistance;
        this.scatteredFeatureSpawnList.add(new Biome.SpawnListEntry(EntityWitch.class, 1, 1, 1));
    }

    public MapGenScatteredFeatureRTG(Map<String, String> p_i2061_1_)
    {
        this();

        for (Entry<String, String> entry : p_i2061_1_.entrySet())
        {
            if (((String)entry.getKey()).equals("distance"))
            {
                this.maxDistanceBetweenScatteredFeatures = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.maxDistanceBetweenScatteredFeatures, 9);
            }
        }
    }

    @Override
    public String getStructureName()
    {
        return "Temple";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        if (chunkZ < 0)
        {
            chunkZ -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        int k = chunkX / this.maxDistanceBetweenScatteredFeatures;
        int l = chunkZ / this.maxDistanceBetweenScatteredFeatures;
        Random random = this.worldObj.setRandomSeed(k, l, 14357617);
        k *= this.maxDistanceBetweenScatteredFeatures;
        l *= this.maxDistanceBetweenScatteredFeatures;
        k += random.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);
        l += random.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);

        if (i == k && j == l)
        {
            BlockPos pos = new BlockPos(i * 16 + 8, 0, j * 16 + 8);
            Biome biome = this.worldObj.getBiomeProvider().getBiome(pos);

            if (null == biome) {
                Logger.error("MapGenScatteredFeatureRTG#canSpawnStructureAtCoords received a null biome at %d %d.", pos.getX(), pos.getZ());
                return false;
            }

            //Desert temple.
            if (canSpawnDesertTemple(biome)) {
                return true;
            }

            //Jungle temple.
            if (canSpawnJungleTemple(biome)) {
                return true;
            }

            //Witch hut.
            if (canSpawnWitchHut(biome)) {
                return true;
            }

            //Igloo.
            if (canSpawnIgloo(biome)) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new MapGenScatteredFeatureRTG.Start(this.worldObj, this.rand, chunkX, chunkZ);
    }

    @Override
    public boolean isSwampHut(BlockPos p_175798_1_)
    {
        StructureStart structurestart = this.getStructureAt(p_175798_1_);

        if (structurestart != null && structurestart instanceof MapGenScatteredFeatureRTG.Start && !structurestart.getComponents().isEmpty())
        {
            StructureComponent structurecomponent = (StructureComponent)structurestart.getComponents().get(0);
            return structurecomponent instanceof ComponentScatteredFeaturePieces.SwampHut;
        }
        else
        {
            return false;
        }
    }

    @Override
    public List<Biome.SpawnListEntry> getScatteredFeatureSpawnList()
    {
        return this.scatteredFeatureSpawnList;
    }

    public static class Start extends StructureStart
    {
        public Start()
        {
        }

        public Start(World worldIn, Random random, int chunkX, int chunkZ)
        {
            this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)));
        }

        public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biomeIn)
        {
            super(chunkX, chunkZ);

            if (null == biomeIn) {
                Logger.error("MapGenScatteredFeatureRTG.Start received a null biome at %d %d.", chunkX * 16 + 8, chunkZ * 16 + 8);
                return;
            }

            LinkedList arrComponents = new LinkedList();

            if (canSpawnDesertTemple(biomeIn)) {
                ComponentScatteredFeaturePieces.DesertPyramid desertpyramid = new ComponentScatteredFeaturePieces.DesertPyramid(random, chunkX * 16, chunkZ * 16);
                arrComponents.add(desertpyramid);
            }

            if (canSpawnJungleTemple(biomeIn)) {
                ComponentScatteredFeaturePieces.JunglePyramid junglepyramid = new ComponentScatteredFeaturePieces.JunglePyramid(random, chunkX * 16, chunkZ * 16);
                arrComponents.add(junglepyramid);
            }

            if (canSpawnWitchHut(biomeIn)) {
                ComponentScatteredFeaturePieces.SwampHut swamphut = new ComponentScatteredFeaturePieces.SwampHut(random, chunkX * 16, chunkZ * 16);
                arrComponents.add(swamphut);
            }

            if (canSpawnIgloo(biomeIn)) {
                ComponentScatteredFeaturePieces.Igloo igloo = new ComponentScatteredFeaturePieces.Igloo(random, chunkX * 16, chunkZ * 16);
                arrComponents.add(igloo);
            }

            this.components.clear();

            if (arrComponents.size() > 0) {
                this.components.add((StructureComponent) arrComponents.get(random.nextInt(arrComponents.size())));
            }

            Logger.debug("Scattered feature candidate at %d, %d", chunkX * 16, chunkZ * 16);

            this.updateBoundingBox();
        }
    }

    private static boolean canSpawnDesertTemple(Biome b) {
        return (BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.HOT) && BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.DRY) && BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.SANDY));
    }

    private static boolean canSpawnJungleTemple(Biome b) {
        return (BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.HOT) && BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.WET) && BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.JUNGLE));
    }

    private static boolean canSpawnWitchHut(Biome b) {
        return (BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.WET) && BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.SWAMP));
    }

    private static boolean canSpawnIgloo(Biome b) {
        return (BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.COLD) && BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.SNOWY) && BiomeDictionary.isBiomeOfType(b, BiomeDictionary.Type.PLAINS));
    }
}