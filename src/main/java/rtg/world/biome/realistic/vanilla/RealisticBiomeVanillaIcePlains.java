package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaIcePlains;
import rtg.util.BlockUtil;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIcePlains;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaIcePlains extends RealisticBiomeVanillaBase {

	public static Biome biome = Biomes.ICE_PLAINS;
	public static Biome river = Biomes.FROZEN_RIVER;
	
	public RealisticBiomeVanillaIcePlains(BiomeConfig config)
	{
		super(config, biome, river,
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
        decoFallenTree.logBlock = BlockUtil.getStateLog(1);
        decoFallenTree.leavesBlock = BlockUtil.getStateLeaf(1);
        decoFallenTree.minSize = 1;
        decoFallenTree.maxSize = 5;        
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaIcePlains.decorationLogsId));
	}

	@Override
	public TerrainBase initTerrain() {

		return new TerrainVanillaIcePlains();
	}

	public class TerrainVanillaIcePlains extends TerrainBase {

		public TerrainVanillaIcePlains() {

		}

		@Override
		public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

			return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 200f, 65f);
		}
	}
}
