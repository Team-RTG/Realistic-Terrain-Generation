package rtg.world.biome.realistic.vanilla;

import rtg.surface.vanilla.SurfaceVanillaColdBeach;
import rtg.terrain.vanilla.TerrainVanillaColdBeach;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaColdBeach extends RealisticBiomeVanillaBase
{	
	public RealisticBiomeVanillaColdBeach()
	{
		super(
			BiomeGenBase.coldBeach,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainVanillaColdBeach(),
			new SurfaceVanillaColdBeach(BiomeGenBase.coldBeach.topBlock, BiomeGenBase.coldBeach.fillerBlock, BiomeGenBase.coldBeach.topBlock, BiomeGenBase.coldBeach.fillerBlock, (byte)0, 1)
		);
	}	
}
