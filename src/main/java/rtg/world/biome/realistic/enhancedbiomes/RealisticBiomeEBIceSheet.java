package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBIceSheet;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBIceSheet;

public class RealisticBiomeEBIceSheet extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBIceSheet(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainEBIceSheet(),
			new SurfaceEBIceSheet(Blocks.ice, Blocks.packed_ice)
		);
		
		this.setRealisticBiomeName("EB Ice Sheet");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBIceSheet;
	}
}
