package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdTaigaHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdTaigaHills;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaColdTaigaHills extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.coldTaigaHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.coldTaigaHills.fillerBlock;
	
	public RealisticBiomeVanillaColdTaigaHills()
	{
		super(
			BiomeGenBase.coldTaigaHills,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainVanillaColdTaigaHills(),
			new SurfaceVanillaColdTaigaHills(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("Vanilla Cold Taiga Hills");
		this.biomeCategory = BiomeCategory.SNOW;
		this.biomeWeight = ConfigVanilla.weightVanillaColdTaigaHills;
	}	
}
