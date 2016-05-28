package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaIcePlains;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIcePlains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaIcePlains;

public class RealisticBiomeVanillaIcePlains extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.icePlains.topBlock;
	public static Block fillerBlock = BiomeGenBase.icePlains.fillerBlock;
	
	public RealisticBiomeVanillaIcePlains(BiomeConfig config)
	{
		super(config, 
			BiomeGenBase.icePlains,
			BiomeGenBase.frozenRiver,
			new TerrainVanillaIcePlains(),
			new SurfaceVanillaIcePlains(config, topBlock, fillerBlock, topBlock, topBlock)
		);
		
		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
        
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.checkRiver = true;
		decoBoulder.minRiver = 0.87f;
		decoBoulder.boulderBlock = Blocks.cobblestone;
		decoBoulder.chance = 16;
		decoBoulder.maxY = 95;
		decoBoulder.strengthFactor = 5f;
		this.addDeco(decoBoulder);
        
        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 24;
        decoFallenTree.maxY = 90;
        decoFallenTree.logBlock = Blocks.log;
        decoFallenTree.logMeta = (byte)1;
        decoFallenTree.leavesBlock = Blocks.leaves;
        decoFallenTree.leavesMeta = (byte)-1;
        decoFallenTree.minSize = 1;
        decoFallenTree.maxSize = 5;        
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaIcePlains.decorationLogsId));
	}
}
