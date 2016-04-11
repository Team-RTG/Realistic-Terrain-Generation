package teamrtg.rtg.world.gen.structure;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;
import net.minecraft.world.gen.structure.StructureStart;
import teamrtg.rtg.api.util.debug.Logger;
import teamrtg.rtg.api.mods.Mods;
import teamrtg.rtg.world.biome.BiomeProviderRTG;
import teamrtg.rtg.api.biome.RealisticBiomeBase;

import java.util.*;

import static teamrtg.rtg.mods.vanilla.biomes.RealisticBiomeVanillaBase.SpawnListEntry;

/**
 * @author topisani
 */
public class StructureOceanMonumentRTG extends StructureOceanMonument {
    public static final List<BiomeGenBase> field_175802_d;
    public static final List<BiomeGenBase> field_186134_b;
    private static final List<BiomeGenBase.SpawnListEntry> field_175803_h;

    static {
        field_175802_d = Arrays.asList(Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.RIVER, Biomes.FROZEN_OCEAN, Biomes.FROZEN_RIVER);
        field_186134_b = Arrays.asList(Biomes.DEEP_OCEAN);
        field_175803_h = Lists.newArrayList();
        field_175803_h.add(new SpawnListEntry(EntityGuardian.class, 1, 2, 4));
    }

    private int field_175800_f;
    private int field_175801_g;

    public StructureOceanMonumentRTG(Map<String, String> p_i45608_1_) {
        this();

        for (Object o : p_i45608_1_.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            if (((String) entry.getKey()).equals("spacing")) {
                this.field_175800_f = MathHelper.parseIntWithDefaultAndMax((String) entry.getValue(), this.field_175800_f, 1);
            } else if (((String) entry.getKey()).equals("separation")) {
                this.field_175801_g = MathHelper.parseIntWithDefaultAndMax((String) entry.getValue(), this.field_175801_g, 1);
            }
        }
    }

    public StructureOceanMonumentRTG() {
        this.field_175800_f = 32;
        this.field_175801_g = 5;
    }

    public String getStructureName() {
        return "Monument";
    }

    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0) {
            chunkX -= this.field_175800_f - 1;
        }

        if (chunkZ < 0) {
            chunkZ -= this.field_175800_f - 1;
        }

        int k = chunkX / this.field_175800_f;
        int l = chunkZ / this.field_175800_f;
        Random random = this.worldObj.setRandomSeed(k, l, 10387313);
        k = k * this.field_175800_f;
        l = l * this.field_175800_f;
        k = k + (random.nextInt(this.field_175800_f - this.field_175801_g) + random.nextInt(this.field_175800_f - this.field_175801_g)) / 2;
        l = l + (random.nextInt(this.field_175800_f - this.field_175801_g) + random.nextInt(this.field_175800_f - this.field_175801_g)) / 2;

        if (i == k && j == l) {
            BiomeGenBase bg = this.worldObj.getBiomeProvider().getBiomeGenerator(new BlockPos(i * 16 + 8, 64, j * 16 + 8), null);

            if (RealisticBiomeBase.getIdForBiome(bg) == Mods.VANILLA.biomes.DEEP_OCEAN.getID()) {

                boolean flag = this.areBiomesViable(i * 16 + 8, j * 16 + 8, 29);

                if (flag) {
                    Logger.debug("Generated Ocean Monument at %s %s", i * 16 + 8, j * 16 + 8);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * checks given Chunk's Biomes against List of allowed ones
     */
    public boolean areBiomesViable(int p_76940_1_, int p_76940_2_, int p_76940_3_) {
        IntCache.resetIntCache();
        int i = p_76940_1_ - p_76940_3_ >> 2;
        int j = p_76940_2_ - p_76940_3_ >> 2;
        int k = p_76940_1_ + p_76940_3_ >> 2;
        int l = p_76940_2_ + p_76940_3_ >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        BiomeProviderRTG wcm;
        try {
            wcm = (BiomeProviderRTG) worldObj.getBiomeProvider();
        } catch (ClassCastException e) {
            Logger.warn("This is not an RTG world, y u want 2 generate rtg Ocean Monuments?");
            return false;
        }
        int[] aint = wcm.getBiomesGens(i, j, i1, j1);

        try {
            for (int k1 = 0; k1 < i1 * j1; ++k1) {
                int biomeID = aint[k1];
                boolean flag = false;
                for (BiomeGenBase biome : field_175802_d) {
                    flag = (flag) || BiomeGenBase.getIdForBiome(biome) == biomeID;
                }
                if (!flag) return false;
            }

            return true;
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
            crashreportcategory.addCrashSection("x", Integer.valueOf(p_76940_1_));
            crashreportcategory.addCrashSection("z", Integer.valueOf(p_76940_2_));
            crashreportcategory.addCrashSection("radius", Integer.valueOf(p_76940_3_));
            throw new ReportedException(crashreport);
        }
    }

    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new StructureOceanMonument.StartMonument(this.worldObj, this.rand, chunkX, chunkZ);
    }

    public List<SpawnListEntry> getScatteredFeatureSpawnList() {
        return field_175803_h;
    }

    public static class StartMonument extends StructureStart {
        private Set<ChunkCoordIntPair> processed = Sets.newHashSet();
        private boolean field_175790_d;

        public StartMonument() {
        }

        public StartMonument(World worldIn, Random random, int chunkX, int chunkZ) {
            super(chunkX, chunkZ);
            this.func_175789_b(worldIn, random, chunkX, chunkZ);
        }

        private void func_175789_b(World worldIn, Random random, int chunkX, int chunkZ) {
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

        public void generateStructure(World worldIn, Random rand, StructureBoundingBox structurebb) {
            if (!this.field_175790_d) {
                this.components.clear();
                this.func_175789_b(worldIn, rand, this.getChunkPosX(), this.getChunkPosZ());
            }

            super.generateStructure(worldIn, rand, structurebb);
        }

        public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
            NBTTagList nbttaglist = new NBTTagList();
            Iterator var3 = this.processed.iterator();

            while (var3.hasNext()) {
                ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair) var3.next();
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setInteger("X", chunkcoordintpair.chunkXPos);
                nbttagcompound.setInteger("Z", chunkcoordintpair.chunkZPos);
                nbttaglist.appendTag(nbttagcompound);
            }

            tagCompound.setTag("Processed", nbttaglist);
        }

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

        public boolean func_175788_a(ChunkCoordIntPair pair) {
            return this.processed.contains(pair) ? false : super.func_175788_a(pair);
        }

        public void func_175787_b(ChunkCoordIntPair pair) {
            super.func_175787_b(pair);
            this.processed.add(pair);
        }
    }
}
