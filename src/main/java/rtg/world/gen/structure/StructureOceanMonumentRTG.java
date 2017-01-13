package rtg.world.gen.structure;

import java.util.*;
import java.util.Map.Entry;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;
import net.minecraft.world.gen.structure.StructureStart;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import rtg.api.RTGAPI;
import rtg.util.Logger;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.BiomeProviderRTG;

public class StructureOceanMonumentRTG extends StructureOceanMonument
{
    private int spacing;
    private int separation;
    public static final List<Biome> WATER_BIOMES = Arrays.<Biome>asList(new Biome[] {Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.RIVER, Biomes.FROZEN_OCEAN, Biomes.FROZEN_RIVER});
    public static final List<Biome> SPAWN_BIOMES = Arrays.<Biome>asList(new Biome[] {Biomes.DEEP_OCEAN});
    private static final List<Biome.SpawnListEntry> MONUMENT_ENEMIES = Lists.<Biome.SpawnListEntry>newArrayList();

    public StructureOceanMonumentRTG()
    {
        this.spacing = RTGAPI.config().OCEAN_MONUMENT_SPACING.get(); // Vanilla = 32
        this.separation = RTGAPI.config().OCEAN_MONUMENT_SEPARATION.get(); // Vanilla = 5
    }

    public StructureOceanMonumentRTG(Map<String, String> p_i45608_1_)
    {
        this();

        for (Entry<String, String> entry : p_i45608_1_.entrySet())
        {
            if (((String)entry.getKey()).equals("spacing"))
            {
                this.spacing = MathHelper.getInt((String)entry.getValue(), this.spacing, 1);
            }
            else if (((String)entry.getKey()).equals("separation"))
            {
                this.separation = MathHelper.getInt((String)entry.getValue(), this.separation, 1);
            }
        }
    }

    @Override
    public String getStructureName()
    {
        return "Monument";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= this.spacing - 1;
        }

        if (chunkZ < 0)
        {
            chunkZ -= this.spacing - 1;
        }

        int k = chunkX / this.spacing;
        int l = chunkZ / this.spacing;
        Random random = this.world.setRandomSeed(k, l, 10387313);
        k = k * this.spacing;
        l = l * this.spacing;
        k = k + (random.nextInt(this.spacing - this.separation) + random.nextInt(this.spacing - this.separation)) / 2;
        l = l + (random.nextInt(this.spacing - this.separation) + random.nextInt(this.spacing - this.separation)) / 2;

        if (i == k && j == l)
        {
            int x = i * 16 + 8;
            int z = j * 16 + 8;

            if (this.world.getBiomeProvider().getBiome(new BlockPos(x, 64, z), Biomes.DEFAULT) != Biomes.DEEP_OCEAN) {
                return false;
            }

            if (!this.areBiomesViable(x, z, 16, SPAWN_BIOMES))
            {
                return false;
            }

            boolean flag = this.areBiomesViable(x, z, 29, WATER_BIOMES);

            if (flag)
            {
                Logger.debug("Ocean monument candidate at %d, %d", x, z);
                return true;
            }
        }

