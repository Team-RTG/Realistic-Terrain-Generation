package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLRedwoodForest;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLRedwoodForest;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLRedwoodForest;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLRedwoodForest extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.redwoodforest.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLRedwoodForest(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.river,
			new TerrainEBXLRedwoodForest(),
			new SurfaceEBXLRedwoodForest(config, topBlock, fillerBlock, false, null, 3f, 4.5f, 60f, 65f, 4.5f, Blocks.dirt, (byte)2, 0.10f)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.notEqualsZeroChance = 3;
		this.addDeco(decoBaseBiomeDecorations);        
        
        DecoFallenTree decoFallenTreeOak = new DecoFallenTree();
        decoFallenTreeOak.distribution.noiseDivisor = 80f;
        decoFallenTreeOak.distribution.noiseFactor = 60f;
        decoFallenTreeOak.distribution.noiseAddend = -15f;
        decoFallenTreeOak.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTreeOak.logConditionNoise = 0f;
        decoFallenTreeOak.logConditionChance = 24;
        decoFallenTreeOak.maxY = 100;
        decoFallenTreeOak.logBlock = Blocks.log;
        decoFallenTreeOak.logMeta = (byte)0;
        decoFallenTreeOak.leavesBlock = Blocks.leaves;
        decoFallenTreeOak.leavesMeta = (byte)-1;
        decoFallenTreeOak.minSize = 2;
        decoFallenTreeOak.maxSize = 3;
        
        DecoFallenTree decoFallenTreeSpruce = new DecoFallenTree();
        decoFallenTreeSpruce.distribution.noiseDivisor = 80f;
        decoFallenTreeSpruce.distribution.noiseFactor = 60f;
        decoFallenTreeSpruce.distribution.noiseAddend = -15f;
        decoFallenTreeSpruce.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTreeSpruce.logConditionNoise = 0f;
        decoFallenTreeSpruce.logConditionChance = 24;
        decoFallenTreeSpruce.maxY = 100;
        decoFallenTreeSpruce.logBlock = Blocks.log;
        decoFallenTreeSpruce.logMeta = (byte)1;
        decoFallenTreeSpruce.leavesBlock = Blocks.leaves;
        decoFallenTreeSpruce.leavesMeta = (byte)-1;
        decoFallenTreeSpruce.minSize = 2;
        decoFallenTreeSpruce.maxSize = 3;        
        
        DecoHelper5050 DecoHelper5050 = new DecoHelper5050(decoFallenTreeOak, decoFallenTreeSpruce);
		this.addDeco(decoFallenTreeOak, this.config._boolean(BiomeConfigEBXLRedwoodForest.decorationLogsId)); 
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 3f;
		this.addDeco(decoShrub);
        
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.flowers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.chance = 4;
		decoFlowersRTG.strengthFactor = 8f;
        this.addDeco(decoFlowersRTG);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
	}
}
