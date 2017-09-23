package rtg.world.gen.structure;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import javax.annotation.Nonnull;

import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import rtg.api.RTGAPI;
import rtg.api.dimension.DimensionManagerRTG;
import rtg.api.util.Logger;
import rtg.world.biome.BiomeProviderRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings({"WeakerAccess", "unused"})
public class MapGenVillageRTG extends MapGenVillage
{
    public static List<Biome> VILLAGE_SPAWN_BIOMES = Arrays.asList(Biomes.PLAINS, Biomes.DESERT, Biomes.SAVANNA, Biomes.TAIGA);
    private int size;
    private int distance;
    private final int minTownSeparation;

    public MapGenVillageRTG() {
        this.size = RTGAPI.config().VILLAGE_SIZE.get(); // Vanilla = 0
        this.distance = RTGAPI.config().MAX_DISTANCE_VILLAGES.get(); // Vanille = 32
        this.minTownSeparation = RTGAPI.config().MIN_DISTANCE_VILLAGES.get(); // Vanilla = 8
    }

    public MapGenVillageRTG(Map<String, String> map) {
        this();

        for (Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().equals("size")) {
                this.size = MathHelper.getInt(entry.getValue(), this.size, 0);
            }
            else if (entry.getKey().equals("distance")) {
                this.distance = MathHelper.getInt(entry.getValue(), this.distance, 9);
            }
        }
    }

    @Override @Nonnull
    public String getStructureName() {
        return "Village";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        boolean canSpawnVillage = false;
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0) chunkX -= this.distance - 1;
        if (chunkZ < 0) chunkZ -= this.distance - 1;

        int k = chunkX / this.distance;
        int l = chunkZ / this.distance;
        Random random = this.world.setRandomSeed(k, l, 10387312);
        k = k * this.distance;
        l = l * this.distance;
        k = k + random.nextInt(this.distance - 8);
        l = l + random.nextInt(this.distance - 8);

        if (i == k && j == l) {

            boolean booRTGWorld = DimensionManagerRTG.isValidDimension(world.provider.getDimension());
            boolean booRTGChunkManager = world.getBiomeProvider() instanceof BiomeProviderRTG;

            int worldX = i * 16 + 8;
            int worldZ = j * 16 + 8;

            if (booRTGWorld && booRTGChunkManager) {

                BiomeProviderRTG cmr = (BiomeProviderRTG) world.getBiomeProvider();
                //Why are we flipping XZ here? No idea, but it works. - Pink
                RealisticBiomeBase realisticBiome = cmr.getBiomeDataAt(worldX, worldZ);

                if (realisticBiome.getConfig().ALLOW_VILLAGES.get()) {
                    canSpawnVillage = true;
                    Logger.debug("Potential village in %s at %d %d", realisticBiome.baseBiome.getBiomeName(), worldX, worldZ);
                }
            }
            else canSpawnVillage = this.world.getBiomeProvider().areBiomesViable(worldX, worldZ, 0, VILLAGE_SPAWN_BIOMES);
        }
        return canSpawnVillage;
    }

    public static class Start extends StructureStart
    {
        private boolean hasMoreThanTwoComponents;

        public Start() {}

        public Start(World worldIn, Random rand, int x, int z, int size) {
            super(x, z);
            List<StructureVillagePieces.PieceWeight> list = StructureVillagePieces.getStructureVillageWeightedPieceList(rand, size);
            StructureVillagePieces.Start structurevillagepieces$start = new StructureVillagePieces.Start(worldIn.getBiomeProvider(), 0, rand, (x << 4) + 2, (z << 4) + 2, list, size);
            this.components.add(structurevillagepieces$start);
            structurevillagepieces$start.buildComponent(structurevillagepieces$start, this.components, rand);
            List<StructureComponent> list1 = structurevillagepieces$start.pendingRoads;
            List<StructureComponent> list2 = structurevillagepieces$start.pendingHouses;

            while (!list1.isEmpty() || !list2.isEmpty()) {
                if (list1.isEmpty()) {
                    int i = rand.nextInt(list2.size());
                    StructureComponent structurecomponent = list2.remove(i);
                    structurecomponent.buildComponent(structurevillagepieces$start, this.components, rand);
                }
                else {
                    int j = rand.nextInt(list1.size());
                    StructureComponent structurecomponent2 = list1.remove(j);
                    structurecomponent2.buildComponent(structurevillagepieces$start, this.components, rand);
                }
            }

            this.updateBoundingBox();
            int k = 0;

            for (StructureComponent structurecomponent1 : this.components) {
                if (!(structurecomponent1 instanceof StructureVillagePieces.Road)) ++k;
            }
            this.hasMoreThanTwoComponents = k > 2;
        }

        @Override
        public boolean isSizeableStructure() {
            return this.hasMoreThanTwoComponents;
        }

        @Override
        public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
            tagCompound.setBoolean("Valid", this.hasMoreThanTwoComponents);
        }

        @Override
        public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
            this.hasMoreThanTwoComponents = tagCompound.getBoolean("Valid");
        }
    }
}