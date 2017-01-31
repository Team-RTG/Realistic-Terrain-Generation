package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

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

    public static BiomeGenBase bopBiome = BOPBiomes.land_of_lakes.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPLandOfLakes(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.river,
            new TerrainBOPLandOfLakes(58f, 76f, 36f),
            new SurfaceBOPLandOfLakes(config, Blocks.grass.getDefaultState(), Blocks.dirt.getDefaultState(), false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.stone.getDefaultState(), 0.10f)
        );

        TreeRTG birchTree = new TreeRTGBetulaPapyrifera();
        birchTree.setLogBlock(Blocks.log.getStateFromMeta(2));
        birchTree.setLeavesBlock(Blocks.leaves.getStateFromMeta(2));
        birchTree.setMinTrunkSize(4);
        birchTree.setMaxTrunkSize(10);
        birchTree.setMinCrownSize(8);
        birchTree.setMaxCrownSize(19);
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
        sitchensisTree.setLogBlock(Blocks.log.getStateFromMeta(1));
        sitchensisTree.setLeavesBlock(Blocks.leaves.getStateFromMeta(1));
        sitchensisTree.setMinTrunkSize(4);
        sitchensisTree.setMaxTrunkSize(9);
        sitchensisTree.setMinCrownSize(5);
        sitchensisTree.setMaxCrownSize(14);
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
        decoFallenTree.randomLogBlocks = new IBlockState[]{Blocks.log.getDefaultState(), Blocks.log.getStateFromMeta(1), Blocks.log.getStateFromMeta(2)};
        decoFallenTree.minSize = 8;
        decoFallenTree.maxSize = 12;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPLandOfLakes.decorationLogsId));

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 3f;
        this.addDeco(decoShrub);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.cobblestone.getDefaultState();
        decoBoulder.maxY = 80;
        decoBoulder.chance = 12;
        decoBoulder.strengthFactor = 1f;
        this.addDeco(decoBoulder);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
