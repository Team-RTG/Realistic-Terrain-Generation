package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.vanilla.VanillaBiomes.Climate;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.vanilla.SurfaceVanillaBeach;
import rtg.terrain.vanilla.TerrainVanillaBeach;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaBeach extends RealisticBiomeVanilla
{	
	public RealisticBiomeVanillaBeach()
	{
		super(
			BiomeGenBase.beach,
			VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainVanillaBeach(),
			new SurfaceVanillaBeach(BiomeGenBase.beach.topBlock, BiomeGenBase.beach.fillerBlock, BiomeGenBase.beach.topBlock, BiomeGenBase.beach.fillerBlock, (byte)0, 1)
		);
	}	
}
