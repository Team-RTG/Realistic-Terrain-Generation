package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPDeadForest;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPDeadForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPDeadForest;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;

public class RealisticBiomeBOPDeadForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.deadForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPDeadForest(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPDeadForest(58f, 80f, 30f),
			new SurfaceBOPDeadForest(config,
                topBlock, //Block top 
                (byte)0, //byte topByte
                fillerBlock, //Block filler, 
                (byte)0, //byte fillerByte
                topBlock, //Block mixTop, 
                (byte)0, //byte mixTopByte, 
                fillerBlock, //Block mixFill, 
                (byte)0, //byte mixFillByte,
                80f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
		);
		
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.cobblestone;
		decoBoulder.chance = 16;
		decoBoulder.maxY = 95;
		decoBoulder.strengthFactor = 1f;
		this.addDeco(decoBoulder);
        
		DecoFallenTree decoFallenTree1 = new DecoFallenTree();
		decoFallenTree1.loops = 1;
		decoFallenTree1.distribution.noiseDivisor = 100f;
		decoFallenTree1.distribution.noiseFactor = 6f;
		decoFallenTree1.distribution.noiseAddend = 0.8f;
		decoFallenTree1.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree1.logConditionNoise = 0f;
		decoFallenTree1.logConditionChance = 10;
		decoFallenTree1.maxY = 100;
		decoFallenTree1.logBlock = BOPCBlocks.logs3;
		decoFallenTree1.logMeta = (byte)2;
		decoFallenTree1.leavesBlock = Blocks.leaves;
		decoFallenTree1.leavesMeta = (byte)-1;
		decoFallenTree1.minSize = 3;
		decoFallenTree1.maxSize = 5;
		
		DecoFallenTree decoFallenTree2 = new DecoFallenTree();
		decoFallenTree2.loops = 1;
		decoFallenTree2.distribution.noiseDivisor = 100f;
		decoFallenTree2.distribution.noiseFactor = 6f;
		decoFallenTree2.distribution.noiseAddend = 0.8f;
		decoFallenTree2.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree2.logConditionNoise = 0f;
		decoFallenTree2.logConditionChance = 10;
		decoFallenTree2.maxY = 100;
		decoFallenTree2.logBlock = Blocks.log;
		decoFallenTree2.logMeta = (byte)1;
		decoFallenTree2.leavesBlock = Blocks.leaves;
		decoFallenTree2.leavesMeta = (byte)-1;
		decoFallenTree2.minSize = 3;
		decoFallenTree2.maxSize = 5;
		
		DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
		decoHelperRandomSplit.decos = new DecoBase[]{decoFallenTree2, decoFallenTree1};
		decoHelperRandomSplit.chances = new int[]{12, 1};
		this.addDeco(decoHelperRandomSplit, this.config._boolean(BiomeConfigBOPDeadForest.decorationLogsId));
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
