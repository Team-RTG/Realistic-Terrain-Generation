package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.CliffCalculator;
import rtg.api.world.RTGWorld;
import rtg.api.util.CanyonColour;
import rtg.api.world.deco.*;
import rtg.api.world.deco.collection.DecoCollectionDesertRiver;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGAcaciaBucheri;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

public class RealisticBiomeVanillaSavannaPlateauM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_SAVANNA_ROCK;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaSavannaPlateauM() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().ALLOW_SCENIC_LAKES.set(false);

        this.getConfig().addProperty(this.getConfig().ALLOW_CACTUS).set(true);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaSavannaPlateauM(true, 35f, 160f, 60f, 40f, rtg.api.RTGAPI.config().SEA_LVL_MODIFIER.get() + 6f);
    }

    public class TerrainVanillaSavannaPlateauM extends TerrainBase {

        private float[] height;
        private int heightLength;
        private float strength;

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
        public TerrainVanillaSavannaPlateauM(boolean riverGen, float heightStrength, float canyonWidth, float canyonHeight, float canyonStrength, float baseHeight) {
            /**
             * Values come in pairs per layer. First is how high to step up.
             * 	Second is a value between 0 and 1, signifying when to step up.
             */
            height = new float[]{18f, 0.4f, 12f, 0.6f, 8f, 0.8f};
            strength = 10f;
            heightLength = height.length;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainPlateau(x, y, rtgWorld.simplex, river, height, border, strength, heightLength, 50f, true);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaSavannaPlateauM(config, biome.topBlock, biome.fillerBlock, 0);
    }

    @Override
    public Biome beachBiome() {
        return this.beachBiome(Biomes.BEACH);
    }

    public class SurfaceVanillaSavannaPlateauM extends SurfaceBase {

        private int grassRaise = 0;

        public SurfaceVanillaSavannaPlateauM(BiomeConfig config, IBlockState top, IBlockState fill, int grassHeight) {

            super(config, top, fill);
            grassRaise = grassHeight;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand;
            float c = CliffCalculator.calc(x, z, noise);
            boolean cliff = c > 1.3f;
            Block b;

            for(int k = 255; k > -1; k--)
            {
                b = primer.getBlockState(x, k, z).getBlock();
                if(b == Blocks.AIR)
                {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff) {
//                    if (!rtgConfig.STONE_SAVANNAS.get()) {
                        primer.setBlockState(x, k, z, CanyonColour.SAVANNA.getBlockForHeight(i, k, j));
//                    }
//                    else {
//                        if (depth > -1 && depth < 2) {
//                            if (rand.nextInt(3) == 0) {
//
//                                primer.setBlockState(x, k, z, hcCobble(world, i, j, x, y, k));
//                            }
//                            else {
//
//                                primer.setBlockState(x, k, z, hcStone(world, i, j, x, y, k));
//                            }
//                        }
//                        else if (depth < 10) {
//                            primer.setBlockState(x, k, z, hcStone(world, i, j, x, y, k));
//                        }
//                    }
                    }
                    else {

                        if (k > (rtg.api.RTGAPI.config().SEA_LVL_MODIFIER.get() + 11) + grassRaise)
                        {
                            if (depth == 0) {
                                if (rand.nextInt(5) == 0) {
                                    primer.setBlockState(x, k, z, BlockUtil.getStateDirt(1));
                                }
                                else {
                                    primer.setBlockState(x, k, z, topBlock);
                                }
                            }
                            else if (depth < 4) {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                        }
                        else if (depth == 0 && k > rtg.api.RTGAPI.config().SEA_LVL_MODIFIER.get() - 2) {
                            int r = (int)((k - ((rtg.api.RTGAPI.config().SEA_LVL_MODIFIER.get() - 1) + grassRaise)) / 2f);
                            if(rand.nextInt(r + 2) == 0)
                            {
                                primer.setBlockState(x, k, z, Blocks.GRASS.getDefaultState());
                            }
                            else if(rand.nextInt((int)(r / 2f) + 2) == 0)
                            {
                                primer.setBlockState(x, k, z, BlockUtil.getStateDirt(1));
                            }
                            else
                            {
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

    @Override
    public void initDecos() {

        this.addDecoCollection(new DecoCollectionDesertRiver(this.getConfig().ALLOW_CACTUS.get()));

        DecoBoulder decoBoulder1 = new DecoBoulder();
        decoBoulder1.setBoulderBlock(Blocks.COBBLESTONE.getDefaultState());
        decoBoulder1.setMaxY(rtg.api.RTGAPI.config().SEA_LVL_MODIFIER.get() + 17);
        decoBoulder1.setChance(24);
        this.addDeco(decoBoulder1);

        DecoBoulder decoBoulder2 = new DecoBoulder();
        decoBoulder2.setBoulderBlock(Blocks.COBBLESTONE.getDefaultState());
        decoBoulder1.setMinY(rtg.api.RTGAPI.config().SEA_LVL_MODIFIER.get() + 47);
        decoBoulder2.setChance(24);
        this.addDeco(decoBoulder2);

        DecoShrub acaciaShrub = new DecoShrub();
        acaciaShrub.setLogBlock(Blocks.LOG2.getDefaultState());
        acaciaShrub.setLeavesBlock(Blocks.LEAVES2.getDefaultState());
        acaciaShrub.setMaxY(rtg.api.RTGAPI.config().SEA_LVL_MODIFIER.get() + 97);
        acaciaShrub.setStrengthFactor(3f);
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
        acaciaTrees.setMaxY(rtg.api.RTGAPI.config().SEA_LVL_MODIFIER.get() + 97);
        this.addDeco(acaciaTrees);

        DecoCactus decoCactus = new DecoCactus();
        decoCactus.setMaxY(rtg.api.RTGAPI.config().SEA_LVL_MODIFIER.get() + 97);
        decoCactus.setLoops(60);
        decoCactus.setChance(8);
        this.addDeco(decoCactus, this.getConfig().ALLOW_CACTUS.get());

        DecoDoubleGrass decoDoubleGrass = new DecoDoubleGrass();
        decoDoubleGrass.setMaxY(rtg.api.RTGAPI.config().SEA_LVL_MODIFIER.get() + 65);
        decoDoubleGrass.setStrengthFactor(3f);
        this.addDeco(decoDoubleGrass);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.setMaxY(rtg.api.RTGAPI.config().SEA_LVL_MODIFIER.get() + 65);
        decoGrass.setStrengthFactor(10f);
        this.addDeco(decoGrass);
    }
}
