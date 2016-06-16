package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPLandOfLakes;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLandOfLakes;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPLandOfLakes;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPLandOfLakes extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.landOfLakes;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPLandOfLakes(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPLandOfLakes(58f, 76f, 36f),
			new SurfaceBOPLandOfLakes(config, Blocks.grass, Blocks.dirt, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.stone, 0.10f)
		);
		
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
		birchTrees.strengthFactorForLoops = 9f;
		birchTrees.treeType = TreeType.RTG_TREE;
		birchTrees.distribution.noiseDivisor = 100f;
		birchTrees.distribution.noiseFactor = 6f;
		birchTrees.distribution.noiseAddend = 0.8f;
		birchTrees.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		birchTrees.treeConditionChance = 1;
		birchTrees.treeConditionNoise = 0f;
		birchTrees.maxY = 120;
		
		TreeRTG sitchensisTree = new TreeRTGPiceaSitchensis();
    	sitchensisTree.logBlock = Blocks.log;
    	sitchensisTree.logMeta = (byte)1;
    	sitchensisTree.leavesBlock = Blocks.leaves;
    	sitchensisTree.leavesMeta = (byte)1;
    	sitchensisTree.minTrunkSize = 4;
    	sitchensisTree.maxTrunkSize = 9;
    	sitchensisTree.minCrownSize = 5;
    	sitchensisTree.maxCrownSize = 14;
		this.addTree(sitchensisTree);
		
		DecoTree smallPine = new DecoTree(sitchensisTree);
    	smallPine.strengthFactorForLoops = 9f;
    	smallPine.treeType = TreeType.RTG_TREE;
    	smallPine.distribution.noiseDivisor = 100f;
    	smallPine.distribution.noiseFactor = 6f;
    	smallPine.distribution.noiseAddend = 0.8f;
    	smallPine.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
    	smallPine.treeConditionChance = 1;
    	smallPine.treeConditionNoise = 0f;
    	smallPine.maxY = 120;
    	
    	DecoHelper5050 decoHelper5050 = new DecoHelper5050(birchTrees, smallPine);

		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 100f;
		decoFallenTree.distribution.noiseFactor = 6f;
		decoFallenTree.distribution.noiseAddend = 0.8f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 12;
		decoFallenTree.randomLogBlocks = new Block[]{Blocks.log, Blocks.log, Blocks.log};
		decoFallenTree.randomLogMetas = new byte[]{0, 1, 2};
		decoFallenTree.minSize = 8;
		decoFallenTree.maxSize = 12;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPLandOfLakes.decorationLogsId));
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 3f;
		this.addDeco(decoShrub);
        
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.cobblestone;
		decoBoulder.maxY = 80;
		decoBoulder.chance = 12;
		decoBoulder.strengthFactor = 1f;
		this.addDeco(decoBoulder);
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
