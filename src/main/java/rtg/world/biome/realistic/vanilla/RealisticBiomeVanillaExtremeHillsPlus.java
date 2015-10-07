package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHillsPlus;
import rtg.world.gen.terrain.vanilla.TerrainVanillaExtremeHillsPlus;

public class RealisticBiomeVanillaExtremeHillsPlus extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.extremeHillsPlus.topBlock;
	public static Block fillerBlock = BiomeGenBase.extremeHillsPlus.fillerBlock;
	
	public RealisticBiomeVanillaExtremeHillsPlus()
	{
		super(
			BiomeGenBase.extremeHillsPlus,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainVanillaExtremeHillsPlus(),
			new SurfaceVanillaExtremeHillsPlus(Blocks.gravel, Blocks.stone, false, null, 1f, 1.5f, 85f, 20f, 4f)
		);
		
		this.setRealisticBiomeName("Vanilla Extreme Hills +");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigRTG.weightVanillaExtremeHillsPlus;
	}	
}
