package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBIceSheet;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBIceSheet;

public class RealisticBiomeEBIceSheet extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBIceSheet(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
			new TerrainEBIceSheet(),
			new SurfaceEBIceSheet(Blocks.ice, Blocks.packed_ice)
		);
		
		this.setRealisticBiomeName("EB Ice Sheet");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBIceSheet);
	}
}
