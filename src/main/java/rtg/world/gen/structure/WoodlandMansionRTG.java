package rtg.world.gen.structure;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkProviderOverworld;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraft.world.gen.structure.*;

import com.google.common.collect.Lists;

import rtg.api.RTGAPI;
import rtg.util.Logger;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.BiomeProviderRTG;

public class WoodlandMansionRTG extends WoodlandMansion
{
    private final int featureSpacing;
    private final int minFeatureSeparation;
    public static final List<Biome> ALLOWED_BIOMES = Arrays.<Biome>asList(new Biome[] {Biomes.ROOFED_FOREST, Biomes.MUTATED_ROOFED_FOREST});
    private final ChunkProviderOverworld provider;

    public WoodlandMansionRTG(ChunkProviderOverworld chunkProviderOverworld)
    {
        super(chunkProviderOverworld);
        this.provider = chunkProviderOverworld;

        this.featureSpacing = RTGAPI.config().WOODLAND_MANSION_SPACING.get(); // Vanilla = 80
        this.minFeatureSeparation = RTGAPI.config().WOODLAND_MANSION_SEPARATION.get(); // Vanilla = 20
    }

    public String getStructureName()
    {
        return "Mansion";
    }

    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            i = chunkX - 79;
        }

        if (chunkZ < 0)
        {
            j = chunkZ - 79;
        }

        int k = i / this.featureSpacing;
        int l = j / this.featureSpacing;
        Random random = this.world.setRandomSeed(k, l, 10387319);
        k = k * this.featureSpacing;
        l = l * this.featureSpacing;
        k = k + (random.nextInt(60) + random.nextInt(60)) / 2;
        l = l + (random.nextInt(60) + random.nextInt(60)) / 2;

        if (chunkX == k && chunkZ == l)
        {
            int x = chunkX * 16 + 8;
            int z = chunkZ * 16 + 8;

            boolean flag = this.areBiomesViable(x, z, 32, ALLOWED_BIOMES);

            if (flag)
            {
                Logger.debug("Woodland mansion candidate at %d, %d", x, z);
                return true;
            }
        }

        return false;
    }

    public boolean areBiomesViable(int x, int z, int radius, List<Biome> allowed)
    {
        // Are we in an RTG world?
        if (!(this.world.getWorldType() instanceof WorldTypeRTG)) {
            //Logger.debug("Could not generate woodland mansion. This is not an RTG world.");
            return false;
        }

        // Do we have RTG's chunk manager?
        if (!(this.world.getBiomeProvider() instanceof BiomeProviderRTG)) {
            //Logger.debug("Could not generate woodland mansion. Incompatible chunk manager detected.");
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
                    //Logger.debug("Could not generate woodland mansion. Biome (%d) nearby.", BiomeUtils.getId(biome));
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

    public BlockPos getClosestStrongholdPos(World worldIn, BlockPos pos, boolean findUnexplored)
    {
        this.world = worldIn;
        BiomeProvider biomeprovider = worldIn.getBiomeProvider();
        return biomeprovider.isFixedBiome() && biomeprovider.getFixedBiome() != Biomes.ROOFED_FOREST ? null : findNearestStructurePosBySpacing(worldIn, this, pos, this.featureSpacing, this.minFeatureSeparation, 10387319, true, 100, findUnexplored);
    }

    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new WoodlandMansionRTG.Start(this.world, this.provider, this.rand, chunkX, chunkZ);
    }

    public static class Start extends StructureStart
    {
        private boolean isValid;

        public Start()
        {
        }

        public Start(World p_i47235_1_, ChunkProviderOverworld p_i47235_2_, Random p_i47235_3_, int p_i47235_4_, int p_i47235_5_)
        {
            super(p_i47235_4_, p_i47235_5_);
            this.create(p_i47235_1_, p_i47235_2_, p_i47235_3_, p_i47235_4_, p_i47235_5_);
        }

        private void create(World world, ChunkProviderOverworld chunkProvider, Random rand, int chunkX, int chunkZ)
        {
            Rotation rotation = Rotation.values()[rand.nextInt(Rotation.values().length)];
            ChunkPrimer chunkprimer = new ChunkPrimer();
            chunkProvider.setBlocksInChunk(chunkX, chunkZ, chunkprimer);
            int i = 5;
            int j = 5;

            if (rotation == Rotation.CLOCKWISE_90)
            {
                i = -5;
            }
            else if (rotation == Rotation.CLOCKWISE_180)
            {
                i = -5;
                j = -5;
            }
            else if (rotation == Rotation.COUNTERCLOCKWISE_90)
            {
                j = -5;
            }

            int k = chunkprimer.findGroundBlockIdx(7, 7);
            int l = chunkprimer.findGroundBlockIdx(7, 7 + j);
            int i1 = chunkprimer.findGroundBlockIdx(7 + i, 7);
            int j1 = chunkprimer.findGroundBlockIdx(7 + i, 7 + j);
            int k1 = Math.min(Math.min(k, l), Math.min(i1, j1));

            if (k1 < 60)
            {
                this.isValid = false;
            }
            else
            {
                BlockPos blockpos = new BlockPos(chunkX * 16 + 8, k1 + 1, chunkZ * 16 + 8);
                List<WoodlandMansionPieces.MansionTemplate> list = Lists.<WoodlandMansionPieces.MansionTemplate>newLinkedList();
                WoodlandMansionPieces.generateMansion(world.getSaveHandler().getStructureTemplateManager(), blockpos, rotation, list, rand);
                this.components.addAll(list);
                this.updateBoundingBox();
                this.isValid = true;
            }
        }

        public void generateStructure(World worldIn, Random rand, StructureBoundingBox structurebb)
        {
            super.generateStructure(worldIn, rand, structurebb);
            int i = this.boundingBox.minY;

            for (int j = structurebb.minX; j <= structurebb.maxX; ++j)
            {
                for (int k = structurebb.minZ; k <= structurebb.maxZ; ++k)
                {
                    BlockPos blockpos = new BlockPos(j, i, k);

                    if (!worldIn.isAirBlock(blockpos) && this.boundingBox.isVecInside(blockpos))
                    {
                        boolean flag = false;

                        for (StructureComponent structurecomponent : this.components)
                        {
                            if (structurecomponent.getBoundingBox().isVecInside(blockpos))
                            {
                                flag = true;
                                break;
                            }
                        }

                        if (flag)
                        {
                            for (int l = i - 1; l > 1; --l)
                            {
                                BlockPos blockpos1 = new BlockPos(j, l, k);

                                if (!worldIn.isAirBlock(blockpos1) && !worldIn.getBlockState(blockpos1).getMaterial().isLiquid())
                                {
                                    break;
                                }

                                worldIn.setBlockState(blockpos1, Blocks.COBBLESTONE.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }
        }

        public boolean isSizeableStructure()
        {
            return this.isValid;
        }
    }
}