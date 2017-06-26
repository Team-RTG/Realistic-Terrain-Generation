package rtg.world.biome.realistic.arsmagica;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.CliffCalculator;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.IRTGWorld;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.deco.DecoFallenTree;
import rtg.api.world.deco.DecoGrass;
import rtg.api.world.deco.DecoShrub;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

public class RealisticBiomeAMWitchwoodForest extends RealisticBiomeAMBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeAMWitchwoodForest(Biome biome) {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainAMWitchwoodForest();
    }

    public class TerrainAMWitchwoodForest extends TerrainBase
    {
        private float hillStrength = 10f;// this needs to be linked to the

        public TerrainAMWitchwoodForest()
        {

        }

        @Override
        public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {

            groundNoise = groundNoise(x, y, groundVariation, rtgWorld.simplex());

            float m = hills(x, y, hillStrength, rtgWorld.simplex(), river);

            float floNoise = 65f + groundNoise + m;

            return riverized(floNoise,river);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceAMWitchwoodForest(config,
            this.baseBiome.topBlock, //Block top
            this.baseBiome.fillerBlock, //Block filler,
            this.baseBiome.topBlock, //IBlockState mixTop,
            this.baseBiome.fillerBlock, //IBlockState mixFill,
            80f, //float mixWidth,
            -0.15f, //float mixHeight,
            10f, //float smallWidth,
            0.5f //float smallStrength
        );
    }

    public class SurfaceAMWitchwoodForest extends SurfaceBase {


        private IBlockState blockMixTop;
        private IBlockState blockMixFiller;
        private float floMixWidth;
        private float floMixHeight;
        private float floSmallWidth;
        private float floSmallStrength;

        public SurfaceAMWitchwoodForest(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFiller,
                                   float mixWidth, float mixHeight, float smallWidth, float smallStrength) {

            super(config, top, filler);

            blockMixTop = mixTop;
            blockMixFiller = mixFiller;

            floMixWidth = mixWidth;
            floMixHeight = mixHeight;
            floSmallWidth = smallWidth;
            floSmallStrength = smallStrength;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            OpenSimplexNoise simplex = rtgWorld.simplex();
            float c = CliffCalculator.calc(x, z, noise);
            boolean cliff = c > 1.4f ? true : false;
            boolean mix = false;

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

                                primer.setBlockState(x, k, z, hcCobble(rtgWorld, i, j, x, z, k));
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                        }
                    }
                    else {
                        if (depth == 0 && k > 61) {
                            if (simplex.noise2(i / floMixWidth, j / floMixWidth) + simplex.noise2(i / floSmallWidth, j / floSmallWidth)
                                * floSmallStrength > floMixHeight) {
                                primer.setBlockState(x, k, z, blockMixTop);

                                mix = true;
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                        else if (depth < 4) {
                            if (mix) {
                                primer.setBlockState(x, k, z, blockMixFiller);
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

    @Override
    public void initDecos() {

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.setLogBlock(this.witchwoodLogBlock());
        decoShrub.setLeavesBlock(this.witchwoodLeavesBlock());
        decoShrub.setMaxY(110);
        decoShrub.setStrengthFactor(4f);
        decoShrub.setChance(6);
        this.addDeco(decoShrub);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.getDistribution().setNoiseDivisor(80f);
        decoFallenTree.getDistribution().setNoiseFactor(60f);
        decoFallenTree.getDistribution().setNoiseAddend(-15f);
        decoFallenTree.setLogCondition(DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenTree.setLogConditionChance(6);
        decoFallenTree.setLogConditionNoise(0f);
        decoFallenTree.setLogBlock(this.witchwoodLogBlock());
        decoFallenTree.setLeavesBlock(this.witchwoodLeavesBlock());
        decoFallenTree.setMinSize(3);
        decoFallenTree.setMaxSize(5);
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.setMaxY(128);
        decoGrass.setLoops(8);
        this.addDeco(decoGrass);
    }

    private IBlockState witchwoodLogBlock() {

        IBlockState witchwoodLogBlock;

        try {
            witchwoodLogBlock = Block.getBlockFromName("arsmagica2:witchwood_log").getDefaultState();
        }
        catch (Exception e) {
            witchwoodLogBlock = Blocks.LOG.getDefaultState();
        }

        return witchwoodLogBlock;
    }

    private IBlockState witchwoodLeavesBlock() {

        IBlockState witchwoodLeavesBlock;

        try {
            witchwoodLeavesBlock = Block.getBlockFromName("arsmagica2:witchwood_leaves").getDefaultState();
        }
        catch (Exception e) {
            witchwoodLeavesBlock = Blocks.LEAVES.getDefaultState();
        }

        return witchwoodLeavesBlock;
    }
}