        return false;
    }

    public boolean areBiomesViable(int x, int z, int radius, List<Biome> allowed)
    {
        // Are we in an RTG world?
        if (!(this.world.getWorldType() instanceof WorldTypeRTG)) {
            //Logger.debug("Could not generate ocean monument. This is not an RTG world.");
            return false;
        }

        // Do we have RTG's chunk manager?
        if (!(this.world.getBiomeProvider() instanceof BiomeProviderRTG)) {
            //Logger.debug("Could not generate ocean monument. Incompatible chunk manager detected.");
            return false;
        }

        IntCache.resetIntCache();
        int i = x - radius >> 2;
        int j = z - radius >> 2;
        int k = x + radius >> 2;
        int l = z + radius >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;

        BiomeProviderRTG cmr = (BiomeProviderRTG) this.world.getBiomeProvider();
        int[] aint = cmr.getBiomesGens(i, j, i1, j1);

        try
        {
            for (int k1 = 0; k1 < i1 * j1; ++k1)
            {
                Biome biome = Biome.getBiome(aint[k1]);

                if (!allowed.contains(biome))
                {
                    //Logger.debug("Could not generate ocean monument. Biome (%d) nearby.", BiomeUtils.getId(biome));
                    return false;
                }
            }

            return true;
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
            crashreportcategory.addCrashSection("Layer", Arrays.toString(aint));
            crashreportcategory.addCrashSection("x", Integer.valueOf(x));
            crashreportcategory.addCrashSection("z", Integer.valueOf(z));
            crashreportcategory.addCrashSection("radius", Integer.valueOf(radius));
            crashreportcategory.addCrashSection("allowed", allowed);
            throw new ReportedException(crashreport);
        }
    }

    @Override
    public BlockPos getClosestStrongholdPos(World worldIn, BlockPos pos, boolean findUnexplored)
    {
        this.world = worldIn;
        return findNearestStructurePosBySpacing(worldIn, this, pos, this.spacing, this.separation, 10387313, true, 100, findUnexplored);
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new StructureOceanMonumentRTG.StartMonument(this.world, this.rand, chunkX, chunkZ);
    }

    @Override
    public List<Biome.SpawnListEntry> getScatteredFeatureSpawnList()
    {
        return MONUMENT_ENEMIES;
    }

    static
    {
        MONUMENT_ENEMIES.add(new Biome.SpawnListEntry(EntityGuardian.class, 1, 2, 4));
    }

    public static class StartMonument extends StructureStart
    {
        private final Set<ChunkPos> processed = Sets.<ChunkPos>newHashSet();
        private boolean wasCreated;

        public StartMonument()
        {
        }

        public StartMonument(World worldIn, Random random, int chunkX, int chunkZ)
        {
            super(chunkX, chunkZ);
            this.create(worldIn, random, chunkX, chunkZ);
        }

        private void create(World worldIn, Random random, int chunkX, int chunkZ)
        {
            random.setSeed(worldIn.getSeed());
            long i = random.nextLong();
            long j = random.nextLong();
            long k = (long)chunkX * i;
            long l = (long)chunkZ * j;
            random.setSeed(k ^ l ^ worldIn.getSeed());
            int i1 = chunkX * 16 + 8 - 29;
            int j1 = chunkZ * 16 + 8 - 29;
            EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(random);
            this.components.add(new StructureOceanMonumentPieces.MonumentBuilding(random, i1, j1, enumfacing));
            this.updateBoundingBox();
            this.wasCreated = true;
        }

        @Override
        public void generateStructure(World worldIn, Random rand, StructureBoundingBox structurebb)
        {
            if (!this.wasCreated)
            {
                this.components.clear();
                this.create(worldIn, rand, this.getChunkPosX(), this.getChunkPosZ());
            }

            super.generateStructure(worldIn, rand, structurebb);
        }

        @Override
        public boolean isValidForPostProcess(ChunkPos pair)
        {
            return this.processed.contains(pair) ? false : super.isValidForPostProcess(pair);
        }

        @Override
        public void notifyPostProcessAt(ChunkPos pair)
        {
            super.notifyPostProcessAt(pair);
            this.processed.add(pair);
        }

        @Override
        public void writeToNBT(NBTTagCompound tagCompound)
        {
            super.writeToNBT(tagCompound);
            NBTTagList nbttaglist = new NBTTagList();

            for (ChunkPos chunkpos : this.processed)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setInteger("X", chunkpos.chunkXPos);
                nbttagcompound.setInteger("Z", chunkpos.chunkZPos);
                nbttaglist.appendTag(nbttagcompound);
            }

            tagCompound.setTag("Processed", nbttaglist);
        }

        @Override
        public void readFromNBT(NBTTagCompound tagCompound)
        {
            super.readFromNBT(tagCompound);

            if (tagCompound.hasKey("Processed", 9))
            {
                NBTTagList nbttaglist = tagCompound.getTagList("Processed", 10);

                for (int i = 0; i < nbttaglist.tagCount(); ++i)
                {
                    NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
                    this.processed.add(new ChunkPos(nbttagcompound.getInteger("X"), nbttagcompound.getInteger("Z")));
                }
            }
        }
    }
}