package rtg.world.gen.structure;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.WoodlandMansionPieces;

import mcp.MethodsReturnNonnullByDefault;

import rtg.world.gen.ChunkGeneratorRTG;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public final class WoodlandMansionRTG extends MapGenStructure {

    private final ChunkGeneratorRTG chunkGenerator;
    private int spacing = 80;
    private int separation = 20;
    private static final List<Biome> ALLOWED_BIOMES = Lists.newArrayList(Biomes.ROOFED_FOREST);

    public WoodlandMansionRTG(final ChunkGeneratorRTG chunkGenerator, final Map<String, String> settings) {
        super();
        this.chunkGenerator = chunkGenerator;
        this.spacing = MathHelper.getInt(settings.get("spacing"), this.spacing);
        this.separation = MathHelper.getInt(settings.get("separation"), this.separation);
        MapGenStructureIO.registerStructure(WoodlandMansionRTG.Start.class, getStructureName());
    }

    @Override
    public String getStructureName() {
        return "Mansion";
    }

    @Override
    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored)
    {
        this.world = worldIn;
        return findNearestStructurePosBySpacing(worldIn, this, pos, spacing, separation, 10387319, true, 100, findUnexplored);
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        int i = chunkX;
        int j = chunkZ;
        if (chunkX < 0) { i = chunkX - (this.spacing - 1); }
        if (chunkZ < 0) { j = chunkZ - (this.spacing - 1); }
        int x = i / this.spacing;
        int z = j / this.spacing;
        Random random = this.world.setRandomSeed(x, z, 10387319);
        x = x * this.spacing;
        z = z * this.spacing;
        x = x + (random.nextInt(this.spacing - this.separation) + random.nextInt(this.spacing - this.separation)) / 2;
        z = z + (random.nextInt(this.spacing - this.separation) + random.nextInt(this.spacing - this.separation)) / 2;
        return chunkX == x && chunkZ == z && this.world.getBiomeProvider().areBiomesViable(chunkX * 16 + 8, chunkZ * 16 + 8, 32, ALLOWED_BIOMES);
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new WoodlandMansionRTG.Start(this.chunkGenerator, this.world, this.rand, chunkX, chunkZ);
    }

    public static class Start extends StructureStart
    {
        private boolean isValid;
        private final ChunkGeneratorRTG chunkGenerator;

        Start(final ChunkGeneratorRTG chunkGenerator, World world, Random rand, int chunkX, int chunkZ) {
            super(chunkX, chunkZ);
            this.chunkGenerator = chunkGenerator;
            this.create(world, rand, new ChunkPos(chunkX, chunkZ));
        }

        private void create(final World world, final Random rand, final ChunkPos chunkPos) {

            Rotation rotation = Rotation.values()[rand.nextInt(Rotation.values().length)];
            ChunkPrimer chunkprimer = new ChunkPrimer();
            float[] chunkNoise = chunkGenerator.getLandscape(world.getBiomeProvider(), chunkPos).noise;// use the cache call
            chunkGenerator.generateTerrain(chunkprimer, chunkNoise);

            int i = 5;
            int j = 5;
            if      (rotation == Rotation.CLOCKWISE_90)        { i = -5; }
            else if (rotation == Rotation.CLOCKWISE_180)       { i = -5; j = -5; }
            else if (rotation == Rotation.COUNTERCLOCKWISE_90) { j = -5; }

            int corner1 = chunkprimer.findGroundBlockIdx(7, 7);
            int corner2 = chunkprimer.findGroundBlockIdx(7, 7 + j);
            int corner3 = chunkprimer.findGroundBlockIdx(7 + i, 7);
            int corner4 = chunkprimer.findGroundBlockIdx(7 + i, 7 + j);

            int lowestY = Math.min(Math.min(corner1, corner2), Math.min(corner3, corner4));
            if (lowestY < 63) {
                this.isValid = false;
            } else {
                BlockPos blockpos = new BlockPos(getChunkPosX() * 16 + 8, lowestY + 1, getChunkPosZ() * 16 + 8);
                List<WoodlandMansionPieces.MansionTemplate> list = Lists.newLinkedList();
                WoodlandMansionPieces.generateMansion(world.getSaveHandler().getStructureTemplateManager(), blockpos, rotation, list, rand);
                this.components.addAll(list);
                this.updateBoundingBox();
                this.isValid = true;
            }
        }

        @Override
        public void generateStructure(World worldIn, Random rand, StructureBoundingBox structurebb) {
            super.generateStructure(worldIn, rand, structurebb);
            int i = this.boundingBox.minY;
            for (int j = structurebb.minX; j <= structurebb.maxX; ++j) {
                for (int k = structurebb.minZ; k <= structurebb.maxZ; ++k) {
                    BlockPos blockpos = new BlockPos(j, i, k);
                    if (!worldIn.isAirBlock(blockpos) && this.boundingBox.isVecInside(blockpos)) {
                        boolean flag = this.components.stream().anyMatch(structurecomponent -> structurecomponent.boundingBox.isVecInside(blockpos));
                        if (flag) {
                            for (int l = i - 1; l > 1; --l) {
                                BlockPos blockpos1 = new BlockPos(j, l, k);
                                if (!worldIn.isAirBlock(blockpos1) && !worldIn.getBlockState(blockpos1).getMaterial().isLiquid()) { break; }
                                worldIn.setBlockState(blockpos1, Blocks.COBBLESTONE.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }
        }

        @Override
        public boolean isSizeableStructure() {
            return this.isValid;
        }
    }
}
