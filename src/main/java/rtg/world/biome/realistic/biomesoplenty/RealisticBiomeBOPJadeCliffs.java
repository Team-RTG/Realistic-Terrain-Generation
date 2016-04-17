package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPJadeCliffs;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPJadeCliffs;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPJadeCliffs;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;

public class RealisticBiomeBOPJadeCliffs extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.jadeCliffs;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPJadeCliffs(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPJadeCliffs(300f, 120f, 68f),
			new SurfaceBOPJadeCliffs(config, topBlock, fillerBlock, false, null, 0.95f)
		);
		this.generatesEmeralds = true;
		
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 80f;
		decoFallenTree.distribution.noiseFactor = 60f;
		decoFallenTree.distribution.noiseAddend = -15f;
		decoFallenTree.logCondition = LogCondition.X_DIVIDED_BY_STRENGTH;
		decoFallenTree.logConditionNoise = 12f;
		decoFallenTree.logConditionChance = 1;
		decoFallenTree.maxY = 100;
		decoFallenTree.logBlock = BOPCBlocks.logs4;
		decoFallenTree.logMeta = (byte)0;
		decoFallenTree.leavesBlock = Blocks.leaves;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 6;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPJadeCliffs.decorationLogsId));
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
