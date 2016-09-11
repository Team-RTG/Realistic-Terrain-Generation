package rtg.world.gen.structure;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import rtg.api.biome.BiomeConfig;
import rtg.config.rtg.ConfigRTG;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;

public class MapGenVillageRTG extends MapGenVillage
{
    public static List<Biome> VILLAGE_SPAWN_BIOMES = Arrays.<Biome>asList(new Biome[] {Biomes.PLAINS, Biomes.DESERT, Biomes.SAVANNA, Biomes.TAIGA});
    private int size;
    private int distance;
    private final int minTownSeparation;

    public MapGenVillageRTG()
    {
        this.size = ConfigRTG.villageSize; // Vanilla = 0
        this.distance = ConfigRTG.maxDistanceVillages; // Vanille = 32
        this.minTownSeparation = ConfigRTG.minDistanceVillages; // Vanilla = 8
    }

    public MapGenVillageRTG(Map<String, String> map)
    {
        this();

        for (Entry<String, String> entry : map.entrySet())
        {
            if (((String)entry.getKey()).equals("size"))
            {
                this.size = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.size, 0);
            }
            else if (((String)entry.getKey()).equals("distance"))
            {
                this.distance = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.distance, 9);
            }
        }
    }

    public String getStructureName()
    {
        return "Village";
    }

    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        boolean booRTGWorld = worldObj.getWorldInfo().getTerrainType() instanceof WorldTypeRTG;
        boolean booRTGChunkManager = worldObj.getBiomeProvider() instanceof WorldChunkManagerRTG;
        boolean canSpawnVillage = false;

        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= this.distance - 1;
        }

        if (chunkZ < 0)
        {
            chunkZ -= this.distance - 1;
        }

        int k = chunkX / this.distance;
        int l = chunkZ / this.distance;
        Random random = this.worldObj.setRandomSeed(k, l, 10387312);
        k = k * this.distance;
        l = l * this.distance;
        k = k + random.nextInt(this.distance - 8);
        l = l + random.nextInt(this.distance - 8);

        if (i == k && j == l) {

            int worldX = i * 16 + 8;
            int worldZ = j * 16 + 8;

            if (booRTGWorld && booRTGChunkManager) {

                WorldChunkManagerRTG cmr = (WorldChunkManagerRTG) worldObj.getBiomeProvider();
                RealisticBiomeBase realisticBiome = cmr.getBiomeDataAt(worldX, worldZ);

                if (realisticBiome.config.getPropertyById(BiomeConfig.allowVillagesId).valueBoolean) {
                    canSpawnVillage = true;
                }
            }
            else {

                canSpawnVillage = this.worldObj.getBiomeProvider().areBiomesViable(worldX, worldZ, 0, VILLAGE_SPAWN_BIOMES);
            }
        }

        return canSpawnVillage;
    }

    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new MapGenVillage.Start(this.worldObj, this.rand, chunkX, chunkZ, this.size);
    }

    public static class Start extends StructureStart
    {
        private boolean hasMoreThanTwoComponents;

        public Start()
        {
        }

        public Start(World worldIn, Random rand, int x, int z, int size)
        {
            super(x, z);
            List<StructureVillagePieces.PieceWeight> list = StructureVillagePieces.getStructureVillageWeightedPieceList(rand, size);
            StructureVillagePieces.Start structurevillagepieces$start = new StructureVillagePieces.Start(worldIn.getBiomeProvider(), 0, rand, (x << 4) + 2, (z << 4) + 2, list, size);
            this.components.add(structurevillagepieces$start);
            structurevillagepieces$start.buildComponent(structurevillagepieces$start, this.components, rand);
            List<StructureComponent> list1 = structurevillagepieces$start.pendingRoads;
            List<StructureComponent> list2 = structurevillagepieces$start.pendingHouses;

            while (!list1.isEmpty() || !list2.isEmpty())
            {
                if (list1.isEmpty())
                {
                    int i = rand.nextInt(list2.size());
                    StructureComponent structurecomponent = (StructureComponent)list2.remove(i);
                    structurecomponent.buildComponent(structurevillagepieces$start, this.components, rand);
                }
                else
                {
                    int j = rand.nextInt(list1.size());
                    StructureComponent structurecomponent2 = (StructureComponent)list1.remove(j);
                    structurecomponent2.buildComponent(structurevillagepieces$start, this.components, rand);
                }
            }

            this.updateBoundingBox();
            int k = 0;

            for (StructureComponent structurecomponent1 : this.components)
            {
                if (!(structurecomponent1 instanceof StructureVillagePieces.Road))
                {
                    ++k;
                }
            }

            this.hasMoreThanTwoComponents = k > 2;
        }

        public boolean isSizeableStructure()
        {
            return this.hasMoreThanTwoComponents;
        }

        public void writeToNBT(NBTTagCompound tagCompound)
        {
            super.writeToNBT(tagCompound);
            tagCompound.setBoolean("Valid", this.hasMoreThanTwoComponents);
        }

        public void readFromNBT(NBTTagCompound tagCompound)
        {
            super.readFromNBT(tagCompound);
            this.hasMoreThanTwoComponents = tagCompound.getBoolean("Valid");
        }
    }
}