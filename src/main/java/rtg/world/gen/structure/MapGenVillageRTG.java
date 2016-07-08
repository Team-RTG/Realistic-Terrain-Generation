package rtg.world.gen.structure;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Road;
import rtg.api.biome.BiomeConfig;
import rtg.config.rtg.ConfigRTG;
import rtg.util.Logger;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

@SuppressWarnings({"unchecked", "unused"})
public class MapGenVillageRTG extends MapGenVillage {

    private int terrainType = ConfigRTG.villageSize;
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
        int minDistanceVillages = ConfigRTG.minDistanceVillages;
        int maxDistanceVillages = ConfigRTG.maxDistanceVillages;

        // Make sure these values are always greater than 0.
        if (minDistanceVillages < 1) {
            minDistanceVillages = 8;
            Logger.warn("Config warning: Minimum distance between villages must be greater than 0. Reverting to vanilla value (%d).", minDistanceVillages);
        }
        if (maxDistanceVillages < 1) {
            maxDistanceVillages = 32;
            Logger.warn("Config warning: Maximum distance between villages must be greater than 0. Reverting to vanilla value (%d).", maxDistanceVillages);
        }

        // If the min is greater than the max, then revert to vanilla values.
        if (minDistanceVillages > maxDistanceVillages) {
            minDistanceVillages = 8;
            maxDistanceVillages = 32;
            Logger.warn("Config warning: Minimum distance between villages must be less than maximum distance between villages. Reverting to vanilla values.");
        }
        // Bad things happen when min & max are the same.
        else if (minDistanceVillages == maxDistanceVillages) {
            maxDistanceVillages++;
            Logger.warn("Config warning: Maximum distance between villages must be greater than minimum distance between villages. Increasing maximum to %d.", maxDistanceVillages);
        }

        Logger.debug("minDistanceVillages = %d", minDistanceVillages);
        Logger.debug("maxDistanceVillages = %d", maxDistanceVillages);

        this.field_82665_g = maxDistanceVillages;
        this.field_82666_h = minDistanceVillages;
    }

    @Override
    public String func_143025_a() {
        return "Village";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int par1, int par2) {
        boolean booRTGWorld = (worldObj.getWorldInfo().getTerrainType() instanceof WorldTypeRTG);
        boolean booRTGChunkManager = (worldObj.getWorldChunkManager() instanceof WorldChunkManagerRTG);
        boolean canSpawnVillage = false;

        int k = par1;
        int l = par2;

        if (par1 < 0) {
            par1 -= this.field_82665_g - 1;
        }

        if (par2 < 0) {
            par2 -= this.field_82665_g - 1;
        }

        int i1 = par1 / this.field_82665_g;
        int j1 = par2 / this.field_82665_g;
        Random random = this.worldObj.setRandomSeed(i1, j1, 10387312);
        i1 *= this.field_82665_g;
        j1 *= this.field_82665_g;
        i1 += random.nextInt(this.field_82665_g - this.field_82666_h);
        j1 += random.nextInt(this.field_82665_g - this.field_82666_h);

        if (k == i1 && l == j1) {
            if (booRTGWorld && booRTGChunkManager) {

                WorldChunkManagerRTG cmr = (WorldChunkManagerRTG) worldObj.getWorldChunkManager();
                int worldX = k * 16 + 8;
                int worldZ = l * 16 + 8;
                RealisticBiomeBase realisticBiome = cmr.getBiomeDataAt(worldX, worldZ);

                if (realisticBiome.config.getPropertyById(BiomeConfig.allowVillagesId).valueBoolean) {
                    canSpawnVillage = true;
                }
            } else {

                canSpawnVillage = this.worldObj.getWorldChunkManager().areBiomesViable(k * 16 + 8, l * 16 + 8, 0, villageSpawnBiomes);
            }
        }

        return canSpawnVillage;
    }

    @Override
    protected StructureStart getStructureStart(int par1, int par2) {
        return new MapGenVillageRTG.Start(this.worldObj, this.rand, par1, par2, this.terrainType);
    }

    public static class Start extends StructureStart {
        /** well ... thats what it does */
        private boolean hasMoreThanTwoComponents;

        public Start() {}

        Start(World p_i2092_1_, Random p_i2092_2_, int p_i2092_3_, int p_i2092_4_, int p_i2092_5_) {
            super(p_i2092_3_, p_i2092_4_);
            List list = StructureVillagePieces.getStructureVillageWeightedPieceList(p_i2092_2_, p_i2092_5_);
            StructureVillagePieces.Start start = new StructureVillagePieces.Start(p_i2092_1_.getWorldChunkManager(), 0, p_i2092_2_, (p_i2092_3_ << 4) + 2, (p_i2092_4_ << 4) + 2, list, p_i2092_5_);
            this.components.add(start);
            start.buildComponent(start, this.components, p_i2092_2_);
            List list1 = start.field_74930_j;
            List list2 = start.field_74932_i;
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

            for (Object component : this.components) {
                StructureComponent structurecomponent1 = (StructureComponent) component;
                if (!(structurecomponent1 instanceof Road)) ++l;
            }

            this.hasMoreThanTwoComponents = l > 2;
        }

        public void func_143022_a(NBTTagCompound p_143022_1_) {
            super.func_143022_a(p_143022_1_);
            p_143022_1_.setBoolean("Valid", this.hasMoreThanTwoComponents);
        }

        public void func_143017_b(NBTTagCompound p_143017_1_) {
            super.func_143017_b(p_143017_1_);
            this.hasMoreThanTwoComponents = p_143017_1_.getBoolean("Valid");
        }

        /**
         * currently only defined for Villages, returns true if Village has more than 2 non-road components
         */
        public boolean isSizeableStructure() {
            return this.hasMoreThanTwoComponents;
        }
    }
}