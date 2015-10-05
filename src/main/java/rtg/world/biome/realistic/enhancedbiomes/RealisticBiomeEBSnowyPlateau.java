package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSnowyPlateau;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSnowyPlateau;

public class RealisticBiomeEBSnowyPlateau extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSnowyPlateau(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBSnowyPlateau(),
			new SurfaceEBSnowyPlateau(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Snowy Plateau");
		this.biomeWeight = ConfigEB.weightEBSnowyPlateau;
	}
}
