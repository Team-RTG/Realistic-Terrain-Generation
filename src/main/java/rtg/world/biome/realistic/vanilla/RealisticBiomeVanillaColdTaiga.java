package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaColdTaiga;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoMushrooms;
import rtg.world.biome.deco.DecoPumpkin;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat.ChanceType;
import rtg.world.gen.feature.tree.rtg.TreeRTGCupressusSempervirens;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdTaiga;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdTaiga;

public class RealisticBiomeVanillaColdTaiga extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.coldTaiga.topBlock;
    public static Block fillerBlock = BiomeGenBase.coldTaiga.fillerBlock;
    
    public RealisticBiomeVanillaColdTaiga(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.coldTaiga,
            BiomeGenBase.frozenRiver,
            new TerrainVanillaColdTaiga(),
            new SurfaceVanillaColdTaiga(config, topBlock, fillerBlock)
        );
        
		DecoTree bigSpruceTrees1 = new DecoTree(new TreeRTGCupressusSempervirens());
		bigSpruceTrees1.logBlock = Blocks.log;
		bigSpruceTrees1.logMeta = (byte)1;
		bigSpruceTrees1.leavesBlock = Blocks.leaves;
		bigSpruceTrees1.leavesMeta = (byte)1;
		bigSpruceTrees1.minTrunkSize = 3;
		bigSpruceTrees1.maxTrunkSize = 7;
		bigSpruceTrees1.minCrownSize = 5;
		bigSpruceTrees1.maxCrownSize = 10;
		bigSpruceTrees1.strengthFactorForLoops = 4f;
		bigSpruceTrees1.treeType = TreeType.RTG_TREE;
		bigSpruceTrees1.distribution.noiseDivisor = 100f;
		bigSpruceTrees1.distribution.noiseFactor = 6f;
		bigSpruceTrees1.distribution.noiseAddend = 0.8f;
		bigSpruceTrees1.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		bigSpruceTrees1.treeConditionNoise = 0f;
		bigSpruceTrees1.treeConditionChance = 1;
		bigSpruceTrees1.maxY = 110;
		DecoTree bigSpruceOakTrees1 = new DecoTree(bigSpruceTrees1);
		bigSpruceOakTrees1.leavesMeta = (byte)0;
		DecoHelperThisOrThat decoHelperThisOrThat1 = new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, bigSpruceTrees1, bigSpruceOakTrees1);
		this.addDeco(decoHelperThisOrThat1);
		
		DecoTree bigSpruceTrees2 = new DecoTree(new TreeRTGCupressusSempervirens());
		bigSpruceTrees2.logBlock = Blocks.log;
		bigSpruceTrees2.logMeta = (byte)1;
		bigSpruceTrees2.leavesBlock = Blocks.leaves;
		bigSpruceTrees2.leavesMeta = (byte)1;
		bigSpruceTrees2.minTrunkSize = 3;
		bigSpruceTrees2.maxTrunkSize = 7;
		bigSpruceTrees2.minCrownSize = 5;
		bigSpruceTrees2.maxCrownSize = 10;
		bigSpruceTrees2.strengthFactorForLoops = 4f;
		bigSpruceTrees2.treeType = TreeType.RTG_TREE;
		bigSpruceTrees2.distribution.noiseDivisor = 80f;
		bigSpruceTrees2.distribution.noiseFactor = 60f;
		bigSpruceTrees2.distribution.noiseAddend = -15f;
		bigSpruceTrees2.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		bigSpruceTrees2.treeConditionNoise = 0f;
		bigSpruceTrees2.treeConditionChance = 1;
		bigSpruceTrees2.maxY = 110;
		DecoTree bigSpruceOakTrees2 = new DecoTree(bigSpruceTrees2);
		bigSpruceOakTrees2.leavesMeta = (byte)0;
		DecoHelperThisOrThat decoHelperThisOrThat2 = new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, bigSpruceTrees2, bigSpruceOakTrees2);
		this.addDeco(decoHelperThisOrThat2);
		
		DecoTree bigSpruceTrees3 = new DecoTree(new TreeRTGCupressusSempervirens());
		bigSpruceTrees3.logBlock = Blocks.log;
		bigSpruceTrees3.logMeta = (byte)1;
		bigSpruceTrees3.leavesBlock = Blocks.leaves;
		bigSpruceTrees3.leavesMeta = (byte)1;
		bigSpruceTrees3.minTrunkSize = 3;
		bigSpruceTrees3.maxTrunkSize = 7;
		bigSpruceTrees3.minCrownSize = 5;
		bigSpruceTrees3.maxCrownSize = 10;
		bigSpruceTrees3.strengthFactorForLoops = 3f;
		bigSpruceTrees3.treeType = TreeType.RTG_TREE;
		bigSpruceTrees3.distribution.noiseDivisor = 80f;
		bigSpruceTrees3.distribution.noiseFactor = 60f;
		bigSpruceTrees3.distribution.noiseAddend = -15f;
		bigSpruceTrees3.treeCondition = TreeCondition.RANDOM_CHANCE;
		bigSpruceTrees3.treeConditionChance = 2;
		bigSpruceTrees3.maxY = 120;
		DecoTree bigSpruceOakTrees3 = new DecoTree(bigSpruceTrees3);
		bigSpruceOakTrees3.leavesMeta = (byte)0;
		DecoHelperThisOrThat decoHelperThisOrThat3 = new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, bigSpruceTrees3, bigSpruceOakTrees3);
		this.addDeco(decoHelperThisOrThat3);
		
		DecoTree decoTrees = new DecoTree(new TreeRTGPiceaSitchensis());
		decoTrees.logBlock = Blocks.log;
		decoTrees.logMeta = (byte)1;
		decoTrees.leavesBlock = Blocks.leaves;
		decoTrees.leavesMeta = (byte)1;
		decoTrees.minTrunkSize = 4;
		decoTrees.maxTrunkSize = 9;
		decoTrees.minCrownSize = 5;
		decoTrees.maxCrownSize = 14;
		decoTrees.strengthFactorForLoops = 4f;
		decoTrees.treeType = TreeType.RTG_TREE;
		decoTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
		decoTrees.treeConditionChance = 3;
		decoTrees.maxY = 120;
		this.addDeco(decoTrees);

		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 100f;
		decoFallenTree.distribution.noiseFactor = 6f;
		decoFallenTree.distribution.noiseAddend = 0.8f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 12;
		decoFallenTree.logBlock = Blocks.log;
		decoFallenTree.logMeta = (byte)1;
		decoFallenTree.leavesBlock = Blocks.leaves;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 6;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaColdTaiga.decorationLogsId));
		
		DecoShrub decoShrubSpruce = new DecoShrub();
		decoShrubSpruce.logBlock = Blocks.log;
		decoShrubSpruce.logMeta = 1;
		decoShrubSpruce.leavesBlock = Blocks.leaves;
		decoShrubSpruce.leavesMeta = 1;
		decoShrubSpruce.maxY = 100;
		decoShrubSpruce.strengthFactor = 3f;
		decoShrubSpruce.chance = 6;
		this.addDeco(decoShrubSpruce);

//		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
//		decoBaseBiomeDecorations.equalsZeroChance = 3;
//		this.addDeco(decoBaseBiomeDecorations);
  
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.mossy_cobblestone;
		decoBoulder.chance = 20;
		decoBoulder.maxY = 95;
		decoBoulder.strengthFactor = 2f;
		this.addDeco(decoBoulder);
		
		DecoPumpkin decoPumpkin = new DecoPumpkin();
		decoPumpkin.maxY = 90;
		decoPumpkin.randomType = rtg.world.biome.deco.DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH;
		decoPumpkin.randomFloat = 32f;
		this.addDeco(decoPumpkin);
		
		DecoMushrooms decoMushrooms = new DecoMushrooms();
		decoMushrooms.maxY = 90;
		decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.X_DIVIDED_BY_STRENGTH;
		decoMushrooms.randomFloat = 24f;
		this.addDeco(decoMushrooms);
		
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 8f;
		this.addDeco(decoGrass);
    }
}
