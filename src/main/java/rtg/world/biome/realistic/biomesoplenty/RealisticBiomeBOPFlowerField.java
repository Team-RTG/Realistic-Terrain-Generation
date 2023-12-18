package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.RealisticBiomeBase;
import rtg.api.world.deco.DecoFlowersRTG;
import rtg.api.world.deco.DecoShrub;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

import static net.minecraft.block.BlockDoublePlant.EnumPlantType.PAEONIA;
import static net.minecraft.block.BlockDoublePlant.EnumPlantType.ROSE;
import static net.minecraft.block.BlockDoublePlant.EnumPlantType.SUNFLOWER;
import static net.minecraft.block.BlockDoublePlant.EnumPlantType.SYRINGA;
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


public class RealisticBiomeBOPFlowerField extends RealisticBiomeBase {

    public RealisticBiomeBOPFlowerField(final Biome biome) { super(biome); }

    @Override
    public void initConfig() {
        this.getConfig().addProperty(this.getConfig().ALLOW_PONDS_WATER).set(true);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPFlowerField();
    }

    @Override
    public SurfaceBase initSurface() {
        return new SurfaceBOPFlowerField(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, baseBiome().topBlock, 0.05f);
    }

    @Override
    public void initDecos() {

        // First, let's get a few shrubs in to break things up a bit.
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.setMaxY(110);
        decoShrub.setLoopMultiplier(4f);
        decoShrub.setChance(3);
        this.addDeco(decoShrub);

        // Flowers are the most aesthetically important feature of this biome, so let's add those next.
        DecoFlowersRTG decoFlowers1 = new DecoFlowersRTG()
            .addFlowers(POPPY, BLUE_ORCHID, ALLIUM, HOUSTONIA, RED_TULIP, ORANGE_TULIP, WHITE_TULIP, PINK_TULIP, OXEYE_DAISY, DANDELION)
            .setStrengthFactor(12f)
            .setHeightType(DecoFlowersRTG.HeightType.GET_HEIGHT_VALUE); // We're only bothered about surface flowers here.
        this.addDeco(decoFlowers1);

        DecoFlowersRTG decoFlowers2 = new DecoFlowersRTG();
        decoFlowers2.addPlants(SUNFLOWER, SYRINGA, ROSE, PAEONIA); //Only 2-block-tall flowers.
        decoFlowers2.setChance(8);
        decoFlowers2.setHeightType(DecoFlowersRTG.HeightType.GET_HEIGHT_VALUE); // We're only bothered about surface flowers here.
        this.addDeco(decoFlowers2);
    }

    @Override
    public void overrideDecorations() {
        baseBiome().decorator.flowersPerChunk = -999;
    }

    public static class TerrainBOPFlowerField extends TerrainBase {

        public TerrainBOPFlowerField() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {
            return terrainPlains(x, y, rtgWorld, river, 160f, 10f, 60f, 80f, 65f);
        }
    }

    public static class SurfaceBOPFlowerField extends SurfaceBase {

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

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float c = TerrainBase.calcCliff(x, z, noise, river);
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

                                primer.setBlockState(x, k, z, hcCobble());
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone());
                            }
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock());
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
                            primer.setBlockState(x, k, z, mix);
                            m = true;
                        }
                        else {
                            primer.setBlockState(x, k, z, topBlock);
                        }
                    }
                    else if (depth < 6) {
                        if (cliff == 1) {
                            primer.setBlockState(x, k, z, hcStone());
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock());
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
