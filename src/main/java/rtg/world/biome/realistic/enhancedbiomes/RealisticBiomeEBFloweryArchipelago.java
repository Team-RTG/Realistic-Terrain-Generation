package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBFloweryArchipelago;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBFloweryArchipelago;

public class RealisticBiomeEBFloweryArchipelago extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBFloweryArchipelago(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBFloweryArchipelago(),
			new SurfaceEBFloweryArchipelago(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Flowery Archipelago");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBFloweryArchipelago;
	}
}
