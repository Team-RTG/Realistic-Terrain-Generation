package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPFen;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPFen;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPFen;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;

public class RealisticBiomeBOPFen extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.fen;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPFen(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPFen(),
			new SurfaceBOPFen(config,
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
		
		DecoBoulder decoBoulder1 = new DecoBoulder();
		decoBoulder1.boulderBlock = Blocks.cobblestone;
		decoBoulder1.maxY = 80;
		decoBoulder1.chance = 12;
		decoBoulder1.strengthFactor = 1f;
		DecoBoulder decoBoulder2 = new DecoBoulder();
		decoBoulder2.boulderBlock = Blocks.cobblestone;
		decoBoulder2.maxY = 80;
		decoBoulder2.chance = 12;
		decoBoulder2.strengthFactor = 1f;
		DecoHelper5050 decoHelper5050 = new DecoHelper5050(decoBoulder1, decoBoulder2);
		this.addDeco(decoHelper5050);
        
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 100f;
		decoFallenTree.distribution.noiseFactor = 6f;
		decoFallenTree.distribution.noiseAddend = 0.8f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 16;
		decoFallenTree.logBlock = BOPCBlocks.logs3;
		decoFallenTree.logMeta = (byte)2;
		decoFallenTree.leavesBlock = Blocks.leaves;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 5;
		DecoFallenTree decoFallenTree2 = new DecoFallenTree();
		decoFallenTree2.distribution.noiseDivisor = 100f;
		decoFallenTree2.distribution.noiseFactor = 6f;
		decoFallenTree2.distribution.noiseAddend = 0.8f;
		decoFallenTree2.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree2.logConditionNoise = 0f;
		decoFallenTree2.logConditionChance = 16;
		decoFallenTree2.maxY = 100;
		decoFallenTree2.logBlock = Blocks.log2;
		decoFallenTree2.logMeta = (byte)1;
		decoFallenTree2.leavesBlock = Blocks.leaves;
		decoFallenTree2.leavesMeta = (byte)-1;
		decoFallenTree2.minSize = 3;
		decoFallenTree2.maxSize = 5;
		DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
		decoHelperRandomSplit.decos = new DecoBase[]{decoFallenTree, decoFallenTree2};
		decoHelperRandomSplit.chances = new int[]{4, 1};
		this.addDeco(decoHelperRandomSplit, this.config._boolean(BiomeConfigBOPFen.decorationLogsId));
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
