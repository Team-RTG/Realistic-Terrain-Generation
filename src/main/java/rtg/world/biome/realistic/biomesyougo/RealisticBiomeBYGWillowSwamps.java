package rtg.world.biome.realistic.biomesyougo;

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
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.IRTGWorld;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.deco.DecoFallenTree;
import rtg.api.world.deco.DecoShrub;
import rtg.api.world.deco.DecoTree;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGSalixMyrtilloides;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import static rtg.api.world.deco.DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;

public class RealisticBiomeBYGWillowSwamps extends RealisticBiomeBYGBase {

    public static Biome river = Biomes.RIVER;

    private static IBlockState willowLogBlock = Block.getBlockFromName("BiomesYouGo:WillowLog").getDefaultState();
    private static IBlockState willowLeavesBlock = Block.getBlockFromName("BiomesYouGo:WillowLeaves").getDefaultState();

    public RealisticBiomeBYGWillowSwamps(Biome biome) {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBYGWillowSwamps();
    }

    public class TerrainBYGWillowSwamps extends TerrainBase {

        public TerrainBYGWillowSwamps() {

        }

        @Override
        public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainMarsh(x, y, rtgWorld.simplex(), 61.5f,river);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBYGWillowSwamps(config, this.baseBiome.topBlock, this.baseBiome.fillerBlock);
    }

    public class SurfaceBYGWillowSwamps extends SurfaceBase {

        public SurfaceBYGWillowSwamps(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            OpenSimplexNoise simplex = rtgWorld.simplex();
            float c = CliffCalculator.calc(x, z, noise);
            boolean cliff = c > 1.4f ? true : false;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff && k > 64) {
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
                            primer.setBlockState(x, k, z, topBlock);
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

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.setMaxY(82);
        this.addDeco(decoBaseBiomeDecorations);

        TreeRTG myrtilloidesTree = new TreeRTGSalixMyrtilloides();
        myrtilloidesTree.setLogBlock(Blocks.LOG.getDefaultState());
        myrtilloidesTree.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        this.addTree(myrtilloidesTree);

        DecoTree decoTrees = new DecoTree(myrtilloidesTree);
        decoTrees.getDistribution().setNoiseDivisor(80f);
        decoTrees.getDistribution().setNoiseFactor(60f);
        decoTrees.getDistribution().setNoiseAddend(-15f);
        decoTrees.setTreeType(DecoTree.TreeType.RTG_TREE);
        decoTrees.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        decoTrees.setTreeConditionNoise(0f);
        decoTrees.setTreeConditionChance(16);
        decoTrees.setMaxY(70);
        this.addDeco(decoTrees);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.getDistribution().setNoiseDivisor(80f);
        decoFallenTree.getDistribution().setNoiseFactor(60f);
        decoFallenTree.getDistribution().setNoiseAddend(-15f);
        decoFallenTree.setLogCondition(NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenTree.setLogConditionNoise(0f);
        decoFallenTree.setLogConditionChance(6);
        decoFallenTree.setLogBlock(BlockUtil.getStateLog2(1));
        decoFallenTree.setLeavesBlock(BlockUtil.getStateLeaf2(1));
        decoFallenTree.setMinSize(3);
        decoFallenTree.setMaxSize(6);
        decoFallenTree.setMaxY(76);
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        TreeRTG deadWillowTree = new TreeRTGSalixMyrtilloides();
        deadWillowTree.setLogBlock(Blocks.LOG.getDefaultState());
        deadWillowTree.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        deadWillowTree.setNoLeaves(true);
        this.addTree(deadWillowTree);

        DecoTree deadWillow = new DecoTree(deadWillowTree);
        deadWillow.setTreeType(DecoTree.TreeType.RTG_TREE);
        deadWillow.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        deadWillow.setTreeConditionChance(18);
        deadWillow.setMaxY(84);
        this.addDeco(deadWillow);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.setMaxY(88);
        decoShrub.setStrengthFactor(6f);
        this.addDeco(decoShrub);

        DecoShrub decoShrubBYG = new DecoShrub();
        decoShrubBYG.setLogBlock(willowLogBlock);
        decoShrubBYG.setLeavesBlock(willowLeavesBlock);
        decoShrubBYG.setMaxY(88);
        decoShrubBYG.setStrengthFactor(3f);
        this.addDeco(decoShrubBYG);
    }
}
