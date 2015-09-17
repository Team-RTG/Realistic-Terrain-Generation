package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
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
			VanillaBiomes.vanillaRiverCold,
			new TerrainVanillaColdBeach(),
			new SurfaceVanillaColdBeach(BiomeGenBase.coldBeach.topBlock, BiomeGenBase.coldBeach.fillerBlock, BiomeGenBase.coldBeach.topBlock, BiomeGenBase.coldBeach.fillerBlock, (byte)0, 1)
		);
	}	
}
