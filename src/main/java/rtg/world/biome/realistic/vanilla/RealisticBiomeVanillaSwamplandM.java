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
import rtg.api.world.IRTGWorld;
import rtg.api.world.deco.*;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGSalixMyrtilloides;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

public class RealisticBiomeVanillaSwamplandM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_SWAMPLAND;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaSwamplandM() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaSwamplandM(50f, 25f, 60f);
    }

    public class TerrainVanillaSwamplandM extends TerrainBase {

        private float width;
        private float strength;
        private float terrainHeight;

        public TerrainVanillaSwamplandM(float mountainWidth, float mountainStrength, float height) {

            width = mountainWidth;
            strength = mountainStrength;
            terrainHeight = height;
        }

        @Override
        public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainLonelyMountain(x, y, rtgWorld.simplex(), rtgWorld.cell(), river, strength, width, terrainHeight);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaSwamplandM(config, biome.topBlock, biome.fillerBlock);
    }

    public class SurfaceVanillaSwamplandM extends SurfaceBase {

        public SurfaceVanillaSwamplandM(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
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

        TreeRTG myrtilloidesTree = new TreeRTGSalixMyrtilloides();
        myrtilloidesTree.setLogBlock(Blocks.LOG.getDefaultState());
        myrtilloidesTree.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        this.addTree(myrtilloidesTree);

        DecoTree decoTrees = new DecoTree(myrtilloidesTree);
        decoTrees.setStrengthNoiseFactorXForLoops(true);
        decoTrees.setStrengthFactorForLoops(1f);
        decoTrees.getDistribution().setNoiseDivisor(80f);
        decoTrees.getDistribution().setNoiseFactor(60f);
        decoTrees.getDistribution().setNoiseAddend(-15f);
        decoTrees.setTreeType(DecoTree.TreeType.RTG_TREE);
        decoTrees.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        decoTrees.setTreeConditionChance(12);
        decoTrees.setMaxY(100);
        this.addDeco(decoTrees);

        TreeRTG ponderosaTree = new TreeRTGPinusPonderosa();
        ponderosaTree.setLogBlock(Blocks.LOG.getDefaultState());
        ponderosaTree.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        ponderosaTree.setMinTrunkSize(3);
        ponderosaTree.setMaxTrunkSize(6);
        ponderosaTree.setMinCrownSize(6);
        ponderosaTree.setMaxCrownSize(14);
        ponderosaTree.setNoLeaves(true);
        this.addTree(ponderosaTree);

        DecoTree deadPineTree = new DecoTree(ponderosaTree);
        deadPineTree.setTreeType(DecoTree.TreeType.RTG_TREE);
        deadPineTree.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        deadPineTree.setTreeConditionChance(18);
        deadPineTree.setMaxY(100);
        this.addDeco(deadPineTree);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.setMaxY(100);
        decoShrub.setStrengthFactor(3f);
        this.addDeco(decoShrub);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.getDistribution().setNoiseDivisor(80f);
        decoFallenTree.getDistribution().setNoiseFactor(60f);
        decoFallenTree.getDistribution().setNoiseAddend(-15f);
        decoFallenTree.setLogCondition(DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenTree.setLogConditionNoise(0f);
        decoFallenTree.setLogConditionChance(6);
        decoFallenTree.setLogBlock(BlockUtil.getStateLog2(1));
        decoFallenTree.setLeavesBlock(BlockUtil.getStateLeaf2(1));
        decoFallenTree.setMinSize(3);
        decoFallenTree.setMaxSize(6);
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoPumpkin decoPumpkin = new DecoPumpkin();
        decoPumpkin.setMaxY(90);
        decoPumpkin.setRandomType(DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH);
        decoPumpkin.setRandomFloat(50f);
        this.addDeco(decoPumpkin);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.setMaxY(128);
        decoGrass.setStrengthFactor(12f);
        this.addDeco(decoGrass);
    }
}
