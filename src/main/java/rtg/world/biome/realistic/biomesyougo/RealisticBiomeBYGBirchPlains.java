package rtg.world.biome.realistic.biomesyougo;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesyougo.config.BiomeConfigBYGBirchPlains;
import rtg.util.BlockUtil;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBYGBirchPlains extends RealisticBiomeBYGBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBYGBirchPlains(Biome biome, BiomeConfig config) {

        super(config, biome, river);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 24;
        decoFallenTree.logBlock = BlockUtil.getStateLog(2);
        decoFallenTree.leavesBlock = BlockUtil.getStateLeaf(2);
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBYGBirchPlains.decorationLogsId));

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.maxY = 100;
        this.addDeco(decoBaseBiomeDecorations);

        DecoShrub decoShrubBirch = new DecoShrub();
        decoShrubBirch.logBlock = BlockUtil.getStateLog(2);
        decoShrubBirch.leavesBlock = BlockUtil.getStateLeaf(2);
        decoShrubBirch.maxY = 90;
        decoShrubBirch.strengthFactor = 6f;
        decoShrubBirch.chance = 4;
        this.addDeco(decoShrubBirch);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 100;
        decoGrass.strengthFactor = 6f;
        this.addDeco(decoGrass);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBYGBirchPlains();
    }

    public class TerrainBYGBirchPlains extends TerrainBase {


        private GroundEffect groundEffect = new GroundEffect(4f);

        public TerrainBYGBirchPlains() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
            //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 80f, 65f);
            return riverized(65f + groundEffect.added(simplex, cell, x, y), river);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBYGBirchPlains(config, this.baseBiome.topBlock, this.baseBiome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, BlockUtil.getStateDirt(2), 0.15f);
    }

    public class SurfaceBYGBirchPlains extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mixBlock;
        private float mixHeight;

        public SurfaceBYGBirchPlains(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
                                     float stoneHeight, float stoneStrength, float clayCliff, IBlockState mix, float mixSize) {

            super(config, top, fill);
            min = minCliff;

            sCliff = stoneCliff;
            sHeight = stoneHeight;
            sStrength = stoneStrength;
            cCliff = clayCliff;

            mixBlock = this.getConfigBlock(config, BiomeConfigBYGBirchPlains.surfaceMixBlockId,
                BiomeConfigBYGBirchPlains.surfaceMixBlockMetaId,
                mix);
            mixHeight = mixSize;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                                 OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

            float c = CliffCalculator.calc(x, y, noise);
            int cliff = 0;
            boolean m = false;

            Block b;
            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, y).getBlock();
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

                                primer.setBlockState(x, k, y, hcCobble(world, i, j, x, y, k));
                            }
                            else {

                                primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                            }
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, y, getShadowStoneBlock(world, i, j, x, y, k));
                        }
                        else if (k < 63) {
                            if (k < 62) {
                                primer.setBlockState(x, k, y, fillerBlock);
                            }
                            else {
                                primer.setBlockState(x, k, y, topBlock);
                            }
                        }
                        else if (simplex.noise2(i / 12f, j / 12f) > mixHeight) {
                            primer.setBlockState(x, k, y, mixBlock);
                            m = true;
                        }
                        else {
                            primer.setBlockState(x, k, y, topBlock);
                        }
                    }
                    else if (depth < 6) {
                        if (cliff == 1) {
                            primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, y, getShadowStoneBlock(world, i, j, x, y, k));
                        }
                        else {
                            primer.setBlockState(x, k, y, fillerBlock);
                        }
                    }
                }
            }
        }
    }
}
