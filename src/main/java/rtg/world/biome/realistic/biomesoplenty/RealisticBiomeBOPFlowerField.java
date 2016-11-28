package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.config.BiomeConfig;
import rtg.util.CliffCalculator;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPFlowerField extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.flower_field.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPFlowerField() {

        super(biome, river);
    }

    @Override
    public void initConfig() {}

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPFlowerField();
    }

    public class TerrainBOPFlowerField extends TerrainBase {

        public TerrainBOPFlowerField() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 80f, 65f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPFlowerField(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, biome.topBlock, 0.05f);
    }

    public class SurfaceBOPFlowerField extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mix;
        private float mixHeight;

        public SurfaceBOPFlowerField(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
                                     float stoneHeight, float stoneStrength, float clayCliff, IBlockState mixBlock, float mixSize) {

            super(config, top, fill);
            min = minCliff;

            sCliff = stoneCliff;
            sHeight = stoneHeight;
            sStrength = stoneStrength;
            cCliff = clayCliff;

            mix = mixBlock;
            mixHeight = mixSize;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand;
            OpenSimplexNoise simplex = rtgWorld.simplex;
            float c = CliffCalculator.calc(x, z, noise);
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

                        float p = simplex.noise3(i / 8f, j / 8f, k / 8f) * 0.5f;
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
                        else if (simplex.noise2(i / 12f, j / 12f) > mixHeight) {
                            primer.setBlockState(x, k, z, mix);
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

    @Override
    public void initDecos() {

        // First, let's get a few shrubs in to break things up a bit.
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 4f;
        decoShrub.chance = 3;
        this.addDeco(decoShrub);

        // Flowers are the most aesthetically important feature of this biome, so let's add those next.
        DecoFlowersRTG decoFlowers1 = new DecoFlowersRTG();
        decoFlowers1.flowers = new int[]{0, 1, 2, 3, 8, 9}; //Only colourful 1-block-tall flowers. No tulips as BOP has those covered.
        decoFlowers1.strengthFactor = 12f; // Lots and lots of flowers!
        decoFlowers1.heightType = DecoFlowersRTG.HeightType.GET_HEIGHT_VALUE; // We're only bothered about surface flowers here.
        this.addDeco(decoFlowers1);

        DecoFlowersRTG decoFlowers2 = new DecoFlowersRTG();
        decoFlowers2.flowers = new int[]{10, 11, 14, 15}; //Only 2-block-tall flowers.
        decoFlowers2.strengthFactor = 2f; // Not as many of these.
        decoFlowers2.chance = 3;
        decoFlowers2.heightType = DecoFlowersRTG.HeightType.GET_HEIGHT_VALUE; // We're only bothered about surface flowers here.
        this.addDeco(decoFlowers2);

        // Not much free space left, so let's give some space to the base biome.
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 4;
        this.addDeco(decoBaseBiomeDecorations);

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 24f;
        this.addDeco(decoGrass);
    }
}
