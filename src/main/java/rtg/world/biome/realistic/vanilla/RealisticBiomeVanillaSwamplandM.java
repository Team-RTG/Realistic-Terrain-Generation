package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.config.BiomeConfig;
import rtg.util.BlockUtil;
import rtg.api.util.noise.CellNoise;
import rtg.util.CliffCalculator;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;
import rtg.world.gen.feature.tree.rtg.TreeRTGSalixMyrtilloides;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaSwamplandM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_SWAMPLAND;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaSwamplandM() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
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
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainLonelyMountain(x, y, simplex, cell, river, strength, width, terrainHeight);
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
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

            float c = CliffCalculator.calc(x, y, noise);
            boolean cliff = c > 1.4f ? true : false;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, y).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff && k > 64) {
                        if (depth > -1 && depth < 2) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, y, hcCobble(world, i, j, x, y, k));
                            }
                            else {

                                primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                        }
                    }
                    else {
                        if (depth == 0 && k > 61) {
                            primer.setBlockState(x, k, y, topBlock);
                        }
                        else if (depth < 4) {
                            primer.setBlockState(x, k, y, fillerBlock);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void initDecos() {

        TreeRTG myrtilloidesTree = new TreeRTGSalixMyrtilloides();
        myrtilloidesTree.logBlock = Blocks.LOG.getDefaultState();
        myrtilloidesTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        this.addTree(myrtilloidesTree);

        DecoTree decoTrees = new DecoTree(myrtilloidesTree);
        decoTrees.strengthNoiseFactorXForLoops = true;
        decoTrees.strengthFactorForLoops = 1f;
        decoTrees.distribution.noiseDivisor = 80f;
        decoTrees.distribution.noiseFactor = 60f;
        decoTrees.distribution.noiseAddend = -15f;
        decoTrees.treeType = DecoTree.TreeType.RTG_TREE;
        decoTrees.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        decoTrees.treeConditionChance = 12;
        decoTrees.maxY = 100;
        this.addDeco(decoTrees);

        TreeRTG ponderosaTree = new TreeRTGPinusPonderosa();
        ponderosaTree.logBlock = Blocks.LOG.getDefaultState();
        ponderosaTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        ponderosaTree.minTrunkSize = 3;
        ponderosaTree.maxTrunkSize = 6;
        ponderosaTree.minCrownSize = 6;
        ponderosaTree.maxCrownSize = 14;
        ponderosaTree.noLeaves = true;
        this.addTree(ponderosaTree);

        DecoTree deadPineTree = new DecoTree(ponderosaTree);
        deadPineTree.treeType = DecoTree.TreeType.RTG_TREE;
        deadPineTree.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        deadPineTree.treeConditionChance = 18;
        deadPineTree.maxY = 100;
        this.addDeco(deadPineTree);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 100;
        decoShrub.strengthFactor = 3f;
        this.addDeco(decoShrub);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 6;
        decoFallenTree.logBlock = BlockUtil.getStateLog2(1);
        decoFallenTree.leavesBlock = BlockUtil.getStateLeaf2(1);
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoPumpkin decoPumpkin = new DecoPumpkin();
        decoPumpkin.maxY = 90;
        decoPumpkin.randomType = rtg.world.biome.deco.DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH;
        decoPumpkin.randomFloat = 50f;
        this.addDeco(decoPumpkin);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
    }
}
