package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBEphemeralLakeEdge;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBEphemeralLakeEdge;

public class RealisticBiomeEBEphemeralLakeEdge extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBEphemeralLakeEdge(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
			new TerrainEBEphemeralLakeEdge(),
			new SurfaceEBEphemeralLakeEdge(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Ephemeral Lake Edge");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBEphemeralLakeEdge);
	}
}
