package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaTaiga;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoMushrooms;
import rtg.world.biome.deco.DecoPumpkin;
import rtg.world.biome.deco.DecoShrubCustom;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.surface.vanilla.SurfaceVanillaTaiga;
import rtg.world.gen.terrain.vanilla.TerrainVanillaTaiga;

public class RealisticBiomeVanillaTaiga extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.taiga.topBlock;
    public static Block fillerBlock = BiomeGenBase.taiga.fillerBlock;
    
    public RealisticBiomeVanillaTaiga(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.taiga,
            BiomeGenBase.river,
            new TerrainVanillaTaiga(),
            new SurfaceVanillaTaiga(config, topBlock, fillerBlock)
        );
        
		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */
        
		DecoTree bigSpruceTrees1 = new DecoTree();
		bigSpruceTrees1.strengthFactorForLoops = 4f;
		bigSpruceTrees1.treeType = TreeType.TAIGA_SPRUCE_TALL;
		bigSpruceTrees1.distribution.noiseDivisor = 100f;
		bigSpruceTrees1.distribution.noiseFactor = 6f;
		bigSpruceTrees1.distribution.noiseAddend = 0.8f;
		bigSpruceTrees1.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		bigSpruceTrees1.treeConditionNoise = 0f;
		bigSpruceTrees1.treeConditionChance = 1;
		bigSpruceTrees1.maxY = 110;
		this.addDeco(bigSpruceTrees1);
		
		DecoTree bigSpruceTrees2 = new DecoTree();
		bigSpruceTrees2.strengthFactorForLoops = 4f;
		bigSpruceTrees2.treeType = TreeType.TAIGA_SPRUCE_TALL;
		bigSpruceTrees2.distribution.noiseDivisor = 80f;
		bigSpruceTrees2.distribution.noiseFactor = 60f;
		bigSpruceTrees2.distribution.noiseAddend = -15f;
		bigSpruceTrees2.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		bigSpruceTrees2.treeConditionNoise = 0f;
		bigSpruceTrees2.treeConditionChance = 1;
		bigSpruceTrees2.maxY = 110;
		this.addDeco(bigSpruceTrees2);
		
		DecoTree bigSpruceTrees3 = new DecoTree();
		bigSpruceTrees3.strengthFactorForLoops = 3f;
		bigSpruceTrees3.treeType = TreeType.TAIGA_SPRUCE_TALL;
		bigSpruceTrees3.treeCondition = TreeCondition.RANDOM_CHANCE;
		bigSpruceTrees3.treeConditionChance = 2;
		bigSpruceTrees3.maxY = 120;
		this.addDeco(bigSpruceTrees3);
		
		DecoTree decoTrees = new DecoTree();
		decoTrees.strengthFactorForLoops = 4f;
		decoTrees.treeType = TreeType.TAIGA_PINE_TALL;
		decoTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
		decoTrees.treeConditionChance = 3;
		decoTrees.maxY = 120;
		this.addDeco(decoTrees);
		
//		DecoTree smallSpruceTrees = new DecoTree();
//		smallSpruceTrees.strengthFactorForLoops = 2f;
//		smallSpruceTrees.treeType = TreeType.TAIGA_SPRUCE_SMALL;
//		smallSpruceTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
//		smallSpruceTrees.treeConditionChance = 3;
//		smallSpruceTrees.maxY = 110;
//		this.addDeco(smallSpruceTrees);

		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 100f;
		decoFallenTree.distribution.noiseFactor = 6f;
		decoFallenTree.distribution.noiseAddend = 0.8f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 12;
		decoFallenTree.maxY = 100;
		decoFallenTree.logBlock = Blocks.log;
		decoFallenTree.logMeta = (byte)1;
		decoFallenTree.leavesBlock = Blocks.leaves;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 6;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaTaiga.decorationLogsId));
		
		DecoShrubCustom decoShrubSpruce = new DecoShrubCustom();
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
		decoGrass.strengthFactor = 10f;
		this.addDeco(decoGrass);
    }
}
