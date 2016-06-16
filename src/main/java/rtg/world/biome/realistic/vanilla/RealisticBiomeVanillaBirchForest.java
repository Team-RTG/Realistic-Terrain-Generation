package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenForest;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBirchForest;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import rtg.world.gen.feature.tree.vanilla.WorldGenTreesRTG;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBirchForest;
import rtg.world.gen.terrain.vanilla.TerrainVanillaBirchForest;

public class RealisticBiomeVanillaBirchForest extends RealisticBiomeVanillaBase
{

    public RealisticBiomeVanillaBirchForest(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.birchForest,
            BiomeGenBase.river,
            new TerrainVanillaBirchForest(),
            new SurfaceVanillaBirchForest(config, BiomeGenBase.birchForest.topBlock, BiomeGenBase.birchForest.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.15f)
        );
        
		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */
        
        TreeRTG birchSmall = new TreeRTGBetulaPapyrifera();
		birchSmall.logBlock = Blocks.log;
		birchSmall.logMeta = (byte)2;
		birchSmall.leavesBlock = Blocks.leaves;
		birchSmall.leavesMeta = (byte)2;
		birchSmall.minTrunkSize = 4;
		birchSmall.maxTrunkSize = 10;
		birchSmall.minCrownSize = 8;
		birchSmall.maxCrownSize = 19;
		this.addTree(birchSmall);
        
		DecoTree smallBirch = new DecoTree(birchSmall);
		smallBirch.strengthNoiseFactorForLoops = true;
		smallBirch.treeType = TreeType.RTG_TREE;
		smallBirch.distribution.noiseDivisor = 80f;
		smallBirch.distribution.noiseFactor = 60f;
		smallBirch.distribution.noiseAddend = -15f;
		smallBirch.treeCondition = TreeCondition.ALWAYS_GENERATE;
		smallBirch.maxY = 120;
		this.addDeco(smallBirch);
		
        TreeRTG birchTree = new TreeRTGBetulaPapyrifera();
		birchTree.logBlock = Blocks.log;
		birchTree.logMeta = (byte)2;
		birchTree.leavesBlock = Blocks.leaves;
		birchTree.leavesMeta = (byte)2;
		birchTree.minTrunkSize = 4;
		birchTree.maxTrunkSize = 10;
		birchTree.minCrownSize = 8;
		birchTree.maxCrownSize = 19;
		this.addTree(birchTree);

		DecoTree birchTrees = new DecoTree(birchTree);
		birchTrees.strengthFactorForLoops = 3f;
		birchTrees.treeType = TreeType.RTG_TREE;
		birchTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
		birchTrees.maxY = 100;
		
		DecoTree rtgTrees = new DecoTree(new WorldGenTreesRTG(false));
		rtgTrees.treeType = TreeType.WORLDGEN;
		rtgTrees.strengthFactorForLoops = 3f;
		rtgTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
		rtgTrees.maxY = 100;
		
		DecoTree vanillaTrees = new DecoTree(new WorldGenForest(false, false));
		vanillaTrees.treeType = TreeType.WORLDGEN;
		vanillaTrees.strengthFactorForLoops = 3f;
		vanillaTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
		vanillaTrees.maxY = 100;
		
		DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
		decoHelperRandomSplit.decos = new DecoBase[]{birchTrees, rtgTrees, vanillaTrees};
		decoHelperRandomSplit.chances = new int[]{10, 4, 1};
		this.addDeco(decoHelperRandomSplit);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 8;
        decoFallenTree.logBlock = Blocks.log;
        decoFallenTree.logMeta = (byte)2;
        decoFallenTree.leavesBlock = Blocks.leaves;
        decoFallenTree.leavesMeta = (byte)-1;
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
		decoFlowersRTG.flowers = new int[] {3, 6};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.strengthFactor = 12f;
        this.addDeco(decoFlowersRTG);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 20f;
        this.addDeco(decoGrass);
    }
}
