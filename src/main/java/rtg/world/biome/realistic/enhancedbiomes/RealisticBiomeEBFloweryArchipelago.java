package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBFloweryArchipelago;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBFloweryArchipelago;

public class RealisticBiomeEBFloweryArchipelago extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBFloweryArchipelago(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainEBFloweryArchipelago(),
			new SurfaceEBFloweryArchipelago(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Flowery Archipelago");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigEB.weightEBFloweryArchipelago;
	}
}
