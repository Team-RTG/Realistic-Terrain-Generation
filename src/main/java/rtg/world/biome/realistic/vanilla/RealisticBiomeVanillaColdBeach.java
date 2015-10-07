package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdBeach;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdBeach;

public class RealisticBiomeVanillaColdBeach extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.coldBeach.topBlock;
	public static Block fillerBlock = BiomeGenBase.coldBeach.fillerBlock;
	
	public RealisticBiomeVanillaColdBeach()
	{
		super(
			BiomeGenBase.coldBeach,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainVanillaColdBeach(),
			new SurfaceVanillaColdBeach(topBlock, fillerBlock, topBlock, fillerBlock, (byte)0, 1)
		);
		
		this.setRealisticBiomeName("Vanilla Cold Beach");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigRTG.weightVanillaColdBeach;
	}	
}
