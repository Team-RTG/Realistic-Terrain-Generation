package teamrtg.rtg.world.gen.structure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;
import net.minecraft.world.gen.structure.StructureStart;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.util.debug.Logger;
import teamrtg.rtg.world.WorldTypeRTG;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author topisani
 */
public class StructureOceanMonumentRTG extends StructureOceanMonument {
    private static final List<BiomeGenBase> WATER_BIOMES;
    private static final List<BiomeGenBase> SPAWN_BIOMES;
    private static final List<BiomeGenBase.SpawnListEntry> MONUMENT_ENEMIES;

    static {
        WATER_BIOMES = Arrays.asList(Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.RIVER, Biomes.FROZEN_OCEAN, Biomes.FROZEN_RIVER);
        SPAWN_BIOMES = Collections.singletonList(Biomes.DEEP_OCEAN);
        MONUMENT_ENEMIES = Lists.newArrayList();
        MONUMENT_ENEMIES.add(new BiomeGenBase.SpawnListEntry(EntityGuardian.class, 1, 2, 4));
    }

    private int spacing;
    private int separation;

    public StructureOceanMonumentRTG(Map<String, String> p_i45608_1_) {
        this();

        for (Object o : p_i45608_1_.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            if (entry.getKey().equals("spacing")) {
                this.spacing = MathHelper.parseIntWithDefaultAndMax((String) entry.getValue(), this.spacing, 1);
            } else if (entry.getKey().equals("separation")) {
                this.separation = MathHelper.parseIntWithDefaultAndMax((String) entry.getValue(), this.separation, 1);
            }
        }
    }

    public StructureOceanMonumentRTG() {
        this.spacing = 32;
        this.separation = 5;
    }

    @Override
    public String getStructureName() {
        return "Monument";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        if (!(this.worldObj.getWorldType() instanceof WorldTypeRTG)) return false;

        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0) {
            chunkX -= this.spacing - 1;
        }

        if (chunkZ < 0) {
            chunkZ -= this.spacing - 1;
        }

        int k = chunkX / this.spacing;
        int l = chunkZ / this.spacing;
        Random random = this.worldObj.setRandomSeed(k, l, 10387313);
        k = k * this.spacing;
        l = l * this.spacing;
        k = k + (random.nextInt(this.spacing - this.separation) + random.nextInt(this.spacing - this.separation)) / 2;
        l = l + (random.nextInt(this.spacing - this.separation) + random.nextInt(this.spacing - this.separation)) / 2;

        if (i == k && j == l) {
            BiomeGenBase bg = this.worldObj.getBiomeProvider().getBiomeGenerator(new BlockPos(i * 16 + 8, 64, j * 16 + 8), null);

            if (BiomeUtils.getId(bg) == BiomeUtils.getId(Biomes.DEEP_OCEAN)) {

                boolean flag = worldObj.getBiomeProvider().areBiomesViable(i * 16 + 8, j * 16 + 8, 29, WATER_BIOMES);

                if (flag) {
                    Logger.debug("Generated Ocean Monument at %s %s", i * 16 + 8, j * 16 + 8);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new StructureOceanMonument.StartMonument(this.worldObj, this.rand, chunkX, chunkZ);
    }

    @Override
    public List<BiomeGenBase.SpawnListEntry> getScatteredFeatureSpawnList() {
        return Collections.unmodifiableList(MONUMENT_ENEMIES);
    }

    public static class StartMonument extends StructureStart {
        private Set<ChunkCoordIntPair> processed = Sets.newHashSet();
        private boolean field_175790_d;

        public StartMonument() {
        }

        public StartMonument(World worldIn, Random random, int chunkX, int chunkZ) {
            super(chunkX, chunkZ);
            this.create(worldIn, random, chunkX, chunkZ);
        }

        private void create(World worldIn, Random random, int chunkX, int chunkZ) {
            random.setSeed(worldIn.getSeed());
            long i = random.nextLong();
            long j = random.nextLong();
            long k = (long) chunkX * i;
            long l = (long) chunkZ * j;
            random.setSeed(k ^ l ^ worldIn.getSeed());
            int i1 = chunkX * 16 + 8 - 29;
            int j1 = chunkZ * 16 + 8 - 29;
            EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(random);
            this.components.add(new StructureOceanMonumentPieces.MonumentBuilding(random, i1, j1, enumfacing));
            this.updateBoundingBox();
            this.field_175790_d = true;
        }

        @Override
        public void generateStructure(World worldIn, Random rand, StructureBoundingBox structurebb) {
            if (!this.field_175790_d) {
                this.components.clear();
                this.create(worldIn, rand, this.getChunkPosX(), this.getChunkPosZ());
            }

            super.generateStructure(worldIn, rand, structurebb);
        }

        @Override
        public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
            NBTTagList nbttaglist = new NBTTagList();

            for (ChunkCoordIntPair chunkcoordintpair : this.processed) {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setInteger("X", chunkcoordintpair.chunkXPos);
                nbttagcompound.setInteger("Z", chunkcoordintpair.chunkZPos);
                nbttaglist.appendTag(nbttagcompound);
            }

            tagCompound.setTag("Processed", nbttaglist);
        }

        @Override
        public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
            if (tagCompound.hasKey("Processed", 9)) {
                NBTTagList nbttaglist = tagCompound.getTagList("Processed", 10);

                for (int i = 0; i < nbttaglist.tagCount(); ++i) {
                    NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
                    this.processed.add(new ChunkCoordIntPair(nbttagcompound.getInteger("X"), nbttagcompound.getInteger("Z")));
                }
            }

        }

        @Override
        public boolean isValidForPostProcess(ChunkCoordIntPair pair) {
            return (!this.processed.contains(pair) && super.isValidForPostProcess(pair));
        }

        @Override
        public void notifyPostProcessAt(ChunkCoordIntPair pair) {
            super.notifyPostProcessAt(pair);
            this.processed.add(pair);
        }
    }
}
