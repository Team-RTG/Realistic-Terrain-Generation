package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.PlateauUtil;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.RealisticBiomeBase;
import rtg.api.world.deco.*;
import rtg.api.world.deco.collection.DecoCollectionDesertRiver;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGAcaciaBucheri;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

import java.util.Random;


public class RealisticBiomeVanillaSavannaPlateau extends RealisticBiomeBase {

    public static Biome biome = Biomes.SAVANNA_PLATEAU;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaSavannaPlateau() {

        super(biome);
    }

    @Override
    public void initConfig() {
        this.getConfig().SURFACE_WATER_LAKE_MULT.set(0.0f);
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
        this.getConfig().addProperty(this.getConfig().ALLOW_CACTUS).set(true);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().ALLOW_PLATEAU_MODIFICATIONS).set(true);
        this.getConfig().addProperty(this.getConfig().PLATEAU_GRADIENT_BLOCK_LIST).set(PlateauUtil.getSavannaPlateauBlocks());
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaSavannaPlateau(true, 35f, 160f, 60f, 40f, 69f);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaSavannaPlateau(getConfig(), biome.topBlock, biome.fillerBlock, 0);
    }

    @Override
    public void initDecos() {

        this.addDecoCollection(new DecoCollectionDesertRiver(this.getConfig()));

        DecoBoulder decoBoulder1 = new DecoBoulder();
        decoBoulder1.setBoulderBlock(Blocks.COBBLESTONE.getDefaultState());
        decoBoulder1.setMaxY(80);
        decoBoulder1.setChance(24);
        this.addDeco(decoBoulder1);

        DecoBoulder decoBoulder2 = new DecoBoulder();
        decoBoulder2.setBoulderBlock(Blocks.COBBLESTONE.getDefaultState());
        decoBoulder1.setMinY(110);
        decoBoulder2.setChance(24);
        this.addDeco(decoBoulder2);

        DecoShrub acaciaShrub = new DecoShrub();
        acaciaShrub.setLogBlock(Blocks.LOG2.getDefaultState());
        acaciaShrub.setLeavesBlock(Blocks.LEAVES2.getDefaultState());
        acaciaShrub.setMaxY(160);
        acaciaShrub.setLoopMultiplier(3f);
        acaciaShrub.setChance(9);
        this.addDeco(acaciaShrub);

        TreeRTG acaciaTree = new TreeRTGAcaciaBucheri();
        acaciaTree.setLogBlock(Blocks.LOG2.getDefaultState());
        acaciaTree.setLeavesBlock(Blocks.LEAVES2.getDefaultState());
        acaciaTree.setMinTrunkSize(12);
        acaciaTree.setMaxTrunkSize(16);
        this.addTree(acaciaTree);

        DecoTree acaciaTrees = new DecoTree(acaciaTree);
        acaciaTrees.setStrengthFactorForLoops(2f);
        acaciaTrees.setTreeType(DecoTree.TreeType.RTG_TREE);
        acaciaTrees.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        acaciaTrees.setTreeConditionChance(12);
        acaciaTrees.setMaxY(90);
        this.addDeco(acaciaTrees);

        DecoCactus decoCactus = new DecoCactus();
        decoCactus.setMaxY(160);
        decoCactus.setLoops(60);
        decoCactus.setChance(8);
        this.addDeco(decoCactus, this.getConfig().ALLOW_CACTUS.get());

        DecoDoubleGrass decoDoubleGrass = new DecoDoubleGrass();
        decoDoubleGrass.setMaxY(128);
        decoDoubleGrass.setStrengthFactor(3f);
        this.addDeco(decoDoubleGrass);
    }

    @Override
    public void overrideDecorations() {
        baseBiome().decorator.cactiPerChunk = -999;
    }

    public static class TerrainVanillaSavannaPlateau extends TerrainBase {

        private boolean booRiver;
        private float[] height;
        private int heightLength;
        private float strength;
        private float smooth;
        private float cWidth;
        private float cHeigth;
        private float cStrength;
        private float base;

        /*
         * Example parameters:
         *
         * allowed to generate rivers?
         * riverGen = true
         *
         * canyon jump heights
         * heightArray = new float[]{2.0f, 0.5f, 6.5f, 0.5f, 14.0f, 0.5f, 19.0f, 0.5f}
         *
         * strength of canyon jump heights
         * heightStrength = 35f
         *
         * canyon width (cliff to cliff)
         * canyonWidth = 160f
         *
         * canyon heigth (total heigth)
         * canyonHeight = 60f
         *
         * canyon strength
         * canyonStrength = 40f
         *
         */
        public TerrainVanillaSavannaPlateau(boolean riverGen, float heightStrength, float canyonWidth, float canyonHeight, float canyonStrength, float baseHeight) {

            booRiver = true;
            /*    Values come in pairs per layer. First is how high to step up.
             * 	Second is a value between 0 and 1, signifying when to step up.
             */
            height = new float[]{12.0f, 0.5f, 6f, 0.7f};
            strength = 10f;
            heightLength = height.length;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainPlateau(x, y, rtgWorld, river, height, border, strength, heightLength, 50f, true);
        }
    }

    public class SurfaceVanillaSavannaPlateau extends SurfaceBase {

        private int grassRaise = 0;
        private IBlockState mixBlock;

        public SurfaceVanillaSavannaPlateau(BiomeConfig config, IBlockState top, IBlockState fill, int grassHeight) {

            super(config, top, fill);
            grassRaise = grassHeight;

            this.mixBlock = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), BlockUtil.getStateDirt(BlockDirt.DirtType.COARSE_DIRT));
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            float c = TerrainBase.calcCliff(x, z, noise);
            boolean cliff = c > 1.3f;
            Block b;

            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff) {
                        if (biomeConfig.ALLOW_PLATEAU_MODIFICATIONS.get()) {
                            primer.setBlockState(x, k, z, PlateauUtil.getPlateauBand(rtgWorld, RealisticBiomeVanillaSavannaPlateau.this, i, k, j));
                        }
                        else {
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
                    }
                    else {

                        if (k > 74 + grassRaise) {
                            if (depth == 0) {
                                if (rand.nextInt(5) == 0) {
                                    primer.setBlockState(x, k, z, mixBlock);
                                }
                                else {
                                    primer.setBlockState(x, k, z, topBlock);
                                }
                            }
                            else if (depth < 4) {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                        }
                        else if (depth == 0 && k > 61) {
                            int r = (int) ((k - (62 + grassRaise)) / 2f);
                            if (rand.nextInt(r + 2) == 0) {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                            else if (rand.nextInt((int) (r / 2f) + 2) == 0) {
                                primer.setBlockState(x, k, z, mixBlock);
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                        else if (depth < 4) {
                            primer.setBlockState(x, k, z, fillerBlock);
                        }
                    }
                }
            }
        }
    }
}