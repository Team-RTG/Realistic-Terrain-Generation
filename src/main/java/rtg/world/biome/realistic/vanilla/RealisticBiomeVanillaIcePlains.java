package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaIcePlains;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIcePlains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaIcePlains;

public class RealisticBiomeVanillaIcePlains extends RealisticBiomeVanillaBase {

	public static Biome biome = Biomes.ICE_PLAINS;
	public static Biome river = Biomes.FROZEN_RIVER;
	
	public RealisticBiomeVanillaIcePlains(BiomeConfig config)
	{
		super(config, biome, river,
			new TerrainVanillaIcePlains(),
			new SurfaceVanillaIcePlains(config, biome.topBlock, biome.fillerBlock, biome.topBlock, biome.topBlock)
		);

		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
        
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.checkRiver = true;
		decoBoulder.minRiver = 0.87f;
		decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
		decoBoulder.chance = 16;
		decoBoulder.maxY = 95;
		decoBoulder.strengthFactor = 5f;
		this.addDeco(decoBoulder);
        
        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 24;
        decoFallenTree.logBlock = Blocks.LOG.getStateFromMeta(1);
        decoFallenTree.leavesBlock = Blocks.LEAVES.getStateFromMeta(1);
        decoFallenTree.minSize = 1;
        decoFallenTree.maxSize = 5;        
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaIcePlains.decorationLogsId));
	}
}
