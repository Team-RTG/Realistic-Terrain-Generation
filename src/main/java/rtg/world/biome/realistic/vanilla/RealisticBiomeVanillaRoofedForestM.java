package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.world.RTGWorld;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.CliffCalculator;
import rtg.world.biome.deco.*;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGRhizophoraMucronata;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaRoofedForestM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_ROOFED_FOREST;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaRoofedForestM() {

        super(biome, river);

        this.noLakes = true;
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);

        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK_META).set(0);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaRoofedForestM();
    }

    public class TerrainVanillaRoofedForestM extends TerrainBase {

        public TerrainVanillaRoofedForestM() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainGrasslandMountains(x, y, rtgWorld.simplex, rtgWorld.cell, river, 4f, 50f, 68f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaRoofedForestM(config, biome.topBlock, biome.fillerBlock);
    }

    public class SurfaceVanillaRoofedForestM extends SurfaceBase {

        public SurfaceVanillaRoofedForestM(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand;
            float c = CliffCalculator.calc(x, z, noise);
            boolean cliff = c > 1.4f ? true : false;

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

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.MOSSY_COBBLESTONE.getDefaultState();
        decoBoulder.chance = 20;
        decoBoulder.maxY = 80;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        TreeRTG mucronataTree = new TreeRTGRhizophoraMucronata(3, 4, 13f, 0.32f, 0.1f);
        mucronataTree.logBlock = BlockUtil.getStateLog2(1);
        mucronataTree.leavesBlock = BlockUtil.getStateLeaf2(1);
        mucronataTree.minTrunkSize = 3;
        mucronataTree.maxTrunkSize = 4;
        mucronataTree.minCrownSize = 7;
        mucronataTree.maxCrownSize = 12;
        this.addTree(mucronataTree);

        DecoTree decoTrees = new DecoTree(mucronataTree);
        decoTrees.strengthFactorForLoops = 24f;
        decoTrees.distribution.noiseDivisor = 80f;
        decoTrees.distribution.noiseFactor = 60f;
        decoTrees.distribution.noiseAddend = -15f;
        decoTrees.treeType = DecoTree.TreeType.RTG_TREE;
        decoTrees.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoTrees.treeConditionNoise = 0f;
        decoTrees.treeConditionChance = 1;
        decoTrees.maxY = 120;
        this.addDeco(decoTrees);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.ALWAYS_GENERATE;
        decoFallenTree.logConditionChance = 1;
        decoFallenTree.loops = 4;
        decoFallenTree.logBlock = BlockUtil.getStateLog2(1);
        decoFallenTree.leavesBlock = BlockUtil.getStateLeaf2(1);
        decoFallenTree.minSize = 4;
        decoFallenTree.maxSize = 9;
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 1f;
        this.addDeco(decoShrub);

        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.maxY = 128;
        decoGrassDoubleTallgrass.strengthFactor = 8f;
        decoGrassDoubleTallgrass.doubleGrassChance = 6;
        this.addDeco(decoGrassDoubleTallgrass);

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.maxY = 128;
        decoDeadBush.chance = 16;
        decoDeadBush.strengthFactor = 1f;
        this.addDeco(decoDeadBush);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 4f;
        decoGrass.chance = 2;
        this.addDeco(decoGrass);

        DecoGrass decoFern = new DecoGrass(2);
        decoFern.maxY = 128;
        decoFern.strengthFactor = 4f;
        decoFern.chance = 2;
        this.addDeco(decoFern);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.maxY = 90;
        decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.ALWAYS_GENERATE;
        this.addDeco(decoMushrooms);
    }
}
