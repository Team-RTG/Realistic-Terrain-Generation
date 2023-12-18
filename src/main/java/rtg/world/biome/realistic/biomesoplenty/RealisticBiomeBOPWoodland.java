package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.Distribution;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.RealisticBiomeBase;
import rtg.api.world.deco.DecoFallenTree;
import rtg.api.world.deco.DecoFlowersRTG;
import rtg.api.world.deco.DecoShrub;
import rtg.api.world.deco.DecoTree;
import rtg.api.world.deco.collection.DecoCollectionBase;
import rtg.api.world.deco.helper.DecoHelper5050;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

import static net.minecraft.block.BlockFlower.EnumFlowerType.ALLIUM;
import static net.minecraft.block.BlockFlower.EnumFlowerType.BLUE_ORCHID;
import static net.minecraft.block.BlockFlower.EnumFlowerType.DANDELION;
import static net.minecraft.block.BlockFlower.EnumFlowerType.HOUSTONIA;
import static net.minecraft.block.BlockFlower.EnumFlowerType.ORANGE_TULIP;
import static net.minecraft.block.BlockFlower.EnumFlowerType.OXEYE_DAISY;
import static net.minecraft.block.BlockFlower.EnumFlowerType.PINK_TULIP;
import static net.minecraft.block.BlockFlower.EnumFlowerType.POPPY;
import static net.minecraft.block.BlockFlower.EnumFlowerType.RED_TULIP;
import static net.minecraft.block.BlockFlower.EnumFlowerType.WHITE_TULIP;


public class RealisticBiomeBOPWoodland extends RealisticBiomeBase {

    public RealisticBiomeBOPWoodland(final Biome biome) { super(biome); }

    @Override
    public Biome preferredBeach() {
        return BOPBiomes.gravel_beach.orNull();
    }

    @Override
    public void initConfig() {
        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPWoodland(10f, 25f, 72f, 120f);
    }

    @Override
    public SurfaceBase initSurface() {
        return new SurfaceBOPWoodland(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock);
    }

    @Override
    public void initDecos() {
        this.addDecoCollection(new DecoCollectionWoodland(this.getConfig()));
    }

    public static class TerrainBOPWoodland extends TerrainBase {

        private float start;
        private float height;
        private float width;

        public TerrainBOPWoodland(float hillStart, float landHeight, float baseHeight, float hillWidth) {

            start = hillStart;
            height = landHeight;
            base = baseHeight;
            width = hillWidth;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainHighland(x, y, rtgWorld, river, start, width, height, base - 62f);
        }
    }

    public static class SurfaceBOPWoodland extends SurfaceBase {

