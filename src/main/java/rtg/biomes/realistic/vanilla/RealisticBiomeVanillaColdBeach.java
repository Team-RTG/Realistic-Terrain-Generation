package rtg.biomes.realistic.vanilla;

import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaColdBeach;
import rtg.surface.vanilla.SurfaceVanillaColdBeach;
import rtg.terrain.vanilla.TerrainVanillaColdBeach;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaColdBeach extends RealisticBiomeVanilla
{	
	public RealisticBiomeVanillaColdBeach()
	{
		super(
			BiomeGenBase.coldBeach,
			RTGBiomes.baseRiverCold,
			new CoastVanillaColdBeach(),
			new TerrainVanillaColdBeach(),
			new SurfaceVanillaColdBeach(BiomeGenBase.coldBeach.topBlock, BiomeGenBase.coldBeach.fillerBlock, BiomeGenBase.coldBeach.topBlock, BiomeGenBase.coldBeach.fillerBlock, (byte)0, 1)
		);
	}	
}
