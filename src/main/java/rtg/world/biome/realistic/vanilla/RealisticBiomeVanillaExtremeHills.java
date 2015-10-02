package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaExtremeHills;

public class RealisticBiomeVanillaExtremeHills extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.extremeHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.extremeHills.fillerBlock;
	
	public RealisticBiomeVanillaExtremeHills()
	{
		super(
			BiomeGenBase.extremeHills,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainVanillaExtremeHills(0f, 140f, 68f, 150f),
			new SurfaceVanillaExtremeHills(topBlock, fillerBlock, false, null, 1f, 1.5f, 85f, 20f, 4f)
		);
		
		this.setRealisticBiomeName("Vanilla Extreme Hills");
		BiomeGenManager.addCoolBiome(this, ConfigRTG.weightVanillaExtremeHills);
	}	
}
