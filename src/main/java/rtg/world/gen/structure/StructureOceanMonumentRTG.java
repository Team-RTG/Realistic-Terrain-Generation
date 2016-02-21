package rtg.world.gen.structure;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBase;

import java.util.*;

/**
 * Created by topisani on 2/20/16.
 */
public class StructureOceanMonumentRTG extends StructureOceanMonument {
    private int field_175800_f;
    private int field_175801_g;
    public static final List<BiomeGenBase> biomes = Arrays.<BiomeGenBase>asList(new BiomeGenBase[]{RealisticBiomeVanillaBase.ocean, RealisticBiomeVanillaBase.deepOcean, RealisticBiomeVanillaBase.river, RealisticBiomeVanillaBase.frozenOcean, RealisticBiomeVanillaBase.frozenRiver});
    private static final List<BiomeGenBase.SpawnListEntry> field_175803_h = Lists.<BiomeGenBase.SpawnListEntry>newArrayList();

    public StructureOceanMonumentRTG() {
        this.field_175800_f = 32;
        this.field_175801_g = 5 ;
    }

    public StructureOceanMonumentRTG(Map<String, String> p_i45608_1_) {
        this();

        for (Map.Entry<String, String> entry : p_i45608_1_.entrySet()) {
            if (((String) entry.getKey()).equals("spacing")) {
                this.field_175800_f = MathHelper.parseIntWithDefaultAndMax((String) entry.getValue(), this.field_175800_f, 1);
            } else if (((String) entry.getKey()).equals("separation")) {
                this.field_175801_g = MathHelper.parseIntWithDefaultAndMax((String) entry.getValue(), this.field_175801_g, 1);
            }
        }
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
            BiomeGenBase bg = this.worldObj.getWorldChunkManager().getBiomeGenerator(new BlockPos(i * 16 + 8, 64, j * 16 + 8), (BiomeGenBase) null);

            if (bg.biomeID == RealisticBiomeVanillaBase.deepOcean.biomeID || bg.biomeID == RealisticBiomeVanillaBase.ocean.biomeID ) {

                boolean flag = this.areBiomesViable(i * 16 + 8, j * 16 + 8, 29, biomes);

                if (flag) {
                    FMLLog.log(Level.INFO, "Generated Ocean Monument at %s %s", i * 16 + 8, j * 16 + 8);
                    return true;
                }
            }
        }

        return false;
    }
    /**
     * checks given Chunk's Biomes against List of allowed ones
     */
    public boolean areBiomesViable(int p_76940_1_, int p_76940_2_, int p_76940_3_, List<BiomeGenBase> p_76940_4_)
    {
        IntCache.resetIntCache();
        int i = p_76940_1_ - p_76940_3_ >> 2;
        int j = p_76940_2_ - p_76940_3_ >> 2;
        int k = p_76940_1_ + p_76940_3_ >> 2;
        int l = p_76940_2_ + p_76940_3_ >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        WorldChunkManagerRTG wcm = (WorldChunkManagerRTG) worldObj.getWorldChunkManager();
        int[] aint = wcm.getBiomesGens(i,j,i1,j1);

        try
        {
            for (int k1 = 0; k1 < i1 * j1; ++k1)
            {
                BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[k1]);

                if (!p_76940_4_.contains(biomegenbase))
                {
                    return false;
                }
            }

            return true;
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
            crashreportcategory.addCrashSection("x", Integer.valueOf(p_76940_1_));
            crashreportcategory.addCrashSection("z", Integer.valueOf(p_76940_2_));
            crashreportcategory.addCrashSection("radius", Integer.valueOf(p_76940_3_));
            crashreportcategory.addCrashSection("allowed", p_76940_4_);
            throw new ReportedException(crashreport);
        }
    }

    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new StructureOceanMonument.StartMonument(this.worldObj, this.rand, chunkX, chunkZ);
    }

    public List<BiomeGenBase.SpawnListEntry> func_175799_b() {
        return field_175803_h;
    }

    static {
        field_175803_h.add(new BiomeGenBase.SpawnListEntry(EntityGuardian.class, 1, 2, 4));
    }

    public static class StartMonument extends StructureStart {
        private Set<ChunkCoordIntPair> field_175791_c = Sets.<ChunkCoordIntPair>newHashSet();
        private boolean field_175790_d;

        public StartMonument() {
        }

        public StartMonument(World worldIn, Random p_i45607_2_, int p_i45607_3_, int p_i45607_4_) {
            super(p_i45607_3_, p_i45607_4_);
            this.func_175789_b(worldIn, p_i45607_2_, p_i45607_3_, p_i45607_4_);
        }

        private void func_175789_b(World worldIn, Random p_175789_2_, int p_175789_3_, int p_175789_4_) {
            p_175789_2_.setSeed(worldIn.getSeed());
            long i = p_175789_2_.nextLong();
            long j = p_175789_2_.nextLong();
            long k = (long) p_175789_3_ * i;
            long l = (long) p_175789_4_ * j;
            p_175789_2_.setSeed(k ^ l ^ worldIn.getSeed());
            int i1 = p_175789_3_ * 16 + 8 - 29;
            int j1 = p_175789_4_ * 16 + 8 - 29;
            EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(p_175789_2_);
            this.components.add(new StructureOceanMonumentPieces.MonumentBuilding(p_175789_2_, i1, j1, enumfacing));
            this.updateBoundingBox();
            this.field_175790_d = true;
        }

        /**
         * Keeps iterating Structure Pieces and spawning them until the checks tell it to stop
         */
        public void generateStructure(World worldIn, Random rand, StructureBoundingBox structurebb) {
            if (!this.field_175790_d) {
                this.components.clear();
                this.func_175789_b(worldIn, rand, this.getChunkPosX(), this.getChunkPosZ());
            }

            super.generateStructure(worldIn, rand, structurebb);
        }

        public boolean func_175788_a(ChunkCoordIntPair pair) {
            return this.field_175791_c.contains(pair) ? false : super.func_175788_a(pair);
        }

        public void func_175787_b(ChunkCoordIntPair pair) {
            super.func_175787_b(pair);
            this.field_175791_c.add(pair);
        }

        public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
            NBTTagList nbttaglist = new NBTTagList();

            for (ChunkCoordIntPair chunkcoordintpair : this.field_175791_c) {
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
                    this.field_175791_c.add(new ChunkCoordIntPair(nbttagcompound.getInteger("X"), nbttagcompound.getInteger("Z")));
                }
            }
        }
    }
}
