package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBEphemeralLake;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBEphemeralLake;

public class RealisticBiomeEBEphemeralLake extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBEphemeralLake(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
			new TerrainEBEphemeralLake(),
			new SurfaceEBEphemeralLake(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Ephemeral Lake");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBEphemeralLake);
	}
}