        public SurfaceBOPWoodland(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            float c = TerrainBase.calcCliff(x, z, noise, river);
            boolean cliff = c > 1.4f;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff) {
                        if (depth > -1 && depth < 2) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, z, hcCobble());
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone());
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, z, hcStone());
                        }
                    }
                    else {
                        if (depth == 0 && k > 61) {
                            primer.setBlockState(x, k, z, topBlock);
                        }
                        else if (depth < 4) {
                            primer.setBlockState(x, k, z, fillerBlock);
                        }
                    }
                }
            }
        }
    }

    // TODO: [1.12] Useless subclass. Just addDeco the Decos in #initDecos!
    private static class DecoCollectionWoodland extends DecoCollectionBase {

        // Tends to return values between -3f to 5f, with some overflow.
        private Distribution forestDistribution = new Distribution(100f, 6f, 0.8f);

        private float tallMin = -1f;
        private float tallMax = 3f;
        private float short1Min = -3f;
        private float short1Max = -1f;

        DecoCollectionWoodland(BiomeConfig config) {

            super(config);

            this
                .addDeco(tallTrees(tallMin, tallMax)) // Tall trees first.
                .addDeco(shortTrees(short1Min, short1Max)) // Short trees next.
                .addDeco(logs(), config.ALLOW_LOGS.get()) // Add some fallen trees of the oak and spruce variety (50/50 distribution).
                .addDeco(shrubsOak()) // Shrubs to fill in the blanks.
                .addDeco(shrubsSpruce()) // Fewer spruce shrubs than oak.
                .addDeco(flowers()) // Only 1-block tall flowers so we can see the trees better.
            ;
        }

        private DecoHelper5050 tallTrees(float noiseMin, float noiseMax) {
            return new DecoHelper5050(
                tallPineTrees(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState(), noiseMin, noiseMax),
                tallPineTrees(BlockUtil.getStateLog(EnumType.SPRUCE), BlockUtil.getStateLeaf(EnumType.SPRUCE), noiseMin, noiseMax)
            );
        }

        private DecoTree tallPineTrees(IBlockState log, IBlockState leaves, float noiseMin, float noiseMax) {

            TreeRTG pinusPonderosa = new TreeRTGPinusPonderosa();
            pinusPonderosa.setLogBlock(log);
            pinusPonderosa.setLeavesBlock(leaves);
            pinusPonderosa.setMinTrunkSize(6);
            pinusPonderosa.setMaxTrunkSize(10);
            pinusPonderosa.setMinCrownSize(8);
            pinusPonderosa.setMaxCrownSize(20);

            this.addTree(pinusPonderosa);

            return new DecoTree(pinusPonderosa)
                .setStrengthFactorForLoops(6f)
                .setTreeType(DecoTree.TreeType.RTG_TREE)
                .setDistribution(forestDistribution)
                .setTreeCondition(DecoTree.TreeCondition.NOISE_BETWEEN_AND_RANDOM_CHANCE)
                .setTreeConditionNoise(noiseMin)
                .setTreeConditionNoise2(noiseMax)
                .setTreeConditionChance(1)
                .setMaxY(85);
        }

        private DecoHelper5050 shortTrees(float noiseMin, float noiseMax) {
            return new DecoHelper5050(
                shortPineTrees(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState(), noiseMin, noiseMax),
                shortPineTrees(BlockUtil.getStateLog(EnumType.SPRUCE), BlockUtil.getStateLeaf(EnumType.SPRUCE), noiseMin, noiseMax)
            );
        }

        private DecoTree shortPineTrees(IBlockState log, IBlockState leaves, float noiseMin, float noiseMax) {

            TreeRTG piceaSitchensis = new TreeRTGPiceaSitchensis()
                .setLogBlock(log)
                .setLeavesBlock(leaves)
                .setMinTrunkSize(4)
                .setMaxTrunkSize(9)
                .setMinCrownSize(6)
                .setMaxCrownSize(13);

            this.addTree(piceaSitchensis);

            return new DecoTree(piceaSitchensis)
                .setStrengthFactorForLoops(6f)
                .setTreeType(DecoTree.TreeType.RTG_TREE)
                .setDistribution(forestDistribution)
                .setTreeCondition(DecoTree.TreeCondition.NOISE_BETWEEN_AND_RANDOM_CHANCE)
                .setTreeConditionNoise(noiseMin)
                .setTreeConditionNoise2(noiseMax)
                .setTreeConditionChance(1)
                .setMaxY(85);
        }

        private DecoHelper5050 logs() {
            return new DecoHelper5050(oakLogs(), spruceLogs());
        }

        private DecoFallenTree oakLogs() {
            return new DecoFallenTree()
                .setLogCondition(DecoFallenTree.LogCondition.RANDOM_CHANCE)
                .setLogConditionChance(10)
                .setMaxY(80)
                .setLogBlock(Blocks.LOG.getDefaultState())
                .setLeavesBlock(Blocks.LEAVES.getDefaultState())
                .setMinSize(3)
                .setMaxSize(6);
        }

        private DecoFallenTree spruceLogs() {
            return new DecoFallenTree()
                .setLogCondition(DecoFallenTree.LogCondition.RANDOM_CHANCE)
                .setLogConditionChance(24)
                .setMaxY(80)
                .setLogBlock(BlockUtil.getStateLog(EnumType.SPRUCE))
                .setLeavesBlock(BlockUtil.getStateLeaf(EnumType.SPRUCE))
                .setMinSize(3)
                .setMaxSize(6);
        }

        private DecoShrub shrubsOak() {
            return new DecoShrub()
                .setMaxY(140)
                .setLoopMultiplier(4f)
                .setChance(3);
        }

        private DecoShrub shrubsSpruce() {
            return new DecoShrub()
                .setLogBlock(BlockUtil.getStateLog(EnumType.SPRUCE))
                .setLeavesBlock(BlockUtil.getStateLeaf(EnumType.SPRUCE))
                .setMaxY(140)
                .setLoopMultiplier(4f)
                .setChance(9);
        }

        private DecoFlowersRTG flowers() {
            return new DecoFlowersRTG()
                .addFlowers(POPPY, BLUE_ORCHID, ALLIUM, HOUSTONIA, RED_TULIP, ORANGE_TULIP, WHITE_TULIP, PINK_TULIP, OXEYE_DAISY, DANDELION)
                .setMaxY(128)
                .setStrengthFactor(6f);
        }
    }
}
