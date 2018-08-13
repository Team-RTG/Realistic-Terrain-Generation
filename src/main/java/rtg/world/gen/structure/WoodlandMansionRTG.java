package rtg.world.gen.structure;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.WoodlandMansionPieces;
import rtg.api.util.Logger;
import rtg.world.gen.ChunkGeneratorRTG;
import rtg.world.gen.ChunkLandscape;


public class WoodlandMansionRTG extends MapGenStructure {

    private final int featureSpacing = 40; //80
    private final int minFeatureSeparation = 10; //20
    public static final List<Biome> ALLOWED_BIOMES = Arrays.<Biome>asList(
        Biomes.ROOFED_FOREST,
        Biomes.MUTATED_ROOFED_FOREST,
        Biomes.PLAINS,
        Biomes.SWAMPLAND,
        Biomes.FOREST,
        Biomes.FOREST_HILLS,
        Biomes.BIRCH_FOREST,
        Biomes.BIRCH_FOREST_HILLS,
        Biomes.TAIGA,
        Biomes.TAIGA_HILLS,
        Biomes.EXTREME_HILLS
    );
    private final ChunkGeneratorRTG generatorRTG;

    public WoodlandMansionRTG(ChunkGeneratorRTG generatorIn) {
        this.generatorRTG = generatorIn;
    }

    public String getStructureName() {
        return "Mansion";
    }

    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0) {
            i = chunkX - (featureSpacing - 1);
        }

        if (chunkZ < 0) {
            j = chunkZ - (featureSpacing - 1);
        }

        int k = i / featureSpacing;
        int l = j / featureSpacing;
        Random random = this.world.setRandomSeed(k, l, 10387319);
        k = k * featureSpacing;
        l = l * featureSpacing;
        k = k + (random.nextInt(60) + random.nextInt(60)) / 2;
        l = l + (random.nextInt(60) + random.nextInt(60)) / 2;

        if (chunkX == k && chunkZ == l) {
            boolean flag = this.world.getBiomeProvider().areBiomesViable(chunkX * 16 + 8, chunkZ * 16 + 8, 32, ALLOWED_BIOMES);

            if (flag) {
                return true;
            }
        }

        return false;
    }

    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
        this.world = worldIn;
        BiomeProvider biomeprovider = worldIn.getBiomeProvider();
        return biomeprovider.isFixedBiome() && biomeprovider.getFixedBiome() != Biomes.ROOFED_FOREST ? null : findNearestStructurePosBySpacing(worldIn, this, pos, 80, 20, 10387319, true, 100, findUnexplored);
    }

    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new WoodlandMansionRTG.Start(this.world, this.generatorRTG, this.rand, chunkX, chunkZ);
    }

    public static class Start extends StructureStart {

        private boolean isValid;

        public Start() {

        }

        public Start(World p_i47235_1_, ChunkGeneratorRTG p_i47235_2_, Random p_i47235_3_, int p_i47235_4_, int p_i47235_5_) {
            super(p_i47235_4_, p_i47235_5_);
            this.create(p_i47235_1_, p_i47235_2_, p_i47235_3_, p_i47235_4_, p_i47235_5_);
        }

        private void create(World p_191092_1_, ChunkGeneratorRTG p_191092_2_, Random p_191092_3_, int p_191092_4_, int p_191092_5_) {
            Rotation rotation = Rotation.values()[p_191092_3_.nextInt(Rotation.values().length)];
            ChunkPrimer chunkprimer = new ChunkPrimer();
            p_191092_2_.generateTerrain(chunkprimer);
            int i = 5;
            int j = 5;

            if (rotation == Rotation.CLOCKWISE_90) {
                i = -5;
            }
            else if (rotation == Rotation.CLOCKWISE_180) {
                i = -5;
                j = -5;
            }
            else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                j = -5;
            }

            int k = chunkprimer.findGroundBlockIdx(7, 7);
            int l = chunkprimer.findGroundBlockIdx(7, 7 + j);
            int i1 = chunkprimer.findGroundBlockIdx(7 + i, 7);
            int j1 = chunkprimer.findGroundBlockIdx(7 + i, 7 + j);
            int k1 = Math.min(Math.min(k, l), Math.min(i1, j1));

            if (k1 < 60) {
                this.isValid = false;
            }
            else {
                BlockPos blockpos = new BlockPos(p_191092_4_ * 16 + 8, k1 + 1, p_191092_5_ * 16 + 8);
                List<WoodlandMansionPieces.MansionTemplate> list = Lists.<WoodlandMansionPieces.MansionTemplate>newLinkedList();
                WoodlandMansionPieces.generateMansion(p_191092_1_.getSaveHandler().getStructureTemplateManager(), blockpos, rotation, list, p_191092_3_);
                this.components.addAll(list);
                this.updateBoundingBox();
                this.isValid = true;
                Logger.debug("Woodland Mansion generated at {} {}", blockpos.getX(), blockpos.getZ());
            }
        }

        /**
         * Keeps iterating Structure Pieces and spawning them until the checks tell it to stop
         */
        public void generateStructure(World worldIn, Random rand, StructureBoundingBox structurebb) {
            super.generateStructure(worldIn, rand, structurebb);
            int i = this.boundingBox.minY;

            for (int j = structurebb.minX; j <= structurebb.maxX; ++j) {
                for (int k = structurebb.minZ; k <= structurebb.maxZ; ++k) {
                    BlockPos blockpos = new BlockPos(j, i, k);

                    if (!worldIn.isAirBlock(blockpos) && this.boundingBox.isVecInside(blockpos)) {
                        boolean flag = false;

                        for (StructureComponent structurecomponent : this.components) {
                            if (structurecomponent.getBoundingBox().isVecInside(blockpos)) {
                                flag = true;
                                break;
                            }
                        }

                        if (flag) {
                            for (int l = i - 1; l > 1; --l) {
                                BlockPos blockpos1 = new BlockPos(j, l, k);

                                if (!worldIn.isAirBlock(blockpos1) && !worldIn.getBlockState(blockpos1).getMaterial().isLiquid()) {
                                    break;
                                }

                                worldIn.setBlockState(blockpos1, Blocks.COBBLESTONE.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }
        }

        /**
         * currently only defined for Villages, returns true if Village has more than 2 non-road components
         */
        public boolean isSizeableStructure() {
            return this.isValid;
        }

//        private void setBlocksInChunk(ChunkGeneratorRTG generatorIn, int chunkX, int chunkZ, ChunkPrimer primer) {
//            Logger.debug("Setting mansion blocks in chunk {} {}", chunkX, chunkZ);
//            ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);
//            ChunkLandscape landscape = ((landscape = generatorIn.landscapeCache.get(chunkPos)) != null)
//                ? landscape
//                : generatorIn.landscape(generatorIn.biomeProvider, new BlockPos(chunkX * 16, 0, chunkZ * 16));
//            generatorIn.generateTerrain(primer, landscape.noise);
//        }
    }
}