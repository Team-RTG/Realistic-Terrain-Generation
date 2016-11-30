package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.world.RTGWorld;
import rtg.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.CanyonColour;
import rtg.api.util.CliffCalculator;
import rtg.world.biome.deco.DecoCactus;
import rtg.world.biome.deco.DecoDeadBush;
import rtg.world.biome.deco.DecoReed;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaMesaPlateauM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_MESA_CLEAR_ROCK;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaMesaPlateauM() {

        super(biome, river);

        this.noLakes = true;
        this.waterSurfaceLakeChance = 30;
    }

    @Override
    public void initConfig() {}

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaMesaPlateauM(true, 15f, 260f, 50f, 30f, 79f);
    }

    public class TerrainVanillaMesaPlateauM extends TerrainBase {

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
        public TerrainVanillaMesaPlateauM(boolean riverGen, float heightStrength, float canyonWidth, float canyonHeight, float canyonStrength, float baseHeight) {
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

            river *= 0.5f;
            return terrainPlateau(x, y, rtgWorld.simplex, river, height, border, strength, heightLength, 50f, true);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaMesaPlateauM(
            config,
            BlockUtil.getStateSand(1),
            BlockUtil.getStateClay(1),
            0
        );
    }

    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        this.rReplaceRiverSurface(primer, i, j, x, y, depth, rtgWorld, noise, river, base);
    }

    @Override
    public int getExtraGoldGenCount() {
        return 20;
    }

    @Override
    public Biome beachBiome() {
        return this.beachBiome(Biomes.BEACH);
    }

    public class SurfaceVanillaMesaPlateauM extends SurfaceBase {

        private int grassRaise = 0;

        public SurfaceVanillaMesaPlateauM(BiomeConfig config, IBlockState top, IBlockState fill, int grassHeight) {

            super(config, top, fill);
            grassRaise = grassHeight;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand;
            float c = CliffCalculator.calc(x, z, noise);
            boolean cliff = c > 1.3f;

            for (int k = 255; k > -1; k--) {

                Block b = primer.getBlockState(x, k, z).getBlock();

                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {

                    depth++;

                    if (cliff) {
                        primer.setBlockState(x, k, z, CanyonColour.MESA.getBlockForHeight(i, k, j));
                    }
                    else {

                        if (k > 74 + grassRaise)
                        {
                            primer.setBlockState(x, k, z, CanyonColour.MESA.getBlockForHeight(i, k, j));
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

        this.addDecoCollection(new DecoCollectionDesertRiver());

        DecoCactus decoCactus = new DecoCactus();
        decoCactus.strengthFactor = 25f;
        decoCactus.soilBlock = BlockUtil.getStateClay(1);
        decoCactus.sandOnly = false;
        decoCactus.maxRiver = 0.8f;
        addDeco(decoCactus);

        DecoReed decoReed = new DecoReed();
        decoReed.loops = 5;
        decoReed.maxRiver = 0.8f;
        addDeco(decoReed);

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.strengthFactor = 5f;
        addDeco(decoDeadBush);
    }
}
