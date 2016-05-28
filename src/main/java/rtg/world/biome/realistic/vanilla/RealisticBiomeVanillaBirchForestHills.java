package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenForest;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBirchForestHills;
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
import rtg.world.gen.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import rtg.world.gen.feature.tree.vanilla.WorldGenTreesRTG;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBirchForestHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaBirchForestHills;

public class RealisticBiomeVanillaBirchForestHills extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.birchForestHills.topBlock;
    public static Block fillerBlock = BiomeGenBase.birchForestHills.fillerBlock;
	
	public RealisticBiomeVanillaBirchForestHills(BiomeConfig config)
	{
		super(config, 
			BiomeGenBase.birchForestHills,
			BiomeGenBase.river,
			new TerrainVanillaBirchForestHills(),
			new SurfaceVanillaBirchForestHills(config, Blocks.grass, Blocks.dirt, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.10f)
		);
        this.noLakes=true;
        
		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */
        
		DecoTree smallBirch = new DecoTree(new TreeRTGBetulaPapyrifera());
		smallBirch.logBlock = Blocks.log;
		smallBirch.logMeta = (byte)2;
		smallBirch.leavesBlock = Blocks.leaves;
		smallBirch.leavesMeta = (byte)2;
		smallBirch.minTrunkSize = 4;
		smallBirch.maxTrunkSize = 10;
		smallBirch.minCrownSize = 8;
		smallBirch.maxCrownSize = 19;
		smallBirch.strengthNoiseFactorForLoops = true;
		smallBirch.treeType = TreeType.RTG_TREE;
		smallBirch.distribution.noiseDivisor = 80f;
		smallBirch.distribution.noiseFactor = 60f;
		smallBirch.distribution.noiseAddend = -15f;
		smallBirch.treeCondition = TreeCondition.ALWAYS_GENERATE;
		smallBirch.maxY = 120;
		this.addDeco(smallBirch);
        
		DecoTree birchTrees = new DecoTree(new TreeRTGBetulaPapyrifera());
		birchTrees.logBlock = Blocks.log;
		birchTrees.logMeta = (byte)2;
		birchTrees.leavesBlock = Blocks.leaves;
		birchTrees.leavesMeta = (byte)2;
		birchTrees.minTrunkSize = 4;
		birchTrees.maxTrunkSize = 10;
		birchTrees.minCrownSize = 8;
		birchTrees.maxCrownSize = 19;
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
        decoFallenTree.maxY = 100;
        decoFallenTree.logBlock = Blocks.log;
        decoFallenTree.logMeta = (byte)2;
        decoFallenTree.leavesBlock = Blocks.leaves;
        decoFallenTree.leavesMeta = (byte)-1;
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;        
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaBirchForestHills.decorationLogsId));
        
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
