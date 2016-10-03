package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPLandOfLakes;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLandOfLakes;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPLandOfLakes;

public class RealisticBiomeBOPLandOfLakes extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.land_of_lakes.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPLandOfLakes(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPLandOfLakes(58f, 76f, 36f),
            new SurfaceBOPLandOfLakes(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), 0f, 1.5f, 60f, 65f, 1.5f, Blocks.STONE.getDefaultState(), 0.10f)
        );

        TreeRTG birchTree = new TreeRTGBetulaPapyrifera();
        birchTree.logBlock = Blocks.LOG.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.BIRCH);
        birchTree.leavesBlock = Blocks.LEAVES.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.BIRCH);
        birchTree.minTrunkSize = 4;
        birchTree.maxTrunkSize = 10;
        birchTree.minCrownSize = 8;
        birchTree.maxCrownSize = 19;
        this.addTree(birchTree);

        DecoTree birchTrees = new DecoTree(birchTree);
        birchTrees.strengthFactorForLoops = 9f;
        birchTrees.treeType = DecoTree.TreeType.RTG_TREE;
        birchTrees.distribution.noiseDivisor = 100f;
        birchTrees.distribution.noiseFactor = 6f;
        birchTrees.distribution.noiseAddend = 0.8f;
        birchTrees.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        birchTrees.treeConditionChance = 1;
        birchTrees.treeConditionNoise = 0f;
        birchTrees.maxY = 120;

        TreeRTG sitchensisTree = new TreeRTGPiceaSitchensis();
        sitchensisTree.logBlock = Blocks.LOG.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
        sitchensisTree.leavesBlock = Blocks.LEAVES.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE);
        sitchensisTree.minTrunkSize = 4;
        sitchensisTree.maxTrunkSize = 9;
        sitchensisTree.minCrownSize = 5;
        sitchensisTree.maxCrownSize = 14;
        this.addTree(sitchensisTree);

        DecoTree smallPine = new DecoTree(sitchensisTree);
        smallPine.strengthFactorForLoops = 9f;
        smallPine.treeType = DecoTree.TreeType.RTG_TREE;
        smallPine.distribution.noiseDivisor = 100f;
        smallPine.distribution.noiseFactor = 6f;
        smallPine.distribution.noiseAddend = 0.8f;
        smallPine.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        smallPine.treeConditionChance = 1;
        smallPine.treeConditionNoise = 0f;
        smallPine.maxY = 120;

        DecoHelper5050 decoHelper5050 = new DecoHelper5050(birchTrees, smallPine);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 12;
        decoFallenTree.randomLogBlocks = new IBlockState[]{Blocks.LOG.getDefaultState(), Blocks.LOG.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.SPRUCE), Blocks.LOG.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.BIRCH)};
        decoFallenTree.minSize = 8;
        decoFallenTree.maxSize = 12;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPLandOfLakes.decorationLogsId));

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 3f;
        this.addDeco(decoShrub);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.maxY = 80;
        decoBoulder.chance = 12;
        decoBoulder.strengthFactor = 1f;
        this.addDeco(decoBoulder);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
