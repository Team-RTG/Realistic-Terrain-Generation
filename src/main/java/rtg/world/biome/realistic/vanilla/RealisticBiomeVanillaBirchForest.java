package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenTrees;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBirchForest;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBirchForest;
import rtg.world.gen.terrain.vanilla.TerrainVanillaBirchForest;

public class RealisticBiomeVanillaBirchForest extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaBirchForest(BiomeConfig config) {

        super(config,
            BiomeGenBase.birchForest,
            BiomeGenBase.river,
            new TerrainVanillaBirchForest(),
            new SurfaceVanillaBirchForest(config, BiomeGenBase.birchForest.topBlock, BiomeGenBase.birchForest.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt.getStateFromMeta(2), 0.15f)
        );

        TreeRTG birchSmall = new TreeRTGBetulaPapyrifera();
        birchSmall.setLogBlock(Blocks.log.getStateFromMeta(2));
        birchSmall.setLeavesBlock(Blocks.leaves.getStateFromMeta(2));
        birchSmall.setMinTrunkSize(4);
        birchSmall.setMaxTrunkSize(10);
        birchSmall.setMinCrownSize(8);
        birchSmall.setMaxCrownSize(19);
        this.addTree(birchSmall);

        DecoTree smallBirch = new DecoTree(birchSmall);
        smallBirch.strengthNoiseFactorForLoops = true;
        smallBirch.treeType = DecoTree.TreeType.RTG_TREE;
        smallBirch.distribution.noiseDivisor = 80f;
        smallBirch.distribution.noiseFactor = 60f;
        smallBirch.distribution.noiseAddend = -15f;
        smallBirch.treeCondition = DecoTree.TreeCondition.ALWAYS_GENERATE;
        smallBirch.maxY = 120;
        this.addDeco(smallBirch);

        TreeRTG birchTree = new TreeRTGBetulaPapyrifera();
        birchTree.setLogBlock(Blocks.log.getStateFromMeta(2));
        birchTree.setLeavesBlock(Blocks.leaves.getStateFromMeta(2));
        birchTree.setMinTrunkSize(4);
        birchTree.setMaxTrunkSize(10);
        birchTree.setMinCrownSize(8);
        birchTree.setMaxCrownSize(19);
        this.addTree(birchTree);

        DecoTree birchTrees = new DecoTree(birchTree);
        birchTrees.strengthFactorForLoops = 3f;
        birchTrees.treeType = DecoTree.TreeType.RTG_TREE;
        birchTrees.treeCondition = DecoTree.TreeCondition.ALWAYS_GENERATE;
        birchTrees.maxY = 100;

        DecoTree rtgTrees = new DecoTree(new WorldGenTrees(false));
        rtgTrees.treeType = DecoTree.TreeType.WORLDGEN;
        rtgTrees.strengthFactorForLoops = 3f;
        rtgTrees.treeCondition = DecoTree.TreeCondition.ALWAYS_GENERATE;
        rtgTrees.maxY = 100;

        DecoTree vanillaTrees = new DecoTree(new WorldGenForest(false, false));
        vanillaTrees.treeType = DecoTree.TreeType.WORLDGEN;
        vanillaTrees.strengthFactorForLoops = 3f;
        vanillaTrees.treeCondition = DecoTree.TreeCondition.ALWAYS_GENERATE;
        vanillaTrees.maxY = 100;

        DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
        decoHelperRandomSplit.decos = new DecoBase[]{birchTrees, rtgTrees, vanillaTrees};
        decoHelperRandomSplit.chances = new int[]{10, 4, 1};
        this.addDeco(decoHelperRandomSplit);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 8;
        decoFallenTree.logBlock = Blocks.log.getStateFromMeta(2);
        decoFallenTree.leavesBlock = Blocks.leaves.getStateFromMeta(2);
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaBirchForest.decorationLogsId));

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 120;
        decoShrub.strengthFactor = 3f;
        this.addDeco(decoShrub);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 3;
        this.addDeco(decoBaseBiomeDecorations);

        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[]{3, 6};
        decoFlowersRTG.maxY = 128;
        decoFlowersRTG.strengthFactor = 12f;
        this.addDeco(decoFlowersRTG);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 20f;
        this.addDeco(decoGrass);
    }
}
