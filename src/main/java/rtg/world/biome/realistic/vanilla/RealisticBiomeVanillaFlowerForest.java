package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.WorldUtil.Terrain;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.deco.DecoFallenTree;
import rtg.api.world.deco.DecoFlowersRTG;
import rtg.api.world.deco.DecoGrass;
import rtg.api.world.deco.DecoShrub;
import rtg.api.world.deco.DecoTree;
import rtg.api.world.deco.collection.DecoCollectionSmallPineTreesForest;
import rtg.api.world.deco.helper.DecoHelper5050;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.world.biome.realistic.RealisticBiomeBase;

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
import static rtg.api.world.deco.DecoFallenTree.LogCondition.RANDOM_CHANCE;


public class RealisticBiomeVanillaFlowerForest extends RealisticBiomeBase {

    public static Biome biome = Biomes.MUTATED_FOREST;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaFlowerForest() {

        super(biome);
    }

    @Override
    public void initConfig() {
        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaFlowerForest();
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaFlowerForest(getConfig(), Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), 0f, 1.5f, 60f, 65f, 1.5f, Blocks.GRASS.getDefaultState(), 0.05f);
    }

    @Override
    public void initDecos() {

        // First, let's get a few shrubs in to break things up a bit.
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.setMaxY(110);
        decoShrub.setStrengthFactor(4f);
        decoShrub.setChance(3);
        this.addDeco(decoShrub);

        // Flowers are the most aesthetically important feature of this biome, so let's add those next.
        DecoFlowersRTG decoFlowers1 = new DecoFlowersRTG()
            .addFlowers(POPPY, BLUE_ORCHID, ALLIUM, HOUSTONIA, RED_TULIP, ORANGE_TULIP, WHITE_TULIP, PINK_TULIP, OXEYE_DAISY, DANDELION)
            .setStrengthFactor(12f)
            .setHeightType(DecoFlowersRTG.HeightType.GET_HEIGHT_VALUE); // We're only bothered about surface flowers here.
        this.addDeco(decoFlowers1);

// TODO: [1.12] Create a new class for double-plants
//        DecoFlowersRTG decoFlowers2 = new DecoFlowersRTG();
//        decoFlowers2.addFlowers(new int[]{10, 11, 14, 15}); //Only 2-block-tall flowers.
//        decoFlowers2.setStrengthFactor(2f); // Not as many of these.
//        decoFlowers2.setChance(3);
//        decoFlowers2.setHeightType(DecoFlowersRTG.HeightType.GET_HEIGHT_VALUE); // We're only bothered about surface flowers here.
//        this.addDeco(decoFlowers2);

        // Trees first.

        TreeRTG ponderosaOakTree = new TreeRTGPinusPonderosa();
        ponderosaOakTree.setLogBlock(Blocks.LOG.getDefaultState());
        ponderosaOakTree.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        ponderosaOakTree.setMinTrunkSize(11);
        ponderosaOakTree.setMaxTrunkSize(21);
        ponderosaOakTree.setMinCrownSize(15);
        ponderosaOakTree.setMaxCrownSize(29);
        this.addTree(ponderosaOakTree);

        DecoTree oakPines = new DecoTree(ponderosaOakTree);
        oakPines.setStrengthNoiseFactorForLoops(true);
        oakPines.setTreeType(DecoTree.TreeType.RTG_TREE);
        oakPines.getDistribution().setNoiseDivisor(80f);
        oakPines.getDistribution().setNoiseFactor(60f);
        oakPines.getDistribution().setNoiseAddend(-15f);
        oakPines.setTreeCondition(DecoTree.TreeCondition.ALWAYS_GENERATE);
        oakPines.setTreeConditionNoise(0f);
        oakPines.setTreeConditionChance(1);
        oakPines.setMaxY(140);

        TreeRTG ponderosaSpruceTree = new TreeRTGPinusPonderosa();
        ponderosaSpruceTree.setLogBlock(BlockUtil.getStateLog(EnumType.SPRUCE));
        ponderosaSpruceTree.setLeavesBlock(BlockUtil.getStateLeaf(EnumType.SPRUCE));
        ponderosaSpruceTree.setMinTrunkSize(11);
        ponderosaSpruceTree.setMaxTrunkSize(21);
        ponderosaSpruceTree.setMinCrownSize(15);
        ponderosaSpruceTree.setMaxCrownSize(29);
        this.addTree(ponderosaSpruceTree);

        DecoTree sprucePines = new DecoTree(ponderosaSpruceTree);
        sprucePines.setStrengthNoiseFactorForLoops(true);
        sprucePines.setTreeType(DecoTree.TreeType.RTG_TREE);
        sprucePines.getDistribution().setNoiseDivisor(80f);
        sprucePines.getDistribution().setNoiseFactor(60f);
        sprucePines.getDistribution().setNoiseAddend(-15f);
        sprucePines.setTreeCondition(DecoTree.TreeCondition.ALWAYS_GENERATE);
        sprucePines.setTreeConditionNoise(0f);
        sprucePines.setTreeConditionChance(1);
        sprucePines.setMaxY(140);

        DecoHelper5050 decoPines = new DecoHelper5050(oakPines, sprucePines);
        this.addDeco(decoPines);

        // More trees.
        this.addDecoCollection(new DecoCollectionSmallPineTreesForest(this.getConfig()));

        // Not much free space left, so let's give some space to the base biome.
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.setNotEqualsZeroChance(4);
        this.addDeco(decoBaseBiomeDecorations);

        // Add some fallen trees of the oak and spruce variety (50/50 distribution).
        DecoFallenTree decoFallenOak = new DecoFallenTree();
        decoFallenOak.setLogCondition(RANDOM_CHANCE);
        decoFallenOak.setLogConditionChance(8);
        decoFallenOak.setMaxY(100);
        decoFallenOak.setLogBlock(Blocks.LOG.getDefaultState());
        decoFallenOak.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        decoFallenOak.setMinSize(3);
        decoFallenOak.setMaxSize(6);
        DecoFallenTree decoFallenSpruce = new DecoFallenTree();
        decoFallenSpruce.setLogCondition(RANDOM_CHANCE);
        decoFallenSpruce.setLogConditionChance(8);
        decoFallenSpruce.setMaxY(100);
        decoFallenSpruce.setLogBlock(BlockUtil.getStateLog(EnumType.SPRUCE));
        decoFallenSpruce.setLeavesBlock(BlockUtil.getStateLeaf(EnumType.SPRUCE));
        decoFallenSpruce.setMinSize(3);
        decoFallenSpruce.setMaxSize(6);
        DecoHelper5050 decoFallenTree = new DecoHelper5050(decoFallenOak, decoFallenSpruce);
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.setMaxY(128);
        decoGrass.setStrengthFactor(24f);
        this.addDeco(decoGrass);
    }

    public class TerrainVanillaFlowerForest extends TerrainBase {

        public TerrainVanillaFlowerForest() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainPlains(x, y, rtgWorld, river, 160f, 10f, 60f, 80f, 65f);
        }
    }

    public class SurfaceVanillaFlowerForest extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mixBlock;
        private float mixHeight;

        public SurfaceVanillaFlowerForest(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
                                          float stoneHeight, float stoneStrength, float clayCliff, IBlockState mix, float mixSize) {

            super(config, top, fill);
            min = minCliff;

            sCliff = stoneCliff;
            sHeight = stoneHeight;
            sStrength = stoneStrength;
            cCliff = clayCliff;

            mixBlock = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), mix);
            mixHeight = mixSize;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float c = Terrain.calcCliff(x, z, noise);
            int cliff = 0;
            boolean m = false;

            Block b;
            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (depth == 0) {

                        float p = simplex.noise3f(i / 8f, j / 8f, k / 8f) * 0.5f;
                        if (c > min && c > sCliff - ((k - sHeight) / sStrength) + p) {
                            cliff = 1;
                        }
                        if (c > cCliff) {
                            cliff = 2;
                        }

                        if (cliff == 1) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, z, hcCobble(rtgWorld, i, j, x, z, k));
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                            }
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock(rtgWorld, i, j, x, z, k));
                        }
                        else if (k < 63) {
                            if (k < 62) {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                        else if (simplex.noise2f(i / 12f, j / 12f) > mixHeight) {
                            primer.setBlockState(x, k, z, mixBlock);
                            m = true;
                        }
                        else {
                            primer.setBlockState(x, k, z, topBlock);
                        }
                    }
                    else if (depth < 6) {
                        if (cliff == 1) {
                            primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock(rtgWorld, i, j, x, z, k));
                        }
                        else {
                            primer.setBlockState(x, k, z, fillerBlock);
                        }
                    }
                }
            }
        }
    }
}
