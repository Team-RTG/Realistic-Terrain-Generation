package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSeasonalForestClearing;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSeasonalForestClearing;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSeasonalForestClearing;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPSeasonalForestClearing extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.seasonalForestClearing;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPSeasonalForestClearing(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPSeasonalForestClearing(65f, 68f, 24f),
			new SurfaceBOPSeasonalForestClearing(config, topBlock, fillerBlock)
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
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPSeasonalForestClearing.decorationLogsId));
	}
}
