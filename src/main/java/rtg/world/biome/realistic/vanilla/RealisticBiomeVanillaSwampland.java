package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSwampland;
import rtg.world.biome.deco.*;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;
import rtg.world.gen.feature.tree.rtg.TreeRTGSalixMyrtilloides;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSwampland;
import rtg.world.gen.terrain.vanilla.TerrainVanillaSwampland;

public class RealisticBiomeVanillaSwampland extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.SWAMPLAND;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaSwampland(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaSwampland(),
            new SurfaceVanillaSwampland(config, biome.topBlock, biome.fillerBlock)
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
        decoTrees.maxY = 70;
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
        decoFallenTree.logBlock = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        decoFallenTree.leavesBlock = Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaSwampland.decorationLogsId));

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
