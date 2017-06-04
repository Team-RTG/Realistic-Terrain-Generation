package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenTrees;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.CliffCalculator;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.*;
import rtg.api.world.deco.collection.DecoCollectionDesertRiver;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

public class RealisticBiomeVanillaMesaPlateauFM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_MESA_ROCK;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaMesaPlateauFM() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().ALLOW_SCENIC_LAKES.set(false);

        this.getConfig().addProperty(this.getConfig().ALLOW_CACTUS).set(true);
    }

    public TerrainBase initTerrain() {

        return new RealisticBiomeVanillaMesaPlateau.TerrainRTGMesaPlateau(rtg.api.RTGAPI.config().SEA_LVL_MODIFIER.get() + 4);
        //return new TerrainVanillaMesaPlateauFM(false, 35f, 160f, 60f, 40f, 69f);
    }

    public class TerrainVanillaMesaPlateauFM extends TerrainBase {

        private float[] height;
        private int heightLength;
        private float strength;
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
        public TerrainVanillaMesaPlateauFM(boolean riverGen, float heightStrength, float canyonWidth, float canyonHeight, float canyonStrength, float baseHeight) {
            /**
             * Values come in pairs per layer. First is how high to step up.
             * 	Second is a value between 0 and 1, signifying when to step up.
             */
            height = new float[]{18.5f, 0.4f};
            strength = 20f;
            heightLength = height.length;
            base = 69f;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainPlateau(x, y, rtgWorld.simplex, river, height, border, strength, heightLength, 100f, false);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaMesaPlateauFM(
            config,
            BlockUtil.getStateSand(1),
            BlockUtil.getStateClay(1),
            0
        );
    }

    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        this.rReplaceWithRiver(primer, i, j, x, y, depth, rtgWorld, noise, river, base);
    }

    @Override
    public int getExtraGoldGenCount() {
        return 20;
    }

    @Override
    public Biome beachBiome() {
        return this.beachBiome(Biomes.BEACH);
    }

    public class SurfaceVanillaMesaPlateauFM extends SurfaceBase {

        private int grassRaise = 0;

        public SurfaceVanillaMesaPlateauFM(BiomeConfig config, IBlockState top, IBlockState fill, int grassHeight) {

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
                                primer.setBlockState(x, k, z, rtgWorld.mesaBiome.getBand(i, k, j));//CanyonColour.MESA.getBlockForHeight(i, k, j));
                    }
                    else {

                        if (k > 74 + grassRaise)
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
                        else if (depth == 0 && k > 61) {
                            int r = (int)((k - (62 + grassRaise)) / 2f);
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

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.setChance(10);
        decoShrub.setStrengthFactor(3f);
        addDeco(decoShrub);

        DecoCactus decoCactus = new DecoCactus();
        decoCactus.setStrengthFactor(25f);
        decoCactus.setSoilBlock(BlockUtil.getStateSand(1));
        decoCactus.setSandOnly(false);
        decoCactus.setMaxRiver(0.8f);
        addDeco(decoCactus, this.getConfig().ALLOW_CACTUS.get());

        DecoReed decoReed = new DecoReed();
        decoReed.setLoops(5);
        decoReed.setMaxRiver(0.8f);
        addDeco(decoReed);

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.setStrengthFactor(5f);
        addDeco(decoDeadBush);

        DecoTree decoTree = new DecoTree(new WorldGenTrees(false));
        decoTree.setLoops(16);
        decoTree.setTreeType(DecoTree.TreeType.WORLDGEN);
        decoTree.setTreeCondition(DecoTree.TreeCondition.X_DIVIDED_BY_STRENGTH);
        decoTree.setDistribution(new DecoTree.Distribution(24f, 1f, 0f));
        decoTree.setTreeConditionChance(0);
        decoTree.setTreeConditionFloat(4f);
        decoTree.setTreeConditionNoise(0f);
        decoTree.setMinY(74);
        addDeco(decoTree);
    }

    @Override
    public int waterSurfaceLakeChance() {
        return 30;
    }
}
