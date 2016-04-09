package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSeasonalForest;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSeasonalForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSeasonalForest;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPSeasonalForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.seasonalForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPSeasonalForest(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPSeasonalForest(15f, 80f, 68f, 170f),
			new SurfaceBOPSeasonalForest(config,
                topBlock, //Block top 
                (byte)0, //byte topByte
                fillerBlock, //Block filler, 
                (byte)0, //byte fillerByte
                topBlock, //Block mixTop, 
                (byte)0, //byte mixTopByte, 
                fillerBlock, //Block mixFill, 
                (byte)0, //byte mixFillByte,
                0.5f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
                
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.cobblestone;
		decoBoulder.maxY = 80;
		decoBoulder.chance = 16;
		decoBoulder.strengthFactor = 1f;
		this.addDeco(decoBoulder);

		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 80f;
		decoFallenTree.distribution.noiseFactor = 60f;
		decoFallenTree.distribution.noiseAddend = -15f;
		decoFallenTree.logCondition = LogCondition.RANDOM_CHANCE;
		decoFallenTree.logConditionChance = 6;
		decoFallenTree.maxY = 100;
		decoFallenTree.randomLogBlocks = new Block[]{Blocks.log2, Blocks.log, Blocks.log};
		decoFallenTree.randomLogMetas = new byte[]{1, 0, 2};
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 4;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPSeasonalForest.decorationLogsId));
	}
}
