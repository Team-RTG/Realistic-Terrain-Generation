package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSpruceWoods;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSpruceWoods;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSpruceWoods;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPSpruceWoods extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.spruceWoods;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPSpruceWoods(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPSpruceWoods(58f, 76f, 20f),
			new SurfaceBOPSpruceWoods(config, 
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
		decoBoulder.maxY = 80;
		decoBoulder.chance = 16;
		decoBoulder.strengthFactor = 1f;
		this.addDeco(decoBoulder);
        
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 100f;
		decoFallenTree.distribution.noiseFactor = 6f;
		decoFallenTree.distribution.noiseAddend = 0.8f;
		decoFallenTree.logCondition = LogCondition.RANDOM_CHANCE;
		decoFallenTree.logConditionChance = 24;
		decoFallenTree.randomLogBlocks = new Block[]{Blocks.log, Blocks.log};
		decoFallenTree.randomLogMetas = new byte[]{0, 1};
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 4;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPSpruceWoods.decorationLogsId));
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
