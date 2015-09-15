package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaBeach;
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
			VanillaBiomes.vanillaRiverOasis,
			new CoastVanillaBeach(),
			new TerrainVanillaBeach(),
			new SurfaceVanillaBeach(BiomeGenBase.beach.topBlock, BiomeGenBase.beach.fillerBlock, BiomeGenBase.beach.topBlock, BiomeGenBase.beach.fillerBlock, (byte)0, 1)
		);
	}	
}
