package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSwamplandM;
import rtg.util.BlockUtil;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;
import rtg.world.gen.feature.tree.rtg.TreeRTGSalixMyrtilloides;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSwamplandM;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaSwamplandM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_SWAMPLAND;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaSwamplandM(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.vanilla.TerrainVanillaSwamplandM(50f, 25f, 60f),
            new SurfaceVanillaSwamplandM(config, biome.topBlock, biome.fillerBlock)
        );

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
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaSwamplandM.decorationLogsId));

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
}
