package teamrtg.rtg.core.world.gen.structure;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Road;
import teamrtg.rtg.api.world.biome.RTGBiome;
import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.core.world.WorldTypeRTG;
import teamrtg.rtg.core.world.BiomeProviderRTG;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class MapGenVillageRTG extends MapGenVillage {
    private int terrainType = Mods.RTG.config.VILLAGE_SIZE.get();
    private int field_82665_g;
    private int field_82666_h;

    public MapGenVillageRTG(Map par1Map) {
        this();

        for (Object o : par1Map.entrySet()) {
            Entry entry = (Entry) o;

            if (entry.getKey().equals("size")) {
                this.terrainType = MathHelper.parseIntWithDefaultAndMax((String) entry.getValue(), this.terrainType, 0);
            } else if (entry.getKey().equals("distance")) {
                this.field_82665_g = MathHelper.parseIntWithDefaultAndMax((String) entry.getValue(), this.field_82665_g, this.field_82666_h + 1);
            }
        }
    }

    public MapGenVillageRTG() {
        int minDistanceVillages = Mods.RTG.config.MIN_DISTANCE_VILLAGES.get();
        int maxDistanceVillages = Mods.RTG.config.MAX_DISTANCE_VILLAGES.get();

        minDistanceVillages = (minDistanceVillages > maxDistanceVillages) ? maxDistanceVillages : minDistanceVillages;

        this.field_82665_g = maxDistanceVillages;
        this.field_82666_h = minDistanceVillages;
    }

    @Override
    public String getStructureName() {
        return "Village";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        boolean booRTGWorld = (worldObj.getWorldInfo().getTerrainType() instanceof WorldTypeRTG);
        boolean booRTGChunkManager = (worldObj.getBiomeProvider() instanceof BiomeProviderRTG);
        boolean canSpawnVillage = false;

        int k = chunkX;
        int l = chunkZ;

        if (chunkX < 0) {
            chunkX -= this.field_82665_g - 1;
        }

        if (chunkZ < 0) {
            chunkZ -= this.field_82665_g - 1;
        }

        int i1 = chunkX / this.field_82665_g;
        int j1 = chunkZ / this.field_82665_g;
        Random random = this.worldObj.setRandomSeed(i1, j1, 10387312);
        i1 *= this.field_82665_g;
        j1 *= this.field_82665_g;
        i1 += random.nextInt(this.field_82665_g - this.field_82666_h);
        j1 += random.nextInt(this.field_82665_g - this.field_82666_h);

        if (k == i1 && l == j1) {
            if (booRTGWorld && booRTGChunkManager) {

                BiomeProviderRTG cmr = (BiomeProviderRTG) worldObj.getBiomeProvider();
                int worldX = k * 16 + 8;
                int worldZ = l * 16 + 8;
                RTGBiome realisticBiome = cmr.getRTGBiomeAt(worldX, worldZ);

                if (realisticBiome.getConfig().ALLOW_VILLAGES.get()) {
                    canSpawnVillage = true;
                }
            } else {

                canSpawnVillage = this.worldObj.getBiomeProvider().areBiomesViable(k * 16 + 8, l * 16 + 8, 0, VILLAGE_SPAWN_BIOMES);
            }
        }

        return canSpawnVillage;
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new MapGenVillageRTG.Start(this.worldObj, this.rand, chunkX, chunkZ, this.terrainType);
    }

    public static class Start extends StructureStart {
        /**
         * well ... thats what it does
         */
        private boolean hasMoreThanTwoComponents;

        public Start() {
        }

        Start(World p_i2092_1_, Random p_i2092_2_, int p_i2092_3_, int p_i2092_4_, int p_i2092_5_) {
            super(p_i2092_3_, p_i2092_4_);
            List list = StructureVillagePieces.getStructureVillageWeightedPieceList(p_i2092_2_, p_i2092_5_);
            StructureVillagePieces.Start start = new StructureVillagePieces.Start(p_i2092_1_.getBiomeProvider(), 0, p_i2092_2_, (p_i2092_3_ << 4) + 2, (p_i2092_4_ << 4) + 2, list, p_i2092_5_);
            this.components.add(start);
            start.buildComponent(start, this.components, p_i2092_2_);
            List list1 = start.pendingRoads;
            List list2 = start.pendingHouses;
            int l;

            while (!list1.isEmpty() || !list2.isEmpty()) {
                StructureComponent structurecomponent;

                if (list1.isEmpty()) {
                    l = p_i2092_2_.nextInt(list2.size());
                    structurecomponent = (StructureComponent) list2.remove(l);
                    structurecomponent.buildComponent(start, this.components, p_i2092_2_);
                } else {
                    l = p_i2092_2_.nextInt(list1.size());
                    structurecomponent = (StructureComponent) list1.remove(l);
                    structurecomponent.buildComponent(start, this.components, p_i2092_2_);
                }
            }

            this.updateBoundingBox();
            l = 0;

            for (Object structurecomponent1 : this.components) {
                if (!(structurecomponent1 instanceof Road)) {
                    ++l;
                }
            }

            this.hasMoreThanTwoComponents = l > 2;
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

        /**
         * currently only defined for Villages, returns true if Village has more than 2 non-road components
         */
        @Override
        public boolean isSizeableStructure() {
            return this.hasMoreThanTwoComponents;
        }
    }
}