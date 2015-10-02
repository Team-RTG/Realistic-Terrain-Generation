package rtg.world.biome.realistic.thaumcraft;

import rtg.config.ConfigTC;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.thaumcraft.SurfaceTCTaintedLand;
import rtg.world.gen.terrain.thaumcraft.TerrainTCTaintedLand;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTCTaintedLand extends RealisticBiomeTCBase
{	
	public RealisticBiomeTCTaintedLand(BiomeGenBase tcBiome)
	{
		super(
			tcBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainTCTaintedLand(),
			new SurfaceTCTaintedLand(tcBiome.topBlock, tcBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("Thaumcraft Tainted Land");
		BiomeGenManager.addWarmBiome(this, ConfigTC.weightTCTaintedLand);
	}
}
