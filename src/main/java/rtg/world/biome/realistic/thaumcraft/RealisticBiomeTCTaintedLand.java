package rtg.world.biome.realistic.thaumcraft;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.thaumcraft.ConfigTC;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.thaumcraft.SurfaceTCTaintedLand;
import rtg.world.gen.terrain.thaumcraft.TerrainTCTaintedLand;

public class RealisticBiomeTCTaintedLand extends RealisticBiomeTCBase
{	
	public RealisticBiomeTCTaintedLand(BiomeGenBase tcBiome)
	{
		super(
			tcBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainTCTaintedLand(),
			new SurfaceTCTaintedLand(tcBiome.topBlock, tcBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("Thaumcraft Tainted Land");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigTC.weightTCTaintedLand;
		this.generateVillages = ConfigTC.villageTCTaintedLand;
	}
}
