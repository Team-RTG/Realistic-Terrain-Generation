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
import rtg.api.util.PlateauUtil;
import rtg.api.world.IRTGWorld;
import rtg.api.world.deco.DecoTree;
import rtg.api.world.deco.collection.DecoCollectionDesertRiver;
import rtg.api.world.deco.collection.DecoCollectionMesa;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

public class RealisticBiomeVanillaMesaPlateauF extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MESA_ROCK;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaMesaPlateauF() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().ALLOW_SCENIC_LAKES.set(false);

        this.getConfig().addProperty(this.getConfig().ALLOW_CACTUS).set(true);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK_META).set(0);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_2_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_2_BLOCK_META).set(0);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_3_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_3_BLOCK_META).set(0);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_4_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_4_BLOCK_META).set(0);

        this.getConfig().addProperty(this.getConfig().ALLOW_PLATEAU_MODIFICATIONS).set(false);
        this.getConfig().addProperty(this.getConfig().PLATEAU_GRADIENT_BLOCK_ID).set("minecraft:stained_hardened_clay");
        this.getConfig().addProperty(this.getConfig().PLATEAU_GRADIENT_METAS).set(BiomeConfig.MESA_PLATEAU_GRADIENT_METAS);
        this.getConfig().addProperty(this.getConfig().PLATEAU_BLOCK_ID).set("minecraft:hardened_clay");
        this.getConfig().addProperty(this.getConfig().PLATEAU_BLOCK_META).set(0);
    }

    public TerrainBase initTerrain() {

       return new RealisticBiomeVanillaMesaPlateau.TerrainRTGMesaPlateau(67);
        //return new TerrainVanillaMesaPlateauF(true, 35f, 160f, 60f, 40f, 69f);
    }

    public class TerrainVanillaMesaPlateauF extends TerrainBase {

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
        public TerrainVanillaMesaPlateauF(boolean riverGen, float heightStrength, float canyonWidth, float canyonHeight, float canyonStrength, float baseHeight) {
            /**    Values come in pairs per layer. First is how high to step up.
             * 	Second is a value between 0 and 1, signifying when to step up.
             */
            height = new float[]{24.0f, 0.4f};
            strength = 10f;
            heightLength = height.length;
            base = 69f;
        }

        @Override
        public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainPlateau(x, y, rtgWorld.simplex(), river, height, border, strength, heightLength, 100f, false);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaMesaPlateauF(config, biome.topBlock, BlockUtil.getStateClay(1), 0, 0.2f, 0.6f);
    }

    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

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

    public class SurfaceVanillaMesaPlateauF extends SurfaceBase {

        private int grassRaise = 0;
        private IBlockState mixBlock;
        private IBlockState mix2Block;
        private IBlockState mix3Block;
        private IBlockState mix4Block;
        private float mix3Height;
        private float mix4Height;

        public SurfaceVanillaMesaPlateauF(BiomeConfig config, IBlockState top, IBlockState fill, int grassHeight, float mix3Height, float mix4Height) {

            super(config, top, fill);
            grassRaise = grassHeight;
            this.mix3Height = mix3Height;
            this.mix4Height = mix4Height;

            this.mixBlock = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), config.SURFACE_MIX_BLOCK_META.get(), BlockUtil.getStateClay(1));
            this.mix2Block = this.getConfigBlock(config.SURFACE_MIX_2_BLOCK.get(), config.SURFACE_MIX_2_BLOCK_META.get(), Blocks.RED_SANDSTONE.getDefaultState());
            this.mix3Block = this.getConfigBlock(config.SURFACE_MIX_3_BLOCK.get(), config.SURFACE_MIX_3_BLOCK_META.get(), BlockUtil.getStateDirt(1));
            this.mix4Block = this.getConfigBlock(config.SURFACE_MIX_4_BLOCK.get(), config.SURFACE_MIX_4_BLOCK_META.get(), Blocks.GRASS.getDefaultState());
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
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
                        primer.setBlockState(x, k, z, PlateauUtil.getPlateauBand(rtgWorld, RealisticBiomeVanillaMesaPlateauF.this, i, k, j));
                    }
                    else {

                        float mixNoise = rtgWorld.simplex().noise2(i / 12f, j / 12f);

                        if (k > 74 + grassRaise)
                        {
                            if (depth == 0) {
                                if (mixNoise > mix4Height) {
                                    primer.setBlockState(x, k, z, mix4Block);
                                }
                                else if (mixNoise > mix3Height) {
                                    primer.setBlockState(x, k, z, mix3Block);
                                }
                                else {
                                    if (rand.nextInt(5) == 0) {
                                        primer.setBlockState(x, k, z, mix2Block);
                                    }
                                    else {
                                        primer.setBlockState(x, k, z, topBlock);
                                    }
                                }
                            }
                            else if (depth < 4) {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                        }
                        else if (depth == 0 && k > 61) {

                            int r = (int)((k - (62 + grassRaise)) / 2f);
                            if (rand.nextInt(r + 2) == 0) {
                                primer.setBlockState(x, k, z, mixBlock);
                            }
                            else if (rand.nextInt((int)(r / 2f) + 2) == 0) {
                                primer.setBlockState(x, k, z, mix2Block);
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

    @Override
    public void initDecos() {

        this.addDecoCollection(new DecoCollectionDesertRiver(this.getConfig()));
        this.addDecoCollection(new DecoCollectionMesa(this.getConfig()));

        DecoTree decoTree = new DecoTree(new WorldGenTrees(false));
        decoTree.setLoops(24);
        decoTree.setTreeType(DecoTree.TreeType.WORLDGEN);
        decoTree.setTreeCondition(DecoTree.TreeCondition.X_DIVIDED_BY_STRENGTH);
        decoTree.setDistribution(new DecoTree.Distribution(24f, 1f, 0f));
        decoTree.setTreeConditionChance(1);
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
